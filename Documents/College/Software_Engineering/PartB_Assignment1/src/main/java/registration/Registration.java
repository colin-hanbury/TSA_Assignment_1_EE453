package registration;

import collegeRegistration.Course;
import collegeRegistration.Module;
import collegeRegistration.Student;
import java.util.ArrayList;
import org.joda.time.DateTime;

/**
 *
 * @author Colin Hanbury
 */
public class Registration {
    
    public static void main(String[] args){
        
        //create string list for modules in each course
        ArrayList<String> modulesEng = new ArrayList<>();
        modulesEng.add("Coding");
        modulesEng.add("Maths");
        modulesEng.add("Hardware");
        
        ArrayList<String> modulesIT = new ArrayList<>();
        modulesIT.add("Coding");
        modulesIT.add("Maths");
        modulesIT.add("Hardware");
        
        //create string list for students in each module
        ArrayList<String> studentsEngineering = new ArrayList<>();
        studentsEngineering.add("John Maher");
        studentsEngineering.add("Paul Fahy");
        
        ArrayList<String> studentsInfoTech = new ArrayList<>();
        studentsInfoTech.add("James Murphy");
        studentsInfoTech.add("Barry Jones");
        
        ArrayList<String> studentsEngIT = new ArrayList<>();
        studentsEngIT.add("John Maher");
        studentsEngIT.add("Paul Fahy");
        studentsEngIT.add("James Murphy");
        studentsEngIT.add("Barry Jones");
        
        //create string lists for related courses
        ArrayList<String> relatedCourses1 = new ArrayList<>();
        relatedCourses1.add("Engineering");
        relatedCourses1.add("IT");
        ArrayList<String> relatedCourses2 = new ArrayList<>();
        relatedCourses2.add("IT");
        ArrayList<String> relatedCourses3 = new ArrayList<>();
        relatedCourses3.add("Engineering");
        
        //create dates
        DateTime startDate = new DateTime(2018, 9, 1, 9, 0);
        DateTime endDate = new DateTime(2019, 5, 14, 18, 0);
        
        //create students as Student objects
        Student john = new Student("John Maher", "23", "20-04-1995", 12344321, "Engineering", modulesEng);
        Student paul = new Student("Peter Fahy", "20", "14-12-1999", 12344322, "IT", modulesIT);
        Student james = new Student("James Murphy", "23", "05-05-1995", 12344321, "Engineering", modulesEng);
        Student barry = new Student("Barry Jones", "20", "10-11-1999", 12344322, "IT", modulesIT);
        //create modules as Module objects
        Module coding = new Module("Coding", "CT101", studentsInfoTech, relatedCourses1);
        Module maths = new Module("Maths", "MA061", studentsEngIT, relatedCourses1);
        Module webDesign = new Module("Web Design", "CT103", studentsInfoTech, relatedCourses2);
        Module hardware = new Module("Harware Design", "Eng401", studentsEngineering, relatedCourses3);
        
        //add students to lists
        ArrayList<Student> studentsEng = new ArrayList<>();
        studentsEng.add(john);
        studentsEng.add(paul);
        
        ArrayList<Student> studentsIT = new ArrayList<>();
        studentsIT.add(james);
        studentsIT.add(barry);
        
        //add modules to lists
        ArrayList<Module> modulesEngineering = new ArrayList<>();
        modulesEngineering.add(coding);
        modulesEngineering.add(maths);
        modulesEngineering.add(hardware);
        
        ArrayList<Module> modulesInfoTech = new ArrayList<>();
        modulesInfoTech.add(coding);
        modulesInfoTech.add(maths);
        modulesInfoTech.add(webDesign);
        
        //create courses
        Course engineering = new Course("Engineering", modulesEngineering, studentsEng, startDate, endDate);
        Course it = new Course("IT", modulesInfoTech, studentsIT, startDate, endDate);
        
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(it);
        courses.add(engineering);
        // the program should print out a list of all the courses, their respective modules,
        // and all students, their usernames, assigned modules and the course(s) they are registered
        // for to the console.
        // student can only do one course at a time, so no need to print student.getCourse()
        for(Course course: courses){
            System.out.println("\n\n\nCourse Name: " + course.getName());
            System.out.println("\nAssociated Modules:");
            for(Module module: course.getModules()){
                System.out.println("\n" + module.getName() + ":");
                System.out.println("\tRelated courses:");
                for(String relCourse: module.getRelCourses()){
                    System.out.println("\t\t" + relCourse);
                }
            }

            System.out.println();
            System.out.println("Students:");
            for(Student student: course.getStudents()){
                System.out.println("\nName: " + student.getName());
                System.out.println("Username: " + student.getUsername());
                System.out.println("Student's Modules:");
                for(String module:  student.getModules()){
                    System.out.println("\t" + module.toString());
                }
            }
        }
    }
}
