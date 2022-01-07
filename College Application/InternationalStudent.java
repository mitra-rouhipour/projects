/**
 **  Author:        Mitra Rouhipour
 **  Date:          February 23, 2021
 **  Course:        CPSC 1150
 **  Compiler:       JDK 1.8
 */

import java.util.Objects;
import java.util.Scanner;

public class InternationalStudent extends Student {
    private String country;
    private static final double internationalTuitionFeePerCredit =625.40;

    /**
     * the first Construct of a InternationalStudent and initialize instance values
     *
     * @param studentName the name of international student
     * @param studentAddress the address of international student
     * @param country the country which international student comes from
     */
    public InternationalStudent(String studentName,String studentAddress, String country){
        super(studentName,studentAddress);
        this.country = country;
    }
    /**
     * the first Construct of a InternationalStudent and initialize instance values
     *
     * @param studentName the name of international student
     * @param country the country which international student comes from
     */
    public InternationalStudent(String studentName,String country){
        super(studentName);
        this.country = country;
    }

    /**
     * Method to ask user the country attribute
     * this method use Scanner method to ask user the country value
     *
     * @return studentCountry
     */
    public static String countryInput() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the name of country: ");
        String studentCountry = scan.nextLine();
        return studentCountry;
    }

    /**
     * calculate international tuition fee
     *
     * @return international tuition fee
     */

    @Override
    public double getTuitionFee(){
        return super.getTotalCredits() * InternationalStudent.internationalTuitionFeePerCredit;
    }

    /**
     *
     * @param otherStudent an object from other class
     * @return false if students objects are not equal otherwise return true
     */
    @Override
    public boolean equals(Object otherStudent) {
        if (!super.equals(otherStudent)) {
            return false;
        }
        if (this == otherStudent) {
            return true;
        }

        InternationalStudent intStudent = (InternationalStudent) otherStudent;
        return country.equals(intStudent.country);
    }
    /**
     * Makes a string of the form (name, address, loginID, studentNumber,
     * totalCredits,totalPoints,country)
     *
     *@return the string
     */
    @Override
    public String toString() {
        return super.toString() +
                "country is " + country;
    }
}
