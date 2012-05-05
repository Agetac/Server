package org.agetac.client;

import org.agetac.client.model.InterventionModel;

public class UpdateThread extends Thread {

	private InterventionModel interModel;
	private boolean doRun = true;

	public UpdateThread(InterventionModel interModel) {
		this.interModel = interModel;
	}

	@Override
	public void run() {
		while (doRun) {

			try {
				System.out.println("update");
				interModel.update();
				sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void doStop() {
		doRun = false;
	}
}
