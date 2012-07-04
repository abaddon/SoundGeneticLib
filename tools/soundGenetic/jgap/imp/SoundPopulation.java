package soundGenetic.jgap.imp;

import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;
import org.jgap.Population;

public class SoundPopulation extends Population{

	public SoundPopulation(SoundConfiguration a_config, int a_size)
			throws InvalidConfigurationException {
		super(a_config, a_size);
		// TODO Auto-generated constructor stub
	}

	public SoundPopulation(SoundConfiguration arg0, IChromosome[] arg1)
			throws InvalidConfigurationException {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public SoundPopulation(SoundConfiguration a_config, IChromosome a_chromosome)
			throws InvalidConfigurationException {
		super(a_config, a_chromosome);
		// TODO Auto-generated constructor stub
	}

	public SoundPopulation(SoundConfiguration a_config)
			throws InvalidConfigurationException {
		super(a_config);
		// TODO Auto-generated constructor stub
	}

	public SoundPopulation() throws InvalidConfigurationException {
		super();
		// TODO Auto-generated constructor stub
	}
	
//	 public IChromosomeExt determineFittestChromosome() {
//		    if (!m_changed && m_fittestChromosome != null) {
//		      return m_fittestChromosome;
//		    }
//		    Iterator it = m_chromosomes.iterator();
//		    FitnessEvaluator evaluator = getConfiguration().getFitnessEvaluator();
//		    double bestFitness;
//		    if (evaluator.isFitter(2.0d, 1.0d)) {
//		      bestFitness = -1.0d;
//		    }
//		    else {
//		      bestFitness = Double.MAX_VALUE;
//		    }
//		    double fitness;
//		    while (it.hasNext()) {
//		      IChromosome chrom = (IChromosome) it.next();
//		      fitness = chrom.getFitnessValue();
//		      if (evaluator.isFitter(fitness, bestFitness) || m_fittestChromosome == null) {
//		        m_fittestChromosome = chrom;
//		        bestFitness = fitness;
//		      }
//		    }
//		    setChanged(false);
//		    return m_fittestChromosome;
//		  }


}
