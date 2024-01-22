## who to run 

``` bash 
    ./mvnw clean install 
    java -jar target/players-0.0.1-SNAPSHOT.jar
```

## open issues 
1. Error and Exception handling -
   * I configured in one place all error handling (ExceptionControllerAdvice) the By default, the methods in an @ControllerAdvice apply globally to all controllers
   * service layer throw exception which is being catched by the thread initator (in our case it was http, but can lso be for example queue) 
   * can also cover cases such where the input (in our case the path param is invalid ) e.g, made from chinse charcters 
2. Testing
   * should have done unit tests for each layer service, (other than controller which will be tested in int test, *since it should be lean* and communicate with the framework bindings which will be tested in prod )
   * Should have done int test with
     * application properties-test file 
     * test class to spin it up PlayControllerIntTest
3. Edge cases -
   * test incompitable value in CSV lines 
     * what happen if there are not enpogth columns in one line 
     * what happen if int value parsed is greater than max size (is it a valid scenario, we can use hibrnate validators to do validate here ...  )
4. Optimizations
   * cache - if the same call is comming over and over again ... 
   * put everything in database, in memeory wont scale and DB knows how to index by primary key ... 
5. Deployment  
   * could have wrap it in docker file and create helm chart for it which will run the DB upload using pre-install hook
   * github workflow as ci cd which per env would have put another file in the code 


More stuff 
1. configuration per env 
2. db 
3. logging 
4. extract loading the data into another file  
5. create a DTO and mapper (mapstruct for example) since the persistant data and the DTO one should be different (consil private data ) 
