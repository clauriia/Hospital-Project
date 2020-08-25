/*
 * This code creates an employee Login for the hospital.
 * the Employee logins in with their employee ID and the can access the database
 */
package hospitallogin;



import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HospitalLogin extends JFrame {
	private JLabel useridL;
	private JTextField useridT;
	private JButton submit;
	private String[] userPass;
	private boolean hasHitButton;
	private submitButtonHandler submitHandler;
		public HospitalLogin() {
			setTitle("Employee Login");
			userPass = new String[2];
			hasHitButton = true;
			//labels
			useridL = new JLabel("User ID: ", SwingConstants.CENTER);
			//passwordL = new JLabel("Password: ", SwingConstants.CENTER);
			
			
			//textFields
			useridT = new JTextField(10);
			//passwordT = new JTextField(10);
			
			
			//button
			submit = new JButton("Login");
			submitHandler = new submitButtonHandler();
			submit.addActionListener(submitHandler);
			
			
			
			
			
			//container
			Container pane = getContentPane();
			pane.setLayout(new GridLayout(3,2));
			pane.add(useridL);
			pane.add(useridT);
			//pane.add(passwordL);
			//pane.add(passwordT);
			pane.add(submit);
			
			
			//frame
			setSize(300, 150);
			setVisible(true);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			
		}
		
		//button handler
		private class submitButtonHandler implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				
				userPass[0] = useridT.getText();
				//userPass[1] = passwordT.getText();
				hasHitButton = false;
				
			}
			
		}
		public boolean getHasHitButton() {
			return hasHitButton;
		}
		public String[] getUserPass() {
			boolean flag = getHasHitButton();
		
			while(flag) {
				flag = getHasHitButton();
				System.out.print("");
			}
			return userPass;
			
		
		}
		
		
		public static void main(String[] args) {
			HospitalLogin l = new HospitalLogin();
			String test[] = l.getUserPass();
			l.dispose();
			
		}
		
}
