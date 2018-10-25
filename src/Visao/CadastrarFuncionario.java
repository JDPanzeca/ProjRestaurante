package Visao;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import controller.Funcionario;
import model.FuncionarioDAO;


public class CadastrarFuncionario extends JFrame implements ActionListener{
    //atributos swing e outros a serem usados na construcao da janela Funcionario
    private final JTextField tNome,tBI,tNUIT,tDataNas,tEnderec,tContact;
    private final JButton bCadastrar,bApagar;
    private final JComboBox <String> cCategoria;
    private final JLabel lNome,lBI,lNUIT,lDataNas,lCategoria,lSexo,lEnderec,lContact;    
    private final JPanel plCadastro,plAux,pane;
    private final JRadioButton rMasc,rFemen;
    private SimpleDateFormat sdf;
    private Date data;    
	
    public CadastrarFuncionario(){
        //Inicializando o nome da frame
        super("Cadastro do Funcionario");
        //inicializando as caixas de textos
        tNome=new JTextField(20);        
        tBI=new JTextField(15);
        tNUIT=new JTextField(15);
        tDataNas=new JTextField(15);       
        tEnderec=new JTextField(15);
        tContact=new JTextField(12);
        //inicializando o radio button
        rFemen=new JRadioButton("Femenino",false);
        rMasc=new JRadioButton("Masculino",false);
        rFemen.setOpaque(false);
        rMasc.setOpaque(false);
        //inicializando os labels
        lSexo=new JLabel("Sexo:");
        lEnderec=new JLabel("Endereco:");
        lContact=new JLabel("Contacto:");
        lNome=new JLabel("Nome:");        
        lBI=new JLabel("BI:");
        lNUIT=new JLabel("NUIT:");
        lDataNas=new JLabel("Data de nascimento:");        
        lCategoria=new JLabel("Categoria");
        //inicializando os botoes
        bCadastrar=new JButton("Cadastrar");
        bApagar=new JButton("Apagar");       
        //inicializando os comboBox
        cCategoria=new JComboBox();
        cCategoria.addItem("Caixa");        
        cCategoria.addItem("Supervisor");
        cCategoria.addItem("Gerente");
        //Inicializando os paineis
        plCadastro=new JPanel(new GridLayout(4,4)); 
        plCadastro.setOpaque(false);
        plAux=new JPanel();        
        pane=new JPanel();
    }        
    public void cadastroFunc(){      
        //definindo a estrutura da janela aplicando os componentes        
        plCadastro.add(lNome);
        plCadastro.add(tNome);     
        plCadastro.add(lBI);
        plCadastro.add(tBI);
        plCadastro.add(lNUIT);
        plCadastro.add(tNUIT);
        plCadastro.add(lDataNas);
        plCadastro.add(tDataNas);
        plCadastro.add(lCategoria);
        plCadastro.add(cCategoria);
        plCadastro.add(lContact);
        plCadastro.add(tContact);
        plCadastro.add(lEnderec);
        plCadastro.add(tEnderec);
        
        pane.add(lSexo);
        pane.add(rMasc);
        pane.add(rFemen);
        
        plCadastro.setBorder(BorderFactory.createTitledBorder("Dados do Funcionario"));
        
        plAux.add(bCadastrar);
        plAux.add(bApagar);        
        //alocando os paineis na frame
        this.add(plCadastro,BorderLayout.NORTH);
        this.add(pane, BorderLayout.CENTER);
        this.add(plAux, BorderLayout.SOUTH); 
        //accoes do componentes da janela
        cCategoria.addActionListener(this);
        bCadastrar.addActionListener(this);
        bApagar.addActionListener(this);
        rFemen.addActionListener(this);
        rMasc.addActionListener(this);
        //comportamento da janela
        this.setSize(600, 250);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        
        /*Verificar as instrucoes de date em sql e java*/
        String dataNas;
        java.sql.Date sqlDate = null; 
       
        int catI,contact,nrNuit;             
        String categ,s="";
        int result;
        
        if(e.getSource()==bCadastrar){
            result=JOptionPane.showConfirmDialog(null,"Desejas continuar com cadastro?","Confirmar",JOptionPane.YES_NO_CANCEL_OPTION);
            if(result==JOptionPane.YES_OPTION){
                catI=cCategoria.getSelectedIndex();
                switch (catI) {
                    case 0:
                        categ="Caixa";
                        break;
                    case 1:
                        categ="Supervisor";                    
                        break;
                    default:
                        categ="Gerente";
                        break;
                }                    
                if(rMasc.isSelected())
                    s="Masculino";
                if(rFemen.isSelected())
                    s="Femenino";           
                dataNas=tDataNas.getText();
                
                sdf=new SimpleDateFormat("dd/MM/yyyy");
                try {
                    data=sdf.parse(dataNas);
                    sqlDate=new java.sql.Date(data.getTime());
                    
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
               
                if(tNome.getText().isEmpty()||tNUIT.getText().isEmpty()||s.isEmpty()||tEnderec.getText().isEmpty()||
                        tContact.getText().isEmpty()||categ.isEmpty()||tDataNas.getText().isEmpty()||tBI.getText().isEmpty())
                        JOptionPane.showMessageDialog(null, "Nao pode deixar campos vazios");

                else{
                    this.dispose();
                    contact=Integer.parseInt(tContact.getText());
                    nrNuit=Integer.parseInt(tNUIT.getText());
                    Funcionario func=new Funcionario(tNome.getText(), tBI.getText(), tEnderec.getText(), s, categ, contact, nrNuit, sqlDate);
                    FuncionarioDAO fD=new FuncionarioDAO();
                    fD.inserirFunc(func);                           
                    JOptionPane.showMessageDialog(null, "Cadastro Feito com sucesso.Obrigado!");                        
                }
            }
            else if(result==JOptionPane.NO_OPTION)
                    System.exit(0);
            }	
            if(e.getSource()==bApagar){                
                tNome.setText("");
                tBI.setText("");
                tDataNas.setText("");
                tNUIT.setText("");
                tContact.setText("");                
                tEnderec.setText("");                

            }
    }
	
	

}
