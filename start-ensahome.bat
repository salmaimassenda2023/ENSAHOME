@echo off
echo Checking Docker availability...

REM Check if Docker is running
docker info > nul 2>&1
if %errorlevel% neq 0 (
    echo Error: Docker is not running or not installed!
    echo Please make sure Docker Desktop is installed and running.
    echo You can download Docker Desktop from: https://www.docker.com/products/docker-desktop
    pause
    exit /b 1
)

echo Docker is running, proceeding with startup...
echo.

REM Clean up existing containers
echo Cleaning up existing containers...
docker stop mongo-kh-1 mongo-kh-2 mongo-kh-3 mongo-mar-1 mongo-mar-2 mongo-mar-3 mongo-ag-1 mongo-ag-2 mongo-ag-3 redis-khouribga redis-marrakech redis-agadir 2>nul
docker rm mongo-kh-1 mongo-kh-2 mongo-kh-3 mongo-mar-1 mongo-mar-2 mongo-mar-3 mongo-ag-1 mongo-ag-2 mongo-ag-3 redis-khouribga redis-marrakech redis-agadir 2>nul

REM Create network if it doesn't exist
docker network create ensahome-network 2>nul

REM Start MongoDB Replica Sets
echo Starting MongoDB Replica Sets...

REM Khouribga Replica Set
docker run -d --name mongo-kh-1 --network ensahome-network -p 27017:27017 mongo:latest --replSet khouribga-rs --bind_ip_all
docker run -d --name mongo-kh-2 --network ensahome-network -p 27018:27017 mongo:latest --replSet khouribga-rs --bind_ip_all
docker run -d --name mongo-kh-3 --network ensahome-network -p 27019:27017 mongo:latest --replSet khouribga-rs --bind_ip_all

REM Marrakech Replica Set
docker run -d --name mongo-mar-1 --network ensahome-network -p 27020:27017 mongo:latest --replSet marrakech-rs --bind_ip_all
docker run -d --name mongo-mar-2 --network ensahome-network -p 27021:27017 mongo:latest --replSet marrakech-rs --bind_ip_all
docker run -d --name mongo-mar-3 --network ensahome-network -p 27022:27017 mongo:latest --replSet marrakech-rs --bind_ip_all

REM Agadir Replica Set
docker run -d --name mongo-ag-1 --network ensahome-network -p 27023:27017 mongo:latest --replSet agadir-rs --bind_ip_all
docker run -d --name mongo-ag-2 --network ensahome-network -p 27024:27017 mongo:latest --replSet agadir-rs --bind_ip_all
docker run -d --name mongo-ag-3 --network ensahome-network -p 27025:27017 mongo:latest --replSet agadir-rs --bind_ip_all

REM Start Redis Servers
echo Starting Redis Servers...

REM Khouribga Redis
docker run -d --name redis-khouribga --network ensahome-network -p 6379:6379 redis:latest

REM Marrakech Redis
docker run -d --name redis-marrakech --network ensahome-network -p 6380:6379 redis:latest

REM Agadir Redis
docker run -d --name redis-agadir --network ensahome-network -p 6381:6379 redis:latest

REM Wait for services to be ready
echo Waiting for services to be ready...
powershell -Command "Start-Sleep -Seconds 15"

REM Initialize MongoDB Replica Sets
echo Initializing MongoDB Replica Sets...

REM Khouribga Replica Set
docker exec mongo-kh-1 mongosh --eval "rs.initiate({_id: 'khouribga-rs', members: [{_id: 0, host: 'mongo-kh-1:27017'}, {_id: 1, host: 'mongo-kh-2:27017'}, {_id: 2, host: 'mongo-kh-3:27017'}]})"

REM Marrakech Replica Set
docker exec mongo-mar-1 mongosh --eval "rs.initiate({_id: 'marrakech-rs', members: [{_id: 0, host: 'mongo-mar-1:27017'}, {_id: 1, host: 'mongo-mar-2:27017'}, {_id: 2, host: 'mongo-mar-3:27017'}]})"

REM Agadir Replica Set
docker exec mongo-ag-1 mongosh --eval "rs.initiate({_id: 'agadir-rs', members: [{_id: 0, host: 'mongo-ag-1:27017'}, {_id: 1, host: 'mongo-ag-2:27017'}, {_id: 2, host: 'mongo-ag-3:27017'}]})"

REM Wait for replica sets to be ready
echo Waiting for replica sets to be ready...
powershell -Command "Start-Sleep -Seconds 15"

REM Check replica set status
echo Checking replica set status...
docker exec mongo-kh-1 mongosh --eval "rs.status()"
docker exec mongo-mar-1 mongosh --eval "rs.status()"
docker exec mongo-ag-1 mongosh --eval "rs.status()"

REM Start Spring Boot Application
echo Starting Spring Boot Application...
cd ensahome_backend
mvn spring-boot:run

echo ENSAHOME Components started successfully! 