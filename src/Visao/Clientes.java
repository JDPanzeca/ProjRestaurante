package Visao;
import controller.Cliente;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.ClienteDAO;


public class Clientes extends JFrame implements ActionListener {
	
	private final JLabel lID, lValor;
	private final JTextField tID_Nome, tValor;
	private final JPanel panelSearch,panelButtonBottom,panelTop,pGeral,panelResul;
	private final JButton bProcurar,bDepositar,bCancelar,bRemove,bAdd,bDeposito;
	private DefaultTableModel modelo;
        private final JRadioButton rID,rNome;
        private final ButtonGroup pesquisar;
        private final JFrame frameDepositar,janelaResul;
        private final JTextArea listaResul;
        private final JScrollPane scrollResul;
     
	public Clientes(){
            //Inicializando o nome da frame
            super("Clientes");
            //Inicializando as labels
            lID=new JLabel("ID:");
            lValor=new JLabel("Valor a depositar:");
            //inicializando os botoes
            bCancelar=new JButton("Apagar");
            bDepositar=new JButton("Depositar");
            bProcurar=new JButton("Procurar");
            bRemove=new JButton("Remover");
            bDeposito=new JButton("Deposito");
            bAdd=new JButton("Adicionar novo");
            bAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("new-icon_1.png")));
            //incializando os radios 
            rID=new JRadioButton("ID",false);
            rNome=new JRadioButton("Nome",false);
            pesquisar=new ButtonGroup();
            pesquisar.add(rID);
            pesquisar.add(rNome);
            //inicializando as caixas de textos
            tID_Nome=new JTextField(15);
            tValor=new JTextField(10);

            //Incializando Paineis
            panelSearch=new JPanel();
            panelButtonBottom=new JPanel(new GridBagLayout());
            panelTop=new JPanel(new GridBagLayout());
            pGeral=new JPanel(new BorderLayout());
            pGeral.setPreferredSize(new Dimension(720,440));
            //Resultado
            janelaResul=new JFrame();
            panelResul=new JPanel();
            listaResul=new JTextArea();
            scrollResul=new JScrollPane(listaResul);
            
            //Accoes nos componentes
            bAdd.addActionListener(this);
            bDeposito.addActionListener(this);
            bProcurar.addActionListener(this);
            //Frame da janela depositar
            frameDepositar=new JFrame();
	}        
       
      
        public void  listarClientes(){
            /*Rever o funcionamento da JTable*/
    	    ClienteDAO d=new ClienteDAO();
            modelo=new DefaultTableModel();
            JTable tabClie=new JTable(modelo);
            modelo.addColumn("ID");
            modelo.addColumn("Nome");
            modelo.addColumn("Apelido");
            modelo.addColumn("Endereco");
            modelo.addColumn("Sexo");
            modelo.addColumn("NUIT");
            modelo.addColumn("Contacto");
            modelo.addColumn("Saldo");
            modelo.addColumn("Nacionalidade");
            tabClie.getColumnModel().getColumn(0).setPreferredWidth(30);
            List <Cliente> cli=d.listarClientes();
            actualizarTabela(cli);
            JScrollPane scroll = new JScrollPane(tabClie);
            JPanel p=new JPanel();
            p.setLayout(null);
            p.setPreferredSize(new Dimension(720, 320));
            p.add(scroll);
            scroll.setBounds(5, 0, 710, 320);
            
            panelSearch.add(rID);
            panelSearch.add(rNome);
            panelSearch.add(tID_Nome);
            
            //Botoes do top
            GridBagConstraints gbcAdd=new GridBagConstraints();
            gbcAdd.gridx=0;
            gbcAdd.gridy=0;
            gbcAdd.insets=new Insets(5, 5, 5,0);
            panelTop.add(bAdd,gbcAdd);
            
            GridBagConstraints gbcDep=new GridBagConstraints();
            gbcDep.gridx=1;
            gbcDep.gridy=0;
            gbcDep.ipadx=30;
            gbcDep.ipady=6;
            gbcDep.anchor=GridBagConstraints.EAST;
            gbcDep.insets=new Insets(5, 5, 5,5);
            panelTop.add(bDeposito,gbcDep);
            
            //Paneil da tabela sendo integrado no painel Top
            GridBagConstraints gbcP=new GridBagConstraints();
            gbcP.gridx=0;
            gbcP.gridy=1;
            gbcP.gridwidth=2;
            //gbcAdd.insets=new Insets(0, 0, 5,0);
            panelTop.add(p,gbcP);
            
            //panelButton.add(bSearch);
            GridBagConstraints gbcSearc=new GridBagConstraints();
            gbcSearc.gridx=0;
            gbcSearc.gridy=0;
            gbcSearc.insets=new Insets(0, 0, 5, 5);
            panelButtonBottom.add(bProcurar,gbcSearc);
            
            GridBagConstraints gbcRemov=new GridBagConstraints();
            gbcRemov.gridx=1;
            gbcRemov.gridy=0;
            gbcRemov.insets=new Insets(0, 0, 5, 5);
            panelButtonBottom.add(bRemove,gbcRemov);          
                   
            pGeral.add(panelTop, BorderLayout.NORTH);
            pGeral.add(panelSearch, BorderLayout.CENTER);
            pGeral.add(panelButtonBottom, BorderLayout.SOUTH);
            this.add(pGeral);
    	  	
        }
        public void actualizarTabela(List<Cliente> cli) {
            for (Cliente e : cli) {
                modelo.addRow(new Object[]{String.valueOf(e.getIdCliente()), e.getNomeP(),e.getNomeU(), e.getEndereco(),e.getSexo(),e.getNrNuit(),e.getContacto(),e.getSaldo(), e.getNacionalidade()});
            }
        }
        
	public void depositar(){  
            JPanel panel1,panel2;
            panel1=new JPanel();
            panel2=new JPanel(); panel1.add(lID);
            panel1.add(tID_Nome);
            panel1.add(lValor);
            panel1.add(tValor);
            
            panel2.add(bDepositar);
            panel2.add(bCancelar);
            bDepositar.addActionListener(this);
            bCancelar.addActionListener(this);
            frameDepositar.add(panel1,BorderLayout.NORTH);
            frameDepositar.add(panel2,BorderLayout.CENTER);
            frameDepositar.setVisible(true);
            frameDepositar.pack();
            frameDepositar.setLocationRelativeTo(null);
        }
        
        public void procurarCliente(){
            panelResul.setBorder(BorderFactory.createTitledBorder("Dados"));
            janelaResul.add(panelResul);
            janelaResul.setTitle("Resultado");
            janelaResul.setSize(500,500);
            janelaResul.setVisible(true);
            janelaResul.setLocationRelativeTo(null);
        }
	

    @Override
    public void actionPerformed(ActionEvent e) {
        /*Rever a actualizacao depois do deposito*/
        CadastrarCliente cd;
        int id;
        if(e.getSource()==bAdd){
            cd=new CadastrarCliente("Cadastrar cliente");
            cd.cadastroClient();            
        }
        if(e.getSource()==bDeposito){          
            depositar(); 
            dispose();
                
        }
        if(e.getSource()==bDepositar){
            int idCli;
            double val;
            if(tID_Nome.getText().isEmpty()||tValor.getText().isEmpty())
                JOptionPane.showMessageDialog(null,"Nao deixe campos vazios");
            else{
                idCli=Integer.parseInt(tID_Nome.getText());
                System.out.println(idCli);
                val=Double.parseDouble(tValor.getText());
                System.out.println(val);
                ClienteDAO cl=new ClienteDAO();            
                cl.depositar(idCli, val);
                frameDepositar.dispose();
                //listarClientes();
                //new Clientes();
            }                       
        }
        if(e.getSource()==bCancelar){
            tID_Nome.setText("");
            tValor.setText("");
        }
        if(e.getSource()==bProcurar){
            procurarCliente();
            int i;
            i=Integer.parseInt(tID_Nome.getText());
            ClienteDAO cliBD=new ClienteDAO();
            Cliente cliMod=new Cliente();
            cliMod=cliBD.encontrarCliente(i);
            String s="ID: "+cliMod.getIdCliente()+"\nNome: "+cliMod.getNomeP()+" "+cliMod.getNomeU()+"\nEndereco: "+cliMod.getEndereco()
                        +"\nSexo: "+cliMod.getSexo()+"\nNuit: "+cliMod.getNrNuit()+"\nSaldo: "+cliMod.getSaldo()+"\nNumero: "+cliMod.getContacto()
                        +"\nNacionalidade: "+cliMod.getNacionalidade();
            listaResul.setText(s);
        }
        
    }
    
    public JPanel getpGeral() {
        return pGeral;
    }
}
