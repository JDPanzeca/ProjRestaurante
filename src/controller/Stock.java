
package controller;


public class Stock {
    
    private int idProd;
    private float quant;
   // private String nome;
    
    public Stock(int idProd,float quant,String nome){
        this.idProd=idProd;
        this.quant=quant;
        //this.nome=nome;
    }
    
    public Stock(){
        
    }


    public int getIdProd() {
        return idProd;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }

    public float getQuant() {
        return quant;
    }

    public void setQuant(float quant) {
        this.quant = quant;
    }

    /*public String getNome() {
        return nome;
    }
 
    public void setNome(String nome) {
        this.nome = nome;
    }*/
   
    
}
