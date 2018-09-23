package com.cookies.ar;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

public class CashDrawerReportWriter {
	private BufferedWriter writer = null;
	private CashDrawer cashDrawer = null;
	private static final String newLine = "\r\n";

	private void init(String filename, CashDrawer cashDrawer) {
		try {
			FileWriter fileWriter = new FileWriter(filename);
			writer = new BufferedWriter(fileWriter);
			this.cashDrawer = cashDrawer;
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			System.out.println("Unable to find file " + filename);
			System.exit(1);
		} catch (IOException e) {
			System.out.println(e + " encountered while initializing cash drawer writer");
		}
	}

	private void init(CashDrawer cashDrawer) {

			this.cashDrawer = cashDrawer;

	}
	
	public String getBalanceReport() {
		return assembleBalanceReport();
		
	}
	private String getAllSales() {
		Iterator iterator = cashDrawer.getSales().iterator();
		StringBuffer buffer = new StringBuffer();
		while (iterator.hasNext()) {
			Sale sale = (Sale) iterator.next();
			if (sale instanceof NonBalancing) {
				NonBalancing nonCashSale = (NonBalancing) sale;
				buffer.append(nonCashSale.getSaleType());
			} else {
				buffer.append("Cash ");
			}
			buffer.append("Sale: Total price:$");
			buffer.append(sale.toString());
			buffer.append(newLine);
		}
		return buffer.toString();
	}

	private String assembleBalanceReport() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("The total sales are: $");
		buffer.append(NumericFormatter.formatPrice(cashDrawer.getTotal()));
		buffer.append(newLine);
		buffer.append("The average sale was: $");
		buffer.append(NumericFormatter.formatPrice(cashDrawer.getAverage()));
		buffer.append(newLine);
		buffer.append("The highest sale was: $");
		buffer.append(NumericFormatter.formatPrice(cashDrawer.getHighest()));
		buffer.append(newLine);
		buffer.append("The lowest sale was: $");
		buffer.append(NumericFormatter.formatPrice(cashDrawer.getLowest()));
		buffer.append(newLine);
		buffer.append("The cash sales are: $");
		buffer.append(NumericFormatter.formatPrice(cashDrawer.getCashTotal()));
		buffer.append(newLine);
		buffer.append("The number of sales was: ");
		int salesCount = cashDrawer.getCount();
		String salesCountAsString = String.valueOf(salesCount);
		buffer.append(salesCountAsString);
		buffer.append(newLine);
		buffer.append("--------------------------------");
		buffer.append("All recorded sales were:");
		buffer.append(newLine);
		buffer.append(getAllSales());
		buffer.append("-------------------------------");
		buffer.append("Cash in was:$");
		buffer.append(NumericFormatter.formatPrice(cashDrawer.getCashIn()));
		buffer.append(". Cash out was:$");
		buffer.append(NumericFormatter.formatPrice(cashDrawer.getCashOut()));
		buffer.append(newLine);
		buffer.append("The cash drawer did ");
		if (cashDrawer.isBalanced() == false) {
			buffer.append("NOT Balance");
			buffer.append(newLine);
			buffer.append("The cash drawer is ");
			buffer.append(NumericFormatter.formatPrice(cashDrawer.getDifference()));
			buffer.append(" dollars out of Balance");
		} else {
			buffer.append("balance");
		}
		return buffer.toString();

	}

	public void writeBalanceReport() {
		try {
			writer.write(assembleBalanceReport());
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO: handle exception
			System.out.println("Unable to succesfully write balance report due to: " + e);
		}
		finally {
			try {
				writer.close();
			} catch (Exception ioe) {
				// TODO: handle exception
				System.out.println("Unable to close report file due to: " + ioe);

			}
		}
	}
	public CashDrawerReportWriter(String filename,CashDrawer cashDrawer) {
		super();
		init(filename,cashDrawer);
	}
	public CashDrawerReportWriter(CashDrawer cashDrawer) {
		super();
		init(cashDrawer);
	}
}
