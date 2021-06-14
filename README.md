This Service interacts with 2 other microservices.
1. From 1st microservice, gets hello string as a ResponseEntity.
2. From 2nd Microservice, get concatenated response of the json fields data as a string.

Components/Libraries use    d:

1. Lambok - dto objects.
2. Feign Client for inter microservice communications.
3. Seluth for distributed tracing.
4. AOP for Logging/Exception Handling.
5. Swagger UI for documentation.



Swagger UI endpoint:
http://ec2-13-233-245-195.ap-south-1.compute.amazonaws.com:8080/swagger-ui.html#/end-point-resource

Logs can be viewed in each server at
tail -F /tmp/log/service1.log


Endpoints:

POST http://ec2-13-233-245-195.ap-south-1.compute.amazonaws.com:8080/greetPerson

{
    "name": "kapil",
    "surname" : "ankam"
}

GET http://ec2-13-233-245-195.ap-south-1.compute.amazonaws.com:8080/healthcheck
