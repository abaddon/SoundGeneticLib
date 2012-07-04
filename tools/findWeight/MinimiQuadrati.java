package tools.findWeight;

import java.util.ArrayList;
import java.util.HashMap;

import Jama.Matrix;

public class MinimiQuadrati {
	
	private ArrayList<HashMap<String, Double>> scoreList;

	//parametri dei valori
	private Matrix A=null;
	//parametri =1
	private Matrix y=null;
	//parametri da stimare
	//private Matrix x=null;
	HashMap<String,Double> x=new HashMap<String,Double>();
	HashMap<String,String> keyConverter=null;
	ArrayList<String> keys=null;
	//m>n
	//m numero equazioni
	private int m=3;
	//n numero incognite
	private int n=2;
	/*
	 * a1+b2=1
	 * a2+b3=1
	 * a1+b2=1
	 */
	public MinimiQuadrati(int m, int n, ArrayList<HashMap<String, Double>> scoreList) {
		this.m=m;
		this.n=n;
		this.scoreList=scoreList;
		istanceKeyConverter();
		istanceMatrixA();
		istanceMatrixY();
		istanceMatrixX();
		
		
		
//		 for(int i=0; i<x.getRowDimension(); i++)
//			 System.out.println(x.get(i, 0));
//		 System.out.println("colonne: "+x.getColumnDimension());
	}
	
	public String printMatrix(Matrix matrix){
		String output="";
		for(int row=0; row<A.getRowDimension();row++){
			for(int col=0;col<A.getColumnDimension(); col++){
				
				String valore=String.valueOf(A.get(row, col));
				//tabulazione //10spazi
				for(int i=valore.length(); i<10; i++)
					valore+=" ";
				output+=valore;
			}
			output+="\n";
		}
		output+="\n";
		return output;
	}
	
	public String printAll(){
		String output="";
		output+="A\n";
		output+=printMatrix(A);
		output+="\n";
		output+="Y\n";
		output+=printMatrix(y);
		
		System.out.println("A");
		A.print(m, n);
		System.out.println("y");
		y.print(m, 1);
		
		return output;
	}
	 
	
	public HashMap<String,Double> getWeight(){
		
		//initArrayList(result,n);
		Matrix Aplus=A.inverse();
		
		System.out.println("APlus");
		Aplus.print(n, m);
		
		//istanzio X
		//x=new Matrix(n,1);
		//String[] keys=(String[]) scoreList.get(1).keySet().toArray();
		for (int i = 0; i < n; i++){
			  for (int j = 0; j < 1; j++){
				  for (int k = 0; k < m; k++){
					 // x.set(i, j, x.get(i,j)+(Aplus.get(i, k)*y.get(k, j)));
					  
					  x.put(keys.get(i), x.get(keys.get(i))+(Aplus.get(i, k)*y.get(k, j)));
					 // result.set(i, (result.get(i)+(Aplus.get(i, k)*y.get(k, j))));
			    }
			  }
		}
		//x.print(n, 1);
		
		return x;
		
	}


//	private void initArrayList(ArrayList<Double> result, int n) {
//		for(int i=0; i<n; i++)
//			result.add(0.0);
//		
//	}

	private void istanceMatrixY() {
		y=new Matrix(m,1);
		for(int m1=0; m1<m; m1++){
			y.set(m1, 0, 1);
		}	
	}



	private void istanceMatrixA() {
		A=new Matrix(m,n);
		
		for(int m1=0; m1<m; m1++){
			HashMap<String, Double> row=scoreList.get(m1);
			int n1=0;
			for(String key: row.keySet()){
				A.set(m1, n1, row.get(key));
				//x.put(keys.get(key), 0.0);
				n1++;
			}	
		}
	}
	
	private void istanceMatrixX(){
		x=new HashMap<String,Double>();
		keys=new ArrayList<String>();
		HashMap<String, Double> row=scoreList.get(1);
		for(String key: row.keySet()){
			x.put(convertKey(key), 0.0);
			keys.add(convertKey(key));
		}	
	}
	
	private void istanceKeyConverter(){
		keyConverter=new HashMap<String,String>();
		keyConverter.put("tonalityScalePitch","tonalityScalePitchWeight");
		keyConverter.put("tonalityExternalScalePitch","tonalityExternalScalePitchWeight");
		keyConverter.put("tonalityPitch","tonalityPitchWeight");

		keyConverter.put("geometryJumpRecover","geometricJumpRecoverWeight");
		keyConverter.put("geometryRibattuta","geometricRibattutaWeight");
		keyConverter.put("geometryPeakDistribution","peakDistributionWeight");
		keyConverter.put("geometryScoreJump","jumpWeight");
		keyConverter.put("geometryPeakCount","peakCountWeight");
		keyConverter.put("geometryJumpDistance","jumpDistanceWeight");
		keyConverter.put("geometryJumpDirection","jumpDirectionWeight");
		
		keyConverter.put("frequencyPitchDistribution","frequencyPitchDistributionWeight");
	}
	
	private String convertKey(String key){
		return keyConverter.get(key);
	}

}
