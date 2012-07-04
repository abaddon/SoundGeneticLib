package soundGenetic.jgap;

import java.util.HashMap;

import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

import soundGenetic.FitnessValue;
import soundGenetic.jgap.FitnessFunction.FuncDistribFrequency;
import soundGenetic.jgap.FitnessFunction.FuncDistribGeometric;
import soundGenetic.jgap.FitnessFunction.FuncDistribNote;
import soundGenetic.jgap.FitnessFunction.GenericFitness;
import soundGenetic.jgap.FitnessFunction.support.PitchFunction;

public class SoundFitnessFunction extends FitnessFunction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ConfigFitness configFitness;
	private ConfigJGap configJGap;


	public SoundFitnessFunction(ConfigFitness configFitness, ConfigJGap configJGap) {
		this.configFitness=configFitness;
		this.configJGap=configJGap;
		//super(configFitness, configJGap);
	}

	@Override
	protected double evaluate( IChromosome chromosome) {
//		PitchFunction.getInstsance().setChromosome(chromosome);
		PitchFunction pitchFunction=new PitchFunction(chromosome,configFitness);
		FitnessValue.reset();

	// batteria di test START

		// funzione distribuzione delle note- tonalitˆ
		FuncDistribNote funcDistribNote = new FuncDistribNote(configFitness,configJGap); //
//		double risDistribNote= funcDistribNote.runTest(chromosome);
		double risDistribNote= funcDistribNote.runTest(pitchFunction);
		
		// funzione geometrica della melodia
		FuncDistribGeometric funcDistribGeometric=new FuncDistribGeometric(configFitness,configJGap);
//		double risDistribGeometric=funcDistribGeometric.runTest(chromosome);
		double risDistribGeometric=funcDistribGeometric.runTest(pitchFunction);
		
		//funzione frequenza note
		FuncDistribFrequency funcDistribFrequency=new FuncDistribFrequency(configFitness,configJGap);
//		double risDistribFrequency=funcDistribFrequency.runTest(chromosome);
		double risDistribFrequency=funcDistribFrequency.runTest(pitchFunction);
		
		//funzione modelli

//		FuncDistribModel funcDistribModel=new FuncDistribModel(configFitness,configJGap);
//		double risDistribModel=funcDistribModel.runTest(pitchFunction);
		
		
		//CALCOLO DEL FITNESS TOTALE
		//da modificare per ogni test che aggiungo

		double maxValue=configFitness.getFitnessTonalityWeight()+configFitness.getFitnessGeometricWeight()+configFitness.getFitnessFrequencyWeight();
		double value=risDistribNote*configFitness.getFitnessTonalityWeight()+
				risDistribGeometric*configFitness.getFitnessGeometricWeight()+
				risDistribFrequency*configFitness.getFitnessFrequencyWeight();//+
//				risDistribModel*0.2;
		
		
		double result=value/maxValue;
		
		FitnessValue.setPiches(pitchFunction.getPitchs());
		FitnessValue.setFitness(result);
		
		HashMap<String, String> lista=new HashMap<String, String>();
		lista.putAll(FitnessValue.getListValue());
		chromosome.setApplicationData(lista);
		
		return result;
	}
	
	

	

}
