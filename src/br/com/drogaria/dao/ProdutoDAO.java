package br.com.drogaria.dao;

import java.util.List;

import javax.transaction.Transaction;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.drogaria.domain.Produto;
import br.com.drogaria.util.HibernateUtil;

public class ProdutoDAO {

	public void salvar(Produto produto) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		org.hibernate.Transaction transacao = null;
		try {
			transacao = session.beginTransaction();
			session.save(produto);
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

	public List<Produto> listar() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		org.hibernate.Transaction transacao = null;
		List<Produto> produtos = null;
		try {
			Query query = session.getNamedQuery("Produto.listar");
			produtos = query.list();

		} catch (Exception e) {

			throw e;
		} finally {
			session.close();
		}

		return produtos;
	}

	public Produto buscarPorCodigo(Long codigo) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		org.hibernate.Transaction transacao = null;
		Produto produto = null;
		try {
			Query consulta = session.getNamedQuery("Produto.buscarPorCodigo");
			consulta = consulta.setLong("codigo", codigo);
			produto = (Produto) consulta.uniqueResult();

		} catch (Exception e) {

			throw e;
		} finally {
			session.close();
		}

		return produto;
	}
	
	public void excluir(Produto produto) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		org.hibernate.Transaction transacao = null;
		try {
			transacao = session.beginTransaction();
			session.delete(produto);
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
