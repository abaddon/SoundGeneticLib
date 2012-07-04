package soundGenetic.jgap.FitnessFunction;

import java.util.ArrayList;

import soundGenetic.FitnessValue;
import soundGenetic.jgap.ConfigFitness;
import soundGenetic.jgap.ConfigJGap;
import soundGenetic.jgap.FitnessFunction.support.PropertyGeometryPitches;

public class FuncDistribGeometric extends GenericFitness {

	private static final long serialVersionUID = 6480286332309188050L;


	public FuncDistribGeometric(ConfigFitness configFitness, ConfigJGap configJGap) {
		super(configFitness, configJGap);
	}


	@Override
	protected double evaluate() {

//		getJumps();

		//Partial Result
		double resultJumpCount=jumpCount();
		double resultJumpDistance=jumpDistance();
		double resultJumpDirection=jumpDirection();
		double resultPeakCount=peakCount();
		double resultPeakDistribution=peakDistribution();
		double resultJumpRecover=jumpRecover();
		double resultRibattuta=ribattute();
		
		FitnessValue.printGeometryJumpCount(pitchFunction.getJumpCount());

		FitnessValue.printGeometryScoreJump(resultJumpCount);
		FitnessValue.printGeometryJumpDistance(resultJumpDistance);
		FitnessValue.printGeometryJumpDirection(resultJumpDirection);
		FitnessValue.printGeometryPeakCount(resultPeakCount);
		FitnessValue.printGeometryPeakDistribution(resultPeakDistribution);
		FitnessValue.printGeometryJumpRecover(resultJumpRecover);
		FitnessValue.printGeometryRibattuta(resultRibattuta);

		//System.out.println("ribatutte: "+resultRibattuta);
		//total result
		double result=configFitness.getJumpWeight()*resultJumpCount+
								configFitness.getJumpDistanceWeight()*resultJumpDistance+
								configFitness.getJumpDirectionWeight()*resultJumpDirection+
								configFitness.getPeakCountWeight()*resultPeakCount+
								configFitness.getPeakDistributionWeight()*resultPeakDistribution+
								configFitness.getGeometricJumpRecoverWeight()*resultJumpRecover+
								configFitness.getGeometricRibattutaWeight()*resultRibattuta;
		
	
		result=result/(configFitness.getJumpWeight()+
				configFitness.getJumpDistanceWeight()+
				configFitness.getJumpDirectionWeight()+
				configFitness.getPeakCountWeight()+
				configFitness.getPeakDistributionWeight()+
				configFitness.getGeometricJumpRecoverWeight()+
				configFitness.getGeometricRibattutaWeight());
		
		FitnessValue.printGeometryScore(result);

		return result;
	}
	
	
// - RIBATTUTE
//	private double ribattute(){
//		int riferimento=-1;
//		int countLocal=0;
//		int count=0;
//		for (int i = 0; i < chromosome.size(); i++) {
//			int pitch = ((Integer) ((CompositeGene) chromosome.getGene(i)).geneAt(2).getAllele())% 12;
//			if(pitch==riferimento){
//				countLocal++;
//			}else{
//				riferimento=pitch;
//				if(countLocal!=0){
//					count+=Math.pow(2.0,(countLocal-1));
//					countLocal=0;
//				}
//				
//			}
//			
//		}
//		
//		double peso1=configFitness.getConfigFunction().getGeometricRibattuteWeight1();
//		double peso2=configFitness.getConfigFunction().getGeometricRibattuteWeight2();
//		double result=1-Math.pow(count/(Math.pow(2.0, peso2)),peso1);
//		if(result<0)
//			result=0;
//		return result;
//	}
	
	private double ribattute(){
		int riferimento=-1;
		int countLocal=0;
		int count=0;
		for (int i = 0; i < pitchFunction.getPitchs().size(); i++) {
			int pitch = pitchFunction.getPitchs().get(i)% 12;
			if(pitch==riferimento){
				countLocal++;
			}else{
				riferimento=pitch;
				if(countLocal!=0){
					count+=Math.pow(2.0,(countLocal-1));
					countLocal=0;
				}
				
			}
			
		}
		
		double peso1=configFitness.getConfigFunction().getGeometricRibattuteWeight1();
		double peso2=configFitness.getConfigFunction().getGeometricRibattuteWeight2();
		double maxStd=(double)pitchFunction.getPitchs().size()/20.0;
		double result=1-Math.pow(count/(Math.pow(2.0, peso2)*maxStd),peso1);
		if(result<0)
			result=0;
		return result;
	}
	
// - DIREZIONE DEI SALTI
	private double jumpDirection() {
		int up=0;
		int down=0;
		for(PropertyGeometryPitches jump: pitchFunction.getPropertyPitches()){
			if(jump.isJump() && !jump.isPeak()){
				if(jump.getJumpDirection()>0)
					up++;
				else if(jump.getJumpDirection()<0)
					down++;
				else{
					System.out.println("ERRORE salto con derivate diverse!!! MERDA");
					System.out.println(jump.getDerivate());
					System.out.println("t-1: "+jump.getPreviousPitch()+" t: "+jump.getPitch()+" t+1: "+jump.getNextPitch()+" isPeak: "+jump.isPeak());
					System.exit(1);
				}
			}
		}
		
		//0.5:value:1:x
		//result=|x|1 (mod 1)
		FitnessValue.printGeometryJumpDirectionUp(up);
		FitnessValue.printGeometryJumpDirectionDown(down);
//		double tot=up+down;
//		double value=(double)up/tot;
//		double result=(value/0.5)%1.0;
		double peso=configFitness.getConfigFunction().getGeometricJumpDirectionWeight()*((double)pitchFunction.getPitchs().size()/20.0);
		double result=1-(Math.abs(up-down)/peso);
		return result;
	}

// - RECUPERO DEI SALTI	
//	private double jumpRecover(){
//		double malus=0.0;
//		for (int i = 0; i < chromosome.size()-2; i++){
//			int nota0=(Integer)((CompositeGene) chromosome.getGene(i)).geneAt(2).getAllele();
//			int nota1=(Integer)((CompositeGene) chromosome.getGene(i+1)).geneAt(2).getAllele();
//			int nota2=(Integer)((CompositeGene) chromosome.getGene(i+2)).geneAt(2).getAllele();
//			int dif1=nota1-nota0;
//			int dif2=nota2-nota1;
//
//			if((Math.abs(dif1)>2 || Math.abs(dif2)>2) && dif1*dif2>0){
//				if(Math.abs(dif1)<=2 ){ 
//					malus+=0;
//				}else if(Math.abs(dif2)<=2){
//					malus+=1;
//				}else{
//					malus+=Math.abs(dif1+dif2);
//				}
//			}
//			
//		}
//		double maxJump=12;
////		System.out.println(chromosome.getGenes().length+" "+configJGap.getSizeSoundChromosome());
//	//????	///double result=1-Math.pow((malus/(size*chromosome.getGenes().length),0.2);
//		
//		double peso=configFitness.getConfigFunction().getGeometricJumpRecoverWeight();
//		double result=1-Math.pow((malus/maxJump*(double) chromosome.getGenes().length),peso);
//		//System.out.println("jump recover: "+result);
//		if (result<0)
//			result=0;
//		return result;
//	}
	
	private double jumpRecover(){
		double malus=0.0;
		for (int i = 0; i < pitchFunction.getPitchs().size()-2; i++){
			int nota0=pitchFunction.getPitchs().get(i);
			int nota1=pitchFunction.getPitchs().get(i+1);
			int nota2=pitchFunction.getPitchs().get(i+2);
			int dif1=nota1-nota0;
			int dif2=nota2-nota1;

			if((Math.abs(dif1)>2 || Math.abs(dif2)>2) && dif1*dif2>0){
				if(Math.abs(dif1)<=2 ){ 
					malus+=0;
				}else if(Math.abs(dif2)<=2){
					malus+=1;
				}else{
					malus+=Math.abs(dif1+dif2);
				}
			}
			
		}
		double maxJump=12;
//		System.out.println(chromosome.getGenes().length+" "+configJGap.getSizeSoundChromosome());
	//????	///double result=1-Math.pow((malus/(size*chromosome.getGenes().length),0.2);
		
		double peso=configFitness.getConfigFunction().getGeometricJumpRecoverWeight();
		double result=1-Math.pow((malus/(maxJump*(double) pitchFunction.getPitchs().size())),peso);
		//System.out.println("jump recover: "+result);
		if (result<0)
			result=0;
		return result;
	}

//DISTRIBUZIONE DEI PICCHI
//TODO countpeak all'interno di ProprietyPitch() da fare!
//	private double peakDistribution() {
//		double result=0;
//		int countPeak=0;
//		int oldPosition=0;
//		ArrayList<Integer> spaces=new ArrayList<Integer>();
//		for(ProprietyPitch proprietyPitch: PitchFunction.getInstsance().getProprietyPitches()){
//			if(proprietyPitch.isPeak()){
//				spaces.add(((int)proprietyPitch.getPosition())-oldPosition-1);
//				oldPosition=((int)proprietyPitch.getPosition());
//				countPeak++;
//			}
//		}
//		if(oldPosition<chromosome.size())
//			spaces.add(chromosome.size()-oldPosition);
//		int pitchFree=chromosome.size()-countPeak;
//		int numSpace=countPeak+1;
//		double pitchForSpace=(double)pitchFree/(double)numSpace;
//		FitnessValue.printGeometryPeackIdealDistribution(pitchForSpace);
//		for(Integer space: spaces){
//			result=result+Math.abs((double)space-pitchForSpace);
//		}
//		double maxResult=(double)chromosome.size()-(double)countPeak+(double)countPeak*pitchForSpace;
//		
//		
//////DEBUG
////		if(result>maxResult){
////		String str="";
////		for (int i = 0; i < chromosome.size(); i++){
////			str=str+((CompositeGene) chromosome.getGene(i)).geneAt(2).getAllele()+"   "; // nota la tempo t
////		}
////		System.out.println(str);
////		str="";
////		String str2="";
////		for(ProprietyPitch proprietyPitch: PitchFunction.getInstsance().getProprietyPitches()){
////			if(proprietyPitch.isPeak())
////				str=str+(proprietyPitch.getPosition())+" ";
////			str2=str2+proprietyPitch.getDerivate()+" | ";
////		}
////		System.out.println("posizione: "+str);
////		System.out.println("derivata: "+str2);
////		System.out.println("num tot note: "+chromosome.size()+" numero picchi: "+countPeak+"  note da distribuire: "+pitchFree);
////		System.out.println("num spazi: "+numSpace);
////		System.out.println("note per spazio: "+pitchForSpace+" ="+pitchFree+"/"+numSpace);
////		String stringa="";
////		for(Integer space: spaces){
////			stringa=stringa+"|"+(double)space+"-"+pitchForSpace+"| + ";
////		}
////		System.out.println(stringa+" = result: "+result);
////		System.out.println("max = "+maxResult);
////		
////			System.out.println("ERRORREEEEEEEE RESULT>MAXRESULT!!!!");
////			System.exit(1);
////		}
////DEBUG FINE
//		
//		result=1.0-(result/maxResult);
//	
//		return result;
//	}
	
//	private double peakDistribution() {
//		double result=0;
//		int countPeak=0;
//		int oldPosition=0;
//		ArrayList<Integer> spaces=new ArrayList<Integer>();
//		for(PropertyGeometryPitches proprietyPitch: pitchFunction.getPropertyPitches()){
//			if(proprietyPitch.isPeak()){
//				spaces.add(((int)proprietyPitch.getPosition())-oldPosition-1);
//				oldPosition=((int)proprietyPitch.getPosition());
//				countPeak++;
//			}
//		}
//		if(oldPosition<pitchFunction.getPitchs().size())
//			spaces.add(pitchFunction.getPitchs().size()-oldPosition);
//		int pitchFree=pitchFunction.getPitchs().size()-countPeak;
//		int numSpace=countPeak+1;
//		double pitchForSpace=(double)pitchFree/(double)numSpace;
//		FitnessValue.printGeometryPeackIdealDistribution(pitchForSpace);
//		for(Integer space: spaces){
//			result=result+Math.abs((double)space-pitchForSpace);
//		}
//		double maxResult=(double)pitchFunction.getPitchs().size()-(double)countPeak+(double)countPeak*pitchForSpace;
//		
//		result=1.0-(result/maxResult);
//	
//		return result;
//	}
	
	private double peakDistribution() {
		
		int countPeak=0;
		int oldPosition=0;
		double sommatoria=0;
		double sqmIdeal=0;
		double peso=0.2;
		
		ArrayList<Integer> spaces=new ArrayList<Integer>();
		for(PropertyGeometryPitches proprietyPitch: pitchFunction.getPropertyPitches()){
			if(proprietyPitch.isPeak()){
				spaces.add(((int)proprietyPitch.getPosition())-oldPosition-1);
				oldPosition=((int)proprietyPitch.getPosition());
				countPeak++;
			}
		}
		
		double percPeak=countPeak*100/pitchFunction.getPitchs().size();
		double intervIdeal=100/(percPeak+1);
		for(Integer space: spaces){
			sommatoria=+Math.pow(Math.abs((double)space-intervIdeal),2);
		}
		double sqm=Math.sqrt(sommatoria);
		double result=1-Math.pow(Math.abs((sqmIdeal-sqm)/100),peso);
		if(result<0)
			result=0;
		return result;
	}

// - DISTANZA TRA UN SALTO E L'ALTRO 
//	private double jumpDistance() {
//		double sommatoria=0;
//
//		for(PropertyGeometryPitches proprietyPitch: pitchFunction.getPropertyPitches()){
//			if(proprietyPitch.isJump()){
//		
//				sommatoria+=proprietyPitch.getJumpDistance();
//			}
//		}
//		
//		//result=(result/(double)PitchFunction.getInstsance().getJumpCount())/configFitness.getJumpDistance();
//		
//		double peso=configFitness.getConfigFunction().getGeometricjumpDistanceWeight();
//		double result=1 - Math.abs((sommatoria/pitchFunction.getJumpCount())-configFitness.getJumpDistance())/peso;
//		if(result<0)
//			result=0;
//		return result;
//	}
	
	private double jumpDistance() {
		double sommatoria=0;
		double peso=configFitness.getConfigFunction().getGeometricjumpDistanceWeight();;
		double sqmIdeal=0;
		//ArrayList<Integer> jumps=new ArrayList<Integer>();
		
		double percSalti=pitchFunction.getJumpCount()*100/pitchFunction.getPitchs().size();
		double intervIdeale=100/(percSalti+1);
		
		for(PropertyGeometryPitches proprietyPitch: pitchFunction.getPropertyPitches()){
			if(proprietyPitch.isJump()){
				sommatoria+=Math.pow(proprietyPitch.getJumpDistance()-intervIdeale, 2);
			}
		}
		
		
		double sqm=Math.sqrt(sommatoria);
		double result=1-Math.pow(Math.abs((sqmIdeal-sqm)/100),peso);
		//System.out.println(result);
		if(result<0)
			result=0;
		return result;
	}
	
// - CONTEGGIO SALTI
//	private double jumpCount() {
//		int countJump12=0;
//		int countJumpMore7=0;
//		//int countAllJump=0;
//		double result=0;
//		double malus=1;
//		ProprietyPitch lastJump=null;
//
//	//CONTA I SALTI 
//		ArrayList<Double[]> list=new ArrayList<Double[]>(); //[0] valore del salto [1] ripetuto n volte
//		for(ProprietyPitch proprietyPitch: PitchFunction.getInstsance().getProprietyPitches()){
//			if(proprietyPitch.isJump()){
//				lastJump=proprietyPitch;
//				//countAllJump++;
//				boolean find=false;
//				for(int i=0; i<list.size();i++){
//					if(list.get(i)[0]==proprietyPitch.getLenghtJump()){
//						find=true;
//						list.get(i)[1]++;
//						if(list.get(i)[1]==12)
//							countJump12++;
//						if(list.get(i)[1]>=7)
//							countJumpMore7++;
//					}
//				}
//				if(!find){
//					list.add(new Double[]{proprietyPitch.getLenghtJump(),1.0});
//				}
//			}
//		}
//
//
//		
//	//CONTROLLO CHE IL SALTO FINALE SIA DIVERSO DA 6 10 11	
//		//lastJump=PitchFunction.getInstsance().getJumps().get(PitchFunction.getInstsance().getJumpCount());
//		if(lastJump.getLenghtJump()==6 || lastJump.getLenghtJump()==10 || lastJump.getLenghtJump()==11)
//			malus=configFitness.getConfigFunction().getGeometricjumpCountMalus();
//		
//	//AMMETTO UN SOLO SALTO DI 12 IN 20 NOTE (1/20=0.05)
//		double peso=configFitness.getConfigFunction().getGeometricjumpCountWeight1();
//		double referene1=configFitness.getConfigFunction().getGeometricjumpCountReference1();
//		double valore=(countJump12/chromosome.size())-referene1;
//		if(valore>0){
//			result+=1-Math.pow(valore,peso);
//		}
//		else
//			result+=1;
//		
//	//AMMETTO POCHI SALTI MAGGIORI DI 7 NOTE (2/20 note =0.1) 
//		peso=configFitness.getConfigFunction().getGeometricjumpCountWeight2();
//		double referene2=configFitness.getConfigFunction().getGeometricjumpCountReference2();
//		valore=(countJumpMore7/chromosome.size())-referene2;
//		if(valore>0){
//			result+=1-Math.pow(valore, peso);
//		}
//		else
//			result+=1;
//		
////TODO DA FIXARE GUARDA GRAFICO / DOCUMENTAZIONE
//		
//		
//	//CONTROLLO SALTI 	E FACCIO UNA SPECIE DI MEDIA DA RAPPORTARE AD UN VALORE DI RIFERIMENTO
////		double resultLocale=0;
////		if(configFitness.getMaxJumpFunction()==GeneticConstants.ESTIMATORE_JUMP_MEDIA)
////			 resultLocale=1- (estimJumpMedia(list)/(configFitness.getMaxJump()*countAllJump));
////		else if(configFitness.getMaxJumpFunction()==GeneticConstants.ESTIMATORE_JUMP_VETT)
////			resultLocale=estimJumpVett(list);
//////BUG?? FIX?? result---> resultLocale \/    MA è GIUSTO??????
////		//resultLocale=1.0-((resultLocale)/configFitness.getMaxJump());
////		if(resultLocale<0)
////			resultLocale=0.0;
////		result+=resultLocale;
//		
////NUOVA VERSIONE
//		double numeratore=0;
//		 peso=configFitness.getConfigFunction().getGeometricjumpCountWeight3();
//		for(int i=0; i<list.size();i++){
//			numeratore+=list.get(i)[0]*list.get(i)[1];
//		}
//		numeratore=Math.abs((numeratore/PitchFunction.getInstsance().getJumpCount())-configFitness.getMaxJump());
//		result+=numeratore/peso;
//		
////FINE FIX		
//		
//		//NORMALIZZO
//		result=result/3.0;
//
//		//System.out.println("num salti: "+PitchFunction.getInstsance().getJumps().size());
//		return malus*result;
//	}
	
	private double jumpCount() {
		int countJump12=0;
		int countJumpMore7=0;
		//int countAllJump=0;
		double result=0;
		double malus=1;
		PropertyGeometryPitches lastJump=null;

	//CONTA I SALTI 
		ArrayList<Double[]> list=new ArrayList<Double[]>(); //[0] valore del salto [1] ripetuto n volte
		for(PropertyGeometryPitches proprietyPitch: pitchFunction.getPropertyPitches()){
			if(proprietyPitch.isJump()){
				lastJump=proprietyPitch;
				//countAllJump++;
				boolean find=false;
				for(int i=0; i<list.size();i++){
					if(list.get(i)[0]==proprietyPitch.getLenghtJump()){
						find=true;
						list.get(i)[1]++;
						if(list.get(i)[0]==12) //C'era un [1] da verificare
							countJump12++;
						if(list.get(i)[0]>=7) //C'era un [1] da verificare
							countJumpMore7++;
					}
				}
				if(!find){
					list.add(new Double[]{proprietyPitch.getLenghtJump(),1.0});
				}
			}
		}


		
	//CONTROLLO CHE IL SALTO FINALE SIA DIVERSO DA 6 10 11	
		//lastJump=PitchFunction.getInstsance().getJumps().get(PitchFunction.getInstsance().getJumpCount());
		if(lastJump!=null){
			if(lastJump.getLenghtJump()==6 || lastJump.getLenghtJump()==10 || lastJump.getLenghtJump()==11)
				malus=configFitness.getConfigFunction().getGeometricjumpCountMalus();
		}
	//AMMETTO UN SOLO SALTO DI 12 IN 20 NOTE (1/20=0.05)
		double peso=configFitness.getConfigFunction().getGeometricjumpCountWeight1();
		double reference1=configFitness.getConfigFunction().getGeometricjumpCountReference1();
		double valore=(countJump12/pitchFunction.getPitchs().size())-reference1;
		if(valore>0){
			result+=1-Math.pow(valore,peso);
		}
		else
			result+=1;
		
	//AMMETTO POCHI SALTI MAGGIORI DI 7 NOTE (2/20 note =0.1) 
		peso=configFitness.getConfigFunction().getGeometricjumpCountWeight2();
		double referene2=configFitness.getConfigFunction().getGeometricjumpCountReference2();
		valore=(countJumpMore7/pitchFunction.getPitchs().size())-referene2;
		if(valore>0){
			result+=1-Math.pow(valore, peso);
		}
		else
			result+=1;
		
	//CALCOLO IL SALTO MEDIO E LO SOTTRAGGO AL SALTO MEDIO IDEALE
		double numeratore=0;
		 peso=configFitness.getConfigFunction().getGeometricjumpCountWeight3();
		for(int i=0; i<list.size();i++){
			numeratore+=list.get(i)[0]*list.get(i)[1];
		}
		numeratore=Math.abs((numeratore/pitchFunction.getJumpCount())-configFitness.getMaxJump());
		result+=numeratore/peso;
		
		//NORMALIZZO
		result=result/3.0;
		return malus*result;
	}
		
//	private double estimJumpVett(ArrayList<Double[]> list){ //estimatore vettoriale
//
//		double result=0.0;
//		for(int i=0; i<list.size();i++){
//			//double res=Math.pow((list.get(i)[0]*list.get(i)[1]),2.0);
//			//result =result+res;
//			result+=Math.pow((list.get(i)[0]*list.get(i)[1]),2.0);
//		}
//		result=Math.sqrt(result);//pow(result,(double)(1/2));
//			return result;
//		}
//	
//	private double estimJumpMedia(ArrayList<Double[]> list){ //estimatore media pesata
//		double result=0.0;
//		for(int i=0; i<list.size();i++){ // utilizzo la media pesata
////			double res=list.get(i)[0]*list.get(i)[1];
////			result=result+res;
//			result+=list.get(i)[0]*list.get(i)[1];
//		}
//		result=result/(double)PitchFunction.getInstsance().getJumps().size();
//		return result;
//	}

// - CONTEGGIO PICCHI
//	private double peakCount(){
////TODO CONTROLLARE I PICCHI
//		int count=0;
//		for(ProprietyPitch proprietyPitch: PitchFunction.getInstsance().getProprietyPitches()){
//			if(proprietyPitch.isPeak())
//				count++;
//		}
//		//maxpicchi=num note*(2 o 3)
//		double ideaPeak=((double)configFitness.getPeakNumber())*(((double)this.chromosome.size())/20.0);
//
//		FitnessValue.printGeometryPeakNumber(count);
//		
//		//max:count=1:x  tutto 1-
//		double result;
//		//result=(((double)count)/maxPeak)%1.0;  //HO TOLTO 1-result
//		double peso=configFitness.getConfigFunction().getGeometricPeakCountWeight();
//		result=1.0-Math.abs((double)count-ideaPeak)/peso;
//		//System.out.println("result: "+result);
//		if(result<0)
//			result=0;
//		return result;
//	}
	
	private double peakCount(){
				int count=0;
				for(PropertyGeometryPitches proprietyPitch: pitchFunction.getPropertyPitches()){
					if(proprietyPitch.isPeak())
						count++;
				}
				//maxpicchi=num note*(2 o 3)
				double ideaPeak=((double)configFitness.getPeakNumber())*(((double)pitchFunction.getPitchs().size())/20.0);

				FitnessValue.printGeometryPeakNumber(count);
				
				//max:count=1:x  tutto 1-
				double result;
				//result=(((double)count)/maxPeak)%1.0;  //HO TOLTO 1-result
				double peso=configFitness.getConfigFunction().getGeometricPeakCountWeight()*((double)pitchFunction.getPitchs().size())/20.0;
				result=1.0-Math.abs((double)count-ideaPeak)/peso;
				//System.out.println("result: "+result);
				if(result<0)
					result=0;
				return result;
			}
	
	


}