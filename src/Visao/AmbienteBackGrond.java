
package Visao;

import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class AmbienteBackGrond extends JPanel {
    private Image image;     
    public AmbienteBackGrond(){
         try{
            image=ImageIO.read(getClass().getResource("amb1.jpg"));
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
