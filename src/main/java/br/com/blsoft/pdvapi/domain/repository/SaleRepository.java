package br.com.blsoft.pdvapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.blsoft.pdvapi.domain.entity.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

}
