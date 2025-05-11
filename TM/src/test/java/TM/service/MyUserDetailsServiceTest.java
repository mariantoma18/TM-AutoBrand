package TM.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import TM.model.User;
import TM.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

@SpringBootTest
class MyUserDetailsServiceTest {

  @Mock private UserRepository userRepository;

  @InjectMocks
  private MyUserDetailsService myUserDetailsService;

  @Test
  void testLoadUserByUsernameWhenUserExists() {
    User user = new User();
    user.setUsername("Test");
    user.setPassword("123");

    when(userRepository.findByUsername("Test")).thenReturn(Optional.of(user));

    UserDetails userDetails = myUserDetailsService.loadUserByUsername("Test");


    assertEquals("Test", userDetails.getUsername());
  }

  @Test
  void testLoadUserByUsernameWhenUserDoesNotExist() {

    when(userRepository.findByUsername("Test")).thenReturn(Optional.empty());

    RuntimeException exception = assertThrows(RuntimeException.class, () ->
            myUserDetailsService.loadUserByUsername("Test")
    );

    assertEquals("User not found by username: Test", exception.getMessage());
  }
}
