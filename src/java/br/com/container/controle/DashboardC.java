/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.container.controle;

import javax.annotation.PostConstruct;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BubbleChartModel;
import org.primefaces.model.chart.BubbleChartSeries;

/**
 *
 * @author adriano
 */
public class DashboardC {

    private BubbleChartModel bubbleModel2;

    @PostConstruct
    public void init() {
        createBubbleModels();
    }

    public BubbleChartModel getBubbleModel1() {
        BubbleChartModel bubbleModel1 = null;
        return bubbleModel1;
    }

    private void createBubbleModels() {
        BubbleChartModel bubbleModel1 = initBubbleModel();
        bubbleModel1.setTitle("Bubble Chart");
        bubbleModel1.getAxis(AxisType.X).setLabel("Price");
        Axis yAxis = bubbleModel1.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(250);
        yAxis.setLabel("Labels");

        bubbleModel2 = initBubbleModel();
        bubbleModel2.setTitle("Custom Options");
        bubbleModel2.setShadow(false);
        bubbleModel2.setBubbleGradients(true);
        bubbleModel2.setBubbleAlpha(0.8);
        bubbleModel2.getAxis(AxisType.X).setTickAngle(-50);
        yAxis = bubbleModel2.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(250);
        yAxis.setTickAngle(50);
    }

    private BubbleChartModel initBubbleModel() {
        BubbleChartModel model = new BubbleChartModel();

        model.add(new BubbleChartSeries("Acura", 70, 183, 55));
        model.add(new BubbleChartSeries("Alfa Romeo", 45, 92, 36));
        model.add(new BubbleChartSeries("AM General", 24, 104, 40));
        model.add(new BubbleChartSeries("Bugatti", 50, 123, 60));
        model.add(new BubbleChartSeries("BMW", 15, 89, 25));
        model.add(new BubbleChartSeries("Audi", 40, 180, 80));
        model.add(new BubbleChartSeries("Aston Martin", 70, 70, 48));

        return model;
    }
}
