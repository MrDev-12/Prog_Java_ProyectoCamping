
package pkgClasesLogica;

import pkgParametros.Param;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


public class Tienda extends Parcela {
    
    boolean electricidad;

    public Tienda(int id) {
        
        super(id);
        electricidad = false;
        
    }
    
    public void setElectricidad(boolean elect) {
        
        electricidad = elect;
        
    }
    
    @Override
    public double checkOut(Param param) {
        
        double total = 0;
        
        if (ocupada == true) {
            
            int diasTranscurridos = (int) ChronoUnit.DAYS.between(fechaEntrada, LocalDateTime.now());
            
            if (electricidad == true) {
                
                total = (param.T_precioDia + param.T_precioElecDia) * diasTranscurridos;
            
                if (diasTranscurridos > 7) {

                    total = total - (total * param.T_desMas7dias);

                }
                
            }
            
            else {
                
                total = param.T_precioDia * diasTranscurridos;
            
                if (diasTranscurridos > 7) {
                
                    total = total - (total * param.T_desMas7dias);
                
                }
                
            }
            
            super.checkOut(param);
            
            return total;
            
        }
        
        else {
            
            return -1;
            
        }
        
    }
    
}
