# Java_LP_CodeChallenge

Project Author: Nishu Nagaraj

## Description

* This repository contains Maven Java project, The project aims to caluclate the amount for the trip made by passengers travelling through 3 different stops in a bus by recording the TAP ON and TAP OFF activity from their card card. .
* The project accepts the tapping history of a passenger in a CSV format.
* The project outputs below fields in output CSV file:
    * The amount charged for **Complete trip**, **Incomplete trip** and **Cancelled trip**.
    * Start Date and Time, End Date and Time
    * Duration of travel
    * FromStop, ToStop
    * CompanyID, BusID
    * Status
    * PAN
    
## Project Dependencies

* Project Management tool : Maven 3.0.0
* Language : Java 8
* Framework/Libraries : Junit 5.6.0, opencsv 5.3

## Project Structure

* As mentioned earlier the input in taken from a csv file **Input.csv** and output is written to a different csv file **Output.csv**. 
* Both input and output files are placed in src/main/java/com/code folder.
* The project includes: 
    * main class - App.java, 
    * Business Logic classes  -  CompletedTrip.java, IncompleteTrip.java
    * Input class - Input.java
    * Output class - Output.java
* To validate the solution of this project, test harness has been provided. Three positive test cases are included in AppTest.java file under folder **src/test/java/com/code**.
* Three Test output files are included in the folder **src/test/java/com/code**.

## Assumptions considered

* Column **ID** is considered as unique field in Input file.
* An exception will be thrown if there is a Duplicate TAP ONs due to Card reader error.
* An exception will be thrown if there is a stale TAP OFF again due to system or card reader error.
* Time will be displayed in 12 Hour format in output file.

## Project execution Instruction

* Three positive tests are included in the AppTest.java.
* To make it easier to test, three different sample test output files are included in folder **src/test/java/com/code**.
    * CancelledTripTestOutput
    * CompleteTripTestOutput
    * IncompleteTripTestOutput



 
    




