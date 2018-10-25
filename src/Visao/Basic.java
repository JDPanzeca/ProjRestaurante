package Visao;

import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;

public class Basic extends JPanel  {	
        private Image image;      
	
	public Basic(){	
        try{
            image=ImageIO.read(getClass().getResource("inicial0C.jpg"));
        }catch(Exception io){
            System.out.println(io.getMessage());
        }        
	}

     @Override
     public void paintComponent(Graphics g){
        super.paintComponents(g);      
        g.drawImage(image, 0, 0, this);
        
    }   
    
}


