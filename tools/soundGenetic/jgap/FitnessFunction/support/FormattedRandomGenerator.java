package soundGenetic.jgap.FitnessFunction.support;

import org.jgap.RandomGenerator;

public class FormattedRandomGenerator implements RandomGenerator {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5019849036983462576L;

	private double m_nextDouble;
	
	public FormattedRandomGenerator() {
		// TODO Auto-generated constructor stub
	}
	
	public FormattedRandomGenerator(double a_nextDouble){
		this();
	    setNextDouble(a_nextDouble);
	}
	
	

	public void setNextDouble(double a_nextDouble) {
	    m_nextDouble = a_nextDouble % 1.0d;
	  }

	public boolean nextBoolean() {
		// TODO Auto-generated method stub
		return false;
	}

	public double nextDouble() {
		
		return 0;
	}

	public float nextFloat() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int nextInt() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int nextInt(int arg0) {
		
		return arg0;
	}

	public long nextLong() {
		// TODO Auto-generated method stub
		return 0;
	}

}
