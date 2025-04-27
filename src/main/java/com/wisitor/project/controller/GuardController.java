package com.wisitor.project.controller;


import com.wisitor.project.model.Visitor;
import com.wisitor.project.repo.VisitorRepo;
import com.wisitor.project.service.GuardService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guard")
public class GuardController {

    @Autowired
    private GuardService guardService;

    @GetMapping("visitorlist")
    public List<Visitor> getVisitorList(){
        return guardService.getVisitorList();
    }
    @PostMapping("visitor")
    public String addvisitor(@RequestBody Visitor visitor){
        guardService.addVisitor(visitor);
        return "success";
    }
    @DeleteMapping("visitor")
    public String deleteVisitor(@RequestBody  Visitor visitor){
        guardService.deleteVisitor(visitor);
        return "Success";
    }
    @PutMapping("visitor")
    public void updateVisitor(@RequestBody Visitor visitor){
        guardService.updateVisitor(visitor);
    }

}
