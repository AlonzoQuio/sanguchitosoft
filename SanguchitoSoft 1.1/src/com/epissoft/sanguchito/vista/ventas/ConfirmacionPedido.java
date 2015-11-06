package com.epissoft.sanguchito.vista.ventas;

import IMPRIMIR.imprimirTicket;
import com.epissoft.sanguchito.controlador.clientecontrolador.ClienteControlador;
import com.epissoft.sanguchito.controlador.facturacioncontrolador.FacturacionControlador;
import com.epissoft.sanguchito.persistencia.Extras;
import com.epissoft.sanguchito.persistencia.Producto;
import com.epissoft.sanguchito.vista.Sanguchito;
import com.epissoft.sanguchito.vista.utilitarios.BotonesComunes;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.print.PrinterException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;

public class ConfirmacionPedido extends javax.swing.JInternalFrame {
    private ArrayList<Producto> producto;
    private ArrayList<ArrayList<Extras>> extras;
    private ArrayList<ArrayList<Integer>> cantidades;
    private static ArrayList<String> descrip;
    private  ArrayList<String> para;
    private float precio;
    private String nDNI;
    private int finalizado=0;
    private boolean conTarjeta=false;
    public ConfirmacionPedido(ArrayList<Producto> producto,ArrayList<ArrayList<Extras>> extras,ArrayList<ArrayList<Integer>> cantidades) {
        initComponents();
        
        tNPedido.setText(""+(FacturacionControlador.facturaAcual()+1));
        
        bImprCocina.setVisible(false);
        bImprTicket.setVisible(false);
        
        this.producto=producto;
        this.extras=extras;
        this.cantidades=cantidades;
        descrip=new ArrayList<>();
        para=new ArrayList<>();
        descrip.add("Nada");
        descrip.add("Poco");
        descrip.add("Normal");
        descrip.add("Mucho");
        try{
            DefaultTableModel tabla = (DefaultTableModel)tabla_prod.getModel();
            for(int i=0;i<producto.size();i++){
                Object[]data= {producto.get(i).getProDes(),"1",producto.get(i).getProPreCar(),"Para servir"};
                tabla.addRow(data);
                para.add("Para servir");
                for(int j=0;j<extras.get(i).size();j++){
                    if(extras.get(i).get(j).getExtPrecSolCar()!=0){
                        Object[]data2={"--"+extras.get(i).get(j).getExtDes(),""+cantidades.get(i).get(j),extras.get(i).get(j).getExtPrecSolCar(),"Para servir"};
                        tabla.addRow(data2);
                    }else{
                        Object[]data2={"--"+extras.get(i).get(j).getExtDes(),""+descrip.get(cantidades.get(i).get(j)),extras.get(i).get(j).getExtPrecSolCar(),"Para servir"};
                        tabla.addRow(data2);
                    }
                    para.add("Para servir");
                }
            }
            tabla_prod.setRowHeight(35);
            tabla_prod.setModel(tabla);
            
            precio=FacturacionControlador.retornarTotal(producto,extras,cantidades);
            tPrecioPedido.setText(""+precio);
            tPrecioTotal.setText(""+precio);
        }catch(Exception e){
            
        }
        
        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyReleased(KeyEvent keyEvent) {
                float descuento;
                try{
                    descuento=Float.parseFloat(tDescuento.getText());
                    if(descuento>precio){
                        tDescuento.setText(""+precio);
                        tPrecioTotal.setText("0.0");
                    }else{
                        tPrecioTotal.setText(""+(precio-descuento));
                    }
                    tVuelto.setText("0.0");
                    tPago.setText("0.0");
                }catch(Exception e){
                    tPrecioTotal.setText(""+(precio));
                }   
            }
            @Override
            public void keyTyped(KeyEvent keyEvent) {}

            @Override
            public void keyPressed(KeyEvent e) {}
        };
        tDescuento.addKeyListener(keyListener);
        
        KeyListener keyListener2 = new KeyListener() {
            @Override
            public void keyReleased(KeyEvent keyEvent) {
                float pago;
                float prec;
                try{
                    pago=Float.parseFloat(tPago.getText());
                    prec=Float.parseFloat(tPrecioTotal.getText());
                    if(pago>prec){
                        tVuelto.setText(""+(pago-prec));
                    }else{
                        tVuelto.setText("0.0");
                    }
                }catch(Exception e){
                    tVuelto.setText("0.0");
                }   
            }
            @Override
            public void keyTyped(KeyEvent keyEvent) {}

            @Override
            public void keyPressed(KeyEvent e) {}
        };
        tPago.addKeyListener(keyListener2);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Venta = new javax.swing.JPanel();
        tDescuento = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tPrecioPedido = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        bTerminar = new javax.swing.JButton();
        bImprTicket = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        tPrecioTotal = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tPago = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        tVuelto = new javax.swing.JTextField();
        bImprCocina = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        tNPedido = new javax.swing.JTextField();
        bTarjeta = new javax.swing.JButton();
        Titulo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        bCerrar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        Pedido = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_prod = new javax.swing.JTable();
        Cliente = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        tDNI = new javax.swing.JTextField();
        bBuscar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        tNombre = new javax.swing.JTextField();
        bLlevarComiendo = new javax.swing.JButton();
        bLlevar = new javax.swing.JButton();
        bServir = new javax.swing.JButton();

        Venta.setBackground(new java.awt.Color(255, 255, 255));
        Venta.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Venta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Cambria", 0, 18))); // NOI18N

        tDescuento.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        tDescuento.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tDescuento.setText("0.0");
        tDescuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tDescuentoActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        jLabel2.setText("Precio pedido:");

        tPrecioPedido.setEditable(false);
        tPrecioPedido.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        tPrecioPedido.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel4.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        jLabel4.setText("Descuento:");

        bTerminar.setBackground(new java.awt.Color(0, 51, 102));
        bTerminar.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        bTerminar.setForeground(new java.awt.Color(255, 255, 255));
        bTerminar.setText("<html><center><p>Terminar</p><p>venta</p></center></html>");
        bTerminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTerminarActionPerformed(evt);
            }
        });

        bImprTicket.setBackground(new java.awt.Color(0, 51, 102));
        bImprTicket.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        bImprTicket.setForeground(new java.awt.Color(255, 255, 255));
        bImprTicket.setText("<html><center>Imprimir ticket de venta</center></html>");
        bImprTicket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bImprTicketActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        jLabel7.setText("Total:");

        tPrecioTotal.setEditable(false);
        tPrecioTotal.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        tPrecioTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel8.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        jLabel8.setText("Pagó con:");

        tPago.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        tPago.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tPago.setText("0.0");

        jLabel9.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        jLabel9.setText("Vuelto");

        tVuelto.setEditable(false);
        tVuelto.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        tVuelto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        bImprCocina.setBackground(new java.awt.Color(0, 51, 102));
        bImprCocina.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        bImprCocina.setForeground(new java.awt.Color(255, 255, 255));
        bImprCocina.setText("<html><center>Imprimir orden de cocina</center></html>");
        bImprCocina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bImprCocinaActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        jLabel10.setText("N° de pedido:");

        tNPedido.setEditable(false);
        tNPedido.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        tNPedido.setForeground(new java.awt.Color(0, 204, 51));
        tNPedido.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tNPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tNPedidoActionPerformed(evt);
            }
        });

        bTarjeta.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        bTarjeta.setText("Con tarjeta");
        bTarjeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTarjetaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout VentaLayout = new javax.swing.GroupLayout(Venta);
        Venta.setLayout(VentaLayout);
        VentaLayout.setHorizontalGroup(
            VentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VentaLayout.createSequentialGroup()
                .addGroup(VentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, VentaLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(VentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(VentaLayout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(bTerminar, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, VentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(bImprTicket, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(bImprCocina, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(21, 21, 21))
                    .addGroup(VentaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(VentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(VentaLayout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(25, 25, 25)
                                .addComponent(tNPedido))
                            .addGroup(VentaLayout.createSequentialGroup()
                                .addGroup(VentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(18, 18, 18)
                                .addGroup(VentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tVuelto)
                                    .addComponent(tPago)
                                    .addGroup(VentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(tDescuento, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tPrecioPedido, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tPrecioTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, VentaLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(bTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        VentaLayout.setVerticalGroup(
            VentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VentaLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(VentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tNPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bTarjeta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(VentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tPrecioPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(VentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(VentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(tPrecioTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(VentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(VentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(tVuelto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(bTerminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bImprTicket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bImprCocina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );

        Titulo.setBackground(new java.awt.Color(0, 51, 102));
        Titulo.setForeground(new java.awt.Color(255, 255, 255));
        Titulo.setToolTipText("");

        jLabel1.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("PEDIDO");

        bCerrar.setBackground(new java.awt.Color(0, 51, 102));
        bCerrar.setForeground(new java.awt.Color(0, 51, 102));
        bCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/close2.png"))); // NOI18N
        bCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCerrarActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/extra.png"))); // NOI18N

        javax.swing.GroupLayout TituloLayout = new javax.swing.GroupLayout(Titulo);
        Titulo.setLayout(TituloLayout);
        TituloLayout.setHorizontalGroup(
            TituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        TituloLayout.setVerticalGroup(
            TituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TituloLayout.createSequentialGroup()
                .addGroup(TituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TituloLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(bCerrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(0, 10, Short.MAX_VALUE))
        );

        Pedido.setBackground(new java.awt.Color(255, 255, 255));

        tabla_prod.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        tabla_prod.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre producto", "Cantidad", "Precio", "Observacion"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabla_prod);
        tabla_prod.getColumnModel().getColumn(1).setMaxWidth(80);
        tabla_prod.getColumnModel().getColumn(2).setMaxWidth(80);
        tabla_prod.getColumnModel().getColumn(3).setMinWidth(180);
        tabla_prod.getColumnModel().getColumn(3).setPreferredWidth(180);
        tabla_prod.getColumnModel().getColumn(3).setMaxWidth(180);

        Cliente.setBackground(new java.awt.Color(255, 255, 255));
        Cliente.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Cambria", 0, 18))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        jLabel5.setText("DNI:");

        tDNI.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        tDNI.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        bBuscar.setBackground(new java.awt.Color(0, 51, 102));
        bBuscar.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        bBuscar.setForeground(new java.awt.Color(255, 255, 255));
        bBuscar.setText("Buscar");
        bBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBuscarActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        jLabel6.setText("Nombre");

        tNombre.setEditable(false);
        tNombre.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        tNombre.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        javax.swing.GroupLayout ClienteLayout = new javax.swing.GroupLayout(Cliente);
        Cliente.setLayout(ClienteLayout);
        ClienteLayout.setHorizontalGroup(
            ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ClienteLayout.createSequentialGroup()
                        .addComponent(tDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(92, 92, 92)
                        .addComponent(bBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 10, Short.MAX_VALUE))
        );
        ClienteLayout.setVerticalGroup(
            ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ClienteLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bBuscar))
                .addGap(18, 18, 18)
                .addGroup(ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        bLlevarComiendo.setBackground(new java.awt.Color(0, 51, 102));
        bLlevarComiendo.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        bLlevarComiendo.setForeground(new java.awt.Color(255, 255, 255));
        bLlevarComiendo.setText("<html><center>Para llevar comiendo</center></html>");
        bLlevarComiendo.setToolTipText("");
        bLlevarComiendo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLlevarComiendoActionPerformed(evt);
            }
        });

        bLlevar.setBackground(new java.awt.Color(0, 51, 102));
        bLlevar.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        bLlevar.setForeground(new java.awt.Color(255, 255, 255));
        bLlevar.setText("<html><center>Para llevar</center></html>");
        bLlevar.setToolTipText("");
        bLlevar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLlevarActionPerformed(evt);
            }
        });

        bServir.setBackground(new java.awt.Color(0, 51, 102));
        bServir.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        bServir.setForeground(new java.awt.Color(255, 255, 255));
        bServir.setText("<html><center>Para servir</center></html>");
        bServir.setToolTipText("");
        bServir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bServirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PedidoLayout = new javax.swing.GroupLayout(Pedido);
        Pedido.setLayout(PedidoLayout);
        PedidoLayout.setHorizontalGroup(
            PedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PedidoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(PedidoLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 23, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PedidoLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(bServir, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(bLlevar, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(bLlevarComiendo, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PedidoLayout.setVerticalGroup(
            PedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PedidoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bServir, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bLlevarComiendo, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bLlevar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Pedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Venta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(Titulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Titulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Pedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(Venta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCerrarActionPerformed
        if(finalizado==0){
            dispose();
        }else{
            BotonesComunes.limpiarVenta();
        }
    }//GEN-LAST:event_bCerrarActionPerformed

    private void tDescuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tDescuentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tDescuentoActionPerformed

    private void bTerminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTerminarActionPerformed
        String nomCliente=tNombre.getText();
        String resultado;
        if(nomCliente.length()>2){
            resultado=FacturacionControlador.agregarFacturacion(producto,extras,cantidades,tDescuento.getText(),Integer.parseInt(nDNI),conTarjeta);
        }else{
            resultado=FacturacionControlador.agregarFacturacion(producto,extras,cantidades,tDescuento.getText(),conTarjeta);
        }
        
        if(resultado.equals("CORRECTO")){
            finalizado=1;
            bImprTicket.setVisible(true);
            bImprCocina.setVisible(true);
            bTerminar.setVisible(false);
            bBuscar.setVisible(false);
            tDescuento.setEditable(false);
            tDNI.setEditable(false);
            
        }else{
            JOptionPane.showMessageDialog(null,resultado,"Error de ingreso",JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_bTerminarActionPerformed

    private void bImprTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bImprTicketActionPerformed
        try {
            imprimirTicket.imprimirTicket(producto, extras, cantidades,tPrecioTotal.getText(),tDescuento.getText());
        } catch (JRException | PrinterException e) {
            JOptionPane.showMessageDialog(null, "No se pudo imprimir");
        }
    }//GEN-LAST:event_bImprTicketActionPerformed

    private void bBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBuscarActionPerformed
        nDNI=tDNI.getText();
        tNombre.setText("");
        String resultado=ClienteControlador.buscarCliente(nDNI);
        if(resultado.length()<1){
            JOptionPane.showMessageDialog(null,"Ingrese un numero de DNI valido","Error",JOptionPane.ERROR_MESSAGE);
        }else{
            if(resultado.equals("NN")){
                OpcionesAgregarCliente opcion=new OpcionesAgregarCliente(this,Integer.parseInt(nDNI));
                opcion.setLocation(Sanguchito.sanguchito.getWidth()/2-opcion.getWidth()/2,Sanguchito.sanguchito.getHeight()/2-opcion.getHeight()/2);
                Sanguchito.sanguchito.add(opcion, new Integer( 10 ));
                opcion.show();
            }else{
                tNombre.setText(resultado);
            }
        }
    }//GEN-LAST:event_bBuscarActionPerformed

    private void bImprCocinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bImprCocinaActionPerformed
        
    }//GEN-LAST:event_bImprCocinaActionPerformed

    private void bLlevarComiendoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLlevarComiendoActionPerformed
        int seleccionado=tabla_prod.getSelectedRow();
        if(seleccionado>=0){
            para.set(seleccionado,"Para llevar comiendo");
            tabla_prod.setValueAt("Para llevar comiendo",seleccionado,3);
        }
    }//GEN-LAST:event_bLlevarComiendoActionPerformed

    private void bLlevarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLlevarActionPerformed
        int seleccionado=tabla_prod.getSelectedRow();
        if(seleccionado>=0){
            para.set(seleccionado,"Para llevar");
            tabla_prod.setValueAt("Para llevar",seleccionado,3);
        }
    }//GEN-LAST:event_bLlevarActionPerformed

    private void bServirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bServirActionPerformed
        int seleccionado=tabla_prod.getSelectedRow();
        if(seleccionado>=0){
            para.set(seleccionado,"Para servir");
            tabla_prod.setValueAt("Para servir",seleccionado,3);
        }
    }//GEN-LAST:event_bServirActionPerformed

    private void tNPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tNPedidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tNPedidoActionPerformed

    private void bTarjetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTarjetaActionPerformed
        if(conTarjeta){
            bTarjeta.setBackground(new Color(240,240,240));
            conTarjeta=false;
        }else{
            bTarjeta.setBackground(Color.GREEN);
            conTarjeta=true;
        }
    }//GEN-LAST:event_bTarjetaActionPerformed
    public void cargarCliente(String dni,String nombreCompleto){
        nDNI=dni;
        tDNI.setText(dni);
        tNombre.setText(nombreCompleto);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Cliente;
    private javax.swing.JPanel Pedido;
    private javax.swing.JPanel Titulo;
    private javax.swing.JPanel Venta;
    private javax.swing.JButton bBuscar;
    private javax.swing.JButton bCerrar;
    private javax.swing.JButton bImprCocina;
    private javax.swing.JButton bImprTicket;
    private javax.swing.JButton bLlevar;
    private javax.swing.JButton bLlevarComiendo;
    private javax.swing.JButton bServir;
    private javax.swing.JButton bTarjeta;
    private javax.swing.JButton bTerminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField tDNI;
    private javax.swing.JTextField tDescuento;
    private javax.swing.JTextField tNPedido;
    private javax.swing.JTextField tNombre;
    private javax.swing.JTextField tPago;
    private javax.swing.JTextField tPrecioPedido;
    private javax.swing.JTextField tPrecioTotal;
    private javax.swing.JTextField tVuelto;
    private javax.swing.JTable tabla_prod;
    // End of variables declaration//GEN-END:variables
}
