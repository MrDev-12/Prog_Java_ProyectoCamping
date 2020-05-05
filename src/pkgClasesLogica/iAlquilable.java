
package pkgClasesLogica;

import pkgParametros.Param;


public interface iAlquilable {
    
    // Check-in marca la parcela como ocupada

    boolean checkIn(String dniHuesped);
    
    // Check-out marca la parcela como libre y
    // calcula el importe a pagar en función de los parámetros del camping

    double checkOut(Param param);
    
}
