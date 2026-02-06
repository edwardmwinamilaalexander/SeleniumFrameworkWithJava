# SeleniumFrameworkWithJava
Test automation framework using Selenium WebDriver and Java
Selenium Automation with Java
📌 Project Description

SeleniumAutomationWithJava is a test automation framework built using Selenium WebDriver and Java, designed to create scalable, maintainable, and reusable automated tests for web applications.
The framework follows best practices such as Page Object Model (POM), external test data management, and centralized configuration.

🛠 Tech Stack

Language: Java

Automation Tool: Selenium WebDriver

Test Framework: TestNG

Build Tool: Maven

Logging: Log4j2

Reporting: TestNG / Extent Reports

Design Pattern: Page Object Model (POM)

📂 Project Structure
SeleniumAutomationWithJava
│
├── .idea/                 # IntelliJ IDEA project files
├── .mvn/                  # Maven wrapper files
│
├── logs/                  # Log files (Log4j2)
├── reports/               # Test execution reports
├── screenshots/           # Screenshots for failed tests
│
├── src/
│   ├── main/java/
│   │   ├── base/           #  BasePage
│   │   ├── config/         # Configuration classes
│   │   ├── pages/          # Page Object classes
│   │   └── utils/          # Utility/helper classes
│   │
│   └── test/java/
│       └── tests/          # TestNG test classes including BaseTest
│
├── target/                # Maven build output
│
├── testdata/
│   └── TestData.xlsx      # External test data (Data-Driven Testing)
│
├── .gitignore
├── pom.xml                # Maven dependencies & build config
└── testng.xml             # TestNG suite configuration

⚙️ Prerequisites

Java (JDK 8 or above)

Maven

Chrome / Firefox browser

IntelliJ IDEA / Eclipse

Internet connection (for Maven dependencies)

🚀 How to Run Tests
Run using Maven
mvn test

Run using TestNG

Open testng.xml

Right-click → Run

🧪 Framework Features

Page Object Model (POM) implementation

Centralized WebDriver management

Data-driven testing using Excel

Screenshot capture on test failure

Detailed test reports

Logging using Log4j2

Easy maintenance and scalability

📊 Reports & Logs

Reports: /reports

Logs: /logs

Screenshots: /screenshots

Reports are generated automatically after test execution.

🔮 Future Enhancements

Parallel test execution

Docker support
