/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.container.controle;

import br.com.container.dao.HibernateUtil;
import br.com.container.dao.ReservaDao;
import br.com.container.dao.ReservaDaoImpl;
import br.com.container.modelo.ChartReservaMes;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import org.hibernate.Session;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author adriano
 */
@ManagedBean(name = "chartReservaC")
public class ChartReversaControle implements Serializable {

    private BarChartModel barModel;
    private HorizontalBarChartModel horizontalBarModel;

    private Session session;           // Sao atributos de banco
    private ReservaDao reservaDao;

    List<ChartReservaMes> totalMesSala = new ArrayList<>();

    private void abreSession() {
        if (session == null) {
            session = HibernateUtil.abreSessao();
        } else if (!session.isOpen()) {
            session = HibernateUtil.abreSessao();
        }
    }
    
    

    @PostConstruct
    public void init() {
        createBarModels();
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public HorizontalBarChartModel getHorizontalBarModel() {
        return horizontalBarModel;
    }

    private BarChartModel initBarModel() {
        
        abreSession();
        reservaDao = new ReservaDaoImpl();
        totalMesSala = reservaDao.totalMesSala(session);
        
        
        BarChartModel model = new BarChartModel();
        ChartSeries meses = new ChartSeries();
        meses.setLabel("Reservas efetuadas");
        
        for (ChartReservaMes mes : totalMesSala) {
            meses.set(mes.getMes(), mes.getTotalDeSalas());
        }
        
        model.addSeries(meses);

        return model;
    }

    private void createBarModels() {
        createBarModel();
        createHorizontalBarModel();
    }

    private void createBarModel() {
        barModel = initBarModel();

        barModel.setTitle("Bar Chart");
        barModel.setLegendPosition("ne");

        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Meses");

        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Quatidade");
        yAxis.setMin(0);
        yAxis.setMax(150);
    }

    private void createHorizontalBarModel() {
        abreSession();
        reservaDao = new ReservaDaoImpl();
        totalMesSala = reservaDao.totalMesSala(session);
        
        horizontalBarModel = new HorizontalBarChartModel();
        ChartSeries meses = new ChartSeries();
        meses.setLabel("Reservas efetuadas");
        
        for (ChartReservaMes mes : totalMesSala) {
            meses.set(mes.getMes(), mes.getTotalDeSalas());
        }
        

        horizontalBarModel.addSeries(meses);

        horizontalBarModel.setTitle("Horizontal and Stacked");
        horizontalBarModel.setLegendPosition("e");
        horizontalBarModel.setStacked(true);

        Axis xAxis = horizontalBarModel.getAxis(AxisType.X);
        xAxis.setLabel("Quantidade");
        xAxis.setMin(0);
        xAxis.setMax(150);

        Axis yAxis = horizontalBarModel.getAxis(AxisType.Y);
        yAxis.setLabel("Meses");
    }
}
