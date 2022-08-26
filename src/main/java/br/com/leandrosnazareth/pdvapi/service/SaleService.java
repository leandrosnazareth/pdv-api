package br.com.leandrosnazareth.pdvapi.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

import br.com.leandrosnazareth.pdvapi.domain.entity.Sale;
import br.com.leandrosnazareth.pdvapi.domain.repository.SaleRepository;
import br.com.leandrosnazareth.pdvapi.dto.SaleDto;

@Service
public class SaleService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SaleRepository saletRepository;

    public SaleDto save(SaleDto saleDto) {
        Sale sale = saletRepository.save(modelMapper.map(saleDto, Sale.class));
        return modelMapper.map(sale, SaleDto.class);
    }

    public Page<SaleDto> findAll(Pageable pageable) {
        return saletRepository.findAll(pageable)
                .map((sale -> DozerBeanMapperBuilder.buildDefault()// converte pag<Sale> para page<saledto>
                        .map(sale, SaleDto.class)));
    }

    public Optional<SaleDto> findById(long id) {
        return saletRepository.findById(id)
                .map(sale -> modelMapper.map(sale, SaleDto.class));
    }

    public void delete(SaleDto saletDto) {
        Sale sale = modelMapper.map(saletDto, Sale.class);
        saletRepository.delete(sale);
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

    public List<SaleDto> findTopByOrderByIdDesc() {
        return saletRepository.findTop5ByOrderByIdDesc().stream()
                .map(sale -> modelMapper.map(sale, SaleDto.class))
                .collect(Collectors.toList());
    }
}
