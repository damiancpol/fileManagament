package wit.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="documentCategory")
public class DocumentCategory {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)

	int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDocumentCategory() {
		return DocumentCategory;
	}
	public void setDocumentCategory(String documentCategory) {
		DocumentCategory = documentCategory;
	}
	String DocumentCategory;
}
