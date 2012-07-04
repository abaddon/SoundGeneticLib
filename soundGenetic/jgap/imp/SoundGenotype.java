package soundGenetic.jgap.imp;

import org.jgap.Configuration;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;

/**
 * @author   abaddon
 */
public class SoundGenotype extends Genotype {
	
	 private SoundPopulation m_population;

	public SoundGenotype(Configuration a_configuration)
			throws InvalidConfigurationException {
		super(a_configuration);
		// TODO Auto-generated constructor stub
	}

	public SoundGenotype(Configuration arg0, SoundPopulation arg1)
			throws InvalidConfigurationException {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public SoundGenotype(Configuration a_configuration,
			IChromosome[] chromosomes) throws InvalidConfigurationException {
		super(a_configuration, chromosomes);
		// TODO Auto-generated constructor stub
	}

	public static SoundGenotype randomInitialGenotype(SoundConfiguration a_configuration) throws InvalidConfigurationException {
		if (a_configuration == null) {
			throw new IllegalArgumentException("The Configuration instance may not be null.");
		}
		a_configuration.lockSettings();
		// Create an array of chromosomes equal to the desired size in the
		// active Configuration and then populate that array with Chromosome
		// instances constructed according to the setup in the sample
		// Chromosome, but with random gene values (alleles). The Chromosome
		// class randomInitialChromosome() method will take care of that for
		// us.
		// ------------------------------------------------------------------
		int populationSize = a_configuration.getPopulationSize();
		SoundPopulation pop = new SoundPopulation(a_configuration, populationSize);
		// Do randomized initialization.
		// -----------------------------
		SoundGenotype result = new SoundGenotype(a_configuration, pop);
		result.fillPopulation(populationSize);
		return result;
	}
	
	public synchronized IChromosomeExt getFittestChromosome() {

	   // return getPopulation().determineFittestChromosome();
return null;
	  }
	
	public SoundPopulation getPopulation() {

	    return m_population;

	  }





}
