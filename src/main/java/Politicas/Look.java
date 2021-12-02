package Politicas;

import java.util.*;
;

public class Look extends Politica {

    public Look(){
        super("Look"); 
    }

    @Override
    public void ejecutarPolitica(Disco d, List<Peticion> peticiones, Integer longLista){
        
        int loop = 2;
        
        //divido las operaciones por menor y mayor
        super.dividirMenoresMayores(d, peticiones);

        while (loop --> 0){

            if (d.getSentidoCabeza() == 'i'){

                for (int i = izq.size() - 1; i >= 0; i--){

                    Peticion p = izq.get(i);  
                    
                    p.setAtendida(true);

                    int distancia = Math.abs( p.getNroPistaRequerida() - d.getPosCabeza()); // valor absoluto desde la cabeza hasta la pista requerida
                    
                    p.setDistancia(distancia); // seteo la distancia para esa peticion en particular 
                    
                    System.out.println("- Peticion atendida: "+ p.getNroPeticion() + "; Pista: " + p.getNroPistaRequerida()+ "; Distancia: "+p.getDistancia());

                    distTotalRecorrida+= p.distancia; // sumo la distancia global
                    
                    peticionesEjecutadas.add(p); //agrego la peticion ejecutada

                    d.setPosCabeza(p.getNroPistaRequerida()); //seteo la nueva posicion 

                }
                
                d.setSentidoCabeza('d'); //termino con los menores, ahora da la vuelta
                
            }else{
                if (d.getSentidoCabeza() == 'd'){

                    for (int i = 0; i < der.size() ; i++){

                        Peticion p = der.get(i);
    
                        p.setAtendida(true);
    
                        int distancia = Math.abs( p.getNroPistaRequerida() - d.getPosCabeza());
    
                        p.setDistancia(distancia); 
    
                        System.out.println("- Peticion atendida: "+ p.getNroPeticion() + "; Pista: " + p.getNroPistaRequerida()+ "; Distancia: "+p.getDistancia());

                        peticionesEjecutadas.add(p); 
    
                        distTotalRecorrida+= p.distancia; 
    
                        d.setPosCabeza(p.getNroPistaRequerida());  
                    }

                    d.setSentidoCabeza('i'); //termino con los mayores, ahora da la vuelta
                }
            }
            
        }
        
        super.calcularAccesoTotal(d.getSeekTime(), d.getRetardoRotacional(), d.gettTransSector());
    }   

    @Override
    public void dividirPeticiones(Integer n, Disco d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
