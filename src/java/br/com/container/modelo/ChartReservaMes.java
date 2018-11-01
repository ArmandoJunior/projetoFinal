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
public class ChartReservaMes implements Serializable{
    private int mes;
    private Long totalDeSalas;

    public ChartReservaMes(int mes, Long totalDeSalas) {
        this.mes = mes;
        this.totalDeSalas = totalDeSalas;
    }

    public ChartReservaMes() {
    }
    

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public Long getTotalDeSalas() {
        return totalDeSalas;
    }

    public void setTotalDeSalas(Long totalDeSalas) {
        this.totalDeSalas = totalDeSalas;
    }
    
    
    
}
