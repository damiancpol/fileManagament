package wit.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Stanowisko {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
int id;
String Stanowisko;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getStanowisko() {
	return Stanowisko;
}
public void setStanowisko(String stanowisko) {
	Stanowisko = stanowisko;
}
}
