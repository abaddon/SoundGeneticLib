package soundGenetic.jgap.FitnessFunction.support;

/**
 * @author  abaddon
 */
public class PropertyGeometryPitches {
	/**
	 * @uml.property  name="previousPitch"
	 */
	private double previousPitch;
	/**
	 * @uml.property  name="pitch"
	 */
	private double pitch;
	/**
	 * @uml.property  name="nextPitch"
	 */
	private double nextPitch;
	private double interval;
	/**
	 * @uml.property  name="position"
	 */
	private double position;
	private double derivDx = 0;
	private double derivSx = 0;
	private int max=0; //o nulla, -1 min, +1 max
	/**
	 * @uml.property  name="lenghtJump"
	 */
	private double lenghtJump;
	/**
	 * @uml.property  name="jumpDistance"
	 */
	private int jumpDistance=0; //distanza tra i salti
	/**
	 * @uml.property  name="peak"
	 */
	private boolean peak=false;
	/**
	 * @uml.property  name="jump"
	 */
	private boolean jump=false;

	
	public PropertyGeometryPitches(double previousPitch, double pitch, double nextPitch, double interval,double position) {
		this.previousPitch=previousPitch;
		this.pitch=pitch;
		this.nextPitch=nextPitch;
		this.interval=interval;
		this.position=position;
		this.lenghtJump=Math.abs(pitch-previousPitch);
		setDerivative();
	//	setJump();
		
	}
	/**
	 * @return
	 * @uml.property  name="lenghtJump"
	 */
	public double getLenghtJump(){
		return lenghtJump;
	}
	
	/**
	 * @param value
	 * @uml.property  name="jump"
	 */
	public void setJump(boolean value){
		jump=value;
//		if(value)
//			this.jump=true;
//		else
//			this.jump=false;
	}
	
	private void setDerivative(){
		derivSx = (pitch - previousPitch) / interval; // derivata sx
		derivDx = (nextPitch - pitch) / interval; // derivata dx
		// calcolo max e min
		if (derivSx > 0 && derivDx < 0) 
			max = 1;
		 else if (derivSx < 0 && derivDx > 0)
			max = -1;
		if(max!=0)
			peak=true;
	}
	

	
	public String getDerivate(){
		return "SX: "+derivSx+" DX: "+derivDx;
	}
	
	/**
	 * @param distance
	 * @uml.property  name="jumpDistance"
	 */
	public void setJumpDistance(int distance){
		this.jumpDistance=distance;
	}
	
	public boolean isMax(){
		if(max==1)
			return true;
		else 
			return false;
	}
	
	/**
	 * @return
	 * @uml.property  name="peak"
	 */
	public boolean isPeak(){
		return peak;
	}
	
	/**
	 * @return
	 * @uml.property  name="jump"
	 */
	public boolean isJump(){
		return jump;
	}
	
	public boolean isMin(){
		if(max==-1)
			return true;
		else 
			return false;
	}
	

	
	/**
	 * @return
	 * @uml.property  name="position"
	 */
	public double getPosition(){
		return position;
	}
	
	/**
	 * @return
	 * @uml.property  name="jumpDistance"
	 */
	public int getJumpDistance(){
		return jumpDistance;
	}
	
	//-1 scende +1 sale 0 ERR
	public int getJumpDirection(){
		if(derivDx>=0 && derivSx>=0)
			return 1;
		else if(derivDx<=0 && derivSx<=0)
			return -1;
		else
			return 0;
	}
	
	/**
	 * @return
	 * @uml.property  name="previousPitch"
	 */
	public double getPreviousPitch(){
		return this.previousPitch;
	}
	
	/**
	 * @return
	 * @uml.property  name="nextPitch"
	 */
	public double getNextPitch(){
		return this.nextPitch;
	}
	/**
	 * @return
	 * @uml.property  name="pitch"
	 */
	public double getPitch() {
		return this.pitch;
	}
	
	
	
}
