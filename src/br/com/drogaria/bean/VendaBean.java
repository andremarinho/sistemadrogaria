package br.com.drogaria.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.domain.Item;
import br.com.drogaria.domain.Produto;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class VendaBean {

	private List<Produto> listaProdutos;
	private List<Produto> listaProdutosFiltrados;
	
	private List<Item> listaItens;
	
	
	public List<Item> getListaItens() {
		return listaItens;
	}
	
	public void setListaItens(List<Item> listaItens) {
		this.listaItens = listaItens;
	}

	public List<Produto> getListaProdutos() {
		if(listaItens == null){
			this.listaItens = new ArrayList<>();
		}
		
		return listaProdutos;
	}

	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

	public List<Produto> getListaProdutosFiltrados() {
		return listaProdutosFiltrados;
	}

	public void setListaProdutosFiltrados(List<Produto> listaProdutosFiltrados) {
		this.listaProdutosFiltrados = listaProdutosFiltrados;
	}

	public void carregarProdutos() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			this.listaProdutos = produtoDAO.listar();
		} catch (Exception e) {
			FacesUtil.adicionarMsgError("Não foi possivel carregar os produtos. " + e.getMessage());
		}

	}
	
	
	public void selecionar(Produto produto){
		Item item = new Item();
		item.setProduto(produto);
		item.setQuantidade(1);
		item.setValor(produto.getPreco());
		
		listaItens.add(item);
		
	}

}
