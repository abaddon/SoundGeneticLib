package soundGenetic.jgap;

import java.util.HashMap;

public class ConfigFunction extends HashMap<String, Double>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2882324453154781535L;

	public ConfigFunction(){
		setGeometricRibattuteWeight1(0.6);
		setGeometricRibattuteWeight2(4);
		
		setGeometricJumpDirectionWeight(10);
		
		setGeometricJumpRecoverWeight(0.2);
		
		setGeometricjumpDistanceWeight(5);
		
		setGeometricjumpCountMalus(0);
		setGeometricjumpCountWeight1(0.1);
		setGeometricjumpCountWeight2(0.1);
		setGeometricjumpCountReference1(0.05);
		setGeometricjumpCountReference2(0.1);
		setGeometricjumpCountWeight3(10);
		
		setGeometricPeakCountWeight(10);
		
		setNoteExternalScalePitchMalus1(0.02);
		setNoteExternalScalePitchMalus2(0.5);
		
		setNoteScalePitchMalus1(0.03);
		setNoteScalePitchMalus2(0.1);
		setNoteScalePitchMalus3(0.5);
		setNoteScalePitchWeight(0.2);
		
		setNoteCheckTemplateWeight1(1);  // >=1
		setNoteCheckTemplateWeight2(1);  // >=1
		
		setFrequencyPitchDistributionWeight(0.8);
	}

	public double getGeometricRibattuteWeight1() {
		return get("GeometricRibattuteWeight1");	
	}
	
	public void setGeometricRibattuteWeight1(double value) {
		put("GeometricRibattuteWeight1",value);
	}
	
	public double getGeometricRibattuteWeight2() {
		return get("GeometricRibattuteWeight2");	
	}
	
	public void setGeometricRibattuteWeight2(double value) {
		put("GeometricRibattuteWeight2",value);
	}

	public double getGeometricJumpDirectionWeight() {
		return get("GeometricJumpDirectionWeight");	
	}
	
	public void setGeometricJumpDirectionWeight(double value) {
		put("GeometricJumpDirectionWeight",value);
	}

	public double getGeometricJumpRecoverWeight() {
		return get("GeometricJumpRecoverWeight");	
	}
	
	public void setGeometricJumpRecoverWeight(double value) {
		put("GeometricJumpRecoverWeight",value);
	}

	public double getGeometricjumpDistanceWeight() {
		return get("GeometricjumpDistanceWeight");
	}
	
	public void setGeometricjumpDistanceWeight(double value) {
		put("GeometricjumpDistanceWeight",value);
	}

	public double getGeometricjumpCountMalus() {
		return get("GeometricjumpCountMalus");
	}
	
	public void setGeometricjumpCountMalus(double value) {
		put("GeometricjumpCountMalus",value);
	}

	public double getGeometricjumpCountWeight1() {
		return get("GeometricjumpCountWeight1");
	}
	
	public void setGeometricjumpCountWeight1(double value){
		put("GeometricjumpCountWeight1",value);
	}
	
	public double getGeometricjumpCountWeight2() {
		return get("GeometricjumpCountWeight2");
	}
	
	public void setGeometricjumpCountWeight2(double value){
		put("GeometricjumpCountWeight2",value);
	}

	public double getGeometricjumpCountReference1() {
		return get("GeometricjumpCountReference1");
	}
	
	public void setGeometricjumpCountReference1(double value){
		put("GeometricjumpCountReference1",value);
	}
	
	public double getGeometricjumpCountReference2() {
		return get("GeometricjumpCountReference2");
	}
	
	public void setGeometricjumpCountReference2(double value){
		put("GeometricjumpCountReference2",value);
	}

	public double getGeometricjumpCountWeight3() {
		return get("GeometricjumpCountWeight3");
	}
	
	public void setGeometricjumpCountWeight3(double value){
		put("GeometricjumpCountWeight3",value);
	}

	public double getGeometricPeakCountWeight() {
		return get("GeometricPeakCountWeight");
	}
	
	public void setGeometricPeakCountWeight(double value) {
		put("GeometricPeakCountWeight",value);
	}

	///NOTE
	
	public double getNoteExternalScalePitchMalus1() {
		return get("NoteExternalScalePitchMalus1");
	}
	
	public void setNoteExternalScalePitchMalus1(double value) {
		put("NoteExternalScalePitchMalus1",value);
	}
	
	public double getNoteExternalScalePitchMalus2() {
		return get("NoteExternalScalePitchMalus2");
	}
	
	public void setNoteExternalScalePitchMalus2(double value) {
		put("NoteExternalScalePitchMalus2",value);
	}

	public double getNoteScalePitchMalus1() {
		return get("NoteScalePitchMalus1");
	}
	
	public void setNoteScalePitchMalus1(double value) {
		put("NoteScalePitchMalus1",value);
	}

	public double getNoteScalePitchMalus2() {
		return get("NoteScalePitchMalus2");
	}
	
	public void setNoteScalePitchMalus2(double value) {
		put("NoteScalePitchMalus2",value);
	}
	
	public double getNoteScalePitchMalus3() {
		return get("NoteScalePitchMalus3");
	}
	
	public void setNoteScalePitchMalus3(double value) {
		put("NoteScalePitchMalus3",value);
	}

	public double getNoteScalePitchWeight() {
		return get("NoteScalePitchWeight");
	}
	
	public void setNoteScalePitchWeight(double value) {
		put("NoteScalePitchWeight",value);
	}

	public double getNoteCheckTemplateWeight1() {
		return get("NoteCheckTemplateWeight1");
	}
	
	public void setNoteCheckTemplateWeight1(double value) {
		put("NoteCheckTemplateWeight1",value);
	}
	
	public double getNoteCheckTemplateWeight2() {
		return get("NoteCheckTemplateWeight2");
	}
	
	public void setNoteCheckTemplateWeight2(double value) {
		put("NoteCheckTemplateWeight2",value);
	}

	public double getFrequencyPitchDistributionWeight() {
		return get("FrequencyPitchDistributionWeight");
	}
	
	public void setFrequencyPitchDistributionWeight(double value) {
		put("FrequencyPitchDistributionWeight",value);
	}
	

	
	
	
	
	
	


}
