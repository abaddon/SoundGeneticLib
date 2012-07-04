package soundGenetic.jgap;

import java.util.HashMap;

import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.Gene;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;
import org.jgap.Population;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;

import soundGenetic.jgap.FitnessFunction.FuncFindWeight;

public class JGapFindWeight {

	private final int POPULATION = 50;
	private final boolean POPULATION_CONSTANT = true;
	private final double FITNESS = 0.9;

	private Configuration gaConf;
	private Genotype genotype = null;
	private boolean isRun=true;
	private HashMap<String, Double> scoreList=new HashMap<String, Double>();
	private double maxFitness=0;

	public JGapFindWeight(HashMap<String, Double> scoreList) {
		this.scoreList=scoreList;
		initialization(scoreList.size());
	}

	private void initialization(int size) {
		gaConf = new DefaultConfiguration();
		gaConf.reset();
		FuncFindWeight funcFindWeight=new FuncFindWeight(scoreList);
		try {
			gaConf.setFitnessFunction(funcFindWeight);
		} catch (InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gaConf.setPreservFittestIndividual(false);
		gaConf.setKeepPopulationSizeConstant(POPULATION_CONSTANT);
		genotype = null;
		
		
		// creazione cromosoma
		createChromosome(size, gaConf);
		
		
	}

	private void createChromosome(int size, Configuration conf) {
		IChromosome chromosome=null;
		System.out.println("SIZE: "+size);
		Gene[] genes = new Gene[size];
		try {
			for (int i = 0; i < size; i++) {
				IntegerGene integerGene=new IntegerGene(conf, 0, 1000);
				genes[i] = integerGene;
				
			}
			chromosome=new Chromosome(conf,genes);
			
			conf.setSampleChromosome(chromosome);
			conf.setPopulationSize(POPULATION);
			genotype = Genotype.randomInitialGenotype(conf);
		} catch (InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void start(){
		int evolution=0;
		while(isRun){
			evolution++;
			genotype.evolve();
			Population population=genotype.getPopulation();
			IChromosome absoluteBestChromosome = genotype.getFittestChromosome();
			
//			//copio la lista di attributi nella lista ricevuta
//			if (absoluteBestChromosome.getApplicationData()  instanceof HashMap) {
//				this.fitnessValue=(HashMap<String, String>)absoluteBestChromosome.getApplicationData();
//			}else{
//				this.fitnessValue=null;
//			}
			
			double fitness = absoluteBestChromosome.getFitnessValue();
			//fitness=fitness/11.0;
			if(maxFitness<fitness){
				maxFitness=fitness;
				System.out.println("result: "+maxFitness);
			}
			
			//fitness ideale raggiunto
			if (fitness >=FITNESS) {
				String note="";
//				listpitch=new int[absoluteBestChromosome.getGenes().length];
//				for (int x = 0; x < absoluteBestChromosome.getGenes().length; x++) {
//					CompositeGene noteGene = (CompositeGene) absoluteBestChromosome.getGene(x);
//					note+=" "+(Integer)noteGene.geneAt(2).getAllele();
//					listpitch[x]=(Integer)noteGene.geneAt(2).getAllele();
//				}
				isRun=false;
			}
		}
//		return
		
	}
}
