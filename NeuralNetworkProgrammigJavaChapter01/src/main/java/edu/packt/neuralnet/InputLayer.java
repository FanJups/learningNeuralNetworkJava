package edu.packt.neuralnet;

import java.util.ArrayList;
import java.util.Arrays;

import edu.packt.neuralnet.math.Linear;

/**
 *
 * InputLayer This class extends from NeuralLayer and represents the input layer
 * of the Neural Network
 * 
 * @author Alan de Souza, Fábio Soares
 * @version 0.1
 */
public class InputLayer extends NeuralLayer {

	/**
	 * InputLayer constructor
	 * 
	 * @param numberofinputs Number of Inputs of this layer and the Neural Network
	 * @see InputLayer
	 */
	public InputLayer(int numberofinputs) {
		super(numberofinputs, new Linear(1));
		previousLayer = null;
		numberOfInputs = numberofinputs;
		init();
	}

	/**
	 * setNextLayer This method links this layer to a next layer in the Neural
	 * Network
	 * 
	 * @param layer Next Neural Layer
	 * @see InputLayer
	 */
	@Override
	public void setNextLayer(NeuralLayer layer) {
		nextLayer = layer;
		if (layer.previousLayer != this)
			layer.setPreviousLayer(this);
	}

	/**
	 * setPreviousLayer This method prevents any attempt to link this layer to a
	 * previous one, provided that this should be the first layer
	 * 
	 * @param layer dummy Neural Layer
	 * @see InputLayer
	 */
	@Override
	public void setPreviousLayer(NeuralLayer layer) {
		previousLayer = null;
	}

	/**
	 * init This method initializes all neurons of this layer
	 * 
	 * @see InputLayer
	 */
	@Override
	public void init() {
		for (int i = 0; i < numberOfInputs; i++) {
			this.setNeuron(i, new InputNeuron());
			this.getNeuron(i).init();
		}
	}

	/**
	 * setInputs This method feeds an array of real values into this layer's inputs
	 * 
	 * @param inputs array of values to be fed into the layer's inputs
	 * @see InputLayer
	 */
	@Override
	public void setInputs(ArrayList<Double> inputs) {
		if (inputs.size() == numberOfInputs) {
			input = inputs;
		}
	}

	/**
	 * calc This method overrides the superclass calc because it just passes the
	 * input values to the outputs, provided this is the input layer
	 * 
	 * @see InputLayer
	 */
	@Override
	public void calc() {
		if (input != null && getListOfNeurons() != null) {
			for (int i = 0; i < numberOfNeuronsInLayer; i++) {
				double[] firstInput = { this.input.get(i) };
				getNeuron(i).setInputs(firstInput);
				getNeuron(i).calc();
				try {
					output.set(i, getNeuron(i).getOutput());
				} catch (IndexOutOfBoundsException iobe) {
					output.add(getNeuron(i).getOutput());
				}
			}
		}
	}

	@Override
	public String toString() {

		String string = "";

		for (int i = 0; i < getListOfNeurons().size(); i++) {
			int x = i + 1;
			double[] tab = getListOfNeurons().get(i).getInputs();
			string = string + "Neuron #" + x + ":" + "\n" + "Input Weights:" + "\n" + Arrays.asList(tab) + "\n";
		}
		return string;
	}

}
