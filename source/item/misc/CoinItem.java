package net.tslat.aoa3.item.misc;

public class CoinItem extends SimpleItem {
	private final int intrinsicValue;

	public CoinItem(String name, String registryName, int value) {
		super(name, registryName);

		this.intrinsicValue = value;
	}

	public int getIntrinsicValue() {
		return intrinsicValue;
	}
}
