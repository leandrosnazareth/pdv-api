package br.com.blsoft.pdvapi.domain.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.blsoft.pdvapi.domain.entity.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT SUM(amount) FROM Sale")
    BigDecimal findValorTotalSales();

    // @Query("SELECT SUM(amount) Total_Amount, MONTH(transactiondate) `MONTH`,  YEAR(transactiondate) `YEAR` FROM Sale GROUP BY MONTH(transactiondate), YEAR(transactiondate)")
    // String findValorTotalMonthAndYear();

    @Query("SELECT MONTHNAME(createdAt), SUM(amount) FROM Sale GROUP BY YEAR(createdAt), MONTH(createdAt)")
    BigDecimal findValorTotalMonthAndYear2(@Param("createdAt") LocalDateTime createdAt);

    long count();
}
