package br.com.leandrosnazareth.pdvapi.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.leandrosnazareth.pdvapi.domain.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByActive(boolean active);

    Optional<Product> findByIdAndActive(Long id, Boolean active);

    // busca produto por nome ativo ou inativo
    Optional<Product> findByNameAndActive(String name, Boolean active);

    Optional<Product> findByName(String name);

    // desativar sem deletar
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE Product r SET r.active = false WHERE r.id = :productId")
    void deactivate(@Param("productId") Long productId);

    Page<Product> findAllByActive(Pageable pageable, boolean active);

    long count();

    @Query("SELECT COUNT(*) FROM Product WHERE active = true")
    long countProductActive();
}
