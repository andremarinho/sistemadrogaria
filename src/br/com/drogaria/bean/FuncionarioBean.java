package br.com.drogaria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.codec.digest.DigestUtils;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.dao.FuncionarioDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Funcionario;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class FuncionarioBean {

	private Funcionario funcionarioCadastro = new Funcionario();

	private List<Funcionario> listaFuncionarios;
	private List<Funcionario> listaFuncionariosFiltrados;

	private String acao;
	private Long codigo;

	public Funcionario getFuncionarioCadastro() {
		return funcionarioCadastro;
	}

	public void setFuncionarioCadastro(Funcionario funcionarioCadastro) {
		this.funcionarioCadastro = funcionarioCadastro;
	}

	public List<Funcionario> getListaFuncionarios() {
		return listaFuncionarios;
	}

	public void setListaFuncionarios(List<Funcionario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}

	public List<Funcionario> getListaFuncionariosFiltrados() {
		return listaFuncionariosFiltrados;
	}

	public void setListaFuncionariosFiltrados(List<Funcionario> listaFuncionariosFiltrados) {
		this.listaFuncionariosFiltrados = listaFuncionariosFiltrados;
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
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			this.listaFuncionarios = funcionarioDAO.listar();

		} catch (Exception e) {
			FacesUtil.adicionarMsgError("Error ao tentar carregar pesquisa. " + e.getMessage());
		}
	}

	public void carregarFuncionario() {
		try {

			if (this.codigo != null) {

				FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
				this.funcionarioCadastro = funcionarioDAO.buscarPorCodigo(codigo);
			} else {

				this.funcionarioCadastro = new Funcionario();

			}
		} catch (Exception e) {
			FacesUtil.adicionarMsgError("Não foi possivel carregar fabricante. " + e.getMessage());
		}
	}

	public void novo() {
		funcionarioCadastro = new Funcionario();
	}

	public void salvar() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioCadastro.setSenha(DigestUtils.md5Hex(funcionarioCadastro.getSenha()));
			funcionarioDAO.salvar(funcionarioCadastro);
			funcionarioCadastro = new Funcionario();
			FacesUtil.adicionarMsgInfo("Funcionario salvo com sucesso!!!");
		} catch (Exception e) {

			FacesUtil.adicionarMsgError("Falha na gravação do funcionario. " + e.getMessage());
		}

	}

	public void excluir() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioDAO.excluir(funcionarioCadastro);
			this.funcionarioCadastro = new Funcionario();
			FacesUtil.adicionarMsgInfo("Funcionario excluido com sucesso!!!");
			this.codigo = null;

		} catch (Exception e) {

			FacesUtil.adicionarMsgError("Falha na exclusao do funcionario. " + e.getMessage());

		}

	}

	public void editar() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioCadastro.setSenha(DigestUtils.md5Hex(funcionarioCadastro.getSenha()));
			funcionarioDAO.editar(funcionarioCadastro);

			FacesUtil.adicionarMsgInfo("Funcionario editado com sucesso!!!");
		} catch (Exception e) {

			FacesUtil.adicionarMsgError("Falha ao tentar editar dados  do funcionario. " + e.getMessage());
		}

	}

}
