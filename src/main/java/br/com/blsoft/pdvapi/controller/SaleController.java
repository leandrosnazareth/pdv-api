package br.com.blsoft.pdvapi.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.blsoft.pdvapi.config.SpringFoxConfig;
import br.com.blsoft.pdvapi.domain.entity.Sale;
import br.com.blsoft.pdvapi.exception.ResourceNotFoundException;
import br.com.blsoft.pdvapi.service.SaleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/pdv/sale")
@Api(tags = { SpringFoxConfig.SALE_TAG })
public class SaleController {

    @Autowired
    private SaleService saleService;

    @GetMapping("{id}")
    @ApiOperation(value = "Buscar venda pelo ID")
    public ResponseEntity<Sale> findSaleById(@PathVariable Long id)
            throws ResourceNotFoundException {
        Sale sale = saleService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado uma venda com id: " + id));
        return ResponseEntity.ok().body(sale);
    }

    @PostMapping
    @ApiOperation(value = "Salva uma venda")
    public Sale createSale(@Valid @RequestBody Sale sale) {
        return saleService.save(sale);
    }

    @ApiOperation(value = "Listar todas vendas ativas com paginação")
    @GetMapping
    public Page<Sale> findAllPagination(
            @RequestParam(value = "page", defaultValue = "0") Integer pagina,
            @RequestParam(value = "size", defaultValue = "5") Integer tamanhoPagina) {
        PageRequest pageRequest = PageRequest.of(pagina, tamanhoPagina);
        return saleService.findAll(pageRequest);
    }

    @ApiOperation(value = "Deletar venda pelo ID")
    @DeleteMapping("{id}")
    public Map<String, Boolean> deleteById(@PathVariable Long id)
            throws ResourceNotFoundException {
        Sale sale = saleService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrada uma venda com id: " + id));
        saleService.delete(sale);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PutMapping("{id}")
    @ApiOperation(value = "Atualizar venda")
    public ResponseEntity<Sale> updateSale(@PathVariable(value = "id") Long id,
            @Valid @RequestBody Sale sale) throws ResourceNotFoundException {
        saleService.findById(sale.getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Não foi encontrada uma venda com id: " + sale.getId()));
        return ResponseEntity.ok(saleService.save(sale));
    }

    @GetMapping("total/{createdAt}")
    @ApiOperation(value = "Listar valor total de vendas no mes")
    public BigDecimal findValorTotalMonthAndYear2(@Valid @RequestBody LocalDateTime createdAt) {
        return saleService.findValorTotalMonthAndYear2(createdAt);
    }

    @GetMapping("somatotal")
    @ApiOperation(value = "Listar valor total de vendas")
    public BigDecimal findValorTotalSales() {
        return saleService.findValorTotalSales();
    }
}
