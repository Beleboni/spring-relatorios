package br.com.adminfo.relatorios.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.adminfo.relatorios.model.ReportChart;
import br.com.adminfo.relatorios.model.State;
import br.com.adminfo.relatorios.repository.StateRepository;

@Service
public class StateServiceImpl implements StateService {
	
	@Autowired
	private StateRepository stateRepository;
	
	public StateServiceImpl(StateRepository stateRepository) {
		this.stateRepository = stateRepository;
	}
	
	@Override
	public State create(State state) {
		return stateRepository.save(state);
	}

	@Override
	public List<State> findAll() {
		return (List<State>) stateRepository.findAll();
	}

	@Override
	public List<ReportChart> countCitiesByState() {
		return stateRepository.countCitiesByState();
	}

}