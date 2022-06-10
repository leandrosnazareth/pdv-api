package br.com.blsoft.pdvapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.blsoft.pdvapi.domain.entity.Sale;
import io.swagger.annotations.Api;

@Api(tags = "Sale")
@RepositoryRestResource(collectionResourceRel = "sales", path = "sale")
public interface SaleRepository extends JpaRepository<Sale, Long> {
}
