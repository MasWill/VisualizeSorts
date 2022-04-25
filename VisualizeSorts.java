//Imports
import javax.swing.*;
import java.awt.*;

public class VisualizeSorts extends JFrame{
	
	public static void main(String[] args) {
		
		//Objects
		VisualizeSorts runMain = new VisualizeSorts();
	}
	
	public VisualizeSorts() {
		//set GUI elements
		setTitle("Assignment11");
		setLayout(new  GridLayout(0,3,0,0));
		setSize(900,400);
		
		//Declare 3 arrays (one for each sort)
		int[] nums = new int[50];
		int[] nums2 = new int[50];
		int[] nums3 = new int[50];
		
		//Add ints 1-50
		for (int i = 1; i <= 50; i++) {
			nums[i-1] = i;
		}
		
		//Shuffle
		for (int i = 0; i < nums.length; i++) {
			int index = (int) (Math.random() * nums.length);
			int temp = nums[i];
			nums[i] = nums[index];
			nums[index] = temp;
		}
		
		//Copy shuffled elements from array1 to the other two
		for (int i = 0; i < nums.length; i++) {
			nums2[i] = nums[i];
			nums3[i] = nums[i];
		}
		
		//Declare and create sort objects
		selectionSort sort1 = new selectionSort("Selection Sort", nums);
		insertionSort sort2 = new insertionSort("Insertion Sort", nums2);
		bubbleSort sort3 = new bubbleSort("Bubble Sort", nums3);
		
		//Add sort objects to frame
		add(sort1);
		add(sort2);
		add(sort3);
		
		//More GUI Elements
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
} //End Main Class


class insertionSort extends JPanel implements Runnable{
	
	//Variables
	private String name;
	private int[] nums;
	private int lastIndex;
	private boolean sortDone = false;

	public insertionSort(String name, int[] nums) {
		this.name = name;
		this.nums = nums;
		lastIndex = nums[nums.length - 1];	//last index of this algorithm is the last index of the array

		//Creates Thread in constructor
		Thread t1 = new Thread(this);
		t1.start();
	}

	//Methods
	public void run() {
		try { 

			for (int i = 1; i < nums.length; i++) {

				for (int j = i - 1; j >= 0 && nums[j] > nums[j + 1]; j--) {
					//Switches next index with current index
					int temp = nums[j + 1]; 
					nums[j + 1] = nums[j];
					nums[j] = temp;
				} //End inner loop

				//Puts thread to sleep, and repaints histogram
				Thread.sleep(500);
				repaint();
			} //end outer loop 

			sortDone = true;	//Sort finished bool
			repaint();			//Repaints final histogram to screen

		} catch (Exception e) {
			e.printStackTrace();
		}
	}//end run method

	//Swing method that allows rectangles to be drawn on panel
	protected void paintComponent(Graphics g) {

		//Calls paintComponent method from component parent class
		super.paintComponent(g);

		g.drawString(name, 125, 50);	//Draws Sort Name
		g.translate(0, 400);			//moves origin to bottom left of panel
		((Graphics2D) g).scale(1,-1);	//flips y axis so y values go up instead of down

		//Prints entire histogram using rectangles based off of nums array
		for(int i = 0; i < nums.length; i++) {
			//makes last index rect black when finished
			if(i == lastIndex && sortDone == true) {	
				g.fillRect(10 + (5 * i), 50, 5, nums[i] * 5);
				g.drawRect(10 + (5 * i), 50, 5, nums[i] * 5);
			} else {
				g.drawRect(10 + (5 * i), 50, 5, nums[i] * 5);
			}
		}
	}//End paintComponent method

}//end insertionSort class

class selectionSort extends JPanel implements Runnable {
	
	//Variables
	private String name;
	private int[] nums;
	private int lastIndex;
	private boolean sortDone = false;

	public selectionSort(String name, int[] nums) {
		this.name = name;
		this.nums = nums;
		
		//Creates Thread in constructor
		Thread t2 = new Thread(this);
		t2.start();
	}

	//Methods
	public void run() {
		try { 
			
			//Selection Sort Algorithm
			int minIndex;
			int temp;
			
			for(int i = 0; i < nums.length - 1; i++) {
				minIndex = i;
				
				for(int j = i+1; j < nums.length; j++) {
					if (nums[j] < nums[minIndex]) {
						minIndex = j;
					}
				} //End inner loop
				
				lastIndex = minIndex;	//finds last index
				
				//Switches current index with min index
				temp = nums[minIndex];
				nums[minIndex] = nums[i];
				nums[i] = temp;
				Thread.sleep(500);
				repaint();
			} //End outer loop
			
			sortDone = true;	//Sort finished bool
			repaint();			//Repaints final histogram to screen
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//end run method

	//Swing method that allows rectangles to be drawn on panel
		protected void paintComponent(Graphics g) {

			//Calls paintComponent method from component parent class
			super.paintComponent(g);

			g.drawString(name, 125, 50);	//Draws Sort Name
			g.translate(0, 400);			//moves origin to bottom left of panel
			((Graphics2D) g).scale(1,-1);	//flips y axis so y values go up instead of down

			//Prints entire histogram using rectangles based off of nums array
			for(int i = 0; i < nums.length; i++) {
				//makes last index rect black when finished
				if(i == lastIndex && sortDone == true) {	
					g.fillRect(10 + (5 * i), 50, 5, nums[i] * 5);
					g.drawRect(10 + (5 * i), 50, 5, nums[i] * 5);
				} else {
					g.drawRect(10 + (5 * i), 50, 5, nums[i] * 5);
				}
			}
		}//End paintComponent method
	
}//end selectionSort class

class bubbleSort extends JPanel implements Runnable{
	
	//Variables
	private String name;
	private int[] nums;
	private int lastIndex;
	private boolean sortDone = false;

	public bubbleSort(String name, int[] sortNums1) {
		this.name = name;
		this.nums = sortNums1;
		
		//Creates thread in constructor
		Thread t3 = new Thread(this);
		t3.start();
	}

	public void run() {
		try { 
			
			//Bubble Sort Algorithm
			for(int i = 0; i < nums.length; i++) {
				
				for(int j = 0; j < nums.length - 1; j++) {
					if(nums[j] > nums[j+1]) {
						//Switches next index with current index if current index is larger
						int temp = nums[j+1];
						nums[j+1] = nums[j];
						nums[j] = temp;

						lastIndex = j;	//Finds last index
					}
				} //End inner loop
				Thread.sleep(500);
				repaint();
			} //End outer loop
			
			sortDone = true;	//Sort finished bool
			repaint();			//Repaints final histogram to screen
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//end run method

	//Swing method that allows rectangles to be drawn on panel
	protected void paintComponent(Graphics g) {

		//Calls paintComponent method from component parent class
		super.paintComponent(g);

		g.drawString(name, 125, 50);	//Draws Sort Name
		g.translate(0, 400);			//moves origin to bottom left of panel
		((Graphics2D) g).scale(1,-1);	//flips y axis so y values go up instead of down

		//Prints entire histogram using rectangles based off of nums array
		for(int i = 0; i < nums.length; i++) {
			//makes last index rect black when finished
			if(i == lastIndex && sortDone == true) {	
				g.fillRect(10 + (5 * i), 50, 5, nums[i] * 5);
				g.drawRect(10 + (5 * i), 50, 5, nums[i] * 5);
			} else {
				g.drawRect(10 + (5 * i), 50, 5, nums[i] * 5);
			}
		}
	}//End paintComponent method

}//End bubbleSort Class



