package com.recruitmenttask.reporetriever.proxy;

import com.recruitmenttask.reporetriever.proxy.dto.BranchDto;
import com.recruitmenttask.reporetriever.proxy.dto.GitHubRepositoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
//Repository Name
//Owner Login
//For each branch it’s name and last commit sha

@FeignClient("https://api.github.com/")
public interface GitHubApiProxy {
    @GetMapping("users/{userName}/repos")
    List<GitHubRepositoryDto> getAllRepos(@PathVariable("userName") String userName);
    @GetMapping("/repos/{owner}/{repo}/branches")
    List<BranchDto> getAllBranches(@PathVariable("owner") String owner, @PathVariable("repo") String repoName);
}
