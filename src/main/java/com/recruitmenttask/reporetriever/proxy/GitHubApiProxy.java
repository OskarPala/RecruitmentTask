package com.recruitmenttask.reporetriever.proxy;

import com.recruitmenttask.reporetriever.proxy.dto.BranchDto;
import com.recruitmenttask.reporetriever.proxy.dto.GitHubRepositoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "github-api-client")
public interface GitHubApiProxy {
    @GetMapping("users/{userName}/repos")
    List<GitHubRepositoryDto> getAllRepos(@PathVariable("userName") String userName);

    @GetMapping("/repos/{owner}/{repo}/branches")
    List<BranchDto> getAllBranches(@PathVariable("owner") String owner, @PathVariable("repo") String repoName);
}
