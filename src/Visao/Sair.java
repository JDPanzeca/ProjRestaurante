
package Visao;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sair extends java.lang.Thread implements ActionListener{   
private  JPanel painel;
    public void actionPerformed(ActionEvent arg0) {
        Thread s= new Thread(){
            public void run(){
                    try{
                    JFrame janela= new JFrame();

                    janela.setSize(300,100);
                    painel=new JPanel(new BorderLayout());
                    JLabel label= new JLabel("     Fechando o programa...");
                    Font f =new Font("SansSerif",Font.BOLD,20);
                    label.setFont(f);
                    //label.setFont("Dialog",Font.PLAIN,14);
                    painel.add(label, BorderLayout.CENTER);
                    Container contentor=janela.getContentPane();
                    contentor.add(painel);
                    janela.setVisible(true);
                    janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    janela.setLocationRelativeTo(null);
                    sleep(3000);
                    }catch(InterruptedException ex){
                            Logger.getLogger(Sair.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    painel.setBackground(Color.blue);
                    System.exit(0);
            }
        };
        s.start();

    }
}

