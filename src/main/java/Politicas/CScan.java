package Politicas;

import java.util.*;


public class CScan extends Politica {
    
    public CScan(){
        super("CScan"); 
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
            
            //Prueba.imprimir("dafa")
    
            distTotalRecorrida+= p.distancia; 
            
            peticionesEjecutadas.add(p); 
    
            d.setPosCabeza(p.getNroPistaRequerida());
    
        }
    }

    private void sumarDiferenciaInferior(Disco d, boolean calcularDistancia){ //sumo la diferencia de posCabeza con el limite inferior
        int distancia=0;
        
        if (!(d.getPosCabeza() == 1)) {
                    
            if (calcularDistancia){distancia = Math.abs( d.getPosCabeza() - 1 );} 


            distTotalRecorrida+= distancia; // sumo la distancia global  
            //hago esto para agregar como peticion cuando va a la ultima pista en ese sentido y asi listar/hacer el calculo de TAT
            Peticion p = new Peticion(peticionesEjecutadas.size()+1, 1, true, distancia);
            peticionesEjecutadas.add(p); //agrego la peticion ejecutada

            d.setPosCabeza(1); 
                    
                   // System.out.println("Se acomodo la cabeza a la primera pista: "+d.getPosCabeza());
        }
        
        /*
        if (!(d.getPosCabeza() == 1)) {
            int distancia = Math.abs( d.getPosCabeza() - 1 );
            
            distTotalRecorrida+= distancia; // sumo la distancia global

            System.out.println("se sumo la diferencia inferior...");
        } */
    }

    private void sumarDiferenciaSuperior(Disco d, boolean calcularDistancia){
        int distancia =0;
        
        if (!(d.getPosCabeza() == d.getCantPistas())) {

            if (calcularDistancia){distancia = Math.abs( d.getPosCabeza() - d.getCantPistas());}

            distTotalRecorrida+= distancia; // sumo la distancia global

            //hago esto para agregar como peticion cuando va a la ultima pista en ese sentido y asi listar/hacer el calculo de TAT
            Peticion p = new Peticion(peticionesEjecutadas.size()+1, d.getCantPistas(), true, distancia);
            peticionesEjecutadas.add(p); //agrego la peticion ejecutada

            d.setPosCabeza(d.getCantPistas()); 
        }    
    }
    
    

    @Override
    public void ejecutarPolitica(Disco d, List<Peticion> peticiones, Integer longLista){
       
        
       //divido las operaciones por menor y mayor
        super.dividirMenoresMayores(d, peticiones);

        if (d.getSentidoCabeza() == 'i'){
            
            ejecutarDerAIzq(izq, d);
            
            sumarDiferenciaInferior(d, true);

            sumarDiferenciaSuperior(d, false);
            
            d.setPosCabeza(d.getCantPistas());//me posiciono en el extremo derecho, circular
           
            ejecutarDerAIzq(der, d); //mando el extremo derecho

            sumarDiferenciaInferior(d, true);

            //no cambio de sentido de cabeza
        }else{
            if (d.getSentidoCabeza() == 'd'){

                ejecutarIzqADer(der, d);
                
                sumarDiferenciaSuperior(d, true);
                
                sumarDiferenciaInferior(d, false);
                
                d.setPosCabeza(1); //me posiciono en el extremo izquierdo, circular

                ejecutarIzqADer(izq, d);

                sumarDiferenciaSuperior(d, true);
                //no cambio de sentido de cabeza
            
            }
        }
        
        super.calcularAccesoTotal(d.getSeekTime(), d.getRetardoRotacional(), d.gettTransSector());
    }

    @Override
    public void dividirPeticiones(Integer n,Disco d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
