/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.container.controle;

import br.com.container.dao.AlunoDao;
import br.com.container.dao.AlunoDaoImpl;
import br.com.container.dao.HibernateUtil;
import br.com.container.modelo.Aluno;
import br.com.container.modelo.Card;
import br.com.container.modelo.Curso;
import br.com.container.modelo.Endereco;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.hibernate.Session;

/**
 *
 * @author adriano
 */
@ManagedBean(name = "alunoC")
@ViewScoped
public class AlunoControle implements Serializable {

    private Aluno aluno;
    private Session session;
    private AlunoDao alunoDao;
    private DataModel<Aluno> modelAluno;
    private List<Aluno> alunos;

    private boolean mostra_toolbar;
    private Endereco endereco;
    private Card carteirinha;
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
        alunoDao = new AlunoDaoImpl();

        try {
            abrirSessao();
            
            alunos = alunoDao.pesquisaPorNome(aluno.getNome(), session);
            modelAluno = new ListDataModel(alunos);
            aluno.setNome(null);
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar aluno por nome: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    public void limpar() {
        aluno = new Aluno();
        endereco = new Endereco();
    }

    //metodos getts e setts
    public void carregarParaAlterar() {
        mostra_toolbar = !mostra_toolbar;
        aluno = modelAluno.getRowData();
        endereco = aluno.getEndereco();

    }

    public void excluir() {

        aluno = modelAluno.getRowData();
        alunoDao = new AlunoDaoImpl();

        abrirSessao();
        try {
            alunoDao.remover(aluno, session);
            alunos.remove(aluno);
            modelAluno = new ListDataModel(alunos);
            Mensagem.excluir("Aluno");
            limpar();
        } catch (Exception e) {
            System.out.println("erro ao excluir: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    public void salvar() {
        alunoDao = new AlunoDaoImpl();
        try {
            aluno.setEndereco(endereco);
            endereco.setPessoa(aluno);
            
            abrirSessao();
            alunoDao.salvarOuAlterar(aluno, session);
            Mensagem.salvar("Aluno " + aluno.getNome());
            aluno = null;
            endereco = null;

        } catch (Exception e) {
            System.out.println("Erro no salvar alunoDao Controle "
                    + e.getMessage());
        } finally {
            session.close();
        }
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

    public List<Aluno> getAlunos() {
        if (alunos == null) {
            alunos = new ArrayList();
        }
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public Endereco getEndereco() {
        if (this.endereco == null) {
            this.endereco = new Endereco();
        }
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public boolean isMostra_toolbar() {
        return mostra_toolbar;
    }

    public void setMostra_toolbar(boolean mostra_toolbar) {
        this.mostra_toolbar = mostra_toolbar;
    }

    public DataModel<Aluno> getModelAluno() {
        return modelAluno;
    }

    public void setModelAluno(DataModel<Aluno> modelAluno) {
        this.modelAluno = modelAluno;
    }

}
