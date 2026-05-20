# 🧪 OrangeHRM Automation Testing Framework

Automation testing framework for the [OrangeHRM Demo Application](https://opensource-demo.orangehrmlive.com/?utm_source=chatgpt.com) using **Selenium WebDriver**, **TestNG**, **Cucumber BDD**, and **Maven**.

This framework automates major modules of OrangeHRM including:

* Login
* PIM (Employee Management)
* Admin Management
* Leave Management
* My Information

---

# 🚀 Tech Stack

| Technology         | Usage                       |
| ------------------ | --------------------------- |
| Java               | Programming Language        |
| Selenium WebDriver | UI Automation               |
| TestNG             | Test Execution              |
| Cucumber BDD       | Behavior Driven Development |
| Maven              | Build Management            |
| Extent Reports     | Reporting                   |
| Jenkins            | CI/CD Integration           |
| Git & GitHub       | Version Control             |

---

# 📂 Project Structure

```bash
ScriptX_OrangeHRM/
│
├── src/
│   ├── test/
│   │   ├── java/
│   │   │   ├── actions/
│   │   │   ├── pages/
│   │   │   ├── runners/
│   │   │   ├── stepdefinitions/
│   │   │   └── utilities/
│   │   │
│   │   └── resources/
│   │       ├── features/
│   │       └── config/
│
├── ExtentReports/
├── test-output/
├── pom.xml
└── README.md
```

---

# ✅ Framework Features

* Page Object Model (POM)
* Reusable Actions and Utilities
* Data-Driven Testing
* Cross Browser Support
* Screenshot Capture on Failure
* Extent Spark Reports
* Jenkins Pipeline Integration
* Cucumber HTML Reports
* Maven Build Execution

---

# 🧩 Modules Covered

## 🔐 Login Module

### Scenarios

* Login with valid default admin credentials
* Login with invalid username and password
* Login with empty username and password
* Login with valid username and invalid password
* Login with invalid username and valid password
* Verify logout functionality
* Login with created employee credentials

---

## 👨‍💼 PIM Module

### Employee Creation

* Create employee with valid first name and last name
* Create employee with custom employee ID
* Create employee without mandatory fields
* Create employee with only first name
* Create employee with special characters in name

### Employee Search

* Search employee by employee name
* Search employee by employee ID
* Search employee with invalid employee name
* Search employee with invalid employee ID

---

## 👨‍💻 Admin Management Module

### User Credential Creation

* Create ESS employee login credential
* Create Admin login credential
* Create user without mandatory fields
* Create user with duplicate username
* Create user with password mismatch

### Search User Credentials

* Search user by username
* Search user by user role
* Search user by employee name
* Search user by status
* Search user with invalid username

---

## 🏖️ Leave Management Module

### Add Leave Entitlement

* Add leave entitlement with valid details
* Add entitlement without mandatory fields
* Add entitlement with invalid employee name

### Employee Entitlements

* Search employee entitlement by employee name
* Search employee entitlement by leave type
* Search employee entitlement by leave period
* Search entitlement with invalid employee name

### Leave List

* View all employee leave requests
* Search leave request by employee name
* Filter leave request by leave status
* Filter leave request by date range

### Approve / Reject Leave

* Approve employee leave request
* Reject employee leave request
* Verify approved/rejected status

### Assign Leave

* Assign leave with valid details
* Assign leave more than available balance
* Verify assigned leave in leave list

### My Entitlements

* View entitlement by leave type and period
* Verify entitlement after admin addition

### Apply Leave

* Apply leave with valid leave type and dates
* Apply leave without mandatory fields
* Apply leave with partial days

### My Leave

* Filter leave by status
* Search leave after applying
* Verify no matching records

### Verify Leave Status

* Verify approved/rejected/pending status
* Verify status using filters

---

## 👤 My Information Module

### Personal Details

* Update personal details with valid data
* Edit personal details without saving

### Profile Picture

* Upload profile picture
* Change profile picture without saving

### Contact Details

* Update contact details
* Add attachment in contact details

---

# 🧪 Test Execution

## Run Using Maven

```bash
mvn clean test
```

---

## Run Specific Test Runner

```bash
mvn test -Dcucumber.filter.tags="@ValidLogin"
```

---

# 📊 Reports

## Extent Spark Report

Generated under:

```bash
/ExtentReports/SparkReport/
```

## TestNG Report

Generated under:

```bash
/test-output/
```

---

# ⚙️ Jenkins Integration

This project supports Jenkins CI/CD pipeline execution.

### Jenkins Stages

* Workspace Cleanup
* Checkout Source Code
* Maven Build
* Test Execution
* Report Publishing

---

# 👥 Team Members

| Module           | Assigned To         |
| ---------------- | ------------------- |
| Login            | Jagadeep            |
| PIM              | Jagadeep            |
| Admin Management | Rohini              |
| Leave Management | Rohini / Mylambigai |
| My Information   | Reshma              |

---

# 📌 Prerequisites

Make sure the following are installed:

* Java 17+
* Maven
* Chrome Browser
* ChromeDriver
* Git
* Jenkins

---

# 🔧 Setup Instructions

## Clone Repository

```bash
git clone <repository-url>
```

## Navigate to Project

```bash
cd ScriptX_OrangeHRM
```

## Install Dependencies

```bash
mvn clean install
```

---

# 📷 Reporting & Screenshots

* Failed test screenshots are captured automatically.
* Extent Spark Report provides detailed execution logs.
* Cucumber step results are integrated into reports.

---
