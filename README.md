# IndustryCompanyProductController
Spring Boot project about Industry , Company and Product microservices and the communications between them

 In this scenario, we have three microservices: Product, Company, and Industry, along with an API Gateway, a Service Registry (Eureka Server), and a Config microservice that handles common settings configured on Git.

Product Microservice:

Handles operations related to products, such as creating, updating, and retrieving product information.
Exposes RESTful APIs to interact with product data.
Communicates with the Config microservice to fetch common settings.
Company Microservice:

Manages company-related functionalities like creating, updating, and retrieving company information.
Provides RESTful APIs for interacting with company data.
Utilizes the Config microservice to access common settings.
Industry Microservice:

Deals with industry-specific operations like managing industries, their details, and related data.
Exposes RESTful APIs to perform industry-related tasks.
Connects with the Config microservice to obtain common settings.
API Gateway:

Acts as a single entry point for all the microservices.
Handles client requests and forwards them to the appropriate microservices.
Provides routing, load balancing, authentication, and other cross-cutting concerns.
Ensures security and simplifies the overall architecture.
Service Registry (Eureka Server):

Functions as a central registry where all microservices register themselves.
Allows other services to discover and communicate with each other dynamically.
Provides high availability and fault tolerance by tracking the health of registered services.
Config Microservice:

Handles the configuration of common settings shared across microservices.
Stores the configuration files on a Git repository.
Microservices can fetch their respective configurations from the Config microservice during startup.
By using Spring Boot and these microservices, you can achieve a scalable and modular architecture. The API Gateway, Service Registry, and Config microservice enhance the system's flexibility, maintainability, and ease of deployment.
