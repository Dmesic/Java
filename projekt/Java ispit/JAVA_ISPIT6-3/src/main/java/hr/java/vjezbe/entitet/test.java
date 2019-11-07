package hr.java.vjezbe.entitet;

import java.util.Scanner;

public class test {

    public static void main(String[] args) {
//        double[] numArray = { 1, 2, 3, 4, 5 };5

        Scanner unos = new Scanner(System.in);
        double[] numArray = new double[5];
        double SD = calculateSD(numArray);


        for (int i = 0; i < numArray.length; i++)
        {
            System.out.println("Please enter number");
            numArray[i] = unos.nextDouble();
        }

        System.out.format("Standard Deviation = %.6f", SD);
    }

    public static double calculateSD(double numArray[])
    {
        double sum = 0.0, standardDeviation = 0.0;
        int length = numArray.length;

        for(double num : numArray) {
            sum += num;
        }

        double mean = sum/length;

        for(double num: numArray) {
            standardDeviation += Math.pow(num - mean, 2);
        }

        return Math.sqrt(standardDeviation/length);
    }
}
