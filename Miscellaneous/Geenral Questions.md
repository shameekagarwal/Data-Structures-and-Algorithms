# General Questions

## Spring vs Spring Boot

- spring boot extends spring framework
- spring boot has its own embedded tomcat server
- auto configuration - convention over configuration - less boilerplate
- starter dependencies for easier dependency management 
- use spring when we need a lot of tweaking and are not comfortable with out of the box stuff

## Inheritance vs Composition

### Inheritance

- is a relationship
- child class deriving from parent class
- thoda real world / business problem ko address karna chaiye - e.g. animal - dog, cat
- disadvantage - multiple inheritance is difficult / complex hierarchy can become difficult to handle

### Composition

- has a relationship
- e.g. vehicle composed using engine, wheels, etc
- flexibility - use behavior of composed objects - `vehicle.start()` will call rotate on wheels ignite on engine, etc
- advantage - lesser coupling unlike inheritance
