/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.container.dao;

import br.com.container.modelo.Empresa;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;


public class EmpresaDaoImpl extends BaseDaoImpl<Empresa, Long> implements EmpresaDao, Serializable  {
    private Empresa empresa;

    @Override
    public Empresa pesquisaEntidadeId(Long id, Session session) throws HibernateException {
         empresa = (Empresa) session.get(Empresa.class, id);
        return empresa;
    }

    @Override
    public List<Empresa> listaTodos(Session session) throws HibernateException {
        Query consulta = session.createQuery("from Empresa");
        return consulta.list();
    }

    @Override
    public List<Empresa> pesquisaPorNome(String nome, Session session) throws HibernateException {
        Query consulta = session.createQuery("from Empresa e where e.nome like :nome");
        consulta.setParameter("nome", "%" + nome + "%");
        return consulta.list();
    }
    
}
