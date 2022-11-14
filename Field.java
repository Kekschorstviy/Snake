import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Field extends JPanel implements ActionListener
{
	
	private static final long serialVersionUID = 1L;
	private final int SIZE = 320;
	private final int S_SIZE = 16; 
	private final int ALL_S = 400;
	
	private int fruitX; //fruit coordinates
	private int fruitY; 
	private int[] x = new int[ALL_S];
	private int[] y = new int[ALL_S];
	private int snakie;
	
	private Timer timer;
	private Image snake;
	private Image fruit;
	
	private boolean left; 
	private boolean up; 
	private boolean down; 
	private boolean Game = true ;
	private boolean right= true ; 
	
	
	class FieldKeyListener extends KeyAdapter{
	       
	        public void keyPressed(KeyEvent e) {
	        	
	            super.keyPressed(e);
	            int key = e.getKeyCode();
	            
	            if(key == KeyEvent.VK_RIGHT && !left)
	            {
	                right = true;
	                up = false;
	                down = false;
	            }
	            
	            if(key == KeyEvent.VK_LEFT && !right)
	            {
	                left = true;
	                up = false;
	                down = false;
	            }
	           

	            if(key == KeyEvent.VK_UP && !down)
	            {
	            	up = true;
	            	right = false;
	               
	                left = false;
	            }
	            if(key == KeyEvent.VK_DOWN && !up)
	            {
	                right = false;
	                down = true;
	                left = false;
	            }
	        }
		
	}
	
	
	public void loadImage () // method to load the  snake pattern and fruit image 
	{
		ImageIcon iia = new ImageIcon("fruit.png");
		fruit = iia.getImage();
		
		ImageIcon iid = new ImageIcon("snake.png");
		snake = iid.getImage();
	}
	
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		if(Game) 
		{
			g.drawImage(fruit, fruitX, fruitY, this);
			for(int i = 0; i < snakie; i++) {
				g.drawImage(snake, x[i], y[i], this);
				
				
				
			}
		}
	}
	
	public void createFruit() 
	{
		
		fruitX = new Random().nextInt(20)*S_SIZE; // 20 because only 20 fruits can be placed on the game field
		fruitY = new Random().nextInt(20)*S_SIZE;
		
	}
	
	public void InGame()
	{
		
		snakie = 3;
		for(int i = 0; i < snakie; i++) 
		{
			x[i] = 48 - i*S_SIZE;
			y[i] = 48;
		}
		timer = new Timer(250, this);
		timer.start();
		createFruit();
	}
	
	public void Moving() 
	{
		for (int i = snakie; i > 0 ; i--) 
		{
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		if (left) 
		{
			x[0] -= S_SIZE;
		}
		
		if (right) 
		{
			x[0] += S_SIZE;
		}
		
		if (down) 
		{
			y[0] += S_SIZE;
		}
		
		if (up) 
		{
			y[0] -= S_SIZE;
		}
	}
	
	public void checkFruit() 
	{
		if (x[0] == fruitX ) 
		{ 
			if(y[0] == fruitY) 
			{
			
				snakie ++;
				createFruit();
		}
			}
			
		}
	
	public void Collision() // method to check if snake met itself or one of the walls 
	{ 
		
		for(int i = snakie; i>0; i--) 
		{
			if (i >5 ) {
				if(x[0] == x[i]) {
					if(y[0] == y[i]) {
						Game = false;
					}
				}
			}
		}
		
		if (x[0] > SIZE) 
		{
			Game = false;
		}
		
		if (y[0] > SIZE) 
		{
			Game = false;
		}
		
		if (x[0] < 0) 
		{
			Game = false;
		}
		
		
		if (y[0] < 0) 
		{
			Game = false;
		}
		
	}
	
	public Field() // constructor to set the background , load images and initialize InGame method
	{
		setBackground(Color.ORANGE);
		loadImage();
		InGame();
		addKeyListener(new FieldKeyListener());
		setFocusable(true);
		
	}

	public void actionPerformed(ActionEvent e)  //method which defines actions performed while timer is running 
	{
		if (Game) 
		{
			Moving();
			checkFruit();
			Collision();
			
		}
		repaint();
	}
	

}
