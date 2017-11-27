package br.com.drogaria.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.drogaria.domain.Venda;
import br.com.drogaria.util.HibernateUtil;

public class VendaDAO {

	public Long salvar(Venda venda) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		Long codigo = null;

		try {
			transacao = session.beginTransaction();
			codigo = (long)session.save(venda);
			transacao.commit();
		} catch (Exception e) {

			if (transacao != null) {
				transacao.rollback();
			}

			throw e;
		} finally {
			session.close();
		}
		
		return codigo;
	}

	public List<Venda> listar() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Venda> vendas = null;

		try {
			Query query = session.getNamedQuery("Venda.listar");
			vendas = query.list();
		} catch (Exception e) {
			throw e;
		} finally {
			session.close();
		}

		return vendas;
	}

	public Venda buscarPorCodigo(Long codigo) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Venda venda = null;

		try {
			Query query = session.getNamedQuery("Venda.buscarPorCodigo");
			query.setLong("codigo", codigo);
			venda = (Venda) query.uniqueResult();
		} catch (Exception e) {
			throw e;
		} finally {
			session.close();
		}

		return venda;
	}
	
	public void excluir(Venda venda) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = session.beginTransaction();
			session.delete(venda);
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
	
	public void editar(Venda venda) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = session.beginTransaction();
			session.update(venda);
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
	

}
