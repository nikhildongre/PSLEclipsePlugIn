package com.psl.pluggin.model;

//POJO to transfer file or directory
// fileOrDirectory can be FILE/DIRECTORY
public class RepositoryFile {

	String fileName;
	String fileHtmlPath;
	String fileOrDirectory;
	String fileContent;

	public RepositoryFile(String fileName, String fileHtmlPath,
			String fileOrDirectory) {
		super();
		this.fileName = fileName;
		this.fileHtmlPath = fileHtmlPath;
		this.fileOrDirectory = fileOrDirectory;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileHtmlPath() {
		return fileHtmlPath;
	}

	public void setFileHtmlPath(String fileHtmlPath) {
		this.fileHtmlPath = fileHtmlPath;
	}

	public String getFileOrDirectory() {
		return fileOrDirectory;
	}

	public void setFileOrDirectory(String fileOrDirectory) {
		this.fileOrDirectory = fileOrDirectory;
	}

	public String getFileContent() {
		return fileContent;
	}

	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}
	
	

}
