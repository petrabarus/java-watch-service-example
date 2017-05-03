# java-watch-service-example
Example for Java WatchService

# Compiling

To compile, execute

```
mvn clean install
```

# Running

To run and watch directory, e.g. `/tmp/`, execute

```
java -jar target/java-watch-service-example-1.0-SNAPSHOT.jar /tmp/
```

Try creating a file in the directory `/tmp`

```
echo "TEST" > /tmp/test.txt
```

The app will output

```
ENTRY_CREATE: test.txt
ENTRY_MODIFY: test.txt
```