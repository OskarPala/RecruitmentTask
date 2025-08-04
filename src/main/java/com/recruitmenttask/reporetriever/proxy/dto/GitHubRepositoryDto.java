package com.recruitmenttask.reporetriever.proxy.dto;

public record GitHubRepositoryDto(String name, OwnerDto owner, boolean fork) {
}
