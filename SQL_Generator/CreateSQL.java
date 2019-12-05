public class CreateSQL{
    public static void main(String[] args) {

        PopulateTable students = new PopulateTable("students", 100);
        PopulateTable courses = new PopulateTable("courses", 5);
        PopulateTable classes = new PopulateTable("classes", 100);

        String[] studentFiles = {"studentID.txt","firstName.txt","lastName.txt","sex.txt"};
        String[] courseFiles ={"courseID.txt","courseTitle.txt","department.txt"};
        String[] classFiles = {"classCode.txt","courseID.txt","studentID.txt","year.txt","semester.txt","GPA.txt"};

        System.out.println(students.InsertData("PopulateStudents.sql", "StudentsData", studentFiles));
        System.out.println(courses.InsertData("PopulateCourses.sql", "CoursesData", courseFiles));
        System.out.println(classes.InsertData("PopulateClasses.sql", "ClassesData", classFiles));
        
    }
}