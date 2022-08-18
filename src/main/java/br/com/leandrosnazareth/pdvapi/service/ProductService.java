package br.com.leandrosnazareth.pdvapi.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.leandrosnazareth.pdvapi.domain.entity.Product;
import br.com.leandrosnazareth.pdvapi.domain.repository.ProductRepository;
import br.com.leandrosnazareth.pdvapi.dto.ProductDto;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product save(ProductDto productDto) {
        ModelMapper modelMapper = new ModelMapper();
        Product product = modelMapper.map(productDto, Product.class);
        return productRepository.save(product);
    }

    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
    
    public Optional<Product> findById(long id) {
        return productRepository.findById(id);
    }

    public Optional<Product> findByName(String name) {
        return productRepository.findByName(name);
    }
    
    public void delete(Product product) {
        productRepository.delete(product);
    }
    
    public List<Product> findAllActive() {
        return productRepository.findByActive(true);
    }

    public Optional<Product> findByIdAndActive(long id) {
        return productRepository.findByIdAndActive(id, true);
    }

    public Optional<Product> findByNameAndActive(String name) {
        return productRepository.findByNameAndActive(name, true);
    }

    public void deactivate(@Valid Long id) {
        productRepository.deactivate(id);
    }

    public long countProducts() {
        return productRepository.count();
    }

    public long countProductActive() {
        return productRepository.countProductActive();
    }
}
