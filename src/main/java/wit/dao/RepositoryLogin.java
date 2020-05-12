
package wit.dao;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import freemarker.template.SimpleDate;
import wit.Hibernateutil;
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
import wit.service.LoginServiceInterface;

@Repository
public class RepositoryLogin implements RepositoryLoginInterface {

	Hibernateutil ss;

	@Override
	public Logins checkLogin(Logins ls) {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();

		Logins logins = (Logins) openSession.createQuery("from Logins where login=:login and password=:password ")
				.setParameter("login", ls.getLogin()).setParameter("password", ls.getPassword()).uniqueResult();
		tr.commit();
		openSession.close();

		return logins;
	}

	@Override
	public List<Logins> getLogins() {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();

		List<Logins> ls = (List<Logins>) openSession.createQuery("from Logins").list();

		tr.commit();
		openSession.close();
		return ls;
	}

	@Override
	public List<Docs> getAllDosc() {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();

		List<Docs> dc = (List<Docs>) openSession.createQuery("from Docs").list();

		tr.commit();
		openSession.close();

		return dc;
	}

	@Override
	public void saveDocs(Docs docs) {

		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();
		openSession.save(docs);

		tr.commit();
		openSession.close();

	}

	@Override
	public void saveLanguage(Language language) {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();
		openSession.save(language);

		tr.commit();
		openSession.close();

	}

	@Override
	public void saveScope(Scope scope) {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();
		openSession.save(scope);

		tr.commit();
		openSession.close();

	}

	@Override
	public void saveDocumentCategory(DocumentCategory documentCategory) {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();
		openSession.save(documentCategory);

		tr.commit();
		openSession.close();

	}

	@Override
	public void saveNavigationTags(NavigationTags navigationTags) {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();
		openSession.save(navigationTags);

		tr.commit();
		openSession.close();

	}

	@Override
	public List<DocumentCategory> getallDocumentCategory() {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();

		List<DocumentCategory> dc = (List<DocumentCategory>) openSession
				.createQuery("from DocumentCategory where DocumentCategory is not null").list();

		tr.commit();
		openSession.close();

		return dc;
	}

	@Override
	public List<NavigationTags> getAllNavigationTags() {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();

		List<NavigationTags> nt = (List<NavigationTags>) openSession
				.createQuery("from NavigationTags where NavigationTags is not null").list();

		tr.commit();
		openSession.close();

		return nt;
	}

	@Override
	public List<Language> getAllLanguages() {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();

		List<Language> ln = (List<Language>) openSession.createQuery("from Language where Language is not null").list();

		tr.commit();
		openSession.close();

		return ln;
	}

	@Override
	public List<Scope> getallScopes() {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();

		List<Scope> sc = (List<Scope>) openSession.createQuery("from Scope where Scope is not null").list();

		tr.commit();
		openSession.close();

		return sc;
	}

	@Override
	public void saveFileLoad(FileLoad fLoad) {

		List<String> pat = fLoad.getPaths();

		String name = new File(fLoad.getPath()).getName();
		fLoad.setNazwaFolderu(name);

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss");
		for (String sss : fLoad.getPaths()) {
			Session openSession = ss.session().openSession();
			Transaction tr = openSession.beginTransaction();

			fLoad.setReleaseDate(simpleDateFormat.format(new Date()));
			fLoad.setPath(sss);
			System.out.println(sss);
			String fe = new File(fLoad.getPath()).getName();
		openSession.createQuery("delete from FileLoad where path like :path").setParameter("path", "%" + fe + "%").executeUpdate();

		openSession.save(fLoad);

			tr.commit();

			openSession.close();

		}

	}

	@Override
	public List<Logins> getAllFileUsers() {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();

		List<Logins> log = (List<Logins>) openSession.createQuery("from Logins").list();

		tr.commit();
		openSession.close();

		return log;
	}

	@Override
	public List<Grupa> getAllGrupa() {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();

		List<Grupa> gr = (List<Grupa>) openSession.createQuery("from Grupa").list();

		tr.commit();
		openSession.close();

		return gr;
	}

	@Override
	public void saveLogins(Logins logins) {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();
		PrzynaleznoscGrup pg = new PrzynaleznoscGrup();
		pg.setGrupa(logins.getGrupa());
		pg.setIduzytkownika(logins.getId());

		openSession.saveOrUpdate(pg);

		tr.commit();
		openSession.close();

	}

	@Override
	public void saveGroup(Grupa groups) {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();

		openSession.saveOrUpdate(groups);

		tr.commit();
		openSession.close();

	}

	@Override
	public void saveDir(FileLoad fl) {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy");

		fl.setPath(fl.getPath1());
		fl.setReleaseDate(simpleDateFormat.format(new Date()));
		openSession.saveOrUpdate(fl);

		tr.commit();
		openSession.close();
	}

	@Override
	public void delDir(FileLoad fl) {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy");
		String name = new File(fl.getPath()).getName();
		System.out.println(name + "TUtaj kurwa mac");

		openSession.createQuery("delete from FileLoad where path like :nazwaFolderu")
				.setParameter("nazwaFolderu", "%" + name + "%").executeUpdate();

		tr.commit();
		openSession.close();

	}

	@Override
	public List<FileLoad> getAllFileLoad() {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();

		List<FileLoad> fl = (List<FileLoad>) openSession.createQuery("FROM FileLoad order by ReleaseDate ").list();

		tr.commit();
		openSession.close();
		return fl;
	}

	@Override
	public List<FileLoad> getAllUsersbyPath(String fl) {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();

		List<FileLoad> fl1 = (List<FileLoad>) openSession
				.createQuery("from FileLoad where path like :path and Users is NOT NULL")
				.setParameter("path", "%" + fl + "%").list();

		tr.commit();
		openSession.close();
		return fl1;
	}

	@Override
	public Set<FileLoad> getAllGroupsbyPath(String fl) {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();

		List<FileLoad> fl1 = (List<FileLoad>) openSession
				.createQuery("from FileLoad where path like :path and Groups is NOT NULL")
				.setParameter("path", "%" + fl + "%").list();

		Set<FileLoad> flo = new HashSet<FileLoad>(fl1);

		tr.commit();
		openSession.close();
		return flo;
	}

	@Override
	public List<FileLoad> searchFiles(FileLoad fLoad) {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();

		List<FileLoad> fl = (List<FileLoad>) openSession
				.createQuery("from FileLoad as tab1  where tab1.path like :path  order by ReleaseDate, path asc")
				.setParameter("path", "%" + fLoad.getSearchFiles() + "%").list();

		tr.commit();
		openSession.close();
		return fl;
	}

	@Override
	public Set<FileLoad> UsersAndGroupFiles(String login, int id) {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();
		System.out.println(id + "-Id uzytkownka" + login);
		List<PrzynaleznoscGrup> st1 = openSession.createQuery("from PrzynaleznoscGrup where iduzytkownika=:id")
				.setParameter("id", id).list();
		Set<String> st = new HashSet<>();
		for (PrzynaleznoscGrup ki : st1) {

			st.add(ki.getGrupa().replace(" ", ""));
            
		}

		Set<FileLoad> lo = new HashSet<FileLoad>();

		for (String so : st) {
			List<FileLoad> fl = openSession
					.createQuery("from FileLoad where  Groups like :so or Users like :user order by path desc")
					.setParameter("so", "%" + so + "%").setParameter("user", "%" + login + "%").list();

			
			
			lo.addAll(fl);

			System.out.println(so + "a to Grupa jest");
		}

		for (FileLoad pp : lo) {

			System.out.println(pp.getPath() + "Ścieżka");

		}

		tr.commit();
		openSession.close();

		return lo;
	}

	@Override
	public void saveDirectory(FileLoad fl) {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();
		openSession.saveOrUpdate(fl);

		tr.commit();
		openSession.close();

	}

	@Override
	public List<FileLoad> getTitle(String title) {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();

		List<FileLoad> ug = (List<FileLoad>) openSession
				.createQuery("from FileLoad as tab1  where tab1.Title like :title order by ReleaseDate, path asc")
				.setParameter("title", "%" + title + "%").list();

		tr.commit();
		openSession.close();
		return ug;
	}

	@Override
	public List<FileLoad> getDocumentCategory(String documentCategory) {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();

		List<FileLoad> ug = (List<FileLoad>) openSession.createQuery(
				"from FileLoad as tab1  where tab1.DocumentCategory like :documentCategory  order by ReleaseDate, path asc")
				.setParameter("documentCategory", "%" + documentCategory + "%").list();

		tr.commit();
		openSession.close();
		return ug;
	}

	@Override
	public List<FileLoad> getAllNavigationTags1(String navigationTags) {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();

		List<FileLoad> ug = (List<FileLoad>) openSession.createQuery(
				"from FileLoad as tab1  where tab1.NavigationTags like :navigationTags   order by ReleaseDate, path asc")
				.setParameter("navigationTags", "%" + navigationTags + "%").list();

		tr.commit();
		openSession.close();
		return ug;
	}

	@Override
	public List<FileLoad> getAllLanguagess(String language) {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();

		List<FileLoad> ug = (List<FileLoad>) openSession
				.createQuery(
						"from FileLoad as tab1  where tab1.Language like :language  order by ReleaseDate, path asc")
				.setParameter("language", "%" + language + "%").list();

		tr.commit();
		openSession.close();
		return ug;
	}

	@Override
	public List<FileLoad> getAllScope(String scope) {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();

		List<FileLoad> ug = (List<FileLoad>) openSession
				.createQuery("from FileLoad as tab1  where tab1.Scope like :scope  order by ReleaseDate, path asc")
				.setParameter("scope", "%" + scope + "%").list();

		tr.commit();
		openSession.close();
		return ug;
	}

	@Override
	public List<FileLoad> getReleaseDate(String releaseDate) {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();

		List<FileLoad> ug = (List<FileLoad>) openSession
				.createQuery("from FileLoad where ReleaseDate like :ReleaseDate path asc")
				.setParameter("ReleaseDate", "%" + releaseDate + "%").list();

		tr.commit();
		openSession.close();
		return ug;
	}

	@Override
	public List<Stanowisko> getAllStanowisko() {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();

		List<Stanowisko> ug = (List<Stanowisko>) openSession.createQuery("from Stanowisko").list();

		tr.commit();
		openSession.close();
		return ug;
	}

	@Override
	public void saveStanowisko(Stanowisko stanowisko) {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();

		openSession.saveOrUpdate(stanowisko);

		tr.commit();
		openSession.close();

	}

	@Override
	public Logins getLoginByid(int id) {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();

		Logins logins = (Logins) openSession.createQuery("from Logins where id=:idd").setParameter("idd", id)
				.uniqueResult();

		tr.commit();
		openSession.close();
		return logins;
	}

	@Override
	public void DodajDoGrupy1(Logins log) {

	}

	@Override
	public List<PrzynaleznoscGrup> getAllGroupByUser(int id) {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();

		List<PrzynaleznoscGrup> gg = (List<PrzynaleznoscGrup>) openSession
				.createQuery("from PrzynaleznoscGrup where iduzytkownika=:id").setParameter("id", id).list();

		return gg;
	}

	@Override
	public void delete(Logins gp) {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();

		openSession.createQuery("delete from PrzynaleznoscGrup where iduzytkownika=:id and Grupa=:Grupa")
				.setParameter("id", gp.getId()).setParameter("Grupa", gp.getGrupa()).executeUpdate();

		tr.commit();
		openSession.close();

	}

	@Override
	public void saveLoginss(Logins login) {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();

		openSession.saveOrUpdate(login);
		Logins logins = (Logins) openSession.createQuery("from Logins where login=:login")
				.setParameter("login", login.getLogin()).uniqueResult();
		openSession.createQuery("delete from PrzynaleznoscGrup where Grupa='-' and iduzytkownika=:id")
				.setParameter("id", logins.getId()).executeUpdate();
		PrzynaleznoscGrup pg = new PrzynaleznoscGrup();
		pg.setGrupa(login.getGrupa());
		pg.setIduzytkownika(logins.getId());

		openSession.saveOrUpdate(pg);
		tr.commit();
		openSession.close();

	}

	@Override
	public List<FileLoad>  saveU( String ses) {
		
		List<FileLoad>asdf=new ArrayList<>();
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();
	
	
		String name = new File(ses).getName();
		List<FileLoad> flo = (List<FileLoad>) openSession.createQuery("from FileLoad where path like :path")
				.setParameter("path", "%" + name + "%").list();
		asdf.addAll(flo);
      
	
        tr.commit();
		 openSession.close();
		
		
		
			
		
		 
		
		return asdf;
		 
		 
		 
		

}

	@Override
	public void saveU1(List<FileLoad> saveU,FileLoad fl1) {
		

		
		for(FileLoad asa:saveU) {
			Session openSession1= ss.session().openSession();
			Transaction tr1 = openSession1.beginTransaction();
			
			System.out.println(asa.getGroups()+"To są grupy >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			FileLoad fl=new FileLoad();
			 fl.setData(asa.getData());
			 fl.setDates(asa.getDates());
			 fl.setDates1(asa.getDates1());
			 fl.setDocumentCategory(asa.getDocumentCategory());
			 fl.setDocumentTags(asa.getDocumentTags());
			 fl.setFiles(asa.getFiles());
			 fl.setGroups(asa.getGroups().replace(" ", ""));
			 fl.setLanguage(asa.getLanguage());
			 fl.setMfile(asa.getMfile());
			 fl.setModified(asa.getModified());
			 fl.setModifiedBy(asa.getModifiedBy());
			 fl.setId(asa.getId());
			 fl.setNavigationTags(asa.getNavigationTags());
			 fl.setNazwaFolderu(asa.getNazwaFolderu());
			 fl.setPath(asa.getPath());
			 fl.setReleaseDate(asa.getReleaseDate());
			 fl.setScope(asa.getScope());
			 fl.setSearchFiles(asa.getSearchFiles());
			 fl.setTitle(asa.getTitle());
			 fl.setUsers(asa.getUsers().replace(" ", "").replace(".null", ""));
			 fl.setUsers(fl.getUsers()+"."+fl1.getUsers().replace(" ", ""));

			 openSession1.saveOrUpdate(fl);
			 tr1.commit();
			 openSession1.close();
			 
			
			
		}
		
		
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUSerfromPath(List<FileLoad> fLoad1,FileLoad fLoad11) {
		// TODO Auto-generated method stub
		List<FileLoad> po1=new ArrayList<>();
	
		
		for(FileLoad asa:fLoad1) {
			Session openSession1= ss.session().openSession();
			Transaction tr1 = openSession1.beginTransaction();
			
		
			FileLoad fl=new FileLoad();
			 fl.setData(asa.getData());
			 fl.setDates(asa.getDates());
			 fl.setDates1(asa.getDates1());
			 fl.setDocumentCategory(asa.getDocumentCategory());
			 fl.setDocumentTags(asa.getDocumentTags());
			 fl.setFiles(asa.getFiles());
			 fl.setGroups(asa.getGroups().replace(" ", ""));
			 fl.setLanguage(asa.getLanguage());
			 fl.setMfile(asa.getMfile());
			 fl.setModified(asa.getModified());
			 fl.setModifiedBy(asa.getModifiedBy());
			 fl.setId(asa.getId());
			 fl.setNavigationTags(asa.getNavigationTags());
			 fl.setNazwaFolderu(asa.getNazwaFolderu());
			 fl.setPath(asa.getPath());
			 fl.setReleaseDate(asa.getReleaseDate());
			 fl.setScope(asa.getScope());
			 fl.setSearchFiles(asa.getSearchFiles());
			 fl.setTitle(asa.getTitle());
			 fl.setUsers(asa.getUsers().replace(" ", "").replace(fLoad11.getUsers().replace(" ", "").replace("..", ""), ""));
			 fl.setUsers(fl.getUsers().replace("..", "").replace(" ", ""));
			 fl.setUsers(fl.getUsers().replace(" ", "").replace(fLoad11.getUsers().replace(" ", ""),""));
			 System.out.println(asa.getUsers().replace(" ", "").replace("."+fLoad11.getUsers(), ""));

			 openSession1.saveOrUpdate(fl);
			 tr1.commit();
			 openSession1.close();
			 
			
			
		}
		
		
		
		
		
	}

	@Override
	public List<FileLoad> deleteUSerList(FileLoad fLoad11) {
		// TODO Auto-generated 	Session openSession= ss.session().openSession();
		Session openSession= ss.session().openSession();
		Transaction tr = openSession.beginTransaction();
	
		List<FileLoad> po=openSession.createQuery("from FileLoad where path like :path").setParameter("path", "%"+fLoad11.getPath()+"%").list();
		
		tr.commit();
		openSession.close();
		  
		return po;
	}

	@Override
	public List<FileLoad>  saveGroupForPath(FileLoad gop, String path) {
		// TODO Auto-generated method stub
		Session openSession= ss.session().openSession();
		Transaction tr = openSession.beginTransaction();
		
		List<FileLoad> po=openSession.createQuery("from FileLoad where path like :path").setParameter("path","%"+ path+"%").list();
	
		tr.commit();
		openSession.close();
		  
		
		return po;
		
		
	}

	@Override
	public void saveGroupForPath1(List<FileLoad> saveGroupForPath,String groups) {
		// TODO Auto-generated method stub
		List<FileLoad> po1=new ArrayList<>();
	
		
		for(FileLoad asa:saveGroupForPath) {
			Session openSession1= ss.session().openSession();
			Transaction tr1 = openSession1.beginTransaction();
			
			System.out.println(asa.getGroups()+"To są grupy >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			FileLoad fl=new FileLoad();
			 fl.setData(asa.getData());
			 fl.setDates(asa.getDates());
			 fl.setDates1(asa.getDates1());
			 fl.setDocumentCategory(asa.getDocumentCategory());
			 fl.setDocumentTags(asa.getDocumentTags());
			 fl.setFiles(asa.getFiles());
			 fl.setGroups((asa.getGroups().replace(" ", "")+"."+groups.replace(" ", "")).replace(".null", "").replace(" ", ""));
			 fl.setLanguage(asa.getLanguage());
			 fl.setMfile(asa.getMfile());
			 fl.setModified(asa.getModified());
			 fl.setModifiedBy(asa.getModifiedBy());
			 fl.setId(asa.getId());
			 fl.setNavigationTags(asa.getNavigationTags());
			 fl.setNazwaFolderu(asa.getNazwaFolderu());
			 fl.setPath(asa.getPath());
			 fl.setReleaseDate(asa.getReleaseDate());
			 fl.setScope(asa.getScope());
			 fl.setSearchFiles(asa.getSearchFiles());
			 fl.setTitle(asa.getTitle());
			 fl.setUsers(asa.getUsers());
			

			 openSession1.saveOrUpdate(fl);
			 tr1.commit();
			 openSession1.close();
			 
			
			
		}
		
		
		
	}

	@Override
	public void delGroupFromPath(List<FileLoad>  groups, FileLoad path) {
		Session openSession= ss.session().openSession();
		Transaction tr = openSession.beginTransaction();
		
		for(FileLoad asa:groups) {
			Session openSession1= ss.session().openSession();
			Transaction tr1 = openSession1.beginTransaction();
			
			System.out.println(asa.getGroups()+"To są grupy1 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			FileLoad fl=new FileLoad();
			 fl.setData(asa.getData());
			 fl.setDates(asa.getDates());
			 fl.setDates1(asa.getDates1());
			 fl.setDocumentCategory(asa.getDocumentCategory());
			 fl.setDocumentTags(asa.getDocumentTags());
			 fl.setFiles(asa.getFiles());
			 fl.setGroups(asa.getGroups().replace(" ", "").replace("."+path.getGroups().replace(" ", ""), ""));
			 
			 fl.setGroups(fl.getGroups().replace(" ", "").replace("..", "").replace(".null", ""));
			 fl.setGroups(fl.getGroups().replace(" ", "").replace(path.getGroups().replace(" ", ""), ""));
			 fl.setLanguage(asa.getLanguage());
			 fl.setMfile(asa.getMfile());
			 fl.setModified(asa.getModified());
			 fl.setModifiedBy(asa.getModifiedBy());
			 fl.setId(asa.getId());
			 fl.setNavigationTags(asa.getNavigationTags());
			 fl.setNazwaFolderu(asa.getNazwaFolderu());
			 fl.setPath(asa.getPath());
			 fl.setReleaseDate(asa.getReleaseDate());
			 fl.setScope(asa.getScope());
			 fl.setSearchFiles(asa.getSearchFiles());
			 fl.setTitle(asa.getTitle());
			 fl.setUsers(asa.getUsers());
			

			 openSession1.saveOrUpdate(fl);
			 tr1.commit();
			 openSession1.close();
			 
			
			
		}
	
		tr.commit();
		openSession.close();
		  
		
	
		
	}

	@Override
	public void addGrouptoPath(FileLoad fLoad,String path) {
		Session openSession= ss.session().openSession();
		Transaction tr = openSession.beginTransaction();
		
		FileLoad po=(FileLoad)openSession.createQuery("from FileLoad where path like :path").setParameter("path","%"+ path+"%").uniqueResult();
	    po.setGroups(po.getGroups().replace(" ", "")+"."+fLoad.getGroups().replace(" ", ""));
	    openSession.saveOrUpdate(po);
		tr.commit();
		openSession.close();
		  
	}

	@Override
	public void deleteGroupfromPath(FileLoad fLoad, String fl) {
		Session openSession= ss.session().openSession();
		Transaction tr = openSession.beginTransaction();
		
		FileLoad po=(FileLoad)openSession.createQuery("from FileLoad where path like :path").setParameter("path","%"+ fl+"%").uniqueResult();
	    po.setGroups(po.getGroups().replace(" ", "").replace("."+fLoad.getGroups().replace(" ", ""),""));
	    po.setGroups(po.getGroups().replace(" ", "").replace(fLoad.getGroups().replace(" ", ""),""));
	    po.setGroups(po.getGroups().replace(" ", "").replace("..", ""));
	    System.out.println("Usuwanie grupy"+po.getGroups().replace(" ", "").replace("."+fLoad.getGroups(),""));
	    System.out.println(""+fLoad.getGroups());
	    openSession.saveOrUpdate(po);
		tr.commit();
		openSession.close();
		
	}

	@Override
	public void addUserToPath(FileLoad fLoad1, String fl) {
		Session openSession= ss.session().openSession();
		Transaction tr = openSession.beginTransaction();
		
		FileLoad po=(FileLoad)openSession.createQuery("from FileLoad where path like :path").setParameter("path","%"+ fl+"%").uniqueResult();
	    po.setUsers(po.getUsers().replace(" ", "")+"."+fLoad1.getUsers().replace(" ", ""));
	    openSession.saveOrUpdate(po);
		tr.commit();
		openSession.close();
		
	}

	@Override
	public void deleteUSerfromPath1(FileLoad fLoadd1, String fl) {
		Session openSession= ss.session().openSession();
		Transaction tr = openSession.beginTransaction();
		
		FileLoad po=(FileLoad)openSession.createQuery("from FileLoad where path like :path").setParameter("path","%"+ fl+"%").uniqueResult();
	    po.setUsers(po.getUsers().replace(" ", "").replace("."+fLoadd1.getUsers().replace(" ", ""), ""));
	    po.setUsers(po.getUsers().replace(" ", "").replace("..", "").replace(".null", ""));
	    po.setUsers(po.getUsers().replace(" ", "").replace(fLoadd1.getUsers().replace(" ", ""), ""));
	    System.out.println("Usuwanie grupy"+po.getUsers().replace(" ", "").replace("."+fLoadd1.getUsers(),""));
	    System.out.println(""+fLoadd1.getUsers());
	    openSession.saveOrUpdate(po);
		tr.commit();
		openSession.close();
		
	}

	@Override
	public Set<FileLoad> searchFilesUser(Logins logins, FileLoad fLoad) {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();
		System.out.println(logins.getId() + "-Id uzytkownka" + logins);
		List<PrzynaleznoscGrup> st1 = openSession.createQuery("from PrzynaleznoscGrup where iduzytkownika=:id")
				.setParameter("id", logins.getId()).list();
		Set<String> st = new HashSet<>();
		for (PrzynaleznoscGrup ki : st1) {

			st.add(ki.getGrupa().replace(" ", ""));
            
		}

		Set<FileLoad> lo = new HashSet<FileLoad>();

		for (String so : st) {
			List<FileLoad> fl = openSession
					.createQuery("from FileLoad where  Groups like :so and path like :path or Users like :user  and path like :path order by path desc")
					.setParameter("so", "%" + so + "%").setParameter("user", "%" + logins.getLogin().replace(" ", "") + "%")
					.setParameter("path", "%"+fLoad.getSearchFiles()+"%").list();

			
			
			lo.addAll(fl);

			System.out.println(so + "a to Grupa jest"+logins.getLogin().replace(" ", "") +"ssssss"+fLoad.getSearchFiles());
		}

		for (FileLoad pp : lo) {

			System.out.println(pp.getGroups() + "a to Grupa jest i login to :"+logins.getLogin().replace(" ", "") +"ssssss");

		}

		tr.commit();
		openSession.close();

		return lo;
	}

	@Override
	public List<FileLoad> szukaj1(String title, Logins logins) {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();
		System.out.println(logins.getId() + "-Id uzytkownka" + logins);
		List<PrzynaleznoscGrup> st1 = openSession.createQuery("from PrzynaleznoscGrup where iduzytkownika=:id")
				.setParameter("id", logins.getId()).list();
		Set<String> st = new HashSet<>();
		for (PrzynaleznoscGrup ki : st1) {

			st.add(ki.getGrupa().replace(" ", ""));
            
		}

		Set<FileLoad> lo = new HashSet<FileLoad>();

		for (String so : st) {
			List<FileLoad> fl = openSession
					.createQuery("from FileLoad where  Groups like :so and Title like :title or Users like :user  and Title like :title order by path desc")
					.setParameter("so", "%" + so + "%").setParameter("user", "%" + logins.getLogin().replace(" ", "") + "%")
					.setParameter("title", "%"+title+"%").list();

			
			
			lo.addAll(fl);

		}
List<FileLoad>ol1=new ArrayList<FileLoad>(lo);
		
		tr.commit();
		openSession.close();

		return ol1;
	}

	@Override
	public List<FileLoad> getallDocumentCategory1(FileLoad ff, Logins logins) {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();
		System.out.println(logins.getId() + "-Id uzytkownka" + logins);
		List<PrzynaleznoscGrup> st1 = openSession.createQuery("from PrzynaleznoscGrup where iduzytkownika=:id")
				.setParameter("id", logins.getId()).list();
		Set<String> st = new HashSet<>();
		for (PrzynaleznoscGrup ki : st1) {

			st.add(ki.getGrupa().replace(" ", ""));
            
		}

		Set<FileLoad> lo = new HashSet<FileLoad>();

		for (String so : st) {
			List<FileLoad> fl = openSession
					.createQuery("from FileLoad where  Groups like :so and DocumentCategory like :DocumentCategory or Users like :user  and DocumentCategory like :DocumentCategory order by path desc")
					.setParameter("so", "%" + so + "%").setParameter("user", "%" + logins.getLogin().replace(" ", "") + "%")
					.setParameter("DocumentCategory", "%"+ff.getDocumentCategory()+"%").list();
			

			
			
			lo.addAll(fl);

		}
List<FileLoad>ol1=new ArrayList<FileLoad>(lo);
		
		tr.commit();
		openSession.close();
		return ol1;
	}

	@Override
	public List<FileLoad> getAllNavigationTags1(FileLoad ff, Logins logins) {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();
		System.out.println(logins.getId() + "-Id uzytkownka" + logins);
		List<PrzynaleznoscGrup> st1 = openSession.createQuery("from PrzynaleznoscGrup where iduzytkownika=:id")
				.setParameter("id", logins.getId()).list();
		Set<String> st = new HashSet<>();
		for (PrzynaleznoscGrup ki : st1) {

			st.add(ki.getGrupa().replace(" ", ""));
            
		}

		Set<FileLoad> lo = new HashSet<FileLoad>();

		for (String so : st) {
			List<FileLoad> fl = openSession
					.createQuery("from FileLoad where  Groups like :so and NavigationTags like :NavigationTags or Users like :user  and NavigationTags like :NavigationTags order by path desc")
					.setParameter("so", "%" + so + "%").setParameter("user", "%" + logins.getLogin().replace(" ", "") + "%")
					.setParameter("NavigationTags", "%"+ff.getNavigationTags()+"%").list();
			

			
			
			lo.addAll(fl);

		}
List<FileLoad>ol1=new ArrayList<FileLoad>(lo);
		
		tr.commit();
		openSession.close();
		return ol1;
	}

	@Override
	public List<FileLoad> getAllLanguage1(FileLoad ff, Logins logins) {
		Session openSession = ss.session().openSession();
		Transaction tr = openSession.beginTransaction();
		System.out.println(logins.getId() + "-Id uzytkownka" + logins);
		List<PrzynaleznoscGrup> st1 = openSession.createQuery("from PrzynaleznoscGrup where iduzytkownika=:id")
				.setParameter("id", logins.getId()).list();
		Set<String> st = new HashSet<>();
		for (PrzynaleznoscGrup ki : st1) {

			st.add(ki.getGrupa().replace(" ", ""));
            
		}

		Set<FileLoad> lo = new HashSet<FileLoad>();

		for (String so : st) {
			List<FileLoad> fl = openSession
					.createQuery("from FileLoad where  Groups like :so and Language like :Language or Users like :user  and Language like :Language order by path desc")
					.setParameter("so", "%" + so + "%").setParameter("user", "%" + logins.getLogin().replace(" ", "") + "%")
					.setParameter("Language", "%"+ff.getLanguage()+"%").list();
			

			
			
			lo.addAll(fl);

		}
List<FileLoad>ol1=new ArrayList<FileLoad>(lo);
		
		tr.commit();
		openSession.close();
		return ol1;
		
	}}
