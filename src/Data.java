import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Class with operations on Person Objects data
 * 
 * @author twistezo
 *
 */

public class Data {
	
	/** ArrayList for storing all Person Objects in app */
	public static ArrayList<Person> persons = new ArrayList<Person>();
	
	/** Method for sending loaded data to GUI when app is starting */
	public static void sendLoadedDataToGUI(){
		
		for(int i=0; i<persons.size(); i++) {
			
			/** Reading previously saved data in persons List */
			GUI.getModel().addRow(new Person(persons.get(i).getName(), persons.get(i).getSurname(),
	        								 persons.get(i).getMail(), persons.get(i).getTelephone()));
		}
	}
	
	/** Method for save persons List to local file */
	public static void savePersons() throws IOException{
		 FileOutputStream fout = null;
		 ObjectOutputStream oos = null;
		 
         try{
             fout = new FileOutputStream("C:\\data.ser", false);
             oos = new ObjectOutputStream(fout);
             oos.writeObject(persons);
             oos.close();
             
         } catch (Exception ex) {
        	 AddressBook.setWarningMsg(ex.getMessage());
         
         } finally {
             if(oos != null){
                 oos.close();
             } 
         }
	}
	
	/** Method for load persons List from local file */
	@SuppressWarnings("unchecked")
	public static void loadPersons() throws IOException{
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		try {
		    fis = new FileInputStream("C:\\data.ser");
		    ois = new ObjectInputStream(fis);
		    persons = (ArrayList<Person>) ois.readObject(); 
            ois.close();
            
		} catch (Exception e) {
			AddressBook.setWarningMsg(e.getMessage());
		
		} finally {
		    if(ois != null){
		        ois.close();
		    } 
		}
	}
	
	/** Method for sorting persons List by A/Z */
	public static void sortPersonsByNameAZ(){
		
		/** Sort A/Z */
		Collections.sort(persons, new PersonNameComparatorAZ());
		
		/** Set sorted values in table */
		for(int i=0; i<persons.size(); i++){
			
			GUI.getModel().setValueAt((persons.get(i).getName()), i, 0);
	    	GUI.getModel().setValueAt((persons.get(i).getSurname()), i, 1);
	    	GUI.getModel().setValueAt((persons.get(i).getMail()), i, 2);
	    	GUI.getModel().setValueAt((persons.get(i).getTelephone()), i, 3);
		}
	}
	
	/** Method for sorting persons List by Z/A */
	public static void sortPersonsByNameZA(){
		
		/** Sort Z/A */
		Collections.sort(persons, new PersonNameComparatorZA());
		
		/** Set sorted values in table */
		for(int i=0; i<persons.size(); i++){
			
			GUI.getModel().setValueAt((persons.get(i).getName()), i, 0);
	    	GUI.getModel().setValueAt((persons.get(i).getSurname()), i, 1);
	    	GUI.getModel().setValueAt((persons.get(i).getMail()), i, 2);
	    	GUI.getModel().setValueAt((persons.get(i).getTelephone()), i, 3);
		}
	}
	
	/** Comparator for sort A/Z */
	public static class PersonNameComparatorAZ implements Comparator<Person> {
	    public int compare(Person p1, Person p2) {
	        return p1.getName().toLowerCase().compareTo(p2.getName().toLowerCase());
	    }
	}
	
	/** Comparator for sort Z/A */
	public static class PersonNameComparatorZA implements Comparator<Person> {
	    public int compare(Person p1, Person p2) {
	        return p2.getName().toLowerCase().compareTo(p1.getName().toLowerCase());
	    }
	}
	
}

