package com.psl.pluggin.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.kohsuke.github.GHContent;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.PagedIterable;
import org.springframework.stereotype.Service;

import com.psl.pluggin.model.RepositoryFile;
import com.psl.pluggin.model.User;

@Service
public class RepositoryService {

	final String gitPrefixUrl = "https://github.com/";
	final String urlSplitString1 = "blob/master/";
	final String urlSplitString2 = "tree/master/";
	final static String FILE = "FILE";
	final static String DIRECTORY = "DIRECTORY";

	public RepositoryService() {
	}

	public boolean authenticate(String userName, String password)
			throws IOException {
		GitHub gitHub = GitHub.connectUsingPassword(userName, password);
		return gitHub.isCredentialValid();
	}

	public User getTreeStructure(User user) {
		List<RepositoryFile> currentBranchTree = new ArrayList<RepositoryFile>();
		List<GHContent> contentList = null;
		String path = user.getUrl();
		String[] urlArray = null;
		if (path != null) {
			path = path.replace(gitPrefixUrl, "");
			urlArray = splitGitURL(path);
			try {
				GHContent ghContent = GitHub
						.connectUsingPassword(user.getUserName(),
								user.getPassword()).getRepository(urlArray[0])
						.getFileContent(urlArray[1]);
				user.setUrlAccessible(true);
				if (ghContent.isFile()) {
					RepositoryFile file = new RepositoryFile(
							ghContent.getName(), ghContent.getHtmlUrl(),
							isFileOrDirectory(ghContent));
					file.setFileContent(ghContent.getContent());
					currentBranchTree.add(file);
				}
			} catch (IOException e) {
				try {
					contentList = GitHub
							.connectUsingPassword(user.getUserName(),
									user.getPassword())
							.getRepository(urlArray[0])
							.getDirectoryContent(urlArray[1]);
					user.setUrlAccessible(true);

					for (GHContent content : contentList) {
						currentBranchTree.add(new RepositoryFile(content
								.getName(), content.getHtmlUrl(),
								isFileOrDirectory(content)));
					}

				} catch (IOException ie) {
					user.setUrlAccessible(false);
					System.out.println(ie.getMessage());
					return user;
				}
			}
			user.setRepositoryFiles(currentBranchTree);
		}
		return user;
	}

	public String isFileOrDirectory(GHContent content) {
		if (content.isFile())
			return FILE;
		if (content.isDirectory())
			return DIRECTORY;
		return null;
	}

	public String[] splitGitURL(String url) {
		String[] urlArray = new String[2];
		String[] tempUrlArray = null;
		if(url.contains(urlSplitString1)){
			tempUrlArray = url.split(urlSplitString1);
		}else{
			tempUrlArray = url.split(urlSplitString2);
		}
		urlArray[0] = tempUrlArray[0];
		if (tempUrlArray.length > 1) {
			urlArray[1] = tempUrlArray[1];
		} else {
			urlArray[1] = "";
		}
		return urlArray;
	}
}
