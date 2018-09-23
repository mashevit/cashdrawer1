package com.cookies.ar;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CashDrawerReader {
	private BufferedReader reader = null;
	private CashDrawer cashDrawer = null;

	public CashDrawer getCashDrawer() {
		return cashDrawer;
	}

	public void setCashDrawer(CashDrawer cashDrawer) {
		this.cashDrawer = cashDrawer;
	}

	public CashDrawerReader(String filename) throws CashDrawerException {
		super();
		init(filename);
	}

	private void init(String filename) throws CashDrawerException {
		try {
			FileReader fileReader = new FileReader(filename);
			reader = new BufferedReader(fileReader);
			cashDrawer = new CashDrawer();
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			throw new CashDrawerException(e.getMessage());
		}
	}

	private Line readLine() {
		Line line = null;
		try {
			String data = reader.readLine();
			if (data == null)
				return line;
			int substringIndex = data.indexOf(" ");
			String firstString = data.substring(0, substringIndex);
			String secondString = data.substring(substringIndex + 1, data.length());
			line = new Line(firstString, secondString);
			return line;
		} catch (IOException e) {
			// TODO: handle exception
			System.out.println(e + "encountered. Unable to process input file.");
			return null;
		}
	}

	public void processFile() {
		Line cashValuesLine = readLine();
		cashDrawer.setCashIn(cashValuesLine.getFirst());
		cashDrawer.setCashOut(cashValuesLine.getSecond());
		Line saleLine = null;
		try {
			do {
				saleLine = readLine();
				if (saleLine == null)
					break;
				cashDrawer.addASale(saleLine.getFirst(), saleLine.getSecond());
			} while (true);
		} catch (CashDrawerException e) {
			// TODO: handle exception
			System.out.println(e + "encountered. Unable to process input file.");
		} finally {
			try {
				reader.close();
			} catch (IOException e2) {
				// TODO: handle exception
				System.exit(1);
			}
		}

	}

}
