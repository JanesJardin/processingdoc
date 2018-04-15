# processingdoc
springboot project:
1. pull off project execute command in the same dir with pom.xml:mvn clean package -Dmaven.test.skip=true
2. import sql file into database and change the connection url of datasource in resources file "application.yml"
3. after build success execute: java -jar target/processingdoc-0.0.1-SNAPSHOT.jar /path/to/cvs/files/folder
/path/to/cvs/files/folder: is the path to the folder of cvs files 
