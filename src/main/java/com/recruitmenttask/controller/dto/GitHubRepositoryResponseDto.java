package com.recruitmenttask.controller.dto;

import java.util.List;

public record GitHubRepositoryResponseDto(String repositoryName, String ownerLogin, List<BranchResponseDto> branches) {
}
