package com.recruitmenttask.reporetriever.service;

import com.recruitmenttask.error.GitHubUserNotFoundException;
import com.recruitmenttask.reporetriever.proxy.GitHubApiProxy;
import com.recruitmenttask.reporetriever.proxy.dto.BranchDto;
import com.recruitmenttask.reporetriever.proxy.dto.GitHubRepositoryDto;
import feign.FeignException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class RepositoryRetrieverImpl implements RepositoryRetriever {
    private final GitHubApiProxy gitHubApiProxy;

    @Override
    public List<GitHubRepositoryDto> makeGetRepositoryRequest(String username) {
        try {
            return gitHubApiProxy.getAllRepos(username);
        } catch (FeignException e) {
            if (e.status() == 404) {
                throw new GitHubUserNotFoundException("User: " + username + " does not exist");
            }
            throw e;
        }
    }

    @Override
    public List<BranchDto> makeBranchesRequest(String owner, String repoName) {
        return gitHubApiProxy.getAllBranches(owner, repoName);
    }

    RepositoryRetrieverImpl(final GitHubApiProxy gitHubApiProxy) {
        this.gitHubApiProxy = gitHubApiProxy;
    }
}
