
package pkgClasesLogica;

import pkgParametros.Param;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


public class Bungalow extends Parcela {
    
    int personas;

    public Bungalow(int id) {
        
        super(id);
        personas = 1;
        
    }
    
    public void setPersonas(int pers) {
        
        personas = pers;
        
    }
    
    @Override
    public double checkOut(Param param) {
        
        double total = 0;
        
        if (ocupada == true) {
            
            int diasTranscurridos = (int) ChronoUnit.DAYS.between(fechaEntrada, LocalDateTime.now());
            
            if (diasTranscurridos > 2) {
                
                total = (param.B_precioDia_Adultos * personas) * diasTranscurridos;
                
            }
            
            else {
                
                total = ((param.B_precioDia_Adultos * personas) * diasTranscurridos) + (param.B_precioDia_Adultos * param.B_porcRecargo);
                
            }
            
            super.checkOut(param);
            
            return total;
            
        }
        
        else {
            
            return -1;
            
        }
        
    }
    
}
