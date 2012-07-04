package soundGenetic.jgap.FitnessFunction;

import soundGenetic.FitnessValue;
import soundGenetic.jgap.ConfigFitness;
import soundGenetic.jgap.ConfigJGap;
import soundGenetic.jgap.FitnessFunction.support.Pitch;


public class FuncDistribFrequency extends GenericFitness{
//	private ConfigFitness configFitness=null;

	/**
	 * 
	 * 
	 */
	private static final long serialVersionUID = 5796877042379010030L;
		

	public FuncDistribFrequency(ConfigFitness configFitness, ConfigJGap configJGap){
		super(configFitness, configJGap);
	}
	
	@Override
	protected double evaluate() {
		double result=0.0;
		//System.out.println("-->"+configFitness.getFitnessFrequencyPitchDistributionWeight());
		if(configFitness.getFitnessFrequencyPitchDistributionWeight()!=0 ){
			double resultPitchDistribution=pitchDistribution();
		
			FitnessValue.printFrequencyPitchDistribution(resultPitchDistribution);
		
			result=configFitness.getFitnessFrequencyPitchDistributionWeight()*resultPitchDistribution;
		
			result=result/(configFitness.getFitnessFrequencyPitchDistributionWeight());
		}
		
			FitnessValue.printFrequencyScore(result);
		
		return result;
	}
	
	//NUOVA VERSIONE CON SCARTO QUADRATICO MEDIO
	private double pitchDistribution(){
		double maxScala=1;   //per avere i valori in % mettere 100 ATTENZIONE il programma funziona con 1
		double avgIdeal=maxScala*(1-configFitness.getTonalityExternalPitchScale())/7.0;
		double weight=configFitness.getConfigFunction().getFrequencyPitchDistributionWeight();
		double bestSqm=0.03*maxScala;
		
		double sqm=0;
		for(Pitch pitch: pitchFunction.getPitchesOrderByPitches()){
			//DEVO IDENTIFICARE LE NOTE IN SCALA
			if(pitch.isScale()){
				//=$frequenza 0/variabili :: $valore $numero note*100
				//frequenza
				double frequency=pitch.getFrequenza()/pitchFunction.getPitchs().size()*maxScala;
				//=POTENZA((Tabella 4 :: $s.q.m scala $media ideale-$'freq. in %' 0);2)
				//scostamento quadratico
				sqm+=Math.pow(avgIdeal-frequency, 2.0); 
			}
		}
		//scarto quadratico medio
		sqm=Math.sqrt(sqm);
		
		double result=1.0-Math.pow(Math.abs(bestSqm-sqm),weight);
		//System.out.println("RESULT: "+result);
		return result;
	}
	
//	private double pitchDistribution(){
//		double somma=0.0;
//		for(Pitch pitch:pitchFunction.getPitchesOrderByPitches()){
//			somma+=Math.pow(2,pitch.getFrequenza());
//		}
//		double peso=configFitness.getConfigFunction().getFrequencyPitchDistributionWeight();
//		double ideale=Math.pow(2, (12.0/2.0))*pitchFunction.getPitchs().size()/20.0;
//		double result=Math.abs(ideale-somma);
//		result=1.0-Math.pow((result/(Math.pow(2, 10)*pitchFunction.getPitchs().size()/20.0)),peso);
//		//System.out.println(result);
//		if(result<0.0)
//			result=0.0;
//		return result;
//	}

}
