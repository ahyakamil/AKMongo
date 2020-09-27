# AKMongo
Spring Boot helper for upsert and upsert all


## How To Use
Simple, 

1. just create the bean:

### Example
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


2. use it in service:

### Example

    @Service
    public class NiceServiceImpl implements NiceService {
      ......

      @Autowired
      AKMongo akMongo;

      @Override
      public void niceThingTodo() {
        ....
        akMongo.upsertAll(bankDataAccountsToUpdate);
      }
    }
