# JavaFX GUI Database Management System using MySQL with Azure & SQLite (file based)

This comprehensive JavaFX-based GUI database management system (DBMS) is built using Maven as the dependency management system. It leverages the power of JavaFX to create a visually appealing user interface, enhanced by the integration of BootstrapFX and custom CSS styling. Originally developed to streamline licensure management in an institutional setting, the program has been tailored for GitHub display and distribution while prioritizing the security of sensitive information such as database login credentials.

![Screenshot 2023-06-17 212441](https://github.com/smithd36/medical-license-tracking-gui-system/assets/90289165/8c6eae98-bc1f-43f0-9256-54b7131a7fc4)
![Screenshot 2023-05-31 133713](https://github.com/smithd36/mysql-javafx-medical-license-system/assets/90289165/7ad64402-6176-499d-ab4d-b48a8e5680b0)
![Screenshot 2023-05-31 133730](https://github.com/smithd36/mysql-javafx-medical-license-system/assets/90289165/19515ee5-e1ca-4827-94e9-e32eaf3539b0)


# Database
The program employs Azure, a highly efficient cloud-based database hosting service, ensuring optimal performance and seamless user experience. The database incorporates a robust login management system, seamlessly integrated into the codebase, allowing only a single user to log in at any given time. For security reasons, the "Lockbox" SQLite database, which contains critical login information, has been omitted from the repository. However, users can effortlessly recreate the database by utilizing non-critical login credentials, enabling hassle-free testing of the program on alternative databases.

# Usage
This program can be effortlessly executed on any device by following the JLaunch deployment process or running the provided executable .jar file. The bundled .jar file includes all necessary dependencies, runtime environments, and drivers, guaranteeing a smooth and hassle-free user experience. The development of this sophisticated DBMS took approximately two weeks of relentless effort, resulting in a powerful and user-friendly solution. Furthermore, during the development phase, an Email Agent (found in a separate repository) was concurrently developed. The Email Agent monitors the database and promptly notifies a pre-defined group of individuals when an employee's license is nearing expiration.

# License
Ⓒ 2023 Smith, Drey. All rights reserved. The distribution rights of this program solely belong to the owner, ensuring exclusive control over its dissemination. Users are granted permission to clone the repository and utilize the code in their own database environments to harness the full power and potential of this exceptional program.
