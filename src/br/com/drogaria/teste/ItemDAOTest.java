package br.com.drogaria.teste;

import java.math.BigDecimal;

import org.junit.Test;

import br.com.drogaria.dao.ItemDAO;
import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.dao.VendaDAO;
import br.com.drogaria.domain.Item;

public class ItemDAOTest {

	@Test
	public void salvar() {
		ItemDAO itemDAO = new ItemDAO();
		Item item = new Item();
		ProdutoDAO produtoDAO = new ProdutoDAO();
		item.setProduto(produtoDAO.buscarPorCodigo(1L));
		item.setQuantidade(2);
		item.setValor(new BigDecimal(12.54D));
		VendaDAO vendaDAO = new VendaDAO();
		item.setVenda(vendaDAO.buscarPorCodigo(2L));
		itemDAO.salvar(item);
	}

}
