package br.com.leandrosnazareth.pdvapi.domain.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.leandrosnazareth.pdvapi.domain.entity.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT SUM(amount) FROM Sale")
    BigDecimal findValorTotalSales();

    @Query("SELECT MONTHNAME(createdAt), SUM(amount) FROM Sale GROUP BY YEAR(createdAt), MONTH(createdAt)")
    BigDecimal findValorTotalMonthAndYear2(@Param("createdAt") LocalDateTime createdAt);

    long count();

    List<Sale> findTop5ByOrderByIdDesc();
}
