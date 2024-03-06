# COMP3005-Assignment3
Console Java application that manipulates student PostgreSQL server

This application was developed and tested using Windows Command Prompt. Compiling and running the application should be done in the directory containing Interact.java and the postgresql-42.7.2.jar files. The following commands must be run in order using Windows Command Prompt in order for the application to run.

1. Unzip Jar File: tar xf postgresql-42.7.2.jar<br />
2. Compilation Command: javac Interact.java <br />
3. Run Command: java Interact.java <br />

The application offers 5 different options as to what operations it can perform on the database, four to utilize the functions specified by the assignment, and one to exit the application. Details as to the options and their corresponding numbers are as follows:<br />
(1) - This option is an implementation of the required getAllStudents function. It retrieves all student records from the database and lists them to the user.<br />
(2) - This option is an implementation of the required addStudent function. It allows the user to enter a first name, last name, email, and date, all of which are inserted in the database as a new student entry.<br />
(3) - This option is an implementation of the required updateStudentEmail function. It allows the user to enter a student id and an email. If an entry that corresponds with the student id exists, its email will be updated to the email the user entered.<br />
(4) - This option is an implementation of the required deleteStudent function. It allows the user to enter a student id. If an entry that corresponds to the student id exists, it will be deleted from the database.<br />
(0) - This option exits the application.<br />

In addition to the files provided for the application, an additional file called setup.sql has been provided. It contains the two queries used to initialize the students table and populate it with the initial data set.
