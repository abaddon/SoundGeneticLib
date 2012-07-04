package soundGenetic.jgap.FitnessFunction;

import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

import soundGenetic.jgap.ConfigFitness;
import soundGenetic.jgap.ConfigJGap;
import soundGenetic.jgap.FitnessFunction.support.PitchFunction;

/**
 * @author   abaddon
 */
public abstract class GenericFitness {
	protected ConfigFitness configFitness=null;
	protected ConfigJGap configJGap=null;
	//protected IChromosome chromosome = null;
	protected PitchFunction pitchFunction=null;
	
	
	public GenericFitness(ConfigFitness configFitness,ConfigJGap configJGap) {
		this.configFitness=configFitness;
		this.configJGap=configJGap;
	}
	
	public double runTest(PitchFunction pitchFunction) {
		//this.chromosome=arg0;
		this.pitchFunction=pitchFunction;
		return evaluate();
	}
	
//	public double runTest(IChromosome chromosome){
//		return evaluate(chromosome);
//	}
	
	protected abstract double evaluate();
	

	
	
	

}
