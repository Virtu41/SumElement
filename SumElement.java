/**
 * Adds integer inputs into arrays that can be listed and removed and find sum of all numbers, even numbers and odd numbers
 * 
 * modified 20220402
 * date 20220328
 * @filename SumElement.java
 * @author Oscar Lam
 * @version 1.0
 * @see unit 2 activity 2
 */

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SumElement extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldInput;
	private JTextField textFieldOutput;
	private JTextField textFieldCount;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	static String input;
	static int total = 10; // list size 
	static int count = 0; // Number of array elements initialized and stored 
	static int val = 0; // limit negative a thousand to a thousand
	static Integer[] num = new Integer[total+1]; //+1 is used for removing elements 

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SumElement frame = new SumElement();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SumElement() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 514, 399);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 502, 364);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblTitle = new JLabel("Integer Sums");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTitle.setBounds(198, 24, 141, 20);
		panel.add(lblTitle);

		JLabel lblInput = new JLabel("Enter an Integer:");
		lblInput.setBounds(20, 72, 95, 14);
		panel.add(lblInput);

		JLabel lblElements = new JLabel("Number of stored values:");
		lblElements.setBounds(148, 117, 157, 14);
		panel.add(lblElements);

		textFieldInput = new JTextField();
		textFieldInput.setBounds(125, 69, 106, 20);
		panel.add(textFieldInput);
		textFieldInput.setColumns(10);

		textFieldOutput = new JTextField();
		textFieldOutput.setEditable(false);
		textFieldOutput.setBounds(29, 289, 341, 20);
		panel.add(textFieldOutput);
		textFieldOutput.setColumns(10);	

		textFieldCount = new JTextField();
		textFieldCount.setText("0");
		textFieldCount.setEditable(false);
		textFieldCount.setBounds(315, 114, 61, 20);
		panel.add(textFieldCount);
		textFieldCount.setColumns(10);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					input = textFieldInput.getText();
					val = Integer.parseInt(input);
					if(count==total){
						textFieldOutput.setText("List is full");
					}
					else if(val>1000||val<-1000) {
						textFieldOutput.setText("Enter integers between -1000 and 1000");
					}
					else {
						textFieldOutput.setText("");
						num[count] = val;
						count++;
						textFieldCount.setText(count+"");
						textFieldInput.setText("");
					}
				} catch(Exception err) {
					textFieldOutput.setText("No input/integers were used");
				}	
			}
		});
		btnAdd.setBounds(260, 68, 91, 23);
		panel.add(btnAdd);

		JButton btnRemove = new JButton("Remove"); // removes array elements that have the values of input
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					textFieldOutput.setText("");
					input = textFieldInput.getText();
					val = Integer.parseInt(input);
					int numRepeat = 0;
					if(count>0) { // find total amount of the same values based on input
						for(int i = 0; i<count; i++ ) {
							if(num[i]==val) {
								numRepeat++;
							}
						}
						while(numRepeat>0) { 
							for(int i = 0; i<count; i++ ) { // finds first value
								if(num[i]==val) {
									for(int j = i; j<count;j++) { // shifts the list to the left
										num[j]=num[j+1];
										if(j>count-i) {
											break;
										}
									}
									numRepeat--;
									count--;
								}
							}

						}
						textFieldCount.setText(count+"");
						textFieldInput.setText("");
					}
					else {
						textFieldOutput.setText("Nothing to remove");
					}
				} catch(Exception err) {
					textFieldOutput.setText("No input/integers were used");
				}
			}
		});
		btnRemove.setBounds(361, 68, 91, 23);
		panel.add(btnRemove);

		JButton btnList = new JButton("List");
		btnList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldOutput.setText("");
				textArea.getText();
				textArea.setText("");
				for(int i = 0; i<count; i++) {
					if(num[i]!=null) {
						textArea.append(num[i]+"\n");
					}
				}
			}
		});
		btnList.setBounds(260, 161, 91, 23);
		panel.add(btnList);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(34, 112, 100, 166);
		panel.add(textArea);

		JButton btnSum = new JButton("Sum all");
		btnSum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int sum = 0;
				int numTotal = 0;
				for(int i = 0; i<count; i++) {
					sum+=num[i];
					numTotal++;
				}
				if(numTotal>0) {
					textFieldOutput.setText("The total of the enitites is: "+sum);
				}
				else {
					textFieldOutput.setText("No values");
				}
			}
		});
		btnSum.setBounds(148, 151, 91, 23);
		panel.add(btnSum);

		JButton btnSumE = new JButton("Sum even");
		btnSumE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int numEven =0;
				int sum = 0;
				for(int i = 0; i<count; i++) { 
					if(Math.abs(num[i])%2==0) { // absolute so remainder can be used for negative numbers
						sum+=num[i];
						numEven++;
					}
				}
				if(numEven>0) {
					textFieldOutput.setText("The total of the enitites is: "+sum);
				}
				else {
					textFieldOutput.setText("No even numbers");
				}
			}
		});
		btnSumE.setBounds(148, 185, 91, 23);
		panel.add(btnSumE);

		JButton btnSumO = new JButton("Sum odd");
		btnSumO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int numOdd = 0;
				int sum = 0;
				for(int i = 0; i<count; i++) {
					if(Math.abs(num[i])%2==1) { // absolute so remainder can be used for negative numbers
						sum+=num[i];
						numOdd++;
					}
				}
				if(numOdd>0) {
					textFieldOutput.setText("The total of the enitites is: "+sum);
				}
				else {
					textFieldOutput.setText("No odd numbers");
				}
			}
		});
		btnSumO.setBounds(148, 219, 91, 23);
		panel.add(btnSumO);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(260, 197, 91, 23);
		panel.add(btnExit);
	}
}