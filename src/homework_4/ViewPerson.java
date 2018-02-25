package homework_4;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import homework_4.Controller;

public class ViewPerson extends JFrame {
	

	private static final long serialVersionUID = 111L;
	GridBagConstraints c = new GridBagConstraints();
	private JButton but1;
	private JButton but2;
	private JButton but3;
	private JButton but4;
	private JTable table;
    private JTextField data1;
    private JTextField data2;
    private JTextField data3;
    private JTextField data4;
    private JTextField data6;
    
    Controller controller;
    
	public ViewPerson(Controller controller) {
		//controller = new Controller();
		this.controller = controller;
		
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLayout(new GridBagLayout());
	    setTitle("Person");
	    
	    table = new JTable();
	    
	    c.gridx = 0;
		c.gridy = 0;
		//table = Controller.viewAllClients();
		getContentPane().add(new JScrollPane(table), c);
		
	    c.gridx = 1;
		c.gridy = 1;
	    but1 = new JButton("Add Person");
	    getContentPane().add(but1, c);
	    
	    c.gridx = 2;
		c.gridy = 1;
	    data1 = new JTextField (10);
	    data1.setText("Id");
	    getContentPane().add(data1, c);
	    
	    c.gridx = 3;
		c.gridy = 1;
	    data2 = new JTextField (10);
	    data2.setText("Name");
	    getContentPane().add(data2, c);
	    
	    c.gridx = 4;
		c.gridy = 1;
	    data6 = new JTextField (10);
	    data6.setText("Phone");
	    getContentPane().add(data6, c);
	    
	    c.gridx = 1;
		c.gridy = 2;
	    but2 = new JButton("Show Person");
	    getContentPane().add(but2, c);
	    
	    c.gridx = 2;
		c.gridy = 2;
	    data3 = new JTextField (10);
	    data3.setText("Id");
	    getContentPane().add(data3, c);
	    
	    c.gridx = 1;
		c.gridy = 3;
	    but3 = new JButton("Delete");
	    getContentPane().add(but3, c);
	    
	    c.gridx = 2;
		c.gridy = 3;
	    data4 = new JTextField (10);
	    data4.setText("Id");
	    getContentPane().add(data4, c);
	    
	    c.gridx = 1;
		c.gridy = 4;
	    but4 = new JButton("View All");
	    getContentPane().add(but4, c);
	    
	    table.setModel(controller.viewAllPersons().getModel());
	    
	    but1.addActionListener( new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e)
	        {      	
	        	String name = data2.getText();
	        	String id = data1.getText();
	        	String phone = data6.getText();

	        	int n = Integer.parseInt(id);
	        	controller.addPerson(n, name, phone);
	        	table.setModel(controller.viewAllPersons().getModel());
	        }
	    });
	    
	    
	    but2.addActionListener( new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e)
	        {      	
	        	String id = data3.getText();
	        	int n = Integer.parseInt(id);
	        	
	        	table.setModel(controller.editPerson(n).getModel());
	        }
	    });
	    
	    but3.addActionListener( new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e)
	        {   
	        	/*   
	        	String id = data4.getText();
	        	int n = Integer.parseInt(id);
	        	controller.removePerson(n);
	        	table.setModel(controller.viewAllPersons().getModel());
	        	*/	
	        	 // i = the index of the selected row
                int i = table.getSelectedRow();
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                if(i >= 0){
                    // remove a row from jtable
                	int id = Integer.parseInt(model.getValueAt(i, 0).toString());
                	controller.removePerson(id);
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
	        	table.setModel(controller.viewAllPersons().getModel());
	        }
	    });
	    
	    Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
	        public void run() {
	            controller.serialize();
	        }
	    })); 
	    
	    
	    table.addMouseListener(new MouseAdapter(){
	        
	        @Override
	        public void mouseClicked(MouseEvent e){
	            
	            // i = the index of the selected row
	            int i = table.getSelectedRow();
	            DefaultTableModel model = (DefaultTableModel) table.getModel();
	            
	            data1.setText(model.getValueAt(i, 0).toString());
	            data2.setText(model.getValueAt(i, 1).toString());
	            data6.setText(model.getValueAt(i, 2).toString());
	            data3.setText(model.getValueAt(i, 0).toString());
	            data4.setText(model.getValueAt(i, 0).toString());
	        }
	        });
	    
	    
	    pack(); 
	  }
}
