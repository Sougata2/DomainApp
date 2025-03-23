package com.sougata.domainApp.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/oauth")
@RequiredArgsConstructor
public class OAuthController {

    @Value("${google.auth.clientId}")
    private String clientId;

    @Value("${google.auth.projectId}")
    private String projectId;

    @Value("${google.auth.authUri}")
    private String authUri;

    @Value("${google.auth.tokenUri}")
    private String tokenUri;

    @Value("${google.auth.auth_provider_x509_cert_url}")
    private String authProviderX509CertUrl;

    @Value("${google.auth.clientSecret}")
    private String clientSecret;

    @Value("${google.auth.redirectUri}")
    private String redirectUri;

    @Value("${google.auth.userInfo.uri}")
    private String userInfoUri;


    private final RestTemplate restTemplate;

    /**
     * GET ACCESS TOKEN FROM THE CODE
     */
    @PostMapping("/access-token")
    public ResponseEntity<Map<String, Object>> getToken(@RequestBody Map<String, Object> body) {
        String code = body.get("code").toString();

        // Prepare request
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", code);
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("redirect_uri", redirectUri);
        params.add("grant_type", "authorization_code");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        // Send request to Google
        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                "https://oauth2.googleapis.com/token", HttpMethod.POST, request, ParameterizedTypeReference.forType(Map.class));

        return ResponseEntity.ok(response.getBody());
    }

    /**
     * GET THE USERINFO FROM THE ACCESS CODE.
     */
    @PostMapping("/user-info")
    public ResponseEntity<Map<String, Object>> getUserInfo(@RequestBody Map<String, Object> requestBody) {
        String accessToken = "Bearer " + requestBody.get("access_token").toString();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", accessToken);
        headers.set("Content-Type", "application/json");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);
        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(userInfoUri, HttpMethod.GET, request, ParameterizedTypeReference.forType(Map.class));
        return ResponseEntity.ok(response.getBody());
    }
}
