package br.com.leandrosnazareth.pdvapi.dto;

import java.util.List;

import br.com.leandrosnazareth.pdvapi.domain.entity.Role;
import lombok.Data;

@Data
public class UserFullDTO {
    private Long id;
    private String username;
    private String password;
    private String name;
    private String token = "";
    private List<Role> roles;
}