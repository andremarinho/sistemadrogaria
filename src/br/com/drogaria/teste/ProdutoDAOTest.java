package br.com.drogaria.teste;

import java.math.BigDecimal;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;

public class ProdutoDAOTest {

	
	@Test
	@Ignore
	public void inserir(){
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscarPorCodigo(2L);
		
		Produto produto = new Produto();
		produto.setNome("Coca Cola");
		produto.setPreco(new BigDecimal(23.55));
		produto.setQuantidade(17);
		produto.setFabricante(fabricante);
		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.salvar(produto);
	}
	
	@Test
	@Ignore
	public void buscarPorCodigo(){
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscarPorCodigo(1L);
		System.out.println(produto);
	}
	
	@Test
	@Ignore
	public void listar(){
		ProdutoDAO produtoDAO = new ProdutoDAO();
		System.out.println(produtoDAO.listar());
	}
	
	@Test
	@Ignore
	public void excluir(){
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscarPorCodigo(1L);
		produtoDAO.excluir(produto);
	}
}
