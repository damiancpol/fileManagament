package wit.controllers;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import freemarker.template.SimpleDate;
import wit.entities.Docs;
import wit.entities.DocumentCategory;
import wit.entities.Dzial;
import wit.entities.FileLoad;
import wit.entities.Groups;
import wit.entities.Grupa;
import wit.entities.Language;
import wit.entities.Logins;
import wit.entities.NavigationTags;
import wit.entities.Pracownik;
import wit.entities.PrzynaleznoscGrup;
import wit.entities.Scope;
import wit.entities.Stanowisko;
import wit.service.LoginServiceInterface;

@Controller
public class Panel {
	@Autowired
	LoginServiceInterface ls;

	@GetMapping("/panel")
	public String showPanel(Model model, HttpSession session) {
		Logins logins = (Logins) session.getAttribute("checkLogin");

		if (logins.getImie() == null) {
			return "login";

		} else {
			if (logins.getStanowisko().replace("                                     ", "").equals("Administrator")) {
				File[] list;
				FileLoad fLoad = new FileLoad();
				model.addAttribute("fLoad", fLoad);
				String sci = (String) session.getAttribute("sciezkaFolderu1");

				if (sci == null) {

					list = new File(
							"E:\\pliki\\src\\main\\resources\\static\\test\\")
									.listFiles();

				} else {

					list = new File(
							"E:\\pliki\\src\\main\\resources\\static\\test\\"
									+ sci).listFiles();
				}
				for (File lista : list) {

					model.addAttribute("listas", lista.lastModified());

				}
				String path1 = "E:\\pliki\\src\\main\\resources\\static\\";
				model.addAttribute("lp", path1);

				model.addAttribute("list", list);
				session.setAttribute("list", list);
				List<FileLoad> allFileLoad = ls.getAllFileLoad();
				model.addAttribute("allFileLoad", allFileLoad);
				FileLoad flo = new FileLoad();
				model.addAttribute("flo", flo);
				return "panel";
			} else {
				File[] list;
				FileLoad fLoad = new FileLoad();
				model.addAttribute("fLoad", fLoad);
				Set<FileLoad> userAndGroupFiles = ls.UserAndGroupFiles(logins.getLogin(), logins.getId());
				String sci = (String) session.getAttribute("sciezkaFolderu1");

				if (sci == null) {

					list = new File(
							"E:\\pliki\\src\\main\\resources\\static\\test\\")
									.listFiles();

				} else {

					list = new File(
							"E:\\pliki\\src\\main\\resources\\static\\test\\"
									+ sci).listFiles();
				}
				for (File lista : list) {

					model.addAttribute("listas", lista.lastModified());

				}
				String path1 = "E:\\pliki\\src\\main\\resources\\static\\";
				model.addAttribute("lp", path1);

				model.addAttribute(userAndGroupFiles);
				session.setAttribute("list", list);
				List<FileLoad> allFileLoad = ls.getAllFileLoad();
				model.addAttribute("allFileLoad", allFileLoad);
				return "panel2";
			}
		}
	}

	@GetMapping("NazwaFolderu")
	public String showFolder(@RequestParam("nazwafolderu") String sciezkaFolderu, Model model, HttpSession session)
			throws ParseException {
		Logins logins = (Logins) session.getAttribute("checkLogin");

		if (logins == null) {
			return "login";

		} else {
			if (logins.getStanowisko().replace("                                     ", "").equals("Administrator")) {
				List<Docs> allDosc = ls.getAllDosc();

				File file = new File(sciezkaFolderu);

				File file2 = new File(file.toString().replace(file.getName(), ""));

				model.addAttribute("pathToFolder", file.toString().replace(
						"E:\\pliki\\src\\main\\resources\\static\\test\\apka",
						""));
				FileLoad filel = new FileLoad();
				File[] listF = file.listFiles();
				filel.setFiles(file.listFiles());

				FileLoad fLoad = new FileLoad();
				model.addAttribute("fLoad", fLoad);

				model.addAttribute("list", listF);

				List<FileLoad> allFileLoad = ls.getAllFileLoad();

				session.setAttribute("sciezkaFolderu1", sciezkaFolderu);
				session.setAttribute("fLoad", fLoad);
				FileLoad flo = new FileLoad();
				model.addAttribute("flo", flo);
				int liczba = allFileLoad.size();
				model.addAttribute("liczba", liczba);
				System.out.println("To jest liczba" + liczba);

				Set<FileLoad> flo1 = new HashSet<>();

				for (FileLoad ld : allFileLoad) {
					FileLoad fl = new FileLoad();

					fl.setData(ld.getData());
					fl.setDates(ld.getDates());
					fl.setDates1(ld.getDates1());
					fl.setDocumentCategory(ld.getDocumentCategory());
					fl.setDocumentTags(fl.getDocumentTags());
					fl.setFiles(ld.getFiles());
					fl.setGroups(ld.getGroups());
					fl.setId(ld.getId());
					fl.setLanguage(ld.getLanguage());
					fl.setMfile(ld.getMfile());
					fl.setModified(ld.getModified());
					fl.setModifiedBy(ld.getModifiedBy());
					fl.setNavigationTags(ld.getNavigationTags());
					fl.setNazwaFolderu(ld.getNazwaFolderu());
					fl.setPath(ld.getPath());
					fl.setReleaseDate(ld.getReleaseDate());
					fl.setScope(ld.getScope());
					fl.setSearchFiles(ld.getSearchFiles());
					fl.setTitle(ld.getTitle());

					flo1.add(fl);

				}
				Set<String> limit = new HashSet<>();

				for (FileLoad pr : allFileLoad) {

					limit.add(pr.getPath());

				}
				model.addAttribute("limit", limit.size());

				model.addAttribute("allFileLoad", flo1);

				return "panel";
			}
			if (!logins.getStanowisko().equals("Administrator"))

			{
				List<Docs> allDosc = ls.getAllDosc();
				Set<FileLoad> userAndGroupFiles = ls.UserAndGroupFiles(logins.getLogin(), logins.getId());
				File file = new File(sciezkaFolderu);
				File file2 = new File(file.toString().replace(file.getName(), ""));

				model.addAttribute("pathToFolder", file.toString().replace(
						"E:\\pliki\\src\\main\\resources\\static\\test\\apka",
						""));

				FileLoad filel = new FileLoad();
				File[] listF = file.listFiles();
				filel.setFiles(file.listFiles());
				List<File> listo = new ArrayList<>();
				for (FileLoad ss : userAndGroupFiles) {

					listo.add(new File(ss.getPath()));
				}

				FileLoad fLoad = new FileLoad();
				model.addAttribute("fLoad", fLoad);

				model.addAttribute("list", listo);

				List<FileLoad> allFileLoad = ls.getAllFileLoad();
				Set<FileLoad> pa = new HashSet<FileLoad>(allFileLoad);
				model.addAttribute("allFileLoad", pa);

				session.setAttribute("sciezkaFolderu1", sciezkaFolderu);
				session.setAttribute("fLoad", fLoad);
				FileLoad flo = new FileLoad();
				model.addAttribute("flo", flo);

				return "panel2";
			}
			return sciezkaFolderu;
		}
	}

	@GetMapping("NazwaFolderuS")
	public String showFolderS(@RequestParam("nazwafolderu") String sciezkaFolderu, Model model, HttpSession session)
			throws ParseException {
		Logins logins = (Logins) session.getAttribute("checkLogin");

		if (logins == null) {
			return "login";

		} else if (logins.getStanowisko().replace("                                     ", "")
				.equals("Administrator")) {
			List<Docs> allDosc = ls.getAllDosc();

			File file = new File(sciezkaFolderu);
			File file2 = new File(file.toString().replace(file.getName(), ""));

			model.addAttribute("pathToFolder", file.toString().replace(
					"E:\\pliki\\src\\main\\resources\\static\\test\\apka",
					""));
			FileLoad filel = new FileLoad();
			File[] listF = file.listFiles();
			filel.setFiles(file.listFiles());

			FileLoad fLoad = new FileLoad();
			model.addAttribute("fLoad", fLoad);

			model.addAttribute("list", listF);

			session.setAttribute("sciezkaFolderu1", sciezkaFolderu);
			session.setAttribute("fLoad", fLoad);

			List<FileLoad> allFileLoad = ls.getAllFileLoad();
			model.addAttribute("allFileLoad", allFileLoad);
			FileLoad flo = new FileLoad();
			model.addAttribute("flo", flo);

			return "panel";
		} else {
			List<Docs> allDosc = ls.getAllDosc();

			File file = new File(sciezkaFolderu);
			File file2 = new File(file.toString().replace(file.getName(), ""));

			model.addAttribute("pathToFolder", file.toString().replace(
					"E:\\pliki\\src\\main\\resources\\static\\test\\apka",
					""));
			FileLoad filel = new FileLoad();
			File[] listF = file.listFiles();
			filel.setFiles(file.listFiles());

			FileLoad fLoad = new FileLoad();
			model.addAttribute("fLoad", fLoad);

			model.addAttribute("list", listF);

			session.setAttribute("sciezkaFolderu1", sciezkaFolderu);
			session.setAttribute("fLoad", fLoad);

			List<FileLoad> allFileLoad = ls.getAllFileLoad();
			model.addAttribute("allFileLoad", allFileLoad);

			return "panel2";
		}
	}

	@GetMapping("NazwaFolderu1")
	public String showFolder1(Model model, HttpSession session) {

		return "NazwaFolderu";
	}

	@PostMapping("loadfile")
	public String lodadFile(@ModelAttribute("fLoad") FileLoad fLoad, HttpSession session, Model model)
			throws FileNotFoundException, IOException, MessagingException {
		Logins logins = (Logins) session.getAttribute("checkLogin");
		List<String> paths = new ArrayList<>();
		if (logins == null) {
			return "login";

		} else if (logins.getStanowisko().replace("                                     ", "")
				.equals("Administrator")) {

			List<MultipartFile> mfile = fLoad.getMfile();
			for (MultipartFile fl : mfile) {

				fl.transferTo(new File(fLoad.getPath() + "\\" + fl.getOriginalFilename()));

				paths.add(fLoad.getPath() + "\\" + fl.getOriginalFilename());
				;
				fLoad.setModifiedBy(logins.getLogin());
				fLoad.setPaths(paths);

			}
			ls.saveFileLoad(fLoad);

			String name = new File(fLoad.getPath()).getName();
			File[] listF = new File(fLoad.getPath().replace(name, "")).listFiles();
			model.addAttribute("list", listF);
			FileLoad fLoad1 = (FileLoad) session.getAttribute("fLoad");
			fLoad.setPath(fLoad.getPath().replace(name,
					"E:\\pliki\\src\\main\\resources\\static\\"));

			model.addAttribute("fLoad", fLoad);
			List<FileLoad> allFileLoad = ls.getAllFileLoad();
			Set<FileLoad> pp = new HashSet<FileLoad>(allFileLoad);
			model.addAttribute("allFileLoad", pp);
			FileLoad flo = new FileLoad();
			model.addAttribute("flo", flo);
			Set<FileLoad> allGroupsbyPath = ls.getAllGroupsbyPath(fLoad.getPath());
			model.addAttribute("allGroupsbyPath", allGroupsbyPath);

			return "panel";
		} else {

			return "panel2";
		}

	}

	@PostMapping("loadfile1")
	public String backlodadFile(@ModelAttribute("fLoad") FileLoad fLoad, HttpSession session, Model model)
			throws FileNotFoundException, IOException, MessagingException {
		Logins logins = (Logins) session.getAttribute("checkLogin");

		if (logins == null) {
			return "login";

		} else if (logins.getStanowisko().replace("                                     ", "")
				.equals("Administrator")) {

			List<MultipartFile> mfile = fLoad.getMfile();
			for (MultipartFile fl : mfile) {

				fl.transferTo(new File(fLoad.getPath() + "\\" + fl.getOriginalFilename()));

			}
			List<FileLoad> allFileLoad = ls.getAllFileLoad();
			model.addAttribute("allFileLoad", allFileLoad);
			FileLoad flo = new FileLoad();
			model.addAttribute("flo", flo);
			return "panel";
		} else {

			List<MultipartFile> mfile = fLoad.getMfile();
			for (MultipartFile fl : mfile) {

				fl.transferTo(new File(fLoad.getPath() + "\\" + fl.getOriginalFilename()));

			}
			List<FileLoad> allFileLoad = ls.getAllFileLoad();
			model.addAttribute("allFileLoad", allFileLoad);
			return "panel2";

		}

	}

	@PostMapping("mkdir")
	public String utworzFolder(@ModelAttribute("fLoad") FileLoad fl, HttpSession session, Model model) {
		Logins logins = (Logins) session.getAttribute("checkLogin");

		if (logins == null) {
			return "login";

		} else if (logins.getStanowisko().replace("                                     ", "")
				.equals("Administrator")) {
			fl.setPath1(fl.getPath() + "\\" + fl.getNazwaFolderu());
			new File(fl.getPath() + "\\" + fl.getNazwaFolderu()).mkdir();
			File file = new File(fl.getPath());
			model.addAttribute("pathToFolder", file.toString().replace("test", ""));
			File[] listF = file.listFiles();
			model.addAttribute("list", listF);
			fl.setPath("");
			model.addAttribute("fLoad", fl);

			ls.saveDir(fl);
			List<FileLoad> allFileLoad = ls.getAllFileLoad();
			model.addAttribute("allFileLoad", allFileLoad);
			FileLoad flo = new FileLoad();
			model.addAttribute("flo", flo);
			return "panel";
		} else {
			fl.setPath1(fl.getPath() + "\\" + fl.getNazwaFolderu());
			new File(fl.getPath() + "\\" + fl.getNazwaFolderu()).mkdir();
			File file = new File(fl.getPath());
			model.addAttribute("pathToFolder", file.toString().replace("test", ""));
			File[] listF = file.listFiles();
			model.addAttribute("list", listF);
			fl.setPath("");
			model.addAttribute("fLoad", fl);

			ls.saveDir(fl);
			List<FileLoad> allFileLoad = ls.getAllFileLoad();
			model.addAttribute("allFileLoad", allFileLoad);

			return "panel2";
		}

	}

	@PostMapping("delDir")
	public String usunFolder(@ModelAttribute("fLoad") FileLoad fl, HttpSession session, Model model) {
		Logins logins = (Logins) session.getAttribute("checkLogin");

		if (logins == null) {
			return "login";

		} else if (logins.getStanowisko().replace("                                     ", "")
				.equals("Administrator")) {

			File file = new File(fl.getPath());
			if (file.isDirectory()) {

				try {
					FileUtils.deleteDirectory(file);
				} catch (IOException e) {
					e.getMessage();
					e.printStackTrace();
				}

			} else {

				file.delete();

			}

			String name = new File(fl.getPath()).getName();

			File[] listF = new File(fl.getPath().replace(name, "")).listFiles();
			model.addAttribute("list", listF);

			model.addAttribute("fLoad", fl);
			model.addAttribute("pathToFolder", fl.getPath().replace(name, "").replace(
					"E:\\pliki\\src\\main\\resources\\static\\test\\apka\\",
					""));
			ls.delDir(fl);
			List<FileLoad> allFileLoad = ls.getAllFileLoad();
			model.addAttribute("allFileLoad", allFileLoad);
			FileLoad flo = new FileLoad();
			model.addAttribute("flo", flo);
			return "panel";
		}
		{
			File file = new File(fl.getPath());
			if (file.isDirectory()) {

				try {
					FileUtils.deleteDirectory(file);
				} catch (IOException e) {
					e.getMessage();
					e.printStackTrace();
				}

			} else {

				file.delete();

			}

			String name = new File(fl.getPath()).getName();

			File[] listF = new File(fl.getPath().replace(name, "")).listFiles();
			model.addAttribute("list", listF);

			model.addAttribute("fLoad", fl);
			model.addAttribute("pathToFolder", fl.getPath().replace(name, "").replace(
					"E:\\pliki\\src\\main\\resources\\static\\test\\apka\\",
					""));
			ls.delDir(fl);
			List<FileLoad> allFileLoad = ls.getAllFileLoad();
			model.addAttribute("allFileLoad", allFileLoad);

			return "panel2";
		}
	}

	@PostMapping("Explorer")
	public String exploreFiles(@ModelAttribute("fLoad") FileLoad fLoad, HttpSession session, Model model)
			throws IOException {
		Logins logins = (Logins) session.getAttribute("checkLogin");

		if (logins == null) {
			return "login";

		} else if (logins.getStanowisko().replace("                                     ", "")
				.equals("Administrator")) {

			// Runtime.getRuntime().exec("explorer.exe /select," + fLoad.getPath());
			String name = new File(fLoad.getPath()).getName();
			Runtime.getRuntime().exec("explorer.exe /select," + fLoad.getPath().trim());

			File[] listF = new File(fLoad.getPath().replace(name, "")).listFiles();
			model.addAttribute("list", listF);
			FileLoad fLoad1 = (FileLoad) session.getAttribute("fLoad");
			fLoad.setPath(fLoad.getPath().replace(name, ""));

			model.addAttribute("fLoad", fLoad);
			List<FileLoad> allFileLoad = ls.getAllFileLoad();
			model.addAttribute("allFileLoad", allFileLoad);
			FileLoad flo = new FileLoad();
			model.addAttribute("flo", flo);
			return "panel";
		}

		else {
			// Runtime.getRuntime().exec("explorer.exe /select," + fLoad.getPath());
			String name = new File(fLoad.getPath()).getName();
			Runtime.getRuntime().exec("explorer.exe /select," + fLoad.getPath().trim());

			File[] listF = new File(fLoad.getPath().replace(name, "")).listFiles();
			model.addAttribute("list", listF);
			FileLoad fLoad1 = (FileLoad) session.getAttribute("fLoad");
			fLoad.setPath(fLoad.getPath().replace(name, ""));

			model.addAttribute("fLoad", fLoad);
			List<FileLoad> allFileLoad = ls.getAllFileLoad();
			model.addAttribute("allFileLoad", allFileLoad);

			return "panel2";
		}

	}

	@GetMapping("Up")
	public String up(@RequestParam("up") String up, HttpSession session, Model model) {
		Logins logins = (Logins) session.getAttribute("checkLogin");
		if (logins == null) {
			return "login";

		} else if (logins.getStanowisko().replace("                                     ", "")
				.equals("Administrator")) {

			File file = new File(up);
			String replace = up.replace("\\" + file.getName(), "");
			File file2 = new File(replace);
			String replace2 = replace.replace("\\" + file2.getName(), "");
			File[] listF = new File(replace2).listFiles();
			FileLoad fLoad = new FileLoad();
			model.addAttribute("fLoad", fLoad);

			model.addAttribute("list", listF);
			model.addAttribute("pathToFolder", replace2.replace("test\\apka", ""));
			List<FileLoad> allFileLoad = ls.getAllFileLoad();
			model.addAttribute("allFileLoad", allFileLoad);
			FileLoad flo = new FileLoad();
			model.addAttribute("flo", flo);
			return "panel";
		} else {
			File file = new File(up);
			String replace = up.replace("\\" + file.getName(), "");
			File file2 = new File(replace);
			String replace2 = replace.replace("\\" + file2.getName(), "");
			File[] listF = new File(replace2).listFiles();
			FileLoad fLoad = new FileLoad();
			model.addAttribute("fLoad", fLoad);

			model.addAttribute("list", listF);
			model.addAttribute("pathToFolder", replace2.replace("test\\apka", ""));
			List<FileLoad> allFileLoad = ls.getAllFileLoad();
			model.addAttribute("allFileLoad", allFileLoad);

			return "panel2";
		}

	}

	@PostMapping("searchFile")
	public String findFile(Model model, @ModelAttribute("fLoad") FileLoad fLoad, HttpSession session) {
		Logins logins = (Logins) session.getAttribute("checkLogin");

		if (logins == null) {
			return "login";

		} else if (logins.getStanowisko().replace("                                     ", "")
				.equals("Administrator")) {

			String path = "E:\\pliki\\src\\main\\resources\\static\\test\\apka\\";
			String nazwa = fLoad.getSearchFiles();

			File[] files = new File(path).listFiles();
			List<File> fl = new ArrayList<>();
			List<FileLoad> searchFiles = ls.searchFiles(fLoad);
			searchFile(path, model, nazwa);
			List<FileLoad> allFileLoad = ls.getAllFileLoad();
			model.addAttribute("allFileLoad", searchFiles);
			FileLoad flo = new FileLoad();
			model.addAttribute("flo", flo);

			return "panel";

		} else {
			FileLoad flo = new FileLoad();
			model.addAttribute("flo", flo);
			Set<FileLoad> searchFilesUser = ls.searchFilesUser(logins, fLoad);
			Set<FileLoad> searchFilesUser1=new HashSet<FileLoad>();
			for(FileLoad ld: searchFilesUser ) {
				FileLoad fl = new FileLoad();

				fl.setData(ld.getData());
				fl.setDates(ld.getDates());
				fl.setDates1(ld.getDates1());
				fl.setDocumentCategory(ld.getDocumentCategory());
				fl.setDocumentTags(fl.getDocumentTags());
				fl.setFiles(ld.getFiles());
				fl.setGroups(ld.getGroups());
				fl.setId(ld.getId());
				fl.setLanguage(ld.getLanguage());
				fl.setMfile(ld.getMfile());
				fl.setModified(ld.getModified());
				fl.setModifiedBy(ld.getModifiedBy());
				fl.setNavigationTags(ld.getNavigationTags());
				fl.setNazwaFolderu(ld.getNazwaFolderu());
				fl.setPath(ld.getPath());
				fl.setReleaseDate(ld.getReleaseDate());
				fl.setScope(ld.getScope());
				fl.setSearchFiles(ld.getSearchFiles());
				fl.setTitle(ld.getTitle());
				fl.setPath1(new File(ld.getPath()).getName());
				searchFilesUser1.add(fl);
				
			}
			
			
			model.addAttribute("list", searchFilesUser1);
			return "panel2";

		}
	}

	public void searchFile(String files, Model model, String nazwa) {

		File[] file = new File(files).listFiles();
		List<File> fl = new ArrayList<>();

		for (File fo : file) {

			if (fo.toString().replace(
					"E:\\pliki\\src\\main\\resources\\static\\test\\apka\\",
					"").contains(".")) {
				Pattern compile = Pattern.compile(nazwa);
				Matcher matcher = compile.matcher(fo.toString());
				if (matcher.find()) {
					fl.add(fo);
					model.addAttribute("list", fl);

					;
				} else if ((fo.toString().replace("\\test\\apka\\", "").equals(nazwa))) {

					fl.add(fo);
					model.addAttribute("list", fl);

				} else if (fo.toString().replace(
						"E:\\pliki\\src\\main\\resources\\static\\test\\apka\\",
						"").startsWith(nazwa)) {

					fl.add(fo);
					model.addAttribute("list", fl);

				} else if (fo.toString().replace(
						"E:\\pliki\\src\\main\\resources\\static\\test\\apka\\",
						"").endsWith(nazwa)) {

					fl.add(fo);
					model.addAttribute("list", fl);

				}

			}

			else if (fo.toString().replace(
					"E:\\pliki\\src\\main\\resources\\static\\test\\apka\\",
					"").contains(nazwa)) {

				fl.add(fo);
				model.addAttribute("list", fl);

			} else {

				searchFile(fo.toString(), model, nazwa);

			}

		}

		for (File fs : file) {

		}

	}

	@GetMapping("login")
	public String login(Model model, HttpSession session) {

		session.removeAttribute("checkLogin");
		Logins login = new Logins();
		model.addAttribute("login", login);

		List<FileLoad> allFileLoad = ls.getAllFileLoad();
		model.addAttribute("allFileLoad", allFileLoad);
		return "login";
	}

	@PostMapping("check")
	public String checkUser(@ModelAttribute("login") Logins login, Model model, HttpSession session) {

		Logins checkLogin = ls.checkLogin(login);
		if (checkLogin == null) {

			model.addAttribute("error", "Nie ma takiego użytkownika");
			return "login";
		}
		if (checkLogin.getStanowisko().replace("                                     ", "").equals("Administrator")) {
			File[] list;
			FileLoad flo = new FileLoad();
			FileLoad fLoad = new FileLoad();
			model.addAttribute("fLoad", fLoad);
			list = new File(
					"E:\\pliki\\src\\main\\resources\\static\\test")
							.listFiles();
			model.addAttribute("flo", flo);
			model.addAttribute("list", list);
			session.setAttribute("list", list);
			session.setAttribute("checkLogin", checkLogin);

			List<FileLoad> allFileLoad = ls.getAllFileLoad();

			model.addAttribute("allFileLoad", allFileLoad);

			return "panel";

		}
		if (checkLogin.getStanowisko() != ("Administrator")) {

			FileLoad flo = new FileLoad();
			FileLoad fLoad = new FileLoad();
			model.addAttribute("fLoad", fLoad);
			model.addAttribute("flo", flo);
			session.setAttribute("checkLogin", checkLogin);

			Set<File> fi = new HashSet<>();
			TreeSet<File> tr = new TreeSet<>();
			File[] pi;
			Set<FileLoad> userAndGroupFiles = ls.UserAndGroupFiles(checkLogin.getLogin().replace(" ", ""),
					checkLogin.getId());

			Set<FileLoad> userAndGroupFiles1 = new HashSet<>();

			for (FileLoad uf : userAndGroupFiles) {
				uf.setPath1(new File(uf.getPath()).getName());
				userAndGroupFiles1.add(uf);

			}

			model.addAttribute("list", userAndGroupFiles1);

			return "panel2";
		}

		return "";
	}

	@GetMapping("check1")
	public String checkUse1r(Model model, HttpSession session) {
		Logins checklogin = (Logins) session.getAttribute("checkLogin");

		if (checklogin == null) {

			model.addAttribute("error", "Nie ma takiego użytkownika");
			return "login";

		} else if (checklogin.getStanowisko().replace("                                     ", "")
				.equals("Administrator")) {

			File[] list;
			FileLoad fLoad = new FileLoad();
			model.addAttribute("fLoad", fLoad);

			list = new File(
					"E:\\pliki\\src\\main\\resources\\static\\test\\")
							.listFiles();

			model.addAttribute("list", list);
			session.setAttribute("list", list);
			List<FileLoad> allFileLoad = ls.getAllFileLoad();
			model.addAttribute("allFileLoad", allFileLoad);

			FileLoad flo = new FileLoad();
			model.addAttribute("flo", flo);
			return "panel";

		}

		else {
			File[] list;
			FileLoad fLoad = new FileLoad();
			model.addAttribute("fLoad", fLoad);

			list = new File(
					"E:\\pliki\\src\\main\\resources\\static\\test\\")
							.listFiles();

			model.addAttribute("list", list);
			session.setAttribute("list", list);
			List<FileLoad> allFileLoad = ls.getAllFileLoad();
			model.addAttribute("allFileLoad", allFileLoad);

			return "panel2";
		}

	}

	@GetMapping("Logout")
	public String logout(Model model, HttpSession session) {

		session.removeAttribute("checkLogin");

		Logins login = new Logins();
		model.addAttribute("login", login);

		return "login";
	}

	@GetMapping("zawI")
	public String zawansowane(@RequestParam("zawansowane") String fl, Model model, HttpSession session) {
		model.addAttribute("i", fl);
		FileLoad fLoad = new FileLoad();
		model.addAttribute("fLoad", fLoad);
		FileLoad fLoad1 = new FileLoad();
		model.addAttribute("fLoad1", fLoad1);
		Docs docs = new Docs();
		model.addAttribute("docs", docs);

		Grupa gop = new Grupa();
		model.addAttribute("gop", gop);

		List<Docs> allDosc = ls.getAllDosc();
		model.addAttribute("allDosc", allDosc);
		session.setAttribute("fl", fl);
		List<DocumentCategory> getallDocumentCategory = ls.getallDocumentCategory();
		List<NavigationTags> allNavigationTags = ls.getAllNavigationTags();
		List<Language> allLanguages = ls.getAllLanguages();
		List<Scope> getallScopes = ls.getallScopes();
		List<Logins> allFileUsers = ls.getAllFileUsers();
		List<Grupa> allGroups = ls.getAllGrupa();
		model.addAttribute("getallDocumentCategory", getallDocumentCategory);
		model.addAttribute("allNavigationTags", allNavigationTags);
		model.addAttribute("allLanguages", allLanguages);
		model.addAttribute("getallScopes", getallScopes);
		model.addAttribute("allFileUsers", allFileUsers);
		model.addAttribute("allGroups", allGroups);
		List<FileLoad> allFileLoad = ls.getAllFileLoad();
		model.addAttribute("allFileLoad", allFileLoad);

		System.out.println("To jest ta ścieżka" + fl);
		Set<FileLoad> allGroupsbyPath = ls.getAllGroupsbyPath(fl);

		model.addAttribute("allGroupsbyPath", allGroupsbyPath);

		Set<String> mm = new HashSet<>();
		for (FileLoad dp : allGroupsbyPath) {

			mm.add(dp.getGroups());

		}
		List<FileLoad> allUsersbyPath = ls.getAllUsersbyPath(fl);

		Set<String> ko = new HashSet<>();
		for (FileLoad pu : allUsersbyPath) {

			ko.add(pu.getUsers());
		}

		model.addAttribute("ko", ko);
		model.addAttribute("mm", mm);

		return "zawansowane";
	}

	@GetMapping("zawI1")
	public String zawansowane1(@RequestParam("zawansowane1") String fl, Model model, HttpSession sesion) {
		sesion.setAttribute("pathfl", fl);
		model.addAttribute("i", fl);
		List<FileLoad> allUsersbyPath = ls.getAllUsersbyPath(fl);
		Set<FileLoad> allGroupsbyPath = ls.getAllGroupsbyPath(fl);
		model.addAttribute("allFileUsers", allUsersbyPath);
		model.addAttribute("allGroups", allGroupsbyPath);
		List<Logins> allFileUsers = ls.getAllFileUsers();
		model.addAttribute("allFileUsers", allFileUsers);
		List<Grupa> allGrupa = ls.getAllGrupa();
		model.addAttribute("allGrupa", allGrupa);
		FileLoad fLoad = new FileLoad();
		model.addAttribute("fLoad", fLoad);

		FileLoad fLoad1 = new FileLoad();
		model.addAttribute("fLoad1", fLoad1);

		model.addAttribute("allGroupsbyPath", allGroupsbyPath);

		Set<String> mm = new HashSet<>();
		for (FileLoad dp : allGroupsbyPath) {

			mm.add(dp.getGroups());

		}

		Set<String> ko = new HashSet<>();
		for (FileLoad pu : allUsersbyPath) {

			ko.add(pu.getUsers());
		}
		model.addAttribute("ko", ko);
		model.addAttribute("mm", mm);

		return "zawansowane1";
	}

	@GetMapping("newOptions")
	public String newOption(Model model) {
		FileLoad fLoad = new FileLoad();
		Docs docs = new Docs();
		model.addAttribute("docs", docs);
		Language language = new Language();
		Scope scope = new Scope();
		DocumentCategory documentCategory = new DocumentCategory();
		NavigationTags navigationTags = new NavigationTags();
		model.addAttribute("Scope", scope);
		model.addAttribute("DocumentCategory", documentCategory);
		model.addAttribute("NavigationTags", navigationTags);
		model.addAttribute("Language", language);
		model.addAttribute("fLoad", fLoad);
		List<FileLoad> allFileLoad = ls.getAllFileLoad();
		model.addAttribute("allFileLoad", allFileLoad);
		return "newOption";

	}

	@PostMapping("saveNewSetting")
	public String saveSetting(@ModelAttribute("Scope") Scope scope1, Model model) {
		Language language = new Language();
		Scope scope = new Scope();
		DocumentCategory documentCategory = new DocumentCategory();
		NavigationTags navigationTags = new NavigationTags();
		model.addAttribute("Scope", scope);
		model.addAttribute("DocumentCategory", documentCategory);
		model.addAttribute("NavigationTags", navigationTags);
		model.addAttribute("Language", language);
		List<FileLoad> allFileLoad = ls.getAllFileLoad();
		model.addAttribute("allFileLoad", allFileLoad);
		ls.saveScope(scope1);

		return "newOption";
	}

	@PostMapping("saveNewSetting1")
	public String saveSetting1(@ModelAttribute("DocumentCategory") DocumentCategory documentCategory1, Model model) {
		Language language = new Language();
		Scope scope = new Scope();
		DocumentCategory documentCategory = new DocumentCategory();
		NavigationTags navigationTags = new NavigationTags();
		model.addAttribute("Scope", scope);
		model.addAttribute("DocumentCategory", documentCategory);
		model.addAttribute("NavigationTags", navigationTags);
		model.addAttribute("Language", language);
		List<FileLoad> allFileLoad = ls.getAllFileLoad();
		model.addAttribute("allFileLoad", allFileLoad);
		ls.saveDocumentCategory(documentCategory1);

		return "newOption";
	}

	@PostMapping("saveNewSetting2")
	public String saveSetting2(@ModelAttribute("NavigationTags") NavigationTags navigationTags1, Model model) {
		Language language = new Language();
		Scope scope = new Scope();
		DocumentCategory documentCategory = new DocumentCategory();
		NavigationTags navigationTags = new NavigationTags();
		model.addAttribute("Scope", scope);
		model.addAttribute("DocumentCategory", documentCategory);
		model.addAttribute("NavigationTags", navigationTags);
		model.addAttribute("Language", language);
		List<FileLoad> allFileLoad = ls.getAllFileLoad();
		model.addAttribute("allFileLoad", allFileLoad);
		ls.saveNavigationTags(navigationTags1);

		return "newOption";
	}

	@PostMapping("saveNewSetting3")
	public String saveSetting3(@ModelAttribute("Language") Language language1, Model model) {
		Language language = new Language();
		Scope scope = new Scope();
		DocumentCategory documentCategory = new DocumentCategory();
		NavigationTags navigationTags = new NavigationTags();
		model.addAttribute("Scope", scope);
		model.addAttribute("DocumentCategory", documentCategory);
		model.addAttribute("NavigationTags", navigationTags);
		model.addAttribute("Language", language);
		List<FileLoad> allFileLoad = ls.getAllFileLoad();
		model.addAttribute("allFileLoad", allFileLoad);
		ls.saveLanguage(language1);

		return "newOption";
	}

	@GetMapping("AddUser")
	public String AddUser(Model model) {

		List<Grupa> allGroups = ls.getAllGrupa();
		Stanowisko stanowisko = new Stanowisko();
		model.addAttribute("stanowisko", stanowisko);
		Logins logins = new Logins();
		Grupa groups = new Grupa();
		model.addAttribute("groups", groups);
		model.addAttribute("logins", logins);

		model.addAttribute("allGroups", allGroups);
		List<FileLoad> allFileLoad = ls.getAllFileLoad();
		model.addAttribute("allFileLoad", allFileLoad);
		List<Stanowisko> allStanowisko = ls.getAllStanowisko();
		model.addAttribute("allStanowisko", allStanowisko);

		List<Logins> allFileUsers = ls.getAllFileUsers();

		model.addAttribute("allFileUsers", allFileUsers);

		return "addUser";

	}

	@PostMapping("saveUSer")
	public String saveUSer(@ModelAttribute("logins") Logins logins, Model model) {

		ls.saveLoginss(logins);
		List<Grupa> allGroups = ls.getAllGrupa();
		Stanowisko stanowisko = new Stanowisko();
		model.addAttribute("stanowisko", stanowisko);
		Logins logins1 = new Logins();
		Grupa groups = new Grupa();
		model.addAttribute("groups", groups);
		model.addAttribute("logins", logins1);
		model.addAttribute("allGroups", allGroups);
		List<FileLoad> allFileLoad = ls.getAllFileLoad();
		model.addAttribute("allFileLoad", allFileLoad);
		List<Stanowisko> allStanowisko = ls.getAllStanowisko();
		model.addAttribute("allStanowisko", allStanowisko);

		return "redirect:AddUser";

	}

	@PostMapping("Addgroup")
	public String Addgroup(@ModelAttribute("groups") Grupa groups, Model model) {
		ls.saveGroup(groups);
		List<Grupa> allGroups = ls.getAllGrupa();
		List<Stanowisko> allStanowisko = ls.getAllStanowisko();
		model.addAttribute("allStanowisko", allStanowisko);
		Logins logins1 = new Logins();
		Grupa groups1 = new Grupa();
		model.addAttribute("groups", groups1);
		model.addAttribute("logins", logins1);
		model.addAttribute("allGroups", allGroups);

		List<FileLoad> allFileLoad = ls.getAllFileLoad();
		model.addAttribute("allFileLoad", allFileLoad);

		return "redirect:AddUser";
	}

	@PostMapping("Addstanowisko")
	public String Addstanowisko(@ModelAttribute("stanowisko") Stanowisko stanowisko1, Model model) {
		Stanowisko stanowisko = new Stanowisko();
		model.addAttribute("stanowisko", stanowisko);
		List<Stanowisko> allStanowisko = ls.getAllStanowisko();
		model.addAttribute("allStanowisko", allStanowisko);
		List<Grupa> allGroups = ls.getAllGrupa();
		ls.saveStanowisko(stanowisko1);
		model.addAttribute("stanowisko", stanowisko);
		Logins logins1 = new Logins();
		Grupa groups1 = new Grupa();
		model.addAttribute("groups", groups1);
		model.addAttribute("logins", logins1);
		model.addAttribute("allGroups", allGroups);
		List<FileLoad> allFileLoad = ls.getAllFileLoad();
		model.addAttribute("allFileLoad", allFileLoad);

		return "redirect:AddUser";
	}

	@PostMapping("searches")
	public String searches(Model model, @ModelAttribute("flo") FileLoad ff) {
		FileLoad flo = new FileLoad();
		model.addAttribute("flo", flo);
		List<FileLoad> szukaj = ls.szukaj(ff.getTitle());
		List<File> file = new ArrayList<>();
		for (FileLoad sw : szukaj) {
			file.add(new File(sw.getPath()));

		}
		model.addAttribute("list", file);
		FileLoad fLoad = new FileLoad();
		model.addAttribute("fLoad", fLoad);
		List<FileLoad> allFileLoad = ls.getAllFileLoad();

		model.addAttribute("allFileLoad", allFileLoad);

		return "panel";

	}

	@PostMapping("searches1")
	public String searches1(Model model, @ModelAttribute("flo") FileLoad ff) {
		FileLoad flo = new FileLoad();
		model.addAttribute("flo", flo);
		List<FileLoad> documentCategory = ls.DocumentCategory(ff.getDocumentCategory());
		List<File> file = new ArrayList<>();
		for (FileLoad sw : documentCategory) {
			file.add(new File(sw.getPath()));

		}
		model.addAttribute("list", file);
		FileLoad fLoad = new FileLoad();
		model.addAttribute("fLoad", fLoad);
		List<FileLoad> allFileLoad = ls.getAllFileLoad();

		model.addAttribute("allFileLoad", allFileLoad);
		return "panel";

	}

	@PostMapping("searches2")
	public String searches2(Model model, @ModelAttribute("flo") FileLoad ff) {
		FileLoad flo = new FileLoad();
		model.addAttribute("flo", flo);
		List<FileLoad> navigationTags = ls.getNavigationTags(ff.getNavigationTags());
		List<File> file = new ArrayList<>();
		for (FileLoad sw : navigationTags) {
			file.add(new File(sw.getPath()));

		}
		model.addAttribute("list", file);
		FileLoad fLoad = new FileLoad();
		model.addAttribute("fLoad", fLoad);
		List<FileLoad> allFileLoad = ls.getAllFileLoad();

		model.addAttribute("allFileLoad", allFileLoad);
		return "panel";

	}

	@PostMapping("searches3")
	public String searches3(Model model, @ModelAttribute("flo") FileLoad ff) {
		FileLoad flo = new FileLoad();
		model.addAttribute("flo", flo);
		List<FileLoad> getallLanguages = ls.getallLanguages(ff.getLanguage());
		List<FileLoad> allFileLoad = ls.getAllFileLoad();

		model.addAttribute("allFileLoad", allFileLoad);
		List<File> file = new ArrayList<>();
		for (FileLoad sw : getallLanguages) {
			file.add(new File(sw.getPath()));

		}
		model.addAttribute("list", file);

		FileLoad fLoad = new FileLoad();
		model.addAttribute("fLoad", fLoad);

		return "panel";

	}

	@PostMapping("searches4")
	public String searches4(Model model, @ModelAttribute("flo") FileLoad ff,HttpSession session) {
		Logins log=(Logins)session.getAttribute("checkLogin");
		FileLoad flo = new FileLoad();
		model.addAttribute("flo", flo);
		List<FileLoad> allTitle = ls.szukaj1(ff.getTitle(),log);
Set<FileLoad>allTitle1=new HashSet<FileLoad>();
for(FileLoad ld:allTitle)
{
	FileLoad fl = new FileLoad();

	fl.setData(ld.getData());
	fl.setDates(ld.getDates());
	fl.setDates1(ld.getDates1());
	fl.setDocumentCategory(ld.getDocumentCategory());
	fl.setDocumentTags(fl.getDocumentTags());
	fl.setFiles(ld.getFiles());
	fl.setGroups(ld.getGroups());
	fl.setId(ld.getId());
	fl.setLanguage(ld.getLanguage());
	fl.setMfile(ld.getMfile());
	fl.setModified(ld.getModified());
	fl.setModifiedBy(ld.getModifiedBy());
	fl.setNavigationTags(ld.getNavigationTags());
	fl.setNazwaFolderu(ld.getNazwaFolderu());
	fl.setPath(ld.getPath());
	fl.setReleaseDate(ld.getReleaseDate());
	fl.setScope(ld.getScope());
	fl.setSearchFiles(ld.getSearchFiles());
	fl.setTitle(ld.getTitle());
    fl.setPath1(new File(ld.getPath()).getName());
	allTitle1.add(fl);
	System.out.println(fl.getPath1()+"To ten co nie jarzy");
}
		
		
		
		model.addAttribute("list", allTitle1);
		FileLoad fLoad = new FileLoad();
		model.addAttribute("fLoad", fLoad);
	
		return "panel2";

	}

	@PostMapping("searches5")

	public String searches5(Model model, @ModelAttribute("flo") FileLoad ff,HttpSession session) {
		Logins log=(Logins)session.getAttribute("checkLogin");
		FileLoad flo = new FileLoad();
		model.addAttribute("flo", flo);
		
		List<FileLoad> getallDocumentCategory1 = ls.getallDocumentCategory1(ff,log);
		Set<FileLoad>getallDocumentCategory11=new HashSet<FileLoad>();
		for(FileLoad ld:getallDocumentCategory1)
		{
			FileLoad fl = new FileLoad();

			fl.setData(ld.getData());
			fl.setDates(ld.getDates());
			fl.setDates1(ld.getDates1());
			fl.setDocumentCategory(ld.getDocumentCategory());
			fl.setDocumentTags(fl.getDocumentTags());
			fl.setFiles(ld.getFiles());
			fl.setGroups(ld.getGroups());
			fl.setId(ld.getId());
			fl.setLanguage(ld.getLanguage());
			fl.setMfile(ld.getMfile());
			fl.setModified(ld.getModified());
			fl.setModifiedBy(ld.getModifiedBy());
			fl.setNavigationTags(ld.getNavigationTags());
			fl.setNazwaFolderu(ld.getNazwaFolderu());
			fl.setPath(ld.getPath());
			fl.setReleaseDate(ld.getReleaseDate());
			fl.setScope(ld.getScope());
			fl.setSearchFiles(ld.getSearchFiles());
			fl.setTitle(ld.getTitle());
		    fl.setPath1(new File(ld.getPath()).getName());
		    getallDocumentCategory11.add(fl);
			System.out.println(fl.getPath1()+"To ten co nie jarzy");
		}
				
				
				
				model.addAttribute("list", getallDocumentCategory11);
				FileLoad fLoad = new FileLoad();
				model.addAttribute("fLoad", fLoad);
		return "panel2";

	}
	
	@PostMapping("searches6")
	public String searches6(Model model, @ModelAttribute("flo") FileLoad ff,HttpSession session) {
		Logins log=(Logins)session.getAttribute("checkLogin");
		FileLoad flo = new FileLoad();
		model.addAttribute("flo", flo);
		List<FileLoad> allNavigationTags1 = ls.getAllNavigationTags1(ff,log);
		Set<FileLoad>allNavigationTags2=new HashSet<FileLoad>();
		for(FileLoad ld:allNavigationTags1)
		{
			FileLoad fl = new FileLoad();

			fl.setData(ld.getData());
			fl.setDates(ld.getDates());
			fl.setDates1(ld.getDates1());
			fl.setDocumentCategory(ld.getDocumentCategory());
			fl.setDocumentTags(fl.getDocumentTags());
			fl.setFiles(ld.getFiles());
			fl.setGroups(ld.getGroups());
			fl.setId(ld.getId());
			fl.setLanguage(ld.getLanguage());
			fl.setMfile(ld.getMfile());
			fl.setModified(ld.getModified());
			fl.setModifiedBy(ld.getModifiedBy());
			fl.setNavigationTags(ld.getNavigationTags());
			fl.setNazwaFolderu(ld.getNazwaFolderu());
			fl.setPath(ld.getPath());
			fl.setReleaseDate(ld.getReleaseDate());
			fl.setScope(ld.getScope());
			fl.setSearchFiles(ld.getSearchFiles());
			fl.setTitle(ld.getTitle());
		    fl.setPath1(new File(ld.getPath()).getName());
		    allNavigationTags2.add(fl);
			System.out.println(fl.getPath1()+"To ten co nie jarzy");
		}
				
				
				
				model.addAttribute("list", allNavigationTags2);
				FileLoad fLoad = new FileLoad();
				model.addAttribute("fLoad", fLoad);
		return "panel2";

	}

	@PostMapping("searches7")
	public String searches7(Model model, @ModelAttribute("flo") FileLoad ff,HttpSession session) {
		Logins log=(Logins)session.getAttribute("checkLogin");
		FileLoad flo = new FileLoad();
		model.addAttribute("flo", flo);
		
		List<FileLoad> allLanguage1 = ls.getAllLanguage1(ff,log);
		Set<FileLoad>allLanguage2=new HashSet<FileLoad>();
		for(FileLoad ld:allLanguage1)
		{
			FileLoad fl = new FileLoad();

			fl.setData(ld.getData());
			fl.setDates(ld.getDates());
			fl.setDates1(ld.getDates1());
			fl.setDocumentCategory(ld.getDocumentCategory());
			fl.setDocumentTags(fl.getDocumentTags());
			fl.setFiles(ld.getFiles());
			fl.setGroups(ld.getGroups());
			fl.setId(ld.getId());
			fl.setLanguage(ld.getLanguage());
			fl.setMfile(ld.getMfile());
			fl.setModified(ld.getModified());
			fl.setModifiedBy(ld.getModifiedBy());
			fl.setNavigationTags(ld.getNavigationTags());
			fl.setNazwaFolderu(ld.getNazwaFolderu());
			fl.setPath(ld.getPath());
			fl.setReleaseDate(ld.getReleaseDate());
			fl.setScope(ld.getScope());
			fl.setSearchFiles(ld.getSearchFiles());
			fl.setTitle(ld.getTitle());
		    fl.setPath1(new File(ld.getPath()).getName());
		    allLanguage2.add(fl);
			System.out.println(fl.getPath1()+"To ten co nie jarzy");
		}
				
				
				
				model.addAttribute("list", allLanguage2);
				FileLoad fLoad = new FileLoad();
				model.addAttribute("fLoad", fLoad);
		return "panel2";

	}

	

	@GetMapping("editUSerr")
	public String editUSer(@RequestParam("idUser") int id, Model model, HttpSession session) {
		Logins gpp = new Logins();
		Logins uSerbyID = ls.getUSerbyID(id);
		session.setAttribute("uSerbyID", uSerbyID);
		Logins uSerbyID2 = ls.getUSerbyID(id);
		session.setAttribute("id", id);

		gpp.setId(uSerbyID.getId());
		gpp.setPassword(uSerbyID2.getPassword());
		gpp.setStanowisko(uSerbyID.getStanowisko());
		gpp.setLogin(uSerbyID.getLogin());
		gpp.setNazwisko(uSerbyID.getImie());
		gpp.setNazwisko(uSerbyID.getNazwisko());
		gpp.setGrupa(uSerbyID.getGrupa() + ".");
		model.addAttribute("gp", gpp);
		session.setAttribute("uSerbyID", gpp);
		model.addAttribute("uSerbyID", uSerbyID);
		List<Stanowisko> allStanowisko = ls.getAllStanowisko();
		model.addAttribute("allStanowisko", allStanowisko);
		List<Grupa> allGroups = ls.getAllGrupa();
		List<PrzynaleznoscGrup> allGroupByUser = ls.getAllGroupByUser(id);
		model.addAttribute("grupa", allGroupByUser);
		model.addAttribute("allGroups", allGroups);
		return "editUser";
	}

	@PostMapping("updateUSer")
	public String updateUSer(@ModelAttribute("uSerbyID") Logins login) {
		login.setGrupa("-");
		ls.saveLoginss(login);
		return "redirect:AddUser";
	}

	@PostMapping("DodajGrupe")
	public String dodajDoGrupy(@ModelAttribute("gp") Logins log1, HttpSession session, Model model) {

		Logins uSerbyID = ls.getUSerbyID(log1.getId());
		Logins gpp = new Logins();

		ls.saveLogins(log1);

		return "redirect:RefreshDodajGrupe";

	}

	@GetMapping("RefreshDodajGrupe")
	public String dodajDoGrupy1(HttpSession session, Model model) {

		Logins log = (Logins) session.getAttribute("uSerbyID");
		System.out.println("To jest to id A!!!!!!!!!!" + log.getId());
		Logins gpp = new Logins();

		Logins uSerbyID = ls.getUSerbyID(log.getId());
		session.setAttribute("uSerbyID", uSerbyID);
		Logins uSerbyID2 = ls.getUSerbyID(log.getId());
		System.out.println(uSerbyID2.getLogin() + "To jest ten login");

		gpp.setId(uSerbyID.getId());
		gpp.setPassword(uSerbyID.getPassword());
		gpp.setStanowisko(uSerbyID.getStanowisko());
		gpp.setLogin(uSerbyID.getLogin());
		gpp.setNazwisko(uSerbyID.getImie());
		gpp.setNazwisko(uSerbyID.getNazwisko());
		gpp.setGrupa(uSerbyID.getGrupa() + ".");
		model.addAttribute("gp", gpp);
		session.setAttribute("uSerbyID", gpp);
		model.addAttribute("uSerbyID", uSerbyID);
		List<Stanowisko> allStanowisko = ls.getAllStanowisko();
		model.addAttribute("allStanowisko", allStanowisko);
		List<Grupa> allGroups = ls.getAllGrupa();

		int id = (int) session.getAttribute("id");

		List<PrzynaleznoscGrup> allGroupByUser = ls.getAllGroupByUser(id);
		model.addAttribute("grupa", allGroupByUser);
		model.addAttribute("allGroups", allGroups);
		return "editUser";

	}

	@PostMapping("UsunGrupe")
	public String usunGrupe(@ModelAttribute("gp") Logins gp, HttpSession session, Model model) {
		ls.deleteLogins(gp);
		Logins log = (Logins) session.getAttribute("uSerbyID");
		Logins gpp = new Logins();

		Logins uSerbyID = ls.getUSerbyID(log.getId());
		session.setAttribute("uSerbyID", uSerbyID);
		Logins uSerbyID2 = ls.getUSerbyID(log.getId());
		gpp.setId(uSerbyID.getId());
		gpp.setPassword(uSerbyID2.getPassword());
		gpp.setStanowisko(uSerbyID.getStanowisko());
		gpp.setLogin(uSerbyID.getLogin());
		gpp.setNazwisko(uSerbyID.getImie());
		gpp.setNazwisko(uSerbyID.getNazwisko());
		gpp.setGrupa(uSerbyID.getGrupa() + ".");
		model.addAttribute("gp", gpp);
		session.setAttribute("uSerbyID", gpp);
		model.addAttribute("uSerbyID", uSerbyID);
		List<Stanowisko> allStanowisko = ls.getAllStanowisko();
		model.addAttribute("allStanowisko", allStanowisko);
		List<Grupa> allGroups = ls.getAllGrupa();

		int id = (int) session.getAttribute("id");

		List<PrzynaleznoscGrup> allGroupByUser = ls.getAllGroupByUser(id);
		model.addAttribute("grupa", allGroupByUser);
		model.addAttribute("allGroups", allGroups);

		return "redirect:RefreshDodajGrupe";
	}

	@PostMapping("dodU")
	public String dodU(@ModelAttribute("fLoad1") FileLoad fl1, HttpSession session, Model model) {
		String ses = (String) session.getAttribute("fl");
		String fl = (String) session.getAttribute("fl");
		
		List<FileLoad>saveGroupForPath=new ArrayList<>();
	    Set<FileLoad>sv1=    ls.getAllGroupsbyPath(fl);
	    saveGroupForPath.addAll(sv1);
		ls.saveU1(saveGroupForPath, fl1);


		return "redirect:refreshstatus";
	}

	@PostMapping("delU")
	public String delU(@ModelAttribute("fLoad1") FileLoad fLoad11, Model model, HttpSession session) {
		String ses = (String) session.getAttribute("fl");
		String fl = (String) session.getAttribute("fl");
		fLoad11.setPath(ses);
		List<FileLoad>saveGroupForPath=new ArrayList<>();
	    Set<FileLoad>sv1=    ls.getAllGroupsbyPath(fl);
	    saveGroupForPath.addAll(sv1);
		ls.deleteUSerfromPath(saveGroupForPath, fLoad11);



		return "redirect:refreshstatus";
	}

	@PostMapping("dodG")
	public String dodG(@ModelAttribute("fLoad1") FileLoad gop, HttpSession session, Model model) {
		String path = (String) session.getAttribute("fl");
		List<FileLoad>saveGroupForPath=new ArrayList<>();
	    Set<FileLoad>sv1=    ls.getAllGroupsbyPath(path);
	    saveGroupForPath.addAll(sv1);
		ls.saveGroupForPath1(saveGroupForPath, gop.getGroups());

	

		return "redirect:refreshstatus";
	}

	@PostMapping("delG")
	public String delG(@ModelAttribute("fLoad1") FileLoad gop, HttpSession session, Model model) {
		String path = (String) session.getAttribute("fl");
		System.out.println("ŚŚŚŚŚŚŚŚŚŚcieżkaaaaaaaaaaaaaaaaa" + gop + "pp");
		List<FileLoad>saveGroupForPath=new ArrayList<>();
	    Set<FileLoad>sv1=    ls.getAllGroupsbyPath(path);
	    saveGroupForPath.addAll(sv1);
		ls.delGroupFromPath(saveGroupForPath, gop);

		
		return "redirect:refreshstatus";
	}

	@PostMapping("dodUG")
	public String doString(@ModelAttribute("fLoad") FileLoad fLoad2, HttpSession sesion, Model model) {
		String fl = (String) sesion.getAttribute("pathfl");
		ls.addGrouptoPath(fLoad2, fl);
		sesion.setAttribute("pathfl", fl);
		model.addAttribute("i", fl);
		List<FileLoad> allUsersbyPath = ls.getAllUsersbyPath(fl);
		Set<FileLoad> allGroupsbyPath = ls.getAllGroupsbyPath(fl);
		model.addAttribute("allFileUsers", allUsersbyPath);
		model.addAttribute("allGroups", allGroupsbyPath);
		List<Logins> allFileUsers = ls.getAllFileUsers();
		model.addAttribute("allFileUsers", allFileUsers);
		List<Grupa> allGrupa = ls.getAllGrupa();
		model.addAttribute("allGrupa", allGrupa);
		FileLoad fLoad = new FileLoad();
		model.addAttribute("fLoad", fLoad);

		FileLoad fLoad1 = new FileLoad();
		model.addAttribute("fLoad1", fLoad1);

		model.addAttribute("allGroupsbyPath", allGroupsbyPath);

		Set<String> mm = new HashSet<>();
		for (FileLoad dp : allGroupsbyPath) {

			mm.add(dp.getGroups());

		}

		Set<String> ko = new HashSet<>();
		for (FileLoad pu : allUsersbyPath) {

			ko.add(pu.getUsers());
		}
		model.addAttribute("ko", ko);
		model.addAttribute("mm", mm);
		return "zawansowane1";

	}

	@PostMapping("delUG")
	public String delUG(@ModelAttribute("fLoad") FileLoad fLoadd, HttpSession sesion, Model model) {
		String fl = (String) sesion.getAttribute("pathfl");
		ls.deleteGroupfromPath(fLoadd, fl);
		sesion.setAttribute("pathfl", fl);
		model.addAttribute("i", fl);
		List<FileLoad> allUsersbyPath = ls.getAllUsersbyPath(fl);
		Set<FileLoad> allGroupsbyPath = ls.getAllGroupsbyPath(fl);
		model.addAttribute("allFileUsers", allUsersbyPath);
		model.addAttribute("allGroups", allGroupsbyPath);
		List<Logins> allFileUsers = ls.getAllFileUsers();
		model.addAttribute("allFileUsers", allFileUsers);
		List<Grupa> allGrupa = ls.getAllGrupa();
		model.addAttribute("allGrupa", allGrupa);
		FileLoad fLoad = new FileLoad();
		model.addAttribute("fLoad", fLoad);

		FileLoad fLoad1 = new FileLoad();
		model.addAttribute("fLoad1", fLoad1);

		model.addAttribute("allGroupsbyPath", allGroupsbyPath);

		Set<String> mm = new HashSet<>();
		for (FileLoad dp : allGroupsbyPath) {

			mm.add(dp.getGroups());

		}

		Set<String> ko = new HashSet<>();
		for (FileLoad pu : allUsersbyPath) {

			ko.add(pu.getUsers());
		}
		model.addAttribute("ko", ko);
		model.addAttribute("mm", mm);

		return "zawansowane1";
	}

	@PostMapping("dodUP")
	public String dodUP(@ModelAttribute("fLoad1") FileLoad fLoadd1, HttpSession sesion, Model model) {
		String fl = (String) sesion.getAttribute("pathfl");

		ls.addUserToPath(fLoadd1, fl);
		sesion.setAttribute("pathfl", fl);
		model.addAttribute("i", fl);
		List<FileLoad> allUsersbyPath = ls.getAllUsersbyPath(fl);
		Set<FileLoad> allGroupsbyPath = ls.getAllGroupsbyPath(fl);
		model.addAttribute("allFileUsers", allUsersbyPath);
		model.addAttribute("allGroups", allGroupsbyPath);
		List<Logins> allFileUsers = ls.getAllFileUsers();
		model.addAttribute("allFileUsers", allFileUsers);
		List<Grupa> allGrupa = ls.getAllGrupa();
		model.addAttribute("allGrupa", allGrupa);
		FileLoad fLoad = new FileLoad();
		model.addAttribute("fLoad", fLoad);

		FileLoad fLoad1 = new FileLoad();
		model.addAttribute("fLoad1", fLoad1);

		model.addAttribute("allGroupsbyPath", allGroupsbyPath);

		Set<String> mm = new HashSet<>();
		for (FileLoad dp : allGroupsbyPath) {

			mm.add(dp.getGroups());

		}

		Set<String> ko = new HashSet<>();
		for (FileLoad pu : allUsersbyPath) {

			ko.add(pu.getUsers());
		}
		model.addAttribute("ko", ko);
		model.addAttribute("mm", mm);

		return "zawansowane1";
	}

	@PostMapping("delUP")
	public String delUP(@ModelAttribute("fLoad1") FileLoad fLoadd1, HttpSession sesion, Model model) {
		String fl = (String) sesion.getAttribute("pathfl");
		ls.deleteUSerfromPath1(fLoadd1, fl);
		sesion.setAttribute("pathfl", fl);
		model.addAttribute("i", fl);
		List<FileLoad> allUsersbyPath = ls.getAllUsersbyPath(fl);
		Set<FileLoad> allGroupsbyPath = ls.getAllGroupsbyPath(fl);
		model.addAttribute("allFileUsers", allUsersbyPath);
		model.addAttribute("allGroups", allGroupsbyPath);
		List<Logins> allFileUsers = ls.getAllFileUsers();
		model.addAttribute("allFileUsers", allFileUsers);
		List<Grupa> allGrupa = ls.getAllGrupa();
		model.addAttribute("allGrupa", allGrupa);
		FileLoad fLoad = new FileLoad();
		model.addAttribute("fLoad", fLoad);

		FileLoad fLoad1 = new FileLoad();
		model.addAttribute("fLoad1", fLoad1);

		model.addAttribute("allGroupsbyPath", allGroupsbyPath);

		Set<String> mm = new HashSet<>();
		for (FileLoad dp : allGroupsbyPath) {

			mm.add(dp.getGroups());

		}

		Set<String> ko = new HashSet<>();
		for (FileLoad pu : allUsersbyPath) {

			ko.add(pu.getUsers());
		}
		model.addAttribute("ko", ko);
		model.addAttribute("mm", mm);

		return "zawansowane1";
	}
	
	@GetMapping("refreshstatus")
	public String refreshstatus(Model model,HttpSession session) {

		String ses = (String) session.getAttribute("fl");
		String fl = (String) session.getAttribute("fl");

		model.addAttribute("i", fl);
		FileLoad fLoad = new FileLoad();
		model.addAttribute("fLoad", fLoad);
		FileLoad fLoad1 = new FileLoad();
		model.addAttribute("fLoad1", fLoad1);
		Docs docs = new Docs();
		model.addAttribute("docs", docs);

		Grupa gop1 = new Grupa();
		model.addAttribute("gop", gop1);

		List<Docs> allDosc = ls.getAllDosc();
		model.addAttribute("allDosc", allDosc);
		session.setAttribute("fl", fl);
		List<DocumentCategory> getallDocumentCategory = ls.getallDocumentCategory();
		List<NavigationTags> allNavigationTags = ls.getAllNavigationTags();
		List<Language> allLanguages = ls.getAllLanguages();
		List<Scope> getallScopes = ls.getallScopes();
		List<Logins> allFileUsers = ls.getAllFileUsers();
		List<Grupa> allGroups = ls.getAllGrupa();
		model.addAttribute("getallDocumentCategory", getallDocumentCategory);
		model.addAttribute("allNavigationTags", allNavigationTags);
		model.addAttribute("allLanguages", allLanguages);
		model.addAttribute("getallScopes", getallScopes);
		model.addAttribute("allFileUsers", allFileUsers);
		model.addAttribute("allGroups", allGroups);
		List<FileLoad> allFileLoad = ls.getAllFileLoad();
		model.addAttribute("allFileLoad", allFileLoad);

		System.out.println("To jest ta ścieżka" + fl);
		Set<FileLoad> allGroupsbyPath = ls.getAllGroupsbyPath(fl);

		model.addAttribute("allGroupsbyPath", allGroupsbyPath);

		Set<String> mm = new HashSet<>();
		for (FileLoad dp : allGroupsbyPath) {

			mm.add(dp.getGroups());

		}
		List<FileLoad> allUsersbyPath = ls.getAllUsersbyPath(fl);

		Set<String> ko = new HashSet<>();
		for (FileLoad pu : allUsersbyPath) {

			ko.add(pu.getUsers());
		}

		model.addAttribute("ko", ko);
		model.addAttribute("mm", mm);

		
		
		
		return "zawansowane";
		
	}
	@GetMapping("skaner")
	public String skaner() throws IOException {
		
		new ProcessBuilder("E:\\pliki\\src\\main\\resources\\static\\icopy\\iCopy.exe").start();
		
		
		
		return "panel";
	}
	
}
