DOCKER_COMP_FILE = docker-compose.yml

.PHONY: rebuild rebuild-backend test clean-start stop start

rebuild-all:
	docker-compose down -v
	cd server && mvn clean install -DskipTests
	docker-compose up -d --build

rebuild-backend:
	docker-compose down
	cd server && mvn clean install -DskipTests
	docker-compose up -d --build server

test:
	cd server && mvn test

clean-start:
	docker-compose down -v
	docker-compose up -d

stop:
	docker-compose stop

start:
	docker-compose start
