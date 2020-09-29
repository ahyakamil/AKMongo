# AKMongo
Spring Boot helper for upsert and upsert all.
Sometimes you don't want to remove existing object, ex:

in service 1 you have:

    {
    id: "a001",
    accountId: "s001",
    lastCheckActivityLogin: "29 Jan 2020, 18:00"    
    }
    
in service 2 you have:

    {
    id: "a001",
    accountId: "s001",
    lastCheckActivityShop: "31 Jan 2020, 17:00"    
    }
    
    
you want have result in mongo database:

    {
    id: "a001",
    accountId: "s001",
    lastCheckActivityLogin: "29 Jan 2020, 18:00"    
    lastCheckActivityShop: "31 Jan 2020, 17:00"    
    }
    

## How To Use
Simple, 

### 1. just add the dependecy:

    ```
    <dependency>
        <groupId>com.ahyakamil</groupId>
        <artifactId>akmongo</artifactId>
        <version>1.0.1</version>
    </dependency>
    ```

### 2. create the bean:

#### Example
    @SpringBootApplication
    public class NiceApplication {
      public static void main(String[] args) {
          SpringApplication.run(NiceApplication.class, args);
      }

      @Bean
      public AKMongo akMongo(MongoOperations mongoOperations) {
          return new AKMongoImpl(mongoOperations);
      }
    }


### 3. use it in service:

#### Example

    @Service
    public class NiceServiceImpl implements NiceService {
      ......

      @Autowired
      AKMongo akMongo;

      @Override
      public void niceThingTodo() {
        ....
        akMongo.upsertAll(datasToUpdate);
      }
    }
