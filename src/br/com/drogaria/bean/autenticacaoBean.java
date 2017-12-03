package br.com.drogaria.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.drogaria.dao.FuncionarioDAO;
import br.com.drogaria.domain.Funcionario;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@SessionScoped
public class autenticacaoBean {

	private Funcionario funcionarioLogado;
	
	private boolean mnTeste = true;
	
	

	public boolean isMnTeste() {
		return mnTeste;
	}

	public void setMnTeste(boolean mnTeste) {
		this.mnTeste = mnTeste;
	}

	public Funcionario getFuncionarioLogado() {
		if (this.funcionarioLogado == null) {
			this.funcionarioLogado = new Funcionario();
		}

		return funcionarioLogado;
	}

	public void setFuncionarioLogado(Funcionario funcionarioLogado) {
		this.funcionarioLogado = funcionarioLogado;
	}

	public void autenticar() {

		try {

			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			this.funcionarioLogado = funcionarioDAO.autenticar(funcionarioLogado.getCpf(),
					funcionarioLogado.getSenha());
			
			if (funcionarioLogado.getFuncao() == null) {
				FacesUtil.adicionarMsgError("Credenciais incorretas");
				
				
			} else {
				FacesUtil.adicionarMsgInfo("Usuario logado com sucesso!!! "+funcionarioLogado.getNome());
				
			}

			this.mnTeste = false;
			
		} catch (Exception e) {
			FacesUtil.adicionarMsgError("Erro ao tentar autenticar no sistema. " + e.getMessage());
		}
	}
}
