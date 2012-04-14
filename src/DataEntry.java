	import javax.swing.JFrame;
	
public class DataEntry {
	static JFrame frame;
    static StudentDB students;

	     /* 
	      * Main
	      */   
	 
	    public static void main(String[] args) {
	    	students = new StudentDB(frame);

	        //Schedule a job for the event-dispatching thread:
	        //creating and showing this application's GUI.
	        javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                new NewFrame(frame, students);
	            }
	        });
	    }
}
