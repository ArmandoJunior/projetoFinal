/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.container.dao;

import br.com.container.modelo.Aluno;
import br.com.container.modelo.Endereco;
import br.com.container.modelo.Pessoa;
import java.util.List;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author adriano
 */
public class AlunoDaoImplTest {
    
    private Aluno aluno;
    private Pessoa pessoa;
    private AlunoDao alunoDao;
    private Session session;
    private Endereco endereco;
    
    public AlunoDaoImplTest() {
        alunoDao = new AlunoDaoImpl();
    }
    @Test
    public void testSalvar(){
        System.out.println("Salvando Aluno");
       aluno = new Aluno();
        
        
        alunoDao.salvarOuAlterar(aluno, session);
    }

    //@Test
    public void testPesquisaEntidadeId() {
        System.out.println("pesquisaEntidadeId");
        Long id = null;
        Session session = null;
        AlunoDaoImpl instance = new AlunoDaoImpl();
        Aluno expResult = null;
        Aluno result = instance.pesquisaEntidadeId(id, session);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    //@Test
    public void testListaTodos() {
        System.out.println("listaTodos");
        Session session = null;
        AlunoDaoImpl instance = new AlunoDaoImpl();
        List<Aluno> expResult = null;
        List<Aluno> result = instance.listaTodos(session);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    //@Test
    public void testPesquisaPorNome() {
        System.out.println("pesquisaPorNome");
        String nome = "";
        Session session = null;
        AlunoDaoImpl instance = new AlunoDaoImpl();
        List<Aluno> expResult = null;
        List<Aluno> result = instance.pesquisaPorNome(nome, session);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
