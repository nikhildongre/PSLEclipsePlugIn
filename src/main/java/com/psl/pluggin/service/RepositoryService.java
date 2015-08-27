package com.psl.pluggin.service;

import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.kohsuke.github.GHContent;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
@Service
public class RepositoryService {

                final String defaultRepository = "nikhildongre/PSLEclipsePlugIn";
                final String prefixUrl = "https://github.com/nikhildongre/PSLEclipsePlugIn/tree/master";

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
                                                                                .getRepository("nikhildongre/PSLEclipsePlugIn")
                                                                                .getDirectoryContent(path);
                                                
                                                

                                } catch (IOException e) {
                                                e.printStackTrace();
                                }

                                return currentBranchTree;
                }
}

