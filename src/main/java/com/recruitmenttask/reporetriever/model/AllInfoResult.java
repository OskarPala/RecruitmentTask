package com.recruitmenttask.reporetriever.model;

import java.util.List;

public record AllInfoResult(String repositoryName, String owner, List<Branch> branches) {
}
