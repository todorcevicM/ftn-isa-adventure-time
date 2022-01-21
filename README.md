# ftn-isa-adventure-time

## Project Description

Adventure Time is a web app designed for booking fishing adventures, cottages and boats.\
It can be used by fishing instructors, cottage owners, boat owners, and their customers.

![name](https://i.imgur.com/Ng4RZ0d.png)

## Libraries and Dependencies

### Vue

1. [axios](https://www.npmjs.com/package/axios)
2. [@fawmi/vue-google-maps](https://www.npmjs.com/package/@fawmi/vue-google-maps) 
3. [vue-simple-calendar](https://www.npmjs.com/package/vue-simple-calendar)

### Spring Boot

1. spring-boot-starter-data-jpa
2. spring-boot-starter-security
3. spring-boot-starter-web
4. mysql-connector-java
5. lombok
6. spring-boot-email-core

## Installation & Launch

### Git

* Clone the repository using\
`git clone https://github.com/todorcevicM/ftn-isa-adventure-time`

### MySQL

* Install MySQL DBMS and create a connection to a new database named `atdb`.
* Create a new user with credentials `newuser` as username and `adventuretime` as password.
* The database will be initialized using the following [script](https://github.com/todorcevicM/ftn-isa-adventure-time/blob/main/src/main/resources/data.sql).
* Make sure the service is running when starting up the server.

### NPM & Vue

* Install Node.js.
* Install Vue from NPM using \
`npm install -g @vue/cli`
* Head into `/src/frontend` and start the server using\
`npm run serve`
* The frontend server is running on port `:3000`.

### Java & Spring Boot

* Install Java JDK 11.
* Open the project in a desired Java IDE.
* Clean and package the project using Maven from the IDE.
* Once the build is complete, start the Java project from the IDE.
* The backend server is running on port `:8080`.

**The application is available on `localhost:3000/`**.

## Developers

[Nikola Matijević IN2-2018](https://github.com/Nikola102)\
[Đorđe Stanković IN13-2018](https://github.com/stankovictab)\
[Marko Todorčević IN3-2018](https://github.com/todorcevicM)

## License

[MIT License](/LICENSE)