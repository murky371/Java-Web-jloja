package br.com.jloja.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.jloja.entity.FabricanteEntity;
import br.com.jloja.util.HibernateUtil;

public class FabricanteDAO {

	public void adicionar(FabricanteEntity fabricante){
		
		Session sessao  = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
		    transacao = sessao.beginTransaction();
			sessao.save(fabricante);
			transacao.commit();
		} catch(Exception ex){
			if(transacao != null) {
				transacao.rollback();
			}
		} finally {
			sessao.close();
		}
	}

public void editar(FabricanteEntity fabricante){
	Session sessao = HibernateUtil.getSessionFactory().openSession();
	Transaction transacao = null;
	
	try{
		transacao = sessao.beginTransaction();
		sessao.update(fabricante);
		transacao.commit();
	} catch(Exception ex){
		if (transacao != null){
			transacao.rollback();
		}
	} finally {
		sessao.close();
	}
}

public void excluir(FabricanteEntity fabricante){
	Session sessao = HibernateUtil.getSessionFactory().openSession();
	Transaction transacao = null;
	
	try{
		transacao = sessao.beginTransaction();
		sessao.delete(fabricante);
		transacao.commit();
	} catch(Exception ex){
		if (transacao != null){
			transacao.rollback();
		}
	} finally {
		sessao.close();
	}
}

public FabricanteEntity buscarPorCodigo(Long codigo){
	Session sessao = HibernateUtil.getSessionFactory().openSession();
	FabricanteEntity fabricante = null;
	
	try{
		Query consulta = sessao.getNamedQuery("FabricanteEntity.buscarPorCodigo");
		consulta.setLong("codigo", codigo);
		fabricante = (FabricanteEntity) consulta.uniqueResult();
	} catch (RuntimeException ex){
		throw ex;
		
	} finally {
		sessao.close();
	}
	return fabricante;
}

@SuppressWarnings("unchecked")
public List<FabricanteEntity> listar() {
	
	Session sessao = HibernateUtil.getSessionFactory().openSession();
	List<FabricanteEntity> fabricantes = null;
	
	try {
		Query consulta = sessao.getNamedQuery("FabricanteEntity.listar");
		fabricantes = consulta.list();
	} catch (RuntimeException ex){
		
		throw ex;
	} finally {
		sessao.close();
	}
	
	return fabricantes;
}
	
	public static void main(String[] args) {
    FabricanteEntity fab1 = new FabricanteEntity();
	fab1.setDescricao("Teste5");
	FabricanteDAO fabDAO = new FabricanteDAO();
	fabDAO.adicionar(fab1);

		//FabricanteDAO dao = new FabricanteDAO();
		//FabricanteEntity fab1 = dao.buscarPorCodigo(codigo);
	    //dao.excluir(fab1);
	}
}


