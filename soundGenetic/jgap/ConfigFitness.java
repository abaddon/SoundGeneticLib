package soundGenetic.jgap;

import java.util.HashMap;

/**
 * @author   abaddon
 */
public class ConfigFitness extends HashMap<String, Double>  {
//	private double fitnessTonalityWeight=0.0;
//	private double tonalityPitchWeight=0.0;
//	private double tonalityExternalPitchWeight=0.0;
//	private double tonalityScalePitchWeight=0.0;
//	private double tonalitaScala=0;
//	private double tonalitaNota=0;
//	private double fitnessGeometricWeight=0.0;
//	private double jumpMax=3;
//	private double estimatoreJump=0;
//	private double jumpWeight=0;
//	private double jumpDistanceWeight=0;
//	private double jumpDistance=0;
//	private double peakNumber=0;
//	private double peakCountWeight=0;
//	private double peakDistributionWeight=0;
//	private double jumpDirectionWeight=0;
	private ConfigFunction configFunction;
	
	public ConfigFitness() {
		configFunction=new ConfigFunction();
		double value=0.0;
		put("fitnessTonalityWeight",value);
		
		put("tonalitaScala",Double.valueOf(0)); //
		put("tonalitaNota",Double.valueOf(0));  //
		put("tonalityPitchWeight",value);
		
		put("tonalityExternalPitchScale",value);
		put("tonalityScalePitchWeight",value);
		
		put("tonalityExternalScalePitchWeight",value);

		
		put("fitnessGeometricWeight",value);
		put("jumpWeight",value);
		put("jumpMax",3.0);
		put("jumpDistanceWeight",value);
		put("jumpDistance",4.0);
		put("jumpDirectionWeight",value);
		put("peakNumber",5.0);
		put("peakCountWeight",value);
		put("peakDistributionWeight",value);
		put("geometricRibattutaWeight",value);
		
		put("frequencyPitchDistributionWeight",value);
	}
	
	/**
	 * @return
	 * @uml.property  name="configFunction"
	 */
	public ConfigFunction getConfigFunction(){
		return this.configFunction;
	}
	
	/**
	 * @param config
	 * @uml.property  name="configFunction"
	 */
	public void setConfigFunction(ConfigFunction config){
		this.configFunction=config;
	}
	
	
	////////TONALITY
	public void setFitnessTonalityWeight(double value){
//		this.fitnessTonalityWeight=value;
		put("fitnessTonalityWeight",value);
	}
	
	public void setTonalita(int  scala, int nota){
//		this.tonalitaScala=scala;
//		this.tonalitaNota=nota;
		put("tonalitaScala",Double.valueOf(scala));
		put("tonalitaNota",Double.valueOf(nota));
	}
	
	public void setTonalityPitchWeight(double value){
//		this.tonalityPitchWeight=value;
		put("tonalityPitchWeight",value);
	}
	
	
//	public void setTonalityExternalPitchWeight(double value){
////		this.tonalityExternalPitchWeight=value;
//		put("tonalityExternalPitchWeight",value);
//	}
	
	public void setTonalityScalePitchWeight(double value){
//		this.tonalityScalePitchWeight=value;
		put("tonalityScalePitchWeight",value);
		
	}
	
	public void setTonalityExternalPitchScale(double value) {
		put("tonalityExternalPitchScale",value);
	}
	
	public void setTonalityExternalScalePitchWeight(double value) {
		put("tonalityExternalScalePitchWeight",value);
	}
	
	///////GEOMETRIC
	
	public void setFitnessGeometricWeight(double value){
//		this.fitnessGeometricWeight=value;
		put("fitnessGeometricWeight",value);
	}
	
	//JUMP
	public void setJumpWeight(double value){
//		this.jumpWeight=value;
		put("jumpWeight",value);
	}
	public void setMaxJump(double jump){
//		this.jumpMax=jump;
		put("jumpMax",jump);
	}
	
	public void setJumpDistanceWeight(double value){
//		this.jumpDistanceWeight=value;
		put("jumpDistanceWeight",value);
	}
	
	public void setJumpDistance(double value){
//		this.jumpDistance=value;
		put("jumpDistance",value);
	}
	
	public void setJumpDirectionWeight(double value) {
//		this.jumpDirectionWeight=value;
		put("jumpDirectionWeight",value);
	}
	
	public void setGeometricJumpRecoverWeight(double value) {
		put("geometricJumpRecoverWeight",value);
		
	}
	
	public void setGeometricRibattutaWeight(double value) {
		put("geometricRibattutaWeight",value);
	}
	
	//PEAK
	public void setPeakNumber(double value){
//		this.peakNumber=value;
		put("peakNumber",value);
	}
	
	public void setPeakCountWeight(double value){
//		this.peakCountWeight=value;
		put("peakCountWeight",value);
	}
	public void setPeakDistributionWeight(double value){
//		this.peakDistributionWeight=value;
		put("peakDistributionWeight",value);
	}
	
	
//FREQUENCY
	
	public void setFitnessFrequencyWeight(double value) {
		put("fitnessFrequencyWeight",value);
	}
	
	public void setFitnessFrequencyPitchDistributionWeight(double value) {
		put("frequencyPitchDistributionWeight",value);
	}
	
	
	
	////////
	
	//GET
	
	////////TONALITY
	public double getFitnessTonalityWeight(){
		return get("fitnessTonalityWeight");
	}
	
	public int getTonalitaScala(){
		return get("tonalitaScala").intValue();
	}
	
	public int getTonalitaNota(){
		return get("tonalitaNota").intValue();
	}
	
	public double getTonalityPitchWeight(){
		return get("tonalityPitchWeight");
	}
	
//	public double getTonalityExternalPitchWeight(){
//		return get("tonalityExternalPitchWeight");
//	}
	
	public double getTonalityScalePitchWeight(){
		return get("tonalityScalePitchWeight");
	}
	
	public double getTonalityExternalPitchScale() {
		return get("tonalityExternalPitchScale");
	}
	
	public double getTonalityExternalScalePitchWeight() {
		return get("tonalityExternalScalePitchWeight");
	}
	
	///////GEOMETRIC
	public double getFitnessGeometricWeight(){
		return get("fitnessGeometricWeight");
	}
	//JUMP
	
	public double getJumpWeight(){
		return get("jumpWeight");
	}
	
	public double getMaxJump(){
		return get("jumpMax");
	}
	
	public int getMaxJumpFunction(){
		return get("estimatoreJump").intValue();
	}
	
	public double getJumpDistanceWeight(){
		return get("jumpDistanceWeight");
	}
	
	public double getJumpDistance(){
		return get("jumpDistance");
	}
	
	public double getJumpDirectionWeight() {
		return get("jumpDirectionWeight");
	}
	
	public double getGeometricJumpRecoverWeight(){
		return get("geometricJumpRecoverWeight");
	}
	
	public double getGeometricRibattutaWeight() {
		return get("geometricRibattutaWeight");
	}
	

	
	//PEAK
	public double getPeakNumber(){
		return get("peakNumber");
	}
	
	public double getPeakCountWeight(){
		return get("peakCountWeight");
	}
	
	public double getPeakDistributionWeight(){
		return get("peakDistributionWeight");
	}
	
	
	//FREQUENCY
	
	public double getFitnessFrequencyWeight() {
		return get("fitnessFrequencyWeight");
	}

	public double getFitnessFrequencyPitchDistributionWeight() {
		return get("frequencyPitchDistributionWeight");
	}

	

	

	

	

	

	

}
