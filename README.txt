Air Company app

1) open Pet-Air-Company folder with IDE
2) do changes in application properties for your db (not for tests)
3) run app, it will create tables in db 
4) run maven package to generate jar file. It shoud be named air-company-0.0.1-SNAPSHOT.jar
5) run docker-compose file to start your app and db in docker
6) your dockerized app will be available on port 8091
you can access swagger by http://localhost:8091/swagger-ui.html#/
7) your db will be available on port 5433
8) to fill your db - execute resources/test-data.sql in appropriate db
9) run test FlightControllerTest
10) import postman collection and run after executing test-data.sql once


when you will use api's ->
- if you dont want to assigne airplaine to airplane company when creating it -> pass air company id 0
- There are two air company types -> "INTERNATIONAL" and "REGIONAL"
- When you change flight status, you must follow rules 
PENDING -> ACTIVE, PENDING -> DELAYED, ACTIVE -> COMPLETED, COMPLETED -> PENDING
