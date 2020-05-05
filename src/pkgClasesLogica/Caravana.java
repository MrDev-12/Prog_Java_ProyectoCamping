
package pkgClasesLogica;

import pkgParametros.Param;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


public class Caravana extends Parcela {

    public Caravana(int id) {
        
        super(id);
        
    }
    
    @Override
    public double checkOut(Param param) {
        
        double total = 0;
        
        if (ocupada == true) {
            
            int diasTranscurridos = (int) ChronoUnit.DAYS.between(fechaEntrada, LocalDateTime.now());
            
            if (diasTranscurridos > param.C_diasEstanciaMin) {
                
                if (fechaEntrada.getDayOfMonth() == 8) {   // Si el mes es Agosto
                
                    total = param.C_precioDia_Agosto * diasTranscurridos;
                
                }
                
                else {
                    
                    total = param.C_precioDia_RestoMeses * diasTranscurridos;
                    
                }
                
                super.checkOut(param);
            
                return total;
                
            }
            
            else {
                
                return 0;   // Devuelve 0 si no cumple el minimo de dias
                
            }
            
        }
        
        else {
            
            return -1;
            
        }
        
    }
    
}
