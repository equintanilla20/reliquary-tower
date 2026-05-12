package org.eqdev.server.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.eqdev.server.exception.UserNotFoundException;
import org.eqdev.server.model.AppUser;
import org.eqdev.server.repository.AppUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AppUserServiceTest {

    @Mock
    private AppUserRepository userRepository;

    @InjectMocks
    private AppUserService userService;

    private AppUser testUser;

    @BeforeEach
    void setUp() {
        testUser = new AppUser("mtg_wizard", "wizard@eqdev.org", "password123", "ROLE_USER");
        testUser.setId(1L);
    }

    @Test
    void registerUser_Success_ShouldReturnSavedUser() {
        when(userRepository.existsByUsername("mtg_wizard")).thenReturn(false);
        when(userRepository.save(any(AppUser.class))).thenReturn(testUser);

        AppUser savedUser = userService.registerUser("mtg_wizard", "wizard@eqdev.org", "password123");

        assertNotNull(savedUser);
        assertEquals("mtg_wizard", savedUser.getUsername());
        verify(userRepository, times(1)).save(any(AppUser.class));
        System.out.println("Test Passed: Register user with unique username succeeded.");
    }

    @Test
    void registerUser_DuplicateUsername_ShouldThrowException() {
        when(userRepository.existsByUsername("mtg_wizard")).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.registerUser("mtg_wizard", "other@email.com", "pass");
        });

        assertEquals("Username already taken", exception.getMessage());
        verify(userRepository, never()).save(any());
        System.out.println("Test Passed: Register user with duplicate username failed as expected.");
    }

    @Test
    void getByUsername_Found_ShouldReturnUser() {
        when(userRepository.findByUsername("mtg_wizard")).thenReturn(Optional.of(testUser));

        AppUser foundUser = userService.getByUsername("mtg_wizard");

        assertEquals("mtg_wizard", foundUser.getUsername());
        System.out.println("Test Passed: Get user by username succeeded.");
    }

    @Test
    void getByUsername_NotFound_ShouldThrowUserNotFoundException() {
        when(userRepository.findByUsername("unknown")).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            userService.getByUsername("unknown");
        });
        System.out.println("Test Passed: Get user by unknown username threw UserNotFoundException as expected.");
    }
}