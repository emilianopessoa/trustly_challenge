# Trustly challenge

This project is related to a challenge made by Trustly, which aims to create an API to consult information from github repositories.

The project is a standard Maven project, so you can import it to your IDE of choice. It is also configured with Spring Boot.

## You can test the App through the links:

Returns the total number of lines and bytes for a given github repository. Grouped by extension.
https://emilianopessoa.herokuapp.com/api/github?url=https://github.com/scrapinghub/sample-projects

Returns the number of lines and bytes of each file from a given github repository.
https://emilianopessoa.herokuapp.com/api/github?url=https://github.com/scrapinghub/sample-projects&grouped=false

Note that the root file will always bring the total number of lines and bytes of the children nodes.

## Running and debugging the applcation

Note: After any 'Running' procedure below open this example link http://localhost:8080/api/github?url=https://github.com/scrapinghub/sample-projects in your browser. 
Note that you can change the url parameter for any public repository. 

### Running the application from the command line.
To run from the command line, go to project folder, execute `./mvnw spring-boot:run` and open the example link above.

### Running and debugging the application in Intellij IDEA
- Locate the Application.java class in the Project view. It is in `src/main/java/com/trustly/emiliano`, under the main package.
- Right click on the Application class
- Select "Debug 'Application.main()'" from the list

After the application has started, you can view the example link above in your browser. 
You can now also attach break points in code for debugging purposes, by clicking next to a line number in any source file.

### Running and debugging the application in Eclipse
- Locate the Application.java class in the Package explorer. It is in `src/main/java/com/trustly/emiliano`, under the main package.
- Right click on the file and select `Debug As` --> `Java Application`.

After the application has started, you can view the example link above in your browser. 
You can now also attach break points in code for debugging purposes, by clicking next to a line number in any source file.

## Project structure

- Main Class: `Application.java` in `src/main/java/com/trustly/emiliano`.
- Controller package: `src/main/java/com/trustly/emiliano/controller`.
- Data Access objects package: `src/main/java/com/trustly/emiliano/dao`.
- Model package: `src/main/java/com/trustly/emiliano/model`.
- Service package: `src/main/java/com/trustly/emiliano/service`.
- Commons package: `src/main/java/com/trustly/emiliano/commons`.
