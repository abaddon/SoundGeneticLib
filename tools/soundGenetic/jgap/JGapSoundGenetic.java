package soundGenetic.jgap;

import java.util.HashMap;

import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.Gene;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;
import org.jgap.Population;
import org.jgap.impl.CompositeGene;
import org.jgap.impl.CrossoverOperator;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;

import soundGenetic.FitnessValue;
import tools.ChartFitness;
import tools.ChartPitchs;
import tools.Debug;

/**
 * @author   abaddon
 */
public class JGapSoundGenetic {

	private ConfigJGap configJGap=null;
	/**
	 * @uml.property  name="sizeSoundChromosome"
	 */
	private int sizeSoundChromosome; // numero geni per cromosoma(un
	// gene=1 nota)

	private int numEvolution = 1000;

	private int numPopulation;  //numero dei cromosomi in ogni popolazione

	private int minPitch; // MIN 53
	private int num_ottave; // num ottave. max note= num_ott*12+min_note
	private int maxPitch;
	private boolean kill=false;
	//private int[] tempConfronto = new int[] { 2, 2, 1, 2, 2, 2, 1 };
	private Configuration gaConf;

	private Genotype genotype = null;
	
	
	///CONSOLE
	//private JTextArea jTextOutput;
	/**
	 * @uml.property  name="fitnessValue"
	 */
	HashMap<String, String> fitnessValue=null;

	public JGapSoundGenetic() {
		//initialization();
		//start();
	}
	
//	public void setOutputConole(JTextArea jTextOutput){
//		this.jTextOutput=jTextOutput;
//	}
	
	/**
	 * @return
	 * @uml.property  name="fitnessValue"
	 */
	public HashMap<String, String> getFitnessValue(){
		return this.fitnessValue;
	}
	
	public void kill(){
		this.kill=true;
	}

	public void initialization(ConfigJGap configJGap, ConfigFitness configFitness) {
		this.configJGap=configJGap;
		this.sizeSoundChromosome=configJGap.getSizeSoundChromosome();
		this.numPopulation=configJGap.getNumPopulation();
		this.minPitch=configJGap.getMinPitch();
		this.num_ottave=configJGap.getNumOttave();
		
		
		try {
			
			// max note
			this.maxPitch = minPitch + (this.num_ottave * 12);
			gaConf = new DefaultConfiguration();
			gaConf.reset();
					
			// gaConf.setFitnessEvaluator(new DeltaFitnessEvaluator());
			// gaConf.setFitnessEvaluator(new DefaultFitnessEvaluator());
			gaConf.setFitnessFunction(new SoundFitnessFunction(configFitness,configJGap));
			gaConf.setPreservFittestIndividual(false);
			gaConf.setKeepPopulationSizeConstant(configJGap.getPopulationSizeConstant());
			genotype = null;
			// creo il cromosoma
			// SoundChromosome soundChromosome = new SoundChromosome(gaConf,new
			// CompositeGene(gaConf), this.sizeSoundChromosome);
			// gaConf.setSampleChromosome(soundChromosome);
	
//TEST MUTAZIONE
//			gaConf.getGeneticOperators().clear();
//			TwoWayMutationOperator mutOp = new TwoWayMutationOperator(gaConf,30);
//			
//			gaConf.addGeneticOperator(mutOp);
//			//gaConf.addGeneticOperator(new CrossoverOperator());
//END MUTAZIONE
			Gene[] sampleGenes = new Gene[sizeSoundChromosome];
			for (int i = 0; i < sizeSoundChromosome; i++) {
				CompositeGene compositeGene = new CompositeGene(gaConf); // creo il gene Composite contenente i dati sulla nota
				IntegerGene chGene = new IntegerGene(gaConf, 1, 16); //canale 
				IntegerGene durGene = new IntegerGene(gaConf, 0, 6); //durata
				IntegerGene pitchGene = new IntegerGene(gaConf, minPitch, //tipo di nota
						maxPitch);
				IntegerGene velocityGene = new IntegerGene(gaConf, 1, 128); //velocitˆ di nota
				compositeGene.addGene(durGene);
				compositeGene.addGene(chGene);
				compositeGene.addGene(pitchGene);
				compositeGene.addGene(velocityGene);
				sampleGenes[i] = compositeGene; //aggiungo la nota/gene al cromosoma
			}
			IChromosome sampleChromosome = new Chromosome(gaConf, sampleGenes);
			
			gaConf.setSampleChromosome(sampleChromosome);
			// ///////////
			gaConf.setPopulationSize(this.numPopulation);
			genotype = Genotype.randomInitialGenotype(gaConf);
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
			System.exit(-2);
		}
	}

	public int[] start() {
		int[] listpitch=null;
		Configuration.reset();
		Debug.println("Start Terno Al Lotto, accendi un cero e spera");

		boolean run=true;
		int evoluzione=0;
		int puntoGrafico=0;
		
		while(run && !kill){
			
			evoluzione++;
			
			genotype.evolve();

			Population popolazione = genotype.getPopulation();

			FitnessValue.setEvolution(evoluzione);
			FitnessValue.setPopulation(popolazione.getChromosomes().size());
			//PRENDO IL CROMOSOMA MIGLIORE IN ASSOLUTO
			IChromosome absoluteBestChromosome = genotype.getFittestChromosome();
			//popolazione.g
			
			//copio la lista di attributi nella lista ricevuta
			if (absoluteBestChromosome.getApplicationData()  instanceof HashMap) {
				this.fitnessValue=(HashMap<String, String>)absoluteBestChromosome.getApplicationData();
			}else{
				this.fitnessValue=null;
			}

//TODO VISUALIZZARE IL MIGLIOR FITNESS DELL“EVOLUZIONE E NON QUELLO ASSOLUTO
			//stampa del grafico delle note
		//TEST
//			IChromosome relativeBestChromosome=popolazione.getChromosome(popolazione.getChromosomes().size()-1);
//			IChromosome relativeBestChromosome1=null;
//			double  relativeBestFitness=0;
//			int iRel=0;;
//			for(int i=popolazione.getChromosomes().size()-2; i>0; i--){
//				if(popolazione.getChromosome(i).getFitnessValue()>relativeBestChromosome.getFitnessValue()){
//					iRel=i;
//					relativeBestChromosome1=relativeBestChromosome;
//					relativeBestFitness=relativeBestChromosome.getFitnessValue();	
//					relativeBestChromosome=popolazione.getChromosome(i);
//				}
//			}
//			System.out.println(iRel+"/"+(popolazione.getChromosomes().size()-1)+": "+relativeBestFitness+"   "+relativeBestChromosome.getFitnessValue());
		//END
			ChartPitchs.updateChart(absoluteBestChromosome);
			
			
			//ottengo il fitness migliore in assoluto
			double fitness = absoluteBestChromosome.getFitnessValue();
			ChartFitness.addNewFitness(fitness);
			
//TEST WHITE NOISE
//ChartFitness.addNewFitness(Float.valueOf(fitnessValue.get("modelStagionalita")));


			//fitness ideale raggiunto
			if (fitness >configJGap.getFitnessValueAccetable() || kill==true) {
				String note="";
				listpitch=new int[absoluteBestChromosome.getGenes().length];
				for (int x = 0; x < absoluteBestChromosome.getGenes().length; x++) {
					CompositeGene noteGene = (CompositeGene) absoluteBestChromosome.getGene(x);
					note+=" "+(Integer)noteGene.geneAt(2).getAllele();
					listpitch[x]=(Integer)noteGene.geneAt(2).getAllele();
				}
				run=false;
			}
		}
		kill=false;
		return listpitch;
	}

	/**
	 * @return
	 * @uml.property  name="sizeSoundChromosome"
	 */
	public int getSizeSoundChromosome() {
		return this.sizeSoundChromosome;
	}

	/**
	 * @param sizeSoundChromosome
	 * @uml.property  name="sizeSoundChromosome"
	 */
	public void setSizeSoundChromosome(int sizeSoundChromosome) {
		this.sizeSoundChromosome = sizeSoundChromosome;
	}

}
