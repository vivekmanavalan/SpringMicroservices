# SpringMicroservices
Contains spring boot microservices beginner to master
Programming techie 6hr video


created seperate dto class for product request and reponse so that we don't need to expose the product entity outside

if at all anything new added in entity that does not needed in response we can do that as well

To interact between microservices we need to use webclient

we can create a config file in the project from where all other microservices are called.

To call another service using webclient. 

webclient.get().uri("http://localhost:8080/api/inventory", uriBuilder -> uriBuilder.queryParam("name", value).build())
.retrieve().bodyToMono(ResponseDtoclassname.class).block();

In the above code block() method is added to make a sync request and bodyToMono() to retrieve value from that microservice. 

# Service discovery:

spring boot eureka server/client

It is like a database of all the microservices information
In the registry it has the ip address and name of the microservices 
also creates a copy of it in the microservice as well so that when the discovery server is down still 
we'll be able to call the service

when you want to call a microservices the call first will go to service discovery 
it'll look for the microservice and then redirect it to that microservice.

For service discovery we use Spring cloud netflix eureka server and client

Suppose if we have two microservices in same name then it might throw error

To split the load between them we can use the annotation loadbalanced in the SpringApplication.class file of the microservice

On doing so it'll automatically redirect the url.


# API Gateway:
Spring cloud gateway

It's the place where the request comes in first and from there it is routed to the microservice.
Acts like a a gatekeeper and can implement security mechanism as well.
can also act as loadbalancer
can also perform http communication since it's internal communication inside the gateway

commonly used gateway kong, apigee



keycloak to secure microservices using a api-gateway

Annotate @EnableWebFluxSecurity
create a bean of return type securitywebfilterchain

Steps in keycloak
login using admin admin

create a realm

create a client

get the below config details.

token endpoint taken from open-id conguration in keycloak
http://localhost:9091/realms/microservices/protocol/openid-connect/token

client secret - EettQpavb3qKwyL82wNHvf9hNUY1OLgC


# Circuit Breaker:

When one of two microservices that communicate with eachother scales down or stops 

Then circuit breaker comes into play.

when there is no problem in connection then the state of circuit breaker is closed 

When requests keep failing for some threshold then 

when some problem  happens in one of two microservices then the state changes to open

when the status is open it does not allow any communication to failed microservice we can even write a fallback logic

after keeping it in open for sometime it changes the state to half open to allow only limited requests 

then after sometime it changes the state to closed again when everything fine

Definition:
Use of the Circuit Breaker pattern can allow a microservice to continue operating when a related service fails, 
preventing the failure from cascading and giving the failing service time to recover.


commonly used circuit breaker are:
Netflix Hystrix
Resilience4j

to enable circuit breaker:

management.health.circuitbreakers.enabled=true
management.endpoints.web.exposure.include=*
management.endpoint.health.show-details=always


Resilience4j:

resilience4j.circuitbreaker.instances.inventory.registerHealthIndicator=true
resilience4j.circuitbreaker.instances.inventory.event-consumer-buffer-size=10

Number of fails allowed:
resilience4j.circuitbreaker.instances.inventory.slidingWindowType=COUNT_BASED
resilience4j.circuitbreaker.instances.inventory.slidingWindowSize=5
resilience4j.circuitbreaker.instances.inventory.failureRateThreshold=50  //in percent

Before changing to half open wait time
resilience4j.circuitbreaker.instances.inventory.waitDurationInOpenState=5s
resilience4j.circuitbreaker.instances.inventory.permittedNumberOfCallsInHalfOpenState=3
resilience4j.circuitbreaker.instances.inventory.automaticTransitionFromOpenToHalfOpenEnabled=true


at the parent microservice from where other microservices are called add the below annotation

For us it's the placeOrder method in ordercontroller
@CircuitBreaker(name = "anything" fallbackMethod = "fallbackmethodname")
public String placeOrder(){}


public fallbackmethodname(OrderRequest or, RunTimeException re){
	return "service down";
}
