package com.cookies.ar;

public class Sale {
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getTotalAsString();
	}

	private float total;

	public float getTotalAsFloat() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public Sale(float total) {
		super();
		this.total = total;
	}

	public Sale(String total) {
		super();
		this.total = Float.parseFloat(total);
	}

	public String getTotalAsString() {
		return NumericFormatter.formatPrice(total);
	}

	public static Sale createSale(String type, String price) throws CashDrawerException {
		int typeAsInt = 0;
		try {
			typeAsInt = Integer.parseInt(type);
		} catch (NumberFormatException e) {
			// TODO: handle exception
			throw new CashDrawerException(
					"Invalid sale type. Valid types are 0,1,2. " + "where  0=Cash, 1=Credit and 2=Check.");
		}
		Sale sale = null;
		switch (typeAsInt) {
		case 0: {
			sale = new CashSale(price);
			break;
		}
		case 1: {
			sale = new CreditSale(price);
			break;
		}
		case 2: {
			sale = new CheckSale(price);
			break;
		}
		default:{
			throw new CashDrawerException(
					"Invalid sale type. Valid types are 0,1,2. " + 
			"where  0=Cash, 1=Credit and 2=Check.");			
		}
		}
		return sale;
	}

	public Sale() {
		// TODO Auto-generated constructor stub
	}

	public void setTotal(String total) {
		this.total = Float.parseFloat(total);
	}

}
