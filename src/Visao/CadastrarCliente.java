package Visao;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.Cliente;
import model.ClienteDAO;

public class CadastrarCliente extends JFrame implements ActionListener {
	
    private final JTextField tNomeP,tNomeU,tNacionalidade,tNUIT,tEnderec,tContact;
    private final JPasswordField tSenha, tReSenha;
    private final JButton bCadastrar,bApagar;    
    private final JLabel lNomeP,lNomeU,lNacionalidade,lNUIT,lSexo,lEnderec,lContact,lSenha,lReSenha;    
    private final JPanel plCadastro,plAux,plCadastro1;
    private final JRadioButton rMasc,rFemen;
    private final JMenuBar barra;
    private final JMenu menu;
    private final JMenuItem item;
    private final ButtonGroup genero;
    boolean up=false;
    int id=-1;
    
    public CadastrarCliente(String titulo){
        //Definindo o titulo da janela
        super(titulo);
        //Inicializando od componentes do Menu da janela
        barra=new JMenuBar();
        menu=new JMenu("Actualizar");
        item=new JMenuItem("Actulizar Cliente");        
        //Inicializando as caixas de textos
        tNomeP=new JTextField(20);
        tNomeU=new JTextField(20); 
        tNacionalidade=new JTextField(20);
        tNUIT=new JTextField(15);        
        tEnderec=new JTextField(20);
        tContact=new JTextField(15);
        tSenha=new JPasswordField(15);
        tReSenha=new JPasswordField(15);
        //Inicializando os RadioButtons
        rFemen=new JRadioButton("Femenino",false);
        rMasc=new JRadioButton("Masculino",false);
        genero=new ButtonGroup();
        genero.add(rMasc);
        genero.add(rFemen);
        rFemen.setOpaque(false);
        rMasc.setOpaque(false);
        //Inicializando os Labels
        lSexo=new JLabel("Sexo:");
        lEnderec=new JLabel("Endereco:");
        lContact=new JLabel("Contacto:");
        lNomeP=new JLabel("Nome:");
        lNomeU=new JLabel("Apelido:");
        lNacionalidade=new JLabel("Nacionalidade:");
        lNUIT=new JLabel("NUIT:");
        lSenha=new JLabel("Senha:");
        lReSenha=new JLabel("Re-Senha:");
        //Inicializando os Buttons
        bCadastrar=new JButton("Confirmar");
        bApagar=new JButton("Apagar");
        //Inicializando os Paineis
        plCadastro1=new JPanel();
        plCadastro1.setOpaque(false);
        plCadastro=new JPanel();
        plCadastro.setLayout(new GridBagLayout());
        plCadastro.setOpaque(false);
        plAux=new JPanel();
        plAux.setOpaque(false);
    }
    public void cadastroClient(){                                                                                               
        //Definindo o menu de cima
        this.setJMenuBar(barra); 
        menu.add(item);
        barra.add(menu);
        //Definindo o corpo do Frame
        GridBagConstraints gbc1=new GridBagConstraints();
        gbc1.gridx=0;
        gbc1.gridy=0;
        gbc1.insets=new Insets(5,0,5,0);
        plCadastro.add(lNomeP,gbc1);
        
        GridBagConstraints gbc2=new GridBagConstraints();
        gbc2.gridx=1;
        gbc2.gridy=0;
        gbc2.gridwidth=2; 
        gbc2.insets=new Insets(5,0,5,0);
        //gbc2.gridwidth=1;
        plCadastro.add(tNomeP,gbc2);
        
        GridBagConstraints gbc3aux=new GridBagConstraints();
        gbc3aux.gridx=2;
        gbc3aux.gridy=0;
        gbc3aux.insets=new Insets(5,0,5,0);
        //gbc3.gridwidth=2;
        //plCadastro.add(lNomeP,gbc3aux);
        
        GridBagConstraints gbc3=new GridBagConstraints();
        gbc3.gridx=3;
        gbc3.gridy=0;
        gbc3.insets=new Insets(5,0,5,0);
        //gbc3.gridwidth=2;
        plCadastro.add(lNomeU,gbc3);
        
        GridBagConstraints gbc4=new GridBagConstraints();
        gbc4.gridx=4;
        gbc4.gridy=0;
        gbc4.gridwidth=2;
        gbc4.insets=new Insets(5,0,5,0);
        plCadastro.add(tNomeU,gbc4);
        
        GridBagConstraints gbc5=new GridBagConstraints();
        gbc5.gridx=0;
        gbc5.gridy=1;
        gbc5.insets=new Insets(5,51,5,5);
        plCadastro.add(lNacionalidade,gbc5);
        
        GridBagConstraints gbc6=new GridBagConstraints();
        gbc6.gridx=1;
        gbc6.gridy=1;
        gbc6.gridwidth=2;
        gbc6.insets=new Insets(5,0,5,0);
        plCadastro.add(tNacionalidade,gbc6);
        
        /*GridBagConstraints gbc7=new GridBagConstraints();
        gbc7.gridx=2;
        gbc7.gridy=1;
        plCadastro.add(tNacionalidade,gbc7);*/
        
        GridBagConstraints gbc8=new GridBagConstraints();
        gbc8.gridx=0;
        gbc8.gridy=2;
        gbc8.insets=new Insets(5,23,5,0);
        plCadastro.add(lEnderec,gbc8);
        
        GridBagConstraints gbc9=new GridBagConstraints();
        gbc9.gridx=1;
        gbc9.gridy=2;
        gbc9.gridwidth=2;
        gbc9.insets=new Insets(5,0,5,0);
        plCadastro.add(tEnderec,gbc9);
        
        GridBagConstraints gbc10=new GridBagConstraints();
        gbc10.gridx=0;
        gbc10.gridy=3;
        gbc10.insets=new Insets(5,0,5,0);
        plCadastro.add(lSexo,gbc10);
        
        GridBagConstraints gbc11=new GridBagConstraints();
        gbc11.gridx=1;
        gbc11.gridy=3;
        gbc11.insets=new Insets(5,0,5,0);
        plCadastro.add(rMasc,gbc11);
        
        GridBagConstraints gbc12=new GridBagConstraints();
        gbc12.gridx=2;
        gbc12.gridy=3;
        gbc12.insets=new Insets(5,0,5,0);
        plCadastro.add(rFemen,gbc12);
        
        /*GridBagConstraints gbc12aux=new GridBagConstraints();
        gbc12aux.gridx=3;
        gbc12aux.gridy=3;
        plCadastro.add(rFemen,gbc12aux); */
        
        GridBagConstraints gbc13=new GridBagConstraints();
        gbc13.gridx=0;
        gbc13.gridy=4;
        gbc13.insets=new Insets(5,0,5,0);
        plCadastro.add(lNUIT,gbc13);
        
        GridBagConstraints gbc14=new GridBagConstraints();
        gbc14.gridx=1;
        gbc14.gridy=4;
        gbc14.gridwidth=2;
        gbc14.insets=new Insets(5,0,5,0);
        plCadastro.add(tNUIT,gbc14);
        
        GridBagConstraints gbc15=new GridBagConstraints();
        gbc15.gridx=0;
        gbc15.gridy=5;
        gbc15.insets=new Insets(5,23,5,0);
        plCadastro.add(lContact,gbc15);
        
        GridBagConstraints gbc16=new GridBagConstraints();
        gbc16.gridx=1;
        gbc16.gridy=5;
        gbc16.gridwidth=2;
        gbc16.insets=new Insets(5,0,5,0);
        plCadastro.add(tContact,gbc16);
        
        GridBagConstraints gbc17=new GridBagConstraints();
        gbc17.gridx=0;
        gbc17.gridy=6;
        gbc17.insets=new Insets(5,8,5,0);
        plCadastro.add(lSenha,gbc17);
        
        GridBagConstraints gbc18=new GridBagConstraints();
        gbc18.gridx=1;
        gbc18.gridy=6;
        gbc18.gridwidth=2;
        gbc18.insets=new Insets(5,0,5,0);
        plCadastro.add(tSenha,gbc18);
        
        GridBagConstraints gbc21=new GridBagConstraints();
        gbc21.gridx=2;
        gbc21.gridy=6;
        gbc21.insets=new Insets(5,0,5,0);
        //gbc3.gridwidth=2;
        //plCadastro.add(lNomeU,gbc3);
        
        GridBagConstraints gbc19=new GridBagConstraints();
        gbc19.gridx=3;
        gbc19.gridy=6;
        gbc19.insets=new Insets(5,0,5,0);
        //gbc19.gridwidth=2;
        plCadastro.add(lReSenha,gbc19);
        
        GridBagConstraints gbc20=new GridBagConstraints();
        gbc20.gridx=4;
        gbc20.gridy=6;
        gbc20.gridwidth=2;
        gbc20.insets=new Insets(5,0,5,0);
        plCadastro.add(tReSenha,gbc20);
        /*plCadastro.add(lNome);
        plCadastro.add(tNome);        
        plCadastro.add(lNUIT);
        plCadastro.add(tNUIT);        
        plCadastro.add(lEnderec);
        plCadastro.add(tEnderec);
        plCadastro.add(lContact);
        plCadastro.add(tContact);*/     
        
        plCadastro.setBorder(BorderFactory.createTitledBorder("Dados do cliente"));       
        
       /* plCadastro1.add(lSexo);
        plCadastro1.add(rMasc);
        plCadastro1.add(rFemen);*/
   
        plAux.add(bCadastrar);
        plAux.add(bApagar);        

        this.add(plCadastro,BorderLayout.NORTH);
        this.add(plCadastro1,BorderLayout.CENTER);
        this.add(plAux, BorderLayout.SOUTH); 
        
        item.addActionListener(this);
        rFemen.addActionListener(this);
        rMasc.addActionListener(this);
        bCadastrar.addActionListener(this);
        bApagar.addActionListener(this); 

        this.setSize(700, 350);
        //this.set
        this.setResizable(false);       
        setLocationRelativeTo(null);
        setVisible(true);
        
    } 
    
    @Override
    public void actionPerformed(ActionEvent e){
        /*Para modificar dados do cliente e necessario usar o ID para buscar dados da base de dados e setar
        nos campos de preenchimento de dados... apois isso agrupas os radios buttons*/
        int contact,nrNuit;              
        String s="",perg,senha,reSenha;
        int result;
        char[] sen, reSen;
        boolean generoSelect=false;
        if(e.getSource()==item){
            id=Integer.parseInt(JOptionPane.showInputDialog("Introduza o codigo"));
            up=true;            
        }
        
        if(e.getSource()==bCadastrar){
            if(up)
                perg="Desejas continuas a actualizacao?";
            else
                perg="Desejas continuar o cadastro?"; 
            result=JOptionPane.showConfirmDialog(null,perg,"Confirmar",JOptionPane.YES_NO_CANCEL_OPTION);
            if(result==JOptionPane.YES_OPTION){
                sen=tSenha.getPassword();
                reSen=tReSenha.getPassword();
                senha= new String(sen);
                reSenha=new String(reSen);
                if(rMasc.isSelected()){
                    s="M";
                    //generoSelect=true;
                }
                if(rFemen.isSelected()){
                    s="F";
                    //generoSelect=true;
                }
               
                if(/*!generoSelect||*/tNomeP.getText().isEmpty()||tNomeU.getText().isEmpty()||
                        tNacionalidade.getText().isEmpty()||
                        tNUIT.getText().isEmpty()||s.isEmpty()||
                        tEnderec.getText().isEmpty()||tContact.getText().isEmpty()||
                        senha.isEmpty()||
                        reSenha.isEmpty())
                        JOptionPane.showMessageDialog(null, "Nao pode deixar campos vazios");

                else{
                    this.dispose();
                    contact=Integer.parseInt(tContact.getText());
                    nrNuit=Integer.parseInt(tNUIT.getText());
                    Cliente cli;
                    ClienteDAO operClient=new ClienteDAO();
                    if(up){
                        cli=new Cliente(tNomeP.getText(),tNomeU.getText(),tNacionalidade.getText(), tEnderec.getText(), s, contact, nrNuit);
                        operClient.actualizar(cli, id);
                        operClient.inserir(cli);
                        JOptionPane.showMessageDialog(null, "Actualizacao feita com sucesso,Obrigado!");
                    }
                    else{
                        cli=new Cliente(tNomeP.getText(),tNomeU.getText(),tNacionalidade.getText(), tEnderec.getText(), s, contact, nrNuit,0);
                        operClient.inserir(cli);
                        JOptionPane.showMessageDialog(null, "Cadastro Feito com sucesso,Obrigado!");
                    }
                                        
               }
            }
            else if(result==JOptionPane.NO_OPTION)
                    System.exit(0);
            }	
            if(e.getSource()==bApagar){                
                tNomeP.setText("");
                tNomeU.setText("");
                tNacionalidade.setText("");
                tNUIT.setText("");
                tContact.setText("");                
                tEnderec.setText(""); 
                tSenha.setText("");
                tReSenha.setText("");
            }
    }
}
