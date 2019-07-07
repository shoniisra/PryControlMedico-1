package com.medico.app.web.models.entities.reportes;

public class PacienteRangoEdad {
    private String label;
    private Integer value;
   
    
	public PacienteRangoEdad(String label, Integer value) {
		super();
		this.label = label;
		this.value = value;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}

    
}
