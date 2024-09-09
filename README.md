Server component of a simple calculator built with Java and Springboot.
Pairs with https://github.com/jonny191095/Calculator-UI

To run the Calculator-Server app locally for testing follow these steps:
 - git clone https://github.com/jonny191095/Calculator-Server.git
 - Open in IDE (eg Intellij)
 - Enable/Import as maven project to allow dependency resolution
 - Execute main class CalculatorApplication.java

App is built with java 17 SDK and will run on port 8080.

Future enhancements:
 - Switch out 4 endpoints for a single "Calculate" endpoint (currently preference but might matter if application grew considerably)
 - Rate limiting
 - Support for additional/more complex operations, e.g. MOD, DIV etc.
 - Support for multiple operations and numbers within single request. Handling operator precedence.
 - Support for multiple/different validation methods per operation. Currently 1 validator is used for everything.
