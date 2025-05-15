@echo off
echo Stopping ENSAHOME Components...

REM Stop Spring Boot Application (if running)
taskkill /F /IM java.exe 2>nul

REM Stop MongoDB Containers
echo Stopping MongoDB containers...
docker stop mongo-kh-1 mongo-kh-2 mongo-kh-3
docker stop mongo-mar-1 mongo-mar-2 mongo-mar-3
docker stop mongo-ag-1 mongo-ag-2 mongo-ag-3

REM Stop Redis Containers
echo Stopping Redis containers...
docker stop redis-khouribga redis-marrakech redis-agadir

REM Remove Containers
echo Removing containers...
docker rm mongo-kh-1 mongo-kh-2 mongo-kh-3
docker rm mongo-mar-1 mongo-mar-2 mongo-mar-3
docker rm mongo-ag-1 mongo-ag-2 mongo-ag-3
docker rm redis-khouribga redis-marrakech redis-agadir

REM Remove Network
echo Removing network...
docker network rm ensahome-network 2>nul

echo ENSAHOME Components stopped successfully! 