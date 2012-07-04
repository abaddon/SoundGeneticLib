package soundGenetic.jgap;

import java.util.HashMap;

public class ConfigJGap  extends HashMap<String, Double> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public void setFitnessValueAccetable(double value){
		put("fitnessValueAccetable",value);

	}
	
	public void setPopulationSizeConstant(boolean value){
//		this.populationSizeConstant=value;
		if(value)
			put("populationSizeConstant",1.0);
		else
			put("populationSizeConstant",0.0);
	}
	
	public void setPreservFitTestIndividual(boolean value){
		if(value)
			put("preservFitTestIndividual",1.0);
		else
			put("preservFitTestIndividual",0.0);
	//	this.preservFitTestIndividual=preservFitTestIndividual;
	}
	
	public void setSizeSoundChromosome(int size){
		put("sizeChromosome", Double.valueOf(size));
	}
	
	public void setNumPopulation(int num){
		put("numPopulation",Double.valueOf(num));
	}
	
	public void setMinPitch(int pitch){
		put("minPitch", Double.valueOf(pitch));
	}
	
	public void setNumOttave(int num){
		put("numOttave",Double.valueOf(num));
	}
	
	///START GET
	
	public double getFitnessValueAccetable(){
		return get("fitnessValueAccetable");
	}
	
	public boolean getPopulationSizeConstant(){
		if(get("populationSizeConstant")==1.0)
			return true;
		else
			return false;
	}
	
	public boolean getPreservFitTestIndividual(){
		if(get("preservFitTestIndividual")==1.0)
			return true;
		else
			return false;
	}
	
	public int getSizeSoundChromosome(){
		return get("sizeChromosome").intValue();
	}
	
	public int getNumPopulation(){
		return get("numPopulation").intValue();
	}
	
	public  int getMinPitch(){
		return get("minPitch").intValue();
	}
	
	public int getNumOttave(){
		return get("numOttave").intValue();
	}
	
	


}
