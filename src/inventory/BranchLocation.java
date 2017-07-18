/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory;


import java.awt.Toolkit;
import java.sql.*;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;



/**
 *
 * @author PC-1
 */
public class BranchLocation extends javax.swing.JFrame {
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    /**
     * Creates new form BranchLocation
     */
private static BranchLocation obj = null;

    private BranchLocation() {
        initComponents();
        conn = javaconnect.ConnecrDb();
        updateBranchTable();
        updateLocationTable();
        hideHelp();
        setIcon();
    }
    
    public static BranchLocation getObj(){
        if (obj == null){
            obj = new BranchLocation();
        }
        return obj;
    }
    
    public void setIcon(){
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon1.png")));
    }
    
    private void hideHelp(){
        mnuHelper.setVisible(false);
        lblBranch.setVisible(false);
        lblLoc.setVisible(false);
        lblID.setVisible(false);
        mnu.setVisible(false);
    }
    
    private void clearTextB(){
        txtBranchID.setText("");
	txtBranchAdd.setText("");
	txtBranch.setText("");
        txtTel.setText("");
    }
    
    private void disableComponentsB(){
        txtBranchID.setEnabled(false);
	txtBranchAdd.setEnabled(false);
	txtBranch.setEnabled(false);
        txtTel.setEnabled(false);
	cmdBranchSave.setEnabled(false);
	cmdBranchCancel.setEnabled(false);
        cmdAddB.setEnabled(true);
        cmdUpdB.setEnabled(true);
        cmdDelB.setEnabled(true);
        txtBranchSearchF.setEnabled(true);
    }
    
    private void enableComponentsB(){
        txtBranchID.setEnabled(true);
	txtBranchAdd.setEnabled(true);
	txtBranch.setEnabled(true);
        txtTel.setEnabled(true);
	cmdBranchSave.setEnabled(true);
	cmdBranchCancel.setEnabled(true);
        cmdAddB.setEnabled(false);
        cmdUpdB.setEnabled(false);
        cmdDelB.setEnabled(false);
        txtBranchSearchF.setEnabled(false);
    }
    
    private void clearTextL(){
        txtLocation.setText("");
    }
    
    private void disableComponentsL(){
        txtLocSearchF.setEnabled(true);
        txtLocation.setEnabled(false);
	cmdLocSave.setEnabled(false);
	cmdLocCancel.setEnabled(false);
        cmdAddL.setEnabled(true);
        cmdUpdL.setEnabled(true);
        cmdDelL.setEnabled(true);
    }
    
    private void enableComponentsL(){
        txtLocation.setEnabled(true);
	cmdLocSave.setEnabled(true);
	cmdLocCancel.setEnabled(true);
        cmdAddL.setEnabled(false);
        cmdUpdL.setEnabled(false);
        cmdDelL.setEnabled(false);
        txtLocSearchF.setEnabled(false);
    }
        
    private void updateBranchTable(){
        try{
            //Initializes the SQL statement
            String sql = "select * from branch;";
            pst = conn.prepareStatement(sql); //Prepares the sql statement
            rs = pst.executeQuery(); //Executes the prepared sql statement
            //Fill the contents of the Equipments table
            //to tbpEquipUpd
            tblBranch.setModel(DbUtils.resultSetToTableModel(rs));
            
        //Adds a SQLException, that will display "Incorrect SQL Syntax
        //in console if the SQL statement executed is incorrect
        }catch(SQLException e){
            System.err.println("Incorrect SQL Syntax");
        }
    }
    
    private void updateLocationTable(){
        try{
            //Initializes the SQL statement
            String sql = "select * from location;";
            pst = conn.prepareStatement(sql); //Prepares the sql statement
            rs = pst.executeQuery(); //Executes the prepared sql statement
            //Fill the contents of the Equipments table
            //to tbpEquipUpd
            tblLoc.setModel(DbUtils.resultSetToTableModel(rs));
            
        //Adds a SQLException, that will display "Incorrect SQL Syntax
        //in console if the SQL statement executed is incorrect
        }catch(SQLException e){
            System.err.println("Incorrect SQL Syntax");
        }
    }
    
    private void editBranch(){
        try{
            String id = txtBranchID.getText();
            String add = txtBranchAdd.getText();
            String name = txtBranch.getText();
            String tel = txtTel.getText();
            
            String sql = "update branch set branch_id='"+id+"', branch='"+name+"', address='"+add+"', " 
                    + " telephone = '"+tel+"' where branch_id='"+id+"' ";
            pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Information Updated",
                    "Updated",JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){
            e.printStackTrace();
        }
        updateBranchTable();
    }
    
    private void addBranch(){
        try{
            String sql = "insert into branch (branch_id,branch,address,telephone) "
                    + "values (?,?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, txtBranchID.getText());
            pst.setString(2, txtBranch.getText());
            pst.setString(3, txtBranchAdd.getText());
            pst.setString(4, txtTel.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Information Saved!",
                    "Saved to Database",JOptionPane.INFORMATION_MESSAGE);
        }catch(SQLException e){
            System.err.println("Invalid SQL Syntax!");
        }catch(Exception e){
            e.printStackTrace();
        }
        updateBranchTable();
    }
    
    private void editLocation(){
        try{
            String loc = txtLocation.getText();
            
            String sql = "update location set locations='"+loc+"' " 
                    + "where locations='"+loc+"' ";
            pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Information Updated",
                    "Updated",JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){
            e.printStackTrace();
        }
        updateLocationTable();
    }
    
    private void addLocation(){
        try{
            String sql = "insert into location (locations) "
                    + "values (?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, txtLocation.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Information Saved!",
                    "Saved to Database",JOptionPane.INFORMATION_MESSAGE);
        }catch(SQLException e){
            System.err.println("Invalid SQL Syntax!");
        }catch(Exception e){
            e.printStackTrace();
        }
        updateLocationTable();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtBranch = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtBranchAdd = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        txtBranchID = new javax.swing.JTextField();
        cmdBranchSave = new javax.swing.JButton();
        cmdBranchCancel = new javax.swing.JButton();
        txtTel = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtBranchSearchF = new javax.swing.JTextField();
        cmdAddB = new javax.swing.JButton();
        cmdUpdB = new javax.swing.JButton();
        cmdDelB = new javax.swing.JButton();
        lblBranch = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBranch = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtBranchSearchT = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtLocation = new javax.swing.JTextField();
        cmdLocSave = new javax.swing.JButton();
        cmdLocCancel = new javax.swing.JButton();
        txtLocSearchF = new javax.swing.JTextField();
        cmdAddL = new javax.swing.JButton();
        cmdUpdL = new javax.swing.JButton();
        cmdDelL = new javax.swing.JButton();
        lblLoc = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtLocSearchT = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblLoc = new javax.swing.JTable();
        mnu = new javax.swing.JMenuBar();
        mnuHelper = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Branch & Location");

        jPanel3.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED)));

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setText("Branch Address");

        txtBranch.setDocument(new JTextFieldLimit(25));
        txtBranch.setEnabled(false);
        txtBranch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBranchKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBranchKeyReleased(evt);
            }
        });

        jLabel1.setText("Branch ID");

        txtBranchAdd.setDocument(new JTextFieldLimit(100));
        txtBranchAdd.setColumns(20);
        txtBranchAdd.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        txtBranchAdd.setRows(5);
        txtBranchAdd.setEnabled(false);
        txtBranchAdd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBranchAddKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(txtBranchAdd);

        jLabel4.setText("Branch Name");

        txtBranchID.setDocument(new JTextFieldLimit(10));
        txtBranchID.setEnabled(false);
        txtBranchID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBranchIDKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBranchIDKeyReleased(evt);
            }
        });

        cmdBranchSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/gfxresources/save.png"))); // NOI18N
        cmdBranchSave.setMnemonic('s');
        cmdBranchSave.setText("Save");
        cmdBranchSave.setEnabled(false);
        cmdBranchSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBranchSaveActionPerformed(evt);
            }
        });

        cmdBranchCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/gfxresources/cancel.png"))); // NOI18N
        cmdBranchCancel.setMnemonic('c');
        cmdBranchCancel.setText("Cancel");
        cmdBranchCancel.setEnabled(false);
        cmdBranchCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBranchCancelActionPerformed(evt);
            }
        });

        txtTel.setDocument(new JTextFieldLimit(30));
        txtTel.setEnabled(false);
        txtTel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTelKeyPressed(evt);
            }
        });

        jLabel7.setText("Telephone");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(cmdBranchSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmdBranchCancel))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                    .addComponent(txtBranchID)
                    .addComponent(txtBranch)
                    .addComponent(txtTel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtBranchID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtBranch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdBranchSave)
                    .addComponent(cmdBranchCancel))
                .addContainerGap())
        );

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/gfxresources/Search.png"))); // NOI18N
        jLabel9.setText("Search");

        txtBranchSearchF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBranchSearchFKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBranchSearchFKeyReleased(evt);
            }
        });

        cmdAddB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/gfxresources/map_add.png"))); // NOI18N
        cmdAddB.setMnemonic('a');
        cmdAddB.setText("Add");
        cmdAddB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAddBActionPerformed(evt);
            }
        });

        cmdUpdB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/gfxresources/map_edit.png"))); // NOI18N
        cmdUpdB.setMnemonic('e');
        cmdUpdB.setText("Update");
        cmdUpdB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdUpdBActionPerformed(evt);
            }
        });

        cmdDelB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/gfxresources/map_delete.png"))); // NOI18N
        cmdDelB.setMnemonic('d');
        cmdDelB.setText("Delete");
        cmdDelB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDelBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(txtBranchSearchF))
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cmdUpdB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmdDelB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmdAddB, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblBranch))
                        .addGap(72, 72, 72))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(lblID)
                        .addGap(81, 81, 81))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(lblBranch)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtBranchSearchF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(cmdAddB)
                        .addGap(48, 48, 48)
                        .addComponent(cmdUpdB)
                        .addGap(48, 48, 48)
                        .addComponent(cmdDelB)
                        .addGap(18, 18, 18)
                        .addComponent(lblID)))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Form View", jPanel3);

        jPanel4.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED)));

        tblBranch.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1"
            }
        ));
        jScrollPane1.setViewportView(tblBranch);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/gfxresources/Search.png"))); // NOI18N
        jLabel2.setText("Search");

        txtBranchSearchT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBranchSearchTKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBranchSearchTKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(txtBranchSearchT, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtBranchSearchT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Table View", jPanel4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Branch", jPanel1);

        jPanel5.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED)));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/gfxresources/Search.png"))); // NOI18N
        jLabel8.setText("Search");

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setText("Location");

        txtLocation.setDocument(new JTextFieldLimit(30));
        txtLocation.setEnabled(false);
        txtLocation.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLocationKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLocationKeyReleased(evt);
            }
        });

        cmdLocSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/gfxresources/Save.png"))); // NOI18N
        cmdLocSave.setMnemonic('s');
        cmdLocSave.setText("Save");
        cmdLocSave.setEnabled(false);
        cmdLocSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdLocSaveActionPerformed(evt);
            }
        });

        cmdLocCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/gfxresources/Cancel.png"))); // NOI18N
        cmdLocCancel.setMnemonic('c');
        cmdLocCancel.setText("Cancel");
        cmdLocCancel.setEnabled(false);
        cmdLocCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdLocCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(21, 21, 21)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtLocation)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(cmdLocSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
                        .addComponent(cmdLocCancel)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(115, 115, 115)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdLocSave)
                    .addComponent(cmdLocCancel))
                .addContainerGap(87, Short.MAX_VALUE))
        );

        txtLocSearchF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLocSearchFKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLocSearchFKeyReleased(evt);
            }
        });

        cmdAddL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/gfxresources/book_add.png"))); // NOI18N
        cmdAddL.setMnemonic('a');
        cmdAddL.setText("Add");
        cmdAddL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAddLActionPerformed(evt);
            }
        });

        cmdUpdL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/gfxresources/book_edit.png"))); // NOI18N
        cmdUpdL.setMnemonic('e');
        cmdUpdL.setText("Update");
        cmdUpdL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdUpdLActionPerformed(evt);
            }
        });

        cmdDelL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/gfxresources/book_delete.png"))); // NOI18N
        cmdDelL.setMnemonic('d');
        cmdDelL.setText("Delete");
        cmdDelL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDelLActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(txtLocSearchF))
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmdUpdL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmdAddL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmdDelL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(lblLoc)))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtLocSearchF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(cmdAddL)
                        .addGap(52, 52, 52)
                        .addComponent(cmdUpdL)
                        .addGap(57, 57, 57)
                        .addComponent(cmdDelL)
                        .addGap(29, 29, 29)
                        .addComponent(lblLoc)))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Form View", jPanel5);

        jPanel6.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED)));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/gfxresources/Search.png"))); // NOI18N
        jLabel3.setText("Search");

        txtLocSearchT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLocSearchTKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLocSearchTKeyReleased(evt);
            }
        });

        tblLoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1"
            }
        ));
        jScrollPane2.setViewportView(tblLoc);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(txtLocSearchT, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtLocSearchT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Table View", jPanel6);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jTabbedPane3)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Location", jPanel2);

        mnu.add(mnuHelper);

        setJMenuBar(mnu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBranchSearchFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBranchSearchFKeyReleased
        String a = "";
        if (a.equals(txtBranchSearchF.getText())){
            clearTextB();
        }else{
            String res = txtBranchSearchF.getText();
            try{
                String sql = "select * from branch where branch_id like ? or branch like ?  or address like ? ";
                pst = conn.prepareStatement(sql);
                pst.setString(1, res + "%");
                pst.setString(2, res + "%");
                pst.setString(3, res + "%");
                rs = pst.executeQuery();
                if(rs.next()){
                    String BID = rs.getString("branch_id");
                    String BN = rs.getString("branch");
                    String BA = rs.getString("Address");

                    txtBranchID.setText(BID);
                    txtBranch.setText(BN);
                    txtBranchAdd.setText(BA);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_txtBranchSearchFKeyReleased

    private void cmdAddBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAddBActionPerformed
        // TODO add your handling code here:
        mnuHelper.setText("AddB");
        enableComponentsB();
        clearTextB();
    }//GEN-LAST:event_cmdAddBActionPerformed

    private void cmdUpdBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdUpdBActionPerformed
        String id = txtBranchID.getText();
        if(id.equals("")){
            JOptionPane.showMessageDialog(null, "No Data to be Updated", 
                "No Data",JOptionPane.WARNING_MESSAGE);
        }else{
            mnuHelper.setText("UpdB");
            enableComponentsB();
            txtBranchID.setEnabled(false);
        }
    }//GEN-LAST:event_cmdUpdBActionPerformed

    private void cmdDelBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDelBActionPerformed
        String type = new Main().lblType.getText();
        if(type.equals("Assistant")){
            JOptionPane.showMessageDialog(null, "Insufficient Account Privillege",
                    "Invalid Account Type",JOptionPane.ERROR_MESSAGE);
        }else{
            String id = txtBranchID.getText();
            if(id.equals("")){
                JOptionPane.showMessageDialog(null, "No Information selected",
                        "Cannot delete Empty record",JOptionPane.ERROR_MESSAGE);
            }else{
                int q = JOptionPane.showConfirmDialog(null,
                        "Do you really want to delete the selected information?","Delete Record",
                        JOptionPane.YES_NO_OPTION);
                if (q==0){
                    String sql = "delete from branch where branch_id =?";
                    try{
                        pst = conn.prepareStatement(sql);
                        pst.setString(1, txtBranchID.getText());
                        pst.execute();
                        JOptionPane.showMessageDialog(null, "Information Deleted!",
                                "Deleted to Database",JOptionPane.INFORMATION_MESSAGE);
                    }catch(SQLException e){
                        System.err.println("Invalid SQL Syntax!");
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    updateBranchTable();
                    clearTextB();
                }
            }
        }
    }//GEN-LAST:event_cmdDelBActionPerformed

    private void cmdDelLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDelLActionPerformed
        String type = new Main().lblType.getText();
        if(type.equals("Assistant")){
            JOptionPane.showMessageDialog(null, "Insufficient Account Privillege",
                    "Invalid Account Type",JOptionPane.ERROR_MESSAGE);
        }else{
            String loc = txtLocation.getText();
            if(loc.equals("")){
                JOptionPane.showMessageDialog(null, "No Information selected",
                        "Cannot delete Empty record",JOptionPane.ERROR_MESSAGE);
            }else{
                int q = JOptionPane.showConfirmDialog(null,
                        "Do you really want to delete the selected information?","Delete Record",
                        JOptionPane.YES_NO_OPTION);
                if (q==0){
                    String sql = "delete from location where locations =?";
                    try{
                        pst = conn.prepareStatement(sql);
                        pst.setString(1, txtLocation.getText());
                        pst.execute();
                        JOptionPane.showMessageDialog(null, "Information Deleted!",
                                "Deleted to Database",JOptionPane.INFORMATION_MESSAGE);
                    }catch(SQLException e){
                        System.err.println("Invalid SQL Syntax!");
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    updateLocationTable();
                    clearTextL();
                }
            }
        }
    }//GEN-LAST:event_cmdDelLActionPerformed

    private void cmdBranchSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBranchSaveActionPerformed
        String id = txtBranchID.getText();
        String name = txtBranch.getText();
        String add = txtBranchAdd.getText();
        if((id.equals("")) || (name.equals("")) || (add.equals(""))){
            JOptionPane.showMessageDialog(null, "Fill-up the blank fields!", "Blank Field",
                    JOptionPane.WARNING_MESSAGE);
        }else if(lblBranch.getText().equals(txtBranch.getText())){
            JOptionPane.showMessageDialog(null,"Branch already exist!",
                "Duplicate Branch Name",JOptionPane.ERROR_MESSAGE);
            txtBranch.setText("");
        }else if(lblID.getText().equals(txtBranchID.getText())){
            JOptionPane.showMessageDialog(null,"Branch ID already exist!",
                    "Duplicate Branch ID",JOptionPane.ERROR_MESSAGE);
            txtBranchID.setText("");
        }else{
            String a = "AddB";
            String u = "UpdB";
            if (a.equals(mnuHelper.getText())){
                addBranch();
                System.out.println("add");
            }else if(u.equals(mnuHelper.getText())){
                editBranch();
                System.out.println("update");
            }else{
                System.err.println("error!");
            }
            disableComponentsB();
            clearTextB();
        }
    }//GEN-LAST:event_cmdBranchSaveActionPerformed

    private void cmdBranchCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBranchCancelActionPerformed
        disableComponentsB();
        clearTextB();
    }//GEN-LAST:event_cmdBranchCancelActionPerformed

    private void cmdLocSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdLocSaveActionPerformed
        if(lblLoc.getText().equals(txtLocation.getText())){
            JOptionPane.showMessageDialog(null,"Location already exists!",
                "Duplicate Location",JOptionPane.ERROR_MESSAGE);
            txtLocation.setText("");
        }else{
            String a = "AddL";
            String u = "UpdL";
            if (a.equals(mnuHelper.getText())){
                addLocation();
                System.out.println("add");
            }else if(u.equals(mnuHelper.getText())){
                editLocation();
                System.out.println("update");
            }else{
                System.err.println("error!");
            }
            disableComponentsL();
            clearTextL();
        }
    }//GEN-LAST:event_cmdLocSaveActionPerformed

    private void cmdLocCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdLocCancelActionPerformed
        disableComponentsL();
        clearTextL();
    }//GEN-LAST:event_cmdLocCancelActionPerformed

    private void txtLocSearchFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLocSearchFKeyReleased
        String a = "";
        if(a.equals(txtLocSearchF.getText())){
            clearTextL();
        }else{
            String res = txtLocSearchF.getText();
            try{
                String sql = "select * from location where locations like ? ";
                pst = conn.prepareStatement(sql);
                pst.setString(1, res + "%");
                rs = pst.executeQuery();
                if(rs.next()){
                    String loc = rs.getString("locations");

                    txtLocation.setText(loc);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_txtLocSearchFKeyReleased

    private void txtBranchSearchTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBranchSearchTKeyReleased
        String res = txtBranchSearchT.getText();
        try{
            String sql = "select * from branch where branch_id like ? or branch like ? "
                    + "or address like ? or telephone like ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, res + "%");
            pst.setString(2, res + "%");
            pst.setString(3, res + "%");
            pst.setString(4, res + "%");
            rs = pst.executeQuery();
            tblBranch.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtBranchSearchTKeyReleased

    private void txtLocSearchTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLocSearchTKeyReleased
        String res = txtLocSearchT.getText();
        try{
            String sql = "select * from location where locations like ? ";
            pst = conn.prepareStatement(sql);
            pst.setString(1, "%" + res + "%");
            rs = pst.executeQuery();
            tblLoc.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtLocSearchTKeyReleased

    private void cmdAddLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAddLActionPerformed
        mnuHelper.setText("AddL");
        enableComponentsL();
        clearTextL();
    }//GEN-LAST:event_cmdAddLActionPerformed

    private void cmdUpdLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdUpdLActionPerformed
        String loc = txtLocation.getText();
        if(loc.equals("")){
            JOptionPane.showMessageDialog(null, "No Data to be Updated", 
                    "No Data",JOptionPane.WARNING_MESSAGE);
        }else{
            mnuHelper.setText("UpdL");
            enableComponentsL();
        }
    }//GEN-LAST:event_cmdUpdLActionPerformed

    private void txtBranchSearchFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBranchSearchFKeyPressed
        evt.getKeyChar();
        if (evt.getKeyCode() >= 65 && evt.getKeyCode() <= 90 || 
                evt.getKeyCode() == 32 || evt.getKeyCode() == 8 || 
		evt.getKeyCode() >= 96 && evt.getKeyCode() <=105 || 
                evt.getKeyCode() >= 44 && evt.getKeyCode() <= 46 ||
                evt.getKeyCode() >= 108 && evt.getKeyCode() <= 110 ||
                evt.getKeyCode() >= 48 && evt.getKeyCode() <= 57 ||
                evt.getKeyCode() == 13 || evt.getKeyCode() >= 16 && 
                evt.getKeyCode() <= 20 || evt.getKeyCode() >= 37 &&
                evt.getKeyCode() <= 40 || evt.getKeyCode() == 10 || 
                evt.getKeyCode() == 27){
            
        }else{
            JOptionPane.showMessageDialog(null,"Invalid character entered!",
                    "Invalid character",JOptionPane.WARNING_MESSAGE);
            evt.setKeyCode(8);
        }
    }//GEN-LAST:event_txtBranchSearchFKeyPressed

    private void txtBranchAddKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBranchAddKeyPressed
        evt.getKeyChar();
        if (evt.getKeyCode() >= 65 && evt.getKeyCode() <= 90 || evt.getKeyCode() == 32 ||
                evt.getKeyCode() >= 8 && evt.getKeyCode() <= 10 || evt.getKeyCode() >= 96 && 
                evt.getKeyCode() <=105 || evt.getKeyCode() >= 44 && 
                evt.getKeyCode() <= 46 || evt.getKeyCode() >= 108 && 
                evt.getKeyCode() <= 110 || evt.getKeyCode() >= 48 && 
                evt.getKeyCode() <= 57 || evt.getKeyCode() == 13 || 
                evt.getKeyCode() >= 16 && evt.getKeyCode() <= 20 || 
                evt.getKeyCode() >= 37 && evt.getKeyCode() <= 40 ||
                evt.getKeyCode() >= 16 && evt.getKeyCode() <= 20 || 
                evt.getKeyCode() >= 37 && evt.getKeyCode() <= 40 || 
                evt.getKeyCode() == 144 || evt.getKeyCode() == 27){
            
        }else{
            JOptionPane.showMessageDialog(null,"Invalid character entered!",
                    "Invalid character",JOptionPane.WARNING_MESSAGE);
            evt.setKeyCode(8);
        }
    }//GEN-LAST:event_txtBranchAddKeyPressed

    private void txtBranchIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBranchIDKeyPressed
        evt.getKeyChar();
        if (evt.getKeyCode() >= 48 && evt.getKeyCode() <= 57 || 
                evt.getKeyCode() >= 96 && evt.getKeyCode() <= 105 ||
                evt.getKeyCode() >= 16 && evt.getKeyCode() <= 20 ||
                evt.getKeyCode() == 10 || evt.getKeyCode() >= 37 &&
                evt.getKeyCode() <= 40 || evt.getKeyCode() == 144 ||    
                evt.getKeyCode() == 8 || evt.getKeyCode() == 27){
                
            
        }else{
            JOptionPane.showMessageDialog(null,"Invalid character entered!",
                    "Invalid character",JOptionPane.WARNING_MESSAGE);
            evt.setKeyCode(8);
        }
    }//GEN-LAST:event_txtBranchIDKeyPressed

    private void txtBranchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBranchKeyPressed
        evt.getKeyChar();
        if (evt.getKeyCode() >= 65 && evt.getKeyCode() <= 90 ||
                evt.getKeyCode() == 32 || evt.getKeyCode() == 8 || 
                evt.getKeyCode() == 13 || evt.getKeyCode() >= 16 && 
                evt.getKeyCode() <= 20 || evt.getKeyCode() >= 37 &&
                evt.getKeyCode() <= 40 || evt.getKeyCode() == 144 ||
                evt.getKeyCode() == 10 || evt.getKeyCode() == 97 || 
                evt.getKeyCode() >= 100 && evt.getKeyCode() <= 102 || 
                evt.getKeyCode() == 27){
            
        }else{
            JOptionPane.showMessageDialog(null,"Invalid character entered!",
                    "Invalid character",JOptionPane.WARNING_MESSAGE);
            evt.setKeyCode(8);
        }
    }//GEN-LAST:event_txtBranchKeyPressed

    private void txtBranchSearchTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBranchSearchTKeyPressed
        evt.getKeyChar();
        if (evt.getKeyCode() >= 65 && evt.getKeyCode() <= 90 || 
                evt.getKeyCode() == 32 || evt.getKeyCode() == 8 || 
		evt.getKeyCode() >= 96 && evt.getKeyCode() <=105 || 
                evt.getKeyCode() >= 44 && evt.getKeyCode() <= 46 ||
                evt.getKeyCode() >= 108 && evt.getKeyCode() <= 110 ||
                evt.getKeyCode() >= 48 && evt.getKeyCode() <= 57 ||
                evt.getKeyCode() == 13 || evt.getKeyCode() >= 16 && 
                evt.getKeyCode() <= 20 || evt.getKeyCode() >= 37 &&
                evt.getKeyCode() <= 40 || evt.getKeyCode() == 144 ||
                evt.getKeyCode() == 10 || evt.getKeyCode() == 27){
            
        }else{
            JOptionPane.showMessageDialog(null,"Invalid character entered!",
                    "Invalid character",JOptionPane.WARNING_MESSAGE);
            evt.setKeyCode(8);
        }
    }//GEN-LAST:event_txtBranchSearchTKeyPressed

    private void txtLocSearchFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLocSearchFKeyPressed
        evt.getKeyChar();
        if (evt.getKeyCode() >= 65 && evt.getKeyCode() <= 90 || 
                evt.getKeyCode() >= 48 && evt.getKeyCode() <= 57 || 
                evt.getKeyCode() >= 96 && evt.getKeyCode() <= 105 ||
                evt.getKeyCode() == 32 || evt.getKeyCode() >= 16 && 
                evt.getKeyCode() <= 20 || evt.getKeyCode() >= 37 &&
                evt.getKeyCode() <= 40 || evt.getKeyCode() == 144 || 
                evt.getKeyCode() == 10 || evt.getKeyCode() == 8 || 
                evt.getKeyCode() == 27){
            
        }else{
            JOptionPane.showMessageDialog(null,"Invalid character entered!",
                    "Invalid character",JOptionPane.WARNING_MESSAGE);
            evt.setKeyCode(8);
        }
    }//GEN-LAST:event_txtLocSearchFKeyPressed

    private void txtLocSearchTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLocSearchTKeyPressed
        evt.getKeyChar();
        if (evt.getKeyCode() >= 65 && evt.getKeyCode() <= 90 || 
                evt.getKeyCode() >= 48 && evt.getKeyCode() <= 57 || 
                evt.getKeyCode() >= 96 && evt.getKeyCode() <= 105 ||
                evt.getKeyCode() == 32 || evt.getKeyCode() >= 16 && 
                evt.getKeyCode() <= 20 || evt.getKeyCode() >= 37 &&
                evt.getKeyCode() <= 40 || evt.getKeyCode() == 144 ||
                evt.getKeyCode() == 10 || evt.getKeyCode() == 8 || 
                evt.getKeyCode() == 27){
            
        }else{
            JOptionPane.showMessageDialog(null,"Invalid character entered!",
                    "Invalid character",JOptionPane.WARNING_MESSAGE);
            evt.setKeyCode(8);
        }
    }//GEN-LAST:event_txtLocSearchTKeyPressed

    private void txtLocationKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLocationKeyPressed
        evt.getKeyChar();
        if (evt.getKeyCode() >= 65 && evt.getKeyCode() <= 90 || 
                evt.getKeyCode() >= 48 && evt.getKeyCode() <= 57 || 
                evt.getKeyCode() >= 96 && evt.getKeyCode() <= 105 ||
                evt.getKeyCode() == 32 || evt.getKeyCode() >= 16 && 
                evt.getKeyCode() <= 20 || evt.getKeyCode() >= 37 &&
                evt.getKeyCode() <= 40 || evt.getKeyCode() == 144 || 
                evt.getKeyCode() == 10 || evt.getKeyCode() == 8 || 
                evt.getKeyCode() == 97 || evt.getKeyCode() >= 100 && 
                evt.getKeyCode() <= 102 || evt.getKeyCode() == 27){
            
        }else{
            JOptionPane.showMessageDialog(null,"Invalid character entered!",
                    "Invalid character",JOptionPane.WARNING_MESSAGE);
            evt.setKeyCode(8);
        }
    }//GEN-LAST:event_txtLocationKeyPressed

    private void txtBranchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBranchKeyReleased
        String brchn = txtBranch.getText();
        if ((brchn.equals(""))){
            
        }else{
            try{
                String sql = "select * from branch where branch=?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, txtBranch.getText());
                rs = pst.executeQuery();              
                if(rs.next()){
                    String brch = rs.getString("branch");
                    lblBranch.setText(brch);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_txtBranchKeyReleased

    private void txtTelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelKeyPressed
        evt.getKeyChar();
        if (evt.getKeyCode() >= 48 && evt.getKeyCode() <= 57 || 
                evt.getKeyCode() >= 96 && evt.getKeyCode() <=105 || 
                evt.getKeyCode() >= 44 && evt.getKeyCode() <= 46 ||
                evt.getKeyCode() >= 16 && evt.getKeyCode() <= 20 || 
                evt.getKeyCode() >= 37 && evt.getKeyCode() <= 40 || 
                evt.getKeyCode() == 8 || evt.getKeyCode() == 16 ||
                evt.getKeyCode() == 13 || evt.getKeyCode() == 144 ||
                evt.getKeyCode() == 10 || evt.getKeyCode() == 27){
            
        }else{
            JOptionPane.showMessageDialog(null,"Invalid character entered!",
                    "Invalid character",JOptionPane.WARNING_MESSAGE);
            evt.setKeyCode(8);
        }
    }//GEN-LAST:event_txtTelKeyPressed

    private void txtLocationKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLocationKeyReleased
        try{
            String sql = "select * from location where locations=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, txtLocation.getText());
            rs = pst.executeQuery();              
            if(rs.next()){
                String loc = rs.getString("locations");
                lblLoc.setText(loc);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtLocationKeyReleased

    private void txtBranchIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBranchIDKeyReleased
        try{
            String sql = "select * from branch where branch_id=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, txtBranchID.getText());
            rs = pst.executeQuery();              
            if(rs.next()){
                String id = rs.getString("branch_id");
                lblID.setText(id);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtBranchIDKeyReleased

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BranchLocation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BranchLocation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BranchLocation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BranchLocation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                BranchLocation frmBL = new BranchLocation();
                frmBL.setLocationRelativeTo(null);
                frmBL.setVisible(true);
                frmBL.setResizable(false);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdAddB;
    private javax.swing.JButton cmdAddL;
    private javax.swing.JButton cmdBranchCancel;
    private javax.swing.JButton cmdBranchSave;
    private javax.swing.JButton cmdDelB;
    private javax.swing.JButton cmdDelL;
    private javax.swing.JButton cmdLocCancel;
    private javax.swing.JButton cmdLocSave;
    private javax.swing.JButton cmdUpdB;
    private javax.swing.JButton cmdUpdL;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JLabel lblBranch;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblLoc;
    private javax.swing.JMenuBar mnu;
    private javax.swing.JMenu mnuHelper;
    private javax.swing.JTable tblBranch;
    private javax.swing.JTable tblLoc;
    private javax.swing.JTextField txtBranch;
    private javax.swing.JTextArea txtBranchAdd;
    private javax.swing.JTextField txtBranchID;
    private javax.swing.JTextField txtBranchSearchF;
    private javax.swing.JTextField txtBranchSearchT;
    private javax.swing.JTextField txtLocSearchF;
    private javax.swing.JTextField txtLocSearchT;
    private javax.swing.JTextField txtLocation;
    private javax.swing.JTextField txtTel;
    // End of variables declaration//GEN-END:variables
}