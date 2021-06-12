This Service interacts with 2 other microservices.
1. From 1st microservice, gets hello string as a ResponseEntity.
2. From 2nd Microservice, get concatenated response of the json fields data as a string.

Components/Libraries usesd:

1. Lambok - dto objects.
2. Feign Client for inter microservice communications.
3. Seluth for distributed tracing.
4. AOP for logging/Exception Handling.
5. Swagger UI for documentation.