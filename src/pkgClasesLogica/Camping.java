
package pkgClasesLogica;

import java.util.ArrayList;
import pkgParametros.Param;


public class Camping {
    
    ArrayList <Parcela> arrayCamping;
    
    public Camping() {
    
        arrayCamping = new ArrayList<>();
        
        int id = 1;
        
        // 10 Tiendas 
        
        for (int i = 0; i < Param.totalTiendas; i++) {
            
            arrayCamping.add(new Tienda(id));
            
            id++;
            
        }
        
        // 20 Caravanas 
        
        for (int i = 0; i < Param.totalCaravanas; i++) {
            
            arrayCamping.add(new Caravana(id));
            
            id++;
            
        }
        
        // 30 Bungalows 
        
        for (int i = 0; i < Param.totalBungalows; i++) {
            
            arrayCamping.add(new Bungalow(id));
            
            id++;
            
        }
    
    }
    
    public ArrayList <Parcela> getCamping() {
        
        return arrayCamping;
        
    }
    
}
