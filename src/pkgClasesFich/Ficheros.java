
package pkgClasesFich;

import java.io.*;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import pkgClasesLogica.*;
import pkgParametros.Param;
import java.time.LocalDateTime;


public class Ficheros {
    
    static Properties config = new Properties();
    
    
    public static void guardarCamping(ArrayList <Parcela> arrayCamp) {
        
        try (
                
            FileOutputStream fos = new FileOutputStream("data/files/arrayCamping.dat");

            BufferedOutputStream bos = new BufferedOutputStream(fos);

            ObjectOutputStream oos = new ObjectOutputStream(bos);
                
        ) {
            
            for (int i = 0; i < arrayCamp.size(); i++) {
            
                oos.writeObject(arrayCamp.get(i));
            
            }
            
        } catch (IOException ex) {
            
            System.out.println("Se produjo un error guardando el Camping");
            
            System.out.println("Error: " + ex.getMessage());
            
        }
        
    }
    
    public static ArrayList <Parcela> cargarCamping() {
        
        ArrayList <Parcela> arCamp = new ArrayList<>();
        
        boolean eof = false;
        
        File fichero = new File("data/files/arrayCamping.dat");
        
        try (
                
            FileInputStream fis = new FileInputStream(fichero);

            BufferedInputStream bufis = new BufferedInputStream(fis);

            ObjectInputStream ois = new ObjectInputStream(bufis);
                
        ) {
            
            while (eof == false) {
                
                arCamp.add((Parcela) ois.readObject());
                
            }
            
        } catch (EOFException e) {
            
            eof = true;
            
        } catch (IOException ex) {
            
            System.out.println("Se produjo un error cargando el Camping");
            
            System.out.println("Prueba a borrar el fichero arrayCamping.dat y vuelve a intentarlo");
            
            System.out.println("Error: " + ex.getMessage());
            
        } catch (ClassNotFoundException exc) {
            
            Logger.getLogger(Ficheros.class.getName()).log(Level.SEVERE, null, exc);
            
        }
        
        return arCamp;
        
    }
    
    public static void Facturacion(Parcela parcela, String dni, LocalDateTime fechEntr, double imp) {
        
        File f = new File("data/files/facturado.csv");
        
        try (
            
            FileWriter fw = new FileWriter(f, true);
            
            BufferedWriter bfw = new BufferedWriter(fw);
        
        ) {
            
            bfw.write(String.valueOf(parcela.getID())); bfw.write(";");

            bfw.write(dni); bfw.write(";");
            
            if (parcela instanceof Tienda) {
                
                bfw.write("Tienda"); bfw.write(";");
                
            }
            
            if (parcela instanceof Caravana) {
                
                bfw.write("Caravana"); bfw.write(";");
                
            }
            
            if (parcela instanceof Bungalow) {
                
                bfw.write("Bungalow"); bfw.write(";");
                
            }

            bfw.write(String.valueOf(fechEntr)); bfw.write(";");

            bfw.write(String.valueOf(LocalDateTime.now())); bfw.write(";");

            bfw.write(String.valueOf(imp)); bfw.write(";");

            bfw.newLine();

        } catch (IOException ex) {

            System.out.println("Se produjo un error guardando los datos de Facturación");
            
            System.out.println("Error: " + ex.getMessage());

        }
        
    }
    
    public static void guardarParam(Param param) {
        
        // Tienda
        
        config.setProperty("T_precioDia", String.valueOf(param.T_precioDia));
        
        config.setProperty("T_precioElecDia", String.valueOf(param.T_precioElecDia));
        
        config.setProperty("T_desMas7dias", String.valueOf(param.T_desMas7dias));
        
        // Caravana
        
        config.setProperty("C_precioDia_Agosto", String.valueOf(param.C_precioDia_Agosto));
        
        config.setProperty("C_precioDia_RestoMeses", String.valueOf(param.C_precioDia_RestoMeses));
        
        config.setProperty("C_diasEstanciaMin", String.valueOf(param.C_diasEstanciaMin));
        
        // Bungalow
        
        config.setProperty("B_precioDia_Adultos", String.valueOf(param.B_precioDia_Adultos));
        
        config.setProperty("B_precioDia_Ninos", String.valueOf(param.B_precioDia_Ninos));
        
        config.setProperty("B_porcRecargo", String.valueOf(param.B_porcRecargo));
        
        try {
            
            config.store(new FileOutputStream("data/files/parametros.txt"), "Fichero de Parámetros del Camping");
            
        } catch (IOException ioe) {
            
            System.out.println("Se produjo un error guardando los Parametros");
            
            System.out.println("Error: " + ioe.getMessage());
            
        }
        
    }
    
    public static void cargarParam(Param param) {
        
        try {
            
            config.load(new FileInputStream("data/files/parametros.txt"));
            
            // Tienda
            
            param.T_precioDia = Integer.valueOf(config.getProperty("T_precioDia"));
            
            param.T_precioElecDia = Integer.valueOf(config.getProperty("T_precioElecDia"));
            
            param.T_desMas7dias = Double.valueOf(config.getProperty("T_desMas7dias"));
            
            // Caravana
            
            param.C_precioDia_Agosto = Integer.valueOf(config.getProperty("C_precioDia_Agosto"));
            
            param.C_precioDia_RestoMeses = Integer.valueOf(config.getProperty("C_precioDia_RestoMeses"));
            
            param.C_diasEstanciaMin = Integer.valueOf(config.getProperty("C_diasEstanciaMin"));
            
            // Bungalow
            
            param.B_precioDia_Adultos = Integer.valueOf(config.getProperty("B_precioDia_Adultos"));
            
            param.B_precioDia_Ninos = Integer.valueOf(config.getProperty("B_precioDia_Ninos"));
            
            param.B_porcRecargo = Double.valueOf(config.getProperty("B_porcRecargo"));
            
        } catch (IOException ioe) {
            
            System.out.println("Se produjo un error cargando los Parámetros");
            
            System.out.println("Comprueba que solo hayas modificado los valores de las Variables");
            
            System.out.println("Si se modificó el nombre de alguna variable, devuelvalo al estado anterior o elimine el fichero parametros.txt");
            
            System.out.println("Error: " + ioe.getMessage());
            
        }
        
    }
    
}
