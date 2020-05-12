package wit.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="docs")
public class Docs {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String Title;
	String DocumentCategory;
	String NavigationTags;
	String DocumentTags;
	String Language;
	String Scope;
	String ReleaseDate;
	String Modified;
	String ModifiedBy;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDocumentCategory() {
		return DocumentCategory;
	}
	public void setDocumentCategory(String documentCategory) {
		DocumentCategory = documentCategory;
	}
	public String getNavigationTags() {
		return NavigationTags;
	}
	public void setNavigationTags(String navigationTags) {
		NavigationTags = navigationTags;
	}
	public String getDocumentTags() {
		return DocumentTags;
	}
	public void setDocumentTags(String documentTags) {
		DocumentTags = documentTags;
	}
	public String getLanguage() {
		return Language;
	}
	public void setLanguage(String language) {
		Language = language;
	}
	public String getScope() {
		return Scope;
	}
	public void setScope(String scope) {
		Scope = scope;
	}
	public String getReleaseDate() {
		return ReleaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		ReleaseDate = releaseDate;
	}
	public String getModified() {
		return Modified;
	}
	public void setModified(String modified) {
		Modified = modified;
	}
	public String getModifiedBy() {
		return ModifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		ModifiedBy = modifiedBy;
	}

}
