package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers() {
        User user1 = new User();
        user1.setId(1L);
        user1.setName("John Doe");
        user1.setEmail("john.doe@example.com");

        User user2 = new User();
        user2.setId(2L);
        user2.setName("Jane Doe");
        user2.setEmail("jane.doe@example.com");

        List<User> users = Arrays.asList(user1, user2);

        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getName());
        assertEquals("Jane Doe", result.get(1).getName());
    }

    @Test
    void testGetAllUsers_EmptyList() {
        when(userRepository.findAll()).thenReturn(Arrays.asList());
        List<User> result = userService.getAllUsers();
        assertTrue(result.isEmpty());
    }

    @Test
    void testCreateUser() {
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");

        when(userRepository.save(user)).thenReturn(user);

        User result = userService.createUser(user);
        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        assertEquals("john.doe@example.com", result.getEmail());
    }

    @Test
    void testCreateUser_NullValues() {
        User user = new User();
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.createUser(user);
        assertNotNull(result);
        assertNull(result.getName());
        assertNull(result.getEmail());
    }

    @Test
    void testUpdateUser() {
        User user = new User();
        user.setId(1L);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");

        User updatedUser = new User();
        updatedUser.setId(1L);
        updatedUser.setName("John Smith");
        updatedUser.setEmail("john.smith@example.com");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(updatedUser);

        User result = userService.updateUser(1L, updatedUser);
        assertNotNull(result);
        assertEquals("John Smith", result.getName());
        assertEquals("john.smith@example.com", result.getEmail());
    }

    @Test
    void testUpdateUser_UserNotFound() {
        User updatedUser = new User();
        updatedUser.setName("John Smith");
        updatedUser.setEmail("john.smith@example.com");

        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            userService.updateUser(1L, updatedUser);
        });
    }

    @Test
    void testUpdateUser_WithNullValues() {
        User existingUser = new User();
        existingUser.setId(1L);
        existingUser.setName("John Doe");
        existingUser.setEmail("john.doe@example.com");

        User updatedUser = new User();
        updatedUser.setId(1L);
        updatedUser.setName(null);
        updatedUser.setEmail(null);

        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(existingUser)).thenReturn(existingUser);

        User result = userService.updateUser(1L, updatedUser);
        assertNotNull(result);
        assertNull(result.getName());
        assertNull(result.getEmail());
    }

    @Test
    void testDeleteUser() {
        User user = new User();
        user.setId(1L);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        doNothing().when(userRepository).deleteById(1L);

        userService.deleteUser(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteUser_UserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        doNothing().when(userRepository).deleteById(1L);

        userService.deleteUser(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }
}
