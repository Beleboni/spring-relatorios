package br.com.adminfo.relatorios.services;

import java.util.List;

import br.com.adminfo.relatorios.model.ReportChart;
import br.com.adminfo.relatorios.model.State;

public interface StateService {
	
	public List<State> findAll();
	public State create(State state);
	public List<ReportChart>countCitiesByState();

}