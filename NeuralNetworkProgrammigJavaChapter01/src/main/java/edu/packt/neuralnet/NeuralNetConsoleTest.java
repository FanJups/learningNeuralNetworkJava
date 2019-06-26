package edu.packt.neuralnet;

import edu.packt.neuralnet.math.IActivationFunction;
import edu.packt.neuralnet.math.Linear;
import edu.packt.neuralnet.math.RandomNumberGenerator;
import edu.packt.neuralnet.math.Sigmoid;

/**
 *
 * NeuralNetConsoleTest This class is solely used for creating and testing your
 * very first Neural Network in Java
 *
 * @author Alan de Souza, Fábio Soares
 */
public class NeuralNetConsoleTest {

	public void print(int numberofinputs, int numberofoutputs, int[] numberofhiddenneurons,
			IActivationFunction[] hiddenAcFnc, IActivationFunction outputAcFnc) {

		InputLayer inputLayer = new InputLayer(numberofinputs);

		HiddenLayer hiddenLayer = new HiddenLayer(numberofhiddenneurons[0], hiddenAcFnc[0], numberofinputs);

		OutputLayer outputLayer = new OutputLayer(numberofhiddenneurons[0], outputAcFnc, numberofinputs);

		System.out.println("### INPUT LAYER ###");
		System.out.println(inputLayer);

		System.out.println("### HIDDEN LAYER ###");
		System.out.println(hiddenLayer);

		System.out.println("### OUTPUT LAYER ###");
		System.out.println(outputLayer);

	}

	public static void main(String[] args) {

		NeuralNetConsoleTest neuralNetConsoleTest = new NeuralNetConsoleTest();

		RandomNumberGenerator.seed = 0;

		int numberOfInputs = 2;
		int numberOfOutputs = 1;
		int[] numberOfHiddenNeurons = { 3 };
		IActivationFunction[] hiddenAcFnc = { new Sigmoid(1.0) };
		Linear outputAcFnc = new Linear(1.0);
		NeuralNet nn = new NeuralNet(numberOfInputs, numberOfOutputs, numberOfHiddenNeurons, hiddenAcFnc, outputAcFnc);

		double[] neuralInput = { 1.5, 0.5 };

		double[] neuralOutput;
		nn.setInputs(neuralInput);
		nn.calc();
		neuralOutput = nn.getOutputs();

		neuralInput[0] = 1.0;
		neuralInput[1] = 2.1;

		nn.setInputs(neuralInput);
		nn.calc();
		neuralOutput = nn.getOutputs();

		neuralNetConsoleTest.print(numberOfInputs, numberOfOutputs, numberOfHiddenNeurons, hiddenAcFnc, outputAcFnc);

	}
}
