/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.container.controle;

import br.com.container.dao.EmpresaDao;
import br.com.container.dao.EmpresaDaoImpl;
import br.com.container.dao.HibernateUtil;
import br.com.container.modelo.Empresa;
import br.com.container.modelo.Endereco;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;

import javax.faces.model.ListDataModel;
import org.hibernate.HibernateException;

/**
 *
 * @author adriano
 */
@ManagedBean(name = "empresaC")
@ViewScoped
public class EmpresaControle implements Serializable{

    private Empresa empresa;
    private EmpresaDao empresaDao;
    private Session session;
    private Endereco endereco;
    private DataModel<Empresa> modelEmpresas;
    private List<Empresa> empresas;
    private boolean mostra_toolbar;

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
        empresaDao = new EmpresaDaoImpl();
        try {
            abrirSessao();
            empresas = empresaDao.pesquisaPorNome(empresa.getNome(), session);
            modelEmpresas = new ListDataModel(empresas);
            empresa.setNome(null);
        } catch (Exception e) {
            System.out.println("erro ao pesquisar sala por nome: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    public void limpar() {
        empresa = new Empresa();
    }
    
    public void carregarParaAlterar() {
        mostra_toolbar = !mostra_toolbar;
        empresa = modelEmpresas.getRowData();
        endereco = empresa.getEndereco();
    }

    public void excluir() {
        empresa = modelEmpresas.getRowData();
        empresaDao = new EmpresaDaoImpl();

        try {
            abrirSessao();
            empresaDao.remover(empresa, session);
            empresas.remove(empresa);

            modelEmpresas = new ListDataModel(empresas);
            Mensagem.excluir("Empresa");
            limpar();
        } catch (Exception e) {
            System.out.println("erro ao excluir empresa: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    public void salvar() {
        empresaDao = new EmpresaDaoImpl();
        abrirSessao();

        try {
            empresa.setEndereco(endereco);
            endereco.setEmpresa(empresa);
            empresaDao.salvarOuAlterar(empresa, session);
            Mensagem.salvar("Empresa " + empresa.getNome());
            empresa = null;

        } catch (HibernateException e) {
            System.out.println("Erro ao salvar empresa " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro no salvar Empresa Controle " + e.getMessage());
        } finally {
            session.close();
        }
    }

    public Empresa getEmpresa() {
        if (empresa == null) {
            empresa = new Empresa();
        }
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Endereco getEndereco() {
        if (endereco == null) {
            endereco = new Endereco();
        }
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }

    public boolean isMostra_toolbar() {
        return mostra_toolbar;
    }

    public void setMostra_toolbar(boolean mostra_toolbar) {
        this.mostra_toolbar = mostra_toolbar;
    }

    public DataModel<Empresa> getModelEmpresas() {
        return modelEmpresas;
    }

    public void setModelEmpresas(DataModel<Empresa> modelEmpresas) {
        this.modelEmpresas = modelEmpresas;
    }
    
    

}
