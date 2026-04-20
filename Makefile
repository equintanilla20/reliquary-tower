# Variables
DOCKER_COMP_FILE = docker-compose.yml

.PHONY: rebuild-all clean-start stop start

# The main command you requested
rebuild:
	docker-compose down -v
	cd server && mvn clean install -DskipTests
	docker-compose up -d --build

# Helpful shortcut to just wipe and restart without a full Maven build
clean-start:
	docker-compose down -v
	docker-compose up -d

# Stop without destroying data
stop:
	docker-compose stop

# Resume from stop
start:
	docker-compose start