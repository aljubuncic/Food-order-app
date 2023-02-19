# Food-order-app
The application provides user a service of ordering varous types of food and drink. App can be used equally for placing a takeaway or delivery order.
User must be logged in or signed up to use this app. App also has admin panel for managing the usres, orders made by users and the current menu of meals.
To run the app, simply position yourself in the directory where you downloaded the app, and type in cmd this two commands:
### mvn clean install
### mvn clean javafx:run
App also has Command Line Interface which can be accessed by simply stating the maven build profile and executing the created jar file in cmd:
### mvn clean install -P cli-app
### java -jar Food-order-app-cli-jar-with-dependencies.jar
