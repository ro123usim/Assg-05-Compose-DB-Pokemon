package Part_5_Motion_Sound;

//import java.util.Timer;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * 
 * Motion and Sound:
 *   Motion of of many dots with collision sound!
 *
 *Try:
 *  1) Run the program
 *  2) theCat -> line 135 ... Print name above the "cat"
 *  3) DrawHere -> lines 29 to 32 ... add in more "cats" 
 *  4) check DrawHere -> line 123  ...make sound play when collision happens
 *  5) DrawHere -> line 82 to 89 ...draw other stuff on the screen
 *  6) Change the cat from a circle to something else theCat -> line 133
 *  7) ...even more cats?
 *
 */
public class Main_A5 {

	// A timer to refresh the screen
	static Timer timer;
	static DrawHere d = new DrawHere();

	public static void main(String[] args) {
		System.out.println("A");

		// Set up jFrame window for drawing
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(650, 750);
		frame.setVisible(true);
		frame.setContentPane(d);

		frame.getRootPane().setBackground(Color.LIGHT_GRAY);

		System.out.println("B");

		// Set up Timer
		timer = new Timer(5, d); // Set time, and this object gets event
		timer.setInitialDelay(100); //
		timer.setCoalesce(true); // Don't stack up events
		timer.start(); // Start the timer object

	}

}