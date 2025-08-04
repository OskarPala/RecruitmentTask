package com.recruitmenttask.reporetriever.service;

import com.recruitmenttask.reporetriever.model.Branch;
import com.recruitmenttask.reporetriever.model.GitHubRepository;
import com.recruitmenttask.reporetriever.proxy.dto.BranchDto;
import com.recruitmenttask.reporetriever.proxy.dto.GitHubRepositoryDto;

import java.util.List;

class RepositoryMapper {
    public static List<Branch> mapFromBranchDtoToBranch(List<BranchDto> result) {
        return result.stream()
                .map(r -> new Branch(r.name(), r.commit().sha()))
                .toList();
    }

    public static List<GitHubRepository> mapFromRepositoryDtoToRepository(List<GitHubRepositoryDto> nonForkRepositories) {
        return nonForkRepositories.stream()
                .map(n -> new GitHubRepository(n.name(), n.owner().login()))
                .toList();
    }
}
