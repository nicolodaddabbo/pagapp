package it.paganello.pagapp.matchingAlgorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import it.paganello.pagapp.enums.MatchingAlgorithmName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MatchingAlgorithmFactory {
	private Map<MatchingAlgorithmName, MatchingAlgorithm> algorithms;
	
	@Autowired
	public MatchingAlgorithmFactory(final Set<MatchingAlgorithm> algorithmsSet) {
		createAlgotithm(algorithmsSet);
	}
	
	public MatchingAlgorithm findMatchingAlgorithm(final MatchingAlgorithmName name) {
		return algorithms.get(name);
	}
	
	private void createAlgotithm(final Set<MatchingAlgorithm> algorithmsSet) {
		algorithms = new HashMap<>();
		algorithmsSet.forEach(algo -> algorithms.put(algo.getMatchingAlgorithmName(), algo));
	}
}
