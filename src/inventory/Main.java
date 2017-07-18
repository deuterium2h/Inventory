/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory;

/**
 *
 * @author Dimacuha, Dan
 */
import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import java.sql.*;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.*;
import javax.swing.*;
import net.proteanit.sql.*;
import javax.swing.text.*;

class JTextFieldLimit extends PlainDocument {
  private int limit;
  JTextFieldLimit(int limit) {
    super();
    this.limit = limit;
  }

  JTextFieldLimit(int limit, boolean upper) {
    super();
    this.limit = limit;
  }

  public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
    if (str == null)
      return;

    if ((getLength() + str.length()) <= limit) {
      super.insertString(offset, str, attr);
    }
  }
}

public class Main extends javax.swing.JFrame {
Connection conn = null;
PreparedStatement pst = null;
ResultSet rs = null;
    /**
     * Creates new form Main
     * Connects the database
     * Update all tables
     */

    public Main() {
        initComponents();
        currentDate();
        setIcon();
        conn = javaconnect.ConnecrDb();
        updateTableEquip();
        updateTableComputer();
        viewTableComputer();
        viewTableEquip();
        fillCboLocation();
        fillCboBranch();
        fillLBB();
        hideHelp();
        
    }
    private void hideHelp(){
        mnuCID.setVisible(false);
        mnuEID.setVisible(false);
        lblHelper.setVisible(false);
    }
    
    public void currentDate(){
        Calendar cal = new GregorianCalendar();
        int mon = cal.get(Calendar.MONTH);
        int dy  = cal.get(Calendar.DAY_OF_MONTH);
        int yr = cal.get(Calendar.YEAR);
        lblDate.setText(yr+"/"+(mon+1)+"/"+dy);
        int hrs = cal.get(Calendar.HOUR);
        int min = cal.get(Calendar.MINUTE);
        int sec = cal.get(Calendar.SECOND);
        int ap = cal.get(Calendar.AM_PM);
        if(ap == Calendar.AM){
            lblTime.setText(hrs+":"+min+":"+sec+" AM");
        }else if(ap == Calendar.PM){
            lblTime.setText(hrs+":"+min+":"+sec+" PM");
        }
        
        
        Thread clock = new Thread(){
            public void run(){
                for(;;){
                    
                    Calendar cal = new GregorianCalendar();
                    int mon = cal.get(Calendar.MONTH);
                    int dy  = cal.get(Calendar.DAY_OF_MONTH);
                    int yr = cal.get(Calendar.YEAR);
                    lblDate.setText(yr+"/"+(mon+1)+"/"+dy);
                    int hrs = cal.get(Calendar.HOUR);
                    int min = cal.get(Calendar.MINUTE);
                    int sec = cal.get(Calendar.SECOND);
                    int ap = cal.get(Calendar.AM_PM);
                    if(ap == Calendar.AM){
                        lblTime.setText(hrs+":"+min+":"+sec+" AM");
                    }else if(ap == Calendar.PM){
                        lblTime.setText(hrs+":"+min+":"+sec+" PM");
                    }
                    try {
                        sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            }
        };
        clock.start();
    }
    /*
    private void tblRes(){
        int width = 0;
        for (row = 0; row < tblCompUpd.getRowCount(); row++) {
            TableCellRenderer renderer = tblCompUpd.getCellRenderer(row, myColumn);
            Component comp = tblCompUpd.prepareRenderer(renderer, row, myColumn);
            width = Math.max (comp.getPreferredSize().width, width);
        }
    }*/
    /*
    public static void packColumn(JTable tblCompUpd, int vColIndex, int margin) {
    DefaultTableColumnModel colModel = (DefaultTableColumnModel)tblCompUpd.getColumnModel();
    TableColumn col = colModel.getColumn(vColIndex);
    int width = 0;

    // Get width of column header
    TableCellRenderer renderer = col.getHeaderRenderer();
    if (renderer == null) {
        renderer = tblCompUpd.getTableHeader().getDefaultRenderer();
    }
    java.awt.Component comp = renderer.getTableCellRendererComponent(
        tblCompUpd, col.getHeaderValue(), false, false, 0, 0);
    width = comp.getPreferredSize().width;

    // Get maximum width of column data
    for (int r=0; r<tblCompUpd.getRowCount(); r++) {
        renderer = tblCompUpd.getCellRenderer(r, vColIndex);
        comp = renderer.getTableCellRendererComponent(
            tblCompUpd, tblCompUpd.getValueAt(r, vColIndex), false, false, r, vColIndex);
        width = Math.max(width, comp.getPreferredSize().width);
    }

    // Add margin
    width += 2*margin;

    // Set the width
    col.setPreferredWidth(width);
}*/
    
    public void setIcon(){
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon1.png")));
    }
    
    private void disableComponentsC(){
        txtPCID.setEnabled(false);
	txtIPAdd.setEnabled(false);
	txtPCName.setEnabled(false);
	cboPCStat.setEnabled(false);
	cboPCLocation.setEnabled(false);
	cboPCBranch.setEnabled(false);
	cmdPCSave.setEnabled(false);
	cmdPCCancel.setEnabled(false);        
        cmdAddC.setEnabled(true);
        cmdUpdateC.setEnabled(true);
        cmdDeleteC.setEnabled(true);
        tblCompUpd.setEnabled(true);
        txtSearchC.setEnabled(true);
        
    }
	
    private void enableComponentsC(){
        txtPCID.setEnabled(true);
	txtIPAdd.setEnabled(true);
	txtPCName.setEnabled(true);
	cboPCStat.setEnabled(true);
	cboPCLocation.setEnabled(true);
	cboPCBranch.setEnabled(true);
	cmdPCSave.setEnabled(true);
	cmdPCCancel.setEnabled(true);        
        cmdAddC.setEnabled(false);
        cmdUpdateC.setEnabled(false);
        cmdDeleteC.setEnabled(false);
        tblCompUpd.setEnabled(false);
        txtSearchC.setEnabled(false);
    }

    private void disableComponentsE(){
        txtEquipID.setEnabled(false);
	txtEquipName.setEnabled(false);
	cboIsWorking.setEnabled(false);
	cboEquipLocation.setEnabled(false);
	cboEquipBranch.setEnabled(false);
	cboLastBorrowedBy.setEnabled(false);
	cmdEquipSave.setEnabled(false);
	cmdEquipCancel.setEnabled(false);
        dtpArrive.setEnabled(false);
        dtpLB.setEnabled(false);
        cmdAddE.setEnabled(true);
        cmdUpdateE.setEnabled(true);
        cmdDeleteE.setEnabled(true);
        tblEquipUpd.setEnabled(true);
        txtSearchE.setEnabled(true);
    }
	
    private void enableComponentsE(){
        txtEquipID.setEnabled(true);
	txtEquipName.setEnabled(true);
	cboIsWorking.setEnabled(true);
	cboEquipLocation.setEnabled(true);
	cboEquipBranch.setEnabled(true);
	cboLastBorrowedBy.setEnabled(true);
	cmdEquipSave.setEnabled(true);
	cmdEquipCancel.setEnabled(true);
        dtpArrive.setEnabled(true);
        dtpLB.setEnabled(true);
        cmdAddE.setEnabled(false);
        cmdUpdateE.setEnabled(false);
        cmdDeleteE.setEnabled(false);
        tblEquipUpd.setEnabled(false);
        txtSearchE.setEnabled(false);
    }
    
    private void clearTextC(){
        txtPCID.setText("");
	txtIPAdd.setText("");
	txtPCName.setText("");
	cboPCStat.setSelectedItem(" ");
	cboPCLocation.setSelectedItem(" ");
	cboPCBranch.setSelectedItem(" ");
    }
    
    private void clearTextE(){
        txtEquipID.setText("");
	txtEquipName.setText("");
	cboIsWorking.setSelectedItem(" ");
	cboEquipLocation.setSelectedItem(" ");
	cboEquipBranch.setSelectedItem(" ");
	cboLastBorrowedBy.setSelectedItem(" ");
        ((JTextField)dtpArrive.getDateEditor().getUiComponent()).setText("");
        ((JTextField)dtpLB.getDateEditor().getUiComponent()).setText("");
    }
    //Update Computer table view
    private void viewTableComputer(){
        try{
            String a = "All";
            if (a.equals(cboCompBranchV.getSelectedItem())){
            //Initializes the SQL statement
                String sql = "select "
                        + "count(distinct PC_NAME) 'Total # of PC', "
                        + "sum(case when STATUS = 'Working' then 1 else 0 end) 'Total # of Working PC', "
                        + "sum(case when STATUS = 'Not Working' then 1 else 0 end) 'Total # of Not Working PC'"
                        + " from computers ";
                pst = conn.prepareStatement(sql); //Prepares the sql statement
                rs = pst.executeQuery(); //Executes the prepared sql statement
                //Fill the contents of the Equipments table
                //to tbpCompV
                tblCompV.setModel(DbUtils.resultSetToTableModel(rs));
                //tblCompV.getColumnModel().getColumn(0).
                
            }else{
                String sql = "select "
                        + "count(distinct PC_NAME) 'Total # of PC', "
                        + "sum(case when STATUS = 'Working' then 1 else 0 end) 'Total # of Working PC', "
                        + "sum(case when STATUS = 'Not Working' then 1 else 0 end) 'Total # of Not Working PC'"
                        + " from computers where branch=?";
                pst = conn.prepareStatement(sql); //Prepares the sql statement
                pst.setString(1, (String) cboCompBranchV.getSelectedItem());
                rs = pst.executeQuery(); //Executes the prepared sql statement
                //Fill the contents of the Equipments table
                //to tbpCompV
                tblCompV.setModel(DbUtils.resultSetToTableModel(rs));
            }
        //Adds a SQLException, that will display "Incorrect SQL Syntax
        //in console if the SQL statement executed is incorrect
        }catch(SQLException e){
            System.err.println("Incorrect SQL Syntax");
        }
    }
       
    private void viewTableComputerInd(){
        try{
            String a = "All";
            if (a.equals(cboCompBranchV.getSelectedItem())){
                //Initializes the SQL statement
                String sql = "select * from computers";
                pst = conn.prepareStatement(sql); //Prepares the sql statement
                rs = pst.executeQuery(); //Executes the prepared sql statement
                //Fill the contents of the Equipments table
                //to tbpCompV
                tblCompV.setModel(DbUtils.resultSetToTableModel(rs));
            }else{
                String sql = "select * from computers where branch =?";
                pst = conn.prepareStatement(sql); //Prepares the sql statement
                pst.setString(1, (String) cboCompBranchV.getSelectedItem());
                rs = pst.executeQuery(); //Executes the prepared sql statement
                //Fill the contents of the Equipments table
                //to tbpCompV
                tblCompV.setModel(DbUtils.resultSetToTableModel(rs));
            }
        //Adds a SQLException, that will display "Incorrect SQL Syntax
        //in console if the SQL statement executed is incorrect
        }catch(SQLException e){
            System.err.println("Incorrect SQL Syntax");
        }
    }
    //Update Equipment table view
    private void viewTableEquip(){
        try{
            String a = "All";
            if(a.equals(cboEquipBranchV.getSelectedItem())){
            //Initializes the SQL statement
            String sql = "select "
                    + "count(distinct ITEM_NAME) 'Total # of Equipments', "
                    + "sum(case when IS_AVAILABLE = 'Yes' then 1 else 0 end) 'Total # of Available Equipments', "
                    + "sum(case when IS_AVAILABLE = 'No' then 1 else 0 end) 'Total # of Not Available Equipments'"
                    + " from equipments;";
            pst = conn.prepareStatement(sql); //Prepares the sql statement
            rs = pst.executeQuery(); //Executes the prepared sql statement
            //Fill the contents of the Equipments table
            //to tbpEquipV
            tblEquipV.setModel(DbUtils.resultSetToTableModel(rs));
            }else{
                String sql = "select "
                    + "count(distinct ITEM_NAME) 'Total # of Equipments', "
                    + "sum(case when IS_AVAILABLE = 'Yes' then 1 else 0 end) 'Total # of Available Equipments', "
                    + "sum(case when IS_AVAILABLE = 'No' then 1 else 0 end) 'Total # of Not Available Equipments'"
                    + " from equipments where branch=? ;";
                pst = conn.prepareStatement(sql); //Prepares the sql statement
                pst.setString(1,(String) cboEquipBranchV.getSelectedItem());
                rs = pst.executeQuery(); //Executes the prepared sql statement
                //Fill the contents of the Equipments table
                //to tbpCompUpd
                tblEquipV.setModel(DbUtils.resultSetToTableModel(rs));
            }
        //Adds a SQLException, that will display "Incorrect SQL Syntax
        //in console if the SQL statement executed is incorrect
        }catch(SQLException e){
            System.err.println("Incorrect SQL Syntax");
        }
    }

    private void viewTableEquipGrp(){
        try{
            String a = "All";
            if(a.equals(cboEquipBranchV.getSelectedItem())){
                //Initializes the SQL statement
                String sql = "select "
                        + "distinct ITEM_NAME 'Item Name', "
                        + "sum(case when IS_AVAILABLE = 'Yes' then 1 else 0 end) '# of Available', "
                        + "sum(case when IS_AVAILABLE = 'No' then 1 else 0 end) '# of Not Available'"
                        + " from equipments group by item_name;";
                pst = conn.prepareStatement(sql); //Prepares the sql statement
                rs = pst.executeQuery(); //Executes the prepared sql statement
                //Fill the contents of the Equipments table
                //to tbpEquipV
                tblEquipV.setModel(DbUtils.resultSetToTableModel(rs));
            }else{
                String sql = "select "
                        + "distinct ITEM_NAME 'Item Name', "
                        + "sum(case when IS_AVAILABLE = 'Yes' then 1 else 0 end) '# of Available', "
                        + "sum(case when IS_AVAILABLE = 'No' then 1 else 0 end) '# of Not Available'"
                        + " from equipments where branch=? group by item_name;";
                pst = conn.prepareStatement(sql); //Prepares the sql statement
                pst.setString(1,(String) cboEquipBranchV.getSelectedItem());
                rs = pst.executeQuery(); //Executes the prepared sql statement
                //Fill the contents of the Equipments table
                //to tbpCompUpd
                tblEquipV.setModel(DbUtils.resultSetToTableModel(rs));
            }
        //Adds a SQLException, that will display "Incorrect SQL Syntax
        //in console if the SQL statement executed is incorrect
        }catch(SQLException e){
            System.err.println("Incorrect SQL Syntax");
        }
    }
     
    private void viewTableEquipInd(){
        try{
            String a = "All";
            if(a.equals(cboEquipBranchV.getSelectedItem())){
                //Initializes the SQL statement
                String sql = "select * from equipments;";
                pst = conn.prepareStatement(sql); //Prepares the sql statement
                rs = pst.executeQuery(); //Executes the prepared sql statement
                //Fill the contents of the Equipments table
                //to tbpCompUpd
                tblEquipV.setModel(DbUtils.resultSetToTableModel(rs));
            }else{
                String sql = "select * from equipments where branch=? ;";
                pst = conn.prepareStatement(sql); //Prepares the sql statement
                pst.setString(1,(String) cboEquipBranchV.getSelectedItem());
                rs = pst.executeQuery(); //Executes the prepared sql statement
                //Fill the contents of the Equipments table
                //to tbpCompUpd
                tblEquipV.setModel(DbUtils.resultSetToTableModel(rs));
            }
        //Adds a SQLException, that will display "Incorrect SQL Syntax
        //in console if the SQL statement executed is incorrect
        }catch(SQLException e){
            System.err.println("Incorrect SQL Syntax");
        }
    }
    
    //Update Computer table
    private void updateTableComputer(){
        try{
            //Initializes the SQL statement
            String sql = "select * from computers;";
            pst = conn.prepareStatement(sql); //Prepares the sql statement
            rs = pst.executeQuery(); //Executes the prepared sql statement
            //Fill the contents of the Equipments table
            //to tbpCompUpd
            tblCompUpd.setModel(DbUtils.resultSetToTableModel(rs));
            
        //Adds a SQLException, that will display "Incorrect SQL Syntax
        //in console if the SQL statement executed is incorrect
        }catch(SQLException e){
            System.err.println("Incorrect SQL Syntax");
        }
    }

    //Update Equipment table
    private void updateTableEquip(){
        try{
            //Initializes the SQL statement
            String sql = "select * from equipments;";
            pst = conn.prepareStatement(sql); //Prepares the sql statement
            rs = pst.executeQuery(); //Executes the prepared sql statement
            //Fill the contents of the Equipments table
            //to tbpEquipUpd
            tblEquipUpd.setModel(DbUtils.resultSetToTableModel(rs));
            
        //Adds a SQLException, that will display "Incorrect SQL Syntax
        //in console if the SQL statement executed is incorrect
        }catch(SQLException e){
            System.err.println("Incorrect SQL Syntax");
        }
    }

    //Link contents of Locations table to all Location combo box
    private void fillCboLocation(){
        cboEquipLocation.addItem(" ");
        cboPCLocation.addItem(" ");
        try{
            //Initializes the sql statement
            String sql = "select * from location";
            pst = conn.prepareStatement(sql); //Prepares the sql statement
            rs = pst.executeQuery(); //Executes the prepared sql statement
            while(rs.next()){
                String loc = rs.getString("locations");
                cboPCLocation.addItem(loc);
                cboEquipLocation.addItem(loc);
                
            }
        //Adds a SQLException, that will display "Incorrect SQL Syntax
        //in console if the SQL statement executed is incorrect
        }catch(SQLException e){
            System.err.println("Incorrect SQL Syntax");
        }
    }
    
    private void fillLBB(){
        cboLastBorrowedBy.addItem(" ");
        try{
            String sql = "select * from facultystaff";
            pst = conn.prepareStatement(sql); //Prepares the sql statement
            rs = pst.executeQuery(); //Executes the prepared sql statement
            while(rs.next()){
                String lbb = rs.getString("icode");
                cboLastBorrowedBy.addItem(lbb);
            }
        }catch(SQLException e){
            System.err.println("Incorrect SQL Syntax");
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    //Link contents of Branch Table to all Branch combo box
    private void fillCboBranch(){
        cboPCBranch.addItem(" ");
        cboEquipBranch.addItem(" ");
        cboCompBranchV.addItem("All");
        cboEquipBranchV.addItem("All");
        try{
            //Initializes the sql statement
            String sql = "select * from branch";
            pst = conn.prepareStatement(sql); //Prepares the sql statement
            rs = pst.executeQuery(); //Executes the prepared sql statement
            while(rs.next()){
                String brch = rs.getString("branch");
                cboPCBranch.addItem(brch);
                cboCompBranchV.addItem(brch);
                cboEquipBranch.addItem(brch);
                cboEquipBranchV.addItem(brch);
            }
        //Adds a SQLException, that will display "Incorrect SQL Syntax
        //in console if the SQL statement executed is incorrect
        }catch(SQLException e){
            System.err.println("Incorrect SQL Syntax");
        }
    }
    
    //Link contents of IS_WORKING column to IsWorking combo box
    private void fillCboLastBorrow(){
        try{
            //Initializes the sql statement
            String sql = "select ICode from facultystaff";
            pst = conn.prepareStatement(sql); //Prepares the sql statement
            rs = pst.executeQuery(); //Executes the prepared sql statement
            cboLastBorrowedBy.setModel(null);
        //Adds a SQLException, that will display "Incorrect SQL Syntax
        //in console if the SQL statement executed is incorrect
        }catch(SQLException e){
            System.err.println("Incorrect SQL Syntax");
        }
    }
    
    private void editComputer(){
        try{
            String id = txtPCID.getText();
            String ip = txtIPAdd.getText();
            String name = txtPCName.getText();
            String status = (String) cboPCStat.getSelectedItem();
            String loc = (String) cboPCLocation.getSelectedItem();
            String brch = (String) cboPCBranch.getSelectedItem();
            String sql = "update computers set pc_id='"+id+"', ip_address='"+ip+"', pc_name='"+name+"', "
                    + "status='"+status+"', location='"+loc+"', branch='"+brch+"' "
                    + "where pc_id='"+id+"' ";
            pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Information Updated",
                    "Updated",JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){
            e.printStackTrace();
        }
        updateTableComputer();
    }
    
    private void addComputer(){
        try{
            String sql = "insert into computers (pc_id,ip_address,pc_name,status,location,branch) "
                    + "values (?,?,?,?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, txtPCID.getText());
            pst.setString(2, txtIPAdd.getText());
            pst.setString(3, txtPCName.getText());
            pst.setString(4, (String) cboPCStat.getSelectedItem());
            pst.setString(5, (String) cboPCLocation.getSelectedItem());
            pst.setString(6, (String) cboPCBranch.getSelectedItem());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Information Saved!",
                    "Saved to Database",JOptionPane.INFORMATION_MESSAGE);
        }catch(SQLException e){
            System.err.println("Invalid SQL Syntax!");
        }catch(Exception e){
            e.printStackTrace();
        }
        updateTableComputer();
    }
    
    private void editEquipment(){
        try{
            String id = txtEquipID.getText();
            String name = txtEquipName.getText();
            String avail = (String) cboIsWorking.getSelectedItem();
            String lbb = (String) cboLastBorrowedBy.getSelectedItem();            
            String loc = (String) cboEquipLocation.getSelectedItem();
            String brch = (String) cboEquipBranch.getSelectedItem();
            String dlb = ((JTextField)dtpLB.getDateEditor().getUiComponent()).getText();
            String sql = "update equipments set item_id='"+id+"', item_name='"+name+"', "
                    + "is_available='"+avail+"', location='"+loc+"', branch='"+brch+"', "
                    + "last_borrowed_by='"+lbb+"',date_lb='"+dlb+"' where item_id='"+id+"' ";
            pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Information Updated",
                    "Updated",JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){
            e.printStackTrace();
        }
        updateTableEquip();
    }
    
    private void addEquipment(){
        try{
            String sql = "insert into equipments(item_id,item_name,is_available,"
                    + "location,branch,last_borrowed_by,date_arrived,date_lb) "
                    + "values (?,?,?,?,?,?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, txtEquipID.getText());
            pst.setString(2, txtEquipName.getText());
            pst.setString(3, (String) cboIsWorking.getSelectedItem());
            pst.setString(4, (String) cboEquipLocation.getSelectedItem());
            pst.setString(5, (String) cboEquipBranch.getSelectedItem());
            pst.setString(6, (String) cboLastBorrowedBy.getSelectedItem());  
            pst.setString(7, ((JTextField)dtpArrive.getDateEditor().getUiComponent()).getText());
            pst.setString(8, ((JTextField)dtpLB.getDateEditor().getUiComponent()).getText());
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "Information Saved!",
                    "Saved to Database",JOptionPane.INFORMATION_MESSAGE);
        }catch(SQLException e){
            System.err.println("Invalid SQL Syntax!");
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        updateTableEquip();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        grpCompView = new javax.swing.ButtonGroup();
        grpEquipView = new javax.swing.ButtonGroup();
        tabTables = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        cboCompBranchV = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCompV = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        optIndivC = new javax.swing.JRadioButton();
        optTotalC = new javax.swing.JRadioButton();
        jLabel23 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        cboEquipBranchV = new javax.swing.JComboBox();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblEquipV = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        optIndivE = new javax.swing.JRadioButton();
        optGrpTotalE = new javax.swing.JRadioButton();
        optTotalE = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        cmdAddC = new javax.swing.JButton();
        cmdUpdateC = new javax.swing.JButton();
        cmdDeleteC = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        fraCompE = new javax.swing.JPanel();
        txtPCName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cboPCLocation = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        cboPCStat = new javax.swing.JComboBox();
        txtIPAdd = new javax.swing.JTextField();
        cmdPCSave = new javax.swing.JButton();
        cmdPCCancel = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        cboPCBranch = new javax.swing.JComboBox();
        jLabel25 = new javax.swing.JLabel();
        txtPCID = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCompUpd = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        txtSearchC = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblEquipUpd = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        cboLastBorrowedBy = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtEquipName = new javax.swing.JTextField();
        cboIsWorking = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        cboEquipLocation = new javax.swing.JComboBox();
        txtEquipID = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cmdEquipSave = new javax.swing.JButton();
        cmdEquipCancel = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        cboEquipBranch = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        dtpArrive = new com.toedter.calendar.JDateChooser();
        jLabel26 = new javax.swing.JLabel();
        dtpLB = new com.toedter.calendar.JDateChooser();
        jPanel7 = new javax.swing.JPanel();
        cmdDeleteE = new javax.swing.JButton();
        cmdUpdateE = new javax.swing.JButton();
        cmdAddE = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        txtSearchE = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lblHelper = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        lblDate = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        lblAlias = new javax.swing.JLabel();
        lblType = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mnuiOut = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mnuiExit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        mnuiPrint = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        mnuiDlvr = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        mnuiBL = new javax.swing.JMenuItem();
        mnuiFS = new javax.swing.JMenuItem();
        mnuiUA = new javax.swing.JMenuItem();
        mnuCID = new javax.swing.JMenu();
        mnuEID = new javax.swing.JMenu();

        jMenuItem2.setText("jMenuItem2");

        jMenuItem3.setText("jMenuItem3");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Binary Bean Inventory System");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel21.setText("Branch :");

        cboCompBranchV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboCompBranchVActionPerformed(evt);
            }
        });

        tblCompV.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblCompV);

        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        grpCompView.add(optIndivC);
        optIndivC.setText("<html>Individual Computer<br>Information</html>");
        optIndivC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optIndivCActionPerformed(evt);
            }
        });

        grpCompView.add(optTotalC);
        optTotalC.setSelected(true);
        optTotalC.setText("Total Number of Computers");
        optTotalC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optTotalCActionPerformed(evt);
            }
        });

        jLabel23.setText("View By :");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addGap(18, 18, 18)
                .addComponent(optTotalC)
                .addGap(37, 37, 37)
                .addComponent(optIndivC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(optTotalC)
                            .addComponent(jLabel23)))
                    .addComponent(optIndivC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(18, 18, 18)
                        .addComponent(cboCompBranchV, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(cboCompBranchV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cboEquipBranchV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboEquipBranchVActionPerformed(evt);
            }
        });

        jLabel22.setText("Branch :");

        tblEquipV.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblEquipV);

        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel24.setText("View By :");

        grpEquipView.add(optIndivE);
        optIndivE.setText("<html>Individual Equipment<br>Information</html>");
        optIndivE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optIndivEActionPerformed(evt);
            }
        });

        grpEquipView.add(optGrpTotalE);
        optGrpTotalE.setText("<html>Total Number of Equipments <br>(Group by Item Name)</html>");
        optGrpTotalE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optGrpTotalEActionPerformed(evt);
            }
        });

        grpEquipView.add(optTotalE);
        optTotalE.setSelected(true);
        optTotalE.setText("Total Number of Equipments");
        optTotalE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optTotalEActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24)
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(optGrpTotalE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(optTotalE)
                        .addGap(40, 40, 40)
                        .addComponent(optIndivE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(optTotalE)))
                    .addComponent(optIndivE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(optGrpTotalE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(18, 18, 18)
                        .addComponent(cboEquipBranchV, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 252, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(cboEquipBranchV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        tabTables.addTab("All", jPanel2);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cmdAddC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/gfxresources/computer_add.png"))); // NOI18N
        cmdAddC.setMnemonic('A');
        cmdAddC.setText("Add");
        cmdAddC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAddCActionPerformed(evt);
            }
        });

        cmdUpdateC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/gfxresources/computer_edit.png"))); // NOI18N
        cmdUpdateC.setMnemonic('U');
        cmdUpdateC.setText("Update");
        cmdUpdateC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdUpdateCActionPerformed(evt);
            }
        });

        cmdDeleteC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/gfxresources/computer_delete.png"))); // NOI18N
        cmdDeleteC.setMnemonic('D');
        cmdDeleteC.setText("Delete");
        cmdDeleteC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDeleteCActionPerformed(evt);
            }
        });

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmdAddC, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmdUpdateC, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmdDeleteC, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator4)
            .addComponent(jSeparator5)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdDeleteC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdAddC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdUpdateC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        fraCompE.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Computer's Information"));
        fraCompE.setName(""); // NOI18N

        txtPCName.setDocument(new JTextFieldLimit(25));
        txtPCName.setEnabled(false);
        txtPCName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPCNameKeyPressed(evt);
            }
        });

        jLabel1.setText("IP Address");

        jLabel2.setText("PC Name");

        jLabel3.setText("Status");

        cboPCLocation.setEnabled(false);

        jLabel4.setText("Location");

        cboPCStat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Working", "Not Working" }));
        cboPCStat.setEnabled(false);

        txtIPAdd.setDocument(new JTextFieldLimit(16));
        txtIPAdd.setEnabled(false);
        txtIPAdd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIPAddKeyPressed(evt);
            }
        });

        cmdPCSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/gfxresources/save.png"))); // NOI18N
        cmdPCSave.setMnemonic('s');
        cmdPCSave.setText("Save");
        cmdPCSave.setEnabled(false);
        cmdPCSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdPCSaveActionPerformed(evt);
            }
        });

        cmdPCCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/gfxresources/cancel.png"))); // NOI18N
        cmdPCCancel.setMnemonic('c');
        cmdPCCancel.setText("Cancel");
        cmdPCCancel.setEnabled(false);
        cmdPCCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdPCCancelActionPerformed(evt);
            }
        });

        jLabel19.setText("Branch");

        cboPCBranch.setEnabled(false);

        jLabel25.setText("PC ID");

        txtPCID.setDocument(new JTextFieldLimit(9));
        txtPCID.setEnabled(false);
        txtPCID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPCIDKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPCIDKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout fraCompELayout = new javax.swing.GroupLayout(fraCompE);
        fraCompE.setLayout(fraCompELayout);
        fraCompELayout.setHorizontalGroup(
            fraCompELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fraCompELayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fraCompELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel19)
                    .addComponent(jLabel25))
                .addGap(33, 33, 33)
                .addGroup(fraCompELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(fraCompELayout.createSequentialGroup()
                        .addComponent(cmdPCSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmdPCCancel))
                    .addComponent(txtPCID)
                    .addComponent(cboPCBranch, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboPCLocation, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboPCStat, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPCName)
                    .addComponent(txtIPAdd))
                .addContainerGap())
        );
        fraCompELayout.setVerticalGroup(
            fraCompELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fraCompELayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fraCompELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPCID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(fraCompELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIPAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(fraCompELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPCName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(fraCompELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboPCStat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(fraCompELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboPCLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(fraCompELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboPCBranch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 122, Short.MAX_VALUE)
                .addGroup(fraCompELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdPCCancel)
                    .addComponent(cmdPCSave))
                .addContainerGap())
        );

        tblCompUpd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Cpm"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCompUpd.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblCompUpd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblCompUpdMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(tblCompUpd);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/gfxresources/search.png"))); // NOI18N
        jLabel5.setText("Quick Search");

        txtSearchC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchCKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchCKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fraCompE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSearchC)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(365, 365, 365))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fraCompE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSearchC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );

        tabTables.addTab("Computers", jPanel3);

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblEquipUpd.setModel(new javax.swing.table.DefaultTableModel(
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
        tblEquipUpd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblEquipUpdMousePressed(evt);
            }
        });
        jScrollPane4.setViewportView(tblEquipUpd);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Equipment's Information"));

        cboLastBorrowedBy.setEnabled(false);

        jLabel8.setText("is Available");

        jLabel6.setText("Item ID");

        jLabel9.setText("Last Borrowed By");

        txtEquipName.setDocument(new JTextFieldLimit(40));
        txtEquipName.setEnabled(false);
        txtEquipName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEquipNameKeyPressed(evt);
            }
        });

        cboIsWorking.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Yes", "No" }));
        cboIsWorking.setEnabled(false);

        jLabel10.setText("Location");

        cboEquipLocation.setEnabled(false);

        txtEquipID.setDocument(new JTextFieldLimit(19));
        txtEquipID.setEnabled(false);
        txtEquipID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEquipIDKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEquipIDKeyReleased(evt);
            }
        });

        jLabel7.setText("Item Name");

        cmdEquipSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/gfxresources/save.png"))); // NOI18N
        cmdEquipSave.setMnemonic('s');
        cmdEquipSave.setText("Save");
        cmdEquipSave.setEnabled(false);
        cmdEquipSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdEquipSaveActionPerformed(evt);
            }
        });

        cmdEquipCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/gfxresources/cancel.png"))); // NOI18N
        cmdEquipCancel.setMnemonic('c');
        cmdEquipCancel.setText("Cancel");
        cmdEquipCancel.setEnabled(false);
        cmdEquipCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdEquipCancelActionPerformed(evt);
            }
        });

        jLabel20.setText("Branch");

        cboEquipBranch.setEnabled(false);

        jLabel16.setText("Date Arrived");

        dtpArrive.setDateFormatString("yyyy-MM-dd");
        dtpArrive.setEnabled(false);
        dtpArrive.setMaxSelectableDate(new java.util.Date(2871734399000L));
        dtpArrive.setMinSelectableDate(new java.util.Date(846781200000L));

        jLabel26.setText("Date Last Borrowed");

        dtpLB.setDateFormatString("yyyy-MM-dd");
        dtpLB.setEnabled(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(53, 53, 53)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEquipName, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cboIsWorking, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtEquipID)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cmdEquipCancel))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel20)
                            .addComponent(jLabel9)
                            .addComponent(jLabel16)
                            .addComponent(jLabel26))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboLastBorrowedBy, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboEquipBranch, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dtpArrive, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(cmdEquipSave)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(dtpLB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboEquipLocation, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtEquipID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtEquipName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cboIsWorking, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cboEquipLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(cboEquipBranch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cboLastBorrowedBy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16)
                    .addComponent(dtpArrive, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel26)
                    .addComponent(dtpLB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdEquipCancel)
                    .addComponent(cmdEquipSave))
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cmdDeleteE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/gfxresources/cog_delete.png"))); // NOI18N
        cmdDeleteE.setMnemonic('D');
        cmdDeleteE.setText("Delete");
        cmdDeleteE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDeleteEActionPerformed(evt);
            }
        });

        cmdUpdateE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/gfxresources/cog_edit.png"))); // NOI18N
        cmdUpdateE.setMnemonic('U');
        cmdUpdateE.setText("Update");
        cmdUpdateE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdUpdateEActionPerformed(evt);
            }
        });

        cmdAddE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/gfxresources/cog_add.png"))); // NOI18N
        cmdAddE.setMnemonic('A');
        cmdAddE.setText("Add");
        cmdAddE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAddEActionPerformed(evt);
            }
        });

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmdAddE, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmdUpdateE, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmdDeleteE, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmdDeleteE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdAddE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdUpdateE, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/gfxresources/search.png"))); // NOI18N
        jLabel11.setText("Quick Search");

        txtSearchE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchEKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchEKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSearchE, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSearchE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(0, 56, Short.MAX_VALUE)))
                .addContainerGap())
        );

        tabTables.addTab("Equipments", jPanel4);

        jLabel12.setFont(new java.awt.Font("Arial", 1, 70)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("<html><p align=\"center\">Ok Katol!<br>Inventory System</p></html>");

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/icon2.png"))); // NOI18N

        jPanel12.setBackground(new java.awt.Color(46, 141, 174));

        lblDate.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel18.setText("Logged In As:");

        jLabel13.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel13.setText("DATE:");

        jLabel15.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel15.setText("Role:");

        jLabel14.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel14.setText("TIME  :");

        lblTime.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        lblAlias.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        lblType.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addGap(18, 18, 18)
                .addComponent(lblAlias, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addComponent(lblType, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addGap(30, 30, 30)
                .addComponent(lblTime, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel14)
                                .addComponent(lblTime, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel13)
                                .addComponent(jLabel18))
                            .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAlias, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel12Layout.createSequentialGroup()
                            .addComponent(lblType, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(1, 1, 1))))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(lblHelper, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblHelper, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jMenu1.setText("File");

        mnuiOut.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        mnuiOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/gfxresources/Logout.png"))); // NOI18N
        mnuiOut.setText("Logout");
        mnuiOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuiOutActionPerformed(evt);
            }
        });
        jMenu1.add(mnuiOut);
        jMenu1.add(jSeparator1);

        mnuiExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.ALT_MASK));
        mnuiExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/gfxresources/cross.png"))); // NOI18N
        mnuiExit.setText("Exit");
        mnuiExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuiExitActionPerformed(evt);
            }
        });
        jMenu1.add(mnuiExit);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Print");

        mnuiPrint.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        mnuiPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/gfxresources/printer.png"))); // NOI18N
        mnuiPrint.setText("Print Form");
        mnuiPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuiPrintActionPerformed(evt);
            }
        });
        jMenu2.add(mnuiPrint);
        jMenu2.add(jSeparator6);

        mnuiDlvr.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        mnuiDlvr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/gfxresources/email.png"))); // NOI18N
        mnuiDlvr.setText("Delivery Form");
        mnuiDlvr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuiDlvrActionPerformed(evt);
            }
        });
        jMenu2.add(mnuiDlvr);

        jMenuBar1.add(jMenu2);

        jMenu3.setText(" Others");

        mnuiBL.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        mnuiBL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/gfxresources/world.png"))); // NOI18N
        mnuiBL.setText("Branch/Locations");
        mnuiBL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuiBLActionPerformed(evt);
            }
        });
        jMenu3.add(mnuiBL);

        mnuiFS.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        mnuiFS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/gfxresources/user.png"))); // NOI18N
        mnuiFS.setText("Faculty/Staff");
        mnuiFS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuiFSActionPerformed(evt);
            }
        });
        jMenu3.add(mnuiFS);

        mnuiUA.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        mnuiUA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/gfxresources/key.png"))); // NOI18N
        mnuiUA.setText("User Accounts");
        mnuiUA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuiUAActionPerformed(evt);
            }
        });
        jMenu3.add(mnuiUA);

        jMenuBar1.add(jMenu3);
        jMenuBar1.add(mnuCID);
        jMenuBar1.add(mnuEID);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(tabTables, javax.swing.GroupLayout.PREFERRED_SIZE, 955, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(tabTables, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Send contents of Equipments table to its associated components
    private void tblEquipUpdMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEquipUpdMousePressed
        // TODO add your handling code here:
        tblEquipUpd.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent evt)
            {
                try{
                int row = tblEquipUpd.getSelectedRow();
                String tblClick = (tblEquipUpd.getModel().getValueAt(row, 0).toString());
                String sql = "Select * from equipments where item_id='"+tblClick+"' ";
                
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                    if(rs.next()){
                        String id = rs.getString("item_id");
                        txtEquipID.setText(id);
                        
                        String name = rs.getString("item_name");
                        txtEquipName.setText(name);
                        
                        String isAvail = rs.getString("is_available");
                        cboIsWorking.setSelectedItem(isAvail);

                        String loc = rs.getString("location");
                        cboEquipLocation.setSelectedItem(loc);
                        
                        String brch = rs.getString("branch");
                        cboEquipBranch.setSelectedItem(brch); 
                        
                        String lbb = rs.getString("last_borrowed_by");
                        cboLastBorrowedBy.setSelectedItem(lbb);
                        
                        Date da = rs.getDate("date_arrived");
                        dtpArrive.setDate(da);
                        
                        Date dlb = rs.getDate("date_lb");
                        dtpLB.setDate(dlb);
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }//GEN-LAST:event_tblEquipUpdMousePressed

    private void cmdEquipSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdEquipSaveActionPerformed
        String id = txtEquipID.getText();
        String name = txtEquipName.getText();
        String avail = (String) cboIsWorking.getSelectedItem();
        String loc = (String) cboEquipLocation.getSelectedItem();
        String brch = (String) cboEquipBranch.getSelectedItem();
        String lbb = (String) cboLastBorrowedBy.getSelectedItem();
        String da = ((JTextField)dtpArrive.getDateEditor().getUiComponent()).getText();
        String dlb = ((JTextField)dtpLB.getDateEditor().getUiComponent()).getText();
        if((id.equals("")) || (name.equals("")) || (avail.equals("")) || (loc.equals(" ")) || 
                (brch.equals(" ")) || (lbb.equals(" ")) || (da.equals(" ")) || (dlb.equals(" "))){
            JOptionPane.showMessageDialog(null, "Fill-up the blank fields!", "Blank Field",
                    JOptionPane.WARNING_MESSAGE);
        }else if(mnuEID.getText().equals(txtEquipID.getText())){
            JOptionPane.showMessageDialog(null,"Item ID already exist!",
                "Duplicate Item ID",JOptionPane.ERROR_MESSAGE);
            txtPCID.setText("");
        }else{
            String a = "AddE";
            String u = "UpdE";
            if (a.equals(lblHelper.getText())){
                addEquipment();
                System.out.println("add");
            }else if(u.equals(lblHelper.getText())){
                editEquipment();
                System.out.println("update");
            }else{
                System.err.println("error!");
            }
            disableComponentsE();
            clearTextE();
        }
    }//GEN-LAST:event_cmdEquipSaveActionPerformed

    private void cmdPCSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdPCSaveActionPerformed
        String id = txtPCID.getText();
        String name = txtPCName.getText();
        String ip = txtIPAdd.getText();
        String stat = (String) cboPCStat.getSelectedItem();
        String loc = (String) cboPCLocation.getSelectedItem();
        String brch = (String) cboPCBranch.getSelectedItem();
        
        if((id.equals("")) || (name.equals("")) || (ip.equals("")) || (stat.equals(" ")) || 
                (loc.equals(" ")) || (brch.equals(" "))){
            JOptionPane.showMessageDialog(null, "Fill-up the blank fields!", "Blank Field",
                    JOptionPane.WARNING_MESSAGE);
        }else if(mnuCID.getText().equals(txtPCID.getText())){
            JOptionPane.showMessageDialog(null,"PC ID already exist!",
                "Duplicate PC ID",JOptionPane.ERROR_MESSAGE);
            txtPCID.setText("");
        }else{
            String a = "AddC";
            String u = "UpdC";
            if (a.equals(lblHelper.getText())){
                addComputer();
                System.out.println("Add");
            }else if(u.equals(lblHelper.getText())){
                editComputer();
                System.out.println("Updated");
            }else{
                System.err.println("error!");
            }
            clearTextC();
            disableComponentsC();
        }
    }//GEN-LAST:event_cmdPCSaveActionPerformed

    private void cmdDeleteCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDeleteCActionPerformed
        String type = lblType.getText();
        if(type.equals("Assistant")){
            JOptionPane.showMessageDialog(null, "Insufficient Account Privillege",
                    "Invalid Account Type",JOptionPane.ERROR_MESSAGE);            
        }else{
            String id = txtPCID.getText();
            if(id.equals("")){
                JOptionPane.showMessageDialog(null, "No Information selected",
                        "Cannot delete Empty record",JOptionPane.ERROR_MESSAGE);
            }else{
                int q = JOptionPane.showConfirmDialog(null,
                        "Do you really want to delete the selected information?","Delete Record",
                        JOptionPane.YES_NO_OPTION);
                if (q==0){
                    String sql = "delete from computers where pc_id =?";
                    try{
                        pst = conn.prepareStatement(sql);
                        pst.setString(1, txtPCID.getText());
                        pst.execute();
                        JOptionPane.showMessageDialog(null, "Information Deleted!",
                                "Deleted to Database",JOptionPane.INFORMATION_MESSAGE);
                    }catch(SQLException e){
                        System.err.println("Invalid SQL Syntax!");
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    clearTextC();
                    updateTableComputer();
                }
            }
        }
    }//GEN-LAST:event_cmdDeleteCActionPerformed

    private void cmdAddCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAddCActionPerformed
        // TODO add your handling code here:
        lblHelper.setText("AddC");
        enableComponentsC();
        clearTextC();
    }//GEN-LAST:event_cmdAddCActionPerformed

    private void cmdUpdateCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdUpdateCActionPerformed
        // TODO add your handling code here:
         lblHelper.setText("UpdC");
         enableComponentsC();
    }//GEN-LAST:event_cmdUpdateCActionPerformed

    private void cmdAddEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAddEActionPerformed
        // TODO add your handling code here:
        lblHelper.setText("AddE");
        enableComponentsE();
        clearTextE();
    }//GEN-LAST:event_cmdAddEActionPerformed

    private void cmdDeleteEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDeleteEActionPerformed
        String type = lblType.getText();
        if(type.equals("Assistant")){
            JOptionPane.showMessageDialog(null, "Insufficient Account Privillege",
                    "Invalid Account Type",JOptionPane.ERROR_MESSAGE);
        }else{
            String id = txtEquipID.getText();
            if(id.equals("")){
                JOptionPane.showMessageDialog(null,"No Information selected",
                        "Cannot delete Empty record",JOptionPane.ERROR_MESSAGE);
            }else{
                int q = JOptionPane.showConfirmDialog(null,
                        "Do you really want to delete the selected information?","Delete Record",
                        JOptionPane.YES_NO_OPTION);
                if (q==0){
                    String sql = "delete from equipments where item_id =?";
                    try{
                        pst = conn.prepareStatement(sql);
                        pst.setString(1, txtEquipID.getText());
                        pst.execute();
                        JOptionPane.showMessageDialog(null, "Information Deleted!",
                                "Deleted to Database",JOptionPane.INFORMATION_MESSAGE);
                    }catch(SQLException e){
                        System.err.println("Invalid SQL Syntax!");
                    }catch(Exception e){
                        e.printStackTrace();
                        clearTextE();
                    }
                    updateTableEquip();
                }
            }
        }
    }//GEN-LAST:event_cmdDeleteEActionPerformed

    private void cmdUpdateEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdUpdateEActionPerformed
        // TODO add your handling code here:
        lblHelper.setText("UpdE");
        enableComponentsE();
        dtpArrive.setEnabled(false);
        txtEquipID.setEnabled(false);
    }//GEN-LAST:event_cmdUpdateEActionPerformed

    private void optTotalCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optTotalCActionPerformed
        // TODO add your handling code here:
        viewTableComputer();
    }//GEN-LAST:event_optTotalCActionPerformed

    private void optIndivCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optIndivCActionPerformed
        // TODO add your handling code here:
        viewTableComputerInd();
    }//GEN-LAST:event_optIndivCActionPerformed

    private void cmdEquipCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdEquipCancelActionPerformed
        // TODO add your handling code here:
        disableComponentsE();
        clearTextE();
    }//GEN-LAST:event_cmdEquipCancelActionPerformed

    private void cmdPCCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdPCCancelActionPerformed
        // TODO add your handling code here:
        disableComponentsC();
        clearTextC();
    }//GEN-LAST:event_cmdPCCancelActionPerformed

    private void txtSearchCKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchCKeyReleased
        String a = "";
        if (a.equals(txtSearchC.getText())){
            updateTableComputer();
        }else{
            try{
                String sql = "select * from computers where pc_name like ? or ip_address like ? "
                        + "or location like ? or status like ? or branch like ? or pc_id like ? ";
                pst = conn.prepareStatement(sql);
                pst.setString(1, txtSearchC.getText());
                pst.setString(2, txtSearchC.getText());
                pst.setString(3, txtSearchC.getText());
                pst.setString(4, txtSearchC.getText());
                pst.setString(5, txtSearchC.getText());
                pst.setString(6, txtSearchC.getText());
                rs = pst.executeQuery();
                //if(rs.next()){
                    tblCompUpd.setModel(DbUtils.resultSetToTableModel(rs));
                //}
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_txtSearchCKeyReleased

    private void txtSearchEKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchEKeyReleased
        // TODO add your handling code here:
        try{
            String sql = "select * from equipments where item_id =? or item_name=? "
                    + "or location=? or is_available=? or branch=? or last_borrowed_by =? ";
            pst = conn.prepareStatement(sql);
            pst.setString(1, txtSearchE.getText());
            pst.setString(2, txtSearchE.getText());
            pst.setString(3, txtSearchE.getText());
            pst.setString(4, txtSearchE.getText());
            pst.setString(5, txtSearchE.getText());
            pst.setString(6, txtSearchE.getText());
            rs = pst.executeQuery();
            //if(rs.next()){
                tblEquipUpd.setModel(DbUtils.resultSetToTableModel(rs));
            //}
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtSearchEKeyReleased

    private void txtPCIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPCIDKeyPressed
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
    }//GEN-LAST:event_txtPCIDKeyPressed

    private void txtIPAddKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIPAddKeyPressed
        // TODO add your handling code here:
        //Numbers Only & dot "."
        evt.getKeyChar();
        if (evt.getKeyCode() >= 48 && evt.getKeyCode() <= 57 || 
                evt.getKeyCode() >= 96 && evt.getKeyCode() <= 105 ||
                evt.getKeyCode() == 8 || evt.getKeyCode() == 46 || 
                evt.getKeyCode() == 27 || evt.getKeyCode() == 110){
            
        }else{
            JOptionPane.showMessageDialog(null,"Invalid character entered!",
                    "Invalid character",JOptionPane.WARNING_MESSAGE);
            evt.setKeyCode(8);
        }
    }//GEN-LAST:event_txtIPAddKeyPressed

    private void txtPCNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPCNameKeyPressed
        // TODO add your handling code here:
        evt.getKeyChar();
        if (evt.getKeyCode() >= 65 && evt.getKeyCode() <= 90 || 
                evt.getKeyCode() >= 48 && evt.getKeyCode() <= 57 || 
                evt.getKeyCode() == 16 || evt.getKeyCode() == 8 || 
		evt.getKeyCode() >= 96 & evt.getKeyCode() <=105 || 
                evt.getKeyCode() == 45 || evt.getKeyCode() == 95 || 
                evt.getKeyCode() == 27 || evt.getKeyCode() == 10){
            
        }else{
            JOptionPane.showMessageDialog(null,"Invalid character entered!",
                    "Invalid character",JOptionPane.WARNING_MESSAGE);
            evt.setKeyCode(8);
        }
    }//GEN-LAST:event_txtPCNameKeyPressed

    private void txtSearchCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchCKeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtSearchCKeyPressed

    private void txtEquipNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEquipNameKeyPressed
        evt.getKeyChar();
        if (evt.getKeyCode() >= 65 && evt.getKeyCode() <= 90 || 
                evt.getKeyCode() == 32 || evt.getKeyCode() >= 8 && 
                evt.getKeyCode() <= 10 || evt.getKeyCode() >= 96 && 
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
    }//GEN-LAST:event_txtEquipNameKeyPressed

    private void txtSearchEKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchEKeyPressed
    
    }//GEN-LAST:event_txtSearchEKeyPressed

    private void optTotalEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optTotalEActionPerformed
        viewTableEquip();
    }//GEN-LAST:event_optTotalEActionPerformed

    private void mnuiBLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuiBLActionPerformed
        BranchLocation bl = BranchLocation.getObj();
        bl.setLocationRelativeTo(null);
        bl.setVisible(true);
        bl.setResizable(false);
    }//GEN-LAST:event_mnuiBLActionPerformed

    private void optGrpTotalEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optGrpTotalEActionPerformed
        viewTableEquipGrp();
    }//GEN-LAST:event_optGrpTotalEActionPerformed

    private void cboCompBranchVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboCompBranchVActionPerformed
        // TODO add your handling code here:
        if(optTotalC.isSelected() == true){
            viewTableComputer();
        }else if (optIndivC.isSelected() == true){
            viewTableComputerInd();
        }
    }//GEN-LAST:event_cboCompBranchVActionPerformed

    private void cboEquipBranchVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboEquipBranchVActionPerformed
        if(optTotalE.isSelected() == true){
            viewTableEquip();
        }else if (optGrpTotalE.isSelected() == true){
            viewTableEquipGrp();
        }else if (optIndivE.isSelected() == true){
            viewTableEquipInd();
        }
    }//GEN-LAST:event_cboEquipBranchVActionPerformed

    private void optIndivEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optIndivEActionPerformed
        viewTableEquipInd();
    }//GEN-LAST:event_optIndivEActionPerformed

    private void mnuiFSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuiFSActionPerformed
        FacultyStaff fs = FacultyStaff.getObj();
        fs.setLocationRelativeTo(null);
        fs.setVisible(true);
        fs.setResizable(false);
    }//GEN-LAST:event_mnuiFSActionPerformed

    private void mnuiUAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuiUAActionPerformed
        UserAccounts ua = UserAccounts.getObj();
        String type = lblType.getText();
        if(type.equals("Assistant")){
            JOptionPane.showMessageDialog(null, "Insufficient Account Privillege",
                    "Invalid Account Type",JOptionPane.ERROR_MESSAGE);
        }else{
            ua.setLocationRelativeTo(null);
            ua.setVisible(true);
            ua.setResizable(false);
        }
    }//GEN-LAST:event_mnuiUAActionPerformed

    //Send contents of Computers table to its associated components
    private void tblCompUpdMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCompUpdMousePressed
        // TODO add your handling code here:
        tblCompUpd.addMouseListener(new MouseAdapter()
            {
                public void mouseClicked(MouseEvent evt)
                {
                    try{
                        int row = tblCompUpd.getSelectedRow();
                        String tblClick = (tblCompUpd.getModel().getValueAt(row, 0).toString());
                        String sql = "Select * from computers where pc_id='"+tblClick+"' ";

                        pst = conn.prepareStatement(sql);
                        rs = pst.executeQuery();
                        if(rs.next()){
                            String id = rs.getString("pc_id");
                            txtPCID.setText(id);

                            String ip = rs.getString("ip_address");
                            txtIPAdd.setText(ip);

                            String name = rs.getString("pc_name");
                            txtPCName.setText(name);

                            String stat = rs.getString("status");
                            cboPCStat.setSelectedItem(stat);

                            String loc = rs.getString("location");
                            cboPCLocation.setSelectedItem(loc);

                            String brch = rs.getString("branch");
                            cboPCBranch.setSelectedItem(brch);
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            });
    }//GEN-LAST:event_tblCompUpdMousePressed

    private void txtEquipIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEquipIDKeyPressed
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
    }//GEN-LAST:event_txtEquipIDKeyPressed

    private void txtPCIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPCIDKeyReleased
        try{
            String sql = "select * from computers where pc_id=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, txtPCID.getText());
            rs = pst.executeQuery();              
            if(rs.next()){
                String id = rs.getString("pc_id");
                mnuCID.setText(id);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtPCIDKeyReleased

    private void txtEquipIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEquipIDKeyReleased
        try{
            String sql = "select * from equipments where item_id=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, txtEquipID.getText());
            rs = pst.executeQuery();              
            if(rs.next()){
                String id = rs.getString("item_id");
                mnuEID.setText(id);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtEquipIDKeyReleased

    private void mnuiDlvrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuiDlvrActionPerformed
        DeliveryReceipt dr = DeliveryReceipt.getObj();
        dr.setLocationRelativeTo(null);
        dr.setVisible(true);
    }//GEN-LAST:event_mnuiDlvrActionPerformed

    private void mnuiPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuiPrintActionPerformed
        Report rep = Report.getObj();
        rep.setLocationRelativeTo(null);
        rep.setVisible(true);
        rep.setResizable(false);
    }//GEN-LAST:event_mnuiPrintActionPerformed

    private void mnuiExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuiExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_mnuiExitActionPerformed

    private void mnuiOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuiOutActionPerformed
        Login frmLogin = new Login();
        frmLogin.setLocationRelativeTo(null);
        frmLogin.setVisible(true);
        frmLogin.setResizable(false);
        dispose();
    }//GEN-LAST:event_mnuiOutActionPerformed

    
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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Main frmMain = new  Main();
                frmMain.setLocationRelativeTo(null);
                frmMain.setVisible(true);
                frmMain.setResizable(false);
                             
                
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cboCompBranchV;
    private javax.swing.JComboBox cboEquipBranch;
    private javax.swing.JComboBox cboEquipBranchV;
    private javax.swing.JComboBox cboEquipLocation;
    private javax.swing.JComboBox cboIsWorking;
    private javax.swing.JComboBox cboLastBorrowedBy;
    private javax.swing.JComboBox cboPCBranch;
    private javax.swing.JComboBox cboPCLocation;
    private javax.swing.JComboBox cboPCStat;
    private javax.swing.JButton cmdAddC;
    private javax.swing.JButton cmdAddE;
    private javax.swing.JButton cmdDeleteC;
    private javax.swing.JButton cmdDeleteE;
    private javax.swing.JButton cmdEquipCancel;
    private javax.swing.JButton cmdEquipSave;
    private javax.swing.JButton cmdPCCancel;
    private javax.swing.JButton cmdPCSave;
    private javax.swing.JButton cmdUpdateC;
    private javax.swing.JButton cmdUpdateE;
    private com.toedter.calendar.JDateChooser dtpArrive;
    private com.toedter.calendar.JDateChooser dtpLB;
    private javax.swing.JPanel fraCompE;
    private javax.swing.ButtonGroup grpCompView;
    private javax.swing.ButtonGroup grpEquipView;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    public javax.swing.JLabel lblAlias;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblHelper;
    private javax.swing.JLabel lblTime;
    public javax.swing.JLabel lblType;
    private javax.swing.JMenu mnuCID;
    private javax.swing.JMenu mnuEID;
    private javax.swing.JMenuItem mnuiBL;
    private javax.swing.JMenuItem mnuiDlvr;
    private javax.swing.JMenuItem mnuiExit;
    private javax.swing.JMenuItem mnuiFS;
    private javax.swing.JMenuItem mnuiOut;
    private javax.swing.JMenuItem mnuiPrint;
    private javax.swing.JMenuItem mnuiUA;
    private javax.swing.JRadioButton optGrpTotalE;
    private javax.swing.JRadioButton optIndivC;
    private javax.swing.JRadioButton optIndivE;
    private javax.swing.JRadioButton optTotalC;
    private javax.swing.JRadioButton optTotalE;
    private javax.swing.JTabbedPane tabTables;
    private javax.swing.JTable tblCompUpd;
    private javax.swing.JTable tblCompV;
    private javax.swing.JTable tblEquipUpd;
    private javax.swing.JTable tblEquipV;
    private javax.swing.JTextField txtEquipID;
    private javax.swing.JTextField txtEquipName;
    private javax.swing.JTextField txtIPAdd;
    private javax.swing.JTextField txtPCID;
    private javax.swing.JTextField txtPCName;
    private javax.swing.JTextField txtSearchC;
    private javax.swing.JTextField txtSearchE;
    // End of variables declaration//GEN-END:variables
}
