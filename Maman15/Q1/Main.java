/*
 * Q1 Maman 15
 */
import java.util.Scanner;

public class Main 
{
	  	private static int size;
	 	private static int numOfThreads;
		private static int[] arr;

	public static void main(String[] args) {
		getInput();
		printArr();
	}

	private static void printArr() {
		arr =new int[size];
		for(int i = 0; i< size; i++)
			arr[i]=(int)(Math.random()*100);
		System.out.println("Initial array:");
		for(int i: arr) {
			System.out.print(i + " ");
		}
		mergeArr();
		System.out.println("\nSorted array:");
		for(int i: arr)
		System.out.print(i+" ");
	}

	private static void mergeArr() {
		MergeThreadHandler handler = new MergeThreadHandler(numOfThreads, size, arr);
		arr = handler.runMergeThread();
	}

	private static void getInput()
	{	
		Scanner scan= new Scanner(System.in); 
		System.out.println("Please enter the size of the array:");
		size = scan.nextInt();
		if (size<=0) {
			System.out.println("Error, input should be bigger than 0");
			System.exit(1);
		}
		System.out.println("Please enter the number of threads");
		numOfThreads = scan.nextInt();
		if (numOfThreads<=0) {
			System.out.println("Error, input should be bigger than 0");
			System.exit(1);
		}
	}
}
