import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JCheckBox;

public class GUI extends JFrame{
	private JTextField txtInstructions;
	
	private static int year;
	private static int month;
	private static int day;
	private JComboBox comboBox_year, comboBox_month, comboBox_day;
	private JTextField textWindMedian;
	private JTextField textTempMedian;
	private JTextField textPressureMedian;
	private JTextField textWindMean;
	private JTextField textTempMean;
	private JTextField textPressureMean;
	
	//Cpnstructor
	public GUI() {
		setResizable(false);
		this.setSize(450,300); //Window size
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //X button
		setTitle("CodeClinic: Lake Pend Oreille Weather");
		getContentPane().setLayout(null);
		
		//Execute button
		setExeBtn();

		//Close button
		setCloseBtn();
		
		//Create instructions area
		setInstructions();
		
		//Year combo
		setYearCombo();
		
		//Month combo
		setMonthCombo();
		
		//Day combo
		setDayCombo();
				
		//Create check boxes
		setCheckBoxes();
		
		//Create text fields
		setTxtFields();
		
		//Create lables
		setLables();
		
		this.setVisible(true); //Set window visible
	}
	
	
	//Create EXECUTE button
	private void setExeBtn(){
		
		JButton btnNewButton = new JButton("Execute");
		btnNewButton.setBounds(222, 227, 86, 23);
		getContentPane().add(btnNewButton);
	}
	
	
	//Create CLOSE button
	private void setCloseBtn(){
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnClose.setBounds(324, 227, 86, 23);
		getContentPane().add(btnClose);
	}
	
	
	//Create instructions area
	private void setInstructions(){
		
		txtInstructions = new JTextField();
		txtInstructions.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtInstructions.setForeground(Color.RED);
		txtInstructions.setEditable(false);
		txtInstructions.setText("Please select a year");
		txtInstructions.setBounds(119, 11, 290, 20);
		getContentPane().add(txtInstructions);
		txtInstructions.setColumns(10);
		
		JLabel lblInstructions = new JLabel("Instructions:");
		lblInstructions.setBounds(24, 14, 85, 14);
		getContentPane().add(lblInstructions);
	}
	
	
	//Create all lables
	private void setLables(){
				
		JLabel lblPleaseSelectA = new JLabel("Select a year:");
		lblPleaseSelectA.setBounds(24, 39, 107, 26);
		getContentPane().add(lblPleaseSelectA);
		
		JLabel lblPleaseSelectA_1 = new JLabel("Select a month:");
		lblPleaseSelectA_1.setBounds(164, 39, 117, 26);
		getContentPane().add(lblPleaseSelectA_1);
		
		JLabel lblPleaseSelectA_2 = new JLabel("Select a day:");
		lblPleaseSelectA_2.setBounds(303, 39, 107, 26);
		getContentPane().add(lblPleaseSelectA_2);
		
		JLabel lblMean = new JLabel("MEAN:");
		lblMean.setBounds(222, 99, 46, 14);
		getContentPane().add(lblMean);
		
		JLabel lblMedian = new JLabel("MEDIAN:");
		lblMedian.setBounds(323, 99, 63, 14);
		getContentPane().add(lblMedian);		
		
		JLabel lblWind_speed = new JLabel("Wind speed:");
		lblWind_speed.setBounds(135, 129, 85, 20);
		getContentPane().add(lblWind_speed);
		
		JLabel lblTempc = new JLabel("Temp (C):");
		lblTempc.setBounds(149, 160, 68, 20);
		getContentPane().add(lblTempc);
		
		JLabel lblPressure = new JLabel("Pressure:");
		lblPressure.setBounds(149, 191, 63, 20);
		getContentPane().add(lblPressure);
	}
	
	
	//Create all txt fields
	private void setTxtFields(){
		
		textWindMedian = new JTextField();
		textWindMedian.setEnabled(false);
		textWindMedian.setBounds(324, 129, 86, 20);
		getContentPane().add(textWindMedian);
		textWindMedian.setColumns(10);
		
		textTempMedian = new JTextField();
		textTempMedian.setEnabled(false);
		textTempMedian.setColumns(10);
		textTempMedian.setBounds(324, 160, 86, 20);
		getContentPane().add(textTempMedian);
		
		textPressureMedian = new JTextField();
		textPressureMedian.setEnabled(false);
		textPressureMedian.setColumns(10);
		textPressureMedian.setBounds(324, 191, 86, 20);
		getContentPane().add(textPressureMedian);
		
		textWindMean = new JTextField();
		textWindMean.setEnabled(false);
		textWindMean.setColumns(10);
		textWindMean.setBounds(222, 129, 86, 20);
		getContentPane().add(textWindMean);
		
		textTempMean = new JTextField();
		textTempMean.setEnabled(false);
		textTempMean.setColumns(10);
		textTempMean.setBounds(222, 160, 86, 20);
		getContentPane().add(textTempMean);
		
		textPressureMean = new JTextField();
		textPressureMean.setEnabled(false);
		textPressureMean.setColumns(10);
		textPressureMean.setBounds(222, 191, 86, 20);
		getContentPane().add(textPressureMean);
	}
	
	
	//Create check-boxes
	private void setCheckBoxes(){
		
		JCheckBox chckbxMean = new JCheckBox("Mean");
		chckbxMean.setBounds(24, 128, 68, 23);
		getContentPane().add(chckbxMean);
		
		JCheckBox chckbxMedian = new JCheckBox("Median");
		chckbxMedian.setBounds(24, 159, 77, 23);
		getContentPane().add(chckbxMedian);
		
		JCheckBox chckbxAll = new JCheckBox("Select all");
		chckbxAll.setBounds(24, 190, 97, 23);
		getContentPane().add(chckbxAll);
	}
	
	private void setYearCombo(){
		
		comboBox_year = new JComboBox();
		comboBox_year.addItemListener(new YearListener());
		comboBox_year.setModel(new DefaultComboBoxModel(new String[] {"2012", "2013", "2014", "2015"}));
		comboBox_year.setBounds(24, 63, 107, 20);
		getContentPane().add(comboBox_year);
	}
	
	private void setMonthCombo(){
		
		comboBox_month = new JComboBox();
		comboBox_month.addItemListener(new MonthListener());
		comboBox_month.setEnabled(false);
		comboBox_month.setModel(new DefaultComboBoxModel(new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}));
		comboBox_month.setBounds(164, 63, 107, 20);
		getContentPane().add(comboBox_month);
	}
	
	private void setDayCombo(){
		
		comboBox_day = new JComboBox();
		comboBox_day.addItemListener(new DayListener());
		comboBox_day.setEnabled(false);
		comboBox_day.setBounds(303, 63, 107, 20);
		getContentPane().add(comboBox_day);
	}
	
	public int getYear(){
		
		JOptionPane.showMessageDialog(null, this.year);
		return this.year; //Return selected year
	}
	
	public int getMonth(){
		
		JOptionPane.showMessageDialog(null, this.month);
		return this.month; //Return selected month
	}

	public int getDay(){
		JOptionPane.showMessageDialog(null, this.day);
		return this.day; //Return selected day
	}
	
	
	private class YearListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent arg0) {
			// TODO Auto-generated method stub
			
			int year = arg0.getID();

			switch(year){
			
			case (2012):
				year = 2012;
				break;
			case (2013):
				year = 2013;
				break;
			case (2014):
				year = 2014;
				break;
			case (2015):
				year = 2015;
				break;
			}
			
			if(comboBox_month.isEnabled() == false){
				
				comboBox_month.setEnabled(true);
			}
			
			txtInstructions.setText("Please select a month");
		}
		
		//ActionListener
	}
	
	
	private class MonthListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent arg0) {
			// TODO Auto-generated method stub
			
			int month = arg0.getID();
			JOptionPane.showMessageDialog(null, "month: " + month);

			
			if(comboBox_day.isEnabled() == false){
				
				comboBox_day.setEnabled(true);
			}
			
			comboBox_day.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
			txtInstructions.setText("Please select a day");
		}
		
		//MonthListener
	}
	
	private class DayListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		//DayListener
	}
}
