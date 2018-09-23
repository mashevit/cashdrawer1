package com.cookies.ar;

import java.util.ArrayList;
import java.util.List;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int count = args.length;
		if ((count < 2) || (count % 2 > 0)) {
			System.out.println(
					"You must enter at least one input file name and one output file name. In put and output file names must be entered in pairs");
			System.exit(1);
		}

		List<CashDrawerBalancer> balancers = new ArrayList<CashDrawerBalancer>();
		List<Thread> threads = new ArrayList<Thread>();
		count /= 2;
		for (int i = 0; i < count; i++) {
			try {
				CashDrawerBalancer balancer = new CashDrawerBalancer(args[2 * i], args[2 * i + 1]);
				balancers.add(balancer);
				Thread thread = new Thread(balancer);
				threads.add(thread);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Driver main() encountered  " + e + "while instantiating threads");
			}
		}
		for (int i = 0; i < count; i++) {

			try {
				Thread tempThread = (Thread) threads.get(i);
				tempThread.start();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Driver main() encountered: " + e + "while starting threads.");
			}

		}
		for (int i = 0; i < count; i++) {
			try {
				Thread tempTread = (Thread) threads.get(i);
				tempTread.join();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Driver main() encountered: " + e + "while waiting for threads to join.");
			}
		}
		double grandTotal = 0;
		for (int i = 0; i < count; i++) {
			CashDrawerBalancer tempBalancer = (CashDrawerBalancer) balancers.get(i);
			grandTotal += tempBalancer.getTotal();
		}
		System.out.println("The grand total sales is $" + NumericFormatter.formatPrice(grandTotal));
	}
}
