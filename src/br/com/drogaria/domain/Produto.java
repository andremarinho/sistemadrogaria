package br.com.drogaria.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tbl_produto")
@NamedQueries({@NamedQuery(name="Produto.listar",query="select produto FROM Produto produto"),
	@NamedQuery(name="Produto.buscarPorCodigo",query="select produto FROM Produto produto WHERE codigo = :codigo")
})

public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pro_codigo")
	private Long codigo;

	@NotEmpty(message="Prenchimento � obrigatorio da descri��o!")
	@Size(min=5,max=50, message="A descri��o deve ter de 5 a 50 caracteres.")
	@Column(name = "pro_descricao", length = 50, nullable = false)
	private String descricao;

	@NotNull(message="O campo pre�o � obrigatorio!")
	@DecimalMin(value="0.00", message="Informe um valor maior ou igual a zero para o compo pre�o.")
	@DecimalMax(value="99999.99",message="Informe um valor menor que R$ 99999,99 para o campo pre�o.")
	@Column(name = "pro_preco", precision = 7, scale = 2, nullable = false)
	private BigDecimal preco;

	@NotNull(message="O campo quantidade � obrigatorio.")
	@Min(value=0, message="Informe um valor maior ou igual a zero para o campo quantidade.")
	@Max(value=9999, message="Informe um valor menor que 10.000 para o campo quantidade.")
	@Column(name = "pro_quantidade", nullable = false)
	private Integer quantidade;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tbl_fabricante_fab_cod", referencedColumnName = "fab_codigo")
	private Fabricante fabricante;
	
	

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return descricao;
	}

	public void setNome(String nome) {
		this.descricao = nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	@Override
	public String toString() {
		return "Produto [codigo=" + codigo + ", nome=" + descricao + ", preco=" + preco + ", quantidade=" + quantidade
				+ ", fabricante=" + fabricante + "]";
	}
	
	

}
