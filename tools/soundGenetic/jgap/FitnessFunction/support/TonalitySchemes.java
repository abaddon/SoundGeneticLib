package soundGenetic.jgap.FitnessFunction.support;

import java.util.ArrayList;
import java.util.HashMap;

import soundGenetic.jgap.GeneticConstants;


/**
 * @author   abaddon
 */
public class TonalitySchemes extends HashMap<Integer, ArrayList<Scheme>>{
	
	private static TonalitySchemes instance=null;
	
	private TonalitySchemes(){
		loadSchemi();
	}
	
	/**
	 * @return
	 * @uml.property  name="instance"
	 */
	public static TonalitySchemes getInstance(){
		if(instance==null)
			instance=new TonalitySchemes();
		return instance;
	}

	
	private void loadSchemi(){
		clear();
		ArrayList<Scheme> schemes=new ArrayList<Scheme>();

		//MAGGIORE 7-6-5 note
		
		schemes.add(new Scheme(new boolean[]{true,false,true,false,true,true,false,true,false,true,false,true},7));//2212221
		schemes.add(new Scheme(new boolean[]{true,false,false,false,true,true,false,true,false,true,false,true},6));//412221
		schemes.add(new Scheme(new boolean[]{true,false,true,false,false,true,false,true,false,true,false,true},6));//232221
		schemes.add(new Scheme(new boolean[]{true,false,true,false,true,false,false,true,false,true,false,true},6));//223221
		schemes.add(new Scheme(new boolean[]{true,false,true,false,true,true,false,false,false,true,false,true},6));//221421
		schemes.add(new Scheme(new boolean[]{true,false,true,false,true,true,false,true,false,false,false,true},6));//221241
		schemes.add(new Scheme(new boolean[]{true,false,true,false,true,true,false,true,false,true,false,false},6));//221223
		schemes.add(new Scheme(new boolean[]{false,false,true,false,true,true,false,true,false,true,false,true},6));//212223
		schemes.add(new Scheme(new boolean[]{true,false,false,false,false,true,false,true,false,true,false,true},5));//52221
		schemes.add(new Scheme(new boolean[]{true,false,true,false,false,false,false,true,false,true,false,true},5));//25221
		schemes.add(new Scheme(new boolean[]{true,false,true,false,true,false,false,false,false,true,false,true},5));//22521
		schemes.add(new Scheme(new boolean[]{true,false,true,false,true,true,false,false,false,false,false,true},5));//22161
		schemes.add(new Scheme(new boolean[]{true,false,true,false,true,true,false,true,false,false,false,false},5));//22125
		schemes.add(new Scheme(new boolean[]{false,false,true,false,true,true,false,true,false,true,false,false},5));//21225
		schemes.add(new Scheme(new boolean[]{false,false,false,false,true,true,false,true,false,true,false,true},5));//12225
		put(GeneticConstants.MAGGIORE, schemes);//MAGGIORE
		
		//MINORE NATURALE 7-6-5 note 
		schemes=new ArrayList<Scheme>();
		schemes.add(new Scheme(new boolean[]{true,false,true,true,false,true,false,true,true,false,true,false},7));//2122122
		schemes.add(new Scheme(new boolean[]{true,false,false,true,false,true,false,true,true,false,true,false},6));//322122
		schemes.add(new Scheme(new boolean[]{true,false,true,false,false,true,false,true,true,false,true,false},6));//232122
		schemes.add(new Scheme(new boolean[]{true,false,true,true,false,false,false,true,true,false,true,false},6));//214122
		schemes.add(new Scheme(new boolean[]{true,false,true,true,false,true,false,false,true,false,true,false},6));//212322
		schemes.add(new Scheme(new boolean[]{true,false,true,true,false,true,false,true,false,false,true,false},6));//212232
		schemes.add(new Scheme(new boolean[]{true,false,true,true,false,true,false,true,true,false,false,false},6));//212214
		schemes.add(new Scheme(new boolean[]{false,false,true,true,false,true,false,true,true,false,true,false},6));//122124
		schemes.add(new Scheme(new boolean[]{true,false,false,false,false,true,false,true,true,false,true,false},5));//52122
		schemes.add(new Scheme(new boolean[]{true,false,true,false,false,false,false,true,true,false,true,false},5));//25122
		schemes.add(new Scheme(new boolean[]{true,false,true,true,false,false,false,false,true,false,true,false},5));//21522
		schemes.add(new Scheme(new boolean[]{true,false,true,true,false,true,false,false,false,false,true,false},5));//21252
		schemes.add(new Scheme(new boolean[]{true,false,true,true,false,true,false,true,false,false,false,false},5));//21225
		schemes.add(new Scheme(new boolean[]{false,false,true,true,false,true,false,true,true,false,false,false},5));//12216
		schemes.add(new Scheme(new boolean[]{false,false,false,true,false,true,false,true,true,false,true,false},5));//22125
		put(GeneticConstants.MINORE_NATURALE, schemes);//MINORE NATURALE
		
		//MINORE ARMONICO 7-6-5 note
		schemes=new ArrayList<Scheme>();
		schemes.add(new Scheme(new boolean[]{true,false,true,true,false,true,false,true,true,false,false,true},7));//2122131
		schemes.add(new Scheme(new boolean[]{true,false,false,true,false,true,false,true,true,false,false,true},6)); //322131
		schemes.add(new Scheme(new boolean[]{true,false,true,false,false,true,false,true,true,false,false,true},6)); //232131
		schemes.add(new Scheme(new boolean[]{true,false,true,true,false,false,false,true,true,false,false,true},6)); //214131
		schemes.add(new Scheme(new boolean[]{true,false,true,true,false,true,false,false,true,false,false,true},6)); //212331
		schemes.add(new Scheme(new boolean[]{true,false,true,true,false,true,false,true,false,false,false,true},6)); //212241
		schemes.add(new Scheme(new boolean[]{true,false,true,true,false,true,false,true,true,false,false,false},6)); //212214
		schemes.add(new Scheme(new boolean[]{false,false,true,true,false,true,false,true,true,false,false,true},6)); //122133
		schemes.add(new Scheme(new boolean[]{true,false,false,false,false,true,false,true,true,false,false,true},5));//52131
		schemes.add(new Scheme(new boolean[]{true,false,true,false,false,false,false,true,true,false,false,true},5));//25131
		schemes.add(new Scheme(new boolean[]{true,false,true,true,false,false,false,false,true,false,false,true},5));//21531
		schemes.add(new Scheme(new boolean[]{true,false,true,true,false,true,false,false,false,false,false,true},5));//21261
		schemes.add(new Scheme(new boolean[]{true,false,true,true,false,true,false,true,false,false,false,false},5));//21225
		schemes.add(new Scheme(new boolean[]{false,false,true,true,false,true,false,true,true,false,false,false},5));//12216
		schemes.add(new Scheme(new boolean[]{false,false,false,true,false,true,false,true,true,false,false,true},5));//22134
		put(GeneticConstants.MINORE_ARMONICO, schemes);//MINORE ARMONICO
		
		//SEMIDIMINUITA
		schemes=new ArrayList<Scheme>();
		schemes.add(new Scheme(new boolean[]{true,true,false,true,true,false,true,true,false,true,true,false},8));//12121212
		put(GeneticConstants.SEMIDIMINUITA, schemes);//SEMIDIMINUITA
		
		//ESATONALE
		schemes=new ArrayList<Scheme>();
		schemes.add(new Scheme(new boolean[]{true,false,true,false,true,false,true,false,true,false,true,false},6)); //222222
		put(GeneticConstants.ESATONALE, schemes);//ESATONALE
	}
}


