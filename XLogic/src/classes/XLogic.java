package classes;

import java.util.Arrays;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.core.learning.SupervisedLearning;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.BackPropagation;
import org.neuroph.util.TransferFunctionType;


public class XLogic {
	public static void main(String[] args) {
        new XLogic().XOR();
    }
	
	// create new perceptron network
	public void XOR(){
		
		MultiLayerPerceptron neuralNetwork = new MultiLayerPerceptron(TransferFunctionType.TANH, 2, 3, 1);
		
        BackPropagation learningRule = neuralNetwork.getLearningRule();
        
        learningRule.setMaxError(0.00001);
        learningRule.setLearningRate(0.1);
        learningRule.setMaxIterations(5000);
        
		DataSet trainingSet = new DataSet(2, 1);
		
	    trainingSet.addRow(new DataSetRow(new double[]{0, 0}, new double[]{0}));
	    
	    trainingSet.addRow(new DataSetRow(new double[]{0, 1}, new double[]{1}));
	    
	    trainingSet.addRow(new DataSetRow(new double[]{1, 0}, new double[]{1}));
	    
	    trainingSet.addRow(new DataSetRow(new double[]{1, 1}, new double[]{0}));

	    System.out.println("Training neural network...");
		neuralNetwork.learn(trainingSet, learningRule);
	    
	    // save trained neural network
        neuralNetwork.save("myMlPerceptron.nnet");

        // load saved neural network
        NeuralNetwork loadedMlPerceptron = NeuralNetwork.load("myMlPerceptron.nnet");

		System.out.println("Testing loaded neural network");
		testNeuralNetwork(neuralNetwork, trainingSet);
	}
	
	public static void testNeuralNetwork(NeuralNetwork neuralNet, DataSet testSet) {

        for(DataSetRow testSetRow : testSet.getRows()) {
            neuralNet.setInput(testSetRow.getInput());
            neuralNet.calculate();
            double[] networkOutput = neuralNet.getOutput();

            System.out.print("Input: " + Arrays.toString( testSetRow.getInput() ) );
            System.out.println(" Output: " + Arrays.toString( networkOutput) );
        }
    }
	

}
