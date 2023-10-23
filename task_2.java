package com.TechVidvan;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
public class StudentManagement {
  private JFrame frmStduentManagementSystem;
  private JTextField nameTextField;
  private JTextField fatherNameTextField;
  private JTextField contactTextField;
  private JTextField emailTextField;
  private JTextField idTextField;
  private JTextField dobtextField;
  private JTable table;
  private JTextField sectionTextField;
  private JTextField deletetextfield;
  private JTextField searchTextField;
  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          Database.dbInit();
          StudentManagement window = new StudentManagement();
          window.frmStduentManagementSystem.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }
  /**
   * Create the application.
   */
  public StudentManagement() {
    initialize();
  }
  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
//		Creating new frame for the components
    frmStduentManagementSystem = new JFrame();
    frmStduentManagementSystem.setTitle("Stduent Management System by TechVidvan");
    frmStduentManagementSystem.setBounds(100, 100, 1100, 600);
    frmStduentManagementSystem.setResizable(false);
    frmStduentManagementSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frmStduentManagementSystem.getContentPane();
//		creating the table model for jtable
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Id");
    model.addColumn("Name");
    model.addColumn("Father's Name");
    model.addColumn("D.O.B");
    model.addColumn("Gender");
    model.addColumn("Contact");
    model.addColumn("Section");
    model.addColumn("E-mail");
    model.addColumn("Address");
    
//		Adding the UI components
    JPanel inputPanel = new JPanel();
    inputPanel.setBounds(0, 0, 400, 565);
    inputPanel.setBackground(new Color(153, 204, 255));
    GridLayout gl_inputPanel = new GridLayout(0, 2);
    gl_inputPanel.setVgap(30);
    inputPanel.setLayout(gl_inputPanel);
    
    JLabel idLabel = new JLabel("Student ID");
    inputPanel.add(idLabel);
    
    idTextField = new JTextField();
    idTextField.setHorizontalAlignment(SwingConstants.LEFT);
    inputPanel.add(idTextField);
    
    JLabel namelabel = new JLabel("Student Name");
    inputPanel.add(namelabel);
    
    nameTextField = new JTextField();
    nameTextField.setToolTipText("");
    nameTextField.setHorizontalAlignment(SwingConstants.LEFT);
    inputPanel.add(nameTextField);
    
    JLabel fatherNameLabel = new JLabel("Father's Name");
    inputPanel.add(fatherNameLabel);
    
    fatherNameTextField = new JTextField();
    fatherNameTextField.setHorizontalAlignment(SwingConstants.LEFT);
    inputPanel.add(fatherNameTextField);
    
    JLabel dobLabel = new JLabel("Date of Birth");
    inputPanel.add(dobLabel);
    
    dobtextField = new JTextField();
    dobtextField.setHorizontalAlignment(SwingConstants.LEFT);
    inputPanel.add(dobtextField);
    
    JLabel genderLabel = new JLabel("Gender");
    inputPanel.add(genderLabel);
    
    JComboBox<String> genderComboBox = new JComboBox<String>();
    genderComboBox.setEditable(false);
    genderComboBox.setMaximumRowCount(3);
    genderComboBox.addItem("Male");
    genderComboBox.addItem("Female");
    genderComboBox.addItem("Other");
    frmStduentManagementSystem.getContentPane().setLayout(null);
    inputPanel.add(genderComboBox);
    
    JLabel contactLabel = new JLabel("Contact");
    inputPanel.add(contactLabel);
    
    contactTextField = new JTextField();
    contactTextField.setHorizontalAlignment(SwingConstants.LEFT);
    inputPanel.add(contactTextField);
    
    JLabel emailLabel = new JLabel("E-mail");
    inputPanel.add(emailLabel);
    
    emailTextField = new JTextField();
    emailTextField.setHorizontalAlignment(SwingConstants.LEFT);
    inputPanel.add(emailTextField);
    
    JLabel sectionLabel = new JLabel("Section");
    inputPanel.add(sectionLabel);
    
    sectionTextField = new JTextField();
    sectionTextField.setHorizontalAlignment(SwingConstants.LEFT);
    inputPanel.add(sectionTextField);
    
    JLabel addressLabel = new JLabel("Address");
    inputPanel.add(addressLabel);
    
    JTextArea addressTextArea = new JTextArea();
    inputPanel.add(addressTextArea);
    frmStduentManagementSystem.getContentPane().add(inputPanel);
    
//		Adding the insert button action listener to insert the student on click
    JButton insertButton = new JButton("Insert");
    inputPanel.add(insertButton);
    insertButton.addActionListener(new ActionListener() {
      
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          Database.insertStudent(idTextField.getText(),
                     nameTextField.getText(),
                     fatherNameTextField.getText(),
                     dobtextField.getText(),
                     genderComboBox.getSelectedItem().toString(),
                     contactTextField.getText(),
                     sectionTextField.getText(),
                     emailTextField.getText(),
                     addressTextArea.getText());
          Database.fetchAllData(model);
          JOptionPane.showMessageDialog(inputPanel,"Student successfully inserted","Inserted", JOptionPane.INFORMATION_MESSAGE);
          
        } catch (Exception e2) {
          JOptionPane.showMessageDialog(inputPanel,"Student ID Already exists","ERROR", JOptionPane.ERROR_MESSAGE);
          e2.printStackTrace();
        }
      }
    });
    
//		Adding the update button action listener to update the student data on click
    JButton updateButton = new JButton("Update");
    inputPanel.add(updateButton);
    updateButton.addActionListener(new ActionListener() {
      
      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        try {
          Database.updateStudent(idTextField.getText(),
               nameTextField.getText(),
               fatherNameTextField.getText(),
               dobtextField.getText(),
               genderComboBox.getSelectedItem().toString(),
               contactTextField.getText(),
               emailTextField.getText(),
               sectionTextField.getText(), addressTextArea.getText());
          Database.fetchAllData(model);
          JOptionPane.showMessageDialog(inputPanel,"Student successfully Updated","Updated", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e2) {
          
          e2.printStackTrace();
        }
      }
    });
    
    JPanel outputPanel = new JPanel();
    outputPanel.setBounds(400, 0, 700, 565);
    outputPanel.setBackground(new Color(51, 209, 122));
    frmStduentManagementSystem.getContentPane().add(outputPanel);
    
    table = new JTable(model);
    table.setVisible(true);
    outputPanel.setLayout(null);
    
    
    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setBounds(0, 143, 700, 421);
    scrollPane.setViewportBorder(null);
    outputPanel.add(scrollPane);
    
    JButton showAllButton = new JButton("Show All");
    showAllButton.setBounds(12, 106, 117, 25);
    outputPanel.add(showAllButton);
    
//		Adding the delete button action listener to delete the student on click
    JButton deleteButton = new JButton("Delete");
    deleteButton.setBounds(12, 12, 117, 25);
    outputPanel.add(deleteButton);
    deleteButton.addActionListener(new ActionListener() {
      
      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        try {
          Database.deleteStudent(deletetextfield.getText());
          Database.fetchAllData(model);
          JOptionPane.showMessageDialog(outputPanel,"Student successfully deleted","Deleted", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e2) {
          // TODO: handle exception
          e2.printStackTrace();
        }
      }
    });
    
    deletetextfield = new JTextField();
    deletetextfield.setBounds(154, 12, 114, 25);
    outputPanel.add(deletetextfield);
    deletetextfield.setColumns(10);
    
//		Adding the search button action listener to search the student on click
    JButton searchButton = new JButton("Search");
    searchButton.setBounds(12, 62, 117, 25);
    outputPanel.add(searchButton);
    searchButton.addActionListener(new ActionListener() {
      
      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
          try {
            Database.searchStudents(model,searchTextField.getText() );
            JOptionPane.showMessageDialog(outputPanel,"NOT Found","ERROR", JOptionPane.ERROR_MESSAGE);
          } catch (Exception e2) {
            // TODO: handle exception
          }
      }
    });
    
    searchTextField = new JTextField();
    searchTextField.setColumns(10);
    searchTextField.setBounds(154, 62, 114, 25);
    outputPanel.add(searchTextField);
    
    JLabel label = new JLabel("<- Enter ID to delete");
    label.setBounds(286, 12, 156, 25);
    outputPanel.add(label);
    
//		Adding the show all button action listener to show all the students on click
    JLabel label_1 = new JLabel("<- Enter Name to Search");
    label_1.setBounds(286, 62, 177, 25);
    outputPanel.add(label_1);
    showAllButton.addActionListener(new ActionListener() {
      
      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        try {
          Database.fetchAllData(model);
        } catch (Exception e2) {
          // TODO: handle exception
          e2.printStackTrace();
        }
      }
    });
    
    
    
  }
}