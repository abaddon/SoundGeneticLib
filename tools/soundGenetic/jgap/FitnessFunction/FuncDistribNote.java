package soundGenetic.jgap.FitnessFunction;

import java.util.ArrayList;

import soundGenetic.FitnessValue;
import soundGenetic.jgap.ConfigFitness;
import soundGenetic.jgap.ConfigJGap;
import soundGenetic.jgap.GeneticConstants;
import soundGenetic.jgap.FitnessFunction.support.Scheme;
import soundGenetic.jgap.FitnessFunction.support.TonalitySchemes;
import tools.Debug;

public class FuncDistribNote extends GenericFitness {

	/**
	 * devo contare le note ordinarle (do re mi fa...) selezionare le 7 più alte
	 * contare i salti di nota ottenere uno schema tipo 2212221 confrontare lo
	 * schema con uno conosciuto identificare la nota di appartenza... (boh
	 * passato proppo tempo non mi ricordo...)
	 */
	private static final long serialVersionUID = 6968456279199232512L;

	// TONALITA'
	// private final int MAGGIORE=1;
	// private final int MINORE_NATURALE=2;
	// private final int MINORE_ARMONICO=3;
	// private final int SEMIDIMINUITA=4;
	// private final int ESATONALE=5;
	// NOTE
	// private final int DO=0;
	// private final int DO_DIESIS=1;
	// private final int RE=2;
	// private final int RE_DIESIS=3;
	// private final int MI=4;
	// private final int FA=5;
	// private final int FA_DIESIS=6;
	// private final int SOL=7;
	// private final int SOL_DIESIS=8;
	// private final int LA=9;
	// private final int LA_DIESIS=10;
	// private final int SI=11;

	// private int pitchMin = 0;
	// private int pitchMax = 0;

	public FuncDistribNote(ConfigFitness configFitness, ConfigJGap configJGap) {
		super(configFitness, configJGap);

	}

	protected double evaluate() {

		double resultTonalityPitch = tonalityPitch();
		double resultScalePitch = scalePitch();
		double resultExternalScalePitch = externalScalePitch();

		// FitnessValue
		FitnessValue.printTonalityPitch(resultTonalityPitch);
		FitnessValue.printTonalityScalePitch(resultScalePitch);
		FitnessValue.printTonalityExternalScalePitch(resultExternalScalePitch);

		double result = configFitness.getTonalityPitchWeight()
				* resultTonalityPitch
				+ configFitness.getTonalityScalePitchWeight()
				* resultScalePitch
				+ configFitness.getTonalityExternalScalePitchWeight()
				* resultExternalScalePitch;

		result = result
				/ (configFitness.getTonalityPitchWeight()
						+ configFitness.getTonalityScalePitchWeight() + configFitness
						.getTonalityExternalScalePitchWeight());
		FitnessValue.printTonalitaScore(result);

		return result;
	}

	// CONTROLLO PRESE NOTE ESTERNE
	// private double externalScalePitch(){
	// double malus=1.0;
	// //estraggo il template della tonalità
	// boolean[] template=null;
	// for (Scheme schema :
	// TonalitySchemes.getInstance().get(configFitness.getTonalitaScala())) {
	// if (schema.getMax() == 7)
	// template = schema.getScheme(configFitness.getTonalitaNota());
	// }
	// //scorro le note
	// for (int i = 1; i < chromosome.size()-1; i++){
	// int pitch=((Integer) ((CompositeGene)
	// chromosome.getGene(i)).geneAt(2).getAllele()) % 12;
	// //seleziono le note fuori scala
	// if(!template[pitch]){
	// int pitch0=((Integer) ((CompositeGene)
	// chromosome.getGene(i-1)).geneAt(2).getAllele()) % 12;
	// int pitch2=((Integer) ((CompositeGene)
	// chromosome.getGene(i+1)).geneAt(2).getAllele()) % 12;
	// int dif0=Math.abs(pitch0-pitch);
	// int dif2=Math.abs(pitch2-pitch);
	// if(dif0>2 && dif2>2){
	// malus=malus*configFitness.getConfigFunction().getNoteExternalScalePitchMalus1();
	// }
	// else if((dif0>2 && dif2<=2) || (dif0<=2 && dif2>2)){
	// malus=malus*configFitness.getConfigFunction().getNoteExternalScalePitchMalus2();
	// }
	// }
	// }
	//
	// return 1.0*malus;
	// }

	private double externalScalePitch() {
		double malus = 1.0;
		// estraggo il template della tonalità
		// TODO DA VERIFICARE
		// boolean[] template=null;
		// for (Scheme schema :
		// TonalitySchemes.getInstance().get(configFitness.getTonalitaScala()))
		// {
		// if (schema.getMax() == 7)
		// template = schema.getScheme(configFitness.getTonalitaNota());
		// }
		// scorro le note
		for (int i = 1; i < pitchFunction.getPitchs().size() - 1; i++) {
			int pitch = pitchFunction.getPitchs().get(i) % 12;
			// seleziono le note fuori scala
			// if(!template[pitch]){
			if (!pitchFunction.getPitchesOrderByPitches().get(pitch).isScale()) {
				int pitch0 = pitchFunction.getPitchs().get(i - 1) % 12;
				int pitch2 = pitchFunction.getPitchs().get(i + 1) % 12;
				int dif0 = Math.abs(pitch0 - pitch);
				int dif2 = Math.abs(pitch2 - pitch);
				if (dif0 > 2 && dif2 > 2) {
					malus = malus* configFitness.getConfigFunction().getNoteExternalScalePitchMalus1();
				} else if ((dif0 > 2 && dif2 <= 2) || (dif0 <= 2 && dif2 > 2)) {
					malus = malus* configFitness.getConfigFunction().getNoteExternalScalePitchMalus2();
				}
			}
		}

		return 1.0 * malus;
	}

	// PENALIZZO LE NOTE FUORI SCALA
	// private double scalePitch() {
	//		
	// /* CONTROLLO LA PRIMA NOTA, DEVE ESSERE IN SCALA (0 2 4 5 7 9 11) -->
	// 2212221
	// * 0 2 4 5 7 9 11
	// * true,false,true,false,true,true,false,true,false,true,false,true
	// //Maggiore DO
	// * 0 1 3 5 6 8 10
	// * true,true,false,true,false,true,true,false,true,false,true,false
	// //Maggiore DO#
	// * 0 2 3 5 6 7 8
	// * true,false,true,true,false,true,false,true,true,false,true,false
	// //MINORE DO
	// *
	// * DA PENALIZZARE MOLTO!! */
	//		
	// //ottengo gli schemi della tonalità
	// ArrayList<Scheme> schemes =
	// TonalitySchemes.getInstance().get(configFitness.getTonalitaScala());
	// //ottengo lo schema della tonalità della nota
	// boolean[] template=null;
	// for (Scheme schema : schemes) {
	// if (schema.getMax() == 7)
	// template = schema.getScheme(configFitness.getTonalitaNota());
	// }
	//		
	// double malus = 1;
	// //OK SISTEMATO FUNZIONA PER TUTTI I CASI DI TONALITA'
	// //NOTA INIZIALE
	// int firstPitch = ((Integer) ((CompositeGene)
	// chromosome.getGene(0)).geneAt(2).getAllele()) % 12;
	// //if (firstPitch != 0 && firstPitch != 2 && firstPitch != 4 && firstPitch
	// != 5 && firstPitch != 7 & firstPitch != 9 && firstPitch != 11)
	// //se è fuori scala
	// if( !template[firstPitch])
	// malus = configFitness.getConfigFunction().getNoteScalePitchMalus1();
	//		
	// //NOTA FINALE
	// int lastPitch = ((Integer) ((CompositeGene)
	// chromosome.getGene(chromosome.getGenes().length -
	// 1)).geneAt(2).getAllele()) % 12;
	// /*if (lastPitch != 0 && lastPitch != 2 && lastPitch != 4 && lastPitch !=
	// 5 && lastPitch != 7 & lastPitch != 9 && lastPitch != 11){
	// *
	// * }else if (lastPitch == 2 || lastPitch == 5 || lastPitch == 9 ||
	// lastPitch == 11) {
	// *
	// * } else if (lastPitch == 4 || lastPitch == 7) {
	// *
	// * }
	// */
	// //note fuori scala
	// if(!template[lastPitch]){
	// malus = malus *
	// configFitness.getConfigFunction().getNoteScalePitchMalus1();
	//	
	// }//MAGGIORE
	// else if(configFitness.getTonalitaScala()==GeneticConstants.MAGGIORE){
	// //note in scala MAGGIORE ma diverse da (0,4,7)
	// if (template[lastPitch] &&
	// configFitness.getTonalitaNota()!=lastPitch &&
	// (configFitness.getTonalitaNota()+4)%12!=lastPitch &&
	// (configFitness.getTonalitaNota()+7)%12!=lastPitch){
	// malus = malus *
	// configFitness.getConfigFunction().getNoteScalePitchMalus2();
	// //note in scala MAGGIORE ma uguali a (4,7)
	// }
	// else if (template[lastPitch] &&
	// ((configFitness.getTonalitaNota()+4)%12==lastPitch ||
	// (configFitness.getTonalitaNota()+7)%12==lastPitch)) {
	// malus = malus *
	// configFitness.getConfigFunction().getNoteScalePitchMalus3();;
	// }
	//	
	// }//SEMIDIMINUITA
	// else
	// if(configFitness.getTonalitaScala()==GeneticConstants.SEMIDIMINUITA){
	// //tutto ok no malus basta che siano in scala
	// }//ESATONALE
	// else if(configFitness.getTonalitaScala()==GeneticConstants.ESATONALE){
	// //tutto ok no malus basta che siano in scala
	// }//MINORE
	// else{//note in scala MINORE ma diverse da (0,3,7)
	// if (template[lastPitch] &&
	// configFitness.getTonalitaNota()!=lastPitch &&
	// (configFitness.getTonalitaNota()+3)%12!=lastPitch &&
	// (configFitness.getTonalitaNota()+7)%12!=lastPitch){
	// malus = malus *
	// configFitness.getConfigFunction().getNoteScalePitchMalus2();
	//			
	// }//note in scala MINORE ma uguali a (3,7)
	// else if (template[lastPitch] &&
	// ((configFitness.getTonalitaNota()+3)%12==lastPitch ||
	// (configFitness.getTonalitaNota()+7)%12==lastPitch)) {
	// malus = malus *
	// configFitness.getConfigFunction().getNoteScalePitchMalus3();
	// }
	// }
	//
	// // POCHE NOTE FUORI SCALA (5 TROPPE IN 20 NOTE)
	// //OK SISTEMATO!! VA BENE PER TUTTE LE TONALITA E PER TUTTE LE NOTE DI
	// TONALITA'
	// double somma=0;
	// // double somma =
	// PitchFunction.getInstsance().getPitchesOrderByPitches().get(1).getFrequenza()
	// // +
	// PitchFunction.getInstsance().getPitchesOrderByPitches().get(3).getFrequenza()
	// // +
	// PitchFunction.getInstsance().getPitchesOrderByPitches().get(6).getFrequenza()
	// // +
	// PitchFunction.getInstsance().getPitchesOrderByPitches().get(8).getFrequenza()
	// // +
	// PitchFunction.getInstsance().getPitchesOrderByPitches().get(10).getFrequenza();
	// for(int i=0; i<template.length; i++){
	// if(!template[i])
	// somma+=PitchFunction.getInstsance().getPitchesOrderByPitches().get(i).getFrequenza();
	// }
	// double peso=configFitness.getConfigFunction().getNoteScalePitchWeight();
	// double result = 1.0 - Math.pow(Math.abs((somma / (double)
	// chromosome.getGenes().length)-
	// configFitness.getTonalityExternalPitchScale()), peso);
	// result = result * malus;
	//
	// return result;
	// }

	private double scalePitch() {

		/*
		 * CONTROLLO LA PRIMA NOTA, DEVE ESSERE IN SCALA (0 2 4 5 7 9 11) -->
		 * 2212221 0 2 4 5 7 9 11
		 * true,false,true,false,true,true,false,true,false,true,false,true
		 * //Maggiore DO 0 1 3 5 6 8 10
		 * true,true,false,true,false,true,true,false,true,false,true,false
		 * //Maggiore DO# 0 2 3 5 6 7 8
		 * true,false,true,true,false,true,false,true,true,false,true,false
		 * //MINORE DO
		 * 
		 * DA PENALIZZARE MOLTO!!
		 */

		// ottengo gli schemi della tonalità
		ArrayList<Scheme> schemes = TonalitySchemes.getInstance().get(
				configFitness.getTonalitaScala());
		// ottengo lo schema della tonalità della nota
		boolean[] template = null;
		for (Scheme schema : schemes) {
			if (schema.getMax() == 7)
				template = schema.getScheme(configFitness.getTonalitaNota());
		}

		double malus = 1;
		// OK SISTEMATO FUNZIONA PER TUTTI I CASI DI TONALITA'
		// NOTA INIZIALE
		int firstPitch = pitchFunction.getPitchs().get(0) % 12;
		// if (firstPitch != 0 && firstPitch != 2 && firstPitch != 4 &&
		// firstPitch != 5 && firstPitch != 7 & firstPitch != 9 && firstPitch !=
		// 11)
		// se è fuori scala
		if (!template[firstPitch])
			malus = configFitness.getConfigFunction().getNoteScalePitchMalus1();

		// NOTA FINALE
		int lastPitch = pitchFunction.getPitchs().get(
				pitchFunction.getPitchs().size() - 1) % 12;
		/*
		 * if (lastPitch != 0 && lastPitch != 2 && lastPitch != 4 && lastPitch !=
		 * 5 && lastPitch != 7 & lastPitch != 9 && lastPitch != 11){
		 * 
		 * }else if (lastPitch == 2 || lastPitch == 5 || lastPitch == 9 ||
		 * lastPitch == 11) {
		 *  } else if (lastPitch == 4 || lastPitch == 7) {
		 *  }
		 */
		// note fuori scala
		if (!template[lastPitch]) {
			malus = malus
					* configFitness.getConfigFunction()
							.getNoteScalePitchMalus1();

		}// MAGGIORE
		else if (configFitness.getTonalitaScala() == GeneticConstants.MAGGIORE) {
			// note in scala MAGGIORE ma diverse da (0,4,7)
			if (template[lastPitch]
					&& configFitness.getTonalitaNota() != lastPitch
					&& (configFitness.getTonalitaNota() + 4) % 12 != lastPitch
					&& (configFitness.getTonalitaNota() + 7) % 12 != lastPitch) {
				malus = malus
						* configFitness.getConfigFunction()
								.getNoteScalePitchMalus2();
				// note in scala MAGGIORE ma uguali a (4,7)
			} else if (template[lastPitch]
					&& ((configFitness.getTonalitaNota() + 4) % 12 == lastPitch || (configFitness
							.getTonalitaNota() + 7) % 12 == lastPitch)) {
				malus = malus
						* configFitness.getConfigFunction()
								.getNoteScalePitchMalus3();
				;
			}

		}// SEMIDIMINUITA
		else if (configFitness.getTonalitaScala() == GeneticConstants.SEMIDIMINUITA) {
			// tutto ok no malus basta che siano in scala
		}// ESATONALE
		else if (configFitness.getTonalitaScala() == GeneticConstants.ESATONALE) {
			// tutto ok no malus basta che siano in scala
		}// MINORE
		else {// note in scala MINORE ma diverse da (0,3,7)
			if (template[lastPitch]
					&& configFitness.getTonalitaNota() != lastPitch
					&& (configFitness.getTonalitaNota() + 3) % 12 != lastPitch
					&& (configFitness.getTonalitaNota() + 7) % 12 != lastPitch) {
				malus = malus
						* configFitness.getConfigFunction()
								.getNoteScalePitchMalus2();

			}// note in scala MINORE ma uguali a (3,7)
			else if (template[lastPitch]
					&& ((configFitness.getTonalitaNota() + 3) % 12 == lastPitch || (configFitness
							.getTonalitaNota() + 7) % 12 == lastPitch)) {
				malus = malus
						* configFitness.getConfigFunction()
								.getNoteScalePitchMalus3();
			}
		}

		// POCHE NOTE FUORI SCALA (5 TROPPE IN 20 NOTE)
		// OK SISTEMATO!! VA BENE PER TUTTE LE TONALITA E PER TUTTE LE NOTE DI
		// TONALITA'
		double somma = 0;
		// double somma =
		// PitchFunction.getInstsance().getPitchesOrderByPitches().get(1).getFrequenza()
		// +
		// PitchFunction.getInstsance().getPitchesOrderByPitches().get(3).getFrequenza()
		// +
		// PitchFunction.getInstsance().getPitchesOrderByPitches().get(6).getFrequenza()
		// +
		// PitchFunction.getInstsance().getPitchesOrderByPitches().get(8).getFrequenza()
		// +
		// PitchFunction.getInstsance().getPitchesOrderByPitches().get(10).getFrequenza();
		for (int i = 0; i < template.length; i++) {
			if (!template[i])
				somma += pitchFunction.getPitchesOrderByPitches().get(i)
						.getFrequenza();
		}
		double peso = configFitness.getConfigFunction()
				.getNoteScalePitchWeight();
		double result = 1.0 - Math.pow(Math.abs((somma / (double) pitchFunction
				.getPitchs().size())
				- configFitness.getTonalityExternalPitchScale()), peso);
		result = result * malus;

		return result;
	}

	// TONALITA NOTE
	private double tonalityPitch() {

		// print();
		int schema = 0;
		int sizeMax = 0;
		Debug.println("----");

		// /CALCOLO LA DISTRIBUZIONE DELLE NOTE A SECONDA DEL TIPO DI TONALITA'
		if (configFitness.getTonalitaScala() == GeneticConstants.SEMIDIMINUITA) { // SEMIDIMINUITA
			sizeMax = 8;
			if (getTemplate(8)) { // TROVO I MAX nel cromosoma
				schema = 8;
			}
		} else if (configFitness.getTonalitaScala() == GeneticConstants.ESATONALE) { // ESATONALE
			sizeMax = 6;
			if (getTemplate(6)) { // TROVO I MAX nel cromosoma
				schema = 6;
			}
		} else { // MAGGIORE - MINORE NAT. - MINORE ARM.
			sizeMax = 7;
			for (int i = 0; i < 3; i++) {
				if (getTemplate(7 - i)) { // TROVO I MAX nel cromosoma
					schema = 7 - i;
					break;
				}
			}
		}
		// FINE CALCOLO

		if (schema != 0) // CALCOLO IL PUNTEGGIO DI FITNESS DELLA TONALITA'
			return checkTemplate(schema, sizeMax);
		return 0; // NESSUN MATCH
	}

	// private void print() {
	// System.out.println("------------------");
	// for (int x = 0; x < chromosome.getGenes().length; x++) {
	// System.out.print(((Integer) ((CompositeGene) chromosome.getGene(x))
	// .geneAt(2).getAllele())
	// % 12 + " | ");
	// }
	// System.out.println("");
	// for (int i = 0; i <
	// PitchFunction.getInstsance().getPitchesOrderByPitches().size(); i++) {
	// System.out.println(PitchFunction.getInstsance().getPitchesOrderByPitches().get(i)
	// .getNome()
	// + ": "
	// + PitchFunction.getInstsance().getPitchesOrderByPitches().get(i)
	// .getFrequenza());
	// }
	// }

	private boolean getTemplate(int num) {
		// Debug.println("size: "
		// + num
		// + " check"
		// + PitchFunction.getInstsance().getPitchesOrderedByFrequency().get(num
		// - 1)
		// .getFrequenza()
		// + " - "
		// +
		// PitchFunction.getInstsance().getPitchesOrderedByFrequency().get(num)
		// .getFrequenza());
		if (pitchFunction.getPitchesOrderedByFrequency().get(num - 1)
				.getFrequenza() == pitchFunction.getPitchesOrderedByFrequency()
				.get(num).getFrequenza()) {
			return false;
		} else {
			// creo max
			for (int j = 0; j < num; j++) {
				pitchFunction.getPitchesOrderedByFrequency().get(j)
						.setMax(true);
			}
			return true;
		}
	}

	private double checkTemplate(double size, double sizeMax) {
		double congruenzaMax = 0; // max 12
		ArrayList<Scheme> schemes = TonalitySchemes.getInstance().get(
				configFitness.getTonalitaScala());
		boolean[] template = null;
		for (Scheme schema : schemes) {
			if (schema.getMax() == size) {
				// ottengo lo schema esatto da confrontare con quello generato
				template = schema.getScheme(configFitness.getTonalitaNota());
				int congruenza = 0; // max 12;
				for (int i = 0; i < template.length; i++) { // contronto nota
					// per nota
					if (pitchFunction.getPitchesOrderedByFrequency().get(i)
							.isMax() == template[pitchFunction
							.getPitchesOrderedByFrequency().get(i).getNome()])
						congruenza++;
				}
				if (congruenza > congruenzaMax) {
					congruenzaMax = congruenza;
				}
			}
		}
		double peso1 = configFitness.getConfigFunction()
				.getNoteCheckTemplateWeight1();
		double peso2 = configFitness.getConfigFunction()
				.getNoteCheckTemplateWeight2();
		double result = (Math.pow((congruenzaMax / 12), peso1) + Math.pow(
				(size / sizeMax), peso2)) / 2.0;

		FitnessValue.printTonalitaSize(size, sizeMax);
		FitnessValue.printTonalitaCongruenza(congruenzaMax, 12.0);

		return result;

	}

}
