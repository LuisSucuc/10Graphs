
package graphs;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Bienvenido extends javax.swing.JFrame {

    public String path;
    
    /**
     *Constructor de la clase
     */
    public Bienvenido() {
        initComponents();
        btnGenerarGrafo.setEnabled(false);
    }
    
    /**
     * Limpia el path y deshabilita la generación del grafo
     */
    public void limpiar(){
        lblPath.setText("No seleccionado...");
        btnGenerarGrafo.setEnabled(false);
        path = "";
    }
    
    /**
     * Obtiene el path del archivo deseado
     */
    public void getPath() {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Selecciona un archivo");
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            path = chooser.getSelectedFile().toString();
            lblPath.setText(path);
            btnGenerarGrafo.setEnabled(true);
        } else {
            limpiar();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnGetArchivo = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnGenerarGrafo = new javax.swing.JButton();
        lblPath = new javax.swing.JLabel();
        btnLimpiar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("BIENVENIDO");

        btnGetArchivo.setText("Seleccionar Archivo");
        btnGetArchivo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGetArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetArchivoActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnGenerarGrafo.setText("Generar Grafo");
        btnGenerarGrafo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarGrafoActionPerformed(evt);
            }
        });

        lblPath.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblPath.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPath.setText("No seleccionado");

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPath, javax.swing.GroupLayout.PREFERRED_SIZE, 626, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(216, 216, 216)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnGenerarGrafo, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGetArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(279, 279, 279))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addGap(95, 95, 95)
                .addComponent(lblPath)
                .addGap(18, 18, 18)
                .addComponent(btnGetArchivo)
                .addGap(18, 18, 18)
                .addComponent(btnLimpiar)
                .addGap(16, 16, 16)
                .addComponent(btnGenerarGrafo)
                .addGap(18, 18, 18)
                .addComponent(btnSalir)
                .addContainerGap(84, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGetArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetArchivoActionPerformed
        getPath();
    }//GEN-LAST:event_btnGetArchivoActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnGenerarGrafoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarGrafoActionPerformed
        Lectura leer = new Lectura();
        try {
            leer.leerArchivoYCrear(path);
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "El archivo no tiene el formato esperado. \nSeleccione otro archivo", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
             limpiar();
        }
    }//GEN-LAST:event_btnGenerarGrafoActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

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
            java.util.logging.Logger.getLogger(Bienvenido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Bienvenido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Bienvenido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Bienvenido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Bienvenido().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerarGrafo;
    private javax.swing.JButton btnGetArchivo;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblPath;
    // End of variables declaration//GEN-END:variables
}
