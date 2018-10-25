package Visao;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class JanelaInicial extends JFrame implements ActionListener {
    //Atributos Swing a serem usados para modelar a pagina inicial
    private final JanelaEvent eventW ;
    private final Basic base; 
    private final JLabel user,pss,lObjectv;
    private final JTextField tUser;
    private final JPasswordField tPss;
    private final JButton bEntrar,bApagar;
    private final JPanel panelAut_Label_Text,panelAut_button,panelAgreg;  
        
    public JanelaInicial(){
        //Definindo o nome da classe sendo esta uma extensao de JFrame...
        super("Sign In");        
        //Instancia da classe JPanel com a foto do fundo... este ee o repositorio de todos os componentes...
        base=new Basic();
        base.setLayout(new BorderLayout());  
        //Inicializando Labels...
        user=new JLabel("Usuário:");
        pss=new JLabel("Senha:");
        lObjectv=new JLabel("O nosso maior objectivo é garantir qualidade nas vendas");
        lObjectv.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icon.png")));
        //Inicializando caixas de textos[JTextfields]...
        tUser=new JTextField(15);
        tPss=new JPasswordField(15);
        //Inicializando os botoes
        bEntrar=new JButton("Entrar");
        bApagar=new JButton("Apagar");
        //Inicializando Paineis...
        panelAgreg=new JPanel(new GridBagLayout());
        panelAut_Label_Text=new JPanel();
        panelAut_Label_Text.setOpaque(false); 
        panelAut_button=new JPanel();
        //Instancia do comportamento da janela quando for clicado o maximize ou minimize ou exit.
        eventW=new JanelaEvent();
               
    }
    //O metodo que constroi a janela inicial******
    public void contruirJanela(){
        //Setando a dimensao da janela ao abrir...
        this.setExtendedState(MAXIMIZED_BOTH);
         //Add componentes ao Painel com os labels e text fields         
        panelAut_Label_Text.add(user);
        panelAut_Label_Text.add(tUser);      
        panelAut_Label_Text.add(pss);
        panelAut_Label_Text.add(tPss);
        //Painel com os botoes de auteticacao         
        panelAut_button.setOpaque(false);
        panelAut_button.add(bEntrar);
        panelAut_button.add(bApagar);
        
        //Definindo o comportamento do painel com label e text fields atraves do GridBagConstracts para o painel base
        GridBagConstraints gbcPI=new GridBagConstraints();
        gbcPI.gridx=1;
        gbcPI.gridy=1;
        panelAgreg.add(panelAut_Label_Text,gbcPI);
        //Definindo o comportamento do painel com botoes atraves do GridBagConstracts para o painel base
        GridBagConstraints gbcAux=new GridBagConstraints();
        gbcAux.gridx=1;
        gbcAux.gridy=2;
        panelAgreg.add(panelAut_button,gbcAux);
        panelAgreg.setOpaque(false);
        
        //Inserindo componentes no painel base da pagina
        base.add(lObjectv,BorderLayout.NORTH);
        base.add(panelAgreg,BorderLayout.CENTER);
        
        //adicionando o painel base ao frame
        this.add(base);
        //Definindo escuta dos eventos aos botoes para os cliques.
        bEntrar.addActionListener(this);
        bApagar.addActionListener(this);   
        
        //Accoes do teclado para a textfield do password        
        tPss.addKeyListener(new java.awt.event.KeyAdapter(){
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt){
              
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    auteticar();
                }
            }
        });
        //fim da KeyListener
        //Definindo comportamento da janela
        this.addWindowListener(eventW);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
        this.setLocationRelativeTo(null);
    }
    //fim do metodo que constroi a janela inicial******
    
    //Subescrevendo o metodo actionPerformed para a accao dos botoes******
    @Override
    public void actionPerformed(ActionEvent e) {    

    	if(e.getSource()==bEntrar){
    		auteticar();
    	}
    	if(e.getSource()==bApagar){
    		tUser.setText(" ");
    		tPss.setText("");    		
    	}
    }
    //Fim do metodo *******
    //Metodo que verifica a autenticacao******
    public void auteticar(){
        String usr;
        String cd;
        char[] cdg;
        JanelaFuncionario f;
        JanelaAdministrador a;
        usr=tUser.getText();
    	cdg=tPss.getPassword();
    	cd=new String(cdg);
    		
        if(usr.equalsIgnoreCase("1")&&cd.equals("1")){
            this.dispose();
            a=new JanelaAdministrador();
            a.visualizarAdmin();
            a.addWindowListener(eventW);
            }
    		
        else{
            this.dispose();
            f=new JanelaFuncionario();
            f.addWindowListener(eventW);        
        }
    }
    //Fim do metodo de autenticacao******
}
