package view;

import objectPackage.Location;

import java.util.Scanner;

/**
 * Created by lytte on 7/11/2016.
 * takes scanner input from user
 * @author lytte
 */
public class BasicView {
    private static Scanner keyboard = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.print("Enter the array's row's ");
        int row = keyboard.nextInt();
        System.out.println("Enter the array's column count ");
        int col = keyboard.nextInt();
        //input for rows and columns

        double array[][] = new double[row][col];
        for(int rowLoop = 0; rowLoop < row; rowLoop++) {
            System.out.println("Enter values");
            for(int colLoop = 0; colLoop < col; colLoop++) {
                array[rowLoop][colLoop] = keyboard.nextDouble();
            }
        }// takes input for columns

        Location locate = Location.locateLargest(array); // calls locateLargest
        System.out.print("\n" + "largest element in array " + locate.maxValue + "\n" + "Row in array "
                + locate.arRow + "\n" + "column in array " + locate.arCol); //prints result
    }

}
