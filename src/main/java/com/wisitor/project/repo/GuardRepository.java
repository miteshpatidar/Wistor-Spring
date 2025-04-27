package com.wisitor.project.repo;

import com.wisitor.project.model.Guard;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuardRepository extends JpaRepository<Guard,Integer> {
}
