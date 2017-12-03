package br.com.drogaria.teste;

import org.junit.Test;

import br.com.drogaria.dao.FuncionarioDAO;
import br.com.drogaria.domain.Funcionario;
import junit.framework.Assert;

public class FuncionarioDAOTest {

	
	@Test
	public void autenticar(){
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		
		Funcionario funcionario = funcionarioDAO.autenticar("111.111.111-86","123");
		
		Assert.assertNotNull(funcionario);
	}
}
