package Politicas;

import java.util.*;


public class Scan extends Politica {

    public Scan(){
        super("Scan"); 
    }
    
    public Scan(String nombre){
        super(nombre); 
    }
    
/*
    public void agregarTope(char sentido, ArrayList<Peticion> p, Integer cantPistas){

        //agrego los valores maximos y minimos que tengo que recorrer para scan
        if (sentido == 'd'){
            Peticion peticion = new Peticion(0, cantPistas ); //la ultima pista del disco, empieza en 1        
            p.add(peticion); 
        }else{
            if (sentido == 'i'){
                Peticion peticion = new Peticion(0, 1 ); //la primer pista del disco, empieza en 1        
                p.add(peticion);  
            }
        }
    }
*/

    
    
    @Override
    public void ejecutarPolitica(Disco d, List<Peticion> peticiones, Integer longLista){
 
        //divido las operaciones por menor y mayor
        super.dividirMenoresMayores(d, peticiones);
              

        int loop = 2;

        while (loop --> 0){

            if (d.getSentidoCabeza() == 'i'){
                
                //System.out.println("sentido de cabeza: "+d.getSentidoCabeza());
              //  agregarTope('i');
                for (int i = izq.size() - 1; i >= 0; i--){

                    Peticion p = izq.get(i);  
                    
                    p.setAtendida(true);

                   // System.out.println("- Peticion atendida: "+ p.getNroPeticion() + "; Pista : " + p.getNroPistaRequerida()+ "; Distancia: "+p.getDistancia());

                    int distancia = Math.abs( p.getNroPistaRequerida() - d.getPosCabeza()); // valor absoluto desde la cabeza hasta la pista requerida

                    p.setDistancia(distancia); // seteo la distancia para esa peticion en particular 

                    distTotalRecorrida+= p.distancia; // sumo la distancia global
                    
                    peticionesEjecutadas.add(p); //agrego la peticion ejecutada

                    d.setPosCabeza(p.getNroPistaRequerida()); //seteo la nueva posicion 

                }
                //termina de atender las peticiones 
                //como es Scan tengo que ir hasta la pista mas baja, 1
                try{
                    System.out.println("VALOR DE IZQ(0)" + izq.get(0));
                    if ( (d.getPosCabeza() == izq.get(0).getNroPistaRequerida())){

                        int distancia = Math.abs( d.getPosCabeza() - 1 );

                        distTotalRecorrida+= distancia; // sumo la distancia global  
                        //hago esto para agregar como peticion cuando va a la ultima pista en ese sentido y asi listar/hacer el calculo de TAT
                        Peticion p = new Peticion(peticionesEjecutadas.size()+1, 1, true, distancia);
                        peticionesEjecutadas.add(p); //agrego la peticion ejecutada

                        d.setPosCabeza(1); 
                        System.out.println("SE PONE LA CABEZA A 1");

                    }

                    d.setSentidoCabeza('d'); //termino con los menores, ahora da la vuelta
                }catch (Exception e){
                    System.out.println(e.getCause());
                }


                d.setSentidoCabeza('d'); //termino con los menores, ahora da la vuelta
                
            }else{
                if (d.getSentidoCabeza() == 'd'){

                   // System.out.println("sentido de cabeza: "+d.getSentidoCabeza());

                    for (int i = 0; i < der.size() ; i++){

                        Peticion p = der.get(i);
    
                        p.setAtendida(true);
                        
                       // System.out.println("- Peticion atendida: "+ p.getNroPeticion() + "; Pista : " + p.getNroPistaRequerida()+ "; Distancia: "+p.getDistancia());

                        int distancia = Math.abs( p.getNroPistaRequerida() - d.getPosCabeza());
    
                        p.setDistancia(distancia); 
    
                        peticionesEjecutadas.add(p); 
    
                        distTotalRecorrida+= p.distancia; 
    
                        d.setPosCabeza(p.getNroPistaRequerida());  
                    }

                    //termina de atender las peticiones 
                    //como es Scan tengo que ir hasta la pista mas alta, 100 por ej
                   try{
                        if ( (d.getPosCabeza() == der.get(der.size()-1 ).getNroPistaRequerida())){

                            int distancia = Math.abs( d.getPosCabeza() - d.getCantPistas());

                            distTotalRecorrida+= distancia; // sumo la distancia global

                            //hago esto para agregar como peticion cuando va a la ultima pista en ese sentido y asi listar/hacer el calculo de TAT
                            Peticion p = new Peticion(peticionesEjecutadas.size()+1, d.getCantPistas(), true, distancia);
                            peticionesEjecutadas.add(p); //agrego la peticion ejecutada

                            d.setPosCabeza(d.getCantPistas()); 
                            System.out.println("SE PONE LA CABEZA A 100");

                        }

                        d.setSentidoCabeza('i'); //termino con los mayores, ahora da la vuelta

                    }catch(Exception e){
                        System.out.println(e.getCause());
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
