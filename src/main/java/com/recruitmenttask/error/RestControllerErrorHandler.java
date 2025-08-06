package com.recruitmenttask.error;

import com.recruitmenttask.controller.GitHubApiRestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(assignableTypes = GitHubApiRestController.class)
public class RestControllerErrorHandler {
    @ExceptionHandler(GitHubUserNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public GitHubUserNotFoundExceptionDto handleException(GitHubUserNotFoundException exception) {
        return new GitHubUserNotFoundExceptionDto(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

}
