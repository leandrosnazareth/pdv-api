package br.com.blsoft.pdvapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.blsoft.pdvapi.domain.entity.Produto;

@RepositoryRestResource(path = "produto")
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    
}
