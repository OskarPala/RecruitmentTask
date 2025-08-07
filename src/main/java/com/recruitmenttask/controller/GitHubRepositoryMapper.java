package com.recruitmenttask.controller;

import com.recruitmenttask.controller.dto.AllRepositoriesResponseDto;
import com.recruitmenttask.controller.dto.BranchResponseDto;
import com.recruitmenttask.controller.dto.GitHubRepositoryResponseDto;
import com.recruitmenttask.repositoryprovider.model.AllInfoResult;

import java.util.List;
import java.util.stream.Collectors;

class GitHubRepositoryMapper {

    public static AllRepositoriesResponseDto mapFromAllInfoResultToAllRepositoriesResponseDto(
            List<AllInfoResult> allRepositoriesList
    ) {
        final List<GitHubRepositoryResponseDto> response =
                mapFromGitHubRepositoryToGitHubRepositoryResponseDto(allRepositoriesList);
        return new AllRepositoriesResponseDto(response);
    }

    private static List<GitHubRepositoryResponseDto> mapFromGitHubRepositoryToGitHubRepositoryResponseDto(
            List<AllInfoResult> allRepositoryList
    ) {
        return allRepositoryList.stream()
                .map(repository -> new GitHubRepositoryResponseDto(
                        repository.repositoryName(),
                        repository.owner(),
                        mapFromBranchToBranchResponseDto(repository)))
                .collect(Collectors.toList());
    }

    private static List<BranchResponseDto> mapFromBranchToBranchResponseDto(AllInfoResult repositories) {
        return repositories.branches()
                .stream()
                .map(branch -> new BranchResponseDto(branch.name(), branch.sha()))
                .collect(Collectors.toList());
    }

}
