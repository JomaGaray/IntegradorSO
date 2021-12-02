package Politicas;

import java.text.DecimalFormat;

public class Disco{

    private char sentidoCabeza;
    private Integer seekTime;
    private Integer velRotacion;  // dada en rpm
    private Integer tTransSector; // tiempo de transferencia de un sector
    private Integer tamañoBloque; // dado en sectores
    private Integer cantPistas;
    private Integer posCabeza;    //posicion de la cabeza
    
    private Double tVueltaPista; // el tiempo que tarda en darle la vuelta a una pista
    
    //calculados
    private Double retardoRotacional;
    private Integer tTransBloque; 
  
    public Disco(char sentidoCabeza, Integer seekTime, Integer velRotacion, Integer tTransSector, Integer tamañoBloque, Integer cantPistas, Integer posCabeza) {
        
        this.setSentidoCabeza(sentidoCabeza);
        this.seekTime = seekTime;
        this.velRotacion = velRotacion;
        this.tTransSector = tTransSector;
        this.tamañoBloque = tamañoBloque;
        this.cantPistas = cantPistas;
        this.posCabeza = posCabeza;
        //atributos calculados
        this.setRetardoRotacional();
        this.setTransBloque();
    }

    private void setRetardoRotacional(){
        
        tVueltaPista = ( (60.000/velRotacion)*1000 )*1.0; //tiempo que el disco da una vuelta (*1000 porque es ms)
        
        retardoRotacional = (tVueltaPista / 2)*1.0; //la mitad de una vuelta    
        
        DecimalFormat df = new DecimalFormat("#.##");
        
        System.out.println("retardo rotacional: " + df.format(retardoRotacional)) ;
        
        //System.out.println("rpm: " + velRotacion );
    }


    private void setTransBloque(){
        tTransBloque = (tTransSector * tamañoBloque);
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return ("--- Disco --- " + "\n"+
                " Direccion: "+ printSentido() + "\n"+
                " CantPistas: "+ cantPistas+ "\n"+
                " SeekTime: "+ seekTime+ "\n"+
                " VelRotacion: "+velRotacion+ " rpm"+ "\n"+
                " TamañoBloque: "+tamañoBloque+ " sectores"+ "\n"+
                " PosCabeza: "+posCabeza+ "\n"+
                " TransferenciaSector: "+tTransSector+ " ms"+ "\n"+
                " RRotacional: "+retardoRotacional +" ms"+ "\n"+
                " TransferenciaBloque: "+ tTransBloque+ " ms"); 
    }

    //getters y setters
    public char getSentidoCabeza() {
        return this.sentidoCabeza;
    }
    
    public String printSentido(){
        if (sentidoCabeza == 'd'){
            return "Creciente";
        }else{
            return "Decreciente";
        }
    }
    
    public void setSentidoCabeza(char sentidoCabeza) {
        this.sentidoCabeza = sentidoCabeza;
    }


    public Integer getCantPistas() {
        return cantPistas;
    }

    public void setCantPistas(Integer cantPistas) {
        this.cantPistas = cantPistas;
    }

    public Integer getPosCabeza() {
        return posCabeza;
    }

    public void setPosCabeza(Integer posCabeza) {
        this.posCabeza = posCabeza;
    }

    public Double getRetardoRotacional() {
        return retardoRotacional;
    }

/*
    public void setRetardoRotacional(Integer retardoRotacional) {
        this.retardoRotacional = retardoRotacional;
    }
*/   
    public Integer getSeekTime() {
        return seekTime;
    }

    public void setSeekTime(Integer seekTime) {
        this.seekTime = seekTime;
    }

    public Integer getTamañoBloque() {
        return tamañoBloque;
    }

    public void setTamañoBloque(Integer tamañoBloque) {
        this.tamañoBloque = tamañoBloque;
    }

    public Integer getVelRotacion() {
        return velRotacion;
    }

    public void setVelRotacion(Integer velRotacion) {
        this.velRotacion = velRotacion;
    }

    public Integer gettTransSector() {
        return tTransSector;
    }

    public void settTransSector(Integer tTransSector) {
        this.tTransSector = tTransSector;
    }

    public Integer gettTransBloque() {
        return tTransBloque;
    }

    public void settTransBloque(Integer tTransBloque) {
        this.tTransBloque = tTransBloque;
    }



}
