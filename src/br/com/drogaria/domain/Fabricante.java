package br.com.drogaria.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "tbl_fabricante")
@NamedQueries({ @NamedQuery(name = "Fabricante.listar", query = "select fabricante FROM Fabricante fabricante"),
		@NamedQuery(name = "Fabricante.buscarPorCodigo", query = " select fabricante FROM Fabricante fabricante where fabricante.codigo = :codigo")

})
public class Fabricante {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "fab_codigo")
	private Long codigo;

	@NotEmpty(message = "O campo descrição é obrigatorio!")
	@Size(min = 5, max = 50, message = "Tamanho invalido para campo descrição, tem que ser de 5 a 50.")
	@Column(name = "fab_descricao", length = 50, nullable = false)
	private String descricao;

	@CPF(message="O CPF informado é invalido!")
	@Column(name = "fun_cpf", length = 14, nullable = false, unique = true)
	private String cpf;

	@NotEmpty(message = "O campo senha é obrigatorio!")
	@Size(min = 6, max = 8, message = "Tamanho invalido para campo senha, tem que ser de 6 a 8.")
	@Column(name = "fun_senha", length = 32, nullable = false)
	private String senha;

	@NotEmpty(message = "O campo função é obrigatorio!")
	@Column(name = "fun_funcao", length = 50, nullable = false)
	private String funcao;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
		return "Fabricante [codigo=" + codigo + ", descricao=" + descricao + "]";
	}

}
