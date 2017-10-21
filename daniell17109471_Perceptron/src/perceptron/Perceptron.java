package perceptron;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import java.util.Arrays;
public class Perceptron {
	ArrayList<Integer> values = new ArrayList<Integer>();
	ArrayList<Double> weights = new ArrayList<Double>();
	int method;
	int iterations = 0;
	Perceptron()
	{
		String[] methods = {"AND", "OR", "NOT","Learn And","Learn Or","Learn Not", "exit"};

		method = JOptionPane.showOptionDialog(null, "Please enter the method",
			"Method", JOptionPane.YES_NO_OPTION,
			JOptionPane.INFORMATION_MESSAGE, null,methods , "False" );
	
			String[] ia = {"False","True"};
		if(method==6)
		{
			System.exit(0);
		}
		values = new ArrayList<Integer>();
		weights = new ArrayList<Double>();
		values.add(JOptionPane.showOptionDialog(null, "Please enter the value for Input 1", "Input 1",
				JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, 
				null, ia, "False" ));
		if(method !=2 &&method!=5){
			values.add(JOptionPane.showOptionDialog(null, "Please enter the value for Input 2", "Input 2",
					JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, 
					null, ia, "False" ));
		}
	}
	void run()
	{
		if(method==0)
		{
			and();
		}else if(method==1)
		{
			or();
		}else if(method==2){
			not();
		}else if(method==3){
			learnAnd();
		}else if (method==4){
			learnOr();
		}else if(method==5){
			learnNot();
		}else if(method==6){
			System.exit(0);
		}else{
			JOptionPane.showMessageDialog(null, "Error: No method selected");
			System.exit(0);
		}
		if(method!=2&&method!=5){
			runMethod();
		}
	}
	void and()
	{
		weights.add(1.0);
		weights.add(1.0);
		weights.add(1.5);
	}
	void or(){
		weights.add(1.0);
		weights.add(1.0);
		weights.add(0.5);
		
	}
	void not(){
		weights.add(-1.0);
		weights.add(-0.5);
		double value = weights.get(0)*values.get(0)+-1*weights.get(1);
		if(value>0)
		{
			JOptionPane.showMessageDialog(null, "True");
		}
		else{
			JOptionPane.showMessageDialog(null, "False");
		}
		String s = "Weights: ";
		for(int k = 0; k<weights.size(); k++){
			s+= weights.get(k);
			s+=" ";
		}
		s+= "\n" + "Iterations: "+iterations;
		s+="\n"+ (weights.get(0)*values.get(0));
		if(value>0){
			s+=" > ";
		}else{
			s+=" !> ";
		}
		s+= weights.get(1);
		JOptionPane.showMessageDialog(null, s);
	}
	void learnAnd(){
		int[][] target = {{0,0,0},{0,1,0},{1,0,0},{1,1,1}};
		//[x1, x2, t]
		double learningrate = 0.2;
		double[] weightsa = {0.0,0.0,0.0};
		double threshinput = -1.0;
		while(test(target, weightsa)!=true){
			for(int k = 0; k<target.length; k++){
				while(1==1){
					int output;
					if(weightsa[2]*threshinput + weightsa[0]*target[k][0]+ weightsa[1]*target[k][1]>0){
						output = 1;
					}
					else{
						output = 0;
					}
					if(output == target[k][2]){
						break;
					}
					for(int i = 0; i<2; i++){
						weightsa[i] = weightsa[i] + learningrate*(target[k][2]-output)*target[k][i];
					}
					
					double temp =  weightsa[2] + learningrate*(target[k][2]-output)*threshinput;
					weightsa[2] = temp;
					
				}
			}
			iterations++;
		}
		weights = new ArrayList<Double>();
		weights.add(weightsa[0]);
		weights.add(weightsa[1]);
		weights.add(weightsa[2]);
		
	}
	void learnOr(){
		int[][] target = {{0,0,0},{0,1,1},{1,0,1},{1,1,1}};
		//[x1, x2, t]
		double learningrate = 0.2;
		double[] weightsa = {0.0,0.0,0.0};
		double threshinput = -1.0;
		while(test(target, weightsa)!=true){
			for(int k = 0; k<target.length; k++){
				while(1==1){
					int output;
					if(weightsa[2]*threshinput + weightsa[0]*target[k][0]+ weightsa[1]*target[k][1]>0){
						output = 1;
					}
					else{
						output = 0;
					}
					if(output == target[k][2]){
						break;
					}
					for(int i = 0; i<2; i++){
						weightsa[i] = weightsa[i] + learningrate*(target[k][2]-output)*target[k][i];
					}
					
					double temp =  weightsa[2] + learningrate*(target[k][2]-output)*threshinput;
					weightsa[2] = temp;
					
				}
			}
			iterations++;
		}
		weights = new ArrayList<Double>();
		weights.add(weightsa[0]);
		weights.add(weightsa[1]);
		weights.add(weightsa[2]);
		
	}
	void learnNot(){
		int[][] target = {{0,1},{1,0}};
		//[x1, x2, t]
		double learningrate = 0.2;
		double[] weightsa = {0.0,0.0};
		double threshinput = -1.0;
		while(testNot(target, weightsa)!=true){
			for(int k = 0; k<target.length; k++){
				while(1==1){
					int output;
					if(weightsa[1]*threshinput + weightsa[0]*target[k][0]>0){
						output = 1;
					}
					else{
						output = 0;
					}
					if(output == target[k][1]){
						break;
					}
					for(int i = 0; i<1; i++){
						weightsa[i] = weightsa[i] + learningrate*(target[k][1]-output)*target[k][i];
					}
					
					double temp =  weightsa[1] + learningrate*(target[k][1]-output)*threshinput;
					weightsa[1] = temp;
					
				}
			}
			iterations++;
		}
		weights = new ArrayList<Double>();
		weights.add(weightsa[0]);
		weights.add(weightsa[1]);
		double temp = weights.get(1)*-1+weights.get(0)*values.get(0);
		if(temp>0){
			JOptionPane.showMessageDialog(null, "True");
		} else{
			JOptionPane.showMessageDialog(null, "False");
		}
		String s = "Weights: ";
		for(int k = 0; k<weights.size(); k++){
			s+= weights.get(k);
			s+=" ";
		}
		s+= "\n" + "Iterations: "+iterations;
		s+="\n"+ (weights.get(0)*values.get(0));
		if(temp>0){
			s+=" > ";
		}else{
			s+=" !> ";
		}
		s+= weights.get(1);
		JOptionPane.showMessageDialog(null, s);
		
	}
	void runMethod(){
		double temp = weights.get(2)*-1+weights.get(0)*values.get(0)+weights.get(1)*values.get(1);
		if(temp>0){
			JOptionPane.showMessageDialog(null, "True");
		} else{
			JOptionPane.showMessageDialog(null, "False");
		}
		String s = "Weights: ";
		for(int k = 0; k<weights.size(); k++){
			s+= weights.get(k);
			s+=" ";
		}
		s+= "\n" + "Iterations: "+iterations;
		s+="\n"+ (weights.get(0)*values.get(0)+weights.get(1)*values.get(1));
		if(temp>0){
			s+=" > ";
		}else{
			s+=" !> ";
		}
		s+= weights.get(2);
		JOptionPane.showMessageDialog(null, s);
	}
	static boolean test(int[][] t, double[] w)
	{
		for(int k = 0; k<t.length; k++){
			int temp = 0;
			if(w[2]*-1 + w[0]*t[k][0]+ w[1]*t[k][1]>0){
				temp = 1;
			}
			if(temp!= t[k][2]){
				return false;
			}
		}
		return true;
	}
	static boolean testNot(int[][] t, double[] w)
	{
		for(int k = 0; k<t.length; k++){
			int temp = 0;
			if(w[1]*-1 + w[0]*t[k][0]>0){
				temp = 1;
			}
			if(temp!= t[k][1]){
				return false;
			}
		}
		return true;
	}
}
