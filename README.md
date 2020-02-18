# CRUD Application
Simple CRUD-service implementation using Java Servlet API

### Prerequisites

Before to start you should create tables in database using init.sql in SQLScripts directory 

```
mysql> source .../SQLScripts/init.sql
```

then configure DataSourceFactory

```
    mysqlDataSource.setDatabaseName("yourDatabaseName");
    mysqlDataSource.setServerName("servername");
    mysqlDataSource.setPort(port);
    mysqlDataSource.setUser("user");
    mysqlDataSource.setPassword("password");
    mysqlDataSource.setServerTimezone("UTC");
```
In order to access the site you need to register an account. 
You can do it at `/register` page

## Built With
* [Maven](https://maven.apache.org/) - Dependency Management


