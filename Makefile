DOCKER_COMP_FILE = docker-compose.yml

.PHONY: rebuild-all clean-start stop start

rebuild:
	docker-compose down -v
	cd server && mvn clean install
	docker-compose up -d --build

clean-start:
	docker-compose down -v
	docker-compose up -d

stop:
	docker-compose stop

start:
	docker-compose start