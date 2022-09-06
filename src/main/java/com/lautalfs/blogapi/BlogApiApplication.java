package com.lautalfs.blogapi;

import com.lautalfs.blogapi.config.AppConstants;
import com.lautalfs.blogapi.entity.RoleEntity;
import com.lautalfs.blogapi.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
public class BlogApiApplication implements CommandLineRunner {

    static Logger logger;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(BlogApiApplication.class, args);
    }
    @Bean
    public ModelMapper modelmapper(){
        return new ModelMapper();
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(this.passwordEncoder.encode("xyz"));

        /*try{
            RoleEntity role = new RoleEntity();
            role.setId(AppConstants.ROLE_ADMIN);
            role.setName("ADMIN");

            RoleEntity role1 = new RoleEntity();
            role1.setId(AppConstants.ROLE_USER);
            role1.setName("USER");

            RoleEntity role2 = new RoleEntity();
            role2.setId(AppConstants.ROLE_TEST);
            role2.setName("TESTER");

            var roles = List.of(role1, role, role2);
            roleRepository.saveAll(roles);
            roles.forEach(r -> System.out.println(r.getName()));

        }catch(Exception e){
            e.printStackTrace();
        }*/
    }
}
