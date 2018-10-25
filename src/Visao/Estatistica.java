package Visao;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Estatistica extends JFrame{
    JLabel lValorFo,lValorFu,lTotal,lLucro;
    JTextField tFo,tFu,tTot,tLucro;
    JPanel panel;
	
	public Estatistica(){
            //Inicializando o nome da frame
            super("Estatisticas");
            
            lValorFo=new JLabel("Fornecedores:");
            lValorFu=new JLabel("Funcionarios:");
            lTotal=new JLabel("Total:");
            lLucro=new JLabel("Lucro: ");

            tFo=new JTextField(5);
            tFo.setEditable(false);

            tFu=new JTextField(5);
            tFu.setEditable(false);

            tTot=new JTextField(5);
            tTot.setEditable(false);

            tLucro=new JTextField(5);
            tLucro.setEditable(false);
            
            panel=new JPanel(new GridLayout(4,2));
            
	}
        public void vizualizarEstatistic(){
            estatistica();
        }
        public void estatistica(){           
            panel.add(lValorFo);
            panel.add(tFo);
            panel.add(lValorFu);
            panel.add(tFu);
            panel.add(lTotal);
            panel.add(tTot);
            panel.add(lLucro);
            panel.add(tLucro);

            add(panel);

            setSize(300,150);
            setLocationRelativeTo(null);
            setVisible(true);


        }

}
