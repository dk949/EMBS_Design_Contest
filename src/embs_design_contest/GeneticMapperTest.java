package embs_design_contest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;

import org.uncommons.watchmaker.framework.EvolutionObserver;
import org.uncommons.watchmaker.framework.PopulationData;
import org.uncommons.watchmaker.framework.termination.GenerationCount;
import org.uncommons.watchmaker.swing.evolutionmonitor.EvolutionMonitor;

public class GeneticMapperTest {

	public static void main(String[] args){
		
		
		System.out.println("Running class: GeneticMapper");
		System.out.println("");

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();

		System.out.println(dateFormat.format(date));
		System.out.println("");
		
		
		// instantiate application tasks and comms
		
		Task[] tasks = new Task[] {
			new Task(0, 0.38), new Task(1, 0.19), new Task(2, 0.29), new Task(3, 0.09), new Task(4, 0.26),
			new Task(5, 0.41), new Task(6, 0.13), new Task(7, 0.32), new Task(8, 0.33), new Task(9, 0.18),
			new Task(10, 0.08), new Task(11, 0.16), new Task(12, 0.17), new Task(13, 0.22), new Task(14, 0.11),
			new Task(15, 0.36), new Task(16, 0.14), new Task(17, 0.07), new Task(18, 0.51), new Task(19, 0.12),
			new Task(20, 0.27), new Task(21, 0.09), new Task(22, 0.36), new Task(23, 0.25), new Task(24, 0.24),
			new Task(25, 0.52), new Task(26, 0.06), new Task(27, 0.21), new Task(28, 0.53), new Task(29, 0.20),
			new Task(30, 0.42), new Task(31, 0.49), new Task(32, 0.20), new Task(33, 0.15), new Task(34, 0.34),
			new Task(35, 0.27), new Task(36, 0.09), new Task(37, 0.28), new Task(38, 0.33), new Task(39, 0.05),
			new Task(40, 0.47), new Task(41, 0.55), new Task(42, 0.34), new Task(43, 0.39), new Task(44, 0.46),
			new Task(45, 0.06), new Task(46, 0.36), new Task(47, 0.11), new Task(48, 0.62), new Task(49, 0.45),
			new Task(50, 0.42), new Task(51, 0.64), new Task(52, 0.29), new Task(53, 0.59)
		};	
		

		Communication[] comms = new Communication[] {
			new Communication(0, 0.47, tasks[27], tasks[34]), new Communication(1, 0.45, tasks[50], tasks[28]),
			new Communication(2, 0.13, tasks[33], tasks[26]), new Communication(3, 0.27, tasks[16], tasks[30]),
			new Communication(4, 0.52, tasks[7], tasks[48]), new Communication(5, 0.43, tasks[43], tasks[21]),
			new Communication(6, 0.44, tasks[8], tasks[38]), new Communication(7, 0.19, tasks[14], tasks[34]),
			new Communication(8, 0.15, tasks[23], tasks[26]), new Communication(9, 0.32, tasks[17], tasks[8]),
			new Communication(10, 0.28, tasks[22], tasks[9]), new Communication(11, 0.05, tasks[49], tasks[27]),
			new Communication(12, 0.22, tasks[22], tasks[41]), new Communication(13, 0.12, tasks[1], tasks[49]),
			new Communication(14, 0.52, tasks[35], tasks[13]), new Communication(15, 0.2, tasks[47], tasks[4]),
			new Communication(16, 0.23, tasks[41], tasks[30]), new Communication(17, 0.06, tasks[40], tasks[3]),
			new Communication(18, 0.51, tasks[14], tasks[1]), new Communication(19, 0.12, tasks[26], tasks[13]),
			new Communication(20, 0.52, tasks[27], tasks[42]), new Communication(21, 0.23, tasks[48], tasks[2]),
			new Communication(22, 0.34, tasks[16], tasks[29]), new Communication(23, 0.13, tasks[0], tasks[8]),
			new Communication(24, 0.37, tasks[20], tasks[32]), new Communication(25, 0.49, tasks[42], tasks[40]),
			new Communication(26, 0.06, tasks[12], tasks[28]), new Communication(27, 0.15, tasks[36], tasks[11]),
			new Communication(28, 0.1, tasks[22], tasks[2]), new Communication(29, 0.4, tasks[18], tasks[18]),
			new Communication(30, 0.12, tasks[28], tasks[18]), new Communication(31, 0.44, tasks[4], tasks[32]),
			new Communication(32, 0.38, tasks[44], tasks[36]), new Communication(33, 0.2, tasks[25], tasks[41]),
			new Communication(34, 0.19, tasks[43], tasks[37]), new Communication(35, 0.06, tasks[37], tasks[2]),
			new Communication(36, 0.42, tasks[30], tasks[49]), new Communication(37, 0.25, tasks[3], tasks[33]),
			new Communication(38, 0.11, tasks[39], tasks[40]), new Communication(39, 0.2, tasks[2], tasks[0]),
			new Communication(40, 0.14, tasks[21], tasks[17]), new Communication(41, 0.21, tasks[8], tasks[33]),
			new Communication(42, 0.33, tasks[33], tasks[7]), new Communication(43, 0.04, tasks[34], tasks[44]),
			new Communication(44, 0.07, tasks[45], tasks[33]), new Communication(45, 0.3, tasks[11], tasks[3]),
			new Communication(46, 0.34, tasks[19], tasks[8]), new Communication(47, 0.16, tasks[0], tasks[9]),
			new Communication(48, 0.11, tasks[38], tasks[11]), new Communication(49, 0.41, tasks[12], tasks[39]),
			new Communication(50, 0.35, tasks[35], tasks[42]), new Communication(51, 0.06, tasks[46], tasks[20]),
			new Communication(52, 0.21, tasks[41], tasks[32]), new Communication(53, 0.12, tasks[5], tasks[44]),
			new Communication(54, 0.28, tasks[44], tasks[13]), new Communication(55, 0.31, tasks[26], tasks[41]),
			new Communication(56, 0.2, tasks[13], tasks[4]), new Communication(57, 0.09, tasks[6], tasks[0]),
			new Communication(58, 0.34, tasks[15], tasks[10]), new Communication(59, 0.36, tasks[31], tasks[39]),
			new Communication(60, 0.18, tasks[17], tasks[5]), new Communication(61, 0.09, tasks[29], tasks[7]),
			new Communication(62, 0.11, tasks[24], tasks[40]), new Communication(63, 0.29, tasks[21], tasks[43]),
			new Communication(64, 0.23, tasks[13], tasks[23]), new Communication(65, 0.38, tasks[19], tasks[26]),
			new Communication(66, 0.08, tasks[32], tasks[10]), new Communication(67, 0.11, tasks[9], tasks[26]),
			new Communication(68, 0.32, tasks[23], tasks[4]), new Communication(69, 0.23, tasks[10], tasks[5])
		};
		
		
		// instantiate mapper
		// passing as argument the array of tasks, array of comms and number of processors in the platform
		
		GeneticMapper mapper = new GeneticMapper(tasks, comms, 3, 3);
		
		
		// set parameters to tune the two elements of the fitness function
		mapper.setAlpha(400.0);
		mapper.setBeta(1.0);		
		

		// use watchmaker framework's debugging and visualisation features
		
		mapper.getEngine().addEvolutionObserver(new EvolutionObserver<int[]>()
				{
		    public void populationUpdate(PopulationData<? extends int[]> data)
		    {
		        System.out.println("Generation: " + data.getGenerationNumber() + ", fitness: " + data.getBestCandidateFitness() + ", mapping: "+ printMapping(data.getBestCandidate()));
		    }
		});
	
		EvolutionMonitor<int[]> monitor = new EvolutionMonitor<int[]>();
		
		mapper.getEngine().addEvolutionObserver(monitor);
		
		JFrame frame = new JFrame("Evolution Monitor");
		frame.getContentPane().add(monitor.getGUIComponent());
		frame.pack();
		frame.setVisible(true);
		
//		int[] result = mapper.getEngine().evolve(1000, 20, new GenerationCount(180)); // evolve for 180 generations
//		System.out.println("Execution terminated - best mapping:");
//		System.out.println(printMapping(result));
//		System.out.println("");
//		
//		System.out.println("Overloaded processors: "+mapper.getOverutilisedProcessors(result));
//		System.out.println("Interprocessor comms: "+mapper.getInterprocessorCommunicationVolume(result));
		
		int[] exampleMapping = new int[] {5, 2, 3, 5, 7, 0, 2, 2, 0, 2, 4, 5, 0, 4, 4, 4, 0, 4, 7, 7, 3, 8, 0, 5, 4, 6, 0, 6, 7, 3, 4, 4, 3, 1, 1, 2, 8, 5, 2, 2, 1, 5, 4, 6, 7, 5, 0, 8, 2, 0, 8, 0, 7, 6};
		NoC platform = new NoC(tasks, comms, 3, 3);
		Link[] linksUsed = platform.getLinksUsedByCommunication(exampleMapping, comms[0]);
		for(int i=0; i<linksUsed.length; i++) {
			System.out.println(linksUsed[i].toString());
		}
		
		int overUtilProcs = platform.getNumberOfOverutilisedProcessors(exampleMapping);
		System.out.println("Number of over-utilised processors: " + Integer.toString(overUtilProcs));
		
		date = new Date();
		System.out.println("");
		System.out.println(dateFormat.format(date));
		
		
		
	}
	
	
	public static String printMapping(int[] mapping){
		
		String output = "[";
		for(int i=0;i<mapping.length-1;i++){
			
			output = output + mapping[i] + " - ";
		}
		output = output + mapping[mapping.length-1] + "]";
		return output;
	}
	
	
}
