package br.com.blsoft.pdvapi.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import br.com.blsoft.pdvapi.domain.entity.Product;

@RepositoryRestResource(collectionResourceRel = "products", path = "product")
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByName(String name);

    Optional<Product> findByIdAndActive(Long id, Boolean active);

    // verificar se o nome da room não existe no banco, para cadastrar nova
    Optional<Product> findByNameAndActive(String name, Boolean active);

    // desativar sem deletar
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE Product r SET r.active = false WHERE r.id = :productId")
    void deactivate(@Param("productId") Long productId);

    // @Modifying(clearAutomatically = true)
    // @Query("update RssFeedEntry feedEntry set feedEntry.read =:isRead where feedEntry.id =:entryId")
    // void markEntryAsRead(@Param("entryId") Long rssFeedEntryId, @Param("isRead") boolean isRead);
}
