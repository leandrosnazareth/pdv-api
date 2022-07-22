package br.com.leandrosnazareth.pdvapi.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.leandrosnazareth.pdvapi.domain.entity.Sale;
import br.com.leandrosnazareth.pdvapi.domain.repository.SaleRepository;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saletRepository;

    public Sale save(Sale sale) {
        return saletRepository.save(sale);
    }

    public Page<Sale> findAll(Pageable pageable) {
        return saletRepository.findAll(pageable);
    }

    public Optional<Sale> findById(long id) {
        return saletRepository.findById(id);
    }

    public void delete(Sale salet) {
        saletRepository.delete(salet);
    }

    public BigDecimal findValorTotalMonthAndYear2(@Valid LocalDateTime createdAt) {
        return saletRepository.findValorTotalMonthAndYear2(createdAt);
    }

    public BigDecimal findValorTotalSales() {
        return saletRepository.findValorTotalSales();
    }

    public long countSales() {
        return saletRepository.count();
    }

    public List<Sale> findTopByOrderByIdDesc() {
        return saletRepository.findTop5ByOrderByIdDesc();
    }
}
