all: compile 

# compile the application
compile:  
	mvn -q clean compile

# package the application into an uber-jar with tests
package:  
	mvn -q clean package

# package the application into an uber-jar without tests
fastPackage:  
	mvn -q clean package -DskipTests=true

# run
run: fastPackage
	java -jar target/*.jar

# clean
clean:
	mvn -q clean

# test
test: clean
	mvn test