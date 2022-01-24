package com.iobuilders.bank.application.service;

import com.iobuilders.bank.application.repository.UserRepository;
import com.iobuilders.bank.domain.User;
import com.iobuilders.bank.domain.exception.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    void getAllUser_WhenUserExist_ThenGetUsers() {
        // given
        User user1 = createUser(1L);
        User user2 = createUser(2L);

        // When
        when(userRepository.findAll()).thenReturn(List.of(user1, user2));

        List<User> resultList = userService.getAllUser();

        // Then
        assertThat(resultList).isEqualTo(List.of(user1, user2));

        verify(userRepository).findAll();
    }

    @Test
    void getUserById_WhenUserExist_ThenGetUser() throws EntityNotFoundException {
        // given
        User user = createUser(1L);

        // When
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        User result = userService.getUserById(user.getId().intValue());

        // Then
        assertThat(result).isEqualTo(user);

        verify(userRepository).findById(user.getId());
    }

    @Test
    void saveOrUpdate_WhenUserValid_ThenSaveUser() {
        // given
        User user = createUser(1L);

        // When
        when(userRepository.save(user)).thenReturn(user);

        userService.saveOrUpdate(user);

        // Then
        verify(userRepository).save(user);
    }

    @Test
    void delete_WhenUserValid_ThenSaveUser() {
        // given
        User user = createUser(1L);

        // When
        userService.delete(user.getId().intValue());

        // Then
        verify(userRepository).deleteById(user.getId().intValue());
    }

    private User createUser(Long id) {
        User user = new User();
        user.setId(id);
        user.setName("name" + id);
        user.setEmail("email" + id);

        return user;
    }
}
