
package pkgParametros;


public class Param {
    
    // Parcelas
    
    public static int totalParcelas = 60;
    public static int totalTiendas = 10;
    public static int totalCaravanas = 20;
    public static int totalBungalows = 30;

    // Tienda
    
    public int T_precioDia = 20;
    public int T_precioElecDia = 1;
    public double T_desMas7dias = 0.10; // Descuento para estancias de mas de 7 dias

    // Caravana
    
    public int C_precioDia_Agosto = 40;
    public int C_precioDia_RestoMeses = 30;
    public int C_diasEstanciaMin = 10; // Dias minimos de estancia. Hasta que se cumplan no se puede hacer Check-out

    // Bungalow
    
    public int B_precioDia_Adultos = 20;
    public int B_precioDia_Ninos = 0;
    public double B_porcRecargo = 0.20; // Porcentaje de recargo si se queda 1 o 2 noches
    
}
