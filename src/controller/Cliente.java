
package controller;


public class Cliente {
    private String nomeP,nomeU,endereco,sexo,nacionalidade;
    private int idCliente,contacto,nrNuit;
    private double saldo;
    
    public Cliente(){}
    public Cliente(String nomeP,String nomeU,String nacionalidade, String endereco, String sexo, int contacto,int nrNuit,double saldo){
        this.nomeP=nomeP;
        this.nomeU=nomeU;
        this.endereco=endereco;
        this.sexo=sexo;
        this.contacto=contacto;
        this.nrNuit=nrNuit;
        this.saldo=saldo;
        this.nacionalidade=nacionalidade;
        
    }
    public Cliente(String nomeP,String nomeU,String nacionalidade, String endereco, String sexo, int contacto,int nrNuit){
        this.nomeP=nomeP;
        this.nomeU=nomeU;
        this.endereco=endereco;
        this.sexo=sexo;
        this.contacto=contacto;
        this.nrNuit=nrNuit;       
        this.nacionalidade=nacionalidade;
    }
    
    public String getNomeP() {
        return nomeP;
    }
    
    public void setNomeP(String nome) {
        this.nomeP = nome;
    }
    public String getNomeU() {
        return nomeU;
    }
    
    public void setNomeU(String nome) {
        this.nomeU = nome;
    }
    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getContacto() {
        return contacto;
    }

    public void setContacto(int contacto) {
        this.contacto = contacto;
    }

    public int getNrNuit() {
        return nrNuit;
    }

    public void setNrNuit(int nrNuit) {
        this.nrNuit = nrNuit;
    }

    
    public int getIdCliente() {
        return idCliente;
    }

    
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    
    public double getSaldo() {
        return saldo;
    }

    
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
     public String getNacionalidade() {
        return nacionalidade;
    }

  
    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }
    public String toString(){
        return "ID: "+this.idCliente+"\nNome: "+this.nomeP+" "+this.nomeU+"\nEndereco: "+this.endereco+"\nSexo: "+
                this.sexo+"\nNUIT: "+this.nrNuit+"\nNacionalidade: "+this.nacionalidade+"\nContacto: "+this.contacto+"\nSaldo: "+this.saldo;
    }

   
   
}
