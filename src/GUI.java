import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;

/** 
 * Class for creating app GUI
 * 
 * @author twistezo
 *
 */

@SuppressWarnings("serial")
public class GUI extends JFrame {
	private int borderOffset = 10;
	private JButton addButton;
	private JButton editButton;
	private JButton deleteButton;
	private JButton generateExamplePeopleButton;
	private JButton deleteAllButton;
	private JButton sortAZButton;
	private JButton sortZAButton;
	private JLabel titleLabel;
	private static JFrame mainFrame;
	private static JPanel mainPanel;
	private JPanel tablePanel;
	private JPanel buttonsPanel;
	private JScrollPane scrollPane;
	private JSeparator separatorH;
	private static MyTableModel model;
	private static JTable table;
	public static final String[] COLUMN_NAMES = { "First Name", "Last Name", "E-mail", "Telephone" };
	private static int selectedRow;
    
	public GUI() {
		
		/** Main Frame - GridBagLayout */
		mainFrame = new JFrame("Address Book");
		mainPanel = new JPanel(new GridBagLayout());
		mainPanel.setBorder(BorderFactory.createEmptyBorder(borderOffset, borderOffset, borderOffset, borderOffset));	// up, left, down, right
		GridBagConstraints c = new GridBagConstraints();
		
		/** Table panel for JTable - BoxLayout */
		tablePanel = new JPanel();
		tablePanel.setLayout((new BoxLayout(tablePanel,BoxLayout.PAGE_AXIS)));
		
		/** Panel for buttons - BoxLayout */
		buttonsPanel = new JPanel();
		buttonsPanel.setLayout((new BoxLayout(buttonsPanel,BoxLayout.PAGE_AXIS)));
		
		/** Head/Heart of table */
		setModel(new MyTableModel(COLUMN_NAMES));
		setTable(new JTable(getModel()));
		getTable().setPreferredScrollableViewportSize(new Dimension(500, 500));
		scrollPane = new JScrollPane(getTable());
		
		/** Title of app */
		titleLabel = new JLabel("My Address Book");
		titleLabel.setFont(new Font(null, Font.PLAIN, 17));
		titleLabel.setForeground(Color.GRAY);
		
		/** Buttons */
		final int BUTTONS_WIDTH = 160;
		final int BUTTONS_HEIGHT = 40;
		addButton = new JButton("Add new person");
		addButton.setPreferredSize(new Dimension(BUTTONS_WIDTH, BUTTONS_HEIGHT));
		addButton.addActionListener(new addButtonListener());
	
		editButton = new JButton("Edit person");
		editButton.setPreferredSize(new Dimension(BUTTONS_WIDTH, BUTTONS_HEIGHT));
		editButton.addActionListener(new editButtonListener());
		
		deleteButton = new JButton("Delete person");
		deleteButton.setPreferredSize(new Dimension(BUTTONS_WIDTH, BUTTONS_HEIGHT));
		deleteButton.addActionListener(new deleteButtonListener());
		
		generateExamplePeopleButton = new JButton("! Gen. Example People");
		generateExamplePeopleButton.setPreferredSize(new Dimension(BUTTONS_WIDTH, BUTTONS_HEIGHT));
		generateExamplePeopleButton.addActionListener(new generateExamplePeopleButtonListener());

		deleteAllButton = new JButton("! Delete ALL");
		deleteAllButton.setPreferredSize(new Dimension(BUTTONS_WIDTH, BUTTONS_HEIGHT));
		deleteAllButton.addActionListener(new deleteAllButtonListener());
		deleteAllButton.setBackground(Color.RED);
		
		/** Fill whole deleteAllButton by red color */
//		deleteAllButton.setContentAreaFilled(false);
//		deleteAllButton.setOpaque(true);
		
		sortAZButton = new JButton("Sort by name A/Z");
		sortAZButton.setPreferredSize(new Dimension(BUTTONS_WIDTH, BUTTONS_HEIGHT));
		sortAZButton.addActionListener(new sortAZButtonListener());
		
		sortZAButton = new JButton("Sort by name Z/A");
		sortZAButton.setPreferredSize(new Dimension(BUTTONS_WIDTH, BUTTONS_HEIGHT));
		sortZAButton.addActionListener(new sortZAButtonListener());
		
		/** Horizontal eparator */
		separatorH = new JSeparator(JSeparator.HORIZONTAL);
		separatorH.setPreferredSize(new Dimension(2,2));
		
		/** Add items to their panels */
		tablePanel.add(getTable().getTableHeader());
		tablePanel.add(scrollPane);
		
		buttonsPanel.add(addButton);
		buttonsPanel.add(Box.createRigidArea(new Dimension(0, 3)));
		buttonsPanel.add(editButton);
		buttonsPanel.add(Box.createRigidArea(new Dimension(0, 3)));
		buttonsPanel.add(deleteButton);
		buttonsPanel.add(Box.createRigidArea(new Dimension(0, 3)));
		buttonsPanel.add(generateExamplePeopleButton);
		buttonsPanel.add(Box.createRigidArea(new Dimension(0, 3)));
		buttonsPanel.add(deleteAllButton);
		buttonsPanel.add(Box.createRigidArea(new Dimension(0, 3)));
		buttonsPanel.add(sortAZButton);
		buttonsPanel.add(Box.createRigidArea(new Dimension(0, 3)));
		buttonsPanel.add(sortZAButton);
		
		/** Relocate compoments */
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0,2,2,0);
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		mainPanel.add(titleLabel, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(0,0,0,0);
		c.fill = GridBagConstraints.HORIZONTAL;
		mainPanel.add(separatorH, c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(10,0,0,0);
		mainPanel.add(tablePanel, c);
		
		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(9,10,0,0);
		mainPanel.add(buttonsPanel, c);
		
		/** Send loaded Person Objects to GUI TABLE */
		Data.sendLoadedDataToGUI();
		
		/** Sort table by click headers */
//		table.setAutoCreateRowSorter(true);

		/** NOT WORKING */
//		table.getRowSorter().addRowSorterListener(new RowSorterListener() {
//		@Override
//        public void sorterChanged(RowSorterEvent e) {
//			
//			System.out.println("----------------------------");
//			for(int i=0; i<Data.persons.size(); i++){
//				
//				int sortedRowIndex = table.getRowSorter().convertRowIndexToView(i);		//get row index 
//				
//				Data.persons.remove(sortedRowIndex);
//				Data.persons.set(i, Data.persons.get(sortedRowIndex));
//				
//				  System.out.println("table.getRowSorter(): " +table.getRowSorter().convertRowIndexToView(i)+ 
//						  " = " +Data.persons.get(i).getName()+ ": Data.persons.get(i).getName()");
//			}
//        }
//		});
		
		/** Activate Main Frame and its properties */
		mainFrame.add(mainPanel);
		mainFrame.setBounds(50, 50, 0, 0);
		mainFrame.setResizable(false);
		mainFrame.pack();
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	/** Getter, setter for table model */
	public static MyTableModel getModel() { return model; }
	public void setModel(MyTableModel model) { GUI.model = model; }

	/** Getter, setter for table */
	public JTable getTable() { return table; }
	public void setTable(JTable table) { GUI.table = table; }
	
	/** Getter for selected row in table */
	public static int getSelectedRow(){
		selectedRow = table.getSelectedRow();
		return selectedRow;
	}

	public class addButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent a) {
			/** new window for add new Person Object to table */
			new AddPersonPanel();
		}
	}
	
	public class editButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent a) {
			
			/** Selected Row in table */
		    selectedRow = table.getSelectedRow();
		    
		    /** if selectedRow isn't empty */
            if(selectedRow != -1){
            	
            	/** new window for edit Person Objects */
            	new EditPersonPanel();
            	
            } else {
            	
            	AddressBook.setWarningMsg("Mark any row before edit person data.");
            	
            }
		}
	}
	
	public class deleteButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent a) {
			
			/** Show ConfirmDialog YES/NO Window */
			int result = JOptionPane.showConfirmDialog(null, 
					   "Do you want delete this row?", "Address Book", JOptionPane.YES_NO_OPTION);
			
				if(result == JOptionPane.YES_OPTION) {
					 
					/** Selected Row in table */
				    selectedRow = table.getSelectedRow();
				    
		            /** if selectedRow isn't empty */
		            if(selectedRow != -1){
		            	
		            	/** Remove Row in Table and  remove Object Person in persons ArrayList */
		            	model.removeRow(selectedRow);
			            Data.persons.remove(selectedRow);
			            
			            /** Save new state of table */
			            try {
							Data.savePersons();		//save new persons ArrayList to file
						} catch (IOException e1) {
							AddressBook.setWarningMsg(e1.getMessage());
						}
		            }
		            
				} 
		}
	}
	
	 /** Unused Global RowListener */
//	 private class RowListener implements ListSelectionListener {
//	        public void valueChanged(ListSelectionEvent event) {
//	            if (event.getValueIsAdjusting()) {
//	            	System.out.println("ok");
//	            }
//	        }
//	 }
	 
	 /** Generate 100 Example Person Objects in table */
	 public class generateExamplePeopleButtonListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent a) {
				
				for(int i=1; i<101; i++){
					
					/** Generate in table */
		        	GUI.getModel().addRow(new Person("Name "+i, "Surname "+i,
							"e-mail "+i, "+"+i+" 123456789"));
		        	
		        	/** Generate the same in List */
		        	Data.persons.add(new Person("Name "+i, "Surname "+i,
							"e-mail "+i, "+"+i+" 123456789"));
		        }
		        
				/** Save new state of table */
		        try {
					Data.savePersons();
				} catch (IOException e1) {
					AddressBook.setWarningMsg(e1.getMessage());
				}
			}
		}
	 
	 /** Delete all Person Objects in table */
	 public class deleteAllButtonListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent a) {
				
				/** Remove all rows in Table and remove all Objects Person in persons ArrayList */
				while(Data.persons.size() != 0){
					
					/** Delete all in table and List */
					for(int i=0; i<Data.persons.size(); i++){
		            	model.removeRow(i);
			            Data.persons.remove(i);
					}
				}
				
				/** Save new state of table */
				try {
					Data.savePersons();		
				} catch (IOException e1) {
					AddressBook.setWarningMsg(e1.getMessage());
				}
			}
		}
	 
	 public class sortAZButtonListener implements ActionListener {
		 @Override
		 public void actionPerformed(ActionEvent a){ 
			 
			Data.sortPersonsByNameAZ(); 
		}
	 }
	 
	 public class sortZAButtonListener implements ActionListener {
		 @Override
		 public void actionPerformed(ActionEvent a){ 
			 
			Data.sortPersonsByNameZA(); 
		}
	 }
}