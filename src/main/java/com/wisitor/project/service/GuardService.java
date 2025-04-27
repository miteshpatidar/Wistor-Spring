package com.wisitor.project.service;

import com.wisitor.project.model.Visitor;
import com.wisitor.project.repo.VisitorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuardService {
    @Autowired
    private VisitorRepo visitorRepo;
    public List<Visitor> getVisitorList() {
        return visitorRepo.findAll();
    }
    public void addVisitor(Visitor visitor) {
        visitorRepo.save(visitor);
    }
    public void deleteVisitor(Visitor visitor) {
        visitorRepo.delete(visitor);
    }
    public void updateVisitor(Visitor visitor) {
        visitorRepo.save(visitor);
    }
}
