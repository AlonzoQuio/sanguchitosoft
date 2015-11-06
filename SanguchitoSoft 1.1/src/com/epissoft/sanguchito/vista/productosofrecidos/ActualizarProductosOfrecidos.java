/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epissoft.sanguchito.vista.productosofrecidos;

import com.epissoft.sanguchito.controlador.categoriaproductocontrolador.CategoriaProductoControlador;
import com.epissoft.sanguchito.controlador.productocontrolador.ActualizarProductoControlador;
import com.epissoft.sanguchito.controlador.utilitarios.ImageResizer;
import com.epissoft.sanguchito.persistencia.CategoriaProducto;
import com.epissoft.sanguchito.persistencia.Producto;
import com.epissoft.sanguchito.vista.ManejoSesion;
import com.epissoft.sanguchito.vista.Sanguchito;
import com.epissoft.sanguchito.vista.utilitarios.BotonesComunes;
import com.epissoft.sanguchito.vista.utilitarios.ActualizarReloj;
import com.epissoft.sanguchito.vista.utilitarios.CargarFecha;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;


public class ActualizarProductosOfrecidos extends javax.swing.JInternalFrame {
    
    private File[] fotos;
    private Producto producto;
    private CategoriaProducto categoria;
    private List<CategoriaProducto> cats;
    public ActualizarProductosOfrecidos(Producto prod,CategoriaProducto cat) {
        initComponents();
        tUsuario.setText(ManejoSesion.getUsuario());
        ActualizarReloj actualizar=new ActualizarReloj(tHora);
        CargarFecha.cargarFechaActual(tFecha);
        producto=prod;
        categoria=cat;
        tNombre.setText(producto.getProDes());
        tPrecFijo.setText(""+producto.getProPreSol());
        tPrecVenta.setText(""+producto.getProPreCar());
        cats=CategoriaProductoControlador.listaCategoriaProducto();
        for(int i=0;i<cats.size();i++){
            cCategoria.addItem(""+cats.get(i).getCatProdDes());
        }
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
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tNombre = new javax.swing.JTextField();
        tPrecFijo = new javax.swing.JTextField();
        tPrecVenta = new javax.swing.JTextField();
        jTImagen = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        imgCategoria = new javax.swing.JDesktopPane();
        jLabel4 = new javax.swing.JLabel();
        bCancelar = new javax.swing.JButton();
        bLimpiar = new javax.swing.JButton();
        bAgregar = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        cCategoria = new javax.swing.JComboBox();

        setPreferredSize(new java.awt.Dimension(1000, 744));
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
        lTitulo.setText("  ACTUALIZAR PRODUCTO OFRECIDO");
        lTitulo.setToolTipText("");
        lTitulo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 102), 1, true));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/logo.png"))); // NOI18N

        jLabel11.setFont(new java.awt.Font("Cambria", 0, 24)); // NOI18N
        jLabel11.setText("Nombre del Producto:");

        jLabel10.setFont(new java.awt.Font("Cambria", 0, 24)); // NOI18N
        jLabel10.setText("Precio Fijo (S/. ) : ");

        jLabel9.setFont(new java.awt.Font("Cambria", 0, 24)); // NOI18N
        jLabel9.setText("Precio Venta (S/. ) : ");

        jLabel8.setFont(new java.awt.Font("Cambria", 0, 24)); // NOI18N
        jLabel8.setText("Imagen :");

        tNombre.setFont(new java.awt.Font("Cambria", 0, 24)); // NOI18N

        tPrecFijo.setFont(new java.awt.Font("Cambria", 0, 24)); // NOI18N

        tPrecVenta.setFont(new java.awt.Font("Cambria", 0, 24)); // NOI18N

        jTImagen.setFont(new java.awt.Font("Cambria", 0, 24)); // NOI18N

        jButton6.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        jButton6.setText("Seleccionar imagen");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        jLabel4.setText("VISTA PREVIA");

        bCancelar.setBackground(new java.awt.Color(0, 51, 102));
        bCancelar.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        bCancelar.setForeground(new java.awt.Color(255, 255, 255));
        bCancelar.setText("Cancelar");
        bCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCancelarActionPerformed(evt);
            }
        });

        bLimpiar.setBackground(new java.awt.Color(0, 51, 102));
        bLimpiar.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        bLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        bLimpiar.setText("Limpiar");
        bLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLimpiarActionPerformed(evt);
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

        jLabel12.setFont(new java.awt.Font("Cambria", 0, 24)); // NOI18N
        jLabel12.setText("Categoría :");

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
                                .addGap(0, 9, Short.MAX_VALUE)
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
                .addContainerGap(35, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(tNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel8))
                                .addGap(41, 41, 41)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tPrecFijo, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                                    .addComponent(jTImagen)
                                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                                    .addComponent(tPrecVenta, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                                    .addComponent(cCategoria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(imgCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addComponent(jLabel4))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(bLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(bAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(bCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)))
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
                .addGap(73, 73, 73)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(imgCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tPrecFijo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tPrecVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                                .addGap(21, 21, 21))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTImagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addComponent(jButton6)
                        .addGap(41, 41, 41)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(130, 130, 130))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 986, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 744, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        JFileChooser fc =new JFileChooser();
        fc.setFileFilter(new FileNameExtensionFilter("Archivos de imagen","tif","jpg","jpeg","png","gif"));
        int opcion = fc.showDialog(this,"Abrir");
        if (opcion == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            //String direccion = file.getPath();
            this.cargaDirectorio(file.getParent());
            if (file!=null){
                cargarImagen(imgCategoria,file);
            }
        }
    }//GEN-LAST:event_jButton6ActionPerformed
 
    private void cargaDirectorio (String folder) {
        File dir = new File(folder);
        if (dir.isDirectory()){
            this.fotos = dir.listFiles(new FilenameFilter(){
                public boolean accept(File file, String nombre){
                    if (nombre.endsWith(".tif" ) || nombre.endsWith(".jpg" ) ||
                    nombre.endsWith(".jpeg") || nombre.endsWith(".gif" ) ||
                    nombre.endsWith(".png")) { return true; }
                    return false;
                }
            });
        }
    }
    public void cargarImagen(javax.swing.JDesktopPane jDeskp, File file){
        try {
            BufferedImage image = ImageIO.read(file);
            jDeskp.setBorder(new com.epissoft.sanguchito.vista.utilitarios.pintaImagen(image));
            jTImagen.setText(file.getAbsolutePath());
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(this, "No se ha cargado la imagen");
        }
        
    }
    
    private void bCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelarActionPerformed
         atras();
    }//GEN-LAST:event_bCancelarActionPerformed

    private void bAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAgregarActionPerformed

        
        String resultado;
        if(cCategoria.getSelectedIndex()==-1){
            resultado=ActualizarProductoControlador.actualizarProducto(producto.getProCod(),tNombre.getText(),"prod"+tNombre.getText()+".png", tPrecFijo.getText(),tPrecVenta.getText(),categoria.getCatProdCod(),producto.getProAlmCod());
        }else{
            resultado=ActualizarProductoControlador.actualizarProducto(producto.getProCod(),tNombre.getText(),"prod"+tNombre.getText()+".png", tPrecFijo.getText(),tPrecVenta.getText(),cats.get(cCategoria.getSelectedIndex()).getCatProdCod(),producto.getProAlmCod());
        }
        if(resultado.equals("CORRECTO")){
            ImageResizer.copyImage(jTImagen.getText(),ManejoSesion.getRuta()+"/Imagenes/","prod"+tNombre.getText(),60,60);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
            }
            atras();
        }else{
            JOptionPane.showMessageDialog(null,resultado);
        }
    }//GEN-LAST:event_bAgregarActionPerformed

    private void bLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLimpiarActionPerformed
        // TODO add your handling code here:
        tPrecFijo.setText("");
        tPrecVenta.setText("");
        jTImagen.setText("");
        tNombre.setText("");
    }//GEN-LAST:event_bLimpiarActionPerformed
    private void atras(){
        Sanguchito.sanguchito.removeAll();
        ListaProductosOfrecidos prodOfrecidos = new ListaProductosOfrecidos(categoria);
        Sanguchito.sanguchito.add(prodOfrecidos);
        try {
            prodOfrecidos.setMaximum(true);
        } catch (Exception e) {
        }
        prodOfrecidos.show();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAgregar;
    private javax.swing.JButton bAsistencia;
    private javax.swing.JButton bCancelar;
    private javax.swing.JButton bCerrarSesion;
    private javax.swing.JButton bLimpiar;
    private javax.swing.JComboBox cCategoria;
    private javax.swing.JDesktopPane imgCategoria;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JTextField jTImagen;
    private javax.swing.JLabel lBienvenida;
    private javax.swing.JLabel lFecha;
    private javax.swing.JLabel lHora;
    private javax.swing.JLabel lTitulo;
    private javax.swing.JLabel lUsuario;
    private javax.swing.JTextField tFecha;
    private javax.swing.JTextField tHora;
    public javax.swing.JTextField tNombre;
    public javax.swing.JTextField tPrecFijo;
    public javax.swing.JTextField tPrecVenta;
    private javax.swing.JTextField tUsuario;
    // End of variables declaration//GEN-END:variables

}
