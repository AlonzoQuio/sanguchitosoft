package com.epissoft.sanguchito.vista.turno;

import com.epissoft.sanguchito.controlador.turnocontrolador.TurnoControlador;
import com.epissoft.sanguchito.persistencia.Turno;
import com.epissoft.sanguchito.vista.ManejoSesion;
import com.epissoft.sanguchito.vista.Sanguchito;
import com.epissoft.sanguchito.vista.utilitarios.ActualizarReloj;
import com.epissoft.sanguchito.vista.utilitarios.BotonesComunes;
import com.epissoft.sanguchito.vista.utilitarios.CargarFecha;
import javax.swing.JOptionPane;

public class ActualizarTurno extends javax.swing.JInternalFrame {
    private Turno turno;
    public ActualizarTurno(Turno t) {
        initComponents();
        tUsuario.setText(ManejoSesion.getUsuario());
        ActualizarReloj actualizar=new ActualizarReloj(tHora);
        CargarFecha.cargarFechaActual(tFecha);
        turno=t;
        tDescripcion.setText(turno.getTurDes());
        hInicio.setText(turno.getTurHorIni().getHours()+":"+turno.getTurHorIni().getMinutes());
        hFin.setText(turno.getTurHorFin().getHours()+":"+turno.getTurHorFin().getMinutes());
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
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tDescripcion = new javax.swing.JTextField();
        bCancelar = new javax.swing.JButton();
        bAgregar = new javax.swing.JButton();
        bLimpiar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        hInicio = new javax.swing.JFormattedTextField();
        hFin = new javax.swing.JFormattedTextField();

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
        tHora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tHoraActionPerformed(evt);
            }
        });

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
        lTitulo.setText("  REGISTRAR NUEVO TURNO");
        lTitulo.setToolTipText("");
        lTitulo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 102), 1, true));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/logo.png"))); // NOI18N

        jLabel9.setFont(new java.awt.Font("Cambria", 0, 24)); // NOI18N
        jLabel9.setText("Descripción :");

        jLabel8.setFont(new java.awt.Font("Cambria", 0, 24)); // NOI18N
        jLabel8.setText("<html><p>Hora de Inicio :</p><p>23:00</p></html>");

        tDescripcion.setFont(new java.awt.Font("Cambria", 0, 24)); // NOI18N

        bCancelar.setBackground(new java.awt.Color(0, 51, 102));
        bCancelar.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        bCancelar.setForeground(new java.awt.Color(255, 255, 255));
        bCancelar.setText("Cancelar");
        bCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCancelarActionPerformed(evt);
            }
        });

        bAgregar.setBackground(new java.awt.Color(0, 51, 102));
        bAgregar.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        bAgregar.setForeground(new java.awt.Color(255, 255, 255));
        bAgregar.setText("Guardar");
        bAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAgregarActionPerformed(evt);
            }
        });

        bLimpiar.setBackground(new java.awt.Color(0, 51, 102));
        bLimpiar.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        bLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        bLimpiar.setText("Restaurar");
        bLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLimpiarActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Cambria", 0, 24)); // NOI18N
        jLabel10.setText("<html><p>Hora de Fin :</p><p>23:00</p></html>");

        hInicio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getTimeInstance(java.text.DateFormat.SHORT))));
        hInicio.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        hInicio.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N

        hFin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getTimeInstance(java.text.DateFormat.SHORT))));
        hFin.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        hFin.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N

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
                .addGap(122, 122, 122)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(73, 73, 73)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(hInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hFin, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(123, 123, 123)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addGap(91, 91, 91)
                            .addComponent(tDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(51, 51, 51)
                            .addComponent(bLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(45, 45, 45)
                            .addComponent(bAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(47, 47, 47)
                            .addComponent(bCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(152, Short.MAX_VALUE)))
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(hInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(hFin, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(215, 215, 215)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(237, 237, 237)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(216, 216, 216)))
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
                .addGap(0, 56, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void bCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelarActionPerformed
        atras();
    }//GEN-LAST:event_bCancelarActionPerformed

    private void bAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAgregarActionPerformed
        
        String resultado = new TurnoControlador().insertarTurno(tDescripcion.getText(),hInicio.getText(),hFin.getText());
        if(resultado.equals("CORRECTO")){
            atras();
        }else{
            JOptionPane.showMessageDialog(null,resultado,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_bAgregarActionPerformed

    private void bLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLimpiarActionPerformed
        tDescripcion.setText(turno.getTurDes());
        hInicio.setText(turno.getTurHorIni().getHours()+":"+turno.getTurHorIni().getMinutes());
        hFin.setText(turno.getTurHorFin().getHours()+":"+turno.getTurHorFin().getMinutes());
    }//GEN-LAST:event_bLimpiarActionPerformed

    private void tHoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tHoraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tHoraActionPerformed
    private void atras(){
        Sanguchito.sanguchito.removeAll();
        ListaTurnos turnos=new ListaTurnos();
        Sanguchito.sanguchito.add(turnos);
        try{
            turnos.setMaximum(true);
        }catch(Exception e){
            
        }
        turnos.show();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAgregar;
    private javax.swing.JButton bAsistencia;
    private javax.swing.JButton bCancelar;
    private javax.swing.JButton bCerrarSesion;
    private javax.swing.JButton bLimpiar;
    private javax.swing.JFormattedTextField hFin;
    private javax.swing.JFormattedTextField hInicio;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lBienvenida;
    private javax.swing.JLabel lFecha;
    private javax.swing.JLabel lHora;
    private javax.swing.JLabel lTitulo;
    private javax.swing.JLabel lUsuario;
    public javax.swing.JTextField tDescripcion;
    private javax.swing.JTextField tFecha;
    private javax.swing.JTextField tHora;
    private javax.swing.JTextField tUsuario;
    // End of variables declaration//GEN-END:variables

}
