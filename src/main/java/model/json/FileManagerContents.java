package model.json;

import java.util.LinkedList;
import java.util.List;

public class FileManagerContents {
	private List<String> files;
	private List<String> directories;
	private boolean root;
	
	public FileManagerContents() {
		files = new LinkedList<String>();
		directories = new LinkedList<String>();
		root = true;
	}

	public List<String> getFiles() {
		return files;
	}

	public void setFiles(List<String> files) {
		this.files = files;
	}

	public List<String> getDirectories() {
		return directories;
	}

	public void setDirectories(List<String> directories) {
		this.directories = directories;
	}

	public boolean isRoot() {
		return root;
	}

	public void setRoot(boolean root) {
		this.root = root;
	}
	
	
	
}
