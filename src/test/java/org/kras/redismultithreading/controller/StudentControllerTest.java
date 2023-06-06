package org.kras.redismultithreading.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kras.redismultithreading.model.Address;
import org.kras.redismultithreading.model.Grade;
import org.kras.redismultithreading.model.dto.StudentResponseDto;
import org.kras.redismultithreading.service.ChainServiceBuilder;
import org.kras.redismultithreading.service.StudentServiceApiImpl;
import org.kras.redismultithreading.service.StudentServiceCacheImpl;
import org.kras.redismultithreading.service.mapper.StudentMapper;
import org.kras.redismultithreading.service.mapper.StudentMapperImpl;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

@ExtendWith(SpringExtension.class)
@WebMvcTest(StudentController.class)
@Import({StudentMapper.class, ChainServiceBuilder.class})
class StudentControllerTest {
    private StudentResponseDto studentDto;
    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private ChainServiceBuilder chainServiceBuilder;
    @MockBean
    @Qualifier("cacheService")
    private StudentServiceCacheImpl serviceCache;
    @MockBean
    @Qualifier("apiService")
    private StudentServiceApiImpl serviceApi;
    @InjectMocks
    private StudentMapperImpl studentMapper;

    @BeforeEach
    void setUp() {
        Grade mathGrade = new Grade("math", "90");
        Grade englishGrade = new Grade("english", "80");
        Address address = new Address("8200", "Dixie Road", "Brampton", "ON");

        this.studentDto = StudentResponseDto.builder()
                .studentNumber("960054419")
                .firstName("Joe")
                .lastName("Smith")
                .phoneNumber("8976543324")
                .grades(Arrays.asList(mathGrade, englishGrade))
                .address(address)
                .build();
    }

    @Test
    void givenEndPointStudent_whenItCalled_thenStudentDtoRespond() throws Exception {
//        Mockito.doNothing().when(chainServiceBuilder);
//        Mockito.when(serviceCache.getStudent(Mockito.any(), Mockito.any())).thenReturn(Student.builder().build());
//        Mockito.when(studentMapper.toResponseDto(Mockito.any())).thenReturn(studentDto);
        mockMvc.perform(MockMvcRequestBuilders.get("/student/960054419"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

}