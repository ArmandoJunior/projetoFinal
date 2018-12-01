/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.container.dao;

import br.com.container.modelo.Card;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;


public class CardDaoImpl extends BaseDaoImpl<Card, Long>implements CardDao {

    @Override
    public Card pesquisaEntidadeId(Long id, Session session) throws HibernateException {
        return (Card) session.get(Card.class, id);
    }

    @Override
    public List<Card> listaTodos(Session session) throws HibernateException {
        return session.createQuery("FROM Card").list();
    }

    @Override
    public List<Card> pesquisaPorNome(String nome, Session session) throws HibernateException {
        Query consulta = session.createQuery("FROM Card c WHERE c.numero LIKE :numero");
        consulta.setParameter("numero", "%" + nome + "%");
        return consulta.list();
    }
    
}
