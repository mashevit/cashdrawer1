package com.cookies.ar;

public class CashDrawerBalancer implements Runnable {
	private String inputFilename;
	private String outputFilename;
	private CashDrawer cashDrawer;

	@Override
	public void run() {
		try {
			CashDrawerReader reader = new CashDrawerReader(inputFilename);
			reader.processFile();
			cashDrawer = reader.getCashDrawer();
			CashDrawerReportWriter writer = new CashDrawerReportWriter(outputFilename, cashDrawer);
			writer.writeBalanceReport();
		} catch (CashDrawerException e) {
			System.out.println(
					"The cash drawer you entered cannot be found or processed. Please reenter the cash drawer input file name");
			System.exit(0);
		}
	}

	public double getTotal() {
		return cashDrawer.getTotal();
	}

	public CashDrawerBalancer(String in, String out) {
		super();
		inputFilename = in;
		outputFilename = out;
	}
}
