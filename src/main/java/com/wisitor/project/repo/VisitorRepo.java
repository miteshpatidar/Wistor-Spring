package com.wisitor.project.repo;

import com.wisitor.project.model.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VisitorRepo extends JpaRepository<Visitor,Integer> {
    @Query("select v from Visitor v where v.member.id=?1")
    List<Visitor> findByMember_Id(int memberid);
}
