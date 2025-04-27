package com.wisitor.project.repo;


import com.wisitor.project.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,Integer> {

    @Query("select m.id from Member m where m.user.username = ?1")
    int findByUser(String username);
}
