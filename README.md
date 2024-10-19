# Nexus: Connecting Ideas, Inspiring Conversations

## Overview

This project is a full-stack blogging website developed using Java and modern web technologies, designed to provide both administrators and users with an intuitive platform to manage and create content. Built with a robust backend using Spring Boot, Spring Security, and MySQL, and a sleek, responsive frontend using Tailwind CSS and Thymeleaf, this website offers secure user authentication, CRUD operations, and a visually appealing interface with dark mode support.

### Key Features:

- **Admin Panel**:
  - View a complete list of registered users.
  - Remove or block users from the platform.
  - Manage posts: Add, delete, or edit blog posts, including title, content, and images.
  - Oversee user activities and posts, ensuring smooth content management.

- **User Panel**:
  - **Secure Registration & Login**: Users can register and login with email verification, ensuring account security using JWT tokens.
  - **Email Verification**: Upon registration, users receive an email to verify their account, powered by Spring Mail.
  - **Create and Manage Blogs**: Users can create blog posts with a title, content, and an image. They also have full control over their posts, allowing updates or deletion as needed.
  - **Comment and Like Feature**: Users can engage with posts by commenting and liking, fostering interaction between users.
  - **Password Management**: Users can reset or change their password securely through email verification.
  - **User Dashboard**: A personalized dashboard for users to manage their posts and account settings.
  - **Secure Logout**: Integrated with Spring Security for secure session management and logout functionality.
  - **Dark Mode & Light Mode**: The platform offers a visually pleasing and fully functional dark mode for an enhanced user experience.
  
### Technologies Used:
- **Backend**: Java, Spring Boot, Spring Data JPA, Spring Security, Spring Mail
- **Frontend**: Thymeleaf, Tailwind CSS, AJAX , JavaScript
- **Database**: MySQL
- **Authentication**: JWT Tokens
- **Build Tool**: Maven

This platform prioritizes user security and experience, making it easy for admins to manage content and users, while providing a seamless and intuitive interface for users to engage with the content.


## Prerequisites

Before getting started, ensure you have the following installed on your machine:

- **Java 17**: Required to run the application.
- **Maven 3.8.1 or higher**: Used for building and managing dependencies.
- **MySQL 8.0 or higher**: The database for storing and querying supplier data.
- **IntelliJ IDEA or VS Code**: Recommended IDEs for running and debugging the project.

## Getting Started

### 1. Clone or Download the Repository

Start by cloning the project repository to your local machine:

```bash
git clone https://github.com/Vin-it-9/BookMyShow.git
```
To download the repository, visit the GitHub Repository, click on the Code button, and select Download ZIP.



### 2. Open the Project in IntelliJ IDEA EPA

To work with the Spring Boot project in IntelliJ IDEA EPA, follow these steps:

1. **Download and Install IntelliJ IDEA EPA**:
   - Download and install [IntelliJ IDEA EPA](https://www.jetbrains.com/idea/).

2. **Clone the Project Repository**:
   - Clone the project repository by running the following command in your terminal:
     ```bash
     git clone https://github.com/Vin-it-9/Blog.git
     ```

3. **Install JDK**:
   - Ensure you have [JDK 17 or later](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html) installed on your machine.

4. **Install the Spring Boot Runner Plugin**:
   - Open IntelliJ IDEA and navigate to **File > Settings** (or **IntelliJ IDEA > Preferences** on macOS).
   - Go to **Plugins** and search for **Spring Boot**.
   - Install the **Spring Boot** plugin and restart IntelliJ IDEA if prompted.

5. **Open the Project**:
   - Select **File > Open...** and navigate to the cloned project directory. Choose the `pom.xml` file to import the Maven project.

6. **Edit the Run Configuration**:
   - Navigate to **Run > Edit Configurations...**.
   - Click on the **+** icon to add a new configuration and select **Spring Boot**.
   - In the **Main class** field, specify the main application class (usually it will be the class annotated with `@SpringBootApplication`).
   - Ensure that the **Working directory** is set to the project root directory.
   - Click **OK** to save the configuration.

7. **Configure Application Properties** (Optional):
   - Open `src/main/resources/application.properties` or `application.yml` to configure your database connection and other application settings, if necessary.

8. **Run the Spring Boot Application**:
   - Now, you can run the application by clicking the green **Run** button or by selecting **Run > Run 'YourConfigurationName'** from the menu. This will start the Spring Boot application and automatically create the required database tables if configured correctly.

Once the application starts, you should see log messages in the console indicating that the Spring Boot application has been successfully launched.




### 3. Open the Project in Visual Studio Code

To work with the Spring Boot project in Visual Studio Code, follow these steps:

1. **Download and Install Visual Studio Code**:
   - Download and install [Visual Studio Code](https://code.visualstudio.com/).

2. **Clone the Project Repository**:
   - Clone the project repository by running the following command in your terminal:
     ```bash
     git clone https://github.com/Vin-it-9/Blog.git
     ```

3. **Install JDK**:
   - Ensure you have [JDK 17 or later](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html) installed on your machine.

4. **Install Required Extensions**:
   - Open Visual Studio Code and go to the Extensions view by clicking on the Extensions icon in the Activity Bar on the side of the window or by pressing `Ctrl+Shift+X`.
   - Search for and install the following extensions:
     - **Spring Boot Tools**
     - **Spring Initializr**
     - **Spring Boot Dashboard**
     - **Java Development Kit (JDK)**
     - **Extension Pack for Java**
     - **Spring Boot Extension Pack**
   - After installation, reload the window if prompted.

5. **Open the Project**:
   - Open the cloned project in VS Code by selecting **File > Open Folder...** and navigating to the cloned project directory.

---

6. **Configure the Application**:
   - Open the `src/main/resources/application.properties` or `application.yml` file to configure your database connection and other application settings.
   - **Database Configuration**:
     - Update the **username** and **password** for your database connection as needed.
     - Ensure that your database URL points to the correct database instance.
   - **Email Configuration**:
     - Modify the email settings to include your SMTP server information.
     - Update the **email** and **key** used for sending verification and notification emails.

   Spring Boot will automatically create the required database tables based on your entity classes when the application starts, so there’s no need for manual table creation.
---
7. **Run the Spring Boot Application**:
   - Open the Command Palette by pressing `Ctrl+Shift+P` and type **Spring Boot Dashboard**. Select **Spring Boot Dashboard** from the dropdown.
   - In the Spring Boot Dashboard, you should see your project listed. Click on the play icon ▶️ next to your application to run it.
   - This will start the Spring Boot application and automatically create the required database tables if configured correctly.

8. **Monitor Application Logs**:
   - You can view the application logs in the integrated terminal or the output window in VS Code to confirm that the application has started successfully.

9. **Access the Application**:
   - After the application is running, you can access it in your web browser at the default URL `http://localhost:8080` , or as specified in your application configuration.



