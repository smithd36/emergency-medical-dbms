# JavaFX GUI Database Management System using MySQL with Azure & SQLite (file based)

This program is a JavaFX-based GUI database management system (DBMS) developed using Maven as the dependency management system. It incorporates BootstrapFX and CSS styling in JavaFX to enhance the visual appearance of the application. Originally designed for managing licensure in an institution, the program has been modified for GitHub display and distribution, with sensitive information such as database login credentials excluded. To get a glimpse of the program in action, please refer to the following screenshots:

![Screenshot 2023-05-31 133628](https://github.com/smithd36/mysql-javafx-medical-license-system/assets/90289165/512a8035-ea82-431c-86e9-41b3b16bed3e)
![Screenshot 2023-05-31 133603](https://github.com/smithd36/mysql-javafx-medical-license-system/assets/90289165/d7cba88c-abb9-4311-8af9-538d573ff3e0)
![Screenshot 2023-05-31 133647](https://github.com/smithd36/mysql-javafx-medical-license-system/assets/90289165/4d168074-c3aa-44a5-83a5-6e7df6e2cb3f)
![Screenshot 2023-05-31 133713](https://github.com/smithd36/mysql-javafx-medical-license-system/assets/90289165/7ad64402-6176-499d-ab4d-b48a8e5680b0)
![Screenshot 2023-05-31 133730](https://github.com/smithd36/mysql-javafx-medical-license-system/assets/90289165/19515ee5-e1ca-4827-94e9-e32eaf3539b0)


# Database
The program utilizes Azure as the database hosting service, ensuring efficient user interaction with quick response times. The database incorporates a login management system integrated into the code, allowing only one user to log in at a time. For security reasons, the "Lockbox" SQLite database, which contains critical login information, is not included in the repository. However, a user can easily reproduce the database with non-critical login credentials to test the program on a different database.

# Usage
This program can be executed on any device by following the JLaunch deployment process or running the provided .jar file, which includes all dependencies and required runtime environments and drivers. The development of this program took approximately two weeks of dedicated effort. Additionally, during the development process, an Email Agent (located in a separate repository) was created to monitor the same database. The Email Agent notifies a predefined group of individuals when an employee in the database is nearing the expiration of a certain license.

# License
Copyright © 2023 Smith, Drey - The distribution rights of this program solely belong to the owner. Users are permitted to use the code on their cloned databases to fully utilize the program's functionality.
