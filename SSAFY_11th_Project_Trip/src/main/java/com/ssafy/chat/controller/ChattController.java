package com.ssafy.chat.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChattController {
	
	@GetMapping("/mychatt")
    public ResponseEntity<Map<String, String>> chatt() {
        Map<String, String> response = new HashMap<>();
        response.put("view", "chatting");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}