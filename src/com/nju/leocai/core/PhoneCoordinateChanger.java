/**
 * 
 */
package com.nju.leocai.core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author leocai
 *
 */
public class PhoneCoordinateChanger {

	public void replay(String fileName) {
		List<double[]> datas = loadData(fileName);
		replay(datas);
	}

	private void replay(final List<double[]> datas) {
		new Thread(new Runnable() {
			PhoneView phoneView = new PhoneView();
			@Override
			public void run() {
				for (double[] rtm : datas) {
					phoneView.setTransform(rtm);
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	private List<double[]> loadData(String fileName) {
		List<double[]> datas = new ArrayList<>();
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			try {
				while ((line = bufferedReader.readLine()) != null) {
					String rmtStr[] = line.split(",");
					double[] rtm = new double[9];
					for (int i = 0; i < 9; i++) {
						rtm[i] = Double.parseDouble(rmtStr[i]);
					}
					datas.add(rtm);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datas;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PhoneCoordinateChanger phoneCoordinateChanger = new PhoneCoordinateChanger();
		String fileName = "./rtm_gyr.csv";
		phoneCoordinateChanger.replay(fileName);
	}

}
