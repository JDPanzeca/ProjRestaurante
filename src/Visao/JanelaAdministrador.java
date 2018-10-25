package Visao;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class JanelaAdministrador extends JFrame implements ActionListener {
    //Atributos Swing a serem usados para modelar a pagina Admin
    private JPanel panelMostrar;		
    private final Basic base;
    private final JButton bClient,bFornec,bTrab,bStock,bVend,bAjuda,bSair,bEstati,bAvaliarStock,bAddProd,bBotao2;
    private final JLabel lObjectv;

    public JanelaAdministrador(){
        //Definindo o nome da classe sendo esta uma extensao de JFrame...
        super ("Administrador");
        base=new Basic();            
        // Inicializando componentes***
        //===Label===
        lObjectv=new JLabel("Qualidade nas vendas");
        lObjectv.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icon.png")));
        //===Botoes===
        bClient=new JButton("Clientes");
        bFornec=new JButton("Fornecedores");
        bTrab=new JButton("Trabalhadores");
        bStock=new JButton("Stock");
        bVend=new JButton("Vendas");
        bAjuda=new JButton("Ajuda");
        bSair=new JButton("Sair");
        bEstati=new JButton("Estatistica");
        bAvaliarStock=new JButton("Avaliar");
        bAddProd=new JButton("Add-Produto");
        bBotao2=new JButton("Botao 2");  

        //Definindo imagens para componentes[botoes]
        bVend.setIcon(new javax.swing.ImageIcon(getClass().getResource("Venda.png")));
        bClient.setIcon(new javax.swing.ImageIcon(getClass().getResource("Cliente.png")));
        bFornec.setIcon(new javax.swing.ImageIcon(getClass().getResource("forneced.png")));
        bTrab.setIcon(new javax.swing.ImageIcon(getClass().getResource("Trab.png")));
        bStock.setIcon(new javax.swing.ImageIcon(getClass().getResource("Stock.png")));
        bAjuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("Ajuda.png")));
        bSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("Sair.png")));
        //Fim da inicializacao

    }       

    public   void administrador(){
        //Setando a dimensao da janela ao abrir...
        this.setExtendedState(MAXIMIZED_BOTH);

        //Componentes com accoes-Botoes
        bAjuda.addActionListener(this);
        bClient.addActionListener(this);
        bFornec.addActionListener(this);
        Sair sss=new Sair();/*Rever a thread*/
        bSair.addActionListener(sss);
        bStock.addActionListener(this);
        bTrab.addActionListener(this);
        bVend.addActionListener(this);
        //Botoes de menus secundarios
        bAddProd.addActionListener(this);

        //Painel de cima com botoes do Menu principais
        JPanel panel1;
        panel1=new JPanel(new GridBagLayout());
        panel1.setOpaque(false); 
        GridBagConstraints gdF=new GridBagConstraints();
        gdF.insets=new Insets(0, 10, 0, 10);
        gdF.anchor=GridBagConstraints.NORTH;           
        panel1.add(bFornec,gdF);

        GridBagConstraints gdC=new GridBagConstraints();
        gdC.insets=new Insets(0, 10, 0, 10);           
        panel1.add(bClient,gdC);

        GridBagConstraints gdT=new GridBagConstraints();
        gdT.insets=new Insets(0, 10, 0, 10);            
        panel1.add(bTrab,gdT);

        GridBagConstraints gdS=new GridBagConstraints();
        gdS.insets=new Insets(0, 10, 0, 10);           
        panel1.add(bStock,gdS);

        GridBagConstraints gdV=new GridBagConstraints();
        gdV.insets=new Insets(0, 10, 0, 10);            
        panel1.add(bVend,gdV);

        //Painel Sub Menus esquerdos
        JPanel panel2;           
        panel2=new JPanel(new GridBagLayout());
        panel2.setOpaque(false);

        GridBagConstraints gdE=new GridBagConstraints();
        gdE.gridx=0;
        gdE.gridy=0;
        gdE.ipadx=75;
        gdE.ipady=10;
        gdE.insets=new Insets(10, 10, 10, 0);
        panel2.add(bEstati,gdE);

        GridBagConstraints gdAS=new GridBagConstraints();
        gdAS.gridx=0;
        gdAS.gridy=1;
        gdAS.ipadx=95;
        gdAS.ipady=10;
        gdAS.insets=new Insets(10, 10, 10, 0);
        panel2.add(bAvaliarStock,gdAS);

        GridBagConstraints gd1=new GridBagConstraints();
        gd1.gridx=0;
        gd1.gridy=2;
        gd1.ipadx=60;
        gd1.ipady=10;
        gd1.insets=new Insets(10, 10, 10, 0);
        panel2.add(bAddProd,gd1);

        GridBagConstraints gd2=new GridBagConstraints();
        gd2.gridx=0;
        gd2.gridy=3;
        gd2.ipadx=90;
        gd2.ipady=10;
        gd2.insets=new Insets(10, 10, 10, 0);
        panel2.add(bBotao2,gd2);           


        //===Ajustado a tela=== Trabalhando com os paneis auxiliares acima
        base.setLayout(new GridBagLayout());
        //Logotipo
        GridBagConstraints gbcLogo=new GridBagConstraints(); 
        gbcLogo.gridx=0;
        gbcLogo.gridy=0;
        gbcLogo.weightx=1;
        gbcLogo.weighty=1;
        gbcLogo.insets=new Insets(10, 10, 0, 10);
        gbcLogo.anchor=GridBagConstraints.NORTHWEST;
        base.add(lObjectv,gbcLogo);

        //Menu Principal
        GridBagConstraints gbcCima=new GridBagConstraints(); 
        gbcCima.gridx=0;
        gbcCima.gridy=1;
        gbcCima.weightx=1;
        gbcCima.weighty=1;
        gbcCima.gridwidth=3;
        gbcCima.anchor=GridBagConstraints.NORTH;
        base.add(panel1,gbcCima);

        //Centro
        GridBagConstraints gbcMeio=new GridBagConstraints(); 
        gbcMeio.gridx=0;
        gbcMeio.gridy=2;
        gbcMeio.weightx=1;
        gbcMeio.weighty=1;
        gbcMeio.anchor=GridBagConstraints.WEST;
        base.add(panel2,gbcMeio);

        //Painel Mostrador[Este e um painel dinamico mostrador no admin]
        panelMostrar=new JPanel();  
        panelMostrar.setOpaque(false);
        GridBagConstraints gdMos=new GridBagConstraints();
        gdMos.gridx=1;
        gdMos.gridy=2;
        gdMos.insets=new Insets(0, 0, 0, 285);            
        gdMos.ipadx=800;
        gdMos.ipady=460;
        gdMos.anchor=GridBagConstraints.CENTER;
        base.add(panelMostrar,gdMos);

        //Ferramenta de Ajuda
        GridBagConstraints gbcOpc=new GridBagConstraints(); 
        gbcOpc.gridx=0;
        gbcOpc.gridy=3;
        gbcOpc.weightx=1;
        gbcOpc.weighty=1;
        gbcOpc.insets=new Insets(10, 10, 10, 10);
        gbcOpc.anchor=GridBagConstraints.SOUTHWEST;
        base.add(bAjuda,gbcOpc);

        //LogOut
        GridBagConstraints gbcSair=new GridBagConstraints(); 
        gbcSair.gridx=1;
        gbcSair.gridy=3;
        gbcSair.weightx=1;
        gbcSair.weighty=1;
        gbcSair.insets=new Insets(10, 10, 10, 10);
        gbcSair.anchor=GridBagConstraints.SOUTHEAST;
        base.add(bSair,gbcSair);   

        this.add(base);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(300, 300);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public void visualizarAdmin(){
        administrador();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Clientes cl;
        FornecedorTab fr;
        Trabalhador tr;
        StockTab st;
        Vender vd;
        CadastrarProduto cp;
        JTextField t;
        Vender v=new Vender();
        
        if(e.getSource()==bFornec){
            fr=new FornecedorTab();
            panelMostrar.removeAll();
            panelMostrar.add(fr.getpGeral());
            panelMostrar.validate();
            
        }
        else 
            if(e.getSource()==bClient){               
                cl=new Clientes();
                panelMostrar.removeAll();
                cl.listarClientes();
                panelMostrar.add(cl.getpGeral());
                panelMostrar.validate();
            }
            else 
                if(e.getSource()==bTrab){
                    tr=new Trabalhador();  
                    panelMostrar.removeAll();
                    panelMostrar.add(tr.getpGeral());
                    panelMostrar.validate();
                }
                else 
                   if(e.getSource()==bStock){
                    st=new StockTab();  
                    panelMostrar.removeAll();
                    panelMostrar.add(st.getpGeral());
                    panelMostrar.validate();
                }
                else
                if(e.getSource()==bVend){
                    vd=new Vender();
                    vd.exibirJanela();
                }
                    else
                       if(e.getSource()==bAjuda)
                           JOptionPane.showMessageDialog(null,"Ainda por implemetar");
                        else
                           if(e.getSource()==bSair);
        if(e.getSource()==bAddProd){
            cp=new CadastrarProduto();            
        }
            
        
    }

}
