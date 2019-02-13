package br.com.adminfo.relatorios.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.adminfo.relatorios.model.ReportChart;
import br.com.adminfo.relatorios.model.State;

@Repository
public interface StateRepository extends CrudRepository<State, Long> {

	Optional<State> findById(Long stateId);
	
	@Query(value= "select new br.com.adminfo.relatorios.model.ReportChart(state.id, state.name, (select count(*) from City city where city.state_id = state.id) ) from State state")
	public List<ReportChart> countCitiesByState();

}