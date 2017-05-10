package asw.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Oriol. Class use to represent the citizens and parse their data.
 */
@Document(collection = "users")
public class User {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String firstName;
    private String lastName;
    private String email;
    private Date birthDate;
    private String address;
    private String ID;
    private String password;
    private String nationality;
    private String NIF;
    private int pollingStation;
    private boolean isAdmin = false;

    public User() {
    }

    public User(String firstName, String lastName, String email,
	    String birthDate, String address, String nationality, String ID,
	    String NIF, int pollingStation) {

	this.firstName = firstName;
	this.lastName = lastName;
	this.email = email;
	setbirthDate(birthDate);
	this.address = address;
	this.nationality = nationality;
	this.ID = ID;
	this.NIF = NIF;
	this.pollingStation = pollingStation;
    }

    public User(String firstName, String lastName, String email, Date birthDate,
	    String address, String nationality, String ID, String NIF,
	    int pollingStation) {

	this.firstName = firstName;
	this.lastName = lastName;
	this.email = email;
	this.birthDate = birthDate;
	this.address = address;
	this.nationality = nationality;
	this.ID = ID;
	this.NIF = NIF;
	this.pollingStation = pollingStation;
    }

    public User(String[] data) {
	this.firstName = data[0];
	this.lastName = data[1];
	this.email = data[2];
	setbirthDate(data[3]);
	this.address = data[4];
	this.nationality = data[5];
	this.ID = data[6];
	this.NIF = data[7];
	this.pollingStation = Integer.parseInt(data[8].replace(".0", ""));
    }

    private void setbirthDate(String birthDate) {
	DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	Date date = null;
	try {
	    date = format.parse(birthDate);
	} catch (ParseException e) {
	    e.printStackTrace();
	}
	this.birthDate = date;
    }

    public String getNationality() {
	return nationality;
    }

    public String getName() {
	return firstName;
    }

    public String getlastName() {
	return lastName;
    }

    public Date getbirthDate() {
	return birthDate;
    }

    public String getAddress() {
	return address;
    }

    public String getID() {
	return ID;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String pw) {
	this.password = pw;
    }

    public String getNIF() {
	return NIF;
    }

    public int getpollingStation() {
	return pollingStation;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((ID == null) ? 0 : ID.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	User other = (User) obj;
	if (ID == null) {
	    if (other.ID != null)
		return false;
	} else if (!ID.equals(other.ID))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "Citizen [firstName=" + firstName + ", lastName=" + lastName
		+ ", email=" + email + ", birthDate=" + birthDate + ", address="
		+ address + ", ID=" + ID + ", nationality=" + nationality + ","
		+ " NIF=" + NIF + ", pollingStation=" + pollingStation + "]";
    }

    public boolean isAdmin() {
	return isAdmin;
    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public Date getBirthDate() {
	return birthDate;
    }

    public void setBirthDate(Date birthDate) {
	this.birthDate = birthDate;
    }

    public int getPollingStation() {
	return pollingStation;
    }

    public void setPollingStation(int pollingStation) {
	this.pollingStation = pollingStation;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    public void setAddress(String address) {
	this.address = address;
    }

    public void setID(String iD) {
	ID = iD;
    }

    public void setNationality(String nationality) {
	this.nationality = nationality;
    }

    public void setNIF(String nIF) {
	NIF = nIF;
    }

    public void setAdmin(boolean isAdmin) {
	this.isAdmin = isAdmin;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

}
