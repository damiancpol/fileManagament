package wit.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="pracownik")
public class Pracownik {
		@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	int id ;
public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

String title;
@Transient
FileLoad fl;

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}
}
