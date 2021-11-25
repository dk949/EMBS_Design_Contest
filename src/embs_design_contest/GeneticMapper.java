package embs_design_contest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.HashSet;

import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.EvolutionEngine;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;
import org.uncommons.watchmaker.framework.FitnessEvaluator;
import org.uncommons.watchmaker.framework.GenerationalEvolutionEngine;
import org.uncommons.watchmaker.framework.SelectionStrategy;
import org.uncommons.watchmaker.framework.operators.EvolutionPipeline;
import org.uncommons.watchmaker.framework.selection.StochasticUniversalSampling;

import lsi.evo.IntegerArrayCrossover;
import lsi.evo.IntegerArrayFactory;
import lsi.evo.IntegerArrayMutation;

public class GeneticMapper implements FitnessEvaluator<int[]>{

	NoC platform;
	int processors;
	EvolutionEngine<int[]> engine;
	double alpha = 1.0;
	double beta = 1.0;
	double gamma = 1.0;
	
	public GeneticMapper(NoC platform) {

		this.platform = platform;
		this.processors = platform.dimensionX * platform.dimensionY;
		
		
		// generates chromosomes with tasks.size() genes (one gene per task)
		// and genes can contain integers from 0 up to "processors - 1"
		
		IntegerArrayFactory factory = new IntegerArrayFactory(processors-1, platform.tasks.length);
		
		
		// create a pipeline that applies cross-over then mutation
		
		List<EvolutionaryOperator<int[]>> operators
		    = new LinkedList<EvolutionaryOperator<int[]>>();

		operators.add(new IntegerArrayCrossover(1,new Probability(0.7)));
		operators.add(new IntegerArrayMutation(processors-1, new Probability(0.1)));

		EvolutionaryOperator<int[]> pipeline = new EvolutionPipeline<int[]>(operators);

		// instantiate evolutionary engine
		SelectionStrategy<Object> selection = new StochasticUniversalSampling();
		Random rng = new MersenneTwisterRNG();

		engine = new GenerationalEvolutionEngine<int[]>(factory,
		                                                pipeline,
		                                                this,  // uses itself as fitness function
		                                                selection,
		                                                rng);	
	
		
		
	}

	
	
	// allows external classes to access the evolution engine
	// useful for debugging and analysis/display of results
	
	public EvolutionEngine<int[]> getEngine(){return engine;}
	



	
	// method called by the evolutionary engine
	// to calculate the fitness of a given individual

	public double getFitness(int[] arg0, List<? extends int[]> arg1) {

		int[] mapping = arg0;
		
		int numberOfOverutilisedProcessors = platform.getNumberOfOverutilisedProcessors(mapping);
			
		int numberOfOverutilisedCommsLinks = platform.getNumberOfOverutilisedCommsLinks(mapping);
		
		HashSet<Element> elementsUsed = new HashSet<Element>();
		for(int i=0; i<mapping.length; i++) {
			elementsUsed.add(new Element(mapping[i], platform.dimensionX));
		}
		int maxX = 0;
		int maxY = 0;
		for(Element e : elementsUsed) {
			if(e.x > maxX) {
				maxX = e.x;
			}
			if(e.y > maxY) {
				maxY = e.y;
			}
		}
		maxX++;
		maxY++;
		
		
		
		return alpha * numberOfOverutilisedProcessors + alpha * numberOfOverutilisedCommsLinks + beta * (maxX * maxY);// + gamma * (platform.getFactorFc() + platform.getFactorFi());

	}
	
	

	public boolean isNatural() {
		
		return false; // meaning a lower fitness value denotes a fitter individual
		
	}
	
	
	public void setAlpha(double alpha){this.alpha=alpha;}
	public void setBeta(double beta){this.beta=beta;}
	public void setGamma(double gamma){this.gamma=gamma;}
	
	
}
