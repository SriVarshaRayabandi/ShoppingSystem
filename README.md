# Shopping System

A simple yet comprehensive shopping system developed in Java. This project demonstrates the application of SOLID principles, Singleton, Factory, and Builder design patterns. It features user authentication, product catalog management, shopping cart functionality, order processing, payment simulation, and basic logging.

Github Repo: https://github.com/SriVarshaRayabandi/ShoppingSystem

## Prerequisites

- Java JDK 11 or later
- Maven (for dependency management)
- IntelliJ IDEA or any preferred IDE with Maven support

## Running the Application

- *IntelliJ IDEA (Which I have used)*

- Navigate to the MainClass in the gui package (Path : FinalProject > ShoppingSystem > src > gui)
- Right-click on MainClass.java and select Run 'MainClass.main()'.
- The GUI of the shopping system will launch.

## Using the Application

1. *Register / Login*

- On starting the application, you'll be greeted with a login screen.
- If it's your first time, click on the Register button after entering a username and password.
- Log in with the credentials you used to register

2. *Browse Products*

- After logging in, you'll see the product catalog.
- Use the Add to Cart button to add products to your shopping cart.

3. *Manage Cart*

- View your cart by clicking on the View Cart button at the bottom.
- Here, you can remove items or clear the cart.

4. *Checkout*

- In the cart view, proceed with Checkout.
- Fill in the mock payment details and submit.

5. *Exit*

- Close the application window at any time to exit.

## Logging

- The application logs important events, such as order creation and finalization, to the console and a logs.txt file in the project root directory