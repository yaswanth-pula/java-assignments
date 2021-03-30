import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

class Student{
    private int studentID;
    private String name;
    private int age;
    private String gender;
    private String department;
    private int yearOfEnrollment;
    private double percentageTillDate;

    public Student(int studentID, String name, int age, String gender,
                   String department, int yearOfEnrollment, double percentageTillDate) {
        this.studentID = studentID;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.department = department;
        this.yearOfEnrollment = yearOfEnrollment;
        this.percentageTillDate = percentageTillDate;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getYearOfEnrollment() {
        return yearOfEnrollment;
    }

    public void setYearOfEnrollment(int yearOfEnrollment) {
        this.yearOfEnrollment = yearOfEnrollment;
    }

    public double getPercentageTillDate() {
        return percentageTillDate;
    }

    public void setPercentageTillDate(double percentageTillDate) {
        this.percentageTillDate = percentageTillDate;
    }

    public String toString(){
        return "\n\nStudent ID: "+getStudentID()+"\nStudent Name: "+getName()
                +"\nStudent Age : "+getAge()+"\nStudent Gender: "+getGender()
                +"\nStudent Department: "+getDepartment()
                +"\nStudent Enrollment Year: "+getYearOfEnrollment()
                +"\nStudent Percentage :"+getPercentageTillDate();
    }
}
public class Assignment12 {
    public static ArrayList<Student> addElementsToList(){
        ArrayList<Student> studentList = new ArrayList<>();
        studentList.add(new Student(111, "Jiya Brein", 17, "Female", "Computer Science", 2018, 70.8));
        studentList.add(new Student(122, "Paul Niksui", 18, "Male", "Mechanical", 2016, 50.2));
        studentList.add(new Student(133, "Martin Theron", 17, "Male", "Electronic", 2017, 90.3));
        studentList.add(new Student(144, "Murali Gowda", 18, "Male", "Electrical", 2018, 80));
        studentList.add(new Student(155, "Nima Roy", 19, "Female", "Textile", 2016, 70));
        studentList.add(new Student(166, "Iqbal Hussain", 18, "Male", "Security", 2016, 70));
        studentList.add(new Student(177, "Manu Sharma", 16, "Male", "Chemical", 2018, 70));
        studentList.add(new Student(188, "Wang Liu", 20, "Male", "Computer Science", 2015, 80));
        studentList.add(new Student(199, "Amelia Zoe", 18, "Female", "Computer Science", 2016, 85));
        studentList.add(new Student(200, "Jaden Dough", 18, "Male", "Security", 2015, 82));
        studentList.add(new Student(211, "Jasna Kaur", 20, "Female", "Electronic", 2019, 83));
        studentList.add(new Student(222, "Nitin Joshi", 19, "Male", "Textile", 2016, 60.4));
        studentList.add(new Student(233, "Jyothi Reddy", 16, "Female", "Computer Science", 2015, 45.6));
        studentList.add(new Student(244, "Nicolus Den", 16, "Male", "Electronic", 2017, 95.8));
        studentList.add(new Student(255, "Ali Baig", 17, "Male", "Electronic", 2018, 88.4));
        studentList.add(new Student(266, "Sanvi Pandey", 17, "Female", "Electric", 2019, 72.4));
        studentList.add(new Student(277, "Anuj Chettiar", 18, "Male", "Computer Science", 2017, 57.5));

        return studentList;
    }
    public static void main(String[] args) {
        ArrayList<Student> studentList = addElementsToList();

//        Print the name of all departments in the college
        Set<String> departmentSet = studentList.stream()
                                .map(Student :: getDepartment)
                                .collect(Collectors.toSet());
        System.out.println("Department List : \n"+ departmentSet);

//        Get the names of all students who have enrolled after 2018?
        List<String> studentNamesList = studentList.stream()
                                        .filter(student -> student.getYearOfEnrollment() > 2018)
                                        .map(Student :: getName)
                                        .collect(Collectors.toList());
        System.out.println("\nStudent Names Enrolled After 2018\n"+studentNamesList);

//        Get the details of all male student in the computer sci department
        List<Student> csMaleStudentList = studentList.stream()
                                         .filter(student -> student.getDepartment().equals("Computer Science"))
                                         .filter(student -> student.getGender().equals("Male"))
                                         .collect(Collectors.toList());

        System.out.println("\nMale Students in Computer Science");
        for(Student student: csMaleStudentList)
            System.out.println(student);

//        How many male and female student are there
        Map<String,Long> genderCountMap = studentList.stream()
                                          .collect(groupingBy(Student::getGender,
                                                   Collectors.counting()));

        Long maleCount = genderCountMap.get("Male");
        Long femaleCount = genderCountMap.get("Female");
        System.out.println("Male Count: "+maleCount);
        System.out.println("Female Count: "+femaleCount);

//        What is the average age of male and female students
        double averageAge = studentList.stream()
                                .collect(Collectors.averagingInt(Student::getAge));
        System.out.println("Average Age is : "+averageAge);


//        Get the details of highest student having highest percentage
        Optional<Student> maxPercentageStudent = studentList.stream().max(Comparator
                .comparingDouble(Student::getPercentageTillDate));

        maxPercentageStudent.ifPresent(student -> System.out.println("\nHighest percentage Student: \n" + student));

//                Count the number of students in each department
        Map<String,Long> studentCountMap = studentList.stream()
                .collect(groupingBy(Student::getDepartment,
                        Collectors.counting()));

        System.out.println("\n\nStudentCount\t\tDepartment");
        for(String department:studentCountMap.keySet()){
            System.out.println("\t"+studentCountMap.get(department)+"\t\t\t\t"+department);
        }

//        What is the average percentage achieved in each department
        Map<String,Double> averagePercentageMap = studentList.stream()
                                            .collect(groupingBy(Student::getDepartment,
                                                    Collectors.averagingDouble(Student::getPercentageTillDate)));
        System.out.println("\n\nAverage\t\t\tDepartment");
        for(String department:averagePercentageMap.keySet()){
            System.out.println(averagePercentageMap.get(department)+"\t\t\t\t"+department);
        }

//        Get the details of youngest male student in the Electronic department
        Optional<Student> youngestElectronicMaleStudent = studentList.stream()
                                                        .filter(student -> student.getDepartment().equals("Electronic"))
                                                        .min(Comparator.comparingInt(Student::getAge));
        youngestElectronicMaleStudent.ifPresent(student ->
                System.out.println("Youngest Electronic Student is " + student));

//        How many male and female students are there in the computer science department
        Map<String,Long> csGenderCountMap = studentList.stream()
                                            .filter(student -> student.getDepartment().equals("Computer Science"))
                                            .collect(groupingBy(Student::getGender,
                                            Collectors.counting()));

        System.out.println("Male and Female Count in Computer Science");
        System.out.println(csGenderCountMap);
    }

}
