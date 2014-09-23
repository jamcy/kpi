package ua.kpi.eec.vml.model.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.Part;

import ua.kpi.eec.vml.common.ConfigurationHelper;


public class FileDao {

	private String basePath = "";

	public FileDao() {
		this.basePath = ConfigurationHelper.getResourceDir();
	}

	public List<String> listFiles(String path) {
		List<String> result = new LinkedList<String>();
		String dir = basePath + path;

		File folder = new File(dir);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				result.add(listOfFiles[i].getName());
			}
		}
		return result;
	}

	public List<String> getDirectories(String path) {
		List<String> result = new LinkedList<String>();
		String dir = basePath + path;
		System.out.println(dir);
		File folder = new File(dir);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (!listOfFiles[i].isFile()) {
				result.add(listOfFiles[i].getName());
			}
		}
		return result;
	}

	public void save(String path, String name, InputStream data) {
		File f = new File(this.basePath + path + "/" + name);
		try {
			OutputStream out = new FileOutputStream(f);
			int read = 0;
			final byte[] bytes = new byte[1024];
			while ((read = data.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void delete(String path, String name) {
		File f = new File(this.basePath + path + "/" + name);
		f.delete();
	}

	private String byteArrayToHex(byte[] a) {
		StringBuilder sb = new StringBuilder();
		for (byte b : a)
			sb.append(String.format("%02x", b & 0xff));
		return sb.toString();
	}

	public String generateName(String path) {
		Date date = null;
		String name = "";
		while (true) {
			date = new Date();
			try {
				byte[] bytesOfMessage = date.toString().getBytes("UTF-8");
				MessageDigest md = MessageDigest.getInstance("MD5");
				byte[] thedigest = md.digest(bytesOfMessage);
				name = byteArrayToHex(thedigest);
			} catch (Exception e) {
				return null;
			}
			File f = new File(this.basePath + path + "/" + name);
			if (!f.exists()) {
				break;
			}
		}
		return name;
	}

	public void makeDir(String path) {
		File f = new File(basePath + path);
		System.out.println(basePath + path);
		if (!f.exists()) {
			f.mkdir();
		}
	}

	public String getFileName(Part part) {
		for (String cd : part.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				return cd.substring(cd.indexOf('=') + 1).trim()
						.replace("\"", "");
			}
		}
		return null;
	}

}
