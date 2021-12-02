package Politicas;

import java.util.List;



public class Fcfs extends Politica {

    //el sentido de la cabeza no tiene sentido en esta politica
    public Fcfs(){
        super("FCFS"); 
    }

    @Override
    public void ejecutarPolitica(Disco d, List<Peticion> peticiones, Integer longLista){
        
       // Integer posCabeza = d.getPosCabeza(); // la pos inicial

        for (int i = 0;  i < peticiones.size(); i++){
            
            Peticion p = peticiones.get(i);
            
            p.setAtendida(true);
 
           // System.out.println("- Peticion atendida: "+ p.getNroPeticion() + " ;Pista: " + p.getNroPistaRequerida());
                    
            int distancia = Math.abs( p.getNroPistaRequerida() - d.getPosCabeza()); // valor absoluto desde la cabeza hasta la pista requerida

            p.setDistancia(distancia); // seteo la distancia para esa peticion en particular
            
            //System.out.println("- Peticion atendida: "+ p.getNroPeticion() + "; Pista : " + p.getNroPistaRequerida()+ "; Distancia: "+p.getDistancia());
            

            distTotalRecorrida+= p.distancia; // sumo la distancia global

            peticionesEjecutadas.add(p); //agrego la peticion ejecutada

            d.setPosCabeza(p.getNroPistaRequerida()); //seteo la nueva posicion 
            
        }
        
        super.calcularAccesoTotal(d.getSeekTime(), d.getRetardoRotacional(), d.gettTransSector());
    }

    @Override
    public void dividirPeticiones(Integer n, Disco d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}           
