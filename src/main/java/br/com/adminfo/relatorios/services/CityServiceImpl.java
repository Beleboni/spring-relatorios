package br.com.adminfo.relatorios.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.adminfo.relatorios.model.City;
import br.com.adminfo.relatorios.model.State;
import br.com.adminfo.relatorios.repository.CityRepository;
import br.com.adminfo.relatorios.repository.StateRepository;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private StateRepository stateRepository;
	
	public CityServiceImpl(CityRepository cityRepository, StateRepository stateRepository) {
		this.cityRepository = cityRepository;
		this.stateRepository = stateRepository;
	}
	
	@Override
	public City create(Long stateId, City city) {
		Optional<State> state = stateRepository.findById(stateId);
		city.setState_id(state.get());
		return cityRepository.save(city);
	}

	@Override
	public List<City> reportAllCitiesByState(Long stateId) {
		return cityRepository.findAllCitiesByState(stateId);
	}

}