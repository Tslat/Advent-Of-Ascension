package net.tslat.aoa3.item.misc;

import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.block.functional.portal.PortalBlock;

import java.util.function.Supplier;

public class Realmstone extends SimpleItem {
	private final PortalBlock portalBlock;
	private final Supplier<SoundEvent> portalSound;
	private final String msgSuffix;

	public Realmstone(String name, String registryName, PortalBlock portalBlock, Supplier<SoundEvent> activationSound, String dimensionSuffix) {
		super(name, registryName);
		this.portalBlock = portalBlock;
		this.portalSound = activationSound;
		this.msgSuffix = dimensionSuffix;

		setMaxStackSize(1);
	}

	public PortalBlock getPortalBlock() {
		return portalBlock;
	}

	public SoundEvent getPortalSound() {
		return portalSound.get();
	}

	public String getMsgSuffix() {
		return msgSuffix;
	}
}
