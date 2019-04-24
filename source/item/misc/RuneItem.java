package net.tslat.aoa3.item.misc;

import net.tslat.aoa3.block.generation.misc.RunePostBlock;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;

public class RuneItem extends SimpleItem {
	private final boolean charged;

	public RuneItem(String name, String registryName, boolean isCharged) {
		this(name, registryName, isCharged, null);
	}

	public RuneItem(String name, String registryName, boolean isCharged, RunePostBlock runePost) {
		super(name, registryName);
		this.charged = isCharged;
		setCreativeTab(CreativeTabsRegister.ammoTab);

		if (runePost != null)
			applyToRunePost(runePost);
	}

	public boolean isChargedRune() {
		return charged;
	}

	private void applyToRunePost(RunePostBlock post) {
		post.setRune(this);
	}
}
