package br.com.leandrosnazareth.pdvapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.leandrosnazareth.pdvapi.config.SpringFoxConfig;
import br.com.leandrosnazareth.pdvapi.dto.ProductDTO;
import br.com.leandrosnazareth.pdvapi.exception.ResourceNotFoundException;
import br.com.leandrosnazareth.pdvapi.service.ProductService;
import br.com.leandrosnazareth.pdvapi.util.MensageConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/pdv/product")
@Api(tags = { SpringFoxConfig.PRODUCT_TAG })
public class ProductController {

    @Autowired
    private ProductService productService;

    @ApiOperation(value = "Listar todos produtos com paginação")
    @GetMapping
    public Page<ProductDTO> findAllPagination(
            @RequestParam(value = "page", defaultValue = "0") Integer pagina,
            @RequestParam(value = "size", defaultValue = "5") Integer tamanhoPagina) {
        PageRequest pageRequest = PageRequest.of(pagina, tamanhoPagina);
        return productService.findAllDto(pageRequest);
    }

    @ApiOperation(value = "Listar todos produtos ativos")
    @GetMapping("all")
    public List<ProductDTO> findAllActive() {
        return productService.findAllActive();
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Buscar produto pelo ID")
    public ResponseEntity<ProductDTO> findProductById(@PathVariable Long id)
            throws ResourceNotFoundException {
        // retornar um Optional<prductDto> e converte para productDto, em caso nulo
        ProductDTO product = productService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(MensageConstant.PRODUTO_NAO_ENCONTRADO + id));
        return ResponseEntity.ok().body(product);
    }

    @GetMapping("active/{id}")
    @ApiOperation(value = "Buscar produto ativo pelo ID")
    public ResponseEntity<ProductDTO> findByIDAndActive(@PathVariable Integer id)
            throws ResourceNotFoundException {
        // retornar um Optional<prductDto> e converte para productDto, em caso nulo
        // retorna a exception
        ProductDTO productDto = productService.findByIdAndActive(id)
                .orElseThrow(() -> new ResourceNotFoundException(MensageConstant.PRODUTO_NAO_ENCONTRADO + id));
        return ResponseEntity.ok().body(productDto);
    }

    @GetMapping("name/{name}")
    @ApiOperation(value = "Buscar qualquer produto pelo nome")
    public ResponseEntity<ProductDTO> findByName(@PathVariable String name)
            throws ResourceNotFoundException {
        // retornar um Optional<prductDto> e converte para productDto, em caso nulo
        // retorna a exception
        ProductDTO productDto = productService.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException(MensageConstant.PRODUTO_NAO_ENCONTRADO + name));
        return ResponseEntity.ok().body(productDto);
    }

    @GetMapping("name/active/{name}")
    @ApiOperation(value = "Buscar produto ativos pelo nome")
    public ResponseEntity<ProductDTO> findByNameAndActive(@PathVariable String name)
            throws ResourceNotFoundException {
        // retornar um Optional<prductDto> e converte para productDto, em caso nulo
        // retorna a exception
        ProductDTO productDto = productService.findByNameAndActive(name)
                .orElseThrow(() -> new ResourceNotFoundException(MensageConstant.PRODUTO_NAO_ENCONTRADO + name));
        return ResponseEntity.ok().body(productDto);
    }

    @PostMapping
    @ApiOperation(value = "Salva um produto")
    public ProductDTO createProduct(@Valid @RequestBody ProductDTO productDto) {
        return productService.save(productDto);
    }

    @ApiOperation(value = "Deletar produto pelo ID")
    @DeleteMapping("{id}")
    public Map<String, Boolean> deleteById(@PathVariable Long id) {
        ProductDTO productDto = productService.findByIdAndActive(id)
                .orElseThrow(() -> new ResourceNotFoundException(MensageConstant.PRODUTO_NAO_ENCONTRADO + id));
        productService.delete(productDto);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PutMapping("{id}")
    @ApiOperation(value = "Atualizar produto")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable(value = "id") Long id,
            @Valid @RequestBody ProductDTO productDto) throws ResourceNotFoundException {
        productService.findById(productDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        MensageConstant.PRODUTO_NAO_ENCONTRADO + productDto.getId()));
        return ResponseEntity.ok(productService.save(productDto));
    }

    @PutMapping("desativar/{id}")
    @ApiOperation(value = "Desativar um produto")
    @ResponseStatus(HttpStatus.CREATED)
    public void deactivate(@Valid @RequestBody Long id) {
        productService.deactivate(id);
    }

    @ApiOperation(value = "Retornar quantidade de Produtos")
    @GetMapping("count")
    public long countProducts() {
        return productService.countProducts();
    }

    @ApiOperation(value = "Retornar quantidade de Produtos Ativos")
    @GetMapping("active")
    public long countProductActive() {
        return productService.countProductActive();
    }
}
