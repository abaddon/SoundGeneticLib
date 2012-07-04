package soundGenetic.jgap.FitnessFunction.support;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import org.jgap.IChromosome;
import org.jgap.impl.CompositeGene;

import soundGenetic.jgap.ConfigFitness;


/**
 * @author   abaddon
 */
public class PitchFunction {
	//private  PitchFunction instance=null;
	//private IChromosome chromosome=null;
	private ArrayList<Pitch> pitchesOrderedByPitches=new ArrayList<Pitch>();
	/**
	 * @uml.property  name="pitchesOrderedByFrequency"
	 */
	private ArrayList<Pitch> pitchesOrderedByFrequency=new ArrayList<Pitch>();
	private ArrayList<PropertyGeometryPitches> propertyGeometryPitches=new ArrayList<PropertyGeometryPitches>();
	private HashMap<Integer, Boolean> propertyTonalityPitches=new HashMap<Integer, Boolean>();
	private ArrayList<Integer> pitches=new ArrayList<Integer>();
	/**
	 * @uml.property  name="jumpCount"
	 */
	private int jumpCount=0;
	private ConfigFitness configFitness=null;
	
	public PitchFunction(IChromosome chromosome,ConfigFitness configFitness){
		setChromosome(chromosome,configFitness);
	}
	
	public PitchFunction(ArrayList<Integer> pitchList, ConfigFitness configFitness){
		setChromosome(pitchList,configFitness);
	}
	
	public void setChromosome(IChromosome chromosome,ConfigFitness configFitness){
		setChromosome(chromosomeToArrayList(chromosome),configFitness);
	}
	
	public void setChromosome(ArrayList<Integer> list,ConfigFitness configFitness){
		this.configFitness=configFitness;
		pitchInt(list);
		setPitchTonalityProperty(list);
		pitchesOrderByPitches(list);
		pitchesOrderByFrequency();
		setPitchGeometryProperty(list);
	}
	
	
	private void setPitchTonalityProperty(ArrayList<Integer> list) {
		boolean[]template=null;
		for (Scheme schema : TonalitySchemes.getInstance().get(configFitness.getTonalitaScala())) {
			if (schema.getMax() == 7) 
				template = schema.getScheme(configFitness.getTonalitaNota());
		}
		for (int i = 0; i < list.size(); i++) {
			//modulo 12
			int pitch12=list.get(i)% 12;
			//seleziono le note fuori scala
			propertyTonalityPitches.put(list.get(i), template[pitch12]);
		}	
	}

	private void pitchesOrderByFrequency() {
		pitchesOrderedByFrequency=new ArrayList<Pitch>();
		pitchesOrderedByFrequency.addAll(pitchesOrderedByPitches);
		Collections.sort(pitchesOrderedByFrequency);
		Collections.reverse(pitchesOrderedByFrequency);
	}

	private void pitchesReset() {
		// pitches.clear();
		pitchesOrderedByPitches=new ArrayList<Pitch>();
		for (int i = 0; i < 12; i++) {
			pitchesOrderedByPitches.add(new Pitch(i, 0));
		}
	}
	
//	private void pitchesOrderByPitches() { 
//		pitchesReset();
//		for (int i = 0; i < chromosome.size(); i++) {
//			CompositeGene compositeGene = (CompositeGene) chromosome.getGene(i);
//			int pitch = (Integer) compositeGene.geneAt(2).getAllele();
//			int modulo = ((pitch) % 12);
//			pitchesOrderedByPitches.get(modulo).setFrequenza(pitchesOrderedByPitches.get(modulo).getFrequenza() + 1);
//		}
//		
//	}
	
	private void pitchesOrderByPitches(ArrayList<Integer> list) { 
		pitchesReset();
		boolean[]template=null;
		for (Scheme schema : TonalitySchemes.getInstance().get(configFitness.getTonalitaScala())) {
			if (schema.getMax() == 7) 
				template = schema.getScheme(configFitness.getTonalitaNota());
		}
		for (int i = 0; i < list.size(); i++) {
			int modulo = (list.get(i) % 12);
			pitchesOrderedByPitches.get(modulo).setFrequenza(pitchesOrderedByPitches.get(modulo).getFrequenza() + 1);
			pitchesOrderedByPitches.get(modulo).setScala(template[modulo]);
			
		}
		
	}
	
	private void pitchInt(ArrayList<Integer> list){
		pitches=list;
	}
	
	private ArrayList<Integer> chromosomeToArrayList(IChromosome chromosome){
		ArrayList<Integer> pitches=new ArrayList<Integer>();
		for(int i=0; i<chromosome.getGenes().length; i++){
			pitches.add((Integer)((CompositeGene) chromosome.getGenes()[i]).geneAt(2).getAllele());
		}
		return pitches;
	}
	
//	private void pitchJump(){ //STO METODO MI DARA' MOLTI PROBLEMI... LO SO.. :(  2-10-2007 avevo ragione.... :/
//		jumpCount=0;
//		proprietyPitches=new ArrayList<ProprietyPitch>();
//		ProprietyPitch proprietyPitch=null;
//		int jumpDistance=0;
//		for (int i = 0; i < chromosome.size(); i++) {  //HO BLOCCATO L'ULTIMA NOTA E' GIUSTO??? NO CAZZO!
//			int position = i+1; // posizione del salto
//			double pitch = (Integer) ((CompositeGene) chromosome.getGene(i)).geneAt(2).getAllele(); // nota al tempo t
//			double previousPitch=pitch;
//			
//			if(i>0)
//				previousPitch = (Integer) ((CompositeGene) chromosome.getGene(i - 1)).geneAt(2).getAllele(); // nota al tempo t-1
//			
//			double nextPitch=pitch;
//			
//			if(i<chromosome.size()-1)
//				nextPitch = (Integer) ((CompositeGene) chromosome.getGene(i + 1)).geneAt(2).getAllele(); // nota al tempo t+1
//			
//			double salto = pitch - previousPitch; // conto le note saltate il segno mi indica se è un salto crescente o decrescente
//			proprietyPitch=new ProprietyPitch(previousPitch, pitch,nextPitch,1, position);
//			
//			if(Math.abs(salto)>1){
//				proprietyPitch.setJump(true);
//				proprietyPitch.setJumpDistance(jumpDistance);
//				jumpDistance=0;
//				jumpCount++;
//				proprietyPitches.add(proprietyPitch);
//			}else{  
//				jumpDistance++;
//			}
////TODO DA VERIFICARE!!!!  perchè aggiungo tutte le note anche se non sono salti???			
////			jumps.add(jump);
//		}
//		
//	}
	
	private void setPitchGeometryProperty(ArrayList<Integer> list){ //STO METODO MI DARA' MOLTI PROBLEMI... LO SO.. :(  2-10-2007 avevo ragione.... :/
		jumpCount=0;
		propertyGeometryPitches=new ArrayList<PropertyGeometryPitches>();
		PropertyGeometryPitches propertyPitch=null;
		int jumpDistance=0;

		for (int i = 0; i < list.size(); i++) {  //HO BLOCCATO L'ULTIMA NOTA E' GIUSTO??? NO CAZZO!
			int position = i+1; // posizione del salto
			double pitch = list.get(i); // nota al tempo t
			double previousPitch=pitch;
			
			if(i>0)
				previousPitch = list.get(i - 1); // nota al tempo t-1
			
			double nextPitch=pitch;
			
			if(i<list.size()-1)
				nextPitch = list.get(i + 1); // nota al tempo t+1
			
			double salto = pitch - previousPitch; // conto le note saltate il segno mi indica se è un salto crescente o decrescente
			propertyPitch=new PropertyGeometryPitches(previousPitch, pitch,nextPitch,1, position);
			
			if(Math.abs(salto)>1){
				propertyPitch.setJump(true);
				propertyPitch.setJumpDistance(jumpDistance);
				jumpDistance=0;
				jumpCount++;
				propertyGeometryPitches.add(propertyPitch);
			}else{  
				jumpDistance++;
			}
			
			
//TODO DA VERIFICARE!!!!  perchè aggiungo tutte le note anche se non sono salti???			
//			jumps.add(jump);
		}
		
	}
	
	public ArrayList<Integer> getPitchs(){
		return pitches;
	}
	
	
	public ArrayList<Pitch> getPitchesOrderByPitches(){
		return this.pitchesOrderedByPitches;
	}
	
	/**
	 * @return
	 * @uml.property  name="pitchesOrderedByFrequency"
	 */
	public ArrayList<Pitch> getPitchesOrderedByFrequency(){
		return this.pitchesOrderedByFrequency;
	}
	
	public ArrayList<PropertyGeometryPitches> getPropertyPitches(){
		return this.propertyGeometryPitches;
	}

	/**
	 * @return
	 * @uml.property  name="jumpCount"
	 */
	public int getJumpCount(){
		return jumpCount;
	}
	
	public HashMap<Integer, Boolean> getPropertyTonalityPitchs(){
		return this.propertyTonalityPitches;
	}
}
