/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package phonebookproject;

/**
 *
 * @author Dhiraj Bhattarai
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class PhoneBookProject implements ActionListener{

    JTextField tf1;
    JPasswordField tf2;
    JLabel l1,l2,l3;
    JButton b1;
    JDialog d;
    Connection con = null;
    Statement stmt = null;
    
    
    PhoneBookProject()throws ClassNotFoundException, SQLException{
        
        d = new JDialog();
        d.setTitle("Login");
        d.setSize(400,250);
        d.setLayout(null);
        d.setModal(true);
        d.setLocation(200,100);
        d.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:Mysql://localhost/abook","root","");
        stmt = con.createStatement();
        Font f1 = new Font("Lucida HandWritting",Font.ITALIC,18);
        l3 = new JLabel("Dhiraj's Address Book");
        l3.setBounds(100,10,300,40);
        l3.setFont(f1);
        d.add(l3);
        
        l1 = new JLabel("User Name");
        l1.setBounds(50,70,100,25);
        d.add(l1);
        
        tf1 = new JTextField();
        tf1.setBounds(160,70,100,25);
        d.add(tf1);
        
        l2 = new JLabel("Password");
        l2.setBounds(50,100,100,25);
        d.add(l2);
        
        tf2 = new JPasswordField();
        tf2.setBounds(160,100,100,25);
        d.add(tf2);
        
        b1 = new JButton("Login");
        b1.setBounds(150,140,100,25);
        d.add(b1);
        
        b1.addActionListener(this);
        d.setVisible(true);
    }
    public void actionPerformed(ActionEvent ea){
        String s1 = tf1.getText();
        String s2 = tf2.getText();
        
        if(ea.getActionCommand() == "Login"){
            try{
                ResultSet rs = stmt.executeQuery("Select * From Login");
                while(rs.next()){
                    String s3 = rs.getString(1);
                    String s4 = rs.getString(2);
                    if(s3.equals(s1) && s4.equals(s2)){
                        d.hide();
                    }
                    
                }
                tf1.setText("");
                tf2.setText("");
                tf1.requestFocus();
            }
            catch(SQLException se){
                System.out.println(se);
            }
        }
    }
}
    class ABStart implements ActionListener{
        
        JButton b1, b2, b3, b4, b5;
        JFrame jf;
        JPanel p1, p2,p3;
        JLabel l,l1,l2,l3,l4,nl;
        JTextField tf1,tf2,tf3,tf4,nt;
        JButton next, prev, first, last, insert, update_name, update_cell, update_email, update_res;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs;
        
        public ABStart() throws ClassNotFoundException, SQLException{
            jf = new JFrame();
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jf.setSize(750,400);
            jf.setTitle("My Address Book");
            jf.setLocation(100,100);
            jf.setLayout(new BorderLayout());
            jf.setResizable(false);
            
            p1 = new JPanel(); p2 = new JPanel(); p3 = new JPanel();
            p3.setBorder(BorderFactory.createLineBorder(Color.BLUE));
            
            jf.add(p1,BorderLayout.NORTH);
            jf.add(p2,BorderLayout.SOUTH);
            jf.add(p3,BorderLayout.CENTER);
            
            Font f1 = new Font("Mistral", Font.BOLD,24);
            l = new JLabel("Dhiraj's Address Book");
            l.setFont(f1);
            p1.add(l);
            
            b1 = new JButton("view");
            b2 = new JButton("Add");
            b3 = new JButton("Delete");
            b4 = new JButton("Update");
            b5 = new JButton("Search");
            p2.add(b1); p2.add(b2); p2.add(b3); p2.add(b4); p2.add(b5);
            
            b1.addActionListener(this);
            b2.addActionListener(this);
            b3.addActionListener(this);
            b4.addActionListener(this);
            b5.addActionListener(this);
            jf.setVisible(true);
            
            new PhoneBookProject();     
        } 
        public void actionPerformed(ActionEvent ae){
            if(ae.getActionCommand()== "view"){
                JPanel sp1 = new JPanel();
                JPanel sp2 = new JPanel();
                
                p3.removeAll();
                p3.setLayout(new BorderLayout());
                l1 = new JLabel("Name     ");
                l2 = new JLabel("Cell Number ");
                l3 = new JLabel("Email     ");
                l4 = new JLabel("Residence   ");
                
                tf1 = new JTextField(15);
                tf2 = new JTextField(15);
                tf3 = new JTextField(15);
                tf4 = new JTextField(15);
                
                first = new JButton("<<");
                last = new JButton(">>");
                next = new JButton(">");
                prev = new JButton("<");
                
                first.setToolTipText("First Record");
                last.setToolTipText("Last Record");
                next.setToolTipText("Next Record");
                prev.setToolTipText("Previous Record");
                
                first.addActionListener(this);
                last.addActionListener(this);
                next.addActionListener(this);
                prev.addActionListener(this);
                
                sp1.add(first); sp1.add(prev); sp1.add(next); sp1.add(last);
                sp2.setLayout(new FlowLayout(FlowLayout.CENTER,150,20));
                
                sp1.setBorder(BorderFactory.createRaisedBevelBorder());
                sp2.setBorder(BorderFactory.createLoweredBevelBorder());
                
                sp2.add(l1); sp2.add(tf1); sp2.add(l2); sp2.add(tf2);
                sp2.add(l3); sp2.add(tf3); sp2.add(l4); sp2.add(tf4);
                p3.add(sp1,BorderLayout.SOUTH);
                p3.add(sp2,BorderLayout.CENTER);
                jf.setVisible(true);
                
                try{
                    
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:Mysql://localhost/abook","root","");
                    stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    rs = stmt.executeQuery("Select * From information");
                    rs.next();
                    tf1.setText(rs.getString(1));
                    tf2.setText(rs.getString(2));
                    tf3.setText(rs.getString(3));
                    tf4.setText(rs.getString(4));
                    
                }
                catch(Exception se){
                    System.out.println(se);
                }  
                
            }
            
            if(ae.getActionCommand()==">"){
                try{
                    
                    rs.next();
                    tf1.setText(rs.getString(1));
                    tf2.setText(rs.getString(2));
                    tf3.setText(rs.getString(3));
                    tf4.setText(rs.getString(4));
                }
                catch(Exception se){
                    System.out.println(se);
                
                } 
            }
             if(ae.getActionCommand()=="<"){
                try{
                    
                    rs.previous();
                    tf1.setText(rs.getString(1));
                    tf2.setText(rs.getString(2));
                    tf3.setText(rs.getString(3));
                    tf4.setText(rs.getString(4));
                }
                catch(Exception se){
                    System.out.println(se);
                
                } 
            }
            
             if(ae.getActionCommand()==">>"){
                try{
                    ResultSet rs = stmt.executeQuery("Select * From information");
                    rs.last();
                    tf1.setText(rs.getString(1));
                    tf2.setText(rs.getString(2));
                    tf3.setText(rs.getString(3));
                    tf4.setText(rs.getString(4));
                }
                catch(Exception se){
                    System.out.println(se);
                
                } 
            }
              if(ae.getActionCommand()=="<<"){
                try{
                    ResultSet rs = stmt.executeQuery("Select * From information");
                    rs.first();
                    tf1.setText(rs.getString(1));
                    tf2.setText(rs.getString(2));
                    tf3.setText(rs.getString(3));
                    tf4.setText(rs.getString(4));
                }
                catch(Exception se){
                    System.out.println(se);
                
                } 
            }
             
              if(ae.getActionCommand()=="Add"){
                  p3.removeAll();
                  JPanel sp1 = new JPanel();
                  JPanel sp2 = new JPanel();
                  insert = new JButton("Insert");
                  
                  p3.setLayout(new BorderLayout());
                  l1 = new JLabel("Name           ");
                  l2 = new JLabel("Cell Number");
                  l3 = new JLabel("Email            ");
                  l4 = new JLabel("Residence  ");
                  
                  tf1 = new JTextField(15);
                  tf2 = new JTextField(15);
                  tf3 = new JTextField(15);
                  tf4 = new JTextField(15);
                  
                  sp1.setLayout(new FlowLayout(FlowLayout.CENTER,150,20));
                  sp2.setLayout(new FlowLayout(FlowLayout.CENTER,150,20));
                  sp1.setBorder(BorderFactory.createLoweredBevelBorder());
                  sp2.setBorder(BorderFactory.createRaisedBevelBorder());
                  
                  sp1.add(l1); sp1.add(tf1); sp1.add(l2); sp1.add(tf2);
                  sp1.add(l3); sp1.add(tf3); sp1.add(l4); sp1.add(tf4);
                  
                  sp2.add(insert); 
                  insert.addActionListener(this); 
                  
                  p3.add(sp1,BorderLayout.CENTER);
                  p3.add(sp2,BorderLayout.SOUTH);
                  jf.setVisible(true);
                  
              }
              
          if(ae.getActionCommand()== "Insert"){
              String n,c,e,r;
              n = tf1.getText();
              c = tf2.getText();
              e = tf3.getText();
              r = tf4.getText();
              
              try{  
                  
                  Class.forName("com.mysql.jdbc.Driver");
                  Connection con = DriverManager.getConnection("jdbc:Mysql://localhost/abook","root","");
                  stmt = con.createStatement();
                  stmt.executeUpdate("Insert into information values('"+n+"', '"+c+"', '"+e+"', '"+r+"')");
                  con.close();
                  JOptionPane.showMessageDialog(jf, "Record Inserted Successfully");//, "Insertion Complete", JOptionPane.INFORMATION_MESSAGE");
                  
              }
              catch(Exception se){
                  System.out.println(se);
              }
        }
          
          if(ae.getActionCommand()=="Search"){
              p3.removeAll();
              JPanel sp1 = new JPanel();
              JPanel sp2 = new JPanel();
              l1 = new  JLabel("Name            ");
              l2 = new JLabel("Cell Number");
              l3 = new JLabel("Email             ");
              l4 = new JLabel("Residance   ");
              
              tf1 = new JTextField(15);
              tf2 = new JTextField(15);
              tf3 = new JTextField(15);
              tf4 = new JTextField(15);
              
              Font f1 = new Font("Lucida Handwritting", Font.ITALIC,12);
              JLabel x = new JLabel("Your Search Result");
              x.setFont(f1);
              x.setForeground(Color.red);
              
              p3.setLayout(new BorderLayout());
              sp1.setLayout(new FlowLayout(FlowLayout.CENTER,150,20));
              sp2.setLayout(new FlowLayout(FlowLayout.CENTER,150,20));
              
              sp1.setBorder(BorderFactory.createLoweredBevelBorder());
              sp2.setBorder(BorderFactory.createRaisedBevelBorder());
              
              sp1.add(l1); sp1.add(tf1); sp1.add(l2); sp1.add(tf2);
              sp1.add(l3); sp1.add(tf3); sp1.add(l4); sp1.add(tf4);
              sp2.add(x);
              p3.add(sp1,BorderLayout.CENTER);
              p3.add(sp2,BorderLayout.NORTH);
              
              jf.setVisible(true);
              
              String n = JOptionPane.showInputDialog("Enter Name Of Person");
              
              try{
                  Class.forName("com.mysql.jdbc.Driver");
                  Connection con = DriverManager.getConnection("jdbc:Mysql://localhost/abook","root","");
                  stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
                  rs = stmt.executeQuery("Select * From information WHERE Name = '"+n+"' ");
                  
                  rs.next();
                  
                  tf1.setText(rs.getString(1));
                  tf2.setText(rs.getString(2));
                  tf3.setText(rs.getString(3));
                  tf4.setText(rs.getString(4));
                  
                  if(!rs.next() ){
                      System.out.println("None");
                  }
   
              }
              catch(Exception se){
                  System.out.println(se);
              }
          }
        
          if(ae.getActionCommand()== "Delete"){
              String n = JOptionPane.showInputDialog("Enter the Name of Person");
              
              try{
                 Class.forName("com.mysql.jdbc.Driver");
                 Connection con = DriverManager.getConnection("jdbc:Mysql://localhost/abook","root","");
                 stmt = con.createStatement();
                 stmt.executeUpdate("Delete  From information WHERE Name = '"+n+"' ");
                 con.close();
                 JOptionPane.showMessageDialog(jf, "Record Deleted Succesfully");
              }
              catch(Exception se){
                  System.out.println(ae);
              }
          }
          
          
    }
    
    public static void main(String[] args) throws ClassNotFoundException,SQLException{
      
      new ABStart();
        
    }
    
}
