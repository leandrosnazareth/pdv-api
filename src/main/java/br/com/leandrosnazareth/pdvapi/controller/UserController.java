package br.com.leandrosnazareth.pdvapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.leandrosnazareth.pdvapi.config.SpringFoxConfig;
import br.com.leandrosnazareth.pdvapi.domain.entity.Usuario;
import br.com.leandrosnazareth.pdvapi.dto.UserDTO;
import br.com.leandrosnazareth.pdvapi.dto.UserFullDTO;
import br.com.leandrosnazareth.pdvapi.exception.ResourceNotFoundException;
import br.com.leandrosnazareth.pdvapi.service.UserService;
import br.com.leandrosnazareth.pdvapi.util.MensageConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/pdv/user")
@Api(tags = { SpringFoxConfig.USER_TAG })
public class UserController {

    @Autowired
    private UserService usuarioService;

    @ApiOperation(value = "Buscar usuario pelo ID")
    @GetMapping("{id}")
    @CacheEvict(value = "cacheuserid", allEntries = true) // limpar o cache que não é utilizado a um tempo
    @CachePut("cacheuserid") // identificar as atualizações no bd e add ao cache
    public ResponseEntity<UserDTO> findUserByID(@PathVariable Long id)
            throws ResourceNotFoundException {
        UserDTO usuarioDTO = usuarioService.findByIdDTO(id)
                .orElseThrow(() -> new ResourceNotFoundException(MensageConstant.PRODUTO_NAO_ENCONTRADO + id));
        return ResponseEntity.ok().body(usuarioDTO);
    }

    @ApiOperation(value = "Deletar usuario pelo ID")
    @DeleteMapping("{id}")
    public Map<String, Boolean> deleteById(@PathVariable Long id) {
        Usuario usuario = usuarioService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(MensageConstant.PRODUTO_NAO_ENCONTRADO + id));
        usuarioService.delete(usuario);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @ApiOperation(value = "Listar todos usuarios")
    @GetMapping
    public List<UserDTO> findAllUsuario() {
        return usuarioService.findAll();
    }

    @ApiOperation(value = "Listar todos usuarios")
    @GetMapping("/testeuser/{id}")
    public Usuario findcomplete(@PathVariable Long id) {
        return usuarioService.findById(id).get();
    }

    @ApiOperation(value = "Criar/cadastrar novo usuario")
    @PostMapping
    public UserDTO createUsuraio(@RequestBody UserFullDTO usuarioFullDTO) {
        String senhacriptografada = new BCryptPasswordEncoder().encode(usuarioFullDTO.getPassword());
        usuarioFullDTO.setPassword(senhacriptografada);
        return usuarioService.save(usuarioFullDTO);
    }

    @ApiOperation(value = "Atualizar usuario")
    @PutMapping
    public UserDTO atualizar(@RequestBody UserFullDTO usuarioFullDTO) {
        Usuario usuarioTemporadrio = usuarioService.findById(usuarioFullDTO.getId()).orElseThrow(
                () -> new ResourceNotFoundException(MensageConstant.PRODUTO_NAO_ENCONTRADO + usuarioFullDTO.getId()));
        if (!usuarioFullDTO.getPassword().equals(usuarioTemporadrio.getPassword())) { // se Senhas for diferentes
            String senhaCriptografada = new BCryptPasswordEncoder().encode(usuarioFullDTO.getPassword());
            usuarioFullDTO.setPassword(senhaCriptografada);
        }
        return usuarioService.save(usuarioFullDTO);
    }
}
