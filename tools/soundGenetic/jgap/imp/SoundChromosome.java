package soundGenetic.jgap.imp;

import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.Gene;
import org.jgap.IGeneConstraintChecker;
import org.jgap.InvalidConfigurationException;
import org.jgap.UnsupportedRepresentationException;

public class SoundChromosome extends Chromosome implements IChromosomeExt{

	public SoundChromosome(Configuration a_configuration, String representatuion)
			throws InvalidConfigurationException,
			UnsupportedRepresentationException {
		super(a_configuration, representatuion);
		// TODO Auto-generated constructor stub
	}

	public SoundChromosome(Configuration a_configuration, int size)
			throws InvalidConfigurationException {
		super(a_configuration, size);
		// TODO Auto-generated constructor stub
	}

	public SoundChromosome(Configuration a_configuration, Gene[] genes,
			IGeneConstraintChecker checker)
			throws InvalidConfigurationException {
		super(a_configuration, genes, checker);
		// TODO Auto-generated constructor stub
	}

	public SoundChromosome(Configuration a_configuration, Gene[] genes)
			throws InvalidConfigurationException {
		super(a_configuration, genes);
		// TODO Auto-generated constructor stub
	}

	public SoundChromosome(Configuration a_configuration, Gene gene, int size)
			throws InvalidConfigurationException {
		super(a_configuration, gene, size);
		// TODO Auto-generated constructor stub
	}

	public SoundChromosome(Configuration a_configuration)
			throws InvalidConfigurationException {
		super(a_configuration);
		// TODO Auto-generated constructor stub
	}

	public SoundChromosome() throws InvalidConfigurationException {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getTest(){
		return "ciao";
	}

}

