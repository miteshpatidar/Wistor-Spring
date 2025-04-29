package com.wisitor.project.service;


import com.wisitor.project.model.Member;
import com.wisitor.project.model.Visitor;
import com.wisitor.project.repo.MemberRepository;
import com.wisitor.project.repo.VisitorRepo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private VisitorRepo visitorRepo;

    @Autowired
    private JwtService jwtService;

    public void save(Member member){
        memberRepository.save(member);
    }
    public List<Visitor> getVisitors(HttpServletRequest request){
        String token = jwtService.getTokenFromRequest(request);
        String username = jwtService.extractUserName(token);
        System.out.println("inside the member service/ getvisitor");

        int memberid = memberRepository.findMemberIdByUser(username);
        return visitorRepo.findByMember_Id(memberid);
    }

    public Optional<Visitor> addVisitor(HttpServletRequest request, Visitor visitor) {
        String token = jwtService.getTokenFromRequest(request);
        String username = jwtService.extractUserName(token);
        Member member = memberRepository.findMemberByUser(username);
        visitor.setMember(member);
        visitorRepo.save(visitor);
        return visitorRepo.findById(visitor.getId());
    }


    public void deleteVisitor(int visitor) {
        visitorRepo.deleteById(visitor); //no validation needed as the member only access there guests and for gaurd has all access
    }

    public void updateVisitor(Visitor visitor) {
        visitorRepo.save(visitor);
    }
}
