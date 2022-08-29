package br.com.leandrosnazareth.pdvapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.leandrosnazareth.pdvapi.domain.entity.Usuario;
import br.com.leandrosnazareth.pdvapi.domain.repository.UsuerRepository;

@Service
public class ImplementacaoUserDetailsSercice implements UserDetailsService {

    @Autowired
    private UsuerRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Consulta no banco o usuario
        Usuario usuario = usuarioRepository.findUserByUsername(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não foi encontrado");
        }
        return new User(usuario.getUsername(), usuario.getPassword(), usuario.getAuthorities());
    }
}
