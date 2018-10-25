package Visao;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;

public class JanelaEvent extends WindowAdapter {
    
    public void windowClosing(WindowEvent e){
       // JOptionPane.showConfirmDialog(null,"Deseja sair?",JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
       int result;
       Object[] opcoes={"Sim","Nao"};
       result=JOptionPane.showOptionDialog(null, "Deseja Sair?", "Alerta", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opcoes, opcoes[0]);
        
       /*do{
            
            result=JOptionPane.showConfirmDialog(null,"Deseja sair do Sistema?", "Alerta",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    	  
       }while( result !=JOptionPane.YES_OPTION && result!=JOptionPane.NO_OPTION);*/
       if(result==0)
           System.exit(0);
           
       else if(result==1){
       }
      
       
        
           //System.out.println("Nao
           
       //else if(result==JOptionPane.NO_OPTION)
            //System.out.println("");
           //this.dispose();
      // else if(result==JOptionPane.NO_OPTION)
           
        //else
           //this.JOptionPane.ABORT;
    }
    
    /*public void windowOpened(WindowEvent ev){
        JOptionPane.showMessageDialog(null,"Bem Vindo ao nosso mundo!");
    }
    
    public void windowIconified(WindowEvent e){
        
        JOptionPane.showMessageDialog(null,"Janela minimizada!");
    }
    
    public void windowDeiconified(WindowEvent e){
        JOptionPane.showMessageDialog(null,"Janela maximizada!");
    }*/

    private void dispose() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
