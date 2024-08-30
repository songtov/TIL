package com.chihosong.my_web_app.service;

import com.chihosong.my_web_app.model.ApiResponse;


import com.chihosong.my_web_app.model.MyRequestObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import io.github.cdimascio.dotenv.Dotenv;


@Service
public class MyService {

    private final RestTemplate restTemplate;
    private final Dotenv dotenv;

    @Value("${jwt.accessToken}")
    private String token;

    public MyService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.dotenv = Dotenv.configure().load();
    }

    public String getToken(){
        return dotenv.get("JWT_TOKEN");
    }

    public String getApiUrl(){
        return dotenv.get("DDM_API_URL");
    }


    public String makePostRequestAndGetToken() {
        String url = getApiUrl() + "/auth/signin";
        MyRequestObject request = new MyRequestObject( dotenv.get("email"),  dotenv.get("password"), "true");

        try {
            // Make the POST request and get the response as ApiResponse
            ResponseEntity<ApiResponse> response = restTemplate.postForEntity(url, request, ApiResponse.class);

            // Extract the JWT token from the response
            ApiResponse apiResponse = response.getBody();
            if (apiResponse != null && apiResponse.getData() != null) {
                return apiResponse.getData().getAttributes().getToken();
            } else {
                return "Token not found in the response";
            }
        } catch (HttpClientErrorException e) {
            return "Error occurred: " + e.getResponseBodyAsString();
        }
    }

    public String makePostRequest() {
        String url = getApiUrl() + "/auth/signin";

        // Create an instance of the request object
        MyRequestObject request = new MyRequestObject( dotenv.get("email") , dotenv.get("password"), "true");

        // Make the POST request
        try {
            // Attempt to make the POST request
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            // Handle 404 Not Found
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                return "404 Not Found Error: " + e.getResponseBodyAsString();
            } else if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                return "401 Unauthorized: " + e.getResponseBodyAsString();
            } else {
                // Handle other HTTP errors
                return "Error occurred: " + e.getResponseBodyAsString();
            }
        } catch (RestClientException e) {
            // Handle other exceptions (e.g., network issues)
            return "General error: " + e.getMessage();
        }
    }

    public String makeAuthenticatedGetRequest() {
        String token = getToken();
        if (token == null || token.isEmpty()) {
            return "Failed to retrieve token";
        }

        String url = getApiUrl() + "/projects";

        // Set the Authorization header with the token
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        headers.set("Content-Type", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        return response.getBody();
    }

    public String makeAuthenticatedPostRequest() {
        String token = getToken();
        if (token == null || token.isEmpty()) {
            return "Failed to retrieve token";
        }

        String url = getApiUrl() + "/projects";

        // Set the Authorization header with the token
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        headers.set("Content-Type", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        return response.getBody();
    }
}