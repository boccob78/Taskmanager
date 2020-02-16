# Omni:us Task Manager code challenge

Simulate a basic Task management system as a code challenge for Omni:us 

## Getting Started

The task objective is the following:

* Tasks can be postponed with a "remind me at" date for later.
* A scheduler should create new tasks at a random interval
* Tasks should be persisted in the database and should have, at least, the
following fields
  * id (uuid)
  * createdAt
  * updatedAt
  * dueDate
  * resolvedAt
  * title
  * description
  * priority
  * status
* REST Endpoints for communication with the Frontend
* For communication with the frontend, DTOs should be used
* Load the tasks from the backend asynchronously and list them according to
their dueDate and the priority
* If new tasks come in, they should be automatically added to the list of tasks
without the need to manually refresh the page

### Building

To install the sample using Maven, you can use the following Maven command from the root folder of the project

```
mvn install
```

### Running

To run the application in a docker container, navigate to the root folder of the project

```
docker-compose up

```

The application should run from [http://localhost:8080](http://localhost:8080)

The user can choose 3 actions:
* He can choose a future date in the remind me column, which sets the status of the task to DEFERRED. No other functionality is attached to this action
![Remind me](/docs/remind-me.png?raw=true "Remind me")
* He can choose to delete the task which removes it from the list of tasks
![Delete task](/docs/delete.png?raw=true "Delete")
* He can choose to resolve the task, which sets the status to COMPLETED
![Resolve task](/docs/resolve.png?raw=true "Resolve")

## Running the tests

To just run the test packages with Maven, run the following command

```
mvn test
```

## Built With

* [Java](https://www.java.com/) - The language of choice
* [Spring Boot](https://spring.io/projects/spring-boot) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [Docker](https://www.docker.com/) - Container service to run the application

## Documentation

The javadoc for this project can be found [here](https://boccob78.github.io/Taskmanager)

## Authors

* **Farzan Zubair** 
