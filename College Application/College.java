
/**
 **  Author:        Mitra Rouhipour
 **  Date:          February 23, 2021
 **  Course:        CPSC 1150
 **  Compiler:       JDK 1.8
 */

import java.util.ArrayList;
public class College {
    private ArrayList<Student> listOfStudent;

    /**
     * Constructs an empty College.
     */
    public College() {

        listOfStudent = new ArrayList<Student>();
    }

    /**
     * Adds a student to the college regardless of whether the <code>student</code> was in the
     * college  already or not
     *
     * @param  aStudent is a Student to add to the college
     */
    public void addStudent(Student aStudent) {
        if (aStudent == null) {
            return;
        }
        listOfStudent.add(aStudent);
    }

    /**
     * Removes a student from the college from the <code>student</code> with using
     * student number
     *
     * @param  studentNumber  is an integer number should be removed from the college
     */
    public void removeStudent(int studentNumber) {
        for (Student aStudent : listOfStudent) {
            if (aStudent.getStudentNumber() == studentNumber) {
                listOfStudent.remove(aStudent);
                break;
            }
        }
    }

    /**
     * look up a student from the college from the <code>student</code> with using
     * student number
     *
     * @param studentNumber is an int number for a student that should be looked up at the college
     * @return aStudent if the student was found by its student number, otherwise return null.
     */
    public Student lookUpStudent(int studentNumber) {
        for (Student aStudent : listOfStudent) {
            if (aStudent.getStudentNumber() == studentNumber) {
                return aStudent;
            }
        }
        return null;
    }

    /**
     *  LooK up login ID from the college from the <code>student</code> with using
     * student number
     *
     * @param studentNumber is an integer number of a student that login ID is related to it.
     * @return null if a student is null, otherwise return student login ID
     */
    public String lookUpLoginID(int studentNumber){
        Student aStudent = this.lookUpStudent(studentNumber);//pointer
        if (aStudent == null){
            return null;
        }
        return aStudent.getLoginID();

    }

    /**
     * Gives the student highest GPA of any student in the college.
     *
     * @return the student with highest  GPA of the student coin in the college  and null if the student is empty.
     */
    public Student findStudentWithHighestGPA(){
        double maxGPA = 0;
        Student maxStudent = null;
        for (Student aStudent : listOfStudent){
            double studentGPA =aStudent.calculateGPA();
            if (studentGPA > maxGPA){
                maxGPA = studentGPA;
                maxStudent = aStudent;
            }
        }
        return maxStudent;
    }

    /**
     * Makes a string of the form (listOfStudent)
     *@return the string
     */
    @Override
    public String toString() {
        String str = "";
        for (Student aStudent : listOfStudent){
            str += "\n" +aStudent.toString();
        }
        return str;
    }

}
