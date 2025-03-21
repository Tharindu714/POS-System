package gui;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.MySQL;

public class stock extends javax.swing.JFrame {
    
    private static HashMap<String, String> brandMap = new HashMap<>();
    private accept_table ACT;
    
    public void setAccept(accept_table ACT) {
        this.ACT = ACT;
    }
    
    private invoice INV;
    
    public void setINV(invoice INV) {
        this.INV = INV;
    }
    
    public stock() {
        initComponents();
        genarateProductumber();
        loadProduct();
        loadBrand();
        loadStock();
        loadReason();
    }
    
    private void genarateProductumber() {
        long id = System.currentTimeMillis();
        jTextField1.setText(String.valueOf(id));
    }
    
    private void loadProduct() {
        try {
            ResultSet resultSet = MySQL.execute("SELECT * FROM `product` "
                    + "INNER JOIN `brand` ON `product`.`brand_id` = `brand`.`id`");
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);
            while (resultSet.next()) {
                
                Vector<String> v = new Vector();
                
                v.add(resultSet.getString("id"));
                v.add(resultSet.getString("name"));
                v.add(resultSet.getString("brand.name"));
                v.add(resultSet.getString("brand.id"));
                v.add(resultSet.getString("reason"));
                
                model.addRow(v);
                jTable1.setModel(model);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void loadBrand() {
        try {
            Vector<String> vector = new Vector<>();
            vector.add("Select");
            
            ResultSet resultSet = MySQL.execute("SELECT * FROM `brand`");
            
            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                brandMap.put(resultSet.getString("name"), resultSet.getString("id"));
                
            }
            
            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox1.setModel(model);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void loadReason() {
        try {
            Vector<String> vector = new Vector<>();
            vector.add("Select");
            
            ResultSet resultSet = MySQL.execute("SELECT `reason` FROM `product`");
            
            while (resultSet.next()) {
                vector.add(resultSet.getString("reason"));
            }
            
            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox3.setModel(model);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void reset() {
        jTextField1.setText("");
        jTextField1.setEditable(true);
        jButton2.setEnabled(true);
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jDateChooser1.setDate(null);
        jComboBox1.setSelectedIndex(0);
        jTable1.clearSelection();
        genarateProductumber();
        jTextField1.grabFocus();
        
    }
    
    private void loadStock() {
        try {
            int row = jTable1.getSelectedRow();
            
            String query = "SELECT * FROM `stock` "
                    + "INNER JOIN `product` ON `stock`.`product_id` = `product`.`id`"
                    + "INNER JOIN `brand` ON `brand`.`id` = `product`.`brand_id`";
            if (row != -1) {
                String stockId = String.valueOf(jTable1.getValueAt(row, 0));
                query += "WHERE `stock`.`product_id`='" + stockId + "'";
            }
            
            if (query.contains("WHERE")) {
                query += "AND ";
            } else {
                query += "WHERE ";
            }
            
            String sort = String.valueOf(jComboBox2.getSelectedItem());
            query += "ORDER BY ";
            query = query.replace("WHERE ORDER BY ", "ORDER BY");
            
            query = query.replace("AND ORDER BY ", "ORDER BY ");
            
            if (sort.equals("Stock ID ASC")) {
                query += "`stock`.`id` ASC";
                
            } else if (sort.equals("Stock ID DESC")) {
                query += "`stock`.`id` DESC";
                
            } else if (sort.equals("Equipment ASC")) {
                query += "`product`.`name` ASC";
                
            } else if (sort.equals("Equipment DESC")) {
                query += "`product`.`name` DESC";
                
            } else if (sort.equals("Brand ASC")) {
                query += "`brand`.`name` ASC";
                
            } else if (sort.equals("Brand DESC")) {
                query += "`brand`.`name` DESC";
                
            } else if (sort.equals("Given Date ASC")) {
                query += "`stock`.`given_date` ASC";
                
            } else if (sort.equals("Given Date DESC")) {
                query += "`stock`.`given_date` DESC";
                
            } else if (sort.equals("Price ASC")) {
                query += "`stock`.`price` ASC";
                
            } else if (sort.equals("Price DESC")) {
                query += "`stock`.`price` DESC";
            }
            
            ResultSet resultSet = MySQL.execute(query);
            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            model.setRowCount(0);
            while (resultSet.next()) {
                
                Vector<String> v = new Vector();
                
                v.add(resultSet.getString("stock.id"));
                v.add(resultSet.getString("product.name"));
                v.add(resultSet.getString("brand.name"));
                v.add(resultSet.getString("price"));
                v.add(resultSet.getString("given_date"));
                v.add(resultSet.getString("product.reason"));
                model.addRow(v);
                jTable2.setModel(model);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton5 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jLabel1.setText("Equipment ID");

        jLabel2.setText("Equipment Type");

        jLabel3.setText("Brand Name");

        jLabel4.setText("New Brand ??");

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("+");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setText("Given Date");

        jButton2.setText("Add New Product");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Update Product");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Reset Window");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel8.setText("Short Reason");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox3ItemStateChanged(evt);
            }
        });
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField4)
                            .addComponent(jComboBox1, 0, 161, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jButton2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton4)
                            .addComponent(jLabel8)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Equipment ID", "Type", "Brand", "Brand ID", "Reason"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel6.setText("Sort By:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Stock ID ASC", "Stock ID DESC", "Equipment ASC", "Equipment DESC", "Brand ASC", "Brand DESC", "Given Date ASC", "Given Date DESC", "Price ASC", "Price DESC" }));
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });

        jButton5.setText("Reset Stock Table");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Stock ID", "Equipment", "Brand", "Payable Price", "Given Date", "Electrical Fault"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jButton6.setText("Close Window");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(80, Short.MAX_VALUE)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 722, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(198, 198, 198))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6)
                .addGap(0, 9, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String brandName = jTextField4.getText();
        if (brandName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Brand Name", "Warning", JOptionPane.WARNING_MESSAGE);
            
        } else {
            try {
                ResultSet resultSet = MySQL.execute("SELECT * FROM `brand` WHERE `name`='" + brandName + "'");
                
                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "Brand already added", "Warning", JOptionPane.WARNING_MESSAGE);
                    jTextField4.setText("");
                } else {
                    if (jComboBox1.getSelectedIndex() == 0) {
                        MySQL.execute("INSERT INTO`brand`(`name`)VALUES('" + brandName + "')");
                        JOptionPane.showMessageDialog(this, "New Brand Added", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        int response = JOptionPane.showConfirmDialog(this, "Do You want to UPDATE this Product", "Update This Product ?", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                        
                        if (response == JOptionPane.YES_OPTION) {
                            MySQL.execute("UPDATE `brand`"
                                    + "SET `name`='" + brandName + "'"
                                    + "WHERE `name`='" + String.valueOf(jComboBox1.getSelectedItem()) + "'");
                            JOptionPane.showMessageDialog(this, "Brand Updated", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                            
                        }
                    }
                    loadBrand();
                    jTextField4.setText("");
                    
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try {
            String id = jTextField1.getText();
            String name = jTextField2.getText();
            String reason = jTextField3.getText();
            String brand = String.valueOf(jComboBox1.getSelectedItem());
            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Product ID", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Product Name", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (brand.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please Select Brand Name", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (reason.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please give a proper Reason", "Warning", JOptionPane.WARNING_MESSAGE);
                
            } else {
                
                ResultSet resultSet = MySQL.execute("SELECT * FROM `product` "
                        + "WHERE `name` = '" + name + "'"
                        + "AND `brand_id` = '" + brandMap.get(brand) + "'"
                        + "AND `id` != '" + id + "'");
                
                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "Product already existed", "Warning", JOptionPane.WARNING_MESSAGE);
                    loadProduct();
                    reset();
                    
                } else {
                    MySQL.execute("UPDATE "
                            + "`product`SET"
                            + "`brand_id`='" + brandMap.get(brand) + "',`name`='" + name + "',`reason`='" + reason + "'"
                            + "WHERE `id`='" + id + "'");
                    JOptionPane.showMessageDialog(this, "Product Updated Successfully", "SUCCESSFUL", JOptionPane.INFORMATION_MESSAGE);
                    loadProduct();
                    reset();
                    
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        reset();
        loadStock();
        loadProduct();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            String id = jTextField1.getText();
            String name = jTextField2.getText();
            String reason = jTextField3.getText();
            String brand = String.valueOf(jComboBox1.getSelectedItem());
            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Product ID", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Product Name", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (brand.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please Select Brand Name", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (reason.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please give a proper Reason", "Warning", JOptionPane.WARNING_MESSAGE);
                
            } else {
                
                ResultSet resultSet = MySQL.execute("SELECT * FROM `product` "
                        + "WHERE `id` = '" + id + "'"
                        + "OR (`name` = '" + name + "'"
                        + "AND `brand_id` = '" + brandMap.get(brand) + "')");
                
                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "Product already existed", "Warning", JOptionPane.WARNING_MESSAGE);
                    loadProduct();
                    reset();
                    
                } else {
                    MySQL.execute("INSERT INTO "
                            + "`product`(`id`,`name`,`brand_id`,`reason`)"
                            + "VALUES('" + id + "','" + name + "','" + brandMap.get(brand) + "','" + reason + "')");
                    JOptionPane.showMessageDialog(this, "Product Registered Successfully", "SUCCESSFUL", JOptionPane.INFORMATION_MESSAGE);
                    loadProduct();
                    loadReason();
                    reset();
                    
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        jComboBox2.setSelectedItem(0);
        jTable2.clearSelection();
        loadStock();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        jButton2.setEnabled(false);
        int SelectedRow = jTable1.getSelectedRow();
        String id = String.valueOf(jTable1.getValueAt(SelectedRow, 0));
        jTextField1.setText(id);
        jTextField1.setEditable(false);
        
        String type = String.valueOf(jTable1.getValueAt(SelectedRow, 1));
        jTextField2.setText(type);
        
        String brand = String.valueOf(jTable1.getValueAt(SelectedRow, 2));
        jComboBox1.setSelectedItem(brand);
        
        String reason = String.valueOf(jTable1.getValueAt(SelectedRow, 4));
        jTextField3.setText(reason);
        
        loadStock();
        if (evt.getClickCount() == 3) {
            if (ACT != null) {
                ACT.getjTextField4().setText(String.valueOf(jTable1.getValueAt(SelectedRow, 0)));
                ACT.getLabel8().setText(String.valueOf(jTable1.getValueAt(SelectedRow, 1)));
                ACT.getlabel9().setText(String.valueOf(jTable1.getValueAt(SelectedRow, 2)));
                ACT.getlabel10().setText(String.valueOf(jTable1.getValueAt(SelectedRow, 4)));
                this.dispose();
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        // TODO add your handling code here:
        loadStock();
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        if (INV != null) {
            int row = jTable2.getSelectedRow();
            INV.getjTextField3().setText(String.valueOf(jTable2.getValueAt(row, 0)));
            INV.getlabel5().setText(String.valueOf(jTable2.getValueAt(row, 2)));
            INV.getjLabel7().setText(String.valueOf(jTable2.getValueAt(row, 1)));
            INV.getjTextField4().setText(String.valueOf(jTable2.getValueAt(row, 3)));
            INV.getLabel19().setText(String.valueOf(jTable2.getValueAt(row, 5)));
            
            this.dispose();
            
        }
    }//GEN-LAST:event_jTable2MouseClicked

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jComboBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox3ItemStateChanged
        // TODO add your handling code here:
        jTextField3.setText(String.valueOf(jComboBox3.getSelectedItem()));
    }//GEN-LAST:event_jComboBox3ItemStateChanged
    
    public static void main(String args[]) {
        FlatMacDarkLaf.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
