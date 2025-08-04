package com.recruitmenttask.reporetriever.service;

import com.recruitmenttask.reporetriever.proxy.dto.BranchDto;
import com.recruitmenttask.reporetriever.proxy.dto.GitHubRepositoryDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RepositoryRetrieverFacade {
    private final RepositoryRetriever repositoryRetriever;




    List<GitHubRepositoryDto> getFilteredRepositories(String username) {
        return repositoryRetriever.makeGetRepositoryRequest(username).stream()
                .filter(gitHubRepositoryDto -> !gitHubRepositoryDto.fork())
                .collect(Collectors.toList()
                );
    }
    RepositoryRetrieverFacade(final RepositoryRetriever repositoryRetriever) {
        this.repositoryRetriever = repositoryRetriever;
    }
}
