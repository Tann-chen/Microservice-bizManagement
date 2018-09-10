# Welcome to contribution for this open-source ERP system !


## Target of this project
This project is to build a skeleton of enterprise management systems. the common business logic functions are extracted from most enterprise management systems. 
* User Access Control
* Inventory Management
* Accounting Management
* Human Resource Management
* Notification Services

More functions may be included into the list of target of this project. We also welcome you to provide suggestions about business logic requirements.


## Architecture of this project
This project takes use of micro-service architecture, considering the reasons below:
* The project only provides basic functions of enterprise management, it should be extensible, to add more functions specific for companies.
* Core functions in enterprise management are critical, they should be error-tolerant.  


## Technical stacks involved
#### Implementation of service
* Spring Boot
* Spring Security
* Hibernate JPA
* Redis (optional)
* MySQL
* Angular JS
* HTML
* CSS

#### Coordinator among services
* Gateway : Zuul
* Service Registry : Eureka
* Load Balancer & Request Proxy : Ribbon / Feign
* Config Management : Config
* Tracing Monitor : Sleuth + Zipkin

#### Deployment
* Docker
* AWS EC2