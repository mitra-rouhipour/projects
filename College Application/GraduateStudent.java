/**
 **  Author:        Mitra Rouhipour
 **  Date:          February 23, 2021
 **  Course:        CPSC 1150
 **  Compiler:       JDK 1.8
 */

import java.util.Scanner;
public class GraduateStudent extends Student {
    private String researchTopic;
    private String studentSupervisor;

    /**
     *the first Construct of a GraduateStudent and initialize instance values
     *
     * @param studentName name of the graduate student
     * @param studentAddress address of the graduate student
     * @param researchTopic the topic of the research
     * @param studentSupervisor the name of student's supervisor
     */
    public GraduateStudent(
            String studentName,String studentAddress, String researchTopic, String studentSupervisor) {
        super(studentName,studentAddress);
        this.researchTopic = researchTopic;
        this.studentSupervisor = studentSupervisor;

    }

    /**
     *the second Construct of a student and initialize instance values
     *
     * @param studentName name of the student
     * @param researchTopic the topic of the research
     * @param studentSupervisor the name of student's supervisor
     */
    public GraduateStudent(
            String studentName,String researchTopic, String studentSupervisor) {
        super(studentName);
        this.researchTopic = researchTopic;
         this.studentSupervisor = studentSupervisor;
    }

    /**
     * Method to ask user research topic
     * this method use Scanner method to ask user the research topic
     *
     * @return studentResearchTopic
     */
    public static String ResearchTopicInput() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the research topic: ");
        String studentResearchTopic = scan.nextLine();
        return studentResearchTopic;
    }

    /**
     * Method to ask user the name of the student's advisor
     * this method use Scanner method to ask user the name of advisor
     *
     * @return studentAdvisorName
     */
    public static String studentAdvisorInput() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the name of student's advisor: ");
        String studentAdvisorName = scan.nextLine();
        return studentAdvisorName;
    }
    /**
     *
     * @param otherStudent an object from other class
     * @return false if students objects are not equal otherwise return true
     */
    @Override
    public boolean equals(Object otherStudent) {
        if (!super.equals(otherStudent)){
            return false;
        }
        if (this == otherStudent){
            return true;
        }
        GraduateStudent gradStudent = (GraduateStudent) otherStudent;
        return researchTopic.equalsIgnoreCase(gradStudent.researchTopic)&&
                studentSupervisor.equalsIgnoreCase(gradStudent.studentSupervisor);
    }

    /**
     * Makes a string of the form (name, address, loginID, studentNumber,
     * totalCredits,totalPoints,researchTopic,studentSupervisor)
     *
     *@return the string
     */
    @Override
    public String toString() {
        return
                super.toString() +
                "Research Topic is: " + researchTopic + "\n" +
                "Student Supervisor is: " + studentSupervisor;
    }
}
