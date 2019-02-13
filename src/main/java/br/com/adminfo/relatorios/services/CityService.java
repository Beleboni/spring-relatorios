package br.com.adminfo.relatorios.services;

import java.util.List;

import br.com.adminfo.relatorios.model.City;

public interface CityService {
	
	public City create(Long stateid, City city);
	public List<City> reportAllCitiesByState(Long stateId);

}