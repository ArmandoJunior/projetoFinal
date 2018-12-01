/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.container.controle;

import br.com.container.dao.AlunoDao;
import br.com.container.dao.AlunoDaoImpl;
import br.com.container.dao.CardDao;
import br.com.container.dao.CardDaoImpl;
import br.com.container.dao.CursoDao;
import br.com.container.dao.CursoDaoImpl;
import br.com.container.dao.HibernateUtil;
import br.com.container.modelo.Aluno;
import br.com.container.modelo.Card;
import br.com.container.modelo.Curso;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author arman
 */
@ManagedBean(name = "cardC")
@ViewScoped
public class CardControle implements Serializable {
    private Card carteirinha;
    private Session session;
    private CardDao cardDao;
    private DataModel<Card> modelCard;
    private List<Card> cards;
    
    private boolean mostra_toolbar;
    
    private List<Aluno> alunos;
    private AlunoDao alunoDao;
    private Aluno aluno;
    
    private List<Curso> cursos;
    private CursoDao cursoDao;
    private Curso curso;

    public void abrirSessao() {
        if (session == null) {
            session = HibernateUtil.abreSessao();
        } else if (!session.isOpen()) {
            session = HibernateUtil.abreSessao();
        }
    }

    public void novo() {
        mostra_toolbar = !mostra_toolbar;

    }

    public void novaPesquisa() {
        mostra_toolbar = !mostra_toolbar;
    }

    public void preparaAlterar() {
        mostra_toolbar = !mostra_toolbar;
    }

    public void pesquisar() {
        cardDao = new CardDaoImpl();
        try {
            abrirSessao();
            
            cards = cardDao.pesquisaPorNome(carteirinha.getNumero(), session);
            modelCard = new ListDataModel(cards);
            carteirinha.setNumero(null);
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar carteirinha por numero: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    public void limpar() {
        carteirinha = new Card();
        aluno = new Aluno();
        curso = new Curso();
    }

    //metodos getts e setts
    public void carregarParaAlterar() {
        mostra_toolbar = !mostra_toolbar;
        carteirinha = modelCard.getRowData();
        aluno = carteirinha.getAluno();
        curso = carteirinha.getCurso();
    }

    public void excluir() {

        carteirinha = modelCard.getRowData();
        cardDao = new CardDaoImpl();

        abrirSessao();
        try {
            cardDao.remover(carteirinha, session);
            cards.remove(carteirinha);
            modelCard = new ListDataModel(cards);
            Mensagem.excluir("Carteirinha");
            limpar();
        } catch (Exception e) {
            System.out.println("erro ao excluir: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    public void salvar() {
        abrirSessao();
        cardDao = new CardDaoImpl();
        alunoDao = new AlunoDaoImpl();
        aluno = alunoDao.pesquisaEntidadeId(aluno.getId(), session);
        curso = cursoDao.pesquisaEntidadeId(curso.getId(), session);
        try {
            carteirinha.setAluno(aluno);
            carteirinha.setCurso(curso);
            carteirinha.setInicio(new Date());
            carteirinha.setNumero(aluno.getCpf());
            
            cardDao.salvarOuAlterar(carteirinha, session);
            Mensagem.salvar("Carteirinha do Alulno " + carteirinha.getAluno().getNome());
            carteirinha = null;
            aluno = null;
            curso = null;

        } catch (Exception e) {
            System.out.println("Erro no salvar cardDao Controle "
                    + e.getMessage());
        } finally {
            session.close();
        }
    }
    
    public List<Curso> pesquisaCurso(String query) {
        abrirSessao();
        cursoDao = new CursoDaoImpl();
        try {
            cursos = cursoDao.pesquisaPorNome(query, session);
        } catch (HibernateException he) {
            System.out.println("Erro no pesquisaCurso Card Controle " + he.getMessage());
        } finally {
            session.close();
        }
        return cursos;
    }
    
    public List<Aluno> pesquisaAluno(String query) {
        abrirSessao();
        alunoDao = new AlunoDaoImpl();
        try {
            alunos = alunoDao.pesquisaPorNome(query, session);
        } catch (HibernateException he) {
            System.out.println("Erro no pesquisaCurso Card Controle " + he.getMessage());
        } finally {
            session.close();
        }
        return alunos;
    }

    public void limparTela() {
        limpar();
    }

    public Aluno getAluno() {
        if (aluno == null) {
            aluno = new Aluno();
        }
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public List<Card> getCard() {
        if (cards == null) {
            cards = new ArrayList();
        }
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public Curso getCurso() {
        if (this.curso == null) {
            this.curso = new Curso();
        }
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public boolean isMostra_toolbar() {
        return mostra_toolbar;
    }

    public void setMostra_toolbar(boolean mostra_toolbar) {
        this.mostra_toolbar = mostra_toolbar;
    }

    public DataModel<Card> getModelCard() {
        return modelCard;
    }

    public void setModelCard(DataModel<Card> modelCard) {
        this.modelCard = modelCard;
    }

    public Card getCarteirinha() {
        if (this.carteirinha == null) {
            this.carteirinha = new Card();
        }
        return carteirinha;
    }

    public void setCarteirinha(Card carteirinha) {
        this.carteirinha = carteirinha;
    }
    
}
