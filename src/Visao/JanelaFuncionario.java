package Visao;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class JanelaFuncionario extends JFrame implements ActionListener /*implements ListSelectionListener*/{
	
	private final JanelaEvent eventW;
	private JPanel panel0,panel1,panelAux;
	private JLabel l1,l2;
        private final JMenuBar barraMenu;
	private JMenu menu,subMenu;
	private JMenuItem item;
        private final Basic base;
        private JButton bVend,bClient,bValDia,bAjuda,bSair;
	
	
	public JanelaFuncionario(){
            super("VENDAS");
            this.setExtendedState(MAXIMIZED_BOTH);
            eventW=new JanelaEvent();
            base=new Basic();
            this.add(base);
            funcionario();
            //menuVendas();
            //menuCliente();

           // menuValorGanho();
            barraMenu=new JMenuBar();
            this.setJMenuBar(barraMenu);
            menu=new JMenu("Ajuda");
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            //barraMenu.add(menu);
	}
        
        public void menuVendas(){
            menu=new JMenu("Efectuar vendas");
            barraMenu.add(menu);
        }
        public void menuCliente(){
            menu=new JMenu("Clientes");                
            barraMenu.add(menu);			
            subMenu=new JMenu("Valor a ser ganho");
            item=new JMenuItem("Total");
            subMenu.add(item);
            subMenu.addSeparator();
            item=new JMenuItem("Inregulares");
            subMenu.add(item);
            subMenu.addSeparator();
            item=new JMenuItem("Regulares");
            subMenu.add(item);		
            
            menu.add(subMenu);
            item=new JMenuItem("Listar Inregulares");
            menu.add(item);
            item=new JMenuItem("Listar Regulares");
            menu.add(item);
            item=new JMenuItem("Tabela Clientes");
            menu.add(item);
        }
        public void menuValorGanho(){
            menu=new JMenu("Valor Ganho");
            barraMenu.add(menu);
        }
	
	 public   void funcionario(){
            JLabel lObjectv=new JLabel("O nosso maior Objectivo garantir qualidade nas vendas");
            lObjectv.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icon.png")));
            
	    
            bSair=new JButton("Sair");
            bVend=new JButton("Vendas");
            bClient=new JButton("Clientes");
            bValDia=new JButton("Valor Ganho");
            bAjuda=new JButton("Ajuda");
            
            bAjuda.addActionListener(this);
            bClient.addActionListener(this);
            Sair s=new Sair();
            bSair.addActionListener(s);
            bValDia.addActionListener(this);
            bVend.addActionListener(this);
            
            bVend.setIcon(new javax.swing.ImageIcon(getClass().getResource("Venda.png")));
            bClient.setIcon(new javax.swing.ImageIcon(getClass().getResource("Cliente.png")));
            bAjuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("Ajuda.png")));
            bSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("Sair.png")));
            bValDia.setIcon(new javax.swing.ImageIcon(getClass().getResource("VenDia.png")));
            
            JPanel panel1=new JPanel();
            panel1.setOpaque(false);
            
            panel1.add(bVend);
            panel1.add(bClient);
            panel1.add(bValDia);
            
            JPanel panel2=new JPanel(/*new BorderLayout()*/);
            panel2.setOpaque(false);
            panel2.add(bAjuda,BorderLayout.EAST);
            panel2.add(bSair,BorderLayout.WEST);
            base.setLayout(new BorderLayout());
            base.add(lObjectv,BorderLayout.NORTH);
            base.add(panel1,BorderLayout.CENTER);
            base.add(panel2,BorderLayout.SOUTH);
	    	
            //JLabel taref=new JLabel("OPERACOES");

            String [] opc={"Efectuar Vendas","Listar clientes","Valor do dia"};
            JList list=new JList(opc);	    	 
            
            //list.addListSelectionListener(this);
            panel0=new JPanel();
            panel1=new JPanel(new BorderLayout());

            //JPanel panel=new JPanel(new GridLayout(2,1));
            //panel0.add(taref);
            panel1.add(list,BorderLayout.SOUTH);

            //add(panel0,BorderLayout.NORTH);
            //add(panelAux,BorderLayout.CENTER);
            //add(panel1,BorderLayout.WEST);


            setSize(300, 300);
            setLocationRelativeTo(null);
            setVisible(true);
	    	
	 }
	 

    @Override
    public void actionPerformed(ActionEvent e) {
        Vender vd;
        Clientes cl;
        
        if(e.getSource()==bClient)
            cl=new Clientes();
        else
            if(e.getSource()==bValDia)
                JOptionPane.showMessageDialog(null, "Ainda por implementar");
            else
                if(e.getSource()==bVend){
                    vd=new Vender();
                    vd.exibirJanela();
                }   
                else
                    if(e.getSource()==bAjuda)
                        JOptionPane.showMessageDialog(null, "Ainda por implementar");
                    else
                        if(e.getSource()==bSair);
                            //System.exit(0);
                        
        
    }

}
