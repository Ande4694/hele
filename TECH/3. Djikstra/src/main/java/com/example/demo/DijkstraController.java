package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class DijkstraController {

    @GetMapping("/dijkstra.html")
    public String getDijkstra(){
        return "dijkstra";
    }
}