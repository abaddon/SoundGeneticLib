package soundGenetic.jgap.FitnessFunction;

import java.util.ArrayList;

import org.jgap.IChromosome;

import soundGenetic.FitnessValue;
import soundGenetic.jgap.ConfigFitness;
import soundGenetic.jgap.ConfigJGap;
@Deprecated
public class FuncDistribModel extends GenericFitness{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -4402409853335180669L;

	public FuncDistribModel(ConfigFitness configFitness, ConfigJGap configJGap) {
		super(configFitness, configJGap);
		
	}

	@Override
	protected double evaluate() {
		double resultWhiteTest=whiteTest();
		
		double resultStagionalita=funzStagionalita();
		
		FitnessValue.printModelWhiteTest(resultWhiteTest);
		FitnessValue.printModelStagionalita(resultStagionalita);
		
		return 0.2*resultStagionalita;
	}
	
	private double whiteTest() {
		/*NORMALE N(0,1)
		 *alfa= 0.05; --> beta=1,96
		 *per cambiare tabelle o sito: http://www.delas.it/universita/Calcolo_area_funzione_Z/
		 * intervallo [-1.96,1.96]
		 *
		 * M << N-1  <<  data dalla percentuale
		 */
		ArrayList<Double> pitchNorm=new ArrayList<Double>();
		pitchNorm=Normalize();
		double percentuale=0.5;
		double alfa=0.05;
		double confidenza=1.96;
		int M=(int) (percentuale*(pitchNorm.size()-1));
		//ArrayList<Double> P=new ArrayList<Double>();
		
		int P=0;
		for(int i=1; i<=M; i++){
			double Pi=Math.sqrt(pitchNorm.size())*funzCovarianzaNormalizzata(i,pitchNorm);
			
			//P.add(Math.sqrt(PitchFunction.getInstsance().getPitchs().size())*funzCovarianzaNormalizzata(i));
			if(confidenza<Pi || -confidenza>Pi)
				P++;
		}
		double result= (double)P/(double)M;
		//System.out.println(P+"/"+M+" = "+result);
		return result;
	}

	private ArrayList<Double> Normalize() {
		//calcolo devstd e media
		double devstd=0;
		double media=0;
		
		double min=100;
		double max=0;
		//calcolo max e media
		for(int pitch: pitchFunction.getPitchs()){
			media+=pitch;
			if(pitch>max)
				max=pitch;
			if(pitch<min)
				min=pitch;
		}
		//calcolo media
		media=media/pitchFunction.getPitchs().size();
		
		//calcolo deviazione standard
		double sommatoria=0;
		for(int pitch: pitchFunction.getPitchs()){
			sommatoria+=Math.pow((pitch-media), 2);
		}
		devstd=Math.sqrt(sommatoria/(pitchFunction.getPitchs().size()-1));
		//System.out.println("media: "+media+" devstd: "+devstd);
		ArrayList<Double> pitchNorm=new ArrayList<Double>();
		//String debug="";
		for(int pitch: pitchFunction.getPitchs()){
			//debug+=" "+((pitch-media)/devstd);
			//System.out.println("devstd:"+devstd+" pitch:"+pitch+" min:"+min+" max:"+max+" media:"+media+" devstd"+devstd);
			//pitchNorm.add(devstd*(pitch-min)/(max-min)+(media-devstd));
			pitchNorm.add((pitch-media)/devstd);
			
		}
		//System.out.println(debug);
		return pitchNorm;
	}

	private double funzCovarianzaNormalizzata(int tau,ArrayList<Double> pitchNorm) {
		//funzione di covarianza normalizzata
		return funzCovarianzaCampionaria(tau, pitchNorm)/funzCovarianzaCampionaria(0, pitchNorm);

	}
		
	private double funzCovarianzaCampionaria(int tau, ArrayList<Double> pitchNorm){
		//funzione di covarianza campionaria di tau;
		double funzCamp=0;
		
		for(int i=1; i<pitchNorm.size()-tau; i++){
			funzCamp+=pitchNorm.get(i)*pitchNorm.get(i+tau);
		}
		funzCamp=(1.0/(pitchNorm.size()-tau))*funzCamp;
		
		return funzCamp;
	}

	private double funzStagionalita(){
		//VARIABILI
		int T=4;
		int M=(20/T);
		double mediaIdeale=2;
		double peso=0.5;
		//END
		
		double[] S=new double[T];
		//ArrayList<Double> lista=Normalize();
		ArrayList<Integer> lista=pitchFunction.getPitchs();
		for(int t=0; t<T; t++){
			double sommatoria=0;
			for(int h=0; h<M; h++){
				sommatoria+=lista.get(t+h*T);
			}
			S[t]=1.0/(double)M*sommatoria;
		}
		//calcolo la media
		double media=0;
		//System.out.println("----------------------");
		for(int i=0; i<pitchFunction.getPitchs().size();i++){
			//System.out.println("delta: "+PitchFunction.getInstsance().getPitchs().get(i)+" - "+S[i%T]);
			media+=Math.abs((double)pitchFunction.getPitchs().get(i)-S[i%T]);
		}
		media=media/(double)pitchFunction.getPitchs().size();
		
		double result=media -mediaIdeale;
		if(result<0)
			result=0;
		if(result>1)
			result=1;
		result=1-Math.pow(result, peso);
		
		System.out.println("media: "+media+" result: "+result);
		return result;
	}
	
}
