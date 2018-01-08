
package Principal;

import Util.HibernateUtil;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import sakila.entity.Film;

/**
 * Clase Consulta, que hereda de JFrame
 * @author Felipon
 */
public class Consulta extends javax.swing.JFrame {

    /**
     * Método Consulta, que inicia los componentes del formulario
     */
    public Consulta() {
        initComponents();
    }
    
    /**
     * CONSTANTE DE LA CONSULTA
     */
    private static final String SENTENCIA = "from Film f WHERE f.title LIKE '%";
    
    /**
     * Método que recibe como parametro una consulta HQL. Vuelva los datos en una lista de Objetos y llama al método de
     * mostrar los datos obtenidos en la tabla.
     * 
     * @param hql String con la sentencia HQL
     */
    private void consultaTitulo (String hql) {
        try {
            //Iniciamos una sesión de Hibernate
            Session session = HibernateUtil.getSessionFactory().openSession();
            //Iniciamos una transacción
            session.beginTransaction();
            //Realizamos la consulta
            Query consulta = session.createQuery(hql);
            //Traspasamos a una lista de objetos el resultado de la consulta
            List resultList = consulta.list();
            //Enviamos los datos al método correspondiente que los tratará y los mostrará en pantalla
            muestraResultados(resultList);
            //Realizamos commit de la transacción al finalizar.
            session.getTransaction().commit();
        } catch (HibernateException he) {
            //En caso de existir errores de Hibernate, imprima los errores
            he.printStackTrace();
        }
    }
    
    /**
     * Método que obtiene el texto del cuadro de búsqueda y los concatena con la setencia y la expresión final, llamando 
     */
    private void ejecutaConsulta() {
        //Llamada al método consultaTitulo, pasa por parámetro el texto obtenido del cuadro concatenado
        consultaTitulo(SENTENCIA + txtBusqueda.getText() + "%'");
    }
    
    /**
     * Método muestraResultados. Recibe por parametro una Lista de Objetos, la cual tratará para dibujar en la tabla del
     * formulario.
     * 
     * @param rs Lista de Objetos
     */
    private void muestraResultados(List rs) {
        //Creamos un vector de cadena para los encabezados
        Vector<String> tableHeaders = new Vector<>();
        //Creamos un array de objetos para los datos
        Vector tableData = new Vector();
        //Creamos los encabezados
        tableHeaders.add("Titulo");
        tableHeaders.add("Descripcion");
        tableHeaders.add("Extras");
        //Recorremos el listado
        for (Object o : rs) {
            //Creamos un objeto pelicula que asignamos un objeto del listado
            Film film = (Film) o;
            //Creamos un vector de fila
            Vector<Object> oneRow = new Vector<>();
            //A ese vector, le asignamos el titulo, la descripción y los extras
            oneRow.add(film.getTitle());
            oneRow.add(film.getDescription());
            oneRow.add(film.getSpecialFeatures());
            //Añadimos la fila al vector de datos
            tableData.add(oneRow);
        }
        //Redibujamos el modelo de la tabla con los datos obtenidos
        tblResultados.setModel(new DefaultTableModel(tableData, tableHeaders));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblFrame = new javax.swing.JLabel();
        txtBusqueda = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblResultados = new javax.swing.JTable();
        btnConsulta = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblFrame.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lblFrame.setText("BUSQUEDA DE PELICULAS POR TITULO");

        tblResultados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblResultados);

        btnConsulta.setText("CONSULTAR");
        btnConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultaActionPerformed(evt);
            }
        });

        jLabel1.setText("TITULO A BUSCAR:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnConsulta))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 933, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(59, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblFrame)
                .addGap(155, 155, 155))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblFrame)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsulta)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultaActionPerformed
        ejecutaConsulta();
    }//GEN-LAST:event_btnConsultaActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Consulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Consulta().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsulta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFrame;
    private javax.swing.JTable tblResultados;
    private javax.swing.JTextField txtBusqueda;
    // End of variables declaration//GEN-END:variables

}
