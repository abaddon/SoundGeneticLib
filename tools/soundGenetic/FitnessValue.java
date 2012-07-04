package soundGenetic;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * @author  abaddon
 */
public class FitnessValue {
	
//	private static double maxFitness=0;
	private static ArrayList<Integer> pitches=new ArrayList<Integer>();
	//private static HashMap<String,String> listMax=new HashMap<String,String>();
	//NOTE
	//private final int DO=0;
	//private final int DO_DIESIS=1;
	//private final int RE=2;
	//private final int RE_DIESIS=3;
	//private final int MI=4;
	//private final int FA=5;
	//private final int FA_DIESIS=6;
	//private final int SOL=7;
	//private final int SOL_DIESIS=8;
	//private final int LA=9;
	//private final int LA_DIESIS=10;
	//private final int SI=11;
	private static String[] pitchName=new String[]{"DO","DO#","RE","RE#","MI","FA","FA#","SOL","SOL#","LA","LA#","SI"};

	private static HashMap<String,String> list=new HashMap<String,String>();
	private static DecimalFormat format=new DecimalFormat("####.####");
	/**
	 * @uml.property  name="population"
	 */
	private static int population=0;
	/**
	 * @uml.property  name="evolution"
	 */
	private static int evolution=0;
	
	//METODI GESTIONE LISTA
	public static HashMap<String,String> getListValue(){
		return list;
	}
	
	public static void reset(){
		list.clear();
	}


	//PARAMETRI GENERICI DELL'EVOLUZIONE
	
	/**
	 * @param value
	 * @uml.property  name="evolution"
	 */
	public static void setEvolution(int value){
		evolution=value;
	}
	
	/**
	 * @param value
	 * @uml.property  name="population"
	 */
	public static void setPopulation(int value){
		population=value;
	}
	
	/**
	 * @return
	 * @uml.property  name="evolution"
	 */
	public static int getEvolution(){
		return evolution;
	}
	
	/**
	 * @return
	 * @uml.property  name="population"
	 */
	public static int getPopulation(){
		return population;
	}
	

	//PARAMETRI DEL CROMOSOMA

	//	public static double getMaxFitness() {
//		return maxFitness;
//	}
//	
	public static void setFitness(double value){
		list.put("fitness", format.format(value));
	}
	
	public static void setPiches(ArrayList<Integer> pitchs) {
		pitches=pitchs;
		list.put("pitchesNumber", getPitchesNumberToString());
		list.put("pitchesChar", getPitchesCharToString());
	}
	
//	
	private static String getPitchesCharToString(){
		String pitchesString="";
		for(int pitch : pitches){
			//ottava:
			int numOtt=(pitch/12)-1;
			pitchesString+=pitchName[pitch%12]+numOtt+" ";
		}
		return pitchesString; 
	}
	
	private static String getPitchesNumberToString() {
		String pitchesString="";
		for(int pitch : pitches){
			pitchesString+=pitch+" ";
		}
		return pitchesString;
	}
	
	
	///TONALITA
	public static void printTonalitaSize(Double value,Double valueMax){
		list.put("tonalitaSize", value+"/"+valueMax);
	}
	
	public static void printTonalitaCongruenza(Double value,Double valueMax){
		list.put("tonalitaCongruenza", value+"/"+valueMax);
	}
	
	public static void printTonalitaScore(double value){
		
		list.put("tonalitaScore", format.format(value));
	}
///MODIFICATO
	public static void printTonalityPitch(double value) {
		list.put("tonalityPitch", format.format(value));
	}
	
//	public static void printTonalityExternalPitchWeight(double value) {
//		list.put("tonalityExternalPitchWeight",format.format(value));
//		
//	}
//MODIFICATO
	public static void printTonalityScalePitch(double value){
		list.put("tonalityScalePitch",format.format(value));
	}
	
	public static void printTonalityExternalScalePitch(double value) {
		list.put("tonalityExternalScalePitch",format.format(value));
	}

	//GEOMETRIA
	public static void printGeometryScore(double value){
		list.put("geometryScore", format.format(value));
	}
	
	public static void printGeometryScoreJump(double value){
		list.put("geometryScoreJump", format.format(value));
	}
	
	public static void printGeometryJumpCount(double value){
		list.put("geometryJumpCount", format.format(value));
	}
	
	public static void printGeometryJumpDistance(double value){
		list.put("geometryJumpDistance",format.format(value));
	}
	
	public static void printGeometryJumpDirection(double value) {
		list.put("geometryJumpDirection", format.format(value));
	}
	
	public static void printGeometryJumpDirectionUp(double value) {
		list.put("geometryJumpDirectionUp", format.format(value));
	}
	
	public static void printGeometryJumpDirectionDown(double value) {
		list.put("geometryJumpDirectionDown", format.format(value));
	}
	
	public static void printGeometryPeakNumber(double value){
		list.put("geometryPeakNumber", format.format(value));
	}
	
	public static void printGeometryPeakCount(double value){
		list.put("geometryPeakCount", format.format(value));
	}

	public static void printGeometryPeackIdealDistribution(double value){
		list.put("geometryPeakIdealDistribution",format.format(value));
	}
	
	public static void printGeometryPeakDistribution(double value) {
		list.put("geometryPeakDistribution",format.format(value));
	}
	
	public static void printGeometryJumpRecover(double value) {
		list.put("geometryJumpRecover",format.format(value));		
	}
	
	public static void printGeometryRibattuta(double value) {
		list.put("geometryRibattuta",format.format(value));		
	}

//FREQUENCY
	
	public static void printFrequencyScore(double value) {
		list.put("frequencyScore", format.format(value));		
	}
	
	public static void printFrequencyPitchDistribution(double value) {
		list.put("frequencyPitchDistribution", format.format(value));
		
	}

	public static void printModelWhiteTest(double value) {
		
		list.put("modelWhiteTest", String.valueOf(value));
		
	}

	public static void printModelStagionalita(double value) {
		list.put("modelStagionalita", String.valueOf(value));
		
	}

	

	

	

	
	
	
	





	
	
	
	
	
}


