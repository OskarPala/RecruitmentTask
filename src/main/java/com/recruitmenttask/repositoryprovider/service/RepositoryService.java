package com.recruitmenttask.repositoryprovider.service;

import com.recruitmenttask.error.GitHubUserNotFoundException;
import com.recruitmenttask.repositoryprovider.model.AllInfoResult;
import com.recruitmenttask.repositoryprovider.model.Branch;
import com.recruitmenttask.repositoryprovider.model.GitHubRepository;
import com.recruitmenttask.repositoryprovider.proxy.GitHubApiProxy;
import com.recruitmenttask.repositoryprovider.proxy.dto.BranchDto;
import com.recruitmenttask.repositoryprovider.proxy.dto.GitHubRepositoryDto;
import feign.FeignException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
class RepositoryService {
    private final GitHubApiProxy gitHubApiProxy;


    List<GitHubRepositoryDto> makeGetRepositoryRequest(String username) {
        try {
            return gitHubApiProxy.getAllRepos(username);
        } catch (FeignException e) {
            if (e.status() == 404) {
                throw new GitHubUserNotFoundException("User: " + username + " does not exist");
            }
            throw e;
        }
    }

    List<BranchDto> makeBranchesRequest(String owner, String repoName) {
        return gitHubApiProxy.getAllBranches(owner, repoName);
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
        return makeBranchesRequest(
                repository.owner(),
                repository.name()
        );
    }

    List<GitHubRepository> getMappedRepositories(String userName) {
        List<GitHubRepositoryDto> filteredRepositories = getFilteredRepositories(userName);
        return RepositoryMapper.mapFromRepositoryDtoToRepository(filteredRepositories);
    }

    List<GitHubRepositoryDto> getFilteredRepositories(String username) {
        return makeGetRepositoryRequest(username).stream()
                .filter(gitHubRepositoryDto -> !gitHubRepositoryDto.fork())
                .collect(Collectors.toList()
                );
    }

    RepositoryService(final GitHubApiProxy gitHubApiProxy) {
        this.gitHubApiProxy = gitHubApiProxy;
    }
}
