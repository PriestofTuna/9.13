package objectPackage;

/**
 * Created by lytte on 7/11/2016.
 * todo
 * locate the largest element in the array
 * Print out the location in the array
 */
public class Location {
    public int arRow = 0;
    public int arCol = 0;
    public double maxValue; //callable values
    public Location(int row, int col, double array[][]) {
        arRow = row;
        arCol = col;
        maxValue = array[row][col];
        //changes public variables so local instances can call them
    }
    public static Location locateLargest(double[][] a) { //port to Location
        double maxValue = 0; //used for checking largest values, does not check for equal values
        int rowspot = 0;
        int colspot = 0;
        for(int row = 0; row < a.length; row++) {
            //row loop
            for (int j = 0; j < a[row].length; j++) {
                //column loop
                if(a[row][j] > maxValue) {
                    maxValue = a[row][j];
                    rowspot = row;
                    colspot = j;
                    //sets rowspot to row, colspot to j, and changes maxValue to new max value.
                }
            }
        }

        Location location = new Location(rowspot, colspot, a); //creates object
        return location; //returns object
    }
}
