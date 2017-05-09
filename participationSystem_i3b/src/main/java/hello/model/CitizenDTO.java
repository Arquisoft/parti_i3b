package hello.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

public class CitizenDTO {

	public String firstName;
	public String lastName;
	public Integer age;
	public String ID;
	public String email;

	public CitizenDTO() {
	}

	public CitizenDTO(User user) {
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		LocalDate date = user.getBirthDate().toInstant().atZone(ZoneId
				.systemDefault()).toLocalDate();
		this.age = Period.between(date, LocalDate.now()).getYears();
		this.ID = user.getNIF();
		this.email = user.getEmail();
	}
}