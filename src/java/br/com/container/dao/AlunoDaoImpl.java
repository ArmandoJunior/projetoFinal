/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.container.dao;

import br.com.container.dao.BaseDaoImpl;
import br.com.container.modelo.Aluno;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;


public class AlunoDaoImpl extends BaseDaoImpl<Aluno, Long>implements AlunoDao, Serializable {

  

    @Override
    public Aluno pesquisaEntidadeId(Long id, Session session) throws HibernateException {
        return (Aluno) session.get(Aluno.class, id);
    }

    @Override
    public List<Aluno> listaTodos(Session session) throws HibernateException {
        return session.createQuery("FROM Aluno").list();
    }

    @Override
    public List<Aluno> pesquisaPorNome(String nome, Session session) throws HibernateException {
        Query consulta = session.createQuery("FROM Aluno a WHERE a.nome LIKE :nome");
        consulta.setParameter("nome", "%" + nome + "%");
        return consulta.list();
    }
    
}
