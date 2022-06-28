package br.com.blsoft.pdvapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.blsoft.pdvapi.domain.entity.Sale;
import br.com.blsoft.pdvapi.domain.repository.SaleRepository;

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
}
