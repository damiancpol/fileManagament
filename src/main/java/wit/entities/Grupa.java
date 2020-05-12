package wit.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table
public class Grupa {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
	String Groups;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGroups() {
		return Groups;
	}
	public void setGroups(String groups) {
		Groups = groups;
	}

}
