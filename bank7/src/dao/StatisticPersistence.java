package dao;
//salut c'est billal

// salut c'est le boss c'est Enzo
import business.client.SimulationEntry;
import business.simulation.StatisticManager;

/**
 * General DAO interface for persistence APIs.
 */
public interface StatisticPersistence {
	
	void dataInit();

	int persist(SimulationEntry simulationEntry, StatisticManager statisticManager);

	int servedClientCount(int simulationEntryId);
	
	int nonServedClientCount(int simulationEntryId);

}
