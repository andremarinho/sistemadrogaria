package br.com.drogaria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class FabricanteBean {

	private Fabricante fabricanteCadastro;

	private List<Fabricante> listaFabricante;
	private List<Fabricante> listaFabricanteFiltrados;

	private String acao;
	private Long codigo;

	public Fabricante getFabricanteCadastro() {

		return fabricanteCadastro;
	}

	public void setFabricanteCadastro(Fabricante fabricanteCadastro) {
		this.fabricanteCadastro = fabricanteCadastro;
	}

	public List<Fabricante> getListaFabricante() {
		return listaFabricante;
	}

	public List<Fabricante> getListaFabricanteFiltrados() {
		return listaFabricanteFiltrados;
	}

	public void setListaFabricante(List<Fabricante> listaFabricante) {
		this.listaFabricante = listaFabricante;
	}

	public void setListaFabricanteFiltrados(List<Fabricante> listaFabricanteFiltrados) {
		this.listaFabricanteFiltrados = listaFabricanteFiltrados;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}
	
	public Long getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public void carregarPesquisa() {
		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			this.listaFabricante = fabricanteDAO.listar();
		} catch (Exception e) {
			FacesUtil.adicionarMsgError("Error ao tentar carregar pesquisa. " + e.getMessage());
		}
	}

	public void carregarFabricante() {
		try {
			

			if (this.codigo != null) {

				FabricanteDAO fabricanteDAO = new FabricanteDAO();
				this.fabricanteCadastro = fabricanteDAO.buscarPorCodigo(codigo);
			} else {

				this.fabricanteCadastro = new Fabricante();

			}
		} catch (Exception e) {
			FacesUtil.adicionarMsgError("Não foi possivel carregar fabricante. " + e.getMessage());
		}
	}

	public void novo() {
		fabricanteCadastro = new Fabricante();
	}

	public void salvar() {
		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricanteDAO.salvar(fabricanteCadastro);
			fabricanteCadastro = new Fabricante();
			FacesUtil.adicionarMsgInfo("Fabricante salvo com sucesso!!!");
		} catch (Exception e) {

			FacesUtil.adicionarMsgError("Falha na gravação do fabricante. " + e.getMessage());
		}

	}

	public void excluir() {
		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricanteDAO.excluir(fabricanteCadastro);
			this.fabricanteCadastro = new Fabricante();
			FacesUtil.adicionarMsgInfo("Fabricante excluido com sucesso!!!");
			this.codigo =null;
			
		} catch (Exception e) {

			FacesUtil.adicionarMsgError("Falha na exclusao do fabricante. " + e.getMessage());
			
		}

	}

	public void editar() {
		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();

			fabricanteDAO.editar(fabricanteCadastro);

			FacesUtil.adicionarMsgInfo("Fabricante editado com sucesso!!!");
		} catch (Exception e) {

			FacesUtil.adicionarMsgError("Falha ao tentar editar dados  do fabricante. " + e.getMessage());
		}

	}

}
