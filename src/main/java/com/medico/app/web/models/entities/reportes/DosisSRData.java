package com.medico.app.web.models.entities.reportes;

import java.util.List;

public class DosisSRData {
    private String seriesname;
    private List<Integer> data;

    public DosisSRData(String seriesname, List<Integer> data) {
        this.seriesname = seriesname;
        this.data = data;
    }

    public String getSeriesname() {
        return seriesname;
    }

    public void setSeriesname(String seriesname) {
        this.seriesname = seriesname;
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }
}
