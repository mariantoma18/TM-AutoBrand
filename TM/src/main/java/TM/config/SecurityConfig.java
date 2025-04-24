package TM.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf(c -> c.disable())
        .httpBasic(Customizer.withDefaults())
        .formLogin(
            form ->
                form.loginPage("/login")
                    .loginProcessingUrl("/login")
                    .defaultSuccessUrl("/", false)
                    .permitAll())
        .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/cart/**").authenticated()
                .anyRequest().permitAll());
    return http.build();
  }

  @Bean
  public PasswordEncoder delegatingPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
