# OrangeHRM Automation Project

## Project Overview

This project is an automation testing framework developed for the OrangeHRM web application.  
It automates important HRM functionalities such as admin login, employee creation, leave entitlement, leave approval or rejection, profile picture upload, and employee search.

The project is developed using Java, Selenium WebDriver, Cucumber, TestNG/JUnit, and follows the Page Object Model design pattern. Jenkins is used for continuous integration, and Git/GitHub is used for version control.

---

Tools and Technologies Used

- Java
- Selenium WebDriver
- Cucumber
- TestNG / JUnit
- Page Object Model
- Log4j
- Jenkins
- Git and GitHub
- WebDriverManager
- Maven

---

## Project Structure

```text
OrangeHRM_Automation_Project
│
├── src/test/java
│   │
│   ├── com.pages
│   │   └── Page classes
│   │
│   ├── com.stepdefinitions
│   │   └── Step definition classes
│   │
│   ├── com.actions
│   │   └── Action classes
│   │
│   ├── com.hooks
│   │   └── Hook class
│   │
│   ├── com.utilities
│   │   ├── Helper classes
│   │   ├── Config reader
│   │   └── Screenshot utility
│   │
│   └── com.runner
│       └── Test runner class
│
├── src/test/resources
│   │
│   ├── features
│   │   └── Feature files
│   │
│   ├── config.properties
│   └── log4j2.xml
│
├── test-output
│
├── screenshots
│
└── pom.xml
```
---
Features Automated

The following functionalities are automated in this project:

Admin Login
Add Leave Entitlement
Approve / Reject Leave
Profile Picture Upload
Employee Entitlement Search
Create Employee
Search Employee
Framework Design

---

This automation framework follows the Page Object Model design pattern.

In this framework:

Page classes contain web elements and locators.
Action classes contain reusable methods to perform actions on web pages.
Step definition classes connect feature file steps with automation code.
Feature files contain test scenarios written in Gherkin language.
Utility classes contain common reusable methods such as browser setup, waits, screenshots, and config reading.
Hooks are used for setup and teardown activities.
Runner class is used to execute the Cucumber test scenarios.
Maven Dependencies

The project uses Maven for dependency management.
All required dependencies are maintained in the pom.xml file.

--- 

Common dependencies used in this project include:

Selenium Java
Cucumber Java
Cucumber TestNG / JUnit
WebDriverManager
Log4j
Apache Commons IO
Prerequisites

---

Before running this project, make sure the following software is installed:

Java JDK
Eclipse IDE / IntelliJ IDEA
Maven
Google Chrome Browser
Git
Jenkins
How to Run the Project
Run Using Eclipse / IntelliJ
Open the project in Eclipse or IntelliJ IDEA.
Make sure all Maven dependencies are downloaded.
Open the runner class from the com.runner package.
Right-click the runner class.
Select Run As TestNG Test or Run As JUnit Test.
Run Using Maven Command

Open the terminal inside the project folder and run:

mvn clean test
Run Using Jenkins
Open Jenkins.
Create a new Jenkins job.
Select Pipeline project.
Connect the GitHub repository.
Add the Jenkins pipeline script.
Save the job.
Click Build Now.
Check the console output for execution results.
Reports and Screenshots

---

After test execution:

Test reports are generated inside the test-output folder.
Screenshots are stored inside the screenshots folder.
Screenshots are captured for failed test scenarios or required validation points.
