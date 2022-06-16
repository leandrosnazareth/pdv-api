package br.com.blsoft.pdvapi.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.blsoft.pdvapi.domain.entity.Product;
import br.com.blsoft.pdvapi.domain.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public List<Product> findAllActive() {
        return productRepository.findByActive(true);
    }

    public Optional<Product> findById(long id) {
        return productRepository.findById(id);
    }

    public Optional<Product> findByIdAndActive(long id) {
        return productRepository.findByIdAndActive(id, true);
    }

    public Optional<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    public Optional<Product> findByNameAndActive(String name) {
        return productRepository.findByNameAndActive(name, true);
    }
    
    public void delete(Product product) {
        productRepository.delete(product);
    }

    public void deactivate(@Valid Long id) {
        productRepository.deactivate(id);
    }
}
