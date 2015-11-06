
package com.epissoft.sanguchito.vista.almacencategoria;

import com.epissoft.sanguchito.controlador.categoriaproductoalmacencontrolador.MostrarCategoriaProductoAlmacenControlador;
import com.epissoft.sanguchito.persistencia.CategoriaProductoAlmacen;
import com.epissoft.sanguchito.vista.ManejoSesion;
import com.epissoft.sanguchito.vista.Sanguchito;
import com.epissoft.sanguchito.vista.almacen.SalidaAlmacen;
import com.epissoft.sanguchito.vista.utilitarios.BotonesComunes;
import com.epissoft.sanguchito.vista.menuprincipal.MenuAdministrador;
import com.epissoft.sanguchito.vista.utilitarios.ActualizarReloj;
import com.epissoft.sanguchito.vista.utilitarios.CargarFecha;
import java.util.List;
import javax.swing.JButton;

public class ListaCategoriasAlmacen extends javax.swing.JInternalFrame{

    
    public ListaCategoriasAlmacen() {
        listaCategorias();
        initComponents();
        tUsuario.setText(ManejoSesion.getUsuario());
        ActualizarReloj actualizar=new ActualizarReloj(tHora);
        CargarFecha.cargarFechaActual(tFecha);
    }
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
        bAgregarCat = new javax.swing.JButton();
        bAtras = new javax.swing.JButton();
        bSalida = new javax.swing.JButton();

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
        lTitulo.setText("  LISTADO DE CATEGORÍAS");
        lTitulo.setToolTipText("");
        lTitulo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 102), 1, true));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/logo.png"))); // NOI18N

        bAgregarCat.setBackground(new java.awt.Color(0, 51, 102));
        bAgregarCat.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        bAgregarCat.setForeground(new java.awt.Color(255, 255, 255));
        bAgregarCat.setText("+ Agregar categoria");
        bAgregarCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAgregarCatActionPerformed(evt);
            }
        });

        bAtras.setBackground(new java.awt.Color(0, 51, 102));
        bAtras.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        bAtras.setForeground(new java.awt.Color(255, 255, 255));
        bAtras.setText("Atras");
        bAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAtrasActionPerformed(evt);
            }
        });

        bSalida.setBackground(new java.awt.Color(0, 51, 102));
        bSalida.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        bSalida.setForeground(new java.awt.Color(255, 255, 255));
        bSalida.setText("Salida de productos");
        bSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSalidaActionPerformed(evt);
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
                .addGap(212, 212, 212)
                .addComponent(bAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(bAgregarCat, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(bSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGap(474, 474, 474)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bAgregarCat, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(83, 83, 83))
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

    private void bAgregarCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAgregarCatActionPerformed
        Sanguchito.sanguchito.removeAll();
        AgregarCategoriaAlmacen agregar=new AgregarCategoriaAlmacen();
        Sanguchito.sanguchito.add(agregar);
        try{
            agregar.setMaximum(true);
        }catch(Exception e){
            
        }
        agregar.show();
    }//GEN-LAST:event_bAgregarCatActionPerformed

    private void bAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAtrasActionPerformed
        Sanguchito.sanguchito.removeAll();
        MenuAdministrador menu=new MenuAdministrador();
        Sanguchito.sanguchito.add(menu);
        try{
            menu.setMaximum(true);
        }catch(Exception e){
            
        }
        menu.show();
    }//GEN-LAST:event_bAtrasActionPerformed

    private void bSalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSalidaActionPerformed
        Sanguchito.sanguchito.removeAll();
        SalidaAlmacen menu=new SalidaAlmacen();
        Sanguchito.sanguchito.add(menu);
        try{
            menu.setMaximum(true);
        }catch(Exception e){
            
        }
        menu.show();
    }//GEN-LAST:event_bSalidaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAgregarCat;
    private javax.swing.JButton bAsistencia;
    private javax.swing.JButton bAtras;
    private javax.swing.JButton bCerrarSesion;
    private javax.swing.JButton bSalida;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lBienvenida;
    private javax.swing.JLabel lFecha;
    private javax.swing.JLabel lHora;
    private javax.swing.JLabel lTitulo;
    private javax.swing.JLabel lUsuario;
    private javax.swing.JTextField tFecha;
    private javax.swing.JTextField tHora;
    private javax.swing.JTextField tUsuario;
    // End of variables declaration//GEN-END:variables

    
    private void listaCategorias(){
        List<CategoriaProductoAlmacen> p= MostrarCategoriaProductoAlmacenControlador.listaCategoriaProductoAlmacen();
        setLayout(null);
        JButton aBu;
        for (int i = 0; i < p.size(); i++) {
            aBu = new JButton(p.get(i).getCatProdAlmDes());
            aBu.setBounds(45 + (i % 5) * 170, 200 + 110 * (int) (i / 5), 160, 100);
            try{
                aBu.setIcon(new javax.swing.ImageIcon("Imagenes/" + p.get(i).getCatProdAlmImg()));    
            }catch(Exception e){
                aBu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/sprodAlmDefault.png")));    
            }
            aBu.addActionListener(new FuncionamientoBotonesListaCategoriasAlmacen(p.get(i)));
            add(aBu);
        }
    }
}
