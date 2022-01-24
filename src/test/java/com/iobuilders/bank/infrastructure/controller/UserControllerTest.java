package com.iobuilders.bank.infrastructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iobuilders.bank.application.service.UserService;
import com.iobuilders.bank.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void findAll_WhenExist_ThenReturnUsers() throws Exception {
        User user1 = createUser(1L);
        User user2 = createUser(2L);

        given(userService.getAllUser()).willReturn(List.of(user1, user2));

        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"id\":1,\"name\":\"name1\",\"email\":\"email1\",\"address\":null,\"creationDate\":null},{\"id\":2,\"name\":\"name2\",\"email\":\"email2\",\"address\":null,\"creationDate\":null}]"));
    }

    @Test
    void getUser_WhenUserId_ThenReturnUser() throws Exception {
        User user = createUser(1L);

        given(userService.getUserById(user.getId().intValue())).willReturn(user);

        mockMvc.perform(get("/user/{id}", user.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":1,\"name\":\"name1\",\"email\":\"email1\",\"address\":null,\"creationDate\":null}"));
    }

    @Test
    void saveUser_WhenUserId_ThenCreateUser() throws Exception {
        User user = createUser(1L);

        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
    }

    @Test
    void deleteUser_WhenUserId_ThenDeleteUser() throws Exception {
        User user = createUser(1L);

        mockMvc.perform(delete("/user/{id}", user.getId()))
                .andExpect(status().isOk());
    }

    private User createUser(Long id) {
        User user = new User();
        user.setId(id);
        user.setName("name" + id);
        user.setEmail("email" + id);

        return user;
    }
}
