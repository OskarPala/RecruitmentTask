package com.recruitmenttask.reporetriever.service;

import com.recruitmenttask.reporetriever.proxy.dto.BranchDto;
import com.recruitmenttask.reporetriever.proxy.dto.GitHubRepositoryDto;

import java.util.List;

public interface RepositoryRetriever {
    List<GitHubRepositoryDto> makeGetRepositoryRequest(String username);

    List<BranchDto> makeBranchesRequest(String owner, String repoName);
}
