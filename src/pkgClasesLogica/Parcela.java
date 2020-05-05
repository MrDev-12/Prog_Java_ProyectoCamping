
package pkgClasesLogica;

import pkgParametros.Param;
import java.io.Serializable;
import java.time.LocalDateTime;


public abstract class Parcela implements iAlquilable, Serializable {
    
    int ID;
    String DNI;
    LocalDateTime fechaEntrada;
    boolean ocupada;

    Parcela(int id) {
        
        ID = id;
        ocupada = false;
        
    }
    
    @Override
    public boolean checkIn(String dniHuesped) {
        
        if (ocupada == false) {
            
            ocupada = true;
            
            DNI = dniHuesped;
            
            fechaEntrada = LocalDateTime.now();
            
            return true;
            
        }
        
        else {
            
            return false;
            
        }
        
    }

    @Override
    public double checkOut(Param param) {
        
        ocupada = false;
        
        DNI = null;
        
        fechaEntrada = null;
        
        return 0;
        
    }
    
    public boolean getOcupada() {
        
        return ocupada;
        
    }

    public int getID() {
        
        return ID;
        
    }

    public String getDNI() {
        
        return DNI;
        
    }

    public LocalDateTime getFechaEntrada() {
        
        return fechaEntrada;
        
    }

    @Override
    public String toString() {
        return "Parcela{" + "ID=" + ID + ", DNI=" + DNI + ", fechaEntrada=" + fechaEntrada + ", ocupada=" + ocupada + '}';
    }

}
