
package com.epissoft.sanguchito.vista.empleados;

import com.epissoft.sanguchito.controlador.usuariocontrolador.UsuarioControlador;
import com.epissoft.sanguchito.vista.Sanguchito;
import com.epissoft.sanguchito.vista.utilitarios.BotonesComunes;
import javax.swing.JOptionPane;

public class RegistrarUsuarios extends javax.swing.JInternalFrame {
    

    /**
     * Creates new form MenuAdministrador
     */
    public RegistrarUsuarios() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lBienvenida = new javax.swing.JLabel();
        lUsuario = new javax.swing.JLabel();
        tUsuario = new javax.swing.JTextField();
        lHora = new javax.swing.JLabel();
        tHora = new javax.swing.JTextField();
        lFecha = new javax.swing.JLabel();
        tFecha = new javax.swing.JTextField();
        bAsistencia = new javax.swing.JButton();
        bCerrarSesion = new javax.swing.JButton();
        lTitulo = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        combo_tipo = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        text_dni = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        text_usu_nombre = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        text_usu_pass1 = new javax.swing.JPasswordField();
        jLabel10 = new javax.swing.JLabel();
        text_usu_pass2 = new javax.swing.JPasswordField();
        boton_guardar = new javax.swing.JButton();
        boton_cancelar = new javax.swing.JButton();

        setRequestFocusEnabled(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 186));

        lBienvenida.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        lBienvenida.setForeground(new java.awt.Color(0, 51, 102));
        lBienvenida.setText("Bienvenido  a  SanGuchitoSoft");

        lUsuario.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        lUsuario.setForeground(new java.awt.Color(0, 51, 102));
        lUsuario.setText(" Usuario :");

        tUsuario.setEditable(false);
        tUsuario.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        tUsuario.setText("Sr. Reaño");
        tUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tUsuarioActionPerformed(evt);
            }
        });

        lHora.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        lHora.setForeground(new java.awt.Color(0, 51, 102));
        lHora.setText("Hora :");

        tHora.setEditable(false);
        tHora.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        tHora.setText("12:23 P.M");

        lFecha.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        lFecha.setForeground(new java.awt.Color(0, 51, 102));
        lFecha.setText("Fecha : ");

        tFecha.setEditable(false);
        tFecha.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        tFecha.setText("07 de Diciembre del 2013");
        tFecha.setPreferredSize(new java.awt.Dimension(155, 28));
        tFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tFechaActionPerformed(evt);
            }
        });

        bAsistencia.setBackground(new java.awt.Color(0, 51, 102));
        bAsistencia.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        bAsistencia.setForeground(new java.awt.Color(255, 255, 255));
        bAsistencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/reloj.png"))); // NOI18N
        bAsistencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAsistenciaActionPerformed(evt);
            }
        });

        bCerrarSesion.setBackground(new java.awt.Color(0, 51, 102));
        bCerrarSesion.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        bCerrarSesion.setForeground(new java.awt.Color(255, 255, 255));
        bCerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/logout.png"))); // NOI18N
        bCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCerrarSesionActionPerformed(evt);
            }
        });

        lTitulo.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        lTitulo.setForeground(new java.awt.Color(0, 51, 102));
        lTitulo.setText("  REGISTRAR USUARIOS");
        lTitulo.setToolTipText("");
        lTitulo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 102), 1, true));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/logo.png"))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel4.setText("INFORMACIÓN");

        jLabel12.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        jLabel12.setText("Tipo Usuario");

        combo_tipo.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        combo_tipo.setForeground(new java.awt.Color(255, 255, 255));
        combo_tipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Administrador", "Cajero" }));
        combo_tipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_tipoActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        jLabel11.setText("DNI");

        text_dni.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        jLabel8.setText("Nombre de Usuario");

        text_usu_nombre.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        jLabel9.setText("Contraseña");

        text_usu_pass1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        jLabel10.setText("Repita Contraseña");

        text_usu_pass2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        boton_guardar.setBackground(new java.awt.Color(0, 51, 102));
        boton_guardar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        boton_guardar.setForeground(new java.awt.Color(255, 255, 255));
        boton_guardar.setText("Guardar");
        boton_guardar.setIconTextGap(20);
        boton_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_guardarActionPerformed(evt);
            }
        });

        boton_cancelar.setBackground(new java.awt.Color(0, 51, 102));
        boton_cancelar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        boton_cancelar.setForeground(new java.awt.Color(255, 255, 255));
        boton_cancelar.setText("Cancelar");
        boton_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_cancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 916, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel15)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 3, Short.MAX_VALUE)
                                .addComponent(lUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lHora, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tHora, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lBienvenida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(2, 2, 2)
                                .addComponent(bAsistencia, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(bCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(28, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(404, 404, 404)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addGap(31, 31, 31))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addGap(94, 94, 94)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(35, 35, 35)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel12)
                                .addComponent(jLabel11))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(boton_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(boton_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(combo_tipo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(text_dni)
                        .addComponent(text_usu_nombre, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(text_usu_pass1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(text_usu_pass2, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(257, 257, 257))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(bCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bAsistencia, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(lBienvenida)
                                .addGap(36, 36, 36)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tHora, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tUsuario)
                                .addComponent(lFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lHora, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel15))
                .addGap(11, 11, 11)
                .addComponent(lTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(combo_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(text_dni, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(text_usu_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(text_usu_pass1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(text_usu_pass2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(boton_guardar)
                    .addComponent(boton_cancelar))
                .addGap(50, 50, 50))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 973, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 744, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void boton_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_cancelarActionPerformed
         // TODO add your handling code here:
        atras();
    }//GEN-LAST:event_boton_cancelarActionPerformed

    private void boton_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_guardarActionPerformed
       // TODO add your handling code here:
        
        String dni = text_dni.getText();
        String nom_usu = text_usu_nombre.getText();
        String pass1 = text_usu_pass1.getText();
        String pass2 = text_usu_pass2.getText();
        String tipo = combo_tipo.getSelectedItem().toString();//tipo.toString();
        UsuarioControlador uc = new UsuarioControlador();
        if(!pass2.equals(pass1)){
            JOptionPane.showMessageDialog(this, "Las contrasemas no coinciden");
        }
        else{
            String resultado=uc.insertarUsuario(dni, nom_usu,tipo ,pass1);
            if(resultado.equals("CORRECTO")){
                atras();
            }else{
                JOptionPane.showMessageDialog(this,resultado);  
            }
            
        }          
    }//GEN-LAST:event_boton_guardarActionPerformed

    private void combo_tipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_tipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_tipoActionPerformed

    private void bCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCerrarSesionActionPerformed
        BotonesComunes.deslogear();
    }//GEN-LAST:event_bCerrarSesionActionPerformed

    private void bAsistenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAsistenciaActionPerformed
        BotonesComunes.ingresoAsistencia();
    }//GEN-LAST:event_bAsistenciaActionPerformed

    private void tFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tFechaActionPerformed

    private void tUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tUsuarioActionPerformed
  private String dni;
    private void atras(){
        Sanguchito.sanguchito.removeAll();
        BuscarUsuario empleado = new BuscarUsuario();
        Sanguchito.sanguchito.add(empleado);
        try{
            empleado.setMaximum(true);
        }catch(Exception e){            
        }
        empleado.show();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAsistencia;
    private javax.swing.JButton bCerrarSesion;
    private javax.swing.JButton boton_cancelar;
    private javax.swing.JButton boton_guardar;
    private javax.swing.JComboBox combo_tipo;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lBienvenida;
    private javax.swing.JLabel lFecha;
    private javax.swing.JLabel lHora;
    private javax.swing.JLabel lTitulo;
    private javax.swing.JLabel lUsuario;
    private javax.swing.JTextField tFecha;
    private javax.swing.JTextField tHora;
    private javax.swing.JTextField tUsuario;
    private javax.swing.JTextField text_dni;
    private javax.swing.JTextField text_usu_nombre;
    private javax.swing.JPasswordField text_usu_pass1;
    private javax.swing.JPasswordField text_usu_pass2;
    // End of variables declaration//GEN-END:variables

}