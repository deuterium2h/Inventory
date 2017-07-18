/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
/**
 *
 * @author PC-1
 */
public class FacultyStaff extends javax.swing.JFrame {
    Connection conn = null;
    PreparedStatement pst =null;
    ResultSet rs = null;
    /**
     * Creates new form FacultyStaff
     */
    private static FacultyStaff obj = null;
    
    private FacultyStaff() {
        initComponents();
        conn = javaconnect.ConnecrDb();
        updateFSTable();
        hideHelp();
    }
    
    public static FacultyStaff getObj(){
        if (obj == null){
            obj = new FacultyStaff();
        }
        return obj;
    }
    
    private void updateFSTable(){
        try{
            //Initializes the SQL statement
            String sql = "select * from facultystaff;";
            pst = conn.prepareStatement(sql); //Prepares the sql statement
            rs = pst.executeQuery(); //Executes the prepared sql statement
            //Fill the contents of the Equipments table
            //to tbpEquipUpd
            tblFS.setModel(DbUtils.resultSetToTableModel(rs));
            
        //Adds a SQLException, that will display "Incorrect SQL Syntax
        //in console if the SQL statement executed is incorrect
        }catch(SQLException e){
            System.err.println("Incorrect SQL Syntax");
        }
    }
    
    private void editFS(){
        try{
            String id = txtEmpID.getText();
            String icode = txtICode.getText();
            String lname = txtLName.getText();
            String fname = txtFName.getText();
            String mi = txtMI.getText();
            String add = txtAdd.getText();
            String num = txtCNum.getText();
            String email = txtEmail.getText();
            String image = txtImgPath.getText();
            
            String sql = "update facultystaff set employee_id='"+id+"', icode='"+icode+"', "
                    + "last_name='"+lname+"', first_name='"+fname+"', m_init='"+mi+"', "
                    + "address='"+add+"', contact_num='"+num+"', e_mail='"+email+"' " 
                    + "where employee_id='"+id+"' ";
            pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Information Updated",
                    "Updated",JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){
            e.printStackTrace();
        }
        updateFSTable();
    }
    
    private void addFS(){
        try{
            String sql = "insert into facultystaff (employee_id,icode,last_name,first_name," 
                    + "m_init,address,contact_num,e_mail,image) "
                    + "values (?,?,?,?,?,?,?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, txtEmpID.getText());
            pst.setString(2, txtICode.getText());
            pst.setString(3, txtLName.getText());
            pst.setString(4, txtFName.getText());
            pst.setString(5, txtMI.getText());
            pst.setString(6, txtAdd.getText());
            pst.setString(7, txtCNum.getText());
            pst.setString(8, txtEmail.getText());
            pst.setBytes(9, imgPerson);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Information Saved!",
                    "Saved to Database",JOptionPane.INFORMATION_MESSAGE);
        }catch(SQLException e){
            System.err.println("Invalid SQL Syntax!");
        }catch(Exception e){
            e.printStackTrace();
        }
        updateFSTable();
    }
    
    private void hideHelp(){
        mnuHelper.setVisible(false);
        mnuID.setVisible(false);
        mnuIC.setVisible(false);
        lblEmpID.setVisible(false);
        mnu.setVisible(false);
    }
    
    private void clearText(){
        txtEmpID.setText("");
	txtICode.setText("");
	txtLName.setText("");
	txtFName.setText("");
        txtMI.setText("");
        txtAdd.setText("");
        txtCNum.setText("");
        txtEmail.setText("");
        txtImgPath.setText("");
    }
    
    private void disableComponents(){
        txtEmpID.setEnabled(false);
	txtICode.setEnabled(false);
	txtLName.setEnabled(false);
	txtFName.setEnabled(false);
        txtMI.setEnabled(false);
        txtAdd.setEnabled(false);
        txtCNum.setEnabled(false);
        txtEmail.setEnabled(false);
        cmdSaveFS.setEnabled(false);
	cmdCancelFS.setEnabled(false);
        cmdBrowseFS.setEnabled(false);
        txtImgPath.setEnabled(false);
        cmdAddFS.setEnabled(true);
        cmdUpdFS.setEnabled(true);
        cmdDelFS.setEnabled(true);
        txtSearchFSF.setEnabled(true);
    }
    
    private void enableComponents(){
        txtEmpID.setEnabled(true);
	txtICode.setEnabled(true);
	txtLName.setEnabled(true);
	txtFName.setEnabled(true);
        txtMI.setEnabled(true);
        txtAdd.setEnabled(true);
        txtCNum.setEnabled(true);
        txtEmail.setEnabled(true);
        cmdSaveFS.setEnabled(true);
	cmdCancelFS.setEnabled(true);
        cmdBrowseFS.setEnabled(true);
        txtImgPath.setEnabled(true);
        cmdAddFS.setEnabled(false);
        cmdUpdFS.setEnabled(false);
        cmdDelFS.setEnabled(false);
        txtSearchFSF.setEnabled(false);
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
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAdd = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        txtMI = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtFName = new javax.swing.JTextField();
        txtICode = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtLName = new javax.swing.JTextField();
        txtEmpID = new javax.swing.JTextField();
        txtCNum = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtImgPath = new javax.swing.JTextField();
        cmdSaveFS = new javax.swing.JButton();
        cmdBrowseFS = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        lblFSName = new javax.swing.JLabel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        imgFSF = new javax.swing.JLabel();
        cmdCancelFS = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtSearchFSF = new javax.swing.JTextField();
        cmdAddFS = new javax.swing.JButton();
        cmdUpdFS = new javax.swing.JButton();
        cmdDelFS = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblFS = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jDesktopPane2 = new javax.swing.JDesktopPane();
        imgFS = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtSearchFST = new javax.swing.JTextField();
        lblEmpID = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        mnu = new javax.swing.JMenuBar();
        mnuHelper = new javax.swing.JMenu();
        mnuID = new javax.swing.JMenu();
        mnuIC = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Faculty/Staff");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Faculty/Staff"));

        jLabel6.setLabelFor(txtAdd);
        jLabel6.setText("Address");

        txtAdd.setDocument(new JTextFieldLimit(100));
        txtAdd.setColumns(20);
        txtAdd.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        txtAdd.setRows(5);
        txtAdd.setEnabled(false);
        txtAdd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAddKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(txtAdd);

        jLabel3.setLabelFor(txtLName);
        jLabel3.setText("Last Name");

        txtMI.setDocument(new JTextFieldLimit(4));
        txtMI.setColumns(3);
        txtMI.setEnabled(false);
        txtMI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMIKeyPressed(evt);
            }
        });

        jLabel5.setLabelFor(txtMI);
        jLabel5.setText("Middle Initial");

        jLabel1.setLabelFor(txtEmpID);
        jLabel1.setText("Employee ID");

        jLabel2.setLabelFor(txtICode);
        jLabel2.setText("Instructor Code");

        jLabel7.setLabelFor(txtCNum);
        jLabel7.setText("Contact Number");

        jLabel4.setLabelFor(txtFName);
        jLabel4.setText("First Name");

        jLabel8.setLabelFor(txtEmail);
        jLabel8.setText("E-Mail Address");

        txtFName.setDocument(new JTextFieldLimit(19));
        txtFName.setEnabled(false);
        txtFName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFNameKeyPressed(evt);
            }
        });

        txtICode.setDocument(new JTextFieldLimit(9));
        txtICode.setEnabled(false);
        txtICode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtICodeKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtICodeKeyReleased(evt);
            }
        });

        txtEmail.setDocument(new JTextFieldLimit(35));
        txtEmail.setEnabled(false);
        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEmailKeyPressed(evt);
            }
        });

        txtLName.setDocument(new JTextFieldLimit(19));
        txtLName.setEnabled(false);
        txtLName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLNameKeyPressed(evt);
            }
        });

        txtEmpID.setDocument(new JTextFieldLimit(15));
        txtEmpID.setEnabled(false);
        txtEmpID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEmpIDKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmpIDKeyReleased(evt);
            }
        });

        txtCNum.setDocument(new JTextFieldLimit(30));
        txtCNum.setEnabled(false);
        txtCNum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCNumKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(txtEmpID)
                    .addComponent(txtICode)
                    .addComponent(txtLName)
                    .addComponent(txtFName)
                    .addComponent(txtMI)
                    .addComponent(txtCNum)
                    .addComponent(txtEmail))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtEmpID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtICode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtLName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtFName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtMI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtCNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED)));

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setLabelFor(txtImgPath);
        jLabel9.setText("Image Path");

        txtImgPath.setEditable(false);
        txtImgPath.setEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtImgPath, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtImgPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        cmdSaveFS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/gfxresources/Save.png"))); // NOI18N
        cmdSaveFS.setMnemonic('s');
        cmdSaveFS.setText("Save");
        cmdSaveFS.setEnabled(false);
        cmdSaveFS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSaveFSActionPerformed(evt);
            }
        });

        cmdBrowseFS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/gfxresources/images.png"))); // NOI18N
        cmdBrowseFS.setMnemonic('b');
        cmdBrowseFS.setText("Browse");
        cmdBrowseFS.setEnabled(false);
        cmdBrowseFS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBrowseFSActionPerformed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));

        lblFSName.setLabelFor(imgFSF);
        lblFSName.setText(" ");

        imgFSF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imgFSF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imgFSF, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                .addContainerGap())
        );
        jDesktopPane1.setLayer(imgFSF, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jDesktopPane1)
                    .addComponent(lblFSName, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFSName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        cmdCancelFS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/gfxresources/Cancel.png"))); // NOI18N
        cmdCancelFS.setMnemonic('c');
        cmdCancelFS.setText("Cancel");
        cmdCancelFS.setEnabled(false);
        cmdCancelFS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCancelFSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(79, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(cmdBrowseFS)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmdSaveFS)
                        .addGap(46, 46, 46)
                        .addComponent(cmdCancelFS)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdSaveFS)
                    .addComponent(cmdBrowseFS)
                    .addComponent(cmdCancelFS))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel10.setDisplayedMnemonic('e');
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/gfxresources/Search.png"))); // NOI18N
        jLabel10.setLabelFor(txtSearchFSF);
        jLabel10.setText("Search");

        txtSearchFSF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchFSFKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchFSFKeyReleased(evt);
            }
        });

        cmdAddFS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/gfxresources/user_add.png"))); // NOI18N
        cmdAddFS.setMnemonic('a');
        cmdAddFS.setText("Add");
        cmdAddFS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAddFSActionPerformed(evt);
            }
        });

        cmdUpdFS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/gfxresources/user_edit.png"))); // NOI18N
        cmdUpdFS.setMnemonic('u');
        cmdUpdFS.setText("Update");
        cmdUpdFS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdUpdFSActionPerformed(evt);
            }
        });

        cmdDelFS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/gfxresources/user_delete.png"))); // NOI18N
        cmdDelFS.setMnemonic('d');
        cmdDelFS.setText("Delete");
        cmdDelFS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDelFSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(txtSearchFSF))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdAddFS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdDelFS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdUpdFS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(cmdAddFS)
                        .addGap(30, 30, 30)
                        .addComponent(cmdUpdFS)
                        .addGap(37, 37, 37)
                        .addComponent(cmdDelFS)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtSearchFSF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Form View", jPanel2);

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblFS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblFS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFSMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblFS);

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        imgFS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jDesktopPane2Layout = new javax.swing.GroupLayout(jDesktopPane2);
        jDesktopPane2.setLayout(jDesktopPane2Layout);
        jDesktopPane2Layout.setHorizontalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imgFS, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                .addContainerGap())
        );
        jDesktopPane2Layout.setVerticalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imgFS, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                .addContainerGap())
        );
        jDesktopPane2.setLayer(imgFS, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/gfxresources/Search.png"))); // NOI18N
        jLabel12.setText("Search Here");

        txtSearchFST.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchFSTKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchFSTKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel12)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 9, Short.MAX_VALUE))
                    .addComponent(txtSearchFST))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearchFST, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 596, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEmpID, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblEmpID, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Table View", jPanel6);

        jButton3.setText("jButton3");

        mnu.add(mnuHelper);
        mnu.add(mnuID);
        mnu.add(mnuIC);

        setJMenuBar(mnu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 878, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdBrowseFSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBrowseFSActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String imagepath = f.getAbsolutePath();
        
        txtImgPath.setText(imagepath);
        
        try{
            File image = new File(imagepath);
            FileInputStream fis = new FileInputStream(image);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            for(int readNum;(readNum = fis.read(buf))!=-1; ){
                bos.write(buf,0,readNum);
            }
        imgPerson = bos.toByteArray();
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_cmdBrowseFSActionPerformed

    private void tblFSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFSMouseClicked
        int row = tblFS.getSelectedRow();
        String tblClick = (tblFS.getModel().getValueAt(row, 0).toString());
        try{
            String sql = "select image from facultystaff where employee_id='"+tblClick+"'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next()){
                byte[]imagedata = rs.getBytes("image");
                format = new ImageIcon(imagedata);
                imgFS.setIcon(format);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        try{
            String sql = "Select * from facultystaff where employee_id='"+tblClick+"' ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
                if(rs.next()){
                    String id = rs.getString("employee_id");
                    lblEmpID.setText(id);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            
        
    }//GEN-LAST:event_tblFSMouseClicked

    private void cmdAddFSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAddFSActionPerformed
        mnuHelper.setText("AddFS");
        enableComponents();
        clearText();
    }//GEN-LAST:event_cmdAddFSActionPerformed

    private void cmdUpdFSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdUpdFSActionPerformed
        String id = txtEmpID.getText();
        if(id.equals("")){
            JOptionPane.showMessageDialog(null, "No Data to be Updated", 
                    "No Data",JOptionPane.WARNING_MESSAGE);
        }else{
            mnuHelper.setText("UpdFS");
            enableComponents();
            txtEmpID.setEnabled(false);
            cmdBrowseFS.setEnabled(false);
        }
    }//GEN-LAST:event_cmdUpdFSActionPerformed

    private void cmdSaveFSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSaveFSActionPerformed
        String id = txtEmpID.getText();
        String ic = txtICode.getText();
        String lname = txtLName.getText();
        String fname = txtFName.getText();
        String add = txtAdd.getText();
        String img = txtImgPath.getText();
        if ((id.equals("")) || ((ic.equals("")) || (lname.equals("")) ||
                (fname.equals("")) || (add.equals("")) || (img.equals("")))){
            JOptionPane.showMessageDialog(null, "Fill-up the Required Fields",
                    "Blank Field/s", JOptionPane.WARNING_MESSAGE);
        }else if(mnuID.getText().equals(txtEmpID.getText())){
            JOptionPane.showMessageDialog(null,"Employee ID already exist!",
                "Duplicate Employee ID",JOptionPane.ERROR_MESSAGE);
            txtEmpID.setText("");
        }else if (mnuIC.getText().equals(txtICode.getText())){
            JOptionPane.showMessageDialog(null,"Instrucor Code already used!",
                "Duplicate Instructor Code",JOptionPane.ERROR_MESSAGE);
            txtICode.setText("");
        }else{    
            String a = "AddFS";
            String u = "UpdFS";
            if (a.equals(mnuHelper.getText())){
                addFS();
                System.out.println("Add");
            }else if(u.equals(mnuHelper.getText())){
                editFS();
                System.out.println("Updated");
            }else{
                System.err.println("error!");
            }
            clearText();
            disableComponents();
        }
    }//GEN-LAST:event_cmdSaveFSActionPerformed

    private void cmdDelFSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDelFSActionPerformed
        String type = new Main().lblType.getText();
        if(type.equals("Assistant")){
            JOptionPane.showMessageDialog(null, "Insufficient Account Privillege",
                    "Invalid Account Type",JOptionPane.ERROR_MESSAGE);
        }else{
            String id = txtEmpID.getText();
            if(id.equals("")){
                JOptionPane.showMessageDialog(null, "No Information selected",
                        "Cannot delete Empty record",JOptionPane.ERROR_MESSAGE);
            }else{
                int q = JOptionPane.showConfirmDialog(null,
                        "Do you really want to delete the selected information?","Delete Record",
                        JOptionPane.YES_NO_OPTION);
                if (q==0){
                    String sql = "delete from facultystaff where employee_id =?";
                    try{
                        pst = conn.prepareStatement(sql);
                        pst.setString(1, txtEmpID.getText());
                        pst.execute();
                        JOptionPane.showMessageDialog(null, "Information Deleted!",
                                "Deleted to Database",JOptionPane.INFORMATION_MESSAGE);
                    }catch(SQLException e){
                        System.err.println("Invalid SQL Syntax!");
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    updateFSTable();
                }
            }
        }
    }//GEN-LAST:event_cmdDelFSActionPerformed

    private void cmdCancelFSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCancelFSActionPerformed
        // TODO add your handling code here:
        disableComponents();
        clearText();
    }//GEN-LAST:event_cmdCancelFSActionPerformed

    private void txtAddKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAddKeyPressed
        evt.getKeyChar();
        if (evt.getKeyCode() >= 65 && evt.getKeyCode() <= 90 || 
                evt.getKeyCode() == 32 || evt.getKeyCode() == 16 || 
		evt.getKeyCode() >= 96 && evt.getKeyCode() <=105 || 
                evt.getKeyCode() >= 44 && evt.getKeyCode() <= 46 ||
                evt.getKeyCode() >= 108 && evt.getKeyCode() <= 110 ||
                evt.getKeyCode() >= 48 && evt.getKeyCode() <= 57 ||
                evt.getKeyCode() == 13 || evt.getKeyCode() >= 16 && 
                evt.getKeyCode() <= 20 || evt.getKeyCode() >= 37 &&
                evt.getKeyCode() <= 40 || evt.getKeyCode() == 144 ||
                evt.getKeyCode() >= 8 && evt.getKeyCode() <= 10 || 
                evt.getKeyCode() == 27){
        }else{
            JOptionPane.showMessageDialog(null,"Invalid character entered!",
                    "Invalid character",JOptionPane.WARNING_MESSAGE);
            evt.setKeyCode(8);
        }
    }//GEN-LAST:event_txtAddKeyPressed

    private void txtSearchFSFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchFSFKeyPressed
        evt.getKeyChar();
        if (evt.getKeyCode() >= 65 && evt.getKeyCode() <= 90 || evt.getKeyCode() == 32 ||
                evt.getKeyCode() == 16 || evt.getKeyCode() == 8 || 
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
    }//GEN-LAST:event_txtSearchFSFKeyPressed

    private void txtSearchFSTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchFSTKeyPressed
        evt.getKeyChar();
        if (evt.getKeyCode() >= 65 && evt.getKeyCode() <= 90 || evt.getKeyCode() == 32 ||
                evt.getKeyCode() == 16 || evt.getKeyCode() == 8 || 
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
    }//GEN-LAST:event_txtSearchFSTKeyPressed

    private void txtCNumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCNumKeyPressed
        evt.getKeyChar();
        if (evt.getKeyCode() >= 48 && evt.getKeyCode() <= 57 || 
                evt.getKeyCode() >= 96 && evt.getKeyCode() <=105 || 
                evt.getKeyCode() >= 44 && evt.getKeyCode() <= 47 ||
                evt.getKeyCode() >= 16 && evt.getKeyCode() <= 20 || 
                evt.getKeyCode() >= 37 && evt.getKeyCode() <= 40 || 
                evt.getKeyCode() == 8 || evt.getKeyCode() == 16 ||
                evt.getKeyCode() == 13 || evt.getKeyCode() == 144 ||
                evt.getKeyCode() == 10 || evt.getKeyCode() == 27 || 
                evt.getKeyCode() == 111){
            
        }else{
            JOptionPane.showMessageDialog(null,"Invalid character entered!",
                    "Invalid character",JOptionPane.WARNING_MESSAGE);
            evt.setKeyCode(8);
        }
    }//GEN-LAST:event_txtCNumKeyPressed

    private void txtEmailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyPressed
        evt.getKeyChar();
        if (evt.getKeyCode() >= 65 && evt.getKeyCode() <= 90 || 
                evt.getKeyCode() == 32 ||evt.getKeyCode() == 8 || 
		evt.getKeyCode() >= 96 && evt.getKeyCode() <=10 || 
                evt.getKeyCode() == 45 || evt.getKeyCode() == 109 || 
                evt.getKeyCode() >= 48 && evt.getKeyCode() <= 57 || 
                evt.getKeyCode() == 13 ||  evt.getKeyCode() == 64 || 
                evt.getKeyCode() >= 16 && evt.getKeyCode() <= 20 || 
                evt.getKeyCode() >= 37 && evt.getKeyCode() <= 40 || 
                evt.getKeyCode() == 144 || evt.getKeyCode() == 10 ||
                evt.getKeyCode() == 46 || evt.getKeyCode() == 110 || 
                evt.getKeyCode() == 27){
            
        }else{
            JOptionPane.showMessageDialog(null,"Invalid character entered!",
                    "Invalid character",JOptionPane.WARNING_MESSAGE);
            evt.setKeyCode(8);
        }
    }//GEN-LAST:event_txtEmailKeyPressed

    private void txtMIKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMIKeyPressed
        evt.getKeyChar();
        if (evt.getKeyCode() >= 65 && evt.getKeyCode() <= 90 ||
                evt.getKeyCode() == 46 ||evt.getKeyCode() == 110 ||
                evt.getKeyCode() >= 16 && evt.getKeyCode() <= 20 || 
                evt.getKeyCode() >= 37 && evt.getKeyCode() <= 40 || 
                evt.getKeyCode() == 144 || evt.getKeyCode() == 10 || 
                evt.getKeyCode() == 8 || evt.getKeyCode() == 97 || 
                evt.getKeyCode() >= 100 && evt.getKeyCode() <= 102 || 
                evt.getKeyCode() == 27){
            
        }else{
            JOptionPane.showMessageDialog(null,"Invalid character entered!",
                    "Invalid character",JOptionPane.WARNING_MESSAGE);
            evt.setKeyCode(8);
        }
    }//GEN-LAST:event_txtMIKeyPressed

    private void txtFNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFNameKeyPressed
        evt.getKeyChar();
        if (evt.getKeyCode() >= 65 && evt.getKeyCode() <= 90 ||
                evt.getKeyCode() == 46 || evt.getKeyCode() == 110 ||
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
    }//GEN-LAST:event_txtFNameKeyPressed

    private void txtLNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLNameKeyPressed
        evt.getKeyChar();
        if (evt.getKeyCode() >= 65 && evt.getKeyCode() <= 90 ||
                evt.getKeyCode() == 45 || evt.getKeyCode() == 109 ||
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
    }//GEN-LAST:event_txtLNameKeyPressed

    private void txtICodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtICodeKeyPressed
        evt.getKeyChar();
        if (evt.getKeyCode() >= 65 && evt.getKeyCode() <= 90 ||
                evt.getKeyCode() >= 16 && evt.getKeyCode() <= 20 || 
                evt.getKeyCode() >= 37 && evt.getKeyCode() <= 40 || 
                evt.getKeyCode() == 144 || evt.getKeyCode() == 10 ||
                evt.getKeyCode() == 8 || evt.getKeyCode() == 97 || 
                evt.getKeyCode() >= 100 && evt.getKeyCode() <= 102 || 
                evt.getKeyCode() == 27){
            
        }else{
            JOptionPane.showMessageDialog(null,"Invalid character entered!",
                    "Invalid character",JOptionPane.WARNING_MESSAGE);
            evt.setKeyCode(8);
        }
    }//GEN-LAST:event_txtICodeKeyPressed

    private void txtEmpIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmpIDKeyPressed
        evt.getKeyChar();
        if (evt.getKeyCode() >= 65 && evt.getKeyCode() <= 90 || 
                evt.getKeyCode() >= 48 && evt.getKeyCode() <= 57 || 
                evt.getKeyCode() >= 96 && evt.getKeyCode() <= 105 || 
                evt.getKeyCode() >= 16 && evt.getKeyCode() <= 20 || 
                evt.getKeyCode() >= 37 &&  evt.getKeyCode() <= 40 ||
                evt.getKeyCode() == 144 || evt.getKeyCode() == 10 || 
                evt.getKeyCode() == 8 || evt.getKeyCode() == 27){
            
        }else{
            JOptionPane.showMessageDialog(null,"Invalid character entered!",
                    "Invalid character",JOptionPane.WARNING_MESSAGE);
            evt.setKeyCode(8);
        }
    }//GEN-LAST:event_txtEmpIDKeyPressed

    private void txtSearchFSTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchFSTKeyReleased
        String a = "";
        if (a.equals(txtSearchFST.getText())){
            updateFSTable();
        }else{
            try{
                String sql = "select * from facultystaff where employee_id =? or icode=? "
                        + "or last_name=? or first_name=? or m_init=? or address=? "
                        + "or contact_num=? or e_mail=?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, txtSearchFST.getText());
                pst.setString(2, txtSearchFST.getText());
                pst.setString(3, txtSearchFST.getText());
                pst.setString(4, txtSearchFST.getText());
                pst.setString(5, txtSearchFST.getText());
                pst.setString(6, txtSearchFST.getText());
                pst.setString(7, txtSearchFST.getText());
                pst.setString(8, txtSearchFST.getText());
                rs = pst.executeQuery();
                
                tblFS.setModel(DbUtils.resultSetToTableModel(rs));
                
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_txtSearchFSTKeyReleased

    private void txtSearchFSFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchFSFKeyReleased
        
        try{
            String sql = "select * from facultystaff where employee_id =? or icode=? "
                    + "or last_name=? or first_name=? or m_init=? or address=? "
                    + "or contact_num=? or e_mail=? ";
            pst = conn.prepareStatement(sql);
            pst.setString(1, txtSearchFSF.getText());
            pst.setString(2, txtSearchFSF.getText());
            pst.setString(3, txtSearchFSF.getText());
            pst.setString(4, txtSearchFSF.getText());
            pst.setString(5, txtSearchFSF.getText());
            pst.setString(6, txtSearchFSF.getText());
            pst.setString(7, txtSearchFSF.getText());
            pst.setString(8, txtSearchFSF.getText());
            rs = pst.executeQuery();
            if(rs.next()){
                String empid = rs.getString("employee_id");
                String ic= rs.getString("icode");
                String ln = rs.getString("last_name");
                String fn = rs.getString("first_name");
                String mi = rs.getString("m_init");
                String add = rs.getString("address");
                String num = rs.getString("contact_num");
                String mail = rs.getString("e_mail");
                byte[]imagedata = rs.getBytes("image");
                format = new ImageIcon(imagedata);
                
                txtEmpID.setText(empid);
                txtICode.setText(ic);
                txtLName.setText(ln);
                txtFName.setText(fn);
                txtMI.setText(mi);
                txtAdd.setText(add);
                txtCNum.setText(num);
                txtEmail.setText(mail);
                
                imgFSF.setIcon(format);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtSearchFSFKeyReleased

    private void txtEmpIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmpIDKeyReleased
        try{
            String sql = "select * from facultystaff where employee_id=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, txtEmpID.getText());
            rs = pst.executeQuery();              
            if(rs.next()){
                String id = rs.getString("employee_id");
                mnuID.setText(id);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtEmpIDKeyReleased

    private void txtICodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtICodeKeyReleased
        try{
            String sql = "select * from facultystaff where icode=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, txtICode.getText());
            rs = pst.executeQuery();              
            if(rs.next()){
                String ic = rs.getString("icode");
                mnuIC.setText(ic);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtICodeKeyReleased

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
            java.util.logging.Logger.getLogger(FacultyStaff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FacultyStaff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FacultyStaff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FacultyStaff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FacultyStaff frmFS = new FacultyStaff();
                frmFS.setLocationRelativeTo(null);
                frmFS.setVisible(true);
                frmFS.setResizable(false);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdAddFS;
    private javax.swing.JButton cmdBrowseFS;
    private javax.swing.JButton cmdCancelFS;
    private javax.swing.JButton cmdDelFS;
    private javax.swing.JButton cmdSaveFS;
    private javax.swing.JButton cmdUpdFS;
    private javax.swing.JLabel imgFS;
    private javax.swing.JLabel imgFSF;
    private javax.swing.JButton jButton3;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblEmpID;
    private javax.swing.JLabel lblFSName;
    private javax.swing.JMenuBar mnu;
    private javax.swing.JMenu mnuHelper;
    private javax.swing.JMenu mnuIC;
    private javax.swing.JMenu mnuID;
    private javax.swing.JTable tblFS;
    private javax.swing.JTextArea txtAdd;
    private javax.swing.JTextField txtCNum;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEmpID;
    private javax.swing.JTextField txtFName;
    private javax.swing.JTextField txtICode;
    private javax.swing.JTextField txtImgPath;
    private javax.swing.JTextField txtLName;
    private javax.swing.JTextField txtMI;
    private javax.swing.JTextField txtSearchFSF;
    private javax.swing.JTextField txtSearchFST;
    // End of variables declaration//GEN-END:variables
private ImageIcon format = null;
String imagepath = null;
int s=0;
byte[] imgPerson = null;
}