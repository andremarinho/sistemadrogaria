package br.com.drogaria.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import br.com.drogaria.dao.FuncionarioDAO;
import br.com.drogaria.dao.ItemDAO;
import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.dao.VendaDAO;
import br.com.drogaria.domain.Funcionario;
import br.com.drogaria.domain.Item;
import br.com.drogaria.domain.Produto;
import br.com.drogaria.domain.Venda;
import br.com.drogaria.filter.VendaFilter;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class VendaBean {

	private Venda vendaCadastro;
	
	private VendaFilter filtro;
	private List<Venda> listaVendasFiltradas;
	

	@ManagedProperty(value = "#{autenticacaoBean}")
	private AutenticacaoBean autenticacaoBean;

	private List<Produto> listaProdutos;
	private List<Produto> listaProdutosFiltrados;
	
	
	
	

	public List<Venda> getListaVendasFiltradas() {
		return listaVendasFiltradas;
	}

	public void setListaVendasFiltradas(List<Venda> listaVendasFiltradas) {
		this.listaVendasFiltradas = listaVendasFiltradas;
	}

	public VendaFilter getFiltro() {
		if(this.filtro == null){
			this.filtro = new VendaFilter();
		}
		
		return filtro;
	}

	public void setFiltro(VendaFilter filtro) {
		if(filtro==null){
			this.filtro = new VendaFilter();
		}
		this.filtro = filtro;
	}

	private List<Item> listaItens;

	public List<Item> getListaItens() {
		return listaItens;
	}

	public void setListaItens(List<Item> listaItens) {
		this.listaItens = listaItens;
	}

	public List<Produto> getListaProdutos() {
		if (listaItens == null) {
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

	public Venda getVendaCadastro() {

		if (vendaCadastro == null) {
			this.vendaCadastro = new Venda();
			this.vendaCadastro.setValor(new BigDecimal("0.00"));
			this.vendaCadastro.setQuantidade(0);
		}
		return vendaCadastro;
	}

	public void setVendaCadastro(Venda vendaCadastro) {
		this.vendaCadastro = vendaCadastro;
	}

	public AutenticacaoBean getAutenticacaoBean() {
		return autenticacaoBean;
	}

	public void setAutenticacaoBean(AutenticacaoBean autenticacaoBean) {
		this.autenticacaoBean = autenticacaoBean;
	}

	public void setListaProdutosFiltrados(List<Produto> listaProdutosFiltrados) {
		this.listaProdutosFiltrados = listaProdutosFiltrados;
	}

	public void carregarProdutos() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			this.listaProdutos = produtoDAO.listar();
		} catch (Exception e) {
			FacesUtil.adicionarMsgError("N�o foi possivel carregar os produtos. " + e.getMessage());
		}

	}

	public void selecionar(Produto produto) {

		int posicaoEncontrada = -1;

		for (int pos = 0; pos < this.listaItens.size() && posicaoEncontrada < 0; pos++) {

			Item itemTemp = this.listaItens.get(pos);

			if (itemTemp.getProduto().equals(produto)) {
				posicaoEncontrada = pos;
			}
		}

		Item item = new Item();
		item.setProduto(produto);

		if (posicaoEncontrada < 0) {
			item.setQuantidade(1);
			item.setValor(produto.getPreco());
			listaItens.add(item);

		} else {
			Item itemEncontrado = this.listaItens.get(posicaoEncontrada);
			item.setQuantidade(itemEncontrado.getQuantidade() + 1);
			item.setValor(produto.getPreco().multiply(new BigDecimal(item.getQuantidade())));
			listaItens.set(posicaoEncontrada, item);
		}

		vendaCadastro.setValor(vendaCadastro.getValor().add(produto.getPreco()));
		vendaCadastro.setQuantidade(vendaCadastro.getQuantidade()+1);
	}

	public void remover(Item item) {
		int posicaoEncontrada = -1;

		for (int pos = 0; pos < this.listaItens.size() && posicaoEncontrada < 0; pos++) {

			Item itemTemp = this.listaItens.get(pos);

			if (itemTemp.getProduto().equals(item.getProduto())) {
				posicaoEncontrada = pos;
			}
		}

		if (posicaoEncontrada > -1) {
			vendaCadastro.setValor(vendaCadastro.getValor().subtract(listaItens.get(posicaoEncontrada).getValor()));
			vendaCadastro.setQuantidade(vendaCadastro.getQuantidade()-item.getQuantidade());
			listaItens.remove(posicaoEncontrada);

		}
	}

	public void carregarDadosVenda() {
		vendaCadastro.setHorario(new Date());

		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		this.vendaCadastro
				.setFuncionario(funcionarioDAO.buscarPorCodigo(autenticacaoBean.getFuncionarioLogado().getCodigo()));

		RequestContext req = RequestContext.getCurrentInstance();
		req.execute("PF('wvdlgFinalizarVenda').show();");

	}

	public void salvar() {
		try {
			VendaDAO vendaDAO = new VendaDAO();
			Long codigoVenda = vendaDAO.salvar(vendaCadastro);
			Venda vendaFK = vendaDAO.buscarPorCodigo(codigoVenda);

			for (Item item : listaItens) {
				item.setVenda(vendaFK);
				ItemDAO itemDAO = new ItemDAO();
				itemDAO.salvar(item);
			}

			vendaCadastro = new Venda();
			vendaCadastro.setValor(new BigDecimal("0.00"));
			this.listaItens.clear();

			FacesUtil.adicionarMsgInfo("Venda salva com sucesso!!!");
		} catch (Exception e) {
			FacesUtil.adicionarMsgError("N�o foi possivel salvar a venda " + e.getMessage());
		}
	}
	
	public void buscar(){
		
		
		try {
			
			VendaDAO vendaDAO = new VendaDAO();
			this.listaVendasFiltradas = vendaDAO.buscar(filtro);
			
			
			
			
		}  catch (Exception e) {
			FacesUtil.adicionarMsgError("Erro ao tentar buscar uma venda " + e.getMessage());
		}
	}

}
