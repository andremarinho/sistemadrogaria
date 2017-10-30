package br.com.drogaria.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.drogaria.domain.Item;
import br.com.drogaria.util.HibernateUtil;

public class ItemDAO {

	public void salvar(Item item) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = session.beginTransaction();
			session.save(item);
			transacao.commit();
		} catch (Exception e) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw e;
		} finally {
			session.close();
		}
	}
	
	public void excluir(Item item) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = session.beginTransaction();
			session.delete(item);
			transacao.commit();
		} catch (Exception e) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw e;
		} finally {
			session.close();
		}
	}
	
	public void atualizar(Item item) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = session.beginTransaction();
			session.update(item);
			transacao.commit();
		} catch (Exception e) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw e;
		} finally {
			session.close();
		}
	}
	
	
	public List<Item> listar(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query consulta= session.getNamedQuery("Item.listar");
		List<Item> listaItens = consulta.list();
		session.close();
		return listaItens;
	}
	
	public Item buscarPorCodigo(Long codigo){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query consulta = session.getNamedQuery("Item.buscarPorCodigo");
		return (Item) consulta.uniqueResult();
	}

}
