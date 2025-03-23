package com.iitj.autoscaleapp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TaskService {

    private final RestTemplate restTemplate = new RestTemplate();

    public String executeTask(String taskName) {
        return "Executing " + taskName + " on Local VM";
    }

    public String executeTask(String taskName, String url) {
        return "Redirecting " + taskName + " to " + url + " -> " + restTemplate.getForObject(url, String.class);
    }
}
