package embs_design_contest;

public class Communication {

		Task sender;
		Task receiver;
		int commFlowNumber;
		double utilisation;

		
		public Communication(int num, double util, Task s, Task r){
			
			commFlowNumber = num;
			utilisation = util;
			sender = s;
			receiver = r;
		}
		
		public int getCommFlowNumber(){
			
			return commFlowNumber;
		}
		
		public Task getReceiver() {
			return receiver;
		}

		public Task getSender() {
			return sender;
		}


		/**
		 * @return the communication utilisation
		 */
		public double getUtilisation() {
			return utilisation;
		}
		
	}
