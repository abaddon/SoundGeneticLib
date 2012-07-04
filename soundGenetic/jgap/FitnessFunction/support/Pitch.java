package soundGenetic.jgap.FitnessFunction.support;



/**
 * @author  abaddon
 */
public class Pitch implements Comparable<Pitch> {
	private int nota;
	/**
	 * @uml.property  name="frequenza"
	 */
	private int frequenza;
	/**
	 * @uml.property  name="max"
	 */
	private boolean max=false;
	/**
	 * @uml.property  name="scale"
	 */
	private boolean scale=false;

	public int compareTo(Pitch o) {
		if (this.frequenza < o.frequenza)
			return -1;
		else if (this.frequenza == o.getFrequenza())
			return 0;
		else
			return 1;
	}

	public Pitch(int nota, int frequenza) {
		this.nota = nota;
		this.frequenza = frequenza;
	}

	public int getNome() {
		return nota;
	}

	public void setNome(int nota) {
		this.nota = nota;
	}

	/**
	 * @return
	 * @uml.property  name="frequenza"
	 */
	public int getFrequenza() {
		return frequenza;
	}
	
	/**
	 * @return
	 * @uml.property  name="scale"
	 */
	public boolean isScale(){
		return this.scale;
	}

	/**
	 * @param frequenza
	 * @uml.property  name="frequenza"
	 */
	public void setFrequenza(int frequenza) {
		this.frequenza = frequenza;
	}
	
	/**
	 * @param valore
	 * @uml.property  name="max"
	 */
	public void setMax(boolean valore){
		this.max=valore;
	}
	
	/**
	 * @return
	 * @uml.property  name="max"
	 */
	public boolean isMax(){
		return this.max;
	}

	public String toString() {
		return "[" + String.valueOf(nota) + "." + String.valueOf(frequenza)
				+ "]";
	}

	public void setScala(boolean value) {
		this.scale=value;
		
	}
}
