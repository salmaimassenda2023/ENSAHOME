@echo off
echo Testing MongoDB and Redis Connections...

echo.
echo Testing Khouribga MongoDB Replica Set...
docker exec mongo-kh-1 mongosh --eval "db.runCommand({ ping: 1 })"
docker exec mongo-kh-1 mongosh --eval "rs.status()"

echo.
echo Testing Marrakech MongoDB Replica Set...
docker exec mongo-mar-1 mongosh --eval "db.runCommand({ ping: 1 })"
docker exec mongo-mar-1 mongosh --eval "rs.status()"

echo.
echo Testing Agadir MongoDB Replica Set...
docker exec mongo-ag-1 mongosh --eval "db.runCommand({ ping: 1 })"
docker exec mongo-ag-1 mongosh --eval "rs.status()"

echo.
echo Testing Redis Connections...
docker exec redis-khouribga redis-cli ping
docker exec redis-marrakech redis-cli ping
docker exec redis-agadir redis-cli ping

echo.
echo Testing Spring Boot Application...
curl http://localhost:8080/actuator/health

echo.
echo All tests completed! 