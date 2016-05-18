# Bi3_CI_Work

Sample application to demonstrate continuous integration. 
A Jenkins CI server in a private cloud is already configured.

To build the project: mvn clean install
To run integration tests: mvn clean veryfy -P integration-tests
To generate site and reports: mvn site

