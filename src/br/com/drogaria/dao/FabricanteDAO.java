package br.com.drogaria.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import org.springframework.aop.ThrowsAdvice;

import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.util.HibernateUtil;

public class FabricanteDAO {

	public void salvar(Fabricante fabricante) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = session.beginTransaction();
			session.save(fabricante);
			transacao.commit();
		} catch (Exception ex) {
			if (transacao != null) {
				transacao.rollback();
			}

			throw ex;

		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Fabricante> listar() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Fabricante> fabricantes = null;
		Query consulta = null;

		try {
			consulta = session.getNamedQuery("Fabricante.listar");
			fabricantes = consulta.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
		return fabricantes;
	}

	public Fabricante buscarPorCodigo(Long codigo) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Fabricante fabricante = null;
		Query consulta = null;

		try {
			consulta = session.getNamedQuery("Fabricante.buscarPorCodigo");
			consulta.setLong("codigo", codigo);
			fabricante = (Fabricante) consulta.uniqueResult();

		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
		return fabricante;
	}

	public void excluir(Fabricante fabricante) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = session.beginTransaction();
			session.delete(fabricante);
			transacao.commit();
		} catch (Exception ex) {
			if (transacao != null) {
				transacao.rollback();
			}

			throw ex;

		} finally {
			session.close();
		}
	}

	

	public void editar(Fabricante fabricante) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = session.beginTransaction();
			Fabricante fabricanteEditar = buscarPorCodigo(fabricante.getCodigo());
			fabricanteEditar.setDescricao(fabricante.getDescricao());
			session.update(fabricanteEditar);
			transacao.commit();
		} catch (Exception ex) {
			if (transacao != null) {
				transacao.rollback();
			}

			throw ex;

		} finally {
			session.close();
		}
	}

}
