package com.recruitmenttask.controller;

import com.recruitmenttask.controller.dto.AllRepositoriesResponseDto;
import com.recruitmenttask.reporetriever.model.AllInfoResult;
import com.recruitmenttask.reporetriever.service.RepositoryRetrieverFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.recruitmenttask.controller.GitHubRepositoryMapper.mapFromAllInfoResultToAllRepositoriesResponseDto;

@RestController
public class GitHubApiRestController {
    private final RepositoryRetrieverFacade repositoryRetrieverFacade;

    @GetMapping("/{userName}")
    public ResponseEntity<AllRepositoriesResponseDto> getAllUserRepositories(
            @PathVariable String userName
    ) {
        final List<AllInfoResult> results = repositoryRetrieverFacade.getAllInfoResult(userName);
        final AllRepositoriesResponseDto responseDto = mapFromAllInfoResultToAllRepositoriesResponseDto(results);
        return ResponseEntity.ok(responseDto);
    }


    GitHubApiRestController(final RepositoryRetrieverFacade repositoryRetrieverFacade) {
        this.repositoryRetrieverFacade = repositoryRetrieverFacade;
    }
}
