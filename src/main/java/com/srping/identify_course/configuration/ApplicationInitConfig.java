package com.srping.identify_course.configuration;

import com.srping.identify_course.Entity.User;
import com.srping.identify_course.Repository.UserRepository;
import com.srping.identify_course.enums.Role;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;
    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
          if(userRepository.findByUsername("admin").isEmpty()){
              var role = new HashSet<String>();
              role.add(Role.ADMIN.name());

              User user = User.builder()
                      .username("admin")
                      .password(passwordEncoder.encode("admin"))
                      .roles(role)
                      .build();

              userRepository.save(user);
              log.warn("admin user has been craated with default password: admin, please change it");
          }
        };
    }
}
