package soundGenetic;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import tools.findWeight.FindWeightFromMidi;
import tools.findWeight.MinimiQuadrati;

public class Main {

	/**
	 * @param args
	 */
	private String svn_version = "$Revision:";
	
	
	public Main(){
		System.out.println(getClass().getResource("/tools/findWeight/DefaultConfig.gsxml").getFile());;
	}
	
	public static void main(String[] args) {
		
		
		//MinimiQuadrati min=new MinimiQuadrati();
		
		
		String path="/Users/abaddon/Documents/Universita/TESI_SPECIALISTICA/midi/mozart/";
		FindWeightFromMidi findWeightFromMidi=new FindWeightFromMidi();
		ArrayList<File> list=new ArrayList<File>();
		list.add(new File(path+"mops1m1.mid"));
		list.add(new File(path+"mops1m2.mid"));
		list.add(new File(path+"mops1m3.mid"));
		list.add(new File(path+"mops2m1.mid"));
		list.add(new File(path+"mops2m2.mid"));
		list.add(new File(path+"mops2m3.mid"));
//		list.add(new File(path+"moz_397.mid"));
//		list.add(new File(path+"moz_475.mid"));
		list.add(new File(path+"moz-283a.mid"));
		list.add(new File(path+"moz-283c.mid"));
//		list.add(new File(path+"Moz.mid"));
		list.add(new File(path+"mozk622.mid"));
		list.add(new File(path+"mozmenu.mid"));
		list.add(new File(path+"wamk280.mid"));
		list.add(new File(path+"wamk282.mid"));
		list.add(new File(path+"wamk283.mid"));
		//HashMap<String, String> scoreList=test.extractMidi(file);
		ArrayList<HashMap<String, Double>> scoreMatrix=findWeightFromMidi.extractMidiFiles(list);
		int n=scoreMatrix.get(1).size();
		int m=scoreMatrix.size();
		MinimiQuadrati minimiQuadrati=new MinimiQuadrati(scoreMatrix.size(),n,scoreMatrix);
		minimiQuadrati.printAll();
		HashMap<String,Double> weightList=minimiQuadrati.getWeight();
		System.out.println("RESULT:");
		for(Double valore: weightList.values())
			System.out.println(valore);
		
		//creo il config nuovo
		findWeightFromMidi.createNewFileConfig(weightList, new File("./filetest1.gsxml"));
		
//PESSIMA SOLUZIONE JGAP NON ADATTO
		//JGapFindWeight jGapFindWeight=new JGapFindWeight(scoreList);
		//jGapFindWeight.start();
	}

//	private static HashMap<String, Double> convertHashMap(HashMap<String, String> list) {
//		HashMap<String, Double> scoreList=new HashMap<String, Double>();
//		for(String key : list.keySet()){
//			scoreList.put(key, Double.valueOf(list.get(key).replace(",", ".")));
//			list.get(key);
//		}
//		
//		return scoreList;
//		
//	}

}
