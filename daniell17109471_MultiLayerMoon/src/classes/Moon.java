package classes;

import graphicsClasses.StdDraw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JOptionPane;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.BackPropagation;
import org.neuroph.util.TransferFunctionType;

public class Moon {
	public static void main(String[] args) {
        new Moon();
    }
	String s = "";
	NeuralNetwork neural;
	int seperation = 0;
	int width = 6;
	int radius = 10;
	int length = 30;
	int height = 30;
	double points[][] = new double[2000][3];
	double testpoints[][] = new double[2000][2];
	double max;
	Moon(){
		initialise();
	}
	public void initialise(){
		s = JOptionPane.showInputDialog("Enter seperation value (Integer).");
		if(s==null){
			JOptionPane.showMessageDialog(null, "No seperation value given.");
			System.exit(0);
		}
		seperation = Integer.parseInt(s);
		StdDraw.setPenColor(StdDraw.BLACK);
	    StdDraw.setPenRadius(0.001);
		StdDraw.setXscale (-length, length);
	    StdDraw.setYscale (-height, height);
	    StdDraw.line(-length, 0, length, 0);
	    StdDraw.line(0, -height, 0, length);
	    makePoints();
	    
	    max = 0;
	    
		for(int k = 0; k<points.length; k++){
			if(points[k][0]>max){
				max = points[k][0];
			}
		}
		
	    shuffleArray(points);
	    
	    int answer = 0;
	    
	    String[] ia = {"No","Yes"};
	    
	    answer = JOptionPane.showOptionDialog(null, "Load Perceptron?", "Perceptron",
				JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, 
				null, ia, "No" );
	    
	    if(answer == 1)
	    	neural = NeuralNetwork.load("myMlPerceptron.nnet");
	    else
	    	learnRegion();
	    
	    StdDraw.setPenRadius(0.006);
	    printPoints(points);
	    makeTestPoints();
	    
	    shuffleArray(testpoints);
	    StdDraw.setPenRadius(0.009);
	    printTestPoints(testpoints);
	}
	void printPoints(double[][] d){
		for(int k = 0; k<2000; k++){
	    	if(d[k][2]>0){
	    		StdDraw.setPenColor(StdDraw.BOOK_BLUE);
	    	}
	    	StdDraw.point(d[k][0], d[k][1]);
	    	StdDraw.setPenColor(StdDraw.BLACK);
	    }
	}
	void makePoints(){
		for(int k = 0; k<1000; k++){
	    	double xc=100;
	    	double yc=100;
	    	while(!((Math.pow(xc-0, 2)+Math.pow(yc-0, 2)<Math.pow(radius+width, 2))&&(Math.pow(xc-0, 2)+Math.pow(yc-0, 2)>Math.pow(radius, 2))&&yc>0)){
	    		xc = (Math.random()*(width*radius*2)-(radius+width));
	    		yc = (Math.random()*(width*radius*2)-(radius+width));
	    	}
	    	double temp[] = {xc,yc,1};
	    	points[k] = temp;
	    }
	    for(int k = 1000; k<2000; k++){
	    	double xc=100;
	    	double yc=100;
	    	while(!((Math.pow(xc-(width/2+radius), 2)+Math.pow(yc-(-seperation), 2)<Math.pow(radius+width, 2))&&(Math.pow(xc-(width/2+radius), 2)+Math.pow(yc-(-seperation), 2)>Math.pow(radius, 2))&&yc<-seperation)){
	    		xc = (Math.random()*(width*radius*2)-(width/2));
	    		yc = (Math.random()*(width*radius*2)-(radius+width)-seperation);
	    	}
	    	double temp[] = {xc,yc,0};
	    	points[k] = temp;
	    }
	}
	void makeTestPoints(){
		for(int k = 0; k<1000; k++){
	    	double xc=100;
	    	double yc=100;
	    	while(!((Math.pow(xc-0, 2)+Math.pow(yc-0, 2)<Math.pow(radius+width, 2))&&(Math.pow(xc-0, 2)+Math.pow(yc-0, 2)>Math.pow(radius, 2))&&yc>0)){
	    		xc = (Math.random()*(width*radius*2)-(radius+width));
	    		yc = (Math.random()*(width*radius*2)-(radius+width));
	    	}
	    	double temp[] = {xc,yc};
	    	testpoints[k] = temp;
	    }
	    for(int k = 1000; k<2000; k++){
	    	double xc=100;
	    	double yc=100;
	    	while(!((Math.pow(xc-(width/2+radius), 2)+Math.pow(yc-(-seperation), 2)<Math.pow(radius+width, 2))&&(Math.pow(xc-(width/2+radius), 2)+Math.pow(yc-(-seperation), 2)>Math.pow(radius, 2))&&yc<-seperation)){
	    		xc = (Math.random()*(width*radius*2)-(width/2));
	    		yc = (Math.random()*(width*radius*2)-(radius+width)-seperation);
	    	}
	    	double temp[] = {xc,yc};
	    	testpoints[k] = temp;
	    }
	}
	
	
	public void learnRegion(){
		
		
		
		MultiLayerPerceptron neuralNetwork = new MultiLayerPerceptron(TransferFunctionType.TANH, 2 ,7, 1);
		
        BackPropagation learningRule = neuralNetwork.getLearningRule();
        
        learningRule.setMaxError(0.000001);
        learningRule.setLearningRate(0.1);
        learningRule.setMaxIterations(3000);
        
		DataSet trainingSet = new DataSet(2, 1);
		
	    for(int k = 0; k<points.length; k++){
	    	trainingSet.addRow(new DataSetRow(new double[]{points[k][0]/max, points[k][1]/max}, new double[]{points[k][2]}));
	    }

	    System.out.println("Training neural network...");
		neuralNetwork.learn(trainingSet, learningRule);
	    
	    // save trained neural network
        neuralNetwork.save("myMlPerceptron.nnet");

        // load saved neural network
        NeuralNetwork loadedMlPerceptron = NeuralNetwork.load("myMlPerceptron.nnet");
        neural = loadedMlPerceptron;
        
	}
	void printTestPoints(double[][] d){
		for(int k = 0; k<2000; k++){
			neural.setInput(new double[]{d[k][0]/max, d[k][1]/max});
			neural.calculate();
	    	if(neural.getOutput()[0]>0.5){
	    		StdDraw.setPenColor(StdDraw.RED);
	    	}
	    	StdDraw.point(d[k][0], d[k][1]);
	    	StdDraw.setPenColor(StdDraw.GRAY);
	    }
	}
	
	static void shuffleArray(double[][] ar)
	  {
	    Random rnd = new Random();
	    for (int i = ar.length - 1; i > 0; i--)
	    {
	      int index = rnd.nextInt(i + 1);
	      // Simple swap
	      double[] a = ar[index];
	      ar[index] = ar[i];
	      ar[i] = a;
	    }
	  }
}
