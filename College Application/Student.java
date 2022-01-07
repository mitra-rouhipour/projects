/**
 **  Author:        Mitra Rouhipour
 **  Date:          February 23, 2021
 **  Course:        CPSC 1150
 **  Compiler:       JDK 1.8
 */
import java.util.Objects;
public class Student {
    //data field
    private String name;
    private String address;
    private String loginID;
    private int studentNumber;
    private static int studentNumberCounter = 10000001;
    private int totalCredits;
    private double totalPoints;
    private static final double tuitionFeePerCredit = 101.41;

    /**
     *the first Construct of a student and initialize instance values
     *
     *@param studentName the name of the student
     *@param studentAddress the address of the student
     */
    public Student(String studentName,String studentAddress){
    name = studentName;
    address = studentAddress;
    studentNumber = studentNumberCounter++; // update student number
    totalCredits = 0;
    totalPoints = 0;

    //compute login id
    loginID = computeLoginID();

    }

    /**
     * the second Constructor of  a student and initialize instance values
     *
     *@param studentName the name of the student
     */

    public Student(String studentName){
    name = studentName;
    address = ""; //initialize address
    studentNumber = studentNumberCounter++; // update student number
    totalCredits = 0;
    totalPoints = 0;

    // compute login id
    loginID = computeLoginID();

    }

    /**
     * x the student name
     *@return the name
     */
    public String getName(){
        return name;
    }//end getName method

    /**
     * Retrieves the student address
     *@return the address
     */
    public String getAddress(){
        return address;
    }//end getAddress method

    /**
     * Retrieves the student number
     *@return the studentNumber
     */
    public int getStudentNumber(){

        return  studentNumber;
    }//end getStudentNumber method

    /**
     * Retrieves the loginID
     *@return the loginID
     */
    public String getLoginID(){
        return loginID;
    }//end getloginID method

    /**
     * generate computer login ID. Computer login Id contains is made of the first letter of
     * the first name + three first letter of last name + the last two digits of student number.
     * <p>
     * the method first character of name and convert it to lower case.
     * Then find the index ' ' between first name and last name in the string.
     * next it find the first three characters after ' '. If the last name is less
     * or equal three letters, it will use all of that to generate loginID.
     * all letters should be in lower case.
     * Student number should be convert to string.
     * The last two digits of student number should be find
     *
     * @return LoginID a string that is combination of firstname,lastname,digits
     */
    public String computeLoginID(){
        String lastNameLetters; // 3 letters of the last name
        //finding the first letter of first name
        char firstName = name.charAt(0);
        //convert the first letter to lower case
        firstName = Character.toLowerCase(firstName);

        //find index of space between first name and last name
        int space = name.indexOf(" ");
        //find last name in the string name
        String lastName = name.substring(space + 1);

        //if the last name is more than 3 letters
        if(lastName.length() > 3){
            lastNameLetters = lastName.substring(0,3);
            lastNameLetters = lastNameLetters.toLowerCase();//convert to lowercase
        }
        //if the last name is less or equal than 3 letters
        else{
            lastNameLetters = lastName;
            lastNameLetters = lastNameLetters.toLowerCase();//convert to lowercase
        }
        //converting student number to string
         String studentNumber1 = Integer.toString(studentNumber);

        //fid the last 2 digits of student number
        String digits = studentNumber1.substring(studentNumber1.length() - 2);

        String loginId = firstName + lastNameLetters + digits;
        return loginId;
    }//end method to generate Login ID

    /**
     * add credits and gradepoint to  the student
     *
     *@param credits an integer number to add number of credits of each course to a student
     *@param gradePoint a double number to add grade of each course to a student
     */
    public void addCourse (int credits, double gradePoint){
        totalCredits += credits;
        totalPoints += gradePoint;
    }//end method of addCourse

    /**
     * calculate of GPA of a student
     *
     *@return gpa a double number which is division of total grades to total credits
     */
    public double calculateGPA(){
        double gpa = this.totalPoints / this.totalCredits;
        return  gpa;
    }//end method of calculateGPA

    /**
     * Calculate to total tuition fee
     *
     * @return total tuition fee by multiply the number of credits to tutition fee per credit
     */
    public double getTuitionFee(){

        return this.totalCredits * Student.tuitionFeePerCredit;
    }

    /**
     *
     * @param otherStudent an object from other class
     * @return false if students objects are not equal otherwise return true
     */
    @Override
    public boolean equals(Object otherStudent) {
        //if object be null or the objects do not belong to the same class
        if (otherStudent == null || getClass() != otherStudent.getClass()){
            return false;
        }
        if (this == otherStudent) {
            return true;
        }
        Student student = (Student) otherStudent;//casting

        return studentNumber == student.studentNumber &&
                totalCredits == student.totalCredits &&
                Double.compare(student.totalPoints, totalPoints) == 0 &&
                name.equalsIgnoreCase(student.getName()) &&
                address.equalsIgnoreCase(student.getAddress()) &&
                loginID.equals(student.getLoginID());
    }

    /**
     * retrieves total credits
     *
     * @return total credits
     */
    public int getTotalCredits(){
        return totalCredits;
    }
    /**
     * Makes a string of the form (name, address, loginID, studentNumber,totalCredits,totalPoints)
     *
     *@return the string
     */
    @Override
    public String toString() {
        return
                getClass().getName() +
                "Student name is: " + name +" \n" +
                "Student address is : " + address + "\n" +
                "The loginID is : " + loginID + "\n" +
                "studentNumber is : " + studentNumber + "\n" +
                "The number os total Credits is: " + totalCredits + "\n" +
                "The total Points is: " + totalPoints + "\n" +
                "The total GPA is : " + calculateGPA() + "\n" +
                "The total tuition fee is: " + getTuitionFee() + "\n";
    }

}
