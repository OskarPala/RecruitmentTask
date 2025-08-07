package com.recruitmenttask.repositoryprovider.proxy.dto;

public record GitHubRepositoryDto(String name, OwnerDto owner, boolean fork) {
}
