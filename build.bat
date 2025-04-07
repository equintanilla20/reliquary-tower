@echo off
cd server
mvn clean install
if errorlevel 1 goto mvn_fail
cd ..
docker-compose up --build
if errorlevel 1 goto docker_fail
echo Build and Docker Compose completed successfully.
goto end

:mvn_fail
echo Maven build failed.
goto end

:docker_fail
echo Docker Compose build failed.

:end