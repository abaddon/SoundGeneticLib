package soundGenetic.jgap.FitnessFunction;

import java.util.HashMap;

import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

public class FuncFindWeight extends FitnessFunction{
	private static final long serialVersionUID = 7836540757384112329L;
	
	private HashMap<String, Double> scoreList=new HashMap<String, Double>();
	
	public FuncFindWeight(HashMap<String, Double> scoreList) {
		this.scoreList=scoreList;
		
	}



	@Override
	protected double evaluate(IChromosome arg0) {
		double sum=0;
		int i=0;
		for(String key : scoreList.keySet()){
			
			Double value=((Integer)arg0.getGene(i).getAllele()).doubleValue();
			sum+=(value*scoreList.get(key))/1000.0;
			//System.out.println(scoreList.get(key)+"*"+value+" = "+sum);
			
			i++;
		}
		sum=sum/(double)scoreList.size();
		//System.out.println("somma: "+sum+ " divisore: "+scoreList.size());
		return sum;
	}

}
