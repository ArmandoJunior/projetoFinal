/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.container.modelo;

import java.io.Serializable;

/**
 *
 * @author adriano
 */
public class GraficoReservaPorMesSala implements Serializable{
    private int mes;
    private int quantidade;

    public GraficoReservaPorMesSala(int mes, int quantidade) {
        this.mes = mes;
        this.quantidade = quantidade;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    
    
}
