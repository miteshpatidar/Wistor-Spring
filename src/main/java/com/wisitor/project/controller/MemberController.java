package com.wisitor.project.controller;


import com.wisitor.project.model.Visitor;
import com.wisitor.project.service.JwtService;
import com.wisitor.project.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("visitorlist")
    public List<Visitor> getVisitors(HttpServletRequest request){
        System.out.println("inside membercotroll/getvisittor");
        return memberService.getVisitors(request);
    }

    @PostMapping("visitor")
    public String addvisitor(@RequestBody Visitor visitor){
        memberService.addVisitor(visitor);
        return "success";
    }
    @DeleteMapping("visitor")
    public String deleteVisitor(@RequestBody  Visitor visitor){
        memberService.deleteVisitor(visitor);
        return "Success";
    }
    @PutMapping("visitor")
    public void updateVisitor(@RequestBody Visitor visitor){
        memberService.updateVisitor(visitor);
    }
}
