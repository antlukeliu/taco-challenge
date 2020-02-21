# Taco Order Total Webservice


### To Run Application
`./mvnw spring-boot:run`


### Swagger-UI
Once SpringBoot has started you can navigate to
`http://localhost:8080/swagger-ui.html` for the swagger documentation

### Build Docker Image
1. run `./mvnw clean package` to generate the jar

2. `docker build -t taco-order-service .`

3. `docker-compose up` -d (optional if you wanted it "detached" mode