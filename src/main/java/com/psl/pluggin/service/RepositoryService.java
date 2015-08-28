package com.psl.pluggin.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.kohsuke.github.GHContent;
import org.kohsuke.github.GitHub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger logger = LoggerFactory
			.getLogger(RepositoryService.class);
	
	public RepositoryService() {
	}

	public boolean authenticate(String userName, String password)
			throws IOException {
		logger.info("authenticating user .....");
		GitHub gitHub = GitHub.connectUsingPassword(userName, password);
		logger.info("is user Authorised ? "+gitHub.isCredentialValid());
		return gitHub.isCredentialValid();
	}

	public User getTreeStructure(User user) {
		
		logger.info(" :: getTreeStructure starts:: ");
		List<RepositoryFile> currentBranchTree = new ArrayList<RepositoryFile>();
		List<GHContent> contentList = null;
		String path = user.getUrl();
		String[] urlArray = null;
		logger.info("path : "+path);
		if (path != null) {
			path = path.replace(gitPrefixUrl, "");
			logger.info("path after replace : "+path);
			urlArray = splitGitURL(path);
			try {
				GHContent ghContent = GitHub
						.connectUsingPassword(user.getUserName(),
								user.getPassword()).getRepository(urlArray[0])
						.getFileContent(urlArray[1]);
				user.setUrlAccessible(true);
				if (ghContent.isFile()) {
					logger.info("::  Fetching file content : : ");
					RepositoryFile file = new RepositoryFile(
							ghContent.getName(), ghContent.getHtmlUrl(),
							isFileOrDirectory(ghContent));
					file.setFileContent(ghContent.getContent());
					currentBranchTree.add(file);
				}
			} catch (Exception e) {
				try {
					
					logger.info(" :: fetching subtree :: ");
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

				} catch (Exception ie) {
					user.setUrlAccessible(false);
					logger.info("Exception in URL :: ");
					logger.error("Exception in URL :: "+ie);
					logger.info(" :: getTreeStructure Ends:: ");
					return user;
				}
			}
			user.setRepositoryFiles(currentBranchTree);
		}
		logger.info(" :: getTreeStructure Ends:: ");
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
