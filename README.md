## Test task for Zipy

### Table of contents
- [About](#about)
- [Libraries](#project-libraries)
- [Build and execution](#build-and-execution)

### About

This application provides data from [Aliexpress flashdeals](https://flashdeals.aliexpress.com/en.htm?)


### Project libraries 

To solve the task i've used those libraries:

1. [Json](https://mvnrepository.com/artifact/org.json/json)
2. [Lombok](https://mvnrepository.com/artifact/org.projectlombok/lombok)
3. [Jackson databind](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind)
4. [Apache POI](https://mvnrepository.com/artifact/org.apache.poi/poi)
5. [Aspose](https://ru.products.aspose.com/cells/net)


### Build and execution

#### 1'st step
```

mvn clean package

```

#### 2'nd step

```
java -jar zipy-1.0-SNAPSHOT.jar
```

#### Result (CSV and XLSX) files will be generated at /resources


## NOTE! 
### No tests provided for this project.
