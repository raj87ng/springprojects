package com.rajat.springparallelwebclients.service;

import com.rajat.springparallelwebclients.bean.StudentBean;
import com.rajat.springparallelwebclients.model.StudentDto;
import com.rajat.springparallelwebclients.service.impl.StudentServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.powermock.modules.junit4.PowerMockRunner;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.anyString;

@RunWith(PowerMockRunner.class)
public class StudentServiceImplUT {

    @InjectMocks
     private StudentServiceImpl studentService;


     @BeforeEach
     public void setup(){
         MockitoAnnotations.openMocks(this);
     }

     @Test
    public void createStudentTest(){
         StudentBean sB= new StudentBean();
         sB.setId(1);
         sB.setName("RA");

         Mono<StudentDto> student = studentService.createStudent(sB);
         StudentDto sDto = student.block();
         Assert.assertEquals("RA",sDto.getName());
     }

}
