package wit.entities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="fileload")
public class FileLoad {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@Transient
	List<MultipartFile> mfile;
	String path;
	String nazwaFolderu;
	String Data;
	File[] files;
	Date dates;
	Long dates1;
	String searchFiles;
	String Title;
	String DocumentCategory;
	String NavigationTags;
	String DocumentTags;
	String Language;
	String Scope;
	String ReleaseDate;
	String Modified;
	String ModifiedBy;
	@Transient
	List<String> paths;
	String Users;
	String Groups;
	@Transient
	String path1;



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPath1() {
		return path1;
	}

	public void setPath1(String path1) {
		this.path1 = path1;
	}

	public String getUsers() {
		return Users;
	}

	public void setUsers(String users) {
		Users = users;
	}

	public String getGroups() {
		return Groups;
	}

	public void setGroups(String groups) {
		Groups = groups;
	}

	public List<String> getPaths() {
		return paths;
	}

	public void setPaths(List<String> paths) {
		this.paths = paths;
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

	public String getSearchFiles() {
		return searchFiles;
	}

	public void setSearchFiles(String searchFiles) {
		this.searchFiles = searchFiles;
	}

	public File[] getFiles() {
		return files;
	}

	public void setFiles(File[] files) {
		this.files = files;
	}

	public Date getDates() {
		return dates;
	}

	public void setDates(Date dates) {
		this.dates = dates;
	}

	public String getData() {
		return Data;
	}

	public Long getDates1() {
		return dates1;
	}

	public void setDates1(Long dates1) {
		this.dates1 = dates1;
	}

	public void setData(String data) {
		Data = data;
	}

	public String getNazwaFolderu() {
		return nazwaFolderu;
	}

	public void setNazwaFolderu(String nazwaFolderu) {
		this.nazwaFolderu = nazwaFolderu;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<MultipartFile> getMfile() {
		return mfile;
	}

	public void setMfile(List<MultipartFile> mfile) {
		this.mfile = mfile;
	}

	public void TranserFiles(String path) throws IllegalStateException, IOException {
		List<MultipartFile> mfile = new ArrayList<MultipartFile>();
		for (MultipartFile fo : mfile) {

			fo.transferTo(new File(path + "\\" + fo.getOriginalFilename()));

		}

	}

}
