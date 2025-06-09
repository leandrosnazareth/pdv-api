package br.com.leandrosnazareth.pdvapi.config;

import java.util.Collections;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.leandrosnazareth.pdvapi.domain.entity.Role;
import br.com.leandrosnazareth.pdvapi.domain.entity.Usuario;
import br.com.leandrosnazareth.pdvapi.domain.repository.RoleRepository;
import br.com.leandrosnazareth.pdvapi.domain.repository.UserRepository;

@Configuration
public class UserInitializer {

    @Bean
    CommandLineRunner initAdminUser(UserRepository usuarioRepository,
            RoleRepository roleRepository,
            PasswordEncoder encoder) {
        return args -> {
            if (usuarioRepository.count() == 0) {

                // Verifica ou cria a Role ADMIN
                Role adminRole = roleRepository.findByNameRole("ROLE_ADMIN");
                if (adminRole == null) {
                    adminRole = new Role("ROLE_ADMIN");
                    adminRole = roleRepository.save(adminRole);
                    System.out.println("Role ROLE_ADMIN criada.");
                }

                // Cria o usuário admin
                Usuario admin = new Usuario();
                admin.setUsername("admin");
                admin.setPassword(new BCryptPasswordEncoder().encode("123"));
                admin.setName("Administrador");
                admin.setRoles(Collections.singletonList(adminRole));

                usuarioRepository.save(admin);
                System.out.println("Usuário admin criado com sucesso.");
            } else {
                System.out.println("Usuários já existem. Nenhum admin criado.");
            }
        };
    }
}
