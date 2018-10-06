package serie01.util;

import util.Contract;

class StdCurrencyDB implements CurrencyDB {
	
	private double rates[];
	
	StdCurrencyDB()
	{
		this.rates = new double[CurrencyId.values().length];
		for(int i = 0; i < CurrencyId.values().length; i++)
			this.rates[i] = CurrencyId.values()[i].getRateForYear2001();
	}
	@Override
	public double getExchangeRate(CurrencyId id) {
		Contract.checkCondition(id != null, "ID ne doit pas être nul");
		return rates[id.ordinal()];
	}

	@Override
	public String getIsoCode(CurrencyId id) {
		Contract.checkCondition(id != null, "ID ne doit pas être nul");
		return id.getIsoCode();
	}

	@Override
	public String getLand(CurrencyId id) {
		Contract.checkCondition(id != null, "ID ne doit pas être nul");
		return id.getLand();
	}

	@Override
	public String getName(CurrencyId id) {
		Contract.checkCondition(id != null, "ID ne doit pas être nul");
		return id.getName();
	}

	@Override
	public void setExchangeRate(CurrencyId id, double rate) {
		Contract.checkCondition(id != null && rate > 0, "ID ne doit pas être "
				+ "nul et le taux de change > 0");
		this.rates[id.ordinal()] = rate;
		
	}

}
