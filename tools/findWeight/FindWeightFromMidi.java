package tools.findWeight;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;

import soundGenetic.FitnessValue;
import soundGenetic.jgap.ConfigFitness;
import soundGenetic.jgap.ConfigJGap;
import soundGenetic.jgap.FitnessFunction.FuncDistribFrequency;
import soundGenetic.jgap.FitnessFunction.FuncDistribGeometric;
import soundGenetic.jgap.FitnessFunction.FuncDistribNote;
import soundGenetic.jgap.FitnessFunction.support.PitchFunction;
import tools.Configs;

/**
 * @author   abaddon
 */
public class FindWeightFromMidi {
		private ConfigFitness configFitness=new ConfigFitness();
		private ConfigJGap configJGap=new ConfigJGap();
		private	PitchFunction pitchFunction=null;
		private URL loadXml=FindWeightFromMidi.class.getResource("/tools/findWeight/DefaultConfig.gsxml");
		private ArrayList<String> listScoreKey=new ArrayList<String>();
//		private URL urlJar=this.getClass().getProtectionDomain().getCodeSource().getLocation();
//		private final ClassLoader loader = this.getClass().getClassLoader();
//		private final URL loadXml = loader.getResource("DefaultConfig.gsxml");
		
	public FindWeightFromMidi(){
		this.listScoreKey=getAllListScoreKey();
		//ClassLoader loader = this.getClass().getClassLoader();
		
		//System.out.println("-->"+urlJar.getPath()+"/tools/findWeight/DefaultConfig.gsxml");
		//System.out.println("-->"+loadXml.getPath());
		//getClass().getResource("tools/findWeight/DefaultConfig.gsxml").toString()
		//ClassFile defaultConfig=new ClassPath().getFile("/tools/findWeight/DefaultConfig.gsxml");
		
	}
	
	public FindWeightFromMidi(ArrayList<String> listScoreKey){
		this.listScoreKey=listScoreKey;
	}
	
	public ArrayList<HashMap<String, Double>> extractMidiFiles(ArrayList<File> list){
		ArrayList<HashMap<String, Double>> matrix= new ArrayList<HashMap<String, Double>>();
		for(File file: list){
			matrix.add(extractMidiFile(file));
		}
		return matrix;
	}
	
	
	private ArrayList<Integer> openMidi(File midiFile){
		ArrayList list=new ArrayList<Integer>();
	//	Sequencer sequencer=null;
		 Sequence sequence=null;
		try {
		//	MidiFileFormat midiFileFormat=MidiSystem.getMidiFileFormat(midiFile);
			sequence = MidiSystem.getSequence(midiFile);
			//sequencer=MidiSystem.getSequencer();
			//sequencer.open();
			//sequencer.setSequence(sequence);
			
//			sequencer.close();
			//for(int i=0; i<sequence.getTracks().length;i++){
			//	System.out.println("track: "+i);
			//	int count=0;
//SELEIONO IL TRACK 1 DI SOLITO QUELLO PIU' USATO
			int i=1;
				for(int x=0; x<(sequence.getTracks()[i]).size();x++){
					MidiEvent event = (sequence.getTracks()[i]).get( x );
					MidiMessage message=event.getMessage();
					message.getMessage();
					if(message  instanceof ShortMessage) {
						ShortMessage shortMsg=(ShortMessage)message;
						if(shortMsg.getCommand()==0x90){
							list.add(shortMsg.getData1());
						//	count++;
							//System.out.println(shortMsg.getData1()+" "+(shortMsg.getData1()%12)+" esadecimale: "+shortMsg.getCommand());
						}
					}
					
				}
			//	System.out.println("size: "+count);
			//}
		
			 //sequencer.setSequence(sequence);
		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	       
		return list;
	}
	
	public HashMap<String, Double> extractMidiFile(File file){
		ArrayList<Integer> pitchList=openMidi(file);

		
		pitchFunction=new PitchFunction(pitchList,configFitness);
		
		Configs config=new Configs();
		config.ImportFromXml(configFitness, configJGap, loadXml);
		System.out.println("pitchList "+pitchList.size());
		configJGap.setSizeSoundChromosome(pitchList.size());
		
		FuncDistribNote funcDistribNote=new FuncDistribNote(configFitness,configJGap);
		double risDistribNote=funcDistribNote.runTest(pitchFunction);
		
		FuncDistribGeometric funcDistribGeometric=new FuncDistribGeometric(configFitness,configJGap);
		double risDistribGeometric=funcDistribGeometric.runTest(pitchFunction);
		
		FuncDistribFrequency funcDistribFrequency=new FuncDistribFrequency(configFitness,configJGap);
		double risDistribFrequency=funcDistribFrequency.runTest(pitchFunction);

//		FuncDistribModel funcDistribModel=new FuncDistribModel(configFitness,configJGap);
//		double risDistribModel=funcDistribModel.runTest(pitchFunction);
		HashMap<String, Double> scoreList=convert(listScoreKey);
		
		System.out.println(risDistribNote+" + "+risDistribGeometric+" + "+risDistribFrequency);
		
		return scoreList;
	}


	private HashMap<String, Double> convert(ArrayList<String> listKey) {
		HashMap<String, Double> scoreList=new HashMap<String, Double>();
		String output="Result:";
		for(String key : listKey){
			System.out.println(key);
			String valore=FitnessValue.getListValue().get(key).replace(",", ".");
			if(valore.matches("([0-9]*\\.[0-9]+|[0-9]+)") /*&& Double.valueOf(valore)!=0*/){
//				if(Double.valueOf(valore)==0)
//					scoreList.put(key, 0.00000000001);
//				else
					scoreList.put(key, Double.valueOf(valore));
				output+=key+": "+FitnessValue.getListValue().get(key)+"\n";
			}else
				System.out.println("ERRORE: "+key+": "+FitnessValue.getListValue().get(key) );
		}
		System.out.println(output);
		return scoreList;
	}
	
	
	public ArrayList<String> getAllListScoreKey(){
		ArrayList<String> listKey=new ArrayList<String>();
		listKey.add("tonalityScalePitch");
		listKey.add("tonalityPitch");
		listKey.add("tonalityExternalScalePitch");
		
		listKey.add("geometryScoreJump");
		listKey.add("geometryJumpDistance");
		listKey.add("geometryRibattuta");
		listKey.add("geometryPeakCount");
		listKey.add("geometryPeakDistribution");
		listKey.add("geometryJumpRecover");
		listKey.add("geometryJumpDirection");
		
		listKey.add("frequencyPitchDistribution");
		
//		listKey.add("tonalityScalePitchWeight");
//		listKey.add("tonalityExternalScalePitchWeight");
//		listKey.add("tonalityPitchWeight");
//
//		listKey.add("geometricJumpRecoverWeight");
//		listKey.add("geometricRibattutaWeight");
//		listKey.add("peakDistributionWeight");
//		listKey.add("jumpWeight");
//		listKey.add("peakCountWeight");
//		listKey.add("jumpDistanceWeight");
//		listKey.add("jumpDirectionWeight");
//		
//		listKey.add("frequencyPitchDistributionWeight");
		
		
		return listKey;
	}
	
//	private ArrayList<String> getListScoreKey1(){
//		ArrayList<String> listKey=new ArrayList<String>();
//		listKey.add("tonalityScalePitch");
//		listKey.add("tonalityPitch");
//		listKey.add("tonalityExternalScalePitch");
//		
//		listKey.add("geometryScoreJump");
//		listKey.add("geometryJumpDistance");
//		listKey.add("geometryRibattuta");
//		listKey.add("geometryPeakCount");
//		listKey.add("geometryPeakDistribution");
//		listKey.add("geometryJumpRecover");
//		listKey.add("geometryJumpDirection");
//		
//		//listKey.add("frequencyPitchDistribution");
//		
////		listKey.add("tonalityScalePitchWeight");
////		listKey.add("tonalityExternalScalePitchWeight");
////		listKey.add("tonalityPitchWeight");
////
////		listKey.add("geometricJumpRecoverWeight");
////		listKey.add("geometricRibattutaWeight");
////		listKey.add("peakDistributionWeight");
////		listKey.add("jumpWeight");
////		listKey.add("peakCountWeight");
////		listKey.add("jumpDistanceWeight");
////		listKey.add("jumpDirectionWeight");
////		
////		listKey.add("frequencyPitchDistributionWeight");
//		
//		
//		return listKey;
//	}
	
	public void createNewFileConfig(HashMap<String, Double> weightList,File file){
		Configs configs= new Configs();
		configJGap.setSizeSoundChromosome(20);
		for(String key: weightList.keySet()){
			configFitness.put(key, weightList.get(key));
		}
		configs.ExportToXml(configFitness, configJGap, file.getAbsolutePath());
	}

}
