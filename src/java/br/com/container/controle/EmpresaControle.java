/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.container.controle;

import br.com.container.dao.EmpresaDao;
import br.com.container.dao.HibernateUtil;
import br.com.container.modelo.Empresa;
import br.com.container.modelo.Endereco;
import java.util.List;
import org.hibernate.Session;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import javax.faces.model.DataModel;

/**
 *
 * @author adriano
 */
@ManagedBean(name = "empresaC")
@ViewScoped
public class EmpresaControle {

    private Empresa empresa;
    private EmpresaDao empresaDao;
    private Session session;
    private Endereco endereco;

    private DataModel<Empresa> modelEmpresa;

    private List<Empresa> empresas;
    private boolean mostra_toolbar;

    public void abrirSessao(){
        if (session == null) {
            session = HibernateUtil.abreSessao();
        } else if(!session.isOpen()) {
            session = HibernateUtil.abreSessao();
        }
    }
    
}
