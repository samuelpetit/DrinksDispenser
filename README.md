# DrinksDispenser

This application aims to develop a drinks dispenser that gives the change when user buys a drink.

We have two controllers:
	- DrinksDispenserController : the main controller which is used to display customer form in order to buy a drink, but also to allow customer to buy its drink
	- ExceptionHandlingController : the controller which is in charge of all exceptions handling in this application
	
Here, the drinks dispenser is represented by the DrinksDispenserContent object (which is a singleton). It is made up of :
	- A list of Coins (each coin has its value and its quantity) contained in the drinks dispenser
	- A list of Drinks (each drink has its name, its unit price and its quantity)

In order to deploy this application, you have to launch this command: mvn spring-boot:run in the project root
