package br.com.container.controle;

import br.com.container.dao.HibernateUtil;
import br.com.container.dao.PlanejamentoTemplateDao;
import br.com.container.dao.PlanejamentoTemplateDaoImpl;
import br.com.container.modelo.AtividadeTemplate;
import br.com.container.modelo.PlanejamentoTemplate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author silvio
 */
@ManagedBean(name = "planejaTemplateC")
@ViewScoped
public class PlanejamentoTemplateControle implements Serializable {

    private PlanejamentoTemplate planejamentoTemplate;
    private PlanejamentoTemplateDao planejamentoTemplateDao;
    private AtividadeTemplate atividadeTemplate;
    private Session sessao;
    private DataModel<PlanejamentoTemplate> modelPlanejamentoTemplates;
    private List<PlanejamentoTemplate> planejamentoTemplates;
    private boolean mostra_toolbar;

    private void abreSessao() {
        if (sessao == null) {
            sessao = HibernateUtil.abreSessao();
        } else if (!sessao.isOpen()) {
            sessao = HibernateUtil.abreSessao();
        }
    }

    public void novo() {
        mostra_toolbar = !mostra_toolbar;
        planejamentoTemplate.setAtividadeTemplates(new ArrayList<>());

    }

    public void novaPesquisa() {
        mostra_toolbar = !mostra_toolbar;
    }

    public void preparaAlterar() {
        mostra_toolbar = !mostra_toolbar;
    }

    public void pesquisar() {
        planejamentoTemplateDao = new PlanejamentoTemplateDaoImpl();
        try {
            abreSessao();
            planejamentoTemplates = planejamentoTemplateDao.
                 pesquisaPorNome(planejamentoTemplate.getNome(), sessao);
            modelPlanejamentoTemplates = new ListDataModel(planejamentoTemplates);
            planejamentoTemplate.setNome(null);
        } catch (Exception e) {
            System.out.println("erro ao pesquisar PlanejamentoTemplate por nome: " + e.getMessage());
        } finally {
            sessao.close();
        }
    }

    public void limpar() {
        planejamentoTemplate = new PlanejamentoTemplate();
    }

    public void carregarParaAlterar() {
        mostra_toolbar = !mostra_toolbar;
        planejamentoTemplate = modelPlanejamentoTemplates.getRowData();
    }

    public void excluir() {
        planejamentoTemplate = modelPlanejamentoTemplates.getRowData();
        planejamentoTemplateDao = new PlanejamentoTemplateDaoImpl();
        abreSessao();
        try {
            planejamentoTemplateDao.remover(planejamentoTemplate, sessao);
            planejamentoTemplates.remove(planejamentoTemplate);
            modelPlanejamentoTemplates = new ListDataModel(planejamentoTemplates);
            Mensagem.excluir("PlanejamentoTemplate");
            limpar();
        } catch (Exception e) {
            System.out.println("erro ao excluir PlanejamentoTemplate: " + e.getMessage());
        } finally {
            sessao.close();
        }
    }

    public void salvar() {
        planejamentoTemplateDao = new PlanejamentoTemplateDaoImpl();
        
        carregarPlanejamentoParaAtividade();
        abreSessao();
        try {
            planejamentoTemplateDao.salvarOuAlterar(planejamentoTemplate, sessao);
            Mensagem.salvar("PlanejamentoTemplate " + planejamentoTemplate.getNome());
            planejamentoTemplate = null;
        } catch (HibernateException e) {
            System.out.println("Erro ao PlanejamentoTemplate Sala " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro no salvar PlanejamentoTemplate Controle " + e.getMessage());
        } finally {
            sessao.close();
        }
    }
    private void carregarPlanejamentoParaAtividade(){
        for (AtividadeTemplate atiTemps : planejamentoTemplate.getAtividadeTemplates()) {
            atiTemps.setPlanejamentoTemplate(planejamentoTemplate);
        }
    }

    public void limparTela() {
        limpar();
    }
    
    public void addAtividadeTemplate(){
        planejamentoTemplate.getAtividadeTemplates().add(atividadeTemplate);
        atividadeTemplate = new AtividadeTemplate();
    }

    //getters e setters
    public PlanejamentoTemplate getPlanejamentoTemplate() {
        if (planejamentoTemplate == null) {
            planejamentoTemplate = new PlanejamentoTemplate();
        }
        return planejamentoTemplate;
    }

    public void setPlanejamentoTemplate(PlanejamentoTemplate planejamentoTemplate) {
        this.planejamentoTemplate = planejamentoTemplate;
    }

    public DataModel<PlanejamentoTemplate> getModelPlanejamentoTemplates() {
        return modelPlanejamentoTemplates;
    }

    public void setModelPlanejamentoTemplates(DataModel<PlanejamentoTemplate> modelPlanejamentoTemplates) {
        this.modelPlanejamentoTemplates = modelPlanejamentoTemplates;
    }

    public boolean isMostra_toolbar() {
        return mostra_toolbar;
    }

    public void setMostra_toolbar(boolean mostra_toolbar) {
        this.mostra_toolbar = mostra_toolbar;
    }

    public List<PlanejamentoTemplate> getPlanejamentoTemplates() {
        return planejamentoTemplates;
    }

    public AtividadeTemplate getAtividadeTemplate() {
        if (atividadeTemplate == null) {
            atividadeTemplate = new AtividadeTemplate();
        }
        return atividadeTemplate;
    }

    public void setAtividadeTemplate(AtividadeTemplate atividadeTemplate) {
        this.atividadeTemplate = atividadeTemplate;
    }
    

}