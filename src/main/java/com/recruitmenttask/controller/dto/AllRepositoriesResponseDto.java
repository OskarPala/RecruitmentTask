package com.recruitmenttask.controller.dto;

import java.util.List;

public record AllRepositoriesResponseDto(List<GitHubRepositoryResponseDto> repositories) {
}
