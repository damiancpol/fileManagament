package wit.service;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

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

public interface LoginServiceInterface {

	
Logins checkLogin(Logins login);

List<Logins> getLogins();

List<Docs> getAllDosc();

void saveDocs(Docs docs);

List<DocumentCategory> getallDocumentCategory();

List<NavigationTags> getAllNavigationTags();

List<Language>getAllLanguages();


void saveLanguage(Language language);

void saveScope(Scope scope);

void saveDocumentCategory(DocumentCategory documentCategory);

void saveNavigationTags(NavigationTags navigationTags);

List<Scope> getallScopes();

void saveFileLoad(FileLoad fLoad);

List<Logins> getAllFileUsers();

List<Grupa> getAllGrupa();

void saveLogins(Logins logins);

void saveGroup(Grupa groups);

void saveDir(FileLoad fl);

void delDir(FileLoad fl);

List<FileLoad> getAllFileLoad();

List<FileLoad> getAllUsersbyPath(String fl);

Set<FileLoad>  getAllGroupsbyPath(String fl);

List<FileLoad> searchFiles(FileLoad fLoad);

Set<FileLoad> UserAndGroupFiles(String login, int i);

void saveDirectory(FileLoad fl);

List<FileLoad> szukaj(String title);

List<FileLoad> DocumentCategory(String documentCategory);

List<FileLoad> getNavigationTags(String navigationTags);

List<FileLoad> getallLanguages(String language);

List<FileLoad> getAllScope(String scope);

List<FileLoad>  getAllReleaseDate(String releaseDate);

List<Stanowisko> getAllStanowisko();

void saveStanowisko(Stanowisko stanowisko);

Logins getUSerbyID(int id);



void dodajDoGrupy(Logins log);

List<PrzynaleznoscGrup> getAllGroupByUser(int id);

void deleteLogins(Logins gp);

void saveLoginss(Logins login);

List<FileLoad> saveU(String ses);

void saveU1(List<FileLoad> saveU, FileLoad fl1);

void deleteUSerfromPath(List<FileLoad> deleteUSerList, FileLoad fLoad11);

List<FileLoad> deleteUSerList(FileLoad fLoad11);

List<FileLoad> saveGroupForPath(FileLoad gop, String path);

void saveGroupForPath1(List<FileLoad> saveGroupForPath, String string);

void delGroupFromPath(List<FileLoad> saveGroupForPath, FileLoad gop);


void addGrouptoPath(FileLoad fLoad, String path);

void deleteGroupfromPath(FileLoad fLoad, String fl);

void addUserToPath(FileLoad fLoad1, String fl);

void deleteUSerfromPath1(FileLoad fLoadd1, String fl);

Set<FileLoad> searchFilesUser(Logins logins, FileLoad fLoad);

List<FileLoad> szukaj1(String title, Logins log);

List<FileLoad> getallDocumentCategory1(FileLoad ff, Logins log);

List<FileLoad> getAllNavigationTags1(FileLoad ff, Logins log);

List<FileLoad> getAllLanguage1(FileLoad ff, Logins log);






	
	



	

}
