package com.recruitmenttask;

interface SampleRepositoriesOffers {

    default String bodyWithTwoRepositoriesWithOneFork() {
        return """
                [
                 {
                     "name": "repository",
                     "owner": { "login": "person" },
                     "fork": false
                   },
                   {
                     "name": "forkedRepository",
                     "owner": { "login": "person" },
                     "fork": true
                   }
                ]
                """.trim();
    }

    default String bodyWithBranch() {
        return """
                [
                {
                    "name": "branch",
                    "commit": {
                      "sha": "sha-134512345asdasd"
                    }
                  }
                ]
                """.trim();
    }
}
