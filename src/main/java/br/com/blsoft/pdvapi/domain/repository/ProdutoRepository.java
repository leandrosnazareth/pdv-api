package br.com.blsoft.pdvapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.blsoft.pdvapi.domain.entity.Produto;

@RepositoryRestResource(collectionResourceRel ="produtos", path = "produto")
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

    Produto findByNome(@Param("nome") String nome);
    
}
