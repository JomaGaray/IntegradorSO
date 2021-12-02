package Politicas;

import java.util.List;


public class Sstf extends Politica {

    public Sstf(){
        super("Sstf"); 
    }


    public static void calcularDistancia(List<Peticion> peticiones, int posCabeza){ // posCabeza va cambiando

        for (int i = 0; i < peticiones.size(); i++){

           // System.out.println("--Prueba posCabeza: "+ posCabeza);

            Peticion p = peticiones.get(i);

            if (!p.isAtendida()){ //no cambio la distancia de las que ya estan atendidas
                
                int distancia = Math.abs( p.getNroPistaRequerida() - posCabeza); // valor absoluto desde la cabeza hasta la pista requerida
    
                //System.out.println("Distancia a poner: "+distancia);
    
                p.setDistancia(distancia); // seteo la distancia para esa peticion
            }
        }

    }

    public static int encontrarMinDist(List<Peticion> peticiones){ //devuelve el indice de la peticion mas cerca de la cabeza

        int index = -1;
        int minDist = Integer.MAX_VALUE;

        for (int i = 0; i < peticiones.size(); i++) {

            Peticion p = peticiones.get(i);

            if ((!p.atendida) && (minDist > p.distancia)) {
                 
                minDist = p.distancia;
                index = i;
            }
        }
        return index;
    }


    // se mueve segun el menor tiempo de posicionamiento SeekTime
    @Override
    public void ejecutarPolitica(Disco d, List<Peticion> peticiones, Integer longLista){ 
 
        if (peticiones.size()==0){
            return;
        }

        for (int i = 0; i < peticiones.size(); i++) {
            
            calcularDistancia(peticiones, d.getPosCabeza());
            
            int index = encontrarMinDist(peticiones); // busco la peticion mas cercana
            
            Peticion p = peticiones.get(index);
            
            System.out.println("Peticion atendida: "+peticiones.get(index).getNroPeticion()+"; Distancia: "+ peticiones.get(index).getDistancia());
            
            p.setAtendida(true); //actualizo esa peticion como atendida
            
            super.peticionesEjecutadas.add(p); //agrego la peticion ejecutada
            
            super.distTotalRecorrida+= p.distancia; // sumo la distancia global
            
            d.setPosCabeza(p.nroPistaRequerida); //seteo la nueva posicion de la cabeza
            
            System.out.println("Cabeza: "+ d.getPosCabeza());
            System.out.println("----");

            //calcularDistancia(peticiones, d.getPosCabeza());
        }

       
        super.calcularAccesoTotal(d.getSeekTime(), d.getRetardoRotacional(), d.gettTransSector());
    }

    @Override
    public void dividirPeticiones(Integer n, Disco d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}