package com.recruitmenttask;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;


public class HappyPathIntegrationTest extends BaseIntegrationTest implements SampleRepositoriesOffers {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldReturnRepositoriesForUser() {
        //given
        String userLogin = "person";
        String repositoryName = "repository";

        wireMockServer.stubFor(WireMock.get("/users/"+ userLogin +"/repos")
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/json")
                        .withBody(bodyWithTwoRepositoriesWithOneFork())));


        wireMockServer.stubFor(WireMock.get("/repos/"+ userLogin +"/"+repositoryName+"/branches")
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/json")
                        .withBody(bodyWithBranch())));

        //when
        ResponseEntity<String> response = restTemplate.getForEntity("/"+ userLogin, String.class);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).doesNotContain("forkedRepository");
        assertThat(response.getBody()).contains("repositoryName");
        assertThat(response.getBody()).contains("ownerLogin");
        assertThat(response.getBody()).contains("lastCommitSha");
        assertThat(response.getBody()).contains(repositoryName);
        assertThat(response.getBody()).contains("branch");
        assertThat(response.getBody()).contains("sha-134512345asdasd");
    }
}