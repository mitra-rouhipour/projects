
/**
 **  Author:        Mitra Rouhipour
 **  Date:          February 23, 2021
 **  Course:        CPSC 1150
 **  Compiler:       JDK 1.8
 */

/**
 * Creates a college and test the various methods of the class.
 */
import java.util.Scanner;
public class TestCollege {
    public static void main(String[] args) {//main method

        //create an object of bank
        College aCollege = new College();

        int menuItem;
        boolean quit =false;

        Scanner input = new Scanner(System.in);

        do {
            // print the menu
            System.out.println("\nPlease enter:");
            System.out.println("1. To add a new student to the college.");
            System.out.println("2. To add a new international student to the college.");
            System.out.println("3. To add a new graduate student to the college.");
            System.out.println("4. To delete a student from the college using the student number.");
            System.out.println("5. To look up an existing student based on the student number.");
            System.out.println("6. To retrieve the login id for an existing student.");
            System.out.println("7. To add the grade point value and credit units earned for a " +
                    "\n course taken by an existing student ");
            System.out.println("8. To find a student with the highest GPA.");
            System.out.println("9. To calculate the student tuition fee");



            //read the item
            menuItem = input.nextInt();
            Student aStudent = null;
            String studentNumber = null;

            // write a switch case for options in the menu
            switch (menuItem) {
                case 1:
                    //To add a new student to the college
                    String name = nameInput();

                    //check validity of name to have two words
                    int nameCount = countWords(name);
                    if (nameCount != 2){
                        System.out.println("invalid name input");
                        break;
                    }
                    aStudent = new Student(name, addressInput());
                    aCollege.addStudent(aStudent);
                    System.out.println(aStudent); //call aStudent.toString()
                    break;
                case 2:
                    //To add a new international student to the college
                    name = nameInput();

                    //check validity of name to have two words
                    nameCount = countWords(name);
                    if (nameCount != 2){
                        System.out.println("invalid name input");
                        break;
                    }
                    aStudent = new InternationalStudent(
                            name,addressInput(),InternationalStudent.countryInput());
                    aCollege.addStudent(aStudent);
                    System.out.println(aStudent); //call aStudent.toString()
                    break;
                case 3:
                    //To add a new international student to the college
                    name = nameInput();

                    //check validity of name to have two words
                    nameCount = countWords(name);
                    if (nameCount != 2){
                        System.out.println("invalid name input");
                        break;
                    }
                    aStudent = new GraduateStudent(
                            name, addressInput(), GraduateStudent.ResearchTopicInput(),
                            GraduateStudent.studentAdvisorInput());
                    aCollege.addStudent(aStudent);
                    System.out.println(aStudent); //call aStudent.toString()
                    break;


                case 4:
                    //To delete a student from the college using the student number
                    studentNumber = studentNumberInput();
                    if (validateStudentNumber(studentNumber) == true) {
                        int num = Integer.parseInt(studentNumber);
                        aCollege.removeStudent(num);
                    }

                    break;

                case 5:
                    //look up for a student by student number
                    //To delete a student from the college using the student number
                    studentNumber = studentNumberInput();
                    if (validateStudentNumber(studentNumber) == true) {
                        int num = Integer.parseInt(studentNumber);
                        aStudent = aCollege.lookUpStudent(num);
                        if (aStudent == null) {
                            System.out.println("The student was not found by student number");
                        } else {
                            System.out.println("The student found by student number is\n " +
                                    aStudent);
                        }
                    }

                    break;

                case 6:
                    // retrieve log in id
                    //look up for a student by student number
                    //To delete a student from the college using the student number
                    studentNumber = studentNumberInput();
                    if (validateStudentNumber(studentNumber) == true) {
                        int num = Integer.parseInt(studentNumber);
                        String studentloginID = aCollege.lookUpLoginID(num);
                        if (studentloginID == null) {
                            System.out.println("The student login ID was not found by student number");
                        } else {
                            System.out.println("The student found by student number is\n " +
                                    studentloginID);
                        }
                    }
                    break;
                case 7:
                    //add a course to student
                    studentNumber = studentNumberInput();
                    if (validateStudentNumber(studentNumber) == true) {
                        int num = Integer.parseInt(studentNumber);
                        aStudent = aCollege.lookUpStudent(num);
                        TestCollege.addCourse2Student(aStudent);

                        System.out.println(aStudent);

                    }

                    break;
                case 8:
                    //Find a student with highest GPA
                    aStudent = aCollege.findStudentWithHighestGPA();
                    System.out.println("The student with highest GPA is " + aStudent);

                    break;


                case 9:
                    //calculate the student tuition fee
                    //add a course to student
                    studentNumber = studentNumberInput();
                    if (validateStudentNumber(studentNumber) == true) {
                        int num = Integer.parseInt(studentNumber);
                        aStudent = aCollege.lookUpStudent(num);
                        double tuitionFee = aStudent.getTuitionFee();
                        System.out.println(aStudent);
                    }
                    break;

                default:
                    //display invalid menu item
                    System.out.println("Error message : invalid input.");
                    quit = true;

            }

        } while (quit = true);
    }//end main


    /**
     * Ask the user a name
     * Use Scanner method to ask user to enter name of the student.
     *
     * @reurn name of the student
     */
    public static String nameInput() {
        Scanner scan = new Scanner(System.in);
        String name;
        System.out.println("Please enter student name:");
        name = scan.nextLine();

        return name;
    }

    //write a method to ask user for student address

    /**
     * Ask the user an address
     * Use Scanner method to ask user to enter address of the student.
     *
     * @reurn address of the student
     */
    public static String addressInput() {
        Scanner scan = new Scanner(System.in);
        String address;
        System.out.println("Please enter student address:");
        address = scan.nextLine();
        return address;
    }

    /**
     * Ask the user an student number and check whether student number is valid or not.
     * Use Scanner method to enter student number.
     *
     * @return studentNumber of the student which is an integer number
     */
    public static String studentNumberInput() {
        Scanner scan = new Scanner(System.in);
        String studentNumber;
        System.out.println("Please enter student number:");
        studentNumber = scan.next();
        return studentNumber;
    }

    /**
     * check whether student number is valid or not.student number should
     * contain 8 digits to be valid. Also, if the user enter input except
     * digits '0' to '9' the program should show error message.
     *
     * @param  studentNumber an integer number belongs to each student in the college
     * @return false if student number is null, or more than 8 digits, or
     * user does not enter digits between '0' to '9'.otherwise return true.
     */
    public static boolean validateStudentNumber(String studentNumber) {
        if (studentNumber == null) {
            System.out.println("The student number is invalid");
            return false;
        }
        if (studentNumber.length() != 8) {
            System.out.println("The student number should be 8 digits");
            return false;
        }
        for (int i = 0; i < studentNumber.length(); i++) {
            char ch = studentNumber.charAt(i);
            if (!(ch <= '9' && ch >= '0')) {
                System.out.println("Student number should contains digits between 0 and 9");
                return false;
            }
        }
        return true;
    }


    /**
     * counting the words in a sentence. The number of " " in a string determines
     * the number of words. If the " " is at the begin of the string it is the
     * begining og the sentence.
     *
     * @param sentence a String  which is the sentence that the method counts its words
     * @return counter and show the number of words in the string
     */
    public static int countWords(String sentence) {

        int counter = 0;
        for (int i = 0; i < sentence.length(); i++) {
            if (sentence.charAt(i) != ' ') {
                //start word
                counter++;

                //continue until end of word
                while (i < sentence.length() && sentence.charAt(i) != ' ') {
                    i++;
                }
                //end of word
            }
        }
        return counter;
    }

    /**
     *add number of credits and the grade of each course to a student.
     *ask user to input the number of credits and grade
     * use Scanner method to ask input from the user
     * add credits and grade to the student until the user does not want to continue
     *
     * @param student a student from the Student Class
     */
    public static void addCourse2Student(Student student) {
        int credit;
        double grade;
        String addCourse;
        //ask user if wants to add course
        Scanner scan = new Scanner(System.in);
        System.out.println("Do you want to add a course courses?(yes/no):");
        addCourse = scan.next();
        addCourse = addCourse.toLowerCase();
        while (addCourse.equals("yes")) {
            System.out.println("Please enter the number of course's credits:");
            credit = scan.nextInt();
            System.out.println("Please enter the course grade:");
            grade = scan.nextDouble();
            student.addCourse(credit, grade);

            //ask user wants to add course or not
            System.out.println("Do you want to add a course courses?(yes/no):");
            addCourse = scan.next();
            addCourse = addCourse.toLowerCase();
        }
    }
}


