package serie01.model;

import serie01.util.Currency;
import serie01.util.CurrencyId;
import util.Contract;

public class StdMultiConverter implements MultiConverter{
	
	private Currency[] tabCurrency;
	private double[] currencyAmount;
	
	public StdMultiConverter(int n) {
		Contract.checkCondition(n >= 2, "Nombre de devise invalide");
		this.tabCurrency = new Currency[n];
		this.currencyAmount = new double[n];
		for(int i = 0; i < tabCurrency.length; i++)
		{
			this.tabCurrency[i] = Currency.get(CurrencyId.EUR);
			currencyAmount[i] = 0.0;
		}
	}
	@Override
	public double getAmount(int index) {
		Contract.checkCondition(index >= 0 && index < this.getCurrencyNb(),
				"Index doit être compris entre 2 et " + this.getCurrencyNb());
		return this.currencyAmount[0] * this.getExchangeRate(0, index);
	}

	@Override
	public Currency getCurrency(int index) {
		Contract.checkCondition(index >= 0 && index < this.getCurrencyNb(),
				"Index doit être compris entre 2 et " + this.getCurrencyNb());
		return tabCurrency[index];
	}

	@Override
	public int getCurrencyNb() {
		return this.tabCurrency.length;
	}

	@Override
	public double getExchangeRate(int index1, int index2) {
		Contract.checkCondition(index1 >= 0 && index1 < this.getCurrencyNb()
				&& index2 >= 0 && index2 < this.getCurrencyNb(),
						"Index doit être compris entre 2 et " + 
				this.getCurrencyNb());
		return this.tabCurrency[index2].getExchangeRate()
				/  this.tabCurrency[index1].getExchangeRate();
	}

	@Override
	public void setAmount(int index, double amount) {
		Contract.checkCondition(index >= 0 && index < this.getCurrencyNb()
				&& amount >= 0,
				"Index doit être compris entre 2 et " + this.getCurrencyNb() +
				"et amount >= 0");
		this.currencyAmount[0] = amount * this.getExchangeRate(index, 0);
		
	}

	@Override
	public void setCurrency(int index, Currency c) {
		Contract.checkCondition(index >= 0 && index < this.getCurrencyNb(),
				"Index doit être compris entre 2 et " + this.getCurrencyNb());
		Contract.checkCondition(c != null,"Currency ne peut pas être nulle");
		this.tabCurrency[index] = c;
		
	}

}
