package com.epissoft.sanguchito.vista.almacen;

import com.epissoft.sanguchito.controlador.categoriaproductoalmacencontrolador.MostrarCategoriaProductoAlmacenControlador;
import com.epissoft.sanguchito.controlador.kardexcontrolador.EgresoKardexControlador;
import com.epissoft.sanguchito.controlador.productoalmacencontrolador.MostrarProductoAlmacenControlador;
import com.epissoft.sanguchito.modelo.almacenmodelo.bean.InsumoAlmacen;
import com.epissoft.sanguchito.persistencia.CategoriaProductoAlmacen;
import com.epissoft.sanguchito.persistencia.ProductoAlmacen;
import com.epissoft.sanguchito.persistencia.Proveedor;
import com.epissoft.sanguchito.vista.*;
import com.epissoft.sanguchito.vista.almacencategoria.ListaCategoriasAlmacen;
import com.epissoft.sanguchito.vista.utilitarios.ActualizarReloj;
import com.epissoft.sanguchito.vista.utilitarios.BotonesComunes;
import com.epissoft.sanguchito.vista.utilitarios.CargarFecha;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class SalidaAlmacen extends javax.swing.JInternalFrame {
    private ArrayList<InsumoAlmacen> list=new ArrayList<>();
    private List<CategoriaProductoAlmacen> categorias;
    private List<ProductoAlmacen> productos;
    private List<Proveedor> proveedores;
    private float montoTotal;
    public SalidaAlmacen() {
        
        initComponents();
        tUsuario.setText(ManejoSesion.getUsuario());
        ActualizarReloj actualizar = new ActualizarReloj(tHora);
        CargarFecha.cargarFechaActual(tFecha);
        
        montoTotal=0;
        cargarCategorias();
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
        ListaInsumos = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tInsumos = new javax.swing.JTable();
        bQuitar = new javax.swing.JButton();
        bRegistrar = new javax.swing.JButton();
        bCancelar = new javax.swing.JButton();
        Insumo = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        cInsumo = new javax.swing.JComboBox();
        jLabel17 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cCategoria = new javax.swing.JComboBox();
        bAgregar = new javax.swing.JButton();
        bLimpiar = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        tCantidadActual = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        tCantidad = new javax.swing.JTextField();
        tUnidad = new javax.swing.JTextField();

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
        lTitulo.setText("REGISTRO DE PAGO POR INSUMOS");
        lTitulo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 102), 1, true));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/logo.png"))); // NOI18N

        ListaInsumos.setBackground(new java.awt.Color(255, 255, 255));
        ListaInsumos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Insumos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Cambria", 0, 18), new java.awt.Color(0, 51, 102))); // NOI18N

        tInsumos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Cantidad"
            }
        ));
        jScrollPane1.setViewportView(tInsumos);

        bQuitar.setBackground(new java.awt.Color(0, 51, 102));
        bQuitar.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        bQuitar.setForeground(new java.awt.Color(255, 255, 255));
        bQuitar.setText("<html><center>Quitar seleccionado</center></html>");
        bQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bQuitarActionPerformed(evt);
            }
        });

        bRegistrar.setBackground(new java.awt.Color(0, 51, 102));
        bRegistrar.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        bRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        bRegistrar.setText("<html><center>Registrar salida</center></html>");
        bRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bRegistrarActionPerformed(evt);
            }
        });

        bCancelar.setBackground(new java.awt.Color(0, 51, 102));
        bCancelar.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        bCancelar.setForeground(new java.awt.Color(255, 255, 255));
        bCancelar.setText("Cancelar");
        bCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ListaInsumosLayout = new javax.swing.GroupLayout(ListaInsumos);
        ListaInsumos.setLayout(ListaInsumosLayout);
        ListaInsumosLayout.setHorizontalGroup(
            ListaInsumosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ListaInsumosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ListaInsumosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ListaInsumosLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(ListaInsumosLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(bQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(bRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))))
        );
        ListaInsumosLayout.setVerticalGroup(
            ListaInsumosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ListaInsumosLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(ListaInsumosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bQuitar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bCancelar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        Insumo.setBackground(new java.awt.Color(255, 255, 255));
        Insumo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Insumo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Cambria", 0, 18), new java.awt.Color(0, 51, 102))); // NOI18N

        jLabel16.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        jLabel16.setText("Insumo:");

        jLabel17.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        jLabel17.setText("Cantidad a retirar :");

        jLabel10.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        jLabel10.setText("Categoría:");

        cCategoria.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N

        bAgregar.setBackground(new java.awt.Color(0, 51, 102));
        bAgregar.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        bAgregar.setForeground(new java.awt.Color(255, 255, 255));
        bAgregar.setText("Agregar");
        bAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAgregarActionPerformed(evt);
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

        jLabel18.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        jLabel18.setText("Cantidad actual :");

        tCantidadActual.setEditable(false);
        tCantidadActual.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        tCantidadActual.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel19.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        jLabel19.setText("Unidad :");

        tCantidad.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        tCantidad.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        tUnidad.setEditable(false);
        tUnidad.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        tUnidad.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        javax.swing.GroupLayout InsumoLayout = new javax.swing.GroupLayout(Insumo);
        Insumo.setLayout(InsumoLayout);
        InsumoLayout.setHorizontalGroup(
            InsumoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InsumoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(InsumoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(InsumoLayout.createSequentialGroup()
                        .addComponent(bLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addComponent(bAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(InsumoLayout.createSequentialGroup()
                        .addGroup(InsumoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE))
                        .addGroup(InsumoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(InsumoLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tCantidadActual, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(InsumoLayout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addGroup(InsumoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cCategoria, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cInsumo, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tUnidad)))))
                    .addGroup(InsumoLayout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(tCantidad)))
                .addContainerGap())
        );
        InsumoLayout.setVerticalGroup(
            InsumoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InsumoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(InsumoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(InsumoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cInsumo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(InsumoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(InsumoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tCantidadActual, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(InsumoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(InsumoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
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
                                        .addComponent(bCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(Insumo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ListaInsumos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGap(31, 31, 31))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ListaInsumos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Insumo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        ListaInsumos.getAccessibleContext().setAccessibleName("Insumos Retirados");

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
                .addGap(0, 0, Short.MAX_VALUE))
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

    private void bAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAgregarActionPerformed
        if(cCategoria.getSelectedIndex()<0||cInsumo.getSelectedIndex()<0){
            JOptionPane.showMessageDialog(null,"Debe seleccionar un insumo","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        int cantidad=0;
        try{
            cantidad=Integer.parseInt(tCantidad.getText());
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Ingrese una cantidad correcta","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (cantidad < 1) {
            JOptionPane.showMessageDialog(null, "La cantidad debe ser como minimo 1", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            list.add(new InsumoAlmacen(productos.get(cInsumo.getSelectedIndex()).getProdAlmCod(),
                    productos.get(cInsumo.getSelectedIndex()).getProdAlmDes(),
                    cantidad,
                    0));
            cargarDatos();
        }       
    }//GEN-LAST:event_bAgregarActionPerformed

    private void bLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLimpiarActionPerformed
        tCantidadActual.setText("");
        tCantidad.setText("");
        tUnidad.setText("");
        cCategoria.setSelectedIndex(-1);
        cInsumo.setSelectedIndex(-1);
    }//GEN-LAST:event_bLimpiarActionPerformed

    private void bQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bQuitarActionPerformed
        if(tInsumos.getSelectedRow()>=0){
            list.remove(tInsumos.getSelectedRow());
            cargarDatos();
        }
    }//GEN-LAST:event_bQuitarActionPerformed

    private void bRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bRegistrarActionPerformed
       EgresoKardexControlador.agregarEgresoKardex(list);
       atras();
    }//GEN-LAST:event_bRegistrarActionPerformed

    private void bCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelarActionPerformed
        atras();
    }//GEN-LAST:event_bCancelarActionPerformed
    private void atras(){
        Sanguchito.sanguchito.removeAll();
        ListaCategoriasAlmacen tipoGasto=new ListaCategoriasAlmacen();
        Sanguchito.sanguchito.add(tipoGasto);
        try{
            tipoGasto.setMaximum(true);
        }catch(Exception e){
            
        }
        tipoGasto.show();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Insumo;
    private javax.swing.JPanel ListaInsumos;
    private javax.swing.JButton bAgregar;
    private javax.swing.JButton bAsistencia;
    private javax.swing.JButton bCancelar;
    private javax.swing.JButton bCerrarSesion;
    private javax.swing.JButton bLimpiar;
    private javax.swing.JButton bQuitar;
    private javax.swing.JButton bRegistrar;
    private javax.swing.JComboBox cCategoria;
    private javax.swing.JComboBox cInsumo;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lBienvenida;
    private javax.swing.JLabel lFecha;
    private javax.swing.JLabel lHora;
    private javax.swing.JLabel lTitulo;
    private javax.swing.JLabel lUsuario;
    private javax.swing.JTextField tCantidad;
    private javax.swing.JTextField tCantidadActual;
    private javax.swing.JTextField tFecha;
    private javax.swing.JTextField tHora;
    private javax.swing.JTable tInsumos;
    private javax.swing.JTextField tUnidad;
    private javax.swing.JTextField tUsuario;
    // End of variables declaration//GEN-END:variables
    private void cargarCategorias(){
        categorias=MostrarCategoriaProductoAlmacenControlador.listaCategoriaProductoAlmacen();
        if(!categorias.isEmpty()){
            for(CategoriaProductoAlmacen cat:categorias){
                cCategoria.addItem(""+cat.getCatProdAlmDes());
            }
            productos=MostrarProductoAlmacenControlador.listaProductoAlmacen(categorias.get(0).getCatProdAlmCod());
            for(ProductoAlmacen prod:productos){
                cInsumo.addItem(""+prod.getProdAlmDes());
            }
            tCantidadActual.setText("");
            tUnidad.setText("");
        }
        cCategoria.setSelectedIndex(-1);
        cCategoria.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent event) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    cInsumo.removeAllItems();
                    productos.clear();
                    productos=MostrarProductoAlmacenControlador.listaProductoAlmacen(categorias.get(cCategoria.getSelectedIndex()).getCatProdAlmCod());
                    for(ProductoAlmacen prod:productos){
                        cInsumo.addItem(""+prod.getProdAlmDes());
                    }
                }
            }
        });
        cInsumo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent event) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    tUnidad.setText(productos.get(cInsumo.getSelectedIndex()).getUniMedCod().getUniMedDes());
                    tCantidadActual.setText(""+productos.get(cInsumo.getSelectedIndex()).getAlmacen().getAlmCant());
                }
            }
        });
        cInsumo.setSelectedIndex(-1);
    }
    private void limpiarTabla(JTable tabla){
        try {
            DefaultTableModel modelo=(DefaultTableModel) tabla.getModel();
            int filas = tabla.getRowCount();
            for (int i = 0;filas>i; i++) {
                modelo.removeRow(0);
            }
        } catch (Exception e) {
            
        }
    }
    private void cargarDatos(){
        limpiarTabla(tInsumos);
        DefaultTableModel tabla = (DefaultTableModel)tInsumos.getModel();
        montoTotal=0;
        for(InsumoAlmacen dato : list){
            Object[]data ={dato.getDesInsumo()
                    ,dato.getCantidad()};
            tabla.addRow(data);
            montoTotal+=(dato.getCantidad()*dato.getPrecio());
        }       
        tInsumos.setModel(tabla);
        tInsumos.setRowHeight(35);
    }
}
