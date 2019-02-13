package br.com.adminfo.relatorios.model;

public class ReportChart {
	
	private Long id;
	private String name;
	private Long total;
	
	public ReportChart() {
		super();
	}

	public ReportChart(Long id, String name, Long total) {
		super();
		this.id = id;
		this.name = name;
		this.total = total;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Long getTotal() {
		return total;
	}
	
	public void setTotal(Long total) {
		this.total = total;
	}

}