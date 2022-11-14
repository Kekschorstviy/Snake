import javax.swing.JFrame;
import javax.swing.WindowConstants;

// class to set the window with constant settings 

public class Window extends JFrame{
	
	public Window() 
	{
	
		setTitle("Snake"); // game name
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // to close the window  and stop iteration
		add(new Field());
		setSize(500, 345); // window size
		setLocation(400, 400);
		setVisible(true); //visible window

	}
	
	public static void main(String[] args) 
	{
		Window w = new Window();
		
	}
}
