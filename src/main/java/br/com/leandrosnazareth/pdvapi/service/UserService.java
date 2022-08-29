package br.com.leandrosnazareth.pdvapi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.leandrosnazareth.pdvapi.domain.entity.Usuario;
import br.com.leandrosnazareth.pdvapi.domain.repository.UsuerRepository;
import br.com.leandrosnazareth.pdvapi.dto.UserDTO;
import br.com.leandrosnazareth.pdvapi.dto.UserFullDTO;

@Service
public class UserService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UsuerRepository usuarioRepository;

    public Optional<UserDTO> findByIdDTO(long id) {
        return usuarioRepository.findById(id)
                .map(usuario -> modelMapper.map(usuario, UserDTO.class));
    }

    public Optional<Usuario> findById(long id) {
        return usuarioRepository.findById(id);
    }

    public void delete(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }

    public List<UserDTO> findAll() {
        return usuarioRepository.findAll().stream()
                .map(usuario -> modelMapper.map(usuario, UserDTO.class))
                .collect(Collectors.toList());
    }

    public UserDTO save(UserFullDTO usuarioFullDTO) {
        Usuario usuario = modelMapper.map(usuarioFullDTO, Usuario.class);
        return modelMapper.map(usuarioRepository.save(usuario), UserDTO.class);
    }
}