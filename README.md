# PwC test assignment

Sample spring boot application calculating optimal route between two counties

Link to the assignment: [assignment.pdf](assignment.pdf)

Note: *The code is not production ready*, it is just a sample implementation of the assignment, check TODOs in the code for improvements

## Development Prerequisites

* java21

## Building

  ```shell
  ./gradlew clean build
  ```   

## Running locally as standalone server fetching data from external API
`https://raw.githubusercontent.com/mledoze/countries/master/countries.json`

By default, server is running on port 8080, in case you want to change it, you can do it in `application.yaml` file
```shell
  ./gradlew bootRun
```   
## Calling routing endpoint
```shell
curl -X GET http://localhost:8080/routing/CZE/ITA
```
