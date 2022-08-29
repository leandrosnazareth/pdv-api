package br.com.leandrosnazareth.pdvapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.leandrosnazareth.pdvapi.domain.entity.Usuario;

@Repository
public interface UsuerRepository  extends JpaRepository<Usuario, Long> {	
	//1 referencia primeiro paramentro
	@Query("select u from Usuario u where u.username = ?1")
	Usuario findUserByUsername(String username);

	Usuario findByUsername(String username);

	//1 e 2 para referenciar os paramentros
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "update usuario set token = ?1 where username = ?2")
	void atualizaTokenUser(String token, String username);
}