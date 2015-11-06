
package com.epissoft.sanguchito.vista.login;

import com.epissoft.sanguchito.controlador.empleadocontrolador.EmpleadoControlador;
import com.epissoft.sanguchito.controlador.usuariocontrolador.UsuarioControlador;
import com.epissoft.sanguchito.persistencia.Empleado;
import com.epissoft.sanguchito.vista.ManejoSesion;
import com.epissoft.sanguchito.vista.Sanguchito;
import com.epissoft.sanguchito.vista.utilitarios.BotonesComunes;
import com.epissoft.sanguchito.vista.menuprincipal.MenuAdministrador;
import com.epissoft.sanguchito.vista.ventas.Venta;
import java.awt.event.KeyEvent;
import java.util.Properties;
import javax.swing.InputMap;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

public class Login extends javax.swing.JInternalFrame {
    
    public Login() {
        initComponents();
        InputMap map = new InputMap();
        map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "pressed");
        map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "released");

        bIniciarSesion.setInputMap(0, map);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        bIniciarSesion = new javax.swing.JButton();
        bAsistencia = new javax.swing.JButton();
        tUsuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        iLogo = new javax.swing.JLabel();
        tPassword = new javax.swing.JPasswordField();
        bCerrar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1000, 744));
        setPreferredSize(new java.awt.Dimension(1000, 744));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(1000, 744));
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 744));

        bIniciarSesion.setBackground(new java.awt.Color(0, 51, 102));
        bIniciarSesion.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        bIniciarSesion.setForeground(new java.awt.Color(255, 255, 255));
        bIniciarSesion.setText("Iniciar sesión");
        bIniciarSesion.setAutoscrolls(true);
        bIniciarSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        bIniciarSesion.setSelected(true);
        bIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bIniciarSesionActionPerformed(evt);
            }
        });

        bAsistencia.setBackground(new java.awt.Color(0, 51, 102));
        bAsistencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/reloj.png"))); // NOI18N
        bAsistencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAsistenciaActionPerformed(evt);
            }
        });

        tUsuario.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel2.setText("Usuario:");
        jLabel2.setDoubleBuffered(true);

        jLabel3.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel3.setText("Contraseña :");

        iLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/sanguchito sin fondo.jpg"))); // NOI18N

        tPassword.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        bCerrar.setBackground(new java.awt.Color(0, 51, 102));
        bCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/close2.png"))); // NOI18N
        bCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(247, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(bIniciarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel3))
                                    .addGap(26, 26, 26)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(tUsuario)
                                        .addComponent(tPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(82, 82, 82)
                                .addComponent(iLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(297, 297, 297))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(bAsistencia, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(bCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bAsistencia, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(iLogo)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(32, 32, 32)
                .addComponent(bIniciarSesion)
                .addContainerGap(203, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bIniciarSesionActionPerformed
        String user = tUsuario.getText();
        String pass = tPassword.getText();
        Properties properties = System.getProperties();
        String ruta = properties.getProperty("user.dir");
        UsuarioControlador uc = new UsuarioControlador();
        boolean exist = uc.existeUser(user);
        if(!exist){
            JOptionPane.showMessageDialog(null, "El usuario " + user + " no existe.");            
        }
        else if(uc.verificarPassword(user, pass)){
            int cargo = uc.getCarUser(user);
            String Dni=uc.getDniUser(user);

            Empleado empl = EmpleadoControlador.buscarEmpleado(Dni);
            switch(cargo){
                case -1:
                    JOptionPane.showMessageDialog(rootPane, "El usuario no existe");
                    break;
                case -2:
                    JOptionPane.showMessageDialog(rootPane, "El usuario esta deshabilitado");
                    break;
                case -3:
                    JOptionPane.showMessageDialog(rootPane, "El empleado esta deshabiliado");
                    break;    
                case 1:
                    ManejoSesion.crearSesion(empl.getEmpApePat()+" "+empl.getEmpApeMat()+", "+empl.getEmpNom(),Dni,tUsuario.getText(), "Administrador",ruta);
                    Sanguchito.sanguchito.removeAll();
                    MenuAdministrador menu=new MenuAdministrador();
                    Sanguchito.sanguchito.add(menu);
                    try{
                        menu.setMaximum(true);
                    }catch(Exception e){
                    }
                    menu.show();
                    break;
                case 2:
                    ManejoSesion.crearSesion(empl.getEmpApePat()+" "+empl.getEmpApeMat()+", "+empl.getEmpNom(),Dni,tUsuario.getText(), "Cajero",ruta);
                    Sanguchito.sanguchito.removeAll();
                    Venta opciones=new Venta();
                    Sanguchito.sanguchito.add(opciones);
                    try{
                        opciones.setMaximum(true);
                    }catch(Exception e){
                    }
                    opciones.show();
                    break;
                case 3:
                    JOptionPane.showMessageDialog(rootPane, "Se logeo como almacenero");                                
                    break;
            }
        }
        else{
            JOptionPane.showMessageDialog(rootPane, "La contraseña no es correcta.");
        }  
    }//GEN-LAST:event_bIniciarSesionActionPerformed

    private void bAsistenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAsistenciaActionPerformed
        BotonesComunes.ingresoAsistencia();
    }//GEN-LAST:event_bAsistenciaActionPerformed

    private void bCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCerrarActionPerformed
        System.exit(0);
    }//GEN-LAST:event_bCerrarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAsistencia;
    private javax.swing.JButton bCerrar;
    private javax.swing.JButton bIniciarSesion;
    private javax.swing.JLabel iLogo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField tPassword;
    private javax.swing.JTextField tUsuario;
    // End of variables declaration//GEN-END:variables
}

