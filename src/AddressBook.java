import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
* @author twistezo
* 
* @todo 
* -connect with sql database
*
* @features
* -Table Rows and Columns are connected to ArrayList of Objects with Persons data
* -Person Object have 'name', 'surname', 'email', 'telephone'
* -Table data is stored in local file "C:\\AddressBookData.ser"
* -On start Table Data is loading from file
* -Every changes by 'add', 'delete', 'edit' buttons causes saving table data in file "C:\\AddressBookData.ser"
* -From JMenu Bar can save file to specific place with specific filename and the same open file
* -Can add new Person Object (name, surname etc.) to table using new window
* -Can mark any row in table and click Edit to edit this Person data in new window
* -Can delete Person from marked row in table by new warning YES/NO window
* -Can sort table data (ArrayList of Persons Objects) by buttons Sort A/Z & Sort Z/A
* -App has buttons for testing '! Gen. Example People', and '! Delete All'
* -App has universal popup warning OK window with information about possible Java Exceptions
* 
* @bugs
* Table sort works properly only with buttons Sort A/Z & Sort Z/A
* If you double click on table header table is sorted only in table VIEW. Not in Person List.
* This type of sort isn't connected to List with Persons Objects.
* By default option with sorting by click header is off.
*/

public class AddressBook {
	public static boolean isThreadRun = true;
	
	public static void main(String[] args) {
		
		try 
	    { 
			/** Set LAF Style */
	        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");  
//	        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
	        
	        /** Load Table data on start app */
	        Data.loadPersons();
	        
	        /** Create GUI */
	        while(isThreadRun){
	        	SwingUtilities.invokeLater(() -> new GUI());
	        	isThreadRun = false;
	        }
	    } 
		
	    catch(Exception e){ 
	    	AddressBook.setWarningMsg(e.getMessage());
	    }
	}
	
	/** Pop-up error message with OK button */
	public static void setWarningMsg(String text){
//	    Toolkit.getDefaultToolkit().beep();
	    JOptionPane optionPane = new JOptionPane(text,JOptionPane.WARNING_MESSAGE);
	    JDialog dialog = optionPane.createDialog("Problem with application");
	    dialog.setAlwaysOnTop(true);
	    dialog.setVisible(true);
	}
	
//	public static boolean getIsThreadRun(){
//		return isThreadRun;
//	}
}


