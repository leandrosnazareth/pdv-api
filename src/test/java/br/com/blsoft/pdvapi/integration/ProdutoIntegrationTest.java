package br.com.blsoft.pdvapi.integration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.blsoft.pdvapi.domain.entity.Produto;
import br.com.blsoft.pdvapi.domain.repository.ProdutoRepository;

@ActiveProfiles(profiles = "integration-test")
@SpringBootTest(classes = br.com.blsoft.pdvapi.PdvApiApplication.class)
public class ProdutoIntegrationTest {

    @Autowired
    ProdutoRepository produtoRepository;

    @Test
    public void testFindallProdutos(){
       assertNotNull(produtoRepository.findAll());
    }

    @Test
    public void testCreteProduto(){
        // var produto = Produto.builder().nome("nome")
    }

}
