package Politicas;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public abstract class Politica {
   
    protected String nombrePolitica;
    protected Integer distTotalRecorrida;
    protected Double accesoTotal; // ret. rotacional + Seek time + t.Transf.
    protected List<Peticion> peticionesEjecutadas; //las peticiones como fueron ejecutadas
    protected List<Peticion> peticionesIniciales; //despues hay que imprimirlas, por eso las guardo
    
    //para la division de la lista de peticiones
    protected List<Peticion> izq;
    protected List<Peticion> der;
    
    protected List<Peticion> peticionesAdicionales; // para FScan

    

  
    public abstract void ejecutarPolitica(Disco d, List<Peticion> peticiones, Integer longLista); //metodo implementado por cada politica
    
    public abstract void dividirPeticiones(Integer n, Disco d);
    
    @Override
    public String toString() {
       
        return ("Politica: " + nombrePolitica + "; Distancia Total: " + distTotalRecorrida + "; Acceso Total: " + accesoTotal);
    }


    public Politica(String nombrePolitica) {
        this.nombrePolitica = nombrePolitica;
        this.distTotalRecorrida=0;
        this.accesoTotal=0.0;
        this.peticionesEjecutadas= new ArrayList<Peticion>();
        this.peticionesIniciales=new ArrayList<Peticion>();
    }

    public void calcularAccesoTotal(Integer seekTime, Double retardoRotacional, Integer transferenciaSector){
        
        int cantPeticiones= this.peticionesEjecutadas.size();
        
        this.accesoTotal = ((this.distTotalRecorrida*seekTime)+ (cantPeticiones*retardoRotacional) + (transferenciaSector*cantPeticiones));
    }
    
    public void dividirMenoresMayores(Disco d, List<Peticion> peticiones){
        
        izq = new ArrayList<Peticion>();
        der = new ArrayList<Peticion>();
        
        for (int i = 0; i < peticiones.size(); i++){
            
            Peticion p = peticiones.get(i);

            //divido las peticiones en dos partes, menores y mayores que mi posicion
            if ( (p.getNroPistaRequerida() > d.getPosCabeza())  || (p.getNroPistaRequerida() == d.getPosCabeza()) ) {
                der.add(p);
            }else{
                if (p.getNroPistaRequerida() < d.getPosCabeza()){
                    izq.add(p);
                }
            }
        }
        //ordeno las listas tanto la izq y der
        Collections.sort(izq);
        Collections.sort(der);
    }
 
    
    public void printPeticiones(List<Peticion> peticiones){
        for (int i=0; i < peticiones.size(); i++){
            App.imprimir(peticiones.get(i).toString()); 
        }    
    }
    
    //getters y setters

    public String getNombrePolitica() {
        return nombrePolitica;
    }

    public Double getAccesoTotal() {
        return accesoTotal;
    }

    public List<Peticion> getPeticionesIniciales() {
        return peticionesIniciales;
    }

    public void setPeticionesIniciales(List<Peticion> peticionesIniciales) {
        this.peticionesIniciales = peticionesIniciales;
    }

    public List<Peticion> getPeticionesEjecutadas() {
        return peticionesEjecutadas;
    }

    public void setPeticionesEjecutadas(List<Peticion> peticionesEjecutadas) {
        this.peticionesEjecutadas = peticionesEjecutadas;
    }

    public List<Peticion> getPeticionesAdicionales() {
        return peticionesAdicionales;
    }

    public void setPeticionesAdicionales(List<Peticion> peticionesAdicionales) {
        this.peticionesAdicionales = peticionesAdicionales;
    }
    
    public Integer getDistTotalRecorrida() {
        return distTotalRecorrida;
    }

    public void setDistTotalRecorrida(Integer distTotalRecorrida) {
        this.distTotalRecorrida = distTotalRecorrida;
    }

    public void setAccesoTotal(Double accesoTotal) {
        this.accesoTotal = accesoTotal;
    }

    





}
