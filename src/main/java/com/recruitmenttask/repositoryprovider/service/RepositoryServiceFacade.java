package com.recruitmenttask.repositoryprovider.service;

import com.recruitmenttask.repositoryprovider.model.AllInfoResult;
import com.recruitmenttask.repositoryprovider.model.GitHubRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepositoryServiceFacade {
    private final RepositoryService repositoryService;

    public List<AllInfoResult> getAllInfoResult(String userName) {
        List<GitHubRepository> results = repositoryService.getMappedRepositories(userName);
        return repositoryService.createAllInfoResult(results);
    }

    RepositoryServiceFacade(final RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }
}
