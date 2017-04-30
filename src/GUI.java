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
//import javax.swing.JInternalFrame;
//import javax.swing.JSplitPane;
//import javax.swing.JTabbedPane;
import javax.swing.JCheckBox;

public class GUI extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Class fields
	private JTextField txtInstructions;
	
	private static int year;
	private static int month;
	private static String monthStr;
	private static int day;
	
	private JComboBox comboBox_year, comboBox_month, comboBox_day;
	private JTextField textWindMedian;
	private JTextField textTempMedian;
	private JTextField textPressureMedian;
	private JTextField textWindMean;
	private JTextField textTempMean;
	private JTextField textPressureMean;
		
	private JCheckBox chckbxMean, chckbxMedian, chckbxAll;
	
	//Constructor
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
		setTxtMedianFields();
		setTxtMeanFields();
		
		//Create labels
		setLables();
		
		this.setVisible(true); //Set window visible
	}
	
	
	//Create EXECUTE button
	private void setExeBtn(){
		
		JButton btnNewButton = new JButton("Execute");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Execute action:
				
				if(month != 0 && day != 0){
					
					Calculator myCalc = new Calculator();			
					myCalc.setYear(year);
					myCalc.setMonth(month);
					myCalc.setDay(day);
					
					myCalc.calcData(chckbxMean.isSelected(), chckbxMedian.isSelected());
					
					if(chckbxMean.isSelected()){
						//****
						textWindMean.setText(String.format("%.2f", myCalc.getMeanWindSpeed()));
						textTempMean.setText(String.format("%.2f", myCalc.getMeanAirTemperature()));
						textPressureMean.setText(String.format("%.2f", myCalc.getMeanBarometricPressure()));		
					}
					
					if(chckbxMedian.isSelected()){
						
						textWindMedian.setText(Double.toString(myCalc.getMedianWindSpeed()));
						textTempMedian.setText(Double.toString(myCalc.getMedianAirTemperature()));
						textPressureMedian.setText(Double.toString(myCalc.getMedianBarometricPressure()));
					}
									
				}
				else{
					
					//Display error dialogue
					JOptionPane.showMessageDialog(null,
							String.format("Please select month and day first:\n %d %d %d", year, month, day),
						    "Program error",
						    JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
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
	
	
	//Create all labels
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
	private void setTxtMedianFields(){
		
		textWindMedian = new JTextField();
		textWindMedian.setEnabled(false);
		textWindMedian.setBounds(324, 129, 86, 20);
		//textWindMedian.setText("");
		getContentPane().add(textWindMedian);
		textWindMedian.setColumns(10);
		
		textTempMedian = new JTextField();
		//textTempMedian.setText("");
		textTempMedian.setEnabled(false);
		textTempMedian.setColumns(10);
		textTempMedian.setBounds(324, 160, 86, 20);
		getContentPane().add(textTempMedian);
		
		textPressureMedian = new JTextField();
		//textPressureMedian.setText("");
		textPressureMedian.setEnabled(false);
		textPressureMedian.setColumns(10);
		textPressureMedian.setBounds(324, 191, 86, 20);
		getContentPane().add(textPressureMedian);
	}
	
	private void unsetTxtMedianFields(){
		
		textWindMedian.setText("n/a");
		textTempMedian.setText("n/a");
		textPressureMedian.setText("n/a");
	}	
	
	private void setTxtMeanFields(){
		
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
	
	private void unsetTxtMeanFields(){

		textWindMean.setText("n/a");
		textTempMean.setText("n/a");
		textPressureMean.setText("n/a");
	}
	
	//Create check-boxes
	private void setCheckBoxes(){
		
		chckbxMean = new JCheckBox("Mean");
		chckbxMean.setSelected(true);
		chckbxMean.addActionListener(new AllBxListener());
		chckbxMean.setBounds(24, 128, 68, 23);
		getContentPane().add(chckbxMean);
		
		chckbxMedian = new JCheckBox("Median");
		chckbxMedian.setSelected(true);
		chckbxMedian.addActionListener(new AllBxListener());
		chckbxMedian.setBounds(24, 159, 77, 23);
		getContentPane().add(chckbxMedian);
		
		chckbxAll = new JCheckBox("Select all");
		chckbxAll.setSelected(true);
		chckbxAll.addActionListener(new AllBxListener());
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

	
	private class AllBxListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			//chckbxMean, chckbxMedian, chckbxAll
			
			String source = e.getActionCommand();
			
			if(source == "Select all"){
				//JOptionPane.showMessageDialog(null, source); //Debug only
				if(chckbxAll.isSelected() == true){
				
					textWindMedian.setText("");
					textTempMedian.setText("");
					textPressureMedian.setText("");
					
					textWindMean.setText("");
					textPressureMean.setText("");
					textTempMean.setText("");
					
					chckbxMean.setSelected(true);
					chckbxMedian.setSelected(true);
					//setTxtMedianFields();
					//setTxtMeanFields();
				}
				else {
					unsetTxtMeanFields();
					unsetTxtMedianFields();
					chckbxMean.setSelected(false);
					chckbxMedian.setSelected(false);
				}
			}
			
			if(source == "Mean"){
				
				if(chckbxMedian.isSelected() == true && chckbxMean.isSelected() == true){
					
					textWindMedian.setText("");
					textTempMedian.setText("");
					textPressureMedian.setText("");
					
					textWindMean.setText("");
					textPressureMean.setText("");
					textTempMean.setText("");
					
					chckbxAll.setSelected(true);
				}
				else{
					
					if(chckbxMean.isSelected() == false){
						unsetTxtMeanFields();
					}
					else{
						textWindMean.setText("");
						textTempMean.setText("");
						textPressureMean.setText("");
					}
					
					chckbxAll.setSelected(false);
				}
				//JOptionPane.showMessageDialog(null, source); //Debug only
				
			}
			
			if(source == "Median"){
				
				if(chckbxMedian.isSelected() == true && chckbxMean.isSelected() == true){
					
					textWindMedian.setText("");
					textTempMedian.setText("");
					textPressureMedian.setText("");
					
					textWindMean.setText("");
					textPressureMean.setText("");
					textTempMean.setText("");
					
					chckbxAll.setSelected(true);
				}
				else{
					chckbxAll.setSelected(false);
					
					if(chckbxMedian.isSelected() == false){
						unsetTxtMedianFields();
					}
					else{
						textWindMedian.setText("");
						textTempMedian.setText("");
						textPressureMedian.setText("");
					}
				}
				//JOptionPane.showMessageDialog(null, source); //Debug only
				
			}
			
		}
		//AllBxListener
	}
	
	//Year combo action listener
	private class YearListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent arg0) {
			// TODO Auto-generated method stub
			
			//int year = arg0.getID();
			int index = comboBox_year.getSelectedIndex();		
			//JOptionPane.showMessageDialog(null, "year: " + index); //Debug only
			
			switch(index){	
				case (0):
					year = 2012;
					break;
				case (1):
					year = 2013;
					break;
				case (2):
					year = 2014;
					break;
				case (3):
					year = 2015;
					break;
			}
			
			//JOptionPane.showMessageDialog(null, "year: " + year); //Debug only			
			if(comboBox_month.isEnabled() == false){
				
				comboBox_month.setEnabled(true);
			}
			
			txtInstructions.setText("Please select a month");
		}
		
		//ActionListener
	}
	
	
	//Set number of days according to chosen month
	private String[] setNumDays(String month, int year){
		
		int i = 0;
		
		switch(month){
			case("January"):
				i = 31;
				break;
			case("February"):
				if(year == 2012){			
					i = 29;
				}
				else {
					i = 28;
				}
				break;
			case("March"):
				i = 31;
				break;
			case("April"):
				i = 30;
				break;
			case("May"):
				i = 31;
				break;
			case("June"):
				i = 30;
				break;
			case("July"):
				i = 31;
				break;
			case("August"):
				i = 31;
				break;
			case("September"):
				i = 30;
				break;
			case("October"):
				i = 31;
				break;
			case("November"):
				i = 30;
				break;
			case("December"):
				i = 31;
				break;		
		}
		
		String[] daysArray = new String[i];	
		
		for(int index = 0; index < i; index++){
			
			daysArray[index] = Integer.toString(index + 1);
		}
		
		return daysArray;		
	}
	
	
	//Set month
	private void setMonth(int index){
		
		switch(index){
		case(0):
			month = 1;
			monthStr = "January";
			break;
		case(1):
			month = 2;
			monthStr = "February";
			break;
		case(2):
			month = 3;
			monthStr = "March";
			break;
		case(3):
			month = 4;
			monthStr = "April";
			break;
		case(4):
			month = 5;
			monthStr = "May";
			break;
		case(5):
			month = 6;
			monthStr = "June";
			break;
		case(6):
			month = 7;
			monthStr = "July";
			break;
		case(7):
			month = 8;
			monthStr = "August";
			break;
		case(8):
			month = 9;
			monthStr = "September";
			break;
		case(9):
			month = 10;
			monthStr = "October";
			break;
		case(10):
			month = 11;
			monthStr = "November";
			break;
		case(11):
			month = 12;
			monthStr = "December";
			break;		
		}
		
	}
	
	
	//Month combo event handler
	private class MonthListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent arg0) {
			// TODO Auto-generated method stub
			
			int index = comboBox_month.getSelectedIndex();
			//JOptionPane.showMessageDialog(null, "month: " + month); //Debug only
					
			if(comboBox_day.isEnabled() == false){
				
				comboBox_day.setEnabled(true);
			}
			
			 setMonth(index);
			 //JOptionPane.showMessageDialog(null, "month: " + month + " " + monthStr); //Debug only
			 
			//Set days combo
			String[] days = setNumDays(monthStr, year);		
			comboBox_day.setModel(new DefaultComboBoxModel(days));
			//new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
			txtInstructions.setText("Please select a day");
		}
		
		//MonthListener
	}
	
	private class DayListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			
			day = comboBox_day.getSelectedIndex();			
		}
		
		//DayListener
	}
	
	//GUI
}
