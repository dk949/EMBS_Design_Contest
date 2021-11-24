package embs_design_contest;

public class NoC {
	
	private Task[] tasks;
	private Communication[] comms;
	public final int dimensionX, dimensionY;
	public MasterLinkList masterList;

	public NoC(Task[] tasks, Communication[] comms, int x, int y) {
		this.tasks=tasks;
		this.comms=comms;
		this.dimensionX = x;
		this.dimensionY = y;
		masterList = new MasterLinkList();
	}
	
	
	private int elementAtCoords(int x, int y) {
		return dimensionX*y + x;
	}
	
	
	/**
	 * Calculates the number of over-utilised processors in a given mapping
	 * @param mapping A mapping of tasks to processing cores
	 * @return The number of over-utilised processors
	 */
	public int getNumberOfOverutilisedProcessors(int[] mapping) {
		
		double[] utilisation = new double[mapping.length];
		
		for(int i=0; i<mapping.length; i++) {
			utilisation[mapping[i]]+= tasks[i].getUtilisation();
		}
		
		int overUtilProcs = 0;
		
		for(int u=0; u<utilisation.length; u++) {
			if(utilisation[u]>1) {
				overUtilProcs++;
			}
		}
		return overUtilProcs;
	}
	
	/**
	 * Perform XY Routing between a sender and a receiver and return a list
	 * of links used for the communication
	 * @param sender The Element to route from
	 * @param receiver The Element to route to
	 * @return A list of Links used by the communication
	 */
	private Link[] XYRoute(Element sender, Element receiver) {
		// Routers are identified in the same way as processing cores (both as Elements)
		
		// No communication links used if sender and receiver tasks mapped to same core
		if(sender.num == receiver.num) {
			return new Link[0];
		}
		
		// For a given sender and receiver, the communication between them uses:
		// * the local link from sender to sender's local router - 1 link
		// * the local link from receiver to receiver's router - 1 link
		// * all the router-router links in between - abs(receiver.x - sender.x) + abs(receiver.y - sender.y) links
		int linkCount = Math.abs(receiver.x - sender.x) + Math.abs(receiver.y - sender.y) + 2;
		Link[] links = new Link[linkCount];
		int listIndex = 0;
		
		// Links are identified by their source and destination
		// Direction of links is important - Link(0,1) and Link(1,0) are different
		// For each dimension, add the router-router links to the list of links
		
		// X and Y coordinates must be used to determine the routes but links are identified
		// by element numbers, so each time a link is used, the coordinates of the source and
		// destination element must be converted into an element number
		
		// sender --> receiver (+x) if receiver has a higher x than sender
		if(receiver.x > sender.x) {
			for(int i=sender.x; i<receiver.x; i++) {
				links[listIndex] = new Link(elementAtCoords(i, sender.y), elementAtCoords(i+1, sender.y));
				listIndex++;
			}
		// receiver <-- sender (-x) if sender has a higher x than receiver
		} else if (sender.x > receiver.x) {
			for(int i=sender.x; i>receiver.x; i--) {
				links[listIndex] = new Link(elementAtCoords(i, sender.y), elementAtCoords(i-1, sender.y));
				listIndex++;
			}
		}
		
		// sender --> receiver (+y) if receiver has a higher y than sender
		if(receiver.y > sender.y) {
			for(int i=sender.y; i<receiver.y; i++) {
				links[listIndex] = new Link(elementAtCoords(receiver.x, i), elementAtCoords(receiver.x, i+1));
				listIndex++;
			}
		// receiver <-- sender (-y) if sender has a higher y than receiver
		} else if (sender.y > receiver.y) {
			for(int i=sender.y; i>receiver.y; i--) {
				links[listIndex] = new Link(elementAtCoords(receiver.x, i), elementAtCoords(receiver.x, i-1));
				listIndex++;
			}
		}
		
		// Add the local links to the list of links
		// -1 is a special value representing a local link
		// where -1 represents the processor connected to a router
		links[listIndex] = new Link(-1, sender.num);
		listIndex++;
		links[listIndex] = new Link(receiver.num, -1);
		
		return links;
	}
	
	
	/**
	 * Given a mapping and a communication flow, determine the elements used by the
	 * communicating tasks and use XY routing to return a list of Links used by the communication
	 * @param mapping A mapping of tasks to processing cores
	 * @param comm A communication flow between two tasks
	 * @return A list of Links used by the communication flow according to the mapping
	 */
	public Link[] getLinksUsedByCommunication(int[] mapping, Communication comm) {
		
		System.out.println("Mapping = " + GeneticMapperTest.printMapping(mapping));
		
		// Get sender and receiver tasks
		Task senderTask = comm.getSender();
		Task receiverTask = comm.getReceiver();

		System.out.println("senderTask = " + senderTask.getTaskNumber());
		System.out.println("receiverTask = " + receiverTask.getTaskNumber());
		
		// Get the cores that sender and receiver tasks are mapped to and create elements		
		Element sender = new Element(mapping[senderTask.getTaskNumber()], dimensionX);
		Element receiver = new Element(mapping[receiverTask.getTaskNumber()], dimensionX);

		System.out.println("senderCore = " + sender.num);
		System.out.println("receiverCore = " + receiver.num);

		System.out.println("sender.x = " + sender.x);
		System.out.println("sender.y = " + sender.y);
		System.out.println("receiver.x = " + receiver.x);
		System.out.println("receiver.y = " + receiver.y);
		
		return XYRoute(sender, receiver);
	}
	
	
	/**
	 * Calculates the number of over-utilised communication links in a given mapping
	 * @param mapping A mapping of tasks to processing cores
	 * @return The number of over-utilised communication links
	 */
	public int getNumberOfOverutilisedCommsLinks(int[] mapping) {

		// Loop through all communications in comms and for each one:
		// * get the links used (getLinksUsedByCommunication())
		// * loop through the returned list of links used and for each one add the utilisation
		//		of the communication to the master link list for the correct link
		// Go through all links in the master link list, count the overutilised links, return the count

		for (Communication comm : comms) {
			for (Link link : getLinksUsedByCommunication(mapping, comm)) {
				masterList.add(link, comm.utilisation);
			}
		}

//		return overUtilLinks;
		return masterList.howManyOverUtelised();
	}

}
