package br.com.leandrosnazareth.pdvapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.leandrosnazareth.pdvapi.domain.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByNameRole(String nameRole);
}
