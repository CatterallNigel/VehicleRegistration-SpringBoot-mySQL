# VehicleRegistration-SpringBoot-mySQL
Demonstration for Spring-mySQL

This has a 'In Memory' h2, as default, however this can be swapped for mySQL by commenting out the hs dependency in the pom.xml and by 
uncommenting the mySQL connection details (and replacing the default values) in the application.properties file found in the 'resources' folder.

On start-up, there are three Vehicle Registrations, inserted into the DB as 'seed' data. (ca.catterall.vehicle.registration.data.DummyData.java)

All responses are in JSON format, with a corresponding HTTP Status Code, for both success and error.

In the 'Postman' directory, is a file that can be imported into Postman, that has all the REST calls, GET,POST and DELETE for: 
  Getting the Registration Listings (ALL)
  Adding a New Registration (success & failure)
  Deleting a Registration (success & failure)
  
There is a set of Mockito/JUnit test cases in ca.catterall.vehicle.registration.controller.VehicleRegistrationControllerTests.java for the application.
