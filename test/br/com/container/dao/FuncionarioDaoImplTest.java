/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.container.dao;

import br.com.container.modelo.Funcionario;
import java.util.List;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author adriano
 */
public class FuncionarioDaoImplTest {
    private Funcionario funcionario;
    private FuncionarioDao funcionarioDao;
    private Session session;
    
    public FuncionarioDaoImplTest() {
        session = HibernateUtil.abreSessao();
        funcionarioDao = new FuncionarioDaoImpl();
    }
    
    @Test
    public void salvar(){
        
//            funcionario = new Funcionario(null, "Funcionario Teste", "funcionario@teste.com", null, null);
//            funcionarioDao.salvarOuAlterar(funcionario, session);
//            assertNotNull(funcionario.getId());
       
    }

    @Test
    public void testPesquisaEntidadeId() {
        System.out.println("pesquisaEntidadeId");
        Long id = null;
        Session session = null;
        FuncionarioDaoImpl instance = new FuncionarioDaoImpl();
        Funcionario expResult = null;
        Funcionario result = instance.pesquisaEntidadeId(id, session);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testListaTodos() {
        System.out.println("listaTodos");
        Session session = null;
        FuncionarioDaoImpl instance = new FuncionarioDaoImpl();
        List<Funcionario> expResult = null;
        List<Funcionario> result = instance.listaTodos(session);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testPesquisaPorNome() {
        System.out.println("pesquisaPorNome");
        String nome = "";
        Session session = null;
        FuncionarioDaoImpl instance = new FuncionarioDaoImpl();
        List<Funcionario> expResult = null;
        List<Funcionario> result = instance.pesquisaPorNome(nome, session);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
