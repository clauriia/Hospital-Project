package querysearch;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
//import com.mysql.*;
//import com.mysql.cj.jdbc.result.ResultSetMetaData;
import java.util.Arrays;
import javax.swing.JTable;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;

public class QuerySearch {

	private JFrame frame;
	/**
	 * @wbp.nonvisual location=1030,631
	 */
	private final JTextPane textPane = new JTextPane();
	private static Statement stmt;
	//private JTable table;
	private String data[][];
	private String columns[];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuerySearch window = new QuerySearch();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		 // TODO code application logic here
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
        
        final String ID = "root";
        final String PW = "kerry2005";
        final String SERVER = "jdbc:mysql://localhost:3306/?user="+ID;
        
        try{
            Connection con = DriverManager.getConnection(SERVER, ID, PW);
            stmt = con.createStatement();
         
           
          
           stmt.executeQuery("use hospital");
			
       
        
        }catch(SQLException e){
            System.err.println(e);
        }

	}
/*
	/**
	 * Create the application.
	 */
	public QuerySearch() {
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		
		frame = new JFrame();
		frame.getContentPane().addInputMethodListener(new InputMethodListener() {
			public void caretPositionChanged(InputMethodEvent event) {
			}
			public void inputMethodTextChanged(InputMethodEvent event) {
			}
		});
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Admitted-Patient", "Discharged-Patient", "Doctor_In_Charge_of_Patient", "Employee Dept Name", "Guardian Name", "Patient Name"}));
		comboBox.setBounds(151, 6, 148, 35);
		frame.getContentPane().add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Select Query\n");
		lblNewLabel.setLabelFor(comboBox);
		lblNewLabel.setBounds(38, 14, 101, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Excute Query");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query = (String) comboBox.getSelectedItem();
				String excuteStatement = getQuery(query);
				
				System.out.println(excuteStatement);
				
				ResultSet rs;
				try {
					 rs = stmt.executeQuery(excuteStatement);
					   ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
					   //System.out.println(excuteStatement);
					   int cn = rsmd.getColumnCount();
					   columns = new String[cn];
					   
					  
					   //get columns
					   for(int i = 1; i <= cn; i++) {
						   columns[i - 1] = rsmd.getColumnName(i);
						  
					   }
					   //getting row number
					   int row = 0;
					   while(rs.next()) {
						   for(int i = 0; i < columns.length; i++) {
							   //System.out.print(rs.getString(columns[i]) + " ");
							   
						   }
						   row++;
						   System.out.println();
					   }
					  rs = stmt.executeQuery(excuteStatement);
					 //getting data
					  //i and j are in the wrong spots
					   int j = 0;
					   data = new String[row][cn];
					   while(rs.next()) {
						   for(int i = 0; i < columns.length; i++) {
							   data[j][i] = rs.getString(columns[i]);
							   //System.out.println(" i: " + (i + 1) + " j: " + (j + 1) + " row: " + row);
							   //System.out.print(rs.getString(columns[i]) + " ");
						   }
						   j++;
						   System.out.println();
					   }
					   for(int i = 0; i < data.length; i++) {
						   System.out.println(Arrays.toString(data[i]));
					   }
					 JTable  table = new JTable(data, columns);
						table.setBounds(74, 53, 294, 198);
						
						frame.getContentPane().add(table);
						
					   
					   
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
				
				
				
				
			}
		});
		btnNewButton.setBounds(313, 9, 117, 29);
		frame.getContentPane().add(btnNewButton);
		
		
		
		
	}
	private String getQuery(String q) {
		switch(q) {
			case "Admitted-Patient":
				return "SELECT *\n" + 
						"FROM Patient\n" + 
						"WHERE patient_status = \"Admitted\";";
			case "Discharged-Patient":
				return "SELECT Discharged-Patient\n" + 
						"FROM Patient\n" + 
						"WHERE patient_status=\"Discharged\";";
			case "Doctor_In_Charge_of_Patient":
				return "SELECT P_FName, P_LName, Fname, Lname, EmployeeId\n" + 
						"FROM Employee e, patient p\n" + 
						"WHERE p.doctor_inchargeSSN = ssn;";
			case "Employee Dept Name":
				return "SELECT Dept_Name, deptHead_SSN, Fname, Lname\n" + 
						"FROM Department d, Employee e\n" + 
						"WHERE d.DeptId = e.deptID AND deptHead_SSN=ssn";
			case "Guardian Name":
				return "SELECT guardian_Fname, guardian_Lname, guardianPhone\n" + 
						"FROM  Guardian g, Patient p\n" + 
						"WHERE g.patient_ssn = p.P_ssn;";
			case "Patient Name":
				return "SELECT CONCAT(P_Fname, \",\", P_Lname) AS \"Name\"\n" + 
						"FROM Patient;";
						
			
		}
		return "error";
	}
 //
}
