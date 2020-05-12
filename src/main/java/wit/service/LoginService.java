package wit.service;



import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import wit.dao.RepositoryLoginInterface;
import wit.entities.Final;
import wit.entities.Czaspracy;
import wit.entities.Docs;
import wit.entities.DocumentCategory;
import wit.entities.Dzial;
import wit.entities.FileLoad;
import wit.entities.Groups;
import wit.entities.Grupa;
import wit.entities.Kalendarz;
import wit.entities.Language;
import wit.entities.Logins;
import wit.entities.NavigationTags;
import wit.entities.Pracownik;
import wit.entities.PrzynaleznoscGrup;
import wit.entities.Scope;
import wit.entities.Stanowisko;
import wit.entities.Urlop;
import wit.entities.Uusers;
@Service
public class LoginService implements LoginServiceInterface {
@Autowired
RepositoryLoginInterface rl;
	
	
	@Transactional
	public Logins checkLogin(Logins login) {
		// TODO Auto-generated method stub
		return rl.checkLogin(login);
	}
	@Override
	public List<Logins> getLogins() {
		// TODO Auto-generated method stub
		return rl.getLogins();
	}
	@Transactional
	public List<Docs> getAllDosc() {
		// TODO Auto-generated method stub
		return rl.getAllDosc();
	}
	@Transactional
	public void saveDocs(Docs docs) {
		// TODO Auto-generated method stub
		rl.saveDocs(docs);
	}
	@Transactional
	public List<DocumentCategory> getallDocumentCategory() {
		// TODO Auto-generated method stub
		return rl.getallDocumentCategory();
	}
	@Transactional
	public List<NavigationTags> getAllNavigationTags() {
		// TODO Auto-generated method stub
		return rl.getAllNavigationTags();
	}
	@Transactional
	public List<Language> getAllLanguages() {
		// TODO Auto-generated method stub
		return rl.getAllLanguages();
	}
	@Transactional
	public List<Scope> getallScopes() {
		// TODO Auto-generated method stub
		return rl.getallScopes();
	}
	@Transactional
	public void saveLanguage(Language language) {
		rl.saveLanguage( language);
		
	}
	@Transactional
	public void saveScope(Scope scope) {
		rl.saveScope( scope);
		
	}
	@Transactional
	public void saveDocumentCategory(DocumentCategory documentCategory) {
		rl. saveDocumentCategory(documentCategory);
		
	}
	@Transactional
	public void saveNavigationTags(NavigationTags navigationTags) {
		rl.saveNavigationTags(navigationTags);
		
	}
	@Transactional
	public void saveFileLoad(FileLoad fLoad) {
		rl.saveFileLoad(fLoad);
		
	}
	@Transactional
	public List<Logins> getAllFileUsers() {
		// TODO Auto-generated method stub
		return rl.getAllFileUsers();
	}
	@Transactional
	public List<Grupa> getAllGrupa() {
		// TODO Auto-generated method stub
		return rl.getAllGrupa();
	}
	@Transactional
	public void saveLogins(Logins logins) {
		// TODO Auto-generated method stub
		rl.saveLogins(logins);
	}
	@Transactional
	public void saveGroup(Grupa groups) {
		// TODO Auto-generated method stub
		rl.saveGroup(groups);
		
	}
	@Transactional
	public void saveDir(FileLoad fl) {
		// TODO Auto-generated method stub
		
		rl.saveDir(fl);
	}
	@Transactional
	public void delDir(FileLoad fl) {
		// TODO Auto-generated method stub
		rl.delDir(fl);
	}
	@Transactional
	public List<FileLoad> getAllFileLoad() {
		// TODO Auto-generated method stub
		return rl.getAllFileLoad();
	}
	@Transactional
	public List<FileLoad> getAllUsersbyPath(String fl) {
		// TODO Auto-generated method stub
		return rl.getAllUsersbyPath(fl);
	}

@Transactional
	public Set<FileLoad> getAllGroupsbyPath(String fl) {
		// TODO Auto-generated method stub
		return rl.getAllGroupsbyPath(fl) ;
	}
@Transactional
public List<FileLoad> searchFiles(FileLoad fLoad) {
	// TODO Auto-generated method stub
	return rl.searchFiles(fLoad) ;
}
@Transactional
public Set<FileLoad> UserAndGroupFiles(String login ,int id) {
	// TODO Auto-generated method stub
	return rl.UsersAndGroupFiles(login,id);
}
@Transactional
public void saveDirectory(FileLoad fl) {
	// TODO Auto-generated method stub
	rl.saveDirectory(fl);
}
@Transactional
public List<FileLoad> szukaj(String title) {
	// TODO Auto-generated method stub
	return rl.getTitle(title);
}
@Transactional
public List<FileLoad> DocumentCategory(String documentCategory) {
	// TODO Auto-generated method stub
	return rl.getDocumentCategory(documentCategory);
}
@Transactional
public List<FileLoad> getNavigationTags(String navigationTags) {
	// TODO Auto-generated method stub
	return rl.getAllNavigationTags1(navigationTags);
}
@Transactional
public List<FileLoad> getallLanguages(String language) {
	// TODO Auto-generated method stub
	return rl.getAllLanguagess(language);
}
@Transactional
public List<FileLoad> getAllScope(String scope) {
	// TODO Auto-generated method stub
	return rl.getAllScope(scope);
}
@Transactional
public List<FileLoad> getAllReleaseDate(String releaseDate) {
	// TODO Auto-generated method stub
	return rl.getReleaseDate(releaseDate);
}
@Transactional
public List<Stanowisko> getAllStanowisko() {
	// TODO Auto-generated method stub
	return rl.getAllStanowisko();
}
@Transactional
public void saveStanowisko(Stanowisko stanowisko) {
	rl.saveStanowisko(stanowisko);
	
}
@Transactional
public Logins getUSerbyID(int id) {
	// TODO Auto-generated method stub
	return rl.getLoginByid(id);
}
@Transactional

public void dodajDoGrupy(Logins log) {
	// TODO Auto-generated method stub
	rl.DodajDoGrupy1(log);
}
@Transactional
public List<PrzynaleznoscGrup> getAllGroupByUser(int id) {
	// TODO Auto-generated method stub
	return rl.getAllGroupByUser(id);
}
@Transactional
public void deleteLogins(Logins gp) {
	rl.delete(gp);
	
}
@Transactional
public void saveLoginss(Logins login) {
	// TODO Auto-generated method stub
	rl.saveLoginss(login);
}
@Transactional
public List<FileLoad> saveU(String ses) {
	return rl.saveU(ses);
}
@Transactional
public void saveU1(List<FileLoad> saveU,FileLoad fl1) {
	// TODO Auto-generated method stub
	rl.saveU1(saveU,fl1);
}
@Transactional
public void deleteUSerfromPath(List<FileLoad> fLoad1,	FileLoad Load11) {
	
	rl.deleteUSerfromPath(fLoad1,Load11);
	
}
@Transactional
public List<FileLoad> deleteUSerList(FileLoad fLoad11) {
	// TODO Auto-generated method stub
	return rl.deleteUSerList(fLoad11);
}
@Transactional
public List<FileLoad>  saveGroupForPath(FileLoad gop, String path) {
	// TODO Auto-generated method stub
	return rl.saveGroupForPath(gop,path);
}
@Transactional
public void saveGroupForPath1(List<FileLoad> saveGroupForPath,String groups) {
	// TODO Auto-generated method stub
	rl.saveGroupForPath1(saveGroupForPath,groups);
}
@Transactional
public void delGroupFromPath(List<FileLoad>  groups, FileLoad path) {
	// TODO Auto-generated method stub
	rl.delGroupFromPath( groups,  path);
}
@Transactional
public void addGrouptoPath(FileLoad fLoad,String path) {
rl.addGrouptoPath(fLoad,path);
}
@Transactional
public void deleteGroupfromPath(FileLoad fLoad, String fl) {
	// TODO Auto-generated method stub
	rl.deleteGroupfromPath( fLoad,  fl);
}
@Transactional
public void addUserToPath(FileLoad fLoad1, String fl) {
	// TODO Auto-generated method stub
	rl.addUserToPath( fLoad1,  fl);
}
@Transactional
public void deleteUSerfromPath1(FileLoad fLoadd1, String fl) {
	// TODO Auto-generated method stub
	rl.deleteUSerfromPath1( fLoadd1,  fl);
}
@Transactional
public Set<FileLoad> searchFilesUser(Logins logins, FileLoad fLoad) {
	// TODO Auto-generated method stub
	return rl.searchFilesUser(logins,  fLoad);
}
@Transactional
public List<FileLoad> szukaj1(String title, Logins log) {
	// TODO Auto-generated method stub
	return rl.szukaj1( title,  log);
}
@Transactional
public List<FileLoad> getallDocumentCategory1(FileLoad ff, Logins log) {
	// TODO Auto-generated method stub
	return rl.getallDocumentCategory1( ff,  log);
}
@Transactional
public List<FileLoad> getAllNavigationTags1(FileLoad ff, Logins log) {
	// TODO Auto-generated method stub
	return rl.getAllNavigationTags1( ff,  log);
}
@Override
public List<FileLoad> getAllLanguage1(FileLoad ff, Logins log) {
	// TODO Auto-generated method stub
	return rl.getAllLanguage1( ff,  log);
}




	
	
	

}
;