## EJDB Java Spark

Crear proyecto con Maven

    $ mvn archetype:generate -DgroupId=pe.softweb -DartifactId=JsonDB -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false

Crear war usando Maven:

    $ mvn package

Ejecutar Main Class usando Maven:

    $ mvn clean && mvn package && mvn exec:java -Dexec.mainClass="config.App"


```
<build>
    <finalName>JsonDB</finalName>
    <plugins>
        <plugin>
            <groupId>org.codehaus.mojo</groupId>
            <artifactId>exec-maven-plugin</artifactId>
            <version>1.2.1</version>
            <executions>
            <execution>
                <goals>
                <goal>java</goal>
                </goals>
            </execution>
            </executions>
            <configuration>
            <mainClass>config.App</mainClass>
            <arguments>
                <argument>foo</argument>
                <argument>bar</argument>
            </arguments>
            </configuration>
        </plugin>
    </plugins>
</build>
```

### Instalar EJDB:

Instalar ejdb:

    $ git clone https://github.com/Softmotions/ejdb-java.git
    $ cd ./ejdb-java
    $ ./configure --with-jdk=/usr/lib/jvm/java-8-oracle --prefix=
    $ make
    $ sudo make install

Agregar manualmente el jar a repositorio local de maven:

    mvn install:install-file -Dfile='src/main/resources/jars/jejdb-1.0.4.jar' -DgroupId=org.ejdb -DartifactId=ejdb -Dversion=1.0.4 -Dpackaging=jar

```
<dependency>
  <groupId>org.ejdb</groupId>
  <artifactId>ejdb</artifactId>
  <version>1.0.4</version>
</dependency>
```

--- 

Fuentes

+ https://www.mkyong.com/maven/how-to-create-a-web-application-project-with-maven/
+ https://stackoverflow.com/questions/9846046/run-main-class-of-maven-project
+ https://stackoverflow.com/questions/32923586/maven-lambda-expressions-are-not-supported-in-source-1-5
+ https://www.mkyong.com/maven/how-to-include-library-manully-into-maven-local-repository/