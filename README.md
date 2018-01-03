# Spring and MongoDB
    Write restful api and connect MongoDb
    Pagination

# Requirements
    Install mongodb on local or using docker
    Java: JDK 1.8
    Maven Build


# MongoDB

### Install mongodb on local

Install on Mac OS X machine with homebrew
```
brew install mongodb
```

Start MongoDb server
```
$ mongod
```

### Install mongodb on docker

```
docker-compose up
```

Check mongodb status
```
docker ps
```

Connect to mongodb
```
mongo 127.0.0.1:27017
```

Check config mongodb
src/main/resource/application.properties

# Integration Tests
An integration test based on Spring's RestTemplate
