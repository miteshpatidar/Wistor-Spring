package com.wisitor.project.controller;


import com.wisitor.project.model.Visitor;
import com.wisitor.project.service.JwtService;
import com.wisitor.project.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
@CrossOrigin(origins = "*")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/visitor")
    public ResponseEntity<List<Visitor>> getVisitors(HttpServletRequest request){
        try {
            System.out.println("inside membercotroll/getvisittor");
            return ResponseEntity.ok().body(memberService.getVisitors(request));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @PostMapping("/visitor")
    public String addvisitor(HttpServletRequest request ,@RequestBody Visitor visitor){

        memberService.addVisitor(request,visitor);
        return "success";
    }
    @DeleteMapping("/visitor/{visitorId}")
    public ResponseEntity<String> deleteVisitor(@PathVariable int visitorId){
        memberService.deleteVisitor(visitorId);
        return ResponseEntity.ok("Success");
    }
    @PutMapping("/visitor")
    public ResponseEntity<String> updateVisitor(@RequestBody Visitor visitor){
        memberService.updateVisitor(visitor);
        return ResponseEntity.ok("Updated Successfully");
    }

}
