
package pkgGUI;

import java.io.*;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import java.util.ArrayList;
import java.time.LocalDateTime;

import pkgClasesLogica.*;
import pkgParametros.Param;
import static pkgParametros.Param.*;
import static pkgClasesFich.Ficheros.*;


public class mainCamping extends javax.swing.JFrame {

    JButton[] parcelas;
    
    Param parametros;
    
    Camping insCamp;
    
    ArrayList <Parcela> arrayCamp;
    
    
    
    public mainCamping() {
        
        initComponents();
        
        
        
        // Config Ventana
        
        setTitle("Camping");
        
        JFrame frame = this;
        
        setLocationRelativeTo(null);
        
        setResizable(false);
        
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            
            public void windowClosing(WindowEvent we) {
                
              int resp = JOptionPane.showConfirmDialog(frame, "¿Seguro que quieres salir?", "Salir", JOptionPane.OK_CANCEL_OPTION);
        
                if (resp == JOptionPane.OK_OPTION) {
            
                    guardarCamping(arrayCamp);
            
                    System.exit(0);
            
                }
            
            }
        
        });
        
        
        
                
        // Parametros
        
        parametros = new Param();
        
        if (new File("data/files/parametros.txt").exists() == false) {
            
            guardarParam(parametros);
            
        }
        
        else {
            
            cargarParam(parametros);
            
        } 
        
        
        
        
        // ArrayList Camping
        
        if (new File("data/files/arrayCamping.dat").exists() == false) {
            
            insCamp = new Camping();
            
            arrayCamp = insCamp.getCamping();
            
        }
        
        else {
            
            arrayCamp = cargarCamping();
            
        }        
        
        
        
        
        // Config Panel Parcelas
        
        parcelas = new JButton[totalParcelas];
        
        
        
        
        // Añadir Parcelas
        
        for (int i = 0; i < parcelas.length; i++) {
            
            int x = i + 1;
            
            parcelas[i] = new JButton();
            
            parcelas[i].addActionListener(new java.awt.event.ActionListener() {
 
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    
                    FActionPerformed(evt);
                
                }
                
            });
            
            parcelas[i].setName(Integer.toString(x - 1));
            
            parcelas[i].setText(String.valueOf(x));
            
            parcelas[i].setVerticalTextPosition(SwingConstants.CENTER);
            parcelas[i].setHorizontalTextPosition(SwingConstants.CENTER);
            
            if (arrayCamp.get(i).getOcupada() == false) {
                
                parcelas[i].setBackground(new Color(153, 217, 234));
                
            }
            
            else {
                
                parcelas[i].setBackground(new Color(255, 138, 132));
                
            }
            
            // Tiendas
            
            if (x >= 1 && x <= 10) {
            
                parcelas[i].setIcon(new javax.swing.ImageIcon("data/img/tienda.png"));
                
            }
            
            // Caravanas
            
            if (x >= 11 && x <= 30) {

                parcelas[i].setIcon(new javax.swing.ImageIcon("data/img/caravana.png"));
                
            }
            
            // Bungalows
            
            if (x >= 31 && x <= 60) {
            
                parcelas[i].setIcon(new javax.swing.ImageIcon("data/img/bungalow.png"));
                
            }
            
            panelParcelas.add(parcelas[i]);
            
        }
        
    }
    
    private void FActionPerformed(java.awt.event.ActionEvent evt) {
        
        cargarParam(parametros);
        
        int idParc = Integer.valueOf(((JButton) evt.getSource()).getName());
        
        Parcela parcela = arrayCamp.get(idParc);
        
        if (parcela.getOcupada() == false) {
            
            String dni = JOptionPane.showInputDialog(this, "Introduce el DNI");
            
            try {
                
                if (dni.length() == 9) {
                
                    int resp = JOptionPane.showConfirmDialog(this, "Estas seguro de elegir esta parcela???");

                    if (resp == JOptionPane.OK_OPTION) {

                        if (parcela instanceof Tienda) {
                            
                            int elect = JOptionPane.showConfirmDialog(this, "¿Quieres Electricidad?", null, JOptionPane.OK_CANCEL_OPTION);
        
                            if (elect == JOptionPane.OK_OPTION) {

                                ((Tienda) parcela).setElectricidad(true);

                            }

                            ((Tienda) parcela).checkIn(dni);

                            parcelas[idParc].setBackground(new Color(255, 138, 132));

                        }

                        if (parcela instanceof Caravana) {

                            ((Caravana) parcela).checkIn(dni);

                            parcelas[idParc].setBackground(new Color(255, 138, 132));

                        }

                        if (parcela instanceof Bungalow) {
                            
                            String pers = JOptionPane.showInputDialog(this, "Introduce la cantidad de Personas");
                            
                            try {
                                
                                int numPers = Integer.parseInt(pers);
                                
                                ((Bungalow) parcela).setPersonas(numPers);
                                
                                ((Bungalow) parcela).checkIn(dni);

                                parcelas[idParc].setBackground(new Color(255, 138, 132));
                                
                            } catch (NumberFormatException e) {
                                
                                if (e.getMessage().equals("null")) {
                                    
                                    JOptionPane.showMessageDialog(this, "Se ha cancelado la operación de registro");
                                    
                                }
                                
                                else {
                                    
                                    JOptionPane.showMessageDialog(this, "Se ha producido un error durante el registro. \n Revisa los datos introducidos.");
                                    
                                }
                                
                            }

                        }

                    }
                    
                    else {
                        
                        JOptionPane.showMessageDialog(this, "Se ha cancelado la operación de registro");
                        
                    }

                }

                else {

                    JOptionPane.showMessageDialog(this, "El DNI introducido no es válido");

                }
                
            } catch (NullPointerException e) {
                
                JOptionPane.showMessageDialog(this, "Se ha cancelado la operación de registro");
                
            }
            
        }
        
        else {
            
            double coste = 0;
            
            String dni = parcela.getDNI();
            LocalDateTime fechEntr = parcela.getFechaEntrada();
            
            if (parcela instanceof Tienda) {

                coste = ((Tienda) parcela).checkOut(parametros);
                
            }
            
            if (parcela instanceof Caravana) {
                
                coste = ((Caravana) parcela).checkOut(parametros);
                
            }
            
            if (parcela instanceof Bungalow) {
                
                coste = ((Bungalow) parcela).checkOut(parametros);
                
            }
            
            Facturacion(parcela, dni, fechEntr, coste);
            
            JOptionPane.showMessageDialog(this, "Coste de la instancia: " + coste);
            
            parcelas[idParc].setBackground(new Color(153, 217, 234));
            
        }
        
        guardarCamping(arrayCamp);
    
    }

    // NO MODIFICAR!
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTitulo = new javax.swing.JPanel();
        labelTitulo = new javax.swing.JLabel();
        panelParcelas = new javax.swing.JPanel();
        panelBotones = new javax.swing.JPanel();
        buttonAyuda = new javax.swing.JButton();
        buttonAbout = new javax.swing.JButton();
        buttonParam = new javax.swing.JButton();
        buttonSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelTitulo.setFont(new java.awt.Font("DejaVu Serif", 1, 36)); // NOI18N
        labelTitulo.setText("Camping");
        panelTitulo.add(labelTitulo);

        panelParcelas.setMinimumSize(new java.awt.Dimension(0, 300));
        panelParcelas.setLayout(new java.awt.GridLayout(10, 10));

        buttonAyuda.setText("Ayuda");
        buttonAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAyudaActionPerformed(evt);
            }
        });
        panelBotones.add(buttonAyuda);

        buttonAbout.setText("About");
        buttonAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAboutActionPerformed(evt);
            }
        });
        panelBotones.add(buttonAbout);

        buttonParam.setText("Parámetros");
        buttonParam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonParamActionPerformed(evt);
            }
        });
        panelBotones.add(buttonParam);

        buttonSalir.setText("Salir");
        buttonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSalirActionPerformed(evt);
            }
        });
        panelBotones.add(buttonSalir);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
                    .addComponent(panelParcelas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelParcelas, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAyudaActionPerformed
        
        JOptionPane.showMessageDialog(this, "Programa de Gestión de Camping: \n\n - Descripción: Programa que permite gestionar las entradas y salidas en las parcelas de un camping calculando \n los importes a pagar por los campistas según el tipo de alojamiento y los días de estancia. \n\n - Instrucciones: \n\n                - Las Parcelas Libres están en color azul. \n                - Las Parcelas Ocupadas están en color rojo. \n                - Hay 3 Tipos de Parcelas: Tiendas, Caravanas o Bungalows (Se pueden identificar por los iconos). \n                - Check-In: hay que pulsar en la parcela libre y cubrir los datos que se soliciten. \n                - Check-Out: hay que pulsar en la parcela ocupada y se mostrará el importe a pagar. \n\n - Otros: \n\n                - El estado del Camping se guarda en archivo propio para poder ser restaurado si se desea. \n                - Los Parámetros se pueden modificar accediendo a su propio archivo. \n                - Todos lo Facturado también se guardará en un archivo propio.");
        
    }//GEN-LAST:event_buttonAyudaActionPerformed

    private void buttonAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAboutActionPerformed
        
        JOptionPane.showMessageDialog(this, " - Autor: Gabriel Carballeira Paredes \n\n - Email: gabifp1721@gmail.com \n\n - Fecha de Inicio: Version: 30/04/2020 \n\n - Fecha de Finalización: 05/05/2020 \n\n - Versión: 2.3");
        
    }//GEN-LAST:event_buttonAboutActionPerformed

    private void buttonParamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonParamActionPerformed
        
        Runtime rs = Runtime.getRuntime();
        
        try {
            
            JOptionPane.showMessageDialog(this, "Los parámetros estan almacenados en: \n\n         /data/files/parametros.txt \n\n Puedes modificar el archivo antes de cerrar \n la ventana para cargar los nuevos parametros");
            
            rs.exec("notepad data\\files\\parametros.txt");
            
        } catch (Exception e) {
            
            System.out.println("Se ha producido un error abriendo el fichero parametros.txt");
            
        }
        
    }//GEN-LAST:event_buttonParamActionPerformed

    private void buttonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSalirActionPerformed
        
        int resp = JOptionPane.showConfirmDialog(this, "¿Seguro que quieres salir?", "Salir", JOptionPane.OK_CANCEL_OPTION);
        
        if (resp == JOptionPane.OK_OPTION) {
            
            guardarCamping(arrayCamp);
            
            System.exit(0);
            
        }
        
    }//GEN-LAST:event_buttonSalirActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(mainCamping.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainCamping.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainCamping.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainCamping.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainCamping().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAbout;
    private javax.swing.JButton buttonAyuda;
    private javax.swing.JButton buttonParam;
    private javax.swing.JButton buttonSalir;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelParcelas;
    private javax.swing.JPanel panelTitulo;
    // End of variables declaration//GEN-END:variables
}
