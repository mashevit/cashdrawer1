package com.cookies.ar;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

//p46 organize imports
//p65.12
public class CashDrawer {
	private List<Sale> sales = new Vector<Sale>();
	private double total = 0;
	private float cashIn;
	private float cashOut;
	private float difference = 0;
	private double nonCashTotal = 0;
	private double cashTotal = 0;

	public double getNonCashTotal() {
		return nonCashTotal;
	}
public CashDrawer() {
	// TODO Auto-generated constructor stub
}
	public void setNonCashTotal(double nonCashTotal) {
		this.nonCashTotal += nonCashTotal;
	}

	public double getCashTotal() {
		return cashTotal;
	}

	public void setCashTotal(double cashTotal) {
		this.cashTotal += cashTotal;
	}

	public float getCashIn() {
		return cashIn;
	}

	public boolean isBalanced() {
		boolean isInBalance = false;
		float combinedCash = (float) (getCashIn() + getCashTotal());
		if (combinedCash == cashOut)
			isInBalance = true;
		else {
			setDifference(getCashOut() - combinedCash);
		}
		return isInBalance;
	}

	public void setCashIn(String cashIn) {
		this.cashIn = Float.parseFloat(cashIn);
	}

	public float getCashOut() {
		return cashOut;
	}

	public List<Sale> getSales() {
		return sales;
	}

	public void setSales(List<Sale> sales) {
		this.sales = sales;
	}

	public void setCashOut(String cashOut) {
		this.cashOut = Float.parseFloat(cashOut);
	}

	private void PrintAllSales() {
		Iterator<Sale> iterator = sales.iterator();
		while (iterator.hasNext()) {

			Sale sale = (Sale) iterator.next();
			int index = sales.indexOf(sale) + 1;
			if (sale instanceof NonBalancing) {
				NonBalancing nonCashSale = (NonBalancing) sale;
				System.out.print(nonCashSale.getSaleType());
			} else {
				System.out.print("Cash ");
			}
			System.out.println("Sale " + index + "Total price:$" + sale);
		}
	}

	public float getDifference() {
		return difference;
	}

	public void setDifference(float difference) {
		this.difference = (difference);
	}

	public double getTotal() {
		return getCashTotal()+getNonCashTotal();
	}

	public float getLowest() {
		return lowest;
	}

	public void setLowest(float lowest) {
		this.lowest = lowest;
	}

	public float getHighest() {
		return highest;
	}

	public void setHighest(float highest) {
		this.highest = highest;
	}

	public int getCount() {
		return sales.size();
	}

	public double getAverage() {
		return (getCashTotal() +getNonCashTotal()) / getCount();
	}

	public void addASale(String type, String price) throws CashDrawerException {
		Sale sale = Sale.createSale(type, price);
		sales.add(sale);
		float saleTotal = sale.getTotalAsFloat();
		if (sale instanceof NonBalancing) {
			setNonCashTotal(saleTotal);
		} else {
			setCashTotal(saleTotal);
		}
		if (saleTotal > getHighest()) {
			setHighest(saleTotal);
		}
		if (saleTotal < getLowest()) {
			setLowest(saleTotal);
		}
	}

	public void printBalanceReport() {
		Date dnow = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("E dd.MM.yyyy 'at' hh:mm:ss a");
		System.out.println("Current date time is: " + sf.format(dnow));
		PrintStream ps = new PrintStream(System.out);
		// System.out.println("The total sales are: $" +
		// NumericFormatter.formatPrice(getTotal()));
		// System.out.println("The average sale was: $" +
		// NumericFormatter.formatPrice(getAverage()));
		// System.out.println("The highest sale was: $" +
		// NumericFormatter.formatPrice(getHighest()));
		// System.out.println("The lowest sale was: $" +
		// NumericFormatter.formatPrice(getLowest()));
		// System.out.println("The number of sales was " +
		// NumericFormatter.formatPrice(getCount()));
		ps.printf("The total sales are: $ %.2f cash total is $ %.2f NonCashTotal is $ %.2f %n", getTotal(),
				getCashTotal(), getNonCashTotal());
		ps.printf("The average sale was: $ %.2f %n", getAverage());
		ps.printf("The highest sale was: $ %.2f %n", getHighest());
		ps.printf("The lowest sale was: $ %.2f %n", getLowest());
		ps.printf("The number of sales was: %d %n", getCount());
		System.out.println("--------------------------------");
		System.out.println("All recorded sales were");
		PrintAllSales();
		System.out.println("--------------------------------");
		System.out.println("Cash in was: $" + NumericFormatter.formatPrice(getCashIn()) + ". Cash out was $"
				+ NumericFormatter.formatPrice(getCashOut()));
		System.out.println("The cash drawer did ");
		if (!isBalanced()) {
			System.out.println("NOT balance.");
			System.out.println(
					"The cash drawer is " + NumericFormatter.formatPrice(getDifference()) + " Dollars out of balance.");
			return;
		}
		System.out.println("balance.");

	}

	private float lowest = Float.MAX_VALUE;
	private float highest = Float.MIN_VALUE;

}
