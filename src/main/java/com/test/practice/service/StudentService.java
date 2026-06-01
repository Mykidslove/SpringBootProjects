package com.test.practice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.test.practice.entity.StudentEntity;
import com.test.practice.repo.StudentRepo;

@Service
public class StudentService {
	
	@Autowired
	StudentRepo repo;

	public StudentEntity saveStudent(StudentEntity request) {
		
		System.out.println("enter"+request.getCourse());
		
		return repo.save(request);
	}

	 public Optional<StudentEntity> getstudentById(Integer id) {

	        Optional<StudentEntity> student= repo.findById(id);
	        
	        return student;

	    }

	 public StudentEntity updatestudent(Integer id, StudentEntity student) {
		 
		 Optional<StudentEntity> existingStudent=repo.findById(id);
		 if(existingStudent.isPresent()) {
			 
			 StudentEntity dbstudent=existingStudent.get();
			 
			 dbstudent.setStu_name(student.getStu_name());
			 dbstudent.setCourse(student.getCourse());
			 
			 return repo.save(dbstudent);
		 }
		return null;
		
	 }

	 public boolean deleteStudentByid(Integer id) {
		 
		 Optional<StudentEntity> student=repo.findById(id);
		 
		 if(student.isPresent()) {
			 repo.deleteById(id);
			 return true;
		 }
		
		return false;
	 }

	 public java.util.List<StudentEntity> getAllstudents() {
		 
		
		return repo.findAll();
	 }

	 public StudentEntity studentPatch(Integer id, StudentEntity student) {
		
		 Optional<StudentEntity> optionalstudent=repo.findById(id);
		 if(optionalstudent.isPresent()) {
			 
			 StudentEntity stu=optionalstudent.get();
			 if(student.getStu_name()!=null) {
				 stu.setStu_name(student.getStu_name());
			 }
			 
			 if(student.getCourse()!=null) {
				 stu.setCourse(student.getCourse());
				 
			 }
			 
			 return repo.save(stu);
		 }
		 
		return null;
	 }

	 public void deleteAllstudents() {
		 
		 repo.deleteAll();
		
	 }
	

}
