package com.dh.SessionBookingSystem.security;

import com.dh.SessionBookingSystem.entity.Usuario;
import com.dh.SessionBookingSystem.entity.UsuarioRole;
import com.dh.SessionBookingSystem.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CargarDatosUsuario implements ApplicationRunner {
    private UserRepository userRepository;

    @Autowired
    public CargarDatosUsuario(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder cifrador = new BCryptPasswordEncoder();
        BCryptPasswordEncoder cifradorAdmin = new BCryptPasswordEncoder();

        String passCifrada = cifrador.encode("1234");
        String passCifradaAdmin = cifradorAdmin.encode("1234");

        Usuario usuario = new Usuario("Muelas", "Muelas", "muelas@gmail.com", passCifrada, UsuarioRole.ROLE_USER);
        userRepository.save(usuario);

        Usuario usuarioAdmin = new Usuario("Nahuel", "Gonzalez", "glnahuel17@gmail.com", passCifradaAdmin, UsuarioRole.ROLE_ADMIN);
        userRepository.save(usuarioAdmin);
    }
}