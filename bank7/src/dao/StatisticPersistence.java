package dao;
//salut c'est billal

// salut c'est la baltringue Enzo
import business.client.SimulationEntry;
import business.simulation.StatisticManager;
//test encore
/**
 * General DAO interface for persistence APIs.
 */
public interface StatisticPersistence {
	
	void dataInit();

	int persist(SimulationEntry simulationEntry, StatisticManager statisticManager);

	int servedClientCount(int simulationEntryId);
	
	int nonServedClientCount(int simulationEntryId);

}
