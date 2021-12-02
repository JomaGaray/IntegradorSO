package Politicas;

import java.util.*;


public class CLook extends Politica {

    public CLook(){
        super("CLook"); 
    }

    private void ejecutarIzqADer(List<Peticion> lista, Disco d){
            
        for (int i = 0; i < lista.size() ; i++){

            Peticion p = lista.get(i);  
            
            p.setAtendida(true); // lista.get(i).setAtendida(true);
    
            
            int distancia = Math.abs( p.getNroPistaRequerida() - d.getPosCabeza()); // valor absoluto desde la cabeza hasta la pista requerida
            
            p.setDistancia(distancia); // seteo la distancia para esa peticion en particular 
            System.out.println("- Peticion atendida: "+ p.getNroPeticion() + "; Pista: " + p.getNroPistaRequerida()+ "; Distancia: "+p.getDistancia());
    
            distTotalRecorrida+= p.distancia; // sumo la distancia global
            
            peticionesEjecutadas.add(p); //agrego la peticion ejecutada
    
            d.setPosCabeza(p.getNroPistaRequerida()); //seteo la nueva posicion  
        }
    }

    private void ejecutarDerAIzq(List<Peticion> lista, Disco d){
        
        for (int i = lista.size() - 1; i >= 0; i--){
    
            Peticion p = lista.get(i);  
            
            p.setAtendida(true); // lista.get(i).setAtendida(true);
    
            
            int distancia = Math.abs( p.getNroPistaRequerida() - d.getPosCabeza()); 
            
            p.setDistancia(distancia); 
            
            System.out.println("- Peticion atendida: "+ p.getNroPeticion() + "; Pista: " + p.getNroPistaRequerida()+ "; Distancia: "+p.getDistancia());
            distTotalRecorrida+= p.distancia; 
            
            peticionesEjecutadas.add(p); 
    
            d.setPosCabeza(p.getNroPistaRequerida());
    
        }
    }

    @Override
    public void ejecutarPolitica(Disco d, List<Peticion> peticiones, Integer longLista){
 
        
        //divido las operaciones por menor y mayor
        super.dividirMenoresMayores(d, peticiones);


        if (d.getSentidoCabeza() == 'i'){
            
            ejecutarDerAIzq(izq, d);

            //obtengo la ultima pista requerida del lado derecho
            d.setPosCabeza(der.get(der.size()-1).getNroPistaRequerida());
            
            ejecutarDerAIzq(der, d); //mando el extremo derecho

            //no cambio de sentido de cabeza
            
        }else{

            if (d.getSentidoCabeza() == 'd'){

                ejecutarIzqADer(der, d);
                
                //obtengo la primera pista requerida del lado izquierdo
                d.setPosCabeza((izq.get(0).getNroPistaRequerida()));

                ejecutarIzqADer(izq, d);

                
            }
        }
        
        super.calcularAccesoTotal(d.getSeekTime(), d.getRetardoRotacional(), d.gettTransSector());
    }

    @Override
    public void dividirPeticiones(Integer n, Disco d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
