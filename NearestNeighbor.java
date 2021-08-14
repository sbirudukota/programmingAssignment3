/*Programming Fundamentals
 Sravani Birudukota
 PROGRAMMING ASSIGNMENT 3*/

/*The Aim of this program is to apply the Nearest Neighbor
algorithm to classify the instance and calculate the Accuracy of correctly classified
instances*/

import java.io.*; 				// import all input and output packages
import java.util.Scanner; 	    //import scanner package
	
public class NearestNeighbor {
	
	public static void main(String[] args) throws FileNotFoundException {

		// declare and initialize the empty arrays
		// creating four different arrays
		double[][] testingValues = new double[75][4]; //2D array
		double[][] trainingValues = new double[75][4];
		String traininglabels[] = new String[75];     //1D array
		String testingLabels[] = new String[75];
		String predictedLabels[] = new String[75];
		
		//details 
		System.out.println("Programming Fundamentals\n" 
		+ "NAME: \n" + "PROGRAMMING ASSIGNMENT 3");
		System.out.println("");

		// allow user to enter the dataset file names
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the name of the training file: ");
		String trainingdata = sc.nextLine();
		System.out.print("Enter the name of the testing file: ");
		String testingdata = sc.nextLine();

		// read the trainingdata using scanner function
		File trainingfile = new File(trainingdata);
		Scanner scan2 = new Scanner(trainingfile);
		for (int i = 0; scan2.hasNextLine(); i++) {
			String str = scan2.nextLine();
			String[] num = str.split(",", 5);
			traininglabels[i] = num[4];
			for (int j = 0; j < 4; j++) {
				trainingValues[i][j] = Double.parseDouble(num[j]);
			}
		}

		// read the testingdata using scanner function
		File testingfile = new File(testingdata);
		Scanner scan = new Scanner(testingfile);
		for (int i = 0; scan.hasNextLine(); i++) {
			String s = scan.nextLine();
			String[] numbers = s.split(",", 5);
			testingLabels[i] = numbers[4];
			for (int j = 0; j < 4; j++) {
				testingValues[i][j] = Double.parseDouble(numbers[j]);
			}
		}
		//colse all scanners
		sc.close();
		scan.close();
		scan2.close();

		// accuracy method call 
		accur(testingLabels, predictions(testingValues, trainingValues, traininglabels, predictedLabels));

	}
	// create a method to make predictions
	private static String[] predictions(double testingVal[][], double trainingVal[][], String[] trainingClassLabel,
			String[] predictedLabels) {
		for (int i = 0; i < 75; i++) {
			int predit = dist(testingVal[i][0], testingVal[i][1], testingVal[i][2], testingVal[i][3], trainingVal);
			predictedLabels[i] = trainingClassLabel[predit];
		}
		return predictedLabels;  
	}

	// create a method to calculate accurancy
	public static void accur(String[] Labels, String[] predictLabels) {
		int n = 0;
		double Value,accut;
		Value = accut = 0;
		System.out.println("\nEX#: TRUE LABEL, PREDICTED LABEL");
		for (int i = 0; i < 75; i++) {
			n++;
			System.out.println(n + ": " + Labels[i] + " " + predictLabels[i]);
			if (Labels[i].equals(predictLabels[i])) {
				Value++;		// check for equality for labels and predicted labels
				accut = (Value / 75);
			}
		}
		//once accurancy is computed print it.
		System.out.print("ACCURACY: " + accut);
	}	 

	// compute distance
	public static int dist(double sepal_len, double sepal_width, double petal_length, double petal_width, double[][] train) {

		int rownum = 0;
		double petal_length_ = Math.pow((train[0][2] - petal_length), 2);
		double petal_width_ = Math.pow((train[0][3] - petal_width), 2);
		double sepal_len_ = (train[0][0] - sepal_len) * 2;
		double sepal_width_= Math.pow((train[0][1] - sepal_width), 2);
		double dist = Math.sqrt(sepal_len_ + sepal_width_ + petal_length_ + petal_width_);
		for (int i = 0; i < 75; i++) {
	    double tsw = Math.pow((train[i][1] - sepal_width), 2);
	    double tpl = Math.pow((train[i][2] - petal_length), 2);
	    double tsl = Math.pow((train[i][0] - sepal_len), 2);
	    double tpw = Math.pow((train[i][3] - petal_width), 2);
	    double testDist = Math.sqrt(tsl + tsw + tpl + tpw);
	    if (testDist < dist) {
				rownum = i;
				dist = testDist;

			}
		}

		return rownum;
	}

	

}
