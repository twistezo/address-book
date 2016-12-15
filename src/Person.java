import java.io.Serializable;

/**
 * Class - pattern for Person data
 * 
 * @author twistezo
 *
 */

public class Person implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String surname;
	private String mail;
	private String telephone;
	Person person;
	
	public Person(String n, String s, String m, String t){
		name = n;
		surname = s;
		mail = m;
		telephone = t;
	}
	
	public String getName() {
        return name;
    }
	
	public void setName(String n){
		name = n;
	}

    public String getSurname() {
        return surname;
    }
    
    public void setSurname(String s){
    	surname = s;
    }

    public String getMail() {
        return mail;
    }
    
    public void setMail(String m){
    	mail = m;
    }

    public String getTelephone() {
        return telephone;
    }
    
    public void setTelephone(String t){
    	telephone = t;
    }
}