package embs_design_contest;


public class Task {

		protected int taskNumber;
		protected double utilisation;

		public Task(int num, double util) {
			taskNumber = num;
			utilisation = util;
		}

		public int getTaskNumber() {
			return taskNumber;
		}
		
		/**
		 * @return the utilisation
		 */
		public double getUtilisation() {
			return utilisation;
		}
		
	}
