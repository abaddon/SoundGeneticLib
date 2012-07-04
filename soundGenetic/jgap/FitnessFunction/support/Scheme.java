package soundGenetic.jgap.FitnessFunction.support;

/**
 * @author  abaddon
 */
public class Scheme {
	
		boolean[] scheme;
		/**
		 * @uml.property  name="max"
		 */
		int max;
		
		public Scheme(boolean[] schema, int max){
			this.scheme=schema;
			this.max=max;
		}
		
		/**
		 * @return
		 * @uml.property  name="max"
		 */
		public int getMax(){
			return max;
		}
		//ritorna lo schema di default (in DO)
		public boolean[] getscheme(){
			return scheme;
		}
		//riorna lo schema giˆ ruotato per la nota indicata
		public boolean[] getScheme(int nota){
			boolean[] newScheme=scheme;
			for(int i=0; i<12; i++){
				newScheme[(i+nota)%12]=scheme[i];
			}
			return newScheme;
		}
		
	}
