# SpringMicroservices
Contains spring boot microservices beginner to master

To interact between microservices we need to use webclient or resttemplate here i have used webclient

we can create a config file in the project from where all other microservices are called.

To call another service using webclient. 

webclient.get().uri("http://localhost:8080/api/inventory", uriBuilder -> uriBuilder.queryParam("name", value).build())
.retrieve().bodyToMono(ResponseDtoclassname.class).block();

In the above code block() method is added to make a sync request and bodyToMono() to retrieve value from that microservice. 

Service discovery:

It is like a database of all the microservices information
it has the ip address and name of the microservices 

when you want to call a microservices the call first will go to service discovery 
it'll look for the microservice and then redirect it to that microservice.

For service discovery we use Spring cloud netflix eureka server and client

Suppose if we have two microservices in same name then it might throw error

To split the load between them we can use the annotation loadbalanced in the SpringApplication.class file of the microservice

On doing so it'll automatically redirect the url.
