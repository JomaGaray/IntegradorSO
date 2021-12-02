/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Politicas;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author joseg
 */
public class NStepScan extends Politica{
    static int indice;
    
    public NStepScan(){
        super("NStepScan");
        indice =0;
    }
    
    public List<Peticion> dividirPeticiones(List<Peticion> peticiones, Integer longLista){
        
        int cantPeticiones=0;
        List<Peticion> listaAux = new ArrayList<>(); //para las nuevas peticiones
          
        while ( (cantPeticiones < longLista)) {
            
            listaAux.add(peticiones.get(indice)); //agrego la peticion
            cantPeticiones++;
            indice++;
        }
        
        if (cantPeticiones==longLista){ //cuando esten las correctas

            cantPeticiones=0;
            //super.printPeticiones(listaAux);
        }
        
        return (listaAux);
    }

    @Override
    public void ejecutarPolitica(Disco d, List<Peticion> peticiones, Integer longLista){
 
        List <Peticion> listaAuxiliar = new ArrayList<Peticion>();
       
        
        
        while(indice < peticiones.size()){ // para recorrer la lista entera
            
            //me devuelve una lista con las peticiones correctas
            listaAuxiliar = dividirPeticiones(peticiones, longLista);
            
            
            App.imprimir("-- Lista --");
            super.printPeticiones(listaAuxiliar);
            
            //ahora ejecuto SCAN
            
            //divido las operaciones por menor y mayor        
            super.dividirMenoresMayores(d, listaAuxiliar);
            
            int loop = 2;

            while (loop --> 0){

                if (d.getSentidoCabeza() == 'i'){
                   System.out.println("#############################");


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
                    //if ( (!(d.getPosCabeza() == 1)) & (indice == peticiones.size()) ) {
                   System.out.println("DISCO BAJANDO");
                    System.out.println("DISCO EN "+ d.getPosCabeza().toString());
                    
                    
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

                }else{
                    if (d.getSentidoCabeza() == 'd'){
                        System.out.println("#############################");


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
                        //if ( (!(d.getPosCabeza() == d.getCantPistas())) & (indice == peticiones.size())){
                        System.out.println("DISCO CRECIENDO");
                        System.out.println("DISCO EN "+ d.getPosCabeza().toString());
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
                    }
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
