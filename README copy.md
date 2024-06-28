# camunda-spring-demo-boilerplate



## How to start your own application

1. Use https://start.spring.io/ to create your basic Springboot application. *I personally use **Maven**, **Java 21**, **Springboot 3.2.4** and **Jar** packaging.*
2. Add that following code to your **pom.xml** file.  Retrieves latest version on https://mvnrepository.com/artifact/io.camunda.connector/spring-boot-starter-camunda-connectors

```xml
<dependency>
    <groupId>io.camunda.connector</groupId>
    <artifactId>spring-boot-starter-camunda-connectors</artifactId>
    <version>8.4.6</version>
</dependency>
```

3. On your **Camunda Console**, create your API client credentials:  Console > Clusters (top menu) > Select your cluster > API > Create new client

   Give a name to your client and select the scope (Personally, I tick all the boxes).

4. Copy to clipboard the Spring Boot  content.

5. Add it to your ***src/main/resources/application.properties***

6. Open the terminal, go to your root application directory and try the following command

   ```bash
   mvn spring-boot:run
   ```

If your terminal is on hold (cursor blinks) that very good: your Camunda application is up and running :facepunch: