package com.epissoft.sanguchito.vista.ventas;
import com.epissoft.sanguchito.vista.ventascaja.AperturaDeCaja;
import com.epissoft.sanguchito.vista.ventascaja.CierreDeCaja;
import com.epissoft.sanguchito.controlador.aperturacajacontrolador.AperturaCajaControlador;
import com.epissoft.sanguchito.controlador.categoriaproductocontrolador.CategoriaProductoControlador;
import com.epissoft.sanguchito.controlador.extracontrolador.ExtraControlador;
import com.epissoft.sanguchito.controlador.facturacioncontrolador.FacturacionControlador;
import com.epissoft.sanguchito.controlador.productocontrolador.MostrarProductoControlador;
import com.epissoft.sanguchito.persistencia.CategoriaExtra;
import com.epissoft.sanguchito.persistencia.CategoriaProducto;
import com.epissoft.sanguchito.persistencia.Extras;
import com.epissoft.sanguchito.persistencia.Producto;
import com.epissoft.sanguchito.vista.ManejoSesion;
import com.epissoft.sanguchito.vista.Sanguchito;
import com.epissoft.sanguchito.vista.utilitarios.BotonesComunes;
import com.epissoft.sanguchito.vista.utilitarios.ActualizarReloj;
import com.epissoft.sanguchito.vista.utilitarios.CargarFecha;
import static com.epissoft.sanguchito.vista.ventas.Venta.SECCION_BOTONES;
import static com.epissoft.sanguchito.vista.ventas.Venta.SECCION_LISTA_CATEGORIAS;
import static com.epissoft.sanguchito.vista.ventas.Venta.SECCION_PEDIDO;
import static com.epissoft.sanguchito.vista.ventas.Venta.SECCION_PEDIDO_NUEVO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Venta extends javax.swing.JInternalFrame{
    
    //Determina si trabajamos sobre productosofrecidos o extras
    //Si tiene valor 1 estamos en productos ofrecidos y si tenemos 2 estamos en extras
    private int ambito;
    //indica que opcion del ambito fue seleccionada
    private int seleccionado;  
    //indica la senccion a la que pertenecen los elementos.
    private int seccion;
    public static int SECCION_PEDIDO_NUEVO=0;
    public static int SECCION_PEDIDO=1;
    public static int SECCION_LISTA_CATEGORIAS=2;
    public static int SECCION_BOTONES=3;
    //indica el espacio superior actualmente utilizado, sirve para posicionar los botones
    private int espacioOcupado=0;
    //margenes a considerar para colocar los botones
    private int margenIzquierdo = 20;
    private int margenSuperior = 10;
    //definimos tamaño de botones para las categorias
    private int alturaBotonCategoria = 40;
    private int anchoBotonCategoria = 119;
    //definimos ancho de botones y labelspara productos
    private int anchoBotonProducto = 90;
    private int altoBotonProducto = 80;
    private int anchoLabelProducto = 90;
    private int altoLabelProducto = 30;
    //numero maximo por filas
    private int productosPorFila = 8;
    //numero maximo de filas
    private int numFilas=2;
    private int pedidoSeleccionado;
    //Paginado de pedidos
    private int paginaActual=0;
    private int numPedFila=8;
    
    private ArrayList<JButton> bCategorias=new ArrayList();
    private ArrayList<JButton> bPedido=new ArrayList();
    private ArrayList<JLabel> lPedido=new ArrayList();
    private ArrayList<JButton> bBotones=new ArrayList();
    private ArrayList<JLabel> lBotones=new ArrayList();
    private JButton bAgregarPedido;
    private JLabel lAgregarPedido;
    private JButton bOpcionesProducto;
    private List<Producto> productos=new ArrayList();
    private List<CategoriaProducto> catProductos=new ArrayList();
    private List<CategoriaExtra> catExtra=new ArrayList();
    private ArrayList<Producto> pedido=new ArrayList();
    private List<Extras> extras=new ArrayList();
    private ArrayList<ArrayList<Extras>> extPedido=new ArrayList();
    private ArrayList<ArrayList<Integer>> cantExtras=new ArrayList();
    public Venta() {
        
        initComponents();
        bPagAtras.setVisible(false);
        bPagSiguiente.setVisible(false);
        if(AperturaCajaControlador.ultimoCierre()){
            AperturaDeCaja lista = new AperturaDeCaja();
            lista.setLocation(Sanguchito.sanguchito.getWidth() / 2 - lista.getWidth() / 2, Sanguchito.sanguchito.getHeight() / 2 - lista.getHeight() / 2);
            Sanguchito.sanguchito.add(lista, new Integer(10));
            lista.show();
        }
        seccion=1;
        ambito=1;
        seleccionado=0;
        //creacion del boton de agregar producto
        bAgregarPedido=new JButton("");
        lAgregarPedido=new JLabel("<html><center><p>Agregar</p><p>pedido</p></center></html>");
        
        bAgregarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/sAgregar.png")));
        bAgregarPedido.addActionListener(new FuncionamientoBotones(0, this,SECCION_PEDIDO_NUEVO));        
        
        bOpcionesProducto=new JButton("Opciones");
        bOpcionesProducto.addActionListener(new FuncionamientoBotonOpciones(this));
        bOpcionesProducto.setVisible(false);
        pPrincipal.add(bOpcionesProducto);
        tUsuario.setText(ManejoSesion.getUsuario());
        ActualizarReloj actualizar=new ActualizarReloj(tHora);
        CargarFecha.cargarFechaActual(tFecha);
        tUsuario.setText(ManejoSesion.getUsuario());
        pPedido.add(bAgregarPedido);
        pPedido.add(lAgregarPedido);
        catProductos=CategoriaProductoControlador.listaCategoriaProducto();
        if(!catProductos.isEmpty()){
            productos=MostrarProductoControlador.productosPorTipo(catProductos.get(0).getCatProdCod());
        }
        actualizarPantalla();
        bCopiar.setVisible(false);
        bDetalle.setVisible(false);
        bEliminar.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pPrincipal = new javax.swing.JPanel();
        lBienvenida = new javax.swing.JLabel();
        lUsuario = new javax.swing.JLabel();
        tUsuario = new javax.swing.JTextField();
        lHora = new javax.swing.JLabel();
        tHora = new javax.swing.JTextField();
        lFecha = new javax.swing.JLabel();
        tFecha = new javax.swing.JTextField();
        bAsistencia = new javax.swing.JButton();
        bCerrarSesion = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        pPedido = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        tPrecioTotal = new javax.swing.JTextField();
        bAceptar = new javax.swing.JButton();
        bPagAtras = new javax.swing.JButton();
        bPagSiguiente = new javax.swing.JButton();
        bDetalle = new javax.swing.JButton();
        bCopiar = new javax.swing.JButton();
        bEliminar = new javax.swing.JButton();
        cCaja = new javax.swing.JButton();
        bLimpiar = new javax.swing.JButton();

        setRequestFocusEnabled(false);

        pPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        pPrincipal.setPreferredSize(new java.awt.Dimension(1000, 186));

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

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/logo.png"))); // NOI18N

        pPedido.setBackground(new java.awt.Color(255, 255, 255));
        pPedido.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pedido", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Cambria", 0, 18), new java.awt.Color(51, 51, 255))); // NOI18N
        pPedido.setPreferredSize(new java.awt.Dimension(941, 140));

        jLabel13.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        jLabel13.setText("Precio total:");

        tPrecioTotal.setEditable(false);
        tPrecioTotal.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        tPrecioTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tPrecioTotalActionPerformed(evt);
            }
        });

        bAceptar.setBackground(new java.awt.Color(0, 51, 102));
        bAceptar.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        bAceptar.setForeground(new java.awt.Color(255, 255, 255));
        bAceptar.setText("Realizar pedido");
        bAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAceptarActionPerformed(evt);
            }
        });

        bPagAtras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fi.png"))); // NOI18N
        bPagAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPagAtrasActionPerformed(evt);
            }
        });

        bPagSiguiente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fd.png"))); // NOI18N
        bPagSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPagSiguienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pPedidoLayout = new javax.swing.GroupLayout(pPedido);
        pPedido.setLayout(pPedidoLayout);
        pPedidoLayout.setHorizontalGroup(
            pPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pPedidoLayout.createSequentialGroup()
                .addGroup(pPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pPedidoLayout.createSequentialGroup()
                        .addContainerGap(523, Short.MAX_VALUE)
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(tPrecioTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(bAceptar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pPedidoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(bPagAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bPagSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pPedidoLayout.setVerticalGroup(
            pPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(tPrecioTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pPedidoLayout.createSequentialGroup()
                .addComponent(bAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(pPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bPagAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bPagSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 56, Short.MAX_VALUE))
        );

        bDetalle.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        bDetalle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/hamb_defecto.png"))); // NOI18N
        bDetalle.setText("Detalle pedido");
        bDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDetalleActionPerformed(evt);
            }
        });

        bCopiar.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        bCopiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/sAgregar.png"))); // NOI18N
        bCopiar.setText("Copiar pedido");
        bCopiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCopiarActionPerformed(evt);
            }
        });

        bEliminar.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        bEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/hamb_quitar.png"))); // NOI18N
        bEliminar.setText("Quitar pedido");
        bEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEliminarActionPerformed(evt);
            }
        });

        cCaja.setBackground(new java.awt.Color(0, 51, 102));
        cCaja.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        cCaja.setForeground(new java.awt.Color(255, 255, 255));
        cCaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/aperturaCaja.png"))); // NOI18N
        cCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cCajaActionPerformed(evt);
            }
        });

        bLimpiar.setBackground(new java.awt.Color(0, 51, 102));
        bLimpiar.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        bLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        bLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/limpiarPedido.png"))); // NOI18N
        bLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pPrincipalLayout = new javax.swing.GroupLayout(pPrincipal);
        pPrincipal.setLayout(pPrincipalLayout);
        pPrincipalLayout.setHorizontalGroup(
            pPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pPrincipalLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(18, 18, 18)
                        .addGroup(pPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pPrincipalLayout.createSequentialGroup()
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
                            .addGroup(pPrincipalLayout.createSequentialGroup()
                                .addComponent(lBienvenida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52)
                                .addComponent(cCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54)
                                .addComponent(bAsistencia, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(bCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pPrincipalLayout.createSequentialGroup()
                        .addComponent(pPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(pPrincipalLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(pPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bCopiar)
                    .addGroup(pPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(bDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 730, Short.MAX_VALUE))
        );
        pPrincipalLayout.setVerticalGroup(
            pPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pPrincipalLayout.createSequentialGroup()
                        .addGroup(pPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pPrincipalLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(pPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(bCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bAsistencia, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pPrincipalLayout.createSequentialGroup()
                                .addComponent(lBienvenida)
                                .addGap(36, 36, 36)))
                        .addGroup(pPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tHora, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tUsuario)
                                .addComponent(lFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lHora, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(bCopiar)
                .addGap(18, 18, 18)
                .addComponent(bDetalle)
                .addGap(18, 18, 18)
                .addComponent(bEliminar)
                .addGap(184, 184, 184))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(pPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 973, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 744, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 36, Short.MAX_VALUE))
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

    private void tPrecioTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tPrecioTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tPrecioTotalActionPerformed

    private void bAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAceptarActionPerformed
        ConfirmacionPedido conf=new ConfirmacionPedido(pedido, extPedido,cantExtras);
        conf.setLocation(Sanguchito.sanguchito.getWidth()/2-conf.getWidth()/2,Sanguchito.sanguchito.getHeight()/2-conf.getHeight()/2);
        Sanguchito.sanguchito.add(conf, new Integer( 10 ));
        conf.show();
    }//GEN-LAST:event_bAceptarActionPerformed

    private void bCopiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCopiarActionPerformed
        Producto r=new Producto(pedido.get(pedidoSeleccionado).getProCod(),
                pedido.get(pedidoSeleccionado).getProDes(),
                pedido.get(pedidoSeleccionado).getProPreSol(),
                pedido.get(pedidoSeleccionado).getProPreCar(),
                pedido.get(pedidoSeleccionado).getProImg(),
                true,
                pedido.get(pedidoSeleccionado).getProAlmCod());
        r.setCatProdCod(pedido.get(pedidoSeleccionado).getCatProdCod());
        extPedido.add(new ArrayList<Extras>());
        cantExtras.add(new ArrayList<Integer>());        
        for(int i=0;i<extPedido.get(pedidoSeleccionado).size();i++){
            extPedido.get(extPedido.size()-1).add(extPedido.get(pedidoSeleccionado).get(i));
            cantExtras.get(cantExtras.size()-1).add(cantExtras.get(pedidoSeleccionado).get(i));
        }
        pedido.add(r);
        ocultarBotones(bCategorias);
        ocultarBotones(bBotones);
        ocultarLabels(lBotones);
        bOpcionesProducto.setVisible(false);
        bCopiar.setVisible(false);
        bDetalle.setVisible(false);
        bEliminar.setVisible(false);
        actualizarPantalla();
    }//GEN-LAST:event_bCopiarActionPerformed

    private void bDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDetalleActionPerformed
        DetallePedido conf=new DetallePedido(pedido.get(pedidoSeleccionado), extPedido.get(pedidoSeleccionado),cantExtras.get(pedidoSeleccionado));
        conf.setLocation(Sanguchito.sanguchito.getWidth()/2-conf.getWidth()/2,Sanguchito.sanguchito.getHeight()/2-conf.getHeight()/2);
        Sanguchito.sanguchito.add(conf, new Integer( 10 ));
        conf.show();
    }//GEN-LAST:event_bDetalleActionPerformed

    private void bEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEliminarActionPerformed
        pedido.remove(pedidoSeleccionado);
        extPedido.remove(pedidoSeleccionado);
        cantExtras.remove(pedidoSeleccionado);
        ocultarBotones(bPedido);
        ocultarLabels(lPedido);
        ocultarBotones(bCategorias);
        ocultarBotones(bBotones);
        ocultarLabels(lBotones);
        bOpcionesProducto.setVisible(false);
        bCopiar.setVisible(false);
        bDetalle.setVisible(false);
        bEliminar.setVisible(false);
        actualizarPantalla();
    }//GEN-LAST:event_bEliminarActionPerformed

    private void cCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cCajaActionPerformed
        if(AperturaCajaControlador.ultimoCierre()){
            JOptionPane.showMessageDialog(null,"El cierre de caja ya fue realizado");
        }else{
            CierreDeCaja lista = new CierreDeCaja();
            lista.setLocation(Sanguchito.sanguchito.getWidth() / 2 - lista.getWidth() / 2, Sanguchito.sanguchito.getHeight() / 2 - lista.getHeight() / 2);
            Sanguchito.sanguchito.add(lista, new Integer(10));
            lista.show();
        }
    }//GEN-LAST:event_cCajaActionPerformed

    private void bPagSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPagSiguienteActionPerformed
        paginaActual++;
        ocultarBotones(bPedido);
        ocultarLabels(lPedido);
        actualizarPantalla();
    }//GEN-LAST:event_bPagSiguienteActionPerformed

    private void bPagAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPagAtrasActionPerformed
        paginaActual--;
        ocultarBotones(bPedido);
        ocultarLabels(lPedido);
        actualizarPantalla();
        
    }//GEN-LAST:event_bPagAtrasActionPerformed

    private void bLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLimpiarActionPerformed
        BotonesComunes.limpiarVenta();
    }//GEN-LAST:event_bLimpiarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAceptar;
    private javax.swing.JButton bAsistencia;
    private javax.swing.JButton bCerrarSesion;
    private javax.swing.JButton bCopiar;
    private javax.swing.JButton bDetalle;
    private javax.swing.JButton bEliminar;
    private javax.swing.JButton bLimpiar;
    private javax.swing.JButton bPagAtras;
    private javax.swing.JButton bPagSiguiente;
    private javax.swing.JButton cCaja;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel lBienvenida;
    private javax.swing.JLabel lFecha;
    private javax.swing.JLabel lHora;
    private javax.swing.JLabel lUsuario;
    private javax.swing.JPanel pPedido;
    private javax.swing.JPanel pPrincipal;
    private javax.swing.JTextField tFecha;
    private javax.swing.JTextField tHora;
    private javax.swing.JTextField tPrecioTotal;
    private javax.swing.JTextField tUsuario;
    // End of variables declaration//GEN-END:variables
private void actualizarPantalla(){
        espacioOcupado=220;
        listaPedido();
        if(ambito==1){
            productosOfrecidos();
        }else{
            extras();
        }
    }
    private void productosOfrecidos(){
        añadirBotones(bCategorias,catProductos.size(),SECCION_LISTA_CATEGORIAS,pPrincipal);
        for(int i=0;i<catProductos.size();i++){
            bCategorias.get(i).setText(catProductos.get(i).getCatProdDes());
            bCategorias.get(i).setBounds(margenIzquierdo+i*anchoBotonCategoria, espacioOcupado, anchoBotonCategoria,alturaBotonCategoria);
            bCategorias.get(i).setVisible(true);
        }
        
        espacioOcupado+=alturaBotonCategoria+margenSuperior;
        añadirBotones(bBotones,productos.size(),SECCION_BOTONES,pPrincipal);
        añadirLabels(lBotones,productos.size(),pPrincipal);
        for (int i = 0; i < productos.size(); i++) {
            bBotones.get(i).setText("");
            bBotones.get(i).setBounds(margenIzquierdo + (i % productosPorFila) * anchoBotonProducto, espacioOcupado + (altoBotonProducto+altoLabelProducto) * (int) (i / productosPorFila), anchoBotonProducto, altoBotonProducto);
            try{
                bBotones.get(i).setIcon(new javax.swing.ImageIcon("Imagenes/"+productos.get(i).getProImg()));    
            }catch(Exception e){
                bBotones.get(i).setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/sprodAlmDefault.png")));    
            }
            bBotones.get(i).setVisible(true);
            lBotones.get(i).setText("<html><p>"+productos.get(i).getProDes()+"</p><p>S/"+productos.get(i).getProPreCar()+"</p></html>");
            lBotones.get(i).setBounds(margenIzquierdo + (i % productosPorFila) * anchoBotonProducto, espacioOcupado+altoBotonProducto + (altoBotonProducto+altoLabelProducto) * (int) (i / productosPorFila), anchoLabelProducto,altoLabelProducto); 
            lBotones.get(i).setVisible(true);
        }
    }
    
    private void añadirBotones(ArrayList<JButton> a,int necesarios,int tipo,JPanel p){
        for (int i = a.size(); i < necesarios; i++) {
            a.add(new JButton(""));
            a.get(i).addActionListener(new FuncionamientoBotones(i,this,tipo));
            p.add(a.get(i));
        }
    }
    private void añadirLabels(ArrayList<JLabel> a,int necesarios,JPanel p){
        for (int i = a.size(); i < necesarios; i++) {
            a.add(new JLabel("Default"));
            p.add(a.get(i));
        }
    }
    private void listaPedido(){
        int margenPedido=70;
        int tPedido=0;
        if(pedido.size()>=numPedFila*(paginaActual+1)){
            tPedido=numPedFila;
            bPagSiguiente.setVisible(true);
            bAgregarPedido.setVisible(false);
            lAgregarPedido.setVisible(false);
        }else{
            tPedido=pedido.size()%numPedFila;
            
            bPagSiguiente.setVisible(false);
            //Posicionar el boton de agregar en caso en funcion a los botones anteriores
            bAgregarPedido.setBounds(margenIzquierdo + 70 + (pedido.size()%numPedFila)* anchoBotonProducto,
                        margenPedido, 
                        anchoBotonProducto,
                        altoBotonProducto);
            bAgregarPedido.setVisible(true);
            
            //Posicionar el label correspondiente
            lAgregarPedido.setBounds(margenIzquierdo + 70 + (pedido.size()%numPedFila) * anchoBotonProducto,
                        margenPedido+altoBotonProducto,
                        anchoLabelProducto,
                        altoLabelProducto);
            lAgregarPedido.setVisible(true);
            
        }
        if(paginaActual>0){
            bPagAtras.setVisible(true);
        }else{
            bPagAtras.setVisible(false);
        }
        //Crear nuevos botones de ser necesario
        añadirBotones(bPedido,pedido.size(),SECCION_PEDIDO,pPedido);
        añadirLabels(lPedido,pedido.size(),pPedido);
        
        //Activamos los botones y los posicionamos
        
        
        for (int i = 0; i < tPedido; i++) {
            bPedido.get(numPedFila*paginaActual+i).setBounds(margenIzquierdo + 70+ i * anchoBotonProducto, margenPedido, anchoBotonProducto, altoBotonProducto);
            try{
                              
                bPedido.get(numPedFila*paginaActual+i).setIcon(new javax.swing.ImageIcon("Imagenes/"+pedido.get(numPedFila*paginaActual+i).getProImg()));    
            }catch(Exception e){
                bPedido.get(numPedFila*paginaActual+i).setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/sprodAlmDefault.png")));    
            }
            bPedido.get(numPedFila*paginaActual+i).setVisible(true);
            float precio=FacturacionControlador.retornarTotal(pedido.get(numPedFila*paginaActual+i), extPedido.get(numPedFila*paginaActual+i),cantExtras.get(numPedFila*paginaActual+i));
            lPedido.get(numPedFila*paginaActual+i).setText("<html><p>"+pedido.get(numPedFila*paginaActual+i).getProDes()+"</p><p>S/"+precio+"</p></html>");
            lPedido.get(numPedFila*paginaActual+i).setBounds(margenIzquierdo + 70+ i * anchoBotonProducto, margenPedido+altoBotonProducto, anchoLabelProducto,altoLabelProducto); 
            lPedido.get(numPedFila*paginaActual+i).setVisible(true);
        }
        //Actualizamos el espacio ocupado
        espacioOcupado+=(altoBotonProducto+altoLabelProducto+margenSuperior);
    }
    private void extras(){
        añadirBotones(bCategorias,catExtra.size(),SECCION_LISTA_CATEGORIAS,pPrincipal);
        for(int i=0;i<catExtra.size();i++){
            bCategorias.get(i).setText(catExtra.get(i).getCatExtNom());
            bCategorias.get(i).setBounds(margenIzquierdo+i*anchoBotonCategoria, espacioOcupado, anchoBotonCategoria,alturaBotonCategoria);
            bCategorias.get(i).setVisible(true);
        }
        bOpcionesProducto.setBounds(margenIzquierdo+catExtra.size()*anchoBotonCategoria, espacioOcupado, anchoBotonCategoria,alturaBotonCategoria);
        bOpcionesProducto.setVisible(true);
        
        espacioOcupado+=alturaBotonCategoria+margenSuperior;
        añadirBotones(bBotones,extras.size(),SECCION_BOTONES,pPrincipal);
        añadirLabels(lBotones,extras.size(),pPrincipal);
        for (int i = 0; i < extras.size(); i++) {
            bBotones.get(i).setText("");
            bBotones.get(i).setBounds(margenIzquierdo + (i % productosPorFila) * anchoBotonProducto, espacioOcupado + (altoBotonProducto+altoLabelProducto) * (int) (i / productosPorFila), anchoBotonProducto, altoBotonProducto);
            try{
                bBotones.get(i).setIcon(new javax.swing.ImageIcon("Imagenes/ext"+extras.get(i).getExtDes()+".png"));    
            }catch(Exception e){
                bBotones.get(i).setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/sprodAlmDefault.png")));    
            }
            bBotones.get(i).setVisible(true);
            lBotones.get(i).setText("<html><p>"+extras.get(i).getExtDes()+"</p><p>S/"+extras.get(i).getExtPrecSolCar()+"</p></html>");
            lBotones.get(i).setBounds(margenIzquierdo + (i % productosPorFila) * anchoBotonProducto, espacioOcupado+altoBotonProducto + (altoBotonProducto+altoLabelProducto) * (int) (i / productosPorFila), anchoLabelProducto,altoLabelProducto); 
            lBotones.get(i).setVisible(true);
        }
        
    }
    private void ocultarBotones(ArrayList<JButton> lista){
        for(JButton l:lista){
            l.setVisible(false);
        }
    }
    private void ocultarLabels(ArrayList<JLabel> lista){
        for(JLabel l:lista){
            l.setVisible(false);
        }
    }
    public void actualizarAmbito(int a){
        ambito=a;
    }
    public void actualizarSeleccionado(int s){
        seleccionado=s;
    }
    public void actualizarSeccion(int s){
        seccion=s;
    }
    public void resolverCambios(){
        if(seccion==SECCION_PEDIDO_NUEVO){
            ambito=1;
        }
        if(seccion==SECCION_LISTA_CATEGORIAS){
            if(ambito==1){
                productos.clear();
                productos=MostrarProductoControlador.productosPorTipo(catProductos.get(seleccionado).getCatProdCod());
            }else{
                extras.clear();
                extras=ExtraControlador.listaExtrasPorCategoria(catExtra.get(seleccionado).getCatExtCod());
            }
        }
        if(seccion==SECCION_BOTONES){
            if(ambito==1){
                pedido.add(productos.get(seleccionado));
                extPedido.add(new ArrayList());
                cantExtras.add(new ArrayList());
                if(pedido.size()>numPedFila*(paginaActual+1)){
                    paginaActual++;
                }
            }else{
                
                if(extras.get(seleccionado).getExtPrecSolCar()==0){
                    IngresarCantidadExtrasBot lista=new IngresarCantidadExtrasBot(this);
                    lista.setLocation(Sanguchito.sanguchito.getWidth()/2-lista.getWidth()/2,Sanguchito.sanguchito.getHeight()/2-lista.getHeight()/2);
                    Sanguchito.sanguchito.add(lista, new Integer( 10 ));
                    lista.show();
                }else{
                    IngresarCantidadExtras lista=new IngresarCantidadExtras(this);
                    lista.setLocation(Sanguchito.sanguchito.getWidth()/2-lista.getWidth()/2,Sanguchito.sanguchito.getHeight()/2-lista.getHeight()/2);
                    Sanguchito.sanguchito.add(lista, new Integer( 10 ));
                    lista.show();
                }
//                extPedido.get(pedidoSeleccionado).add(extras.get(seleccionado));
                
            }
            float precio=FacturacionControlador.retornarTotal(pedido,extPedido,cantExtras);
            tPrecioTotal.setText("S/ "+precio);
        }
        if(seccion==SECCION_PEDIDO){
            ambito=2;
            pedidoSeleccionado=seleccionado;
            catExtra.clear();
            catExtra=CategoriaProductoControlador.listaCategoriaExtraPorProducto(pedido.get(pedidoSeleccionado).getCatProdCod().getCatProdCod());
            extras.clear();
            if(!catExtra.isEmpty()){
                extras=ExtraControlador.listaExtrasPorCategoria(catExtra.get(0).getCatExtCod());
            }
       }
        ocultarBotones(bPedido);
        ocultarLabels(lPedido);
        ocultarBotones(bCategorias);
        ocultarBotones(bBotones);
        ocultarLabels(lBotones);
        bOpcionesProducto.setVisible(false);
        bCopiar.setVisible(false);
        bDetalle.setVisible(false);
        bEliminar.setVisible(false);
        actualizarPantalla();
    }
    public void agregarCantidad(int cantidad){
        extPedido.get(pedidoSeleccionado).add(extras.get(seleccionado));
        cantExtras.get(pedidoSeleccionado).add(cantidad);
        ocultarBotones(bPedido);
        ocultarLabels(lPedido);
        ocultarBotones(bCategorias);
        ocultarBotones(bBotones);
        ocultarLabels(lBotones);
        bOpcionesProducto.setVisible(false);
        bCopiar.setVisible(false);
        bDetalle.setVisible(false);
        bEliminar.setVisible(false);
        actualizarPantalla();
    }

    void mostrarOpciones() {
        ocultarBotones(bBotones);
        ocultarLabels(lBotones);
        bCopiar.setVisible(true);
        bDetalle.setVisible(true);
        bEliminar.setVisible(true);
    }
}
