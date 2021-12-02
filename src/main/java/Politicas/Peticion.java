package Politicas;

public class Peticion implements Comparable<Peticion>{

    public Integer nroPeticion;
    protected Integer nroPistaRequerida;
    protected Integer distancia; // distancia recorrida para esa peticion
    protected boolean atendida;           // si la peticion fue atendida o no

   
    
    public Peticion(Integer nroPeticion, Integer nroPistaRequerida) {
        this.nroPeticion = nroPeticion;
        this.nroPistaRequerida = nroPistaRequerida;
        this.atendida=false;
        this.distancia=0;
    }
    
    public Peticion(Integer nroPeticion, Integer nroPistaRequerida, boolean atendida, Integer distancia) {
        this.nroPeticion = nroPeticion;
        this.nroPistaRequerida = nroPistaRequerida;
        this.atendida= atendida;
        this.distancia= distancia;
    }
    
    @Override
    public String toString() {
        return ("Nro Peticion: " + nroPeticion + "; Pista Requerida: "+ nroPistaRequerida+ "; Distancia: "+distancia);
    }

    @Override
	public int compareTo(Peticion p) {
            return this.getNroPistaRequerida().compareTo(p.getNroPistaRequerida());
	}

    //getters y setters 
    
    public boolean isAtendida() {
        return atendida;
    }

    public void setAtendida(boolean atendida) {
        this.atendida = atendida;
    }

    public Integer getDistancia() {
        return distancia;
    }
    public Integer getNroPeticion() {
        return nroPeticion;
    }
    public void setNroPeticion(Integer nroPeticion) {
        this.nroPeticion = nroPeticion;
    }
    public Integer getNroPistaRequerida() {
        return nroPistaRequerida;
    }
    public void setNroPistaRequerida(Integer nroPistaRequerida) {
        this.nroPistaRequerida = nroPistaRequerida;
    }
    public void setDistancia(Integer distancia) {
        this.distancia = distancia;
    }

}
