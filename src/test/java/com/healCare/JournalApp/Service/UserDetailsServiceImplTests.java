package com.healCare.JournalApp.Service;

import com.healCare.JournalApp.Entity.User;
import com.healCare.JournalApp.Repository.UserRepository;
import com.healCare.JournalApp.service.UserDetailsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceImplTests {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepository userRepository;

    @Test
    void loadUserByUsernameTest() {
        // Arrange
        User mockUser = User.builder()
                .userName("Piyush")
                .password("dthkghjkrfrf")
                .roles(new ArrayList<>())
                .build();

        when(userRepository.findByUserName("Piyush")).thenReturn(mockUser);

        // Act
        UserDetails user = userDetailsService.loadUserByUsername("Piyush");

        // Assert
        Assertions.assertNotNull(user);
        Assertions.assertEquals("Piyush", user.getUsername());

        // Verify interaction
        verify(userRepository, times(1)).findByUserName("Piyush");
    }

    @Test
    void loadUserByUsername_UserNotFound() {
        when(userRepository.findByUserName("UnknownUser")).thenReturn(null);



        Assertions.assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsService.loadUserByUsername("UnknownUser");
        });

        verify(userRepository, times(1)).findByUserName("UnknownUser");
    }
}
