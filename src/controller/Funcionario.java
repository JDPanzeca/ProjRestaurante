
package controller;
import java.sql.Date;

public class Funcionario {
    private String nome,bi,endereco,sexo,categoria;
    private int contacto, nrNuit,idFunc;
    private Date dataNasc;
    
    public Funcionario(){}
    public Funcionario(String nome,String bi,String endereco,String sexo,String categoria,int contacto,int nrNuit,Date dataNasc ){
        this.nome=nome;
        this.bi=bi;
        this.endereco=endereco;
        this.sexo=sexo;
        this.categoria=categoria;
        this.contacto=contacto;
        this.nrNuit=nrNuit;
        this.dataNasc=dataNasc;
        
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getBi() {
        return bi;
    }
    
    public void setBi(String bi) {
        this.bi = bi;
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
    
    public String getCategoria() {
        return categoria;
    }
  
    public void setCategoria(String categoria) {
        this.categoria = categoria;
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
   
    public Date getDataNasc() {
        return dataNasc;
    }
    
    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public int getIdFunc() {
        return idFunc;
    }

    public void setIdFunc(int idFunc) {
        this.idFunc = idFunc;
    }
    
    
}
