package com.recruitmenttask.reporetriever.service;

import com.recruitmenttask.reporetriever.model.AllInfoResult;
import com.recruitmenttask.reporetriever.model.Branch;
import com.recruitmenttask.reporetriever.model.GitHubRepository;
import com.recruitmenttask.reporetriever.proxy.dto.BranchDto;
import com.recruitmenttask.reporetriever.proxy.dto.GitHubRepositoryDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RepositoryRetrieverFacade {
    private final RepositoryRetriever repositoryRetriever;

    public List<AllInfoResult> getAllInfoResult(String userName) {
        List<GitHubRepository> results = getMappedRepositories(userName);
        return createAllInfoResult(results);
    }

    List<AllInfoResult> createAllInfoResult(List<GitHubRepository> results) {
        return results.stream()
                .map(repository -> new AllInfoResult(
                        repository.name(),
                        repository.owner(),
                        getMappedBranches(repository)
                ))
                .collect(Collectors.toList());
    }

    List<Branch> getMappedBranches(GitHubRepository repository) {
        List<BranchDto> allRepositoryBranches = getAllBranchesDto(repository);
        return RepositoryMapper.mapFromBranchDtoToBranch(allRepositoryBranches);

    }

    List<BranchDto> getAllBranchesDto(GitHubRepository repository) {
        return repositoryRetriever.makeBranchesRequest(
                repository.owner(),
                repository.name()
        );
    }

    List<GitHubRepository> getMappedRepositories(String userName) {
        List<GitHubRepositoryDto> filteredRepositories = getFilteredRepositories(userName);
        return RepositoryMapper.mapFromRepositoryDtoToRepository(filteredRepositories);
    }

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
