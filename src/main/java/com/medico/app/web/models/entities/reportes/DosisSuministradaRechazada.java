package com.medico.app.web.models.entities.reportes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DosisSuministradaRechazada {
    private List<CategoriaEstadoDosis> category;
    private List<DosisSRData> dataset;

    public DosisSuministradaRechazada() {
        this.category = new ArrayList<>();
        this.dataset = new ArrayList<>();
    }

    public List<CategoriaEstadoDosis> getCategory() {
        return category;
    }

    public void setCategory(List<CategoriaEstadoDosis> category) {
        this.category = category;
    }

    public List<DosisSRData> getDataset() {
        return dataset;
    }

    public void setDataset(List<DosisSRData> dataset) {
        this.dataset = dataset;
    }

    public void setDaysInList(LocalDate firstDayOfWeek){
        String[] weekdays = new String[]{"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};
        this.category.add(new CategoriaEstadoDosis(weekdays[0] + "  " + firstDayOfWeek.getDayOfMonth()));
        for (int i=1; i<=6; i++){
            String day = weekdays[i] + "  " + firstDayOfWeek.plusDays(i).getDayOfMonth();
            this.category.add(new CategoriaEstadoDosis(day));
        }
    }
    public void addDosisData(DosisSRData dosisSRData){
        this.dataset.add(dosisSRData);
    }
}
