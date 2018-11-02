/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.container.dao;

import br.com.container.modelo.Empresa;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;


public class EmpresaDaoImpl implements EmpresaDao {

    @Override
    public void salvarOuAlterar(Empresa entidade, Session session) throws HibernateException {
    }

    @Override
    public void remover(Empresa entidade, Session session) throws HibernateException {
    }

    @Override
    public Empresa pesquisaEntidadeId(Long id, Session session) throws HibernateException {
        return null;
    }

    @Override
    public List<Empresa> listaTodos(Session session) throws HibernateException {
        return null;
    }

    @Override
    public List<Empresa> pesquisaPorNome(String nome, Session session) throws HibernateException {
        return null;
    }
    
}
