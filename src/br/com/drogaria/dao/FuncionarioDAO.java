package br.com.drogaria.dao;

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
}