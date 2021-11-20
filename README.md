# AKMongo
Spring Boot helper for upsert and upsert all.
Sometimes you don't want to remove another existing object.

for example, in mongodb you have data:

    {
    _id: "a001",
    lastCheckActivityLogin: "29 Jan 2020, 18:00"    
    }
    
and on your spring boot entity you have:

    {
    _id: "a001",
    lastCheckActivityShop: "31 Jan 2020, 17:00"    
    }
    
    
you want have result in mongo database:

    {
    _id: "a001",
    lastCheckActivityLogin: "29 Jan 2020, 18:00"    
    lastCheckActivityShop: "31 Jan 2020, 17:00"    
    }
    

## How To Use
### 1. just add the dependecy:
    <distributionManagement>
        .....
        <snapshotRepository>
            <id>ossrh</id>
            <url>https://oss.sonatype.org/content/repositories/snapshots</url>
        </snapshotRepository>
        <repository>
            <id>ossrh</id>
            <url>https://oss.sonatype.org/service/local/staging/deploy/maven2/</url>
        </repository>
    </distributionManagement>

    <dependecies>
        .....    
        <dependency>
            <groupId>com.ahyakamil</groupId>
            <artifactId>akmongo</artifactId>
            <version>1.0.2.rc0-SNAPSHOT</version>
        </dependency>
    </dependencies>

### 2. add this to main:
    @SpringBootApplication
    @EnableMongoRepositories(
            repositoryBaseClass = AKMongoImpl.class
    )    
    public class NiceApplication {
        ....
    }


### 3. use it in a repository:
You need to extend AKMongo on your repository

    @Repository
    public interface NiceThingRepository extends AKMongo<NiceThing, String> {
    }
    
    public interface NiceThingService {
        void upsert(NiceThing niceThing);
        void usertAll(List<NiceThing> niceThings);
    }
    
    @Service
    public interface NiceThingServiceImpl implement NiceThingService {
        @Autowired
        NiceThingRepository niceThingRepository;
        
        @Override
        public void upsert(NiceThingDto niceThingDto) {
            //map dto to entity, and do other nice thing
            ....
            niceThingRepository.akUpsert(niceThing);
        }
        
        @Override
        public void upsertAll(List<NiceThingDto> niceThingDtos) {
            //map list dto to list entity, and do other nice thing
            ....
            niceThingRepository.akUpsertAll(niceThings);
        }
    }
    
You still can use another spring boot method like save, delete, etc as usual.

## License
Licensed under [Apache 2.0 license](https://www.apache.org/licenses/LICENSE-2.0.html)

## Buy Me A Chocolate
[Buy Me A Chocolate](https://www.paypal.com/paypalme/ahyaalkamil1)


