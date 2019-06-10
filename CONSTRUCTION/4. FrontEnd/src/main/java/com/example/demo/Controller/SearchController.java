package com.example.demo.Controller;


import com.example.demo.Model.AjaxResponseBody;
import com.example.demo.Model.GoldenCow;
import com.example.demo.Model.SearchCriteria;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class SearchController {

    @Autowired
    private UserService userService;


    @PostMapping("/api/search")
    public ResponseEntity<?> getSearchResult(@Valid @RequestBody SearchCriteria s, Errors errors){
        System.out.println("getSearch kaldt");
        AjaxResponseBody ajaxResponseBody = new AjaxResponseBody();
        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {
            ajaxResponseBody.setMsg(errors.getAllErrors().get(0).getDefaultMessage());
            return ResponseEntity.badRequest().body(ajaxResponseBody);
        }
        List<GoldenCow> searchResult = userService.findCows(s.getUsername());
        if(searchResult.size() == 0){
            ajaxResponseBody.setMsg("user not found, searching for " +s.getUsername());
        }else {
            ajaxResponseBody.setMsg("user(s) found");
        }
        ajaxResponseBody.setResult(searchResult);

        return ResponseEntity.ok(ajaxResponseBody);
    }

    @GetMapping("/api/search")
    public ResponseEntity<?> getBasicAjax(@RequestBody SearchCriteria searchCriteria){
        System.out.println("getSearch kaldt med GET værdi:" + searchCriteria.getUsername());
        AjaxResponseBody ajaxResponseBody = new AjaxResponseBody();
        List<GoldenCow> searchResult = userService.findCows(searchCriteria.getUsername());
        ajaxResponseBody.setResult(searchResult);
        ajaxResponseBody.setMsg("Found one rich client");
        return ResponseEntity.ok(ajaxResponseBody);
    }

}
