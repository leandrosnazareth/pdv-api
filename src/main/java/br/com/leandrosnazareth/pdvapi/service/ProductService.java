package br.com.leandrosnazareth.pdvapi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.github.dozermapper.core.DozerBeanMapperBuilder;

import br.com.leandrosnazareth.pdvapi.domain.entity.Product;
import br.com.leandrosnazareth.pdvapi.domain.repository.ProductRepository;
import br.com.leandrosnazareth.pdvapi.dto.ProductDTO;

@Service
public class ProductService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductRepository productRepository;

    public Page<ProductDTO> findAllDto(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map((product -> DozerBeanMapperBuilder.buildDefault()// converte pag<Product> para pga<pageproductdto>
                        .map(product, ProductDTO.class)));
    }

    public List<ProductDTO> findAllActive() {
        // busca lista de produtos e mapeia para lista de produtosdto
        return productRepository.findByActive(true).stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    public ProductDTO save(ProductDTO productDto) {
        Product product = productRepository.save(modelMapper.map(productDto, Product.class));
        return modelMapper.map(product, ProductDTO.class);
    }

    public Optional<ProductDTO> findById(long id) {
        return productRepository.findById(id)
                .map(product -> modelMapper.map(product, ProductDTO.class));
    }

    public Optional<ProductDTO> findByName(String name) {
        return productRepository.findByName(name)
                .map(product -> modelMapper.map(product, ProductDTO.class));
    }

    public Optional<ProductDTO> findByIdAndActive(long id) {
        return productRepository.findByIdAndActive(id, true)
                .map(product -> modelMapper.map(product, ProductDTO.class));
    }

    public void delete(ProductDTO productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        productRepository.delete(product);
    }

    public Optional<ProductDTO> findByNameAndActive(String name) {
        return productRepository.findByNameAndActive(name, true)
                .map(product -> modelMapper.map(product, ProductDTO.class));
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
