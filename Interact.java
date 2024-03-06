import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Interact {

    /**
     * Returning all records from the students table.
     */
    static void getAllStudents(Connection conn) {

        try {
            // Create statement
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM students ORDER BY student_id ASC";

            // Getting query results
            System.out.println("\nStudents:");
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                int student_id = result.getInt("student_id");
                String first_name = result.getString("first_name");
                String last_name = result.getString("last_name");
                String email = result.getString("email");
                Date date = result.getDate("enrollment_date");
                System.out.println("ID: " + student_id + ", Name: " + first_name + " " +
                                    last_name + ", Email: " + email + ", Date: " + date);
            }

            // Close resources
            result.close();
            statement.close();
        } catch (SQLException e) {
             System.out.println("\nUnexpected error in query -> " + e + "\n");
        }

    }

    /**
     * Adding a student to the database.
     */
    static void addStudent(Connection conn, Scanner scanner) {

        // Important values for insert
        String fName;
        String lName;
        String email;
        Date date;
        DateFormat format = new SimpleDateFormat("yyyy-mm-dd");

        // Looping until correct input is collected
        while (true) {

            try {

                // Getting input
                System.out.println("\nInsert Student:");
                System.out.print("First Name: ");
                fName = scanner.nextLine();

                System.out.print("Last Name: ");
                lName = scanner.nextLine();

                System.out.print("Email: ");
                email = scanner.nextLine();

                System.out.print("Date (yyyy-mm-dd): ");
                String dateStr = scanner.nextLine();
                date = format.parse(dateStr);

                break;

            } catch (Exception e) {
                System.out.println("\nBad input provided for insert -> " + e);
            }
        }

        try {
            // Create statement
            Statement statement = conn.createStatement();

            // Creating insert query
            String insert = "INSERT INTO students (first_name, last_name, email," +
                            " enrollment_date) VALUES ('" + fName + "', '" + lName +
                            "', '" + email + "', '" + format.format(date) + "')";

            // Getting query results
            int result = statement.executeUpdate(insert);
            if (result > 0) {
                System.out.println("A new student was inserted successfully!");
            } else {
                System.out.println("Something went wrong with insertion");
            }

            // Closing statement
            statement.close();

        } catch (SQLException e) {
            System.out.println("\nUnexpected error in query -> " + e + "\n");
        }

    }

    /**
     * Updates student email given specific student ID.
     */
    static void updateStudentEmail(Connection conn, Scanner scanner) {

        // Important query values
        int student_id;
        String newEmail;

        // Looping until proper values accepted
        while (true) {
            System.out.println("\nUpdate Email:");
            try {

                // Getting the student ID
                System.out.print("Enter the student ID whose email will be changed: ");
                String idStr = scanner.nextLine();
                student_id = Integer.valueOf(idStr);

                // Getting the new email
                System.out.print("Enter new email: ");
                newEmail = scanner.nextLine();

                break;

            } catch (Exception e) {
                System.out.println("\nPlease input proper student id and email");
            }
        }

        try {
            // Create statement
            Statement statement = conn.createStatement();

            // Creating update query
            String update = "UPDATE students SET email = '" + newEmail +
                            "' WHERE student_id = " + Integer.toString(student_id);

            // Getting query results
            int result = statement.executeUpdate(update);
            if (result > 0) {
                System.out.println("Email updated successfully!");
            } else {
                System.out.println("Something went wrong with update");
            }

            // Closing statement
            statement.close();

        } catch (SQLException e) {
            System.out.println("\nUnexpected error in query -> " + e + "\n");
        }

    }

    /**
     * Deletes student entry from database.
     */
    static void deleteStudent(Connection conn, Scanner scanner) {

        // Important query values
        int student_id;

        // Looping until proper values accepted
        while (true) {
            System.out.println("\nDelete Student:");
            try {

                // Getting the student ID
                System.out.print("Enter the student ID to delete: ");
                String idStr = scanner.nextLine();
                student_id = Integer.valueOf(idStr);

                break;

            } catch (Exception e) {
                System.out.println("\nPlease input proper student id");
            }
        }

        try {
            // Create statement
            Statement statement = conn.createStatement();

            // Creating delete query
            String delete = "DELETE FROM students WHERE student_id = " + Integer.toString(student_id);

            // Getting query results
            int result = statement.executeUpdate(delete);
            if (result > 0) {
                System.out.println("Student deleted successfully!");
            } else {
                System.out.println("Something went wrong with deletion");
            }

            // Closing statement
            statement.close();

        } catch (SQLException e) {
            System.out.println("\nUnexpected error in query -> " + e + "\n");
        }

    }

    /**
     * Menu of potential database manipulation options.
     */
    static void menu(Connection conn) {

        // Scanner to get input
        Scanner scanner = new Scanner(System.in);

        // Main menu loop
        while (true) {
            // Printing menu options
            System.out.println("\nDatabase Options:");
            System.out.println("(1) Retrieve All Entries From Student Table");
            System.out.println("(2) Insert Student Into Database");
            System.out.println("(3) Update Student Email");
            System.out.println("(4) Delete Student From Database");
            System.out.println("(0) Exit");
            System.out.print("Enter the number that corresponds to the operation you want: ");

            // Getting user data
            try {

                // Using scanner to get number
                String optionStr = scanner.nextLine();
                int option = Integer.valueOf(optionStr);

                // Seeing what option the user chose
                if (option == 0) {
                    break;
                } else if (option == 1) {

                    // Returning all database results
                    getAllStudents(conn);

                } else if (option == 2) {

                    // Inserting student into database
                    addStudent(conn, scanner);

                } else if (option == 3) { 

                    // Updating student email
                    updateStudentEmail(conn, scanner);

                } else if (option == 4) {

                    // Deleting student from database
                    deleteStudent(conn, scanner);

                } else {
                    System.out.println("\n\nPlease enter a valid number from the provided choices\n");
                }

            } catch (Exception e) {
                // Printing that the input was invalid
                System.out.println("\n\nPlease enter a valid number from the provided choices\n");
                break;
            }
        }

        // Closing the scanner
        scanner.close();

    }

    /**
     * Main function that connects to database and enters menu loop.
     */
    public static void main(String[] args) {

        // Strings for database connection
        String url = "jdbc:postgresql://localhost:5432/A3";
        String user = "postgres";
        String password = "postgres";

        // Load PostgreSQL JDBC Driver
        try {
            Class.forName("org.postgresql.Driver");

            // Connect to the database
            Connection conn = DriverManager.getConnection(url, user, password);
            if (conn != null) {
                System.out.println("Connected to PostgreSQL successfully!");
            } else {
                System.out.println("Failed to establish connection.");
            }

            // Entering menu loop
            menu(conn);

            // Close the connection (in a real scenario, do this in a finally
            conn.close();

        // Catching any exceptions that might occur
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}