# rating-app-backend

This repository contains a Spring Boot application. Its main purpose is to collect simple ratings based on user experience and provide ratings for
statistics that can be displayed in a chronological manner. 

We use the following technologies:

* Java 11
* Maven 
* MariaDB with phpMyAdmin as its administration tool
* Docker
* Spring Boot
* Spring Security
* Spring Data JPA with Hibernate as its default implementation
* Java Validation API
* Lombok
* Flyway
* Swagger 
* Pusher

## Prerequisites and getting started
In order to run the application you need to have Docker and docker-compose installed on your machine.

When you have docker up and running you need to navigate to the project folder that you cloned. Final step is to execute the following command that will run our bash script:

* `bash rating-app-backend.sh`

It will clone two other projects (Vue.js apps - our front side), as well as this one, and store it in /rating-application folder. To conclude, it will also run `docker-compose up -d` command. 

After everything has started you have your application running on http://localhost:8080/, backend on http://localhost:8081/ and phpMyAdmin on http://localhost:8000/. You can access the database 
"rating_app" with the following credentials:
* username: root
* password: password123

If you wish to clone other projects separately you can find them on following links:
* [Rating app - dashboard](https://github.com/MonikaBrzica/rating-app)
* [Rating app - public view](https://github.com/Andric34/rating-app-client)


### Emoji
Emoji entity is defined as a rating category in itself. We use it for evaluation of our app through ratings. In our current setup, we store five different emojis (`VERY_HAPPY`, `HAPPY`, `MEH`, `SAD` and `VERY_SAD`). Besides that, Emoji also provide color and image values to our frontend.

* Emoji controller

Controller contains only one API endpoint that returns a list of emojis that are currently in use based on our rating settings.

### Rating settings
Rating settings is instrumental for setting our basic rating features. With this setting we can set a number of displayed emojis (3-5), our feedback message (3-120 chars) and a timeout after which our message is shown (0-10s). 

### Emoji config
With emoji config we define all possible variations of our rating settings. 

Example:

* Emoji config can define that rating setting with number of emojis set to 3 contains following emojis: `VERY_HAPPY`, `MEH`, and `VERY_SAD`.

### Rating
Rating is connected to Emoji entity with `@ManyToOne` relationship. This helps us to store data that contains an emoji (describes user experience) and a timestamp (current datetime of a rating). 

* Rating controller

Controller contains API endpoints for posting a rating, retrieving current rating settings, updating rating settings and retrieving ratings between two dates.

### Authorization

* Our application has two roles: `USER` and `ADMIN`. 
* For user authentication we use Google OAuth 2.0 (on our SPA). 
* Session management is set to `STATELESS` (we're using Google Access Token to retrieve protected resources).


