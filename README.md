# VodafoneAssignment

This assignmnet is a java API in which we are loading the location of IoT tracking devices details from a csv file to in memory database.
After stroring the data we are fetching device details based on the product id and timestamp where product id is manadatory and timestamp is optional.

## Installation

Download the zip file from git.
Open Eclipse and select File > Import.
In the import wizard, choose Maven > Existing Maven Projects, then click Next.
Select the java-maven-starter-project as the project root directory.
Click Finish to complete the import.
Select Project > Properties . In Java Build Path, ensure that under the Libraries tab, Modulepath is set to JRE System Library (JavaSE-8). In Java Compiler, ensure that the Use compliance from execution environment 'JavaSE-8' on the 'Java Build Path' checkbox is selected.
Right-click the project in the Project Explorer or Package Explorer and choose Run As > Maven Build.... In the Edit Configuration dialog, create a new configuration with name projectbuild. In the Goals field, enter dependency:clean install. Click Run to run the goal. This will download on the dependency.
Select the project, opne the bootstrap class i.e VodafoneAssignmentApplication.java and run as java application.
Startup logs will be display on the console.
Open the swagger to know about the API specification using URL: http://localhost:8080/swagger-ui.html#!/device-resource and can try to test API directly from there or can access via SOAP-UI/Postman
We are using in memory DB, DB can be access by URL: http://localhost:8080/h2-console and JDBC URL will be jdbc:h2:mem:testdb and username/password will be sa/sa

## Usage
We can upload the batch file from CSv file to DB and also can fetch the data from DB.
