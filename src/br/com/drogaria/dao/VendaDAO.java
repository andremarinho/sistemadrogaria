package br.com.drogaria.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.drogaria.domain.Venda;
import br.com.drogaria.filter.VendaFilter;
import br.com.drogaria.util.HibernateUtil;

public class VendaDAO {

	public Long salvar(Venda venda) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		Long codigo = null;

		try {
			transacao = session.beginTransaction();
			codigo = (long) session.save(venda);
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

	@SuppressWarnings("unchecked")
	public List<Venda> buscar(VendaFilter filtro) {
		List<Venda> vendas = null;
		Session session = HibernateUtil.getSessionFactory().openSession();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT venda FROM Venda venda ");

		if (filtro.getDataInicial() != null && filtro.getDataFinal() != null) {
			sql.append("WHERE venda.horario BETWEEN :dataInicial AND :dataFinal ");
		}

		sql.append("ORDER BY venda.horario ");

		try {

			Query consulta = session.createQuery(sql.toString());

			if (filtro.getDataInicial() != null && filtro.getDataFinal() != null) {
				
				consulta.setDate("dataInicial", filtro.getDataInicial());
				consulta.setDate("dataFinal", filtro.getDataFinal());

			}

			vendas = consulta.list();
		} catch (Exception e) {
			throw e;
		} finally {
			session.close();
		}

		return vendas;
	}

}
