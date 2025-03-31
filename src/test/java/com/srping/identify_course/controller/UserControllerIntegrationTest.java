package com.srping.identify_course.controller;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.srping.identify_course.dto.request.UserCreationRequest;
import com.srping.identify_course.dto.response.UserResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc // tạo mock request đến controller
@Testcontainers
public class UserControllerIntegrationTest {
    @Container
    static final MySQLContainer<?> mysqlContainer = new MySQLContainer<>("mysql:latest");

    @DynamicPropertySource
    static void configureDataSource(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mysqlContainer::getUsername);
        registry.add("spring.datasource.password", mysqlContainer::getPassword);
        registry.add("spring.datasource.driverClassName", () -> "com.mysql.cj.jdbc.Driver");
        registry.add("spring.jpa.hibernate.dll-auto", () -> "update");
    }

    @Autowired
    private MockMvc
            mockMvc; // tương tác với các controller request bởi vì controller không được gọi trức tiếp như 1 class

    private UserCreationRequest request;
    private UserResponse userResponse;
    private LocalDate dob;

    @BeforeEach
    void initData() {
        dob = LocalDate.of(1990, 1, 1);
        request = UserCreationRequest.builder()
                .username("jonnd3")
                .firstName("jon")
                .lastName("n")
                .dob(dob)
                .password("12345678")
                .build();

        userResponse = UserResponse.builder()
                .id("0d268a0b523c")
                .username("jonnd3")
                .firstName("John")
                .lastName("Doe")
                .dob(dob)
                .build();
    }

    @Test
    void createUser_validRequest_success() throws Exception {
        // GIVEN
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        String content = mapper.writeValueAsString(request);

        // WHEN
        mockMvc.perform(MockMvcRequestBuilders.post("/users") // phương thức
                        .contentType(MediaType.APPLICATION_JSON_VALUE) // kiểu nhận vào
                        .content(content)) // dữ liệu nhận vào
                .andExpect(MockMvcResultMatchers.status().isOk()) // code http trả về
                .andExpect(MockMvcResultMatchers.jsonPath("code") //
                        .value(1000));
        // THEN
    }
}
