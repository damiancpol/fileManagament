package wit.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="navigationTags")
public class NavigationTags {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String NavigationTags;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNavigationTags() {
		return NavigationTags;
	}
	public void setNavigationTags(String navigationTags) {
		NavigationTags = navigationTags;
	}

}
