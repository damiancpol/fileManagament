package wit.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table()
public class PrzynaleznoscGrup {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	int iduzytkownika;
	String Grupa;
	public int getIduzytkownika() {
		return iduzytkownika;
	}
	public void setIduzytkownika(int iduzytkownika) {
		this.iduzytkownika = iduzytkownika;
	}
	public String getGrupa() {
		return Grupa;
	}
	public void setGrupa(String grupa) {
		Grupa = grupa;
	}
	
}
