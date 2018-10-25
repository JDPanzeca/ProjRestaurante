package Visao;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import model.ProdutoDAO;
import controller.Produto;
import controller.VendaUnit;
import controller.Cliente;
import model.ClienteDAO;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Vender extends JFrame implements ActionListener{
	
    private final JanelaEvent eventW;
    private JComboBox<String> prod,mesa,categoria;
    private final JLabel lProduto,lQuant,lValor,lTroc,lCod,lValPag, lID_Client;
    private final JTextField tQuant,tValor,tTroc,tValPag,tID_Client;
    private final JButton bEfectuar,bCancelar, bAdd,bRemove,bProcurar, bCancelRemov;
    private final JPasswordField tPssCliente;
    private final String [] lt;
    private int j=0,idProd=0;
    private   JList  listaProd, listaRem;
    private final DefaultListModel modProdut, modRemov;
    private double valorApagar=0;
    private final JMenuBar barra;
    //private final JMenuItem item;
    private List lisBD;
    private final List listaVendaUnits,listaAuxIndice ;
    private final JTextArea resultClient;
    private final JPanel pJunt,pBuscaClient,pAddRemover/*, principal*/;
    private final AmbienteBackGrond ambiente;
    
    public Vender(){
        //Definindo titulo da janela
        super("Efectuando vendas");
        //Instancia do background
        ambiente=new AmbienteBackGrond();       
        //Criando a barra de Menu e add a janela
        barra=new JMenuBar();            
        //Inicializando listas
        listaVendaUnits=new ArrayList();
        listaAuxIndice=new ArrayList();        
        //Inicializando Labels
        lt=new String[100];
        eventW=new JanelaEvent();
        lValPag=new JLabel("Valor Pago:");
        lTroc=new JLabel("Trocos:");
        lProduto=new JLabel("Produto:");
        lQuant=new JLabel("Quantidade:");
        lValor=new JLabel("Valor a pagar:");
        lCod=new JLabel("Codigo:");
        lID_Client= new JLabel("ID");   
        //Instancias de modelos de listas para remocao e produtos
        modProdut=new DefaultListModel(); 
        modRemov=new DefaultListModel();
        //Inicializando TextFields
        tValPag=new JTextField(7);
        tQuant=new JTextField(5);
        tValor=new JTextField(10);
        tTroc=new JTextField(10);
        tID_Client=new JTextField(5);
        tTroc.setEditable(false);
        tValor.setEditable(false);
        //Inicializando Botoes
        bRemove=new JButton("Apagar");
        bAdd=new JButton("Adicionar");
        bEfectuar=new JButton("Efectuar");
        bCancelar=new JButton("Cancelar");
        bProcurar=new JButton("Procurar");
        bCancelRemov=new JButton("Cancelar");
        //Inicializando Paineis
        pJunt=new JPanel(new BorderLayout());
        pBuscaClient=new JPanel();
        pBuscaClient.setBorder(BorderFactory.createTitledBorder("Resultado"));
        pBuscaClient.setOpaque(false);
        pAddRemover=new JPanel();
        GridBagLayout layout=new GridBagLayout();
        pAddRemover.setLayout(layout);
        pAddRemover.setOpaque(false);
        
        //principal=new JPanel();
        
        GridBagLayout gb=new GridBagLayout();
        //principal.setLayout(gb);
        ambiente.setLayout(gb);
        //principal.setOpaque(true);
                
        //Colocando imagens nos botoes
        bEfectuar.setIcon(new ImageIcon(getClass().getResource("Efect.png")));
        bCancelar.setIcon(new ImageIcon(getClass().getResource("canc.png")));
        
        //Text do password -Codigo
        tPssCliente=new JPasswordField(7);
        
        //Inicializando o TextArea
        resultClient=new JTextArea();
        resultClient.setEditable(false); 
        
        //Acao para o item adicionar produto    
        //item.addActionListener(this);
        
    }
    public void exibirJanela(){
        vender();
    }
	
    public  void vender(){
        this.add(ambiente);
        //Chamada do Metodo que contem o painel de procura do cliente no Menu da vendas
        telaProcurarCliente();
        //Chamada do metodo principal de vendas
        venderPrincipal();
        //Chamada do Metodo que contem a Tela que vao ser listados os produtos a comprar
        telaProdutos();
        //Chamada do metodo que conte a tela dos produtos a remover
        telaRemover();
        //Chamada do metodo que contem o painel de tipo de compra(Cliente cadastrado ou nao)
        tipoCompra();
        //Definindo comportamento dos componentes usando o GridBagLayout(Botoes e paineis da classe Vender)
             
        GridBagConstraints gb2=new GridBagConstraints();
        gb2.gridx=0;
        gb2.gridy=2;
        gb2.weightx=1;
        gb2.weighty=1;
        gb2.fill=GridBagConstraints.BOTH;
        //principal.add(pAddRemover,gb2);
        ambiente.add(pAddRemover,gb2);
        
        //painel de baixo  
        JPanel confir=new JPanel();
        confir.add(bEfectuar);
        confir.add(bCancelar);
        confir.setOpaque(false);
        
        GridBagConstraints gb3=new GridBagConstraints();
        gb3.gridx=0;
        gb3.gridy=3;
        gb3.weightx=1;
        gb3.weighty=1;
        gb3.fill=GridBagConstraints.BOTH;
        gb3.insets=new Insets(80, 0, 0, 0);
        //principal.add(confir,gb3);
        ambiente.add(confir,gb3);
        
        //ambiente.setLayout(new BorderLayout());
        //ambiente.add(principal,BorderLayout.CENTER);
        //this.add(principal);
        //Definindo metodos de accoes nos botoes
        bAdd.addActionListener(this);
        bRemove.addActionListener(this);
        bEfectuar.addActionListener(this);
        bCancelar.addActionListener(this);
        bCancelRemov.addActionListener(this);
        bProcurar.addActionListener(this);
        
        this.pack();
        setLocationRelativeTo(null);
        setVisible(true);           
                
    }
        
    
    private void telaRemover(){
        JPanel removerProd=new JPanel(new BorderLayout());
        removerProd.setOpaque(false);
        
        JPanel aux=new JPanel();
        aux.setOpaque(false);
        aux.add(bRemove);
        aux.add(bCancelRemov);
        
        listaRem=new JList(modRemov);
        JScrollPane srr=new JScrollPane(listaRem);
        removerProd.add(srr, BorderLayout.CENTER);        
        removerProd.add(aux, BorderLayout.SOUTH);
        removerProd.setBorder(BorderFactory.createTitledBorder("Remover"));
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.insets=new Insets(10, 10, 10, 10);        
        gbc.weightx=1;
        gbc.weighty=1;
        gbc.fill=GridBagConstraints.BOTH;
        pAddRemover.add(removerProd,gbc);
        
    }
    
    private void telaProdutos(){
        JPanel addProdutos=new JPanel(new BorderLayout());
        addProdutos.setOpaque(false);
        
        JPanel val_List_Prod=new JPanel();
        val_List_Prod.setOpaque(false);
        val_List_Prod.add(lValor);
        val_List_Prod.add(tValor);
        val_List_Prod.add(lTroc);
        val_List_Prod.add(tTroc);
        
        listaProd=new JList(modProdut);
        JScrollPane sr=new JScrollPane(listaProd);
        listaProd.addMouseListener(new MouseAdapter() { 
            //Metodo para evento do clique duplo para adicionar os produtos na lista de remocao
            public void mouseClicked( MouseEvent evt){                
                int k=listaProd.getSelectedIndex();
                boolean ver=false;
                if(evt.getClickCount()==2){
                    if(listaAuxIndice.isEmpty()){
                        String item=(String)listaProd.getSelectedValue();
                        removListaProdu(item); 
                        listaAuxIndice.add(k);
                    }
                    else{
                        for(int i=0; i<listaAuxIndice.size();i++){
                            int ind=(int)listaAuxIndice.get(i);
                            if(ind==k){                                
                                ver=true;
                            }                            
                        }
                        if(ver){
                            JOptionPane.showMessageDialog(null,"Este produto ja foi selecionado");   
                        }
                        else{
                            String item=(String)listaProd.getSelectedValue();
                            removListaProdu(item); 
                            listaAuxIndice.add(k);                            
                        }
                    }
                }
                    
            }

        });
        
        addProdutos.add(sr, BorderLayout.CENTER);
        addProdutos.add(val_List_Prod, BorderLayout.SOUTH);
        addProdutos.setBorder(BorderFactory.createTitledBorder("Produtos"));
        GridBagConstraints gbc1=new GridBagConstraints();
        gbc1.gridx=1;
        gbc1.gridy=0;
        gbc1.insets=new Insets(10, 10, 10, 10);
      
        gbc1.weightx=1;
        gbc1.weighty=1;
        gbc1.fill=GridBagConstraints.BOTH;
        pAddRemover.add(addProdutos,gbc1);     
        
    }
    
    private void telaProcurarCliente(){
        
        //Definindo a procura do cliente
        JPanel procurarClient=new JPanel();
        procurarClient.setOpaque(false);
        GridBagLayout gblProcClient=new GridBagLayout();
        procurarClient.setLayout(gblProcClient);
        //Definindo comportamento de componentes
        GridBagConstraints gbcPrCli=new GridBagConstraints();
        gbcPrCli.gridx=0;
        gbcPrCli.gridy=0;
        gbcPrCli.insets=new Insets(5,5,5,5);
        procurarClient.add(lID_Client,gbcPrCli);
        
        GridBagConstraints gbcPrCli1=new GridBagConstraints();
        gbcPrCli1.gridx=1;
        gbcPrCli1.gridy=0;  
        gbcPrCli1.insets=new Insets(5,5,5,5);
        procurarClient.add(tID_Client,gbcPrCli1);        
        
        GridBagConstraints gbcPrCli2=new GridBagConstraints();
        gbcPrCli2.gridx=2;
        gbcPrCli2.gridy=0;
        gbcPrCli2.insets=new Insets(5,5,5,5);
        procurarClient.add(bProcurar,gbcPrCli2);        
         
        pBuscaClient.add(resultClient);
                
        GridBagConstraints gbcPrCli3=new GridBagConstraints();
        gbcPrCli3.gridx=1;
        gbcPrCli3.gridy=1; 
        gbcPrCli3.gridwidth=3;
        gbcPrCli3.weightx=1;
        gbcPrCli3.weighty=1;
        gbcPrCli3.fill=GridBagConstraints.BOTH;
        gbcPrCli3.insets=new Insets(5,5,5,5);
        procurarClient.add(pBuscaClient,gbcPrCli3);        
        procurarClient.setBorder(BorderFactory.createTitledBorder("Clientes"));        
        
        GridBagConstraints gbc2=new GridBagConstraints();
        gbc2.gridx=2;
        gbc2.gridy=0;
        gbc2.insets=new Insets(10, 10, 10, 10);        
        gbc2.weightx=1;
        gbc2.weighty=1;
        gbc2.fill=GridBagConstraints.BOTH;
        pAddRemover.add(procurarClient,gbc2);
       
    }
      
    public void novoProduto(String produto){
        prod.addItem(produto);       
        
    }
    
    public void removListaProdu(String nome){
        modRemov.add(j,nome);
        j++;
    }
    
    public void addListaProdu(String nome){            
        modProdut.add(idProd,nome);        
        idProd++;
    }
    
    public void venderPrincipal(){
        JLabel lcategoria,lmesa;
        lcategoria=new JLabel("Categoria");
        lmesa=new JLabel("MESA");
        Produto p;    
        ProdutoDAO pd=new ProdutoDAO();
        lisBD=new ArrayList<>();
        lisBD=pd.listarProdutos();
        prod=new JComboBox();
        categoria=new JComboBox();
        mesa=new JComboBox();
        prod.addActionListener(this);
        for(int i=0;i<lisBD.size();i++){
            p=(Produto)lisBD.get(i);
            prod.addItem(p.getNome()+"-----"+p.getPreco()+"Mt");
        }
        JPanel vend=new JPanel(new GridBagLayout());
        JPanel aux1=new JPanel();
        JPanel aux2=new JPanel();
        vend.setOpaque(false);
        aux1.setOpaque(false);
        aux2.setOpaque(false);
        vend.setBorder(BorderFactory.createBevelBorder(j));
        
        aux1.add(lmesa);
        aux1.add(mesa);
        
        aux2.add(lcategoria);
        aux2.add(categoria);
        aux2.add(lProduto);
        aux2.add(prod);
        aux2.add(lQuant);
        aux2.add(tQuant);
        aux2.add(bAdd);
        
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.gridx=0;
        gbc.gridy=0;
        vend.add(aux1,gbc);
        
        gbc.gridx=0;
        gbc.gridy=1;
        vend.add(aux2,gbc);
        
        pJunt.add(vend,BorderLayout.CENTER);
        
    }
    
    private void tipoCompra(){
        ButtonGroup formaPagar;
        
        JRadioButton rNumerario,rPOS,rMPESA;
        rNumerario=new JRadioButton("Numerario",true);
        rNumerario.setOpaque(false);
        rPOS=new JRadioButton("POS");
        rPOS.setOpaque(false);
        rMPESA=new JRadioButton("MPESA");
        rMPESA.setOpaque(false);
        
        JPanel pFormPag,pVendaCab,pPagar;
        
        formaPagar=new ButtonGroup();
        formaPagar.add(rNumerario);
        formaPagar.add(rPOS);
        formaPagar.add(rMPESA);
        
        pFormPag=new JPanel();
        pFormPag.setOpaque(false);
        pFormPag.add(rNumerario);
        pFormPag.add(rPOS);
        pFormPag.add(rMPESA);
        
        pPagar=new JPanel();
        pPagar.setOpaque(false);
        pPagar.add(lCod);
        pPagar.add(tPssCliente);
        pPagar.add(lValPag);
        pPagar.add(tValPag);
        
        pVendaCab=new JPanel(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.gridx=0;
        gbc.gridy=0;
        pVendaCab.add(pFormPag,gbc);
        gbc.gridx=0;
        gbc.gridy=1;
        pVendaCab.add(pPagar,gbc);
        pVendaCab.setBorder(BorderFactory.createTitledBorder("A Compra"));
        pVendaCab.setOpaque(false);
                        
        pJunt.add(pVendaCab,BorderLayout.NORTH);        
        pJunt.setOpaque(false);
        
        GridBagConstraints gb1=new GridBagConstraints();
        gb1.gridx=0;
        gb1.gridy=1;
        gb1.weightx=1;
        gb1.weighty=1;
        //principal.add(junt,gb1);
        ambiente.add(pJunt,gb1);
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        //JTextField t;
        //CadastrarProduto cp;
        String e;
        boolean addV=false;
        VendaUnit pp=new VendaUnit();

        float quant;
            //Accoes que vao acontecer se fpr clicado no item do menu acima na tela
            /*if(event.getSource()==item){
                cp=new CadastrarProduto();
                t=cp.gettNome();
                novoProduto(t.getText());
            }  */        
            //Accoes que vao acontecer se for clicado o botao adicionar da tema
            if(event.getSource()==bAdd){                    
                Produto p;
                int ind=prod.getSelectedIndex();                 
                e=prod.getSelectedItem().toString();
                quant=Float.parseFloat(tQuant.getText());
                for(int i=0;i<lisBD.size();i++){
                    p=(Produto)lisBD.get(i);
                    if(e.equalsIgnoreCase(p.getNome()+"-----"+p.getPreco()+"Mt")){
                        addListaProdu(e+"-----"+quant+"un-----Total:"+quant*p.getPreco());
                        tQuant.setText("");
                        valorApagar+=p.getPreco()*quant;
                        pp=new VendaUnit(idProd,e, p.getPreco()*quant,quant);                            
                        tValor.setText(valorApagar+"");
                    }                            
                }
                listaVendaUnits.add(pp);                    
            }
            //Accoes que vao acontecer se for clicado o botao apagar na tela de remocao
            if(event.getSource()==bRemove){   
                VendaUnit vend;
                int ind;
                if(!modRemov.isEmpty()){
                    for(int i=0;i<listaAuxIndice.size();i++){
                        ind=(int)listaAuxIndice.get(i);
                        vend=(VendaUnit)listaVendaUnits.get(ind);
                        valorApagar-=vend.getPreco();
                        modProdut.set(ind,"______Removido______");
                    }
                    j=0;
                    listaAuxIndice.clear();
                    modRemov.removeAllElements();                    
                    tValor.setText(valorApagar+"");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Lista vazia!");
                }

            }
            //Accoes que vao acontecer se for clicado o botao cancelar no painel de remocao
            if(event.getSource()==bCancelRemov){
                j=0;
                listaAuxIndice.clear();
                modRemov.removeAllElements();                 
            }
            //Accoes para a procura do cliente
            if(event.getSource()==bProcurar){
                int id;
                id=Integer.parseInt(tID_Client.getText());
                ClienteDAO cliBD=new ClienteDAO();
                Cliente cliMod=new Cliente();
                cliMod=cliBD.encontrarCliente(id);
                String s="ID: "+cliMod.getIdCliente()+"\nNome: "+cliMod.getNomeP()+" "+cliMod.getNomeU()+"\nEndereco: "+cliMod.getEndereco()
                        +"\nSexo: "+cliMod.getSexo()+"\nNuit: "+cliMod.getNrNuit()+"\nSaldo: "+cliMod.getSaldo()+"\nNumero: "+cliMod.getContacto()
                        +"\nNacionalidade: "+cliMod.getNacionalidade();
                resultClient.setText(s);
            }
            
            //Accoes que vao ocorrer se for clicado o botao efectuar nas vendas
            if(event.getSource()==bEfectuar){
                char cd[];
                cd=tPssCliente.getPassword();
                String cod=new String(cd);
                    
                if(modProdut.isEmpty())
                    JOptionPane.showMessageDialog(null, "Escolha os produtos primeiro");
                else if(!cod.isEmpty()&&!tValPag.getText().isEmpty())
                    JOptionPane.showMessageDialog(null, "Nao e permitida mais de uma forma de pagar!","Alerta", JOptionPane.WARNING_MESSAGE);
                else if(cod.isEmpty()&&tValPag.getText().isEmpty())
                    JOptionPane.showMessageDialog(null, "Forma de pagar!/?","Alerta", JOptionPane.WARNING_MESSAGE);
                else if(!cod.isEmpty()&&!modProdut.isEmpty()){
                    int codCli=Integer.parseInt(new String(cd));
                    ClienteDAO cliD=new ClienteDAO();
                    Cliente cl;
                    cl=cliD.encontrarCliente(codCli);
                    if(cl.getSaldo()<-500)
                        JOptionPane.showMessageDialog(null, "Teu saldo e insuficiente");
                    else{
                        cliD.debito(cl, valorApagar);
                    }               
                }
                else if(!tValPag.getText().isEmpty()&&!modProdut.isEmpty()){
                    double valorPago=Double.parseDouble(tValPag.getText());
                    double valrAPagar=Double.parseDouble(tValor.getText());
                    if(valorPago<valrAPagar)
                        JOptionPane.showMessageDialog(null, "O valor nao e suficiente!");
                    else{
                        tTroc.setText(""+(valorPago-valrAPagar));
                        JOptionPane.showMessageDialog(null,"Compra feita");
                    }    
                }
                                   
            }            
            //Accoes que vao ocorrer que for clicado o botao candelar mais a baixo da tela vendas
            if(event.getSource()==bCancelar)
                this.dispose();
    }
}
