package com.psl.pluggin.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.kohsuke.github.GHContent;
import org.kohsuke.github.GitHub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.psl.pluggin.util.Property;
@Service
public class RepositoryService {
	private static final Logger logger = LoggerFactory.getLogger(RepositoryService.class);
	Property prop=new Property();

                final String defaultRepository = prop.getProperty().getProperty("repo.name");
                final String prefixUrl = prop.getProperty().getProperty("repo.url");
               

                public RepositoryService() {
                }

                public boolean authenticate(String userName, String password)
                                                throws IOException {
                                GitHub gitHub = GitHub.connectUsingPassword(userName, password);
                               
                                return gitHub.isCredentialValid();
                               
                }

                public Map<String, String> getTreeStructure(String path, String userName,
                                                String password) {
                                Map<String, String> currentBranchTree = null;
                                List<GHContent> content = null;
                                try {
                                             
                                                path = path.replace(prefixUrl, "");
                                                content = GitHub
                                                                                .connectUsingPassword(userName, password)
                                                                                .getRepository(defaultRepository)
                                                                                .getDirectoryContent(path);
                                                
                                                

                                } catch (IOException e) {
                                                e.printStackTrace();
                                }

                                return currentBranchTree;
                }
}

