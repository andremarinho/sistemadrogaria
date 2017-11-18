package br.com.drogaria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class ProdutoBean {

	private Produto produtoCadastro;
	private Long codigo;
	private String acao;
	
	
	private List<Produto> listaProdutos;
	private List<Produto> listaProdutosFiltrados;
	
	private List<Fabricante> listaFabricantes;
	
	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}
	
	public List<Produto> getListaProdutosFiltrados() {
		return listaProdutosFiltrados;
	}
	
	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}
	
	public void setListaProdutosFiltrados(List<Produto> listaProdutosFiltrados) {
		this.listaProdutosFiltrados = listaProdutosFiltrados;
	}
	
	public List<Fabricante> getListaFabricantes() {
		return listaFabricantes;
	}
	
	public void setListaFabricantes(List<Fabricante> listaFabricantes) {
		this.listaFabricantes = listaFabricantes;
	}
	
	
	
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public Produto getProdutoCadastro() {
		return produtoCadastro;
	}
	
	public void setProdutoCadastro(Produto produtoCadastro) {
		this.produtoCadastro = produtoCadastro;
	}
	
	public void carregarPesquisa(){
		ProdutoDAO produtoDAO = new ProdutoDAO();
		this.listaProdutos = produtoDAO.listar();
	}
	
	
	public void carregarProduto(){
		
		try {
			
			if(this.codigo!=null){
				ProdutoDAO produtoDAO = new ProdutoDAO();
				this.produtoCadastro = produtoDAO.buscarPorCodigo(codigo);
				}else{
					this.produtoCadastro = new Produto();
				}
			
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			this.listaFabricantes = fabricanteDAO.listar();
				
			}catch (Exception e) {
				FacesUtil.adicionarMsgError("Não possivel carregar o produto.");
				
			}finally {
				
			}
			
	}
	
	public void salvar(){
		try {
			
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.salvar(produtoCadastro);
			FacesUtil.adicionarMsgInfo("Produto salvo com sucesso!");
			
		} catch (Exception e) {
			FacesUtil.adicionarMsgError("Ocorreu um erro na gravação do produto");
		}
	}
	
	public void excluir(){
		try {
			
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.excluir(produtoCadastro);
			FacesUtil.adicionarMsgInfo("Produto "+produtoCadastro.getDescricao()+" excluido com sucesso.");
			
		} catch (Exception e) {
			FacesUtil.adicionarMsgError("Ocorreu um erro durante a exclusão do produto");
		}
	}
	
	public void editar(){
		try {
			
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.editar(produtoCadastro);
			FacesUtil.adicionarMsgInfo("O produto foi atualizado com sucesso!");
		} catch (Exception e) {
			FacesUtil.adicionarMsgError("Ocorreu um erro na atualização do produto.");
		}
	}
	
}
