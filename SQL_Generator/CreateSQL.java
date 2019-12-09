import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;

public class CreateSQL{
    public static void main(String[] args) {
        try{
            // Populate Table Setup for getting the file data, and generating the SQL statements
            PopulateTable students = new PopulateTable("students", 100);
            PopulateTable courses = new PopulateTable("courses", 5);
            PopulateTable classes = new PopulateTable("classes", 100);
            // File names of the data for the columns of each table
            String[] studentFiles = {"studentID.txt","firstName.txt","lastName.txt","sex.txt"};
            String[] courseFiles ={"courseID.txt","courseTitle.txt","department.txt"};
            String[] classFiles = {"classCode.txt","courseID.txt","studentID.txt","year.txt","semester.txt","GPA.txt"};

            // Used to connect to the database and pass all queries
            StatementQuery connect = new StatementQuery("jdbc:mysql://localhost/studentdb?","root","89a55cc5d12E3$$$");

            // Retrieving the data for each table based on the files provided
            ArrayList<ArrayList<String>> AllStudentLines = students.getFileLines("src\\StudentsData",studentFiles);
            ArrayList<ArrayList<String>> AllCourseLines = courses.getFileLines("src\\CoursesData",courseFiles);
            ArrayList<ArrayList<String>> AllClassLines = classes.getFileLines("src\\ClassesData",classFiles);

            // Generating the INSERT INTO table statements based on the file's data
            ArrayList<String> AllStudentStatements = students.getSQLStatements(AllStudentLines);
            ArrayList<String> AllCourseStatements = courses.getSQLStatements(AllCourseLines);
            ArrayList<String> AllClassStatements = classes.getSQLStatements(AllClassLines);

            // Executing all the SQL Statements and applying it for each table in the database
            connect.executeListStatements(AllStudentStatements);
            connect.executeListStatements(AllCourseStatements);
            connect.executeListStatements(AllClassStatements);

            // Generate the SQL Statements into the files from the SQL statements above

            //  System.out.println(students.InsertData("PopulateStudents.sql", "StudentsData", studentFiles));
            //  System.out.println(courses.InsertData("PopulateCourses.sql", "CoursesData", courseFiles));
            //  System.out.println(classes.InsertData("PopulateClasses.sql", "ClassesData", classFiles));

        } catch(FileNotFoundException ex){
            System.out.println(ex);
        } catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        } catch (ClassNotFoundException ex){
            System.out.println("Exception: " + ex.getMessage());
            ex.printStackTrace();
        } catch (NullPointerException ex){
            System.out.println("Exception: " + ex.getMessage());
            ex.printStackTrace();
        }



        
    }
}