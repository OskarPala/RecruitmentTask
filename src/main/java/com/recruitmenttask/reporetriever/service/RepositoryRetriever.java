package com.recruitmenttask.reporetriever.service;

import com.recruitmenttask.reporetriever.proxy.GitHubApiProxy;
import com.recruitmenttask.reporetriever.proxy.dto.BranchDto;
import com.recruitmenttask.reporetriever.proxy.dto.GitHubRepositoryDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
class RepositoryRetriever {
    private final GitHubApiProxy gitHubApiProxy;


    List<GitHubRepositoryDto> makeGetRepositoryRequest(String username) {
        return gitHubApiProxy.getAllRepos(username);
    }

    List<BranchDto> makeBranchesRequest(String owner, String repoName) {
        return gitHubApiProxy.getAllBranches(owner, repoName);
    }

    RepositoryRetriever(final GitHubApiProxy gitHubApiProxy) {
        this.gitHubApiProxy = gitHubApiProxy;
    }
}
