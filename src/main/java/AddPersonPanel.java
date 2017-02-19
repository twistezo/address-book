import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

/** 
 * Class for creating window for add Person Objects data
 * 
 * @author twistezo
 *
 */

@SuppressWarnings("serial")
public class AddPersonPanel extends JPanel{
	   private static final int BORDER_OFFSET = 10;
	   private static final int TEXT_FIELD_WIDTH = 15;
	   private JPanel mainPanel;
	   private JFrame mainFrame;
	   private JPanel buttonsPanel;
	   private GridBagConstraints c;
	    JLabel nameLabel;
	    JLabel surnameLabel;
	    JLabel emailLabel;
	    JLabel telephoneLabel;
	   private JLabel title;
	   private JTextField nameField;
	    JTextField surnameField;
	    JTextField emailField;
	    JTextField telephoneField;
	   private JButton addButton;
	   private JButton cancelButton;
	   private JLabel labelsArray[] = new JLabel[4];
	   private JTextField textsFieldArray[] = new JTextField[4];
	   private JSeparator separatorH;
	   
	   
	   public AddPersonPanel(){
		   
		   mainFrame = new JFrame();
		   mainPanel = new JPanel(new GridBagLayout());
		   mainPanel.setBorder(BorderFactory.createEmptyBorder(BORDER_OFFSET, BORDER_OFFSET, BORDER_OFFSET, BORDER_OFFSET));
		   buttonsPanel = new JPanel(new FlowLayout());
		   c = new GridBagConstraints();	
		   
		   title = new JLabel("Create new person data");
		   title.setFont(new Font(null, Font.PLAIN, 15));
		   title.setForeground(Color.GRAY);
		   
		   separatorH = new JSeparator(JSeparator.HORIZONTAL);
		   separatorH.setPreferredSize(new Dimension(2,2));
		   
		   labelsArray[0] = nameLabel = new JLabel("Name: ");
		   labelsArray[1] = surnameLabel = new JLabel("Surname: ");
		   labelsArray[2] = emailLabel = new JLabel("E-mail: ");
		   labelsArray[3] = telephoneLabel = new JLabel("Telephone: ");
		   
		   textsFieldArray[0] = nameField = new JTextField("",TEXT_FIELD_WIDTH);
		   textsFieldArray[1] = surnameField = new JTextField("",TEXT_FIELD_WIDTH);
		   textsFieldArray[2] = emailField = new JTextField("",TEXT_FIELD_WIDTH);
		   textsFieldArray[3] = telephoneField = new JTextField("",TEXT_FIELD_WIDTH);
		   
		   addButton = new JButton(new AddPersonAction("Add"));
		   cancelButton = new JButton(new CancelAction("Cancel"));
		   
		   /** offset */
		   c.insets = new Insets(0,0,2,0);
		   
		   c.anchor = GridBagConstraints.FIRST_LINE_START;
		   c.gridwidth = 2;
		   mainPanel.add(title, c);
		   
		   c.gridy = 1;
		   c.insets = new Insets(0,0,10,0);
		   c.fill = GridBagConstraints.HORIZONTAL;
		   mainPanel.add(separatorH, c);
		   
		   c.anchor = GridBagConstraints.FIRST_LINE_END;
		   c.insets = new Insets(0,0,10,0);
		   
		   /** x={0}, y={1,4} */
		   for(int i=0; i<labelsArray.length; i++){
			   c.gridx = 0;
			   c.gridy = i+2;
			   c.gridwidth = 1;
			   c.fill = GridBagConstraints.VERTICAL;
			   mainPanel.add(labelsArray[i], c);
		   }
		   
		   for(int i=0; i<textsFieldArray.length; i++){
			   c.gridx = 1;
			   c.gridy = i+2;
			   c.gridwidth = 1;
			   c.fill = GridBagConstraints.VERTICAL;
			   mainPanel.add(textsFieldArray[i], c);
		   }
		   
		   buttonsPanel.add(addButton);
		   buttonsPanel.add(cancelButton);
		   
		   c.gridx = 0;
		   c.gridy = 6;
		   c.gridwidth = 2;
		   c.insets = new Insets(0,0,0,0);
		   c.anchor = GridBagConstraints.CENTER;
		   mainPanel.add(buttonsPanel, c);
		   
		   /** Add one Keylistener to every *JField */
		   nameField.addKeyListener(new EnterKeyListener());
		   surnameField.addKeyListener(new EnterKeyListener());
		   emailField.addKeyListener(new EnterKeyListener());
		   telephoneField.addKeyListener(new EnterKeyListener());
		   
		   mainFrame.add(mainPanel);
		   mainFrame.setBounds(600, 250, 0, 0);
		   mainFrame.setResizable(false);
		   mainFrame.pack();
		   mainFrame.setVisible(true);
	   }
	   
	   private class AddPersonAction extends AbstractAction {
	        public AddPersonAction(String name) {
	            super(name);
	        }
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	
	        	/** Add user input data do Table and persons List */
	        	GUI.getModel().addRow(new Person(nameField.getText(), surnameField.getText(), 
	            								emailField.getText(), telephoneField.getText()));
	        	
	        	Data.persons.add(new Person(nameField.getText(), surnameField.getText(), 
											 emailField.getText(), telephoneField.getText()));
	            
	        	/** Save to file */
	            try {
					Data.savePersons();
				} catch (IOException e1) {
					AddressBook.setWarningMsg(e1.getMessage());
				}
	            
	            mainFrame.dispose();
	        }
	   }   
	   
	   private class CancelAction extends AbstractAction {
	        public CancelAction(String name) {
	            super(name);
	        }
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            mainFrame.dispose();
	        }
	   }   
	   
	   /** The same as in AddKeyListener but works after Enter key */
	   public class EnterKeyListener implements KeyListener {
		   
		   public void keyTyped(KeyEvent e) {}

           public void keyReleased(KeyEvent e) {}

           public void keyPressed(KeyEvent e) {
               
     		int key = e.getKeyCode();
   	  	    if (key == KeyEvent.VK_ENTER) {

	   	  	    GUI.getModel().addRow(new Person(nameField.getText(), surnameField.getText(), 
						emailField.getText(), telephoneField.getText()));
	
				Data.persons.add(new Person(nameField.getText(), surnameField.getText(), 
											 emailField.getText(), telephoneField.getText()));
				
				try {
					Data.savePersons();
				} catch (IOException e1) {
					AddressBook.setWarningMsg(e1.getMessage());
				}
				
				mainFrame.dispose();
   	  	    }	
          }
		}
	   
          
	   
}