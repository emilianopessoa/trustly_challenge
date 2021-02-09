# Trustly challenge

This project is related to a challenge made by Trustly, which aims to create an API to consult information from github repositories.

The project is a standard Maven project, so you can import it to your IDE of choice. It is also configured with Spring Boot.

## Running and debugging the applcation

Note: After any 'Running' procedure below open this example link http://localhost:8080/api/github?url=https://github.com/scrapinghub/sample-projects in your browser. 
Note that you can change "https://github.com/scrapinghub/sample-projects" for any public repository. 
This link will bring the total number of lines and the total number of bytes of all the files of the give repository, grouped by file extension. 
If you don't want to perform the extension grouping just add the parameter &grouped=false to the url.
The root file always will bring the total of the children files lines and bytes.

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

## Deploying using Docker

To build the Dockerized version of the project, run

```
docker build . -t challenge:latest
```

Once the Docker image is correctly built, you can test it locally using

```
docker run -p 8080:8080 challenge:latest
```
