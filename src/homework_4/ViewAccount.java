package homework_4;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ViewAccount extends JFrame {
	

	private static final long serialVersionUID = 111L;
	GridBagConstraints c = new GridBagConstraints();
	private JButton but1;
	private JButton but2;
	private JButton but3;
	private JButton but4;
	private JButton but5;
	private JButton but6;
	private JTable table;
    private JTextField data1;
    private JTextField data2;
    private JTextField data3;
    private JTextField data4;
    private JTextField data5;
    private JTextField data6;
    private JTextField data7;
    private JTextField data8;
    private JTextField data9;
    private JTextField data10;
    private JTextField data11;
    private JTextField data12;
    private JTextField data13;
    private JTextField data14;
    private JTextField data15;
    private JLabel label;
    
    Controller controller;
    
	public ViewAccount(Controller controller) {
		//controller = new Controller();
		this.controller = controller;
		  
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLayout(new GridBagLayout());
	    setTitle("Account");
	    
	    table = new JTable();
	    
	    c.gridx = 0;
		c.gridy = 0;
		//table = Controller.viewAllClients();
		getContentPane().add(new JScrollPane(table), c);
		
		c.gridx = 1;
		c.gridy = 0;
		label = new JLabel("Observer: ");
		//getContentPane().add(label, c);
		    
	    c.gridx = 1;
		c.gridy = 1;
	    but1 = new JButton("Add Account");
	    getContentPane().add(but1, c);
	    
	    c.gridx = 2;
		c.gridy = 1;
	    data1 = new JTextField (10);
	    data1.setText("Id");
	    getContentPane().add(data1, c);
	    
	    c.gridx = 3;
		c.gridy = 1;
	    data2 = new JTextField (10);
	    data2.setText("Type: SP/SA");
	    getContentPane().add(data2, c);
	    
	    c.gridx = 4;
		c.gridy = 1;
	    data6 = new JTextField (10);
	    data6.setText("No");
	    getContentPane().add(data6, c);
	    
	    c.gridx = 5;
		c.gridy = 1;
	    data7 = new JTextField (10);
	    data7.setText("Balance");
	    getContentPane().add(data7, c);
	    
	    c.gridx = 6;
		c.gridy = 1;
	    data8 = new JTextField (10);
	    data8.setText("0");
	    getContentPane().add(data8, c);
 
	    c.gridx = 1;
		c.gridy = 2;
	    but2 = new JButton("Show Account");
	    getContentPane().add(but2, c);
	    
	    c.gridx = 2;
		c.gridy = 2;
	    data3 = new JTextField (10);
	    data3.setText("Id");
	    getContentPane().add(data3, c);
	    
	    c.gridx = 3;
		c.gridy = 2;
	    data9 = new JTextField (10);
	    data9.setText("No");
	    getContentPane().add(data9, c);
	    
	    c.gridx = 1;
		c.gridy = 3;
	    but3 = new JButton("Delete");
	    getContentPane().add(but3, c);
	    
	    c.gridx = 2;
		c.gridy = 3;
	    data4 = new JTextField (10);
	    data4.setText("Id");
	    getContentPane().add(data4, c);
	    
	    c.gridx = 3;
		c.gridy = 3;
	    data5 = new JTextField (10);
	    data5.setText("No");
	    getContentPane().add(data5, c);
	    
	    c.gridx = 1;
		c.gridy = 4;
	    but4 = new JButton("View All");
	    getContentPane().add(but4, c);
	    
	    c.gridx = 1;
		c.gridy = 5;
	    but5 = new JButton("Deposit");
	    getContentPane().add(but5, c);
	    
	    c.gridx = 2;
		c.gridy = 5;
		data10 = new JTextField (10);
	    data10.setText("Id");
	    getContentPane().add(data10, c);
	    
	    c.gridx = 3;
		c.gridy = 5;
		data11 = new JTextField (10);
	    data11.setText("No");
	    getContentPane().add(data11, c);
	    
	    c.gridx = 4;
		c.gridy = 5;
		data14 = new JTextField (10);
	    data14.setText("Amount");
	    getContentPane().add(data14, c);
	    
	    c.gridx = 1;
		c.gridy = 6;
	    but6 = new JButton("Withdraw");
	    getContentPane().add(but6, c);
	    
	    c.gridx = 2;
		c.gridy = 6;
		data12 = new JTextField (10);
		data12.setText("Id");
	    getContentPane().add(data12, c);
	    
	    c.gridx = 3;
		c.gridy = 6;
		data13 = new JTextField (10);
		data13.setText("No");
	    getContentPane().add(data13, c);
	    
	    c.gridx = 4;
		c.gridy = 6;
		data15 = new JTextField (10);
		data15.setText("Amount");
	    getContentPane().add(data15, c);

	    table.setModel(controller.viewAllAccounts().getModel());
	    
	    but1.addActionListener( new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e)
	        {      	
	        	int id = Integer.parseInt(data1.getText());
	        	String type = data2.getText();
	        	int accountNo = Integer.parseInt(data6.getText());
	        	double balance = Double.parseDouble(data7.getText());
	        	double interestRate = Double.parseDouble(data8.getText());
	   
	        	
	        	controller.addAccount(id, type, accountNo, balance, interestRate);
	        	table.setModel(controller.viewAllAccounts().getModel());
	        }
	    });
	    	    
	    but2.addActionListener( new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e)
	        {      	
	        	int accountNo = Integer.parseInt(data9.getText());
	        	int id = Integer.parseInt(data3.getText());
	        	
	        	table.setModel(controller.editAccount(id, accountNo).getModel());
	        }
	    });
	    
	    but3.addActionListener( new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e)
	        {   
	        	 // i = the index of the selected row
                int i = table.getSelectedRow();
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                if(i >= 0){
                    // remove a row from jtable
                	int id = Integer.parseInt(model.getValueAt(i, 0).toString());
                	int accountNo = Integer.parseInt(model.getValueAt(i, 1).toString());
                	controller.removeAccount(id, accountNo);
                    model.removeRow(i);
                }
                else{
                    System.out.println("Delete Error");
                }
	        }
	    });
	    
	    but4.addActionListener( new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e)
	        {      	
	        	table.setModel(controller.viewAllAccounts().getModel());
	        }
	    });
	    
	    but5.addActionListener( new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e)
	        {      	
	        	int id = Integer.parseInt(data10.getText());
	        	int accountNo = Integer.parseInt(data11.getText());
	        	double amount = Double.parseDouble(data14.getText());
	        	controller.deposit(id, accountNo, amount);
	        	//label.setText("Observer: " + controller.getDataObserver(id));
	        	table.setModel(controller.viewAllAccounts().getModel());
	        }
	    });
	    
	    but6.addActionListener( new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e)
	        {      	
	        	int id = Integer.parseInt(data12.getText());
	        	int accountNo = Integer.parseInt(data13.getText());
	        	double amount = Double.parseDouble(data15.getText());
	        	controller.withdraw(id, accountNo, amount);
	        	//label.setText("Observer: " + controller.getDataObserver(id));
	        	table.setModel(controller.viewAllAccounts().getModel());
	        }
	    });

	    
	    
	    Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
	        public void run() {
	            controller.serialize();
	        }
	    })); 
	    
	    // get selected row data From table to textfields 
	    table.addMouseListener(new MouseAdapter(){
	        
	        @Override
	        public void mouseClicked(MouseEvent e){
	            
	            // i = the index of the selected row
	            int i = table.getSelectedRow();
	            DefaultTableModel model = (DefaultTableModel) table.getModel();
	            
	            data1.setText(model.getValueAt(i, 0).toString());
	            data10.setText(model.getValueAt(i, 0).toString());
	            data12.setText(model.getValueAt(i, 0).toString());
	            //data6.setText(model.getValueAt(i, 1).toString());
	            data5.setText(model.getValueAt(i, 1).toString());
	            data9.setText(model.getValueAt(i, 1).toString());
	            data11.setText(model.getValueAt(i, 1).toString());
	            data13.setText(model.getValueAt(i, 1).toString());

	            //data7.setText(model.getValueAt(i, 2).toString());
	           // data8.setText(model.getValueAt(i, 3) != null ? model.getValueAt(i, 4).toString() : "0");
	            data3.setText(model.getValueAt(i, 0).toString());
	            data4.setText(model.getValueAt(i, 0).toString());
	            
	            int row = table.rowAtPoint(e.getPoint());
	            int col = table.columnAtPoint(e.getPoint());
	            if (row >= 0 && col == 5) {
	                // System.out.println(model.getValueAt(row, col));
	            	String val = model.getValueAt(row, col).toString();
	            	System.out.println(val.equals("NO"));
	            	if (val.equals("NO")) {
	            		int id = Integer.parseInt(model.getValueAt(row, 0).toString());
	            		int accountNo = Integer.parseInt(model.getValueAt(row, 1).toString());
	            		controller.subscribe(id, accountNo);
	            		System.out.println("Subscribed");
	            		table.getModel().setValueAt("YES", row, col);
	            	} else {
	            		int id = Integer.parseInt(model.getValueAt(row, 0).toString());
	            		int accountNo = Integer.parseInt(model.getValueAt(row, 1).toString());
	            		controller.unsubscribe(id, accountNo);
	            		table.getModel().setValueAt("NO", row, col);
	            	}
	            }
	        }
	        });
	    
	    
	    pack(); 
	  }
}
