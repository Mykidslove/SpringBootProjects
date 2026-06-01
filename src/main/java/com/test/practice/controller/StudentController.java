package com.test.practice.controller;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.test.practice.entity.StudentEntity;
import com.test.practice.service.StudentService;


@RestController
@RequestMapping("/restapi")
public class StudentController {

    @Autowired
    StudentService service;

    @PostMapping("/createuser")
    public ResponseEntity<StudentEntity> createOrder(
            @RequestBody StudentEntity request) {

        try {

            StudentEntity response = service.saveStudent(request);

            return ResponseEntity.ok(response);

        } catch (Exception e) {

            e.printStackTrace();

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

		
    @GetMapping("/students/{id}")
	 public ResponseEntity<StudentEntity> getstudentrById(@PathVariable Integer id) {
	     try {
	    	 System.out.println("TESdt get");
	         Optional<StudentEntity> student = service.getstudentById(id);
	         return student.map(ResponseEntity::ok)
	                    .orElseGet(() -> ResponseEntity.notFound().build());
	     } catch (Exception e) {
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	     }
	 }
   
    
    
    @PutMapping("/studentupdate/{id}")
    public ResponseEntity<StudentEntity> updatestudent(@PathVariable Integer id,@RequestBody StudentEntity student)
        {
    	try {
    		
    		StudentEntity updatedStudent=service.updatestudent(id, student);
    				
    		if(updatedStudent !=null){
    			
    			return ResponseEntity.ok(updatedStudent);
    		}else {
    		
    				return ResponseEntity.notFound().build();
    	}
    		
    	}catch(Exception e) {
    		
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    		
    		
    	}
        }
    	
    	@DeleteMapping("/deleteById/{id}")
    	public ResponseEntity<String> deleteStudentByid(@PathVariable Integer id){
    		
    		try {
    			
    			boolean deleted=service.deleteStudentByid(id);
    			if(deleted) {
    				
    				return ResponseEntity.ok("Deleted Successfully");
    				
    			}else {
    			
    			return ResponseEntity.notFound().build();
    			}
    		}catch(Exception e) {
    			
    			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    					.body("Error deleting student");
    		}
    		
  		
    	}

    	
    	@GetMapping("/getAllstudents")
    	public ResponseEntity<List<StudentEntity>> getAllstudents(){
    		try {
    			
    			List<StudentEntity> student=service.getAllstudents();
    			if(student.isEmpty()) {
    				return ResponseEntity.noContent().build();
    		}
    			
      			
    			return ResponseEntity.ok(student);
    		
    		
    	}catch(Exception e) {
    		
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    	}
		
     	
    }
    		
    @PatchMapping("/studentPatch/{id}")
    
    public ResponseEntity<StudentEntity> studentPatch(@PathVariable Integer id,@RequestBody StudentEntity student){
    	try {
    		
    		StudentEntity updatedstudent=service.studentPatch(id,student);
    		if(updatedstudent!=null) {
    			
    			return ResponseEntity.ok(updatedstudent);
    			
    		}
    		
    		return ResponseEntity.noContent().build();
    	}catch(Exception e) {
    		
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    	}
    	
    }
    	
    @DeleteMapping("/deleteAllStudents")
    public ResponseEntity<String> deleteAllstudents(){
    	
    	try {
    		
    		service.deleteAllstudents();
    		return ResponseEntity.ok("All students record deleted successfully");
    		
    	}catch(Exception e) {
    		
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    	}
    	
    }
}
	
	


