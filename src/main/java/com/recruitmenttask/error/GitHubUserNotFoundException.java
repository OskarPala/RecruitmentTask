package com.recruitmenttask.error;

public class GitHubUserNotFoundException extends RuntimeException{
   public GitHubUserNotFoundException(final String message) {
        super(message);
    }
}
