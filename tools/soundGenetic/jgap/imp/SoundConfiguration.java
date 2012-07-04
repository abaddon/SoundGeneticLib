package soundGenetic.jgap.imp;

import org.jgap.Configuration;
import org.jgap.InvalidConfigurationException;

/**
 * @author   abaddon
 */
public class SoundConfiguration extends Configuration{

	 private IChromosomeExt m_sampleChromosome;
	 private int m_chromosomeSize;
	 
	 public SoundConfiguration(String a_id, String a_name){
		 super(a_id,a_name);
	 }
	 
	 public void setSampleChromosome(final IChromosomeExt a_sampleChromosomeToSet) throws InvalidConfigurationException {
		verifyChangesAllowed();
		// Sanity check: Make sure that the given chromosome isn't null.
		// -----------------------------------------------------------
		if (a_sampleChromosomeToSet == null) {
			throw new InvalidConfigurationException("The sample chromosome instance may not be null.");
		}
		if (a_sampleChromosomeToSet.getConfiguration() == null) {
			throw new InvalidConfigurationException("The sample chromosome's configuration may not be null.");
		}
		// Ensure that no other sample chromosome has been set in a
		// different configuration object within the same thread!
		// --------------------------------------------------------
		checkProperty(PROPERTY_SAMPLE_CHROM_INST, a_sampleChromosomeToSet,"Sample chromosome has already been set differently.");
		m_sampleChromosome = a_sampleChromosomeToSet;
		m_chromosomeSize = m_sampleChromosome.size();
	 }
	 


}
