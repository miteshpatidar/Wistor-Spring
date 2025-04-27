package com.wisitor.project.service;

import com.wisitor.project.dto.RegisterDTO;
import com.wisitor.project.model.Guard;
import com.wisitor.project.model.Member;
import com.wisitor.project.model.User;
import com.wisitor.project.repo.GuardRepository;
import com.wisitor.project.repo.MemberRepository;
import com.wisitor.project.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private GuardRepository guardRepo;
    @Autowired
    private MemberRepository memberRepo;
    public void save(RegisterDTO dto){

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        userRepo.save(user);

        String role = dto.getRole();
        if(role.equalsIgnoreCase("guard")){
            Guard guard = new Guard();
            guard.setName(dto.getName());
            guard.setContact(dto.getContact());
            guard.setUser(user);
            guardRepo.save(guard);
        }
        else if(role.equalsIgnoreCase("member")){
            Member member = new Member();
            member.setName(dto.getName());
            member.setContact(dto.getContact());
            member.setUser(user);
            memberRepo.save(member);
        }
    }
}
