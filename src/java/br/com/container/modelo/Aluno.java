/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.container.modelo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author adriano
 */
@Entity
@Table(name = "aluno")
@PrimaryKeyJoinColumn(name = "IdPessoa")
public class Aluno extends Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;
    
}
