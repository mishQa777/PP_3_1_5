package com.example.PP_3_1_5;

import com.example.PP_3_1_5.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Component
public class Connect {
    private final RestTemplate restTemplate;
    private String URL = "http://94.198.50.185:7081/api/users";
    private StringBuilder result = new StringBuilder();

    @Autowired
    public Connect(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public StringBuilder getResult() {
        return result;
    }

    public List<String> getAllUsers(HttpEntity<User> requestEntity) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL,
                HttpMethod.GET,
                requestEntity,
                String.class);
        System.out.println(responseEntity.getBody());
        return responseEntity.getHeaders().get("Set-Cookie");
    }

    public void addUser(HttpEntity<User> requestEntity) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL,
                HttpMethod.POST,
                requestEntity,
                String.class);
        System.out.println(responseEntity.getBody());
        result.append(responseEntity.getBody());
    }

     public void editUser(HttpEntity<User> requestEntity) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL,
                HttpMethod.PUT,
                requestEntity,
                String.class);
        System.out.println(responseEntity.getBody());
         result.append(responseEntity.getBody());
    }

    public void deleteUserById(HttpEntity<User> requestEntity,int id) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL + "/" + id,
                HttpMethod.DELETE,
                requestEntity,
                String.class);
        System.out.println(responseEntity.getStatusCode());
        System.out.println(responseEntity.getBody());
        result.append(responseEntity.getBody());
    }
}

