/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.container.dao;

import br.com.container.modelo.AtividadeTemplate;
import br.com.container.modelo.PlanejamentoTemplate;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author adriano
 */
public class PlanejamentoTemplateDaoImplTest {
    private PlanejamentoTemplate template;
    private PlanejamentoTemplateDao templateDao;
    private Session session;
    public PlanejamentoTemplateDaoImplTest() {
        templateDao = new PlanejamentoTemplateDaoImpl();
        
    }
    
    @Test
    public void testSalvar(){
        
        session = HibernateUtil.abreSessao();
        template = new PlanejamentoTemplate();
        
        AtividadeTemplate atividade1 = new AtividadeTemplate();
        AtividadeTemplate atividade2 = new AtividadeTemplate();
        
        List<AtividadeTemplate> atividadeTemplates = new ArrayList<>();
        atividadeTemplates.add(atividade1);
        atividadeTemplates.add(atividade2);
        
        template.setAtividadeTemplates(atividadeTemplates);
        
        template.setNome("Coordenador Graduação");
        template.setDescricao("Todos Coordenador da Graduação");
        
        atividade1.setName("1 reuniao NDE");
        atividade1.setDescricao("É FEITA NO 1 MES DE SEMESTRE");
        
        atividade2.setName("1 reuniao NDE");
        atividade2.setDescricao("É FEITA NO 1 MES DE SEMESTRE");
        
        for (AtividadeTemplate atividadeTemplate : atividadeTemplates) {
            atividadeTemplate.setPlanejamentoTemplate(template);
        }
    }

    //@Test
    public void testPesquisaEntidadeId() {
        System.out.println("pesquisaEntidadeId");
        Long id = null;
        Session session = null;
        PlanejamentoTemplateDaoImpl instance = new PlanejamentoTemplateDaoImpl();
        PlanejamentoTemplate expResult = null;
        PlanejamentoTemplate result = instance.pesquisaEntidadeId(id, session);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

   // @Test
    public void testListaTodos() {
        System.out.println("listaTodos");
        Session session = null;
        PlanejamentoTemplateDaoImpl instance = new PlanejamentoTemplateDaoImpl();
        List<PlanejamentoTemplate> expResult = null;
        List<PlanejamentoTemplate> result = instance.listaTodos(session);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

   // @Test
    public void testPesquisaPorNome() {
        System.out.println("pesquisaPorNome");
        String nome = "";
        Session session = null;
        PlanejamentoTemplateDaoImpl instance = new PlanejamentoTemplateDaoImpl();
        List<PlanejamentoTemplate> expResult = null;
        List<PlanejamentoTemplate> result = instance.pesquisaPorNome(nome, session);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
