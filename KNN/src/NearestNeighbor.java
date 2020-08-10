//Importing all utilities/inputs outputs.
import java.util.Scanner;
import java.io.IOException;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors

	/* A Machine Learning Program that takes data
	 * from a file and uses the Nearest Neighbor algorithm
	 * to help learn from training and testing examples.
	 */

public class NearestNeighbor {
 
	static Double [][] testingData = new Double [75][4];
	static Double [][] trainingData = new Double [75][4];
	static String [] testingLabels = new String [75];
	static String [] trainingLabels = new String [75];
	
	public static void main (String[] args) throws IOException {

		Scanner scn = new Scanner (System.in);
		
		//Introduction.
		System.out.println("Programming Fundamentals\nNAME: Ryan Manning\nPROGRAMMING ASSIGNMENT 3");
		System.out.print("Enter the name of the training file: ");
		String fileName = scn.nextLine (); // Type in "iris-training-data.csv"
		readFile (fileName, trainingData, trainingLabels);
	  
		System.out.print ("Enter the name of the testing file: ");    
		fileName = scn.nextLine (); // Type in "iris-testing-data.csv"
		readFile (fileName, testingData, testingLabels);
		
		String [] predictions = makePredictions ();
		System.out.println ("ACCURACY: " +calcAccuracy (predictions));

	}
	
	private static double calcAccuracy(String [] predictions) {
		
		double classCalc = 0;
		System.out.println("EX#: TRUE LABEL, PREDICTED LABEL");
		for (int x = 0; x < testingLabels.length; x++) {
		
			System.out.println(x+": "+testingLabels[x]+" "+predictions[x]);
			
	     if (testingLabels[x].equals (predictions[x])) {
	            
	      	  //Classification calculation.
	      	  classCalc++;
	        }
	    }
	    double accur = classCalc / (testingLabels.length);
	    return accur;
	}
	
	private static String [] makePredictions () {
		
		String [] predictions = new String [75];
		
		//Distance needed from the closest 'neighbors' of each attribute.
	    double minimumDist = 99999, classCalc = 0;
	    int nearestSampIndex = 0;
	    
	    for (int x = 0; x < testingData.length; x++) {
	    	minimumDist = 99999;
	    	
	    	for(int y = 0; y < trainingData.length; y++) {
	    		double calcDist = calcDist (x, y);
	    			if (calcDist < minimumDist ) {
	    				minimumDist = calcDist;
	    				nearestSampIndex = y;
	    		}
	    	}
	    	
	    	predictions [x] = trainingLabels [nearestSampIndex];
	    }
	    
	    return predictions;
	}
	
	private static double calcDist (int testingDataIndex, int trainingDataIndex) {
		
	   	double d1 = Math.pow ((testingData[testingDataIndex][0] - trainingData[trainingDataIndex][0]), 2);
	    double d2 = Math.pow ((testingData[testingDataIndex][1] - trainingData[trainingDataIndex][1]), 2);
	    double d3 = Math.pow ((testingData[testingDataIndex][2] - trainingData[trainingDataIndex][2]), 2);
	    double d4 = Math.pow ((testingData[testingDataIndex][3] - trainingData[trainingDataIndex][3]), 2);
	    return Math.sqrt (d1+d2+d3+d4);
		
	}
	
	private static void readFile(String fileName, Double [][] attributes, String [] labels) throws FileNotFoundException {

		File myObj = new File(fileName);
	    Scanner myReader = new Scanner(myObj);
		
	    //Line number of file.
	    int index = 0;
	    
	    //Loop through our file.
	    while (myReader.hasNextLine ()) {
	        String data = myReader.nextLine ();
	        String [] lineData = data.split (","); //Split up the line data into the array.
	        
	        //Loop through line data to set 2D array.
	        for (int x = 0; x < lineData.length; x++) {
	        	if (x < 4) {
	        	attributes [index][x] = Double.valueOf(lineData [x]);
	        	}
	        	else {
	        		labels [index] = lineData [x];
	        	}
	        }
	        index++;
	      }
	    myReader.close();
	}
}
