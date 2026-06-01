package com.test.practice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.practice.entity.StudentEntity;

@Repository
public interface StudentRepo extends JpaRepository<StudentEntity, Integer> {

}
