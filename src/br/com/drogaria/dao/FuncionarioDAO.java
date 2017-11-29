package br.com.drogaria.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.drogaria.domain.Funcionario;
import br.com.drogaria.util.HibernateUtil;

public class FuncionarioDAO {

	
	public void salvar(Funcionario funcionario){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = session.beginTransaction();
			session.save(funcionario);
			transacao.commit();
		}catch(Exception e ){
			if(transacao != null){
				transacao.rollback();
			}
			throw e;
		}finally {
			session.close();
		}
	}
	
	public void editar(Funcionario funcionario){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = session.beginTransaction();
			session.update(funcionario);
			transacao.commit();
		}catch(Exception e ){
			if(transacao != null){
				transacao.rollback();
			}
			throw e;
		}finally {
			session.close();
		}
	}
	
	public void excluir(Funcionario funcionario){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = session.beginTransaction();
			session.delete(funcionario);
			transacao.commit();
		}catch(Exception e ){
			if(transacao != null){
				transacao.rollback();
			}
			throw e;
		}finally {
			session.close();
		}
	}
	
	public List<Funcionario> listar(){
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query consulta;
		List<Funcionario> funcionarios;
		
		try {
			consulta = session.getNamedQuery("Funcionario.listar");
			funcionarios = consulta.list();
		} catch (Exception e) {
			throw e;
		}finally {
			session.close();
		}
		
		return funcionarios;
	}
	
	public Funcionario buscarPorCodigo(Long codigo){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Funcionario funcionario = null;
		
		try{
			Query query = session.getNamedQuery("Funcionario.buscarPorCodigo");
			query.setLong("codigo", codigo);
			funcionario = (Funcionario) query.uniqueResult();
		}catch(Exception e ){
			throw e;
		}finally {
			session.close();
		}
		return funcionario;
	}
	
	public Funcionario autenticar(String cpf, String senha){
		Funcionario funcionario = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try{
			Query query = session.getNamedQuery("Funcionario.autenticar");
			query.setString("cpf", cpf);
			query.setString("senha", senha);
			funcionario = (Funcionario) query.uniqueResult();
		}catch(Exception e ){
			throw e;
		}finally {
			session.close();
		}
		
		
		return funcionario;
				
		
		
	}
}
