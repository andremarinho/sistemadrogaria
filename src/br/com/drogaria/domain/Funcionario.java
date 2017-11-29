package br.com.drogaria.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "tbl_funcionario")
@NamedQueries({
	@NamedQuery(name="Funcionario.listar", query="SELECT funcionario FROM Funcionario funcionario"),
	@NamedQuery(name="Funcionario.buscarPorCodigo", query="SELECT funcionario FROM Funcionario funcionario WHERE codigo = :codigo"),
	@NamedQuery(name="Funcionario.autenticar", query="SELECT  funcionario  FROM Funcionario funcionario WHERE funcionario.cpf = :cpf AND funcionario.senha = :senha")
	
})

public class Funcionario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "fun_codigo")
	private Long codigo;

	@NotEmpty(message="O campo nome é requerido o preenchimento!")
	@Column(name = "fun_nome", length = 50, nullable = false)
	private String nome;

	@NotEmpty(message="O CPF tem que ser preenchido.")
	@CPF(message="O CPF digitado não é valido!")
	@Column(name = "cpf", length = 14, nullable = false, unique = true)
	private String cpf;

	@NotEmpty(message="O campo senha é requerido o preenchimento!")
	@Column(name = "fun_senha", length = 32, nullable = false)
	private String senha;

	@NotEmpty(message="O campo funcao é requerido o preenchimento!")
	@Column(name = "fun_funcao", length = 50, nullable = false)
	private String funcao;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	@Override
	public String toString() {
		return "Funcionario [codigo=" + codigo + ", nome=" + nome + ", cpf=" + cpf + ", senha=" + senha + ", funcao="
				+ funcao + "]";
	}
	
	

}
