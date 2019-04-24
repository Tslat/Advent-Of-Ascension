package net.tslat.aoa3.item.misc;

import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.block.functional.portal.PortalBlock;

public class Realmstone extends SimpleItem {
	private final PortalBlock portalBlock;
	private final SoundEvent portalSound;
	private final String msgSuffix;

	public Realmstone(String name, String registryName, PortalBlock portalBlock, SoundEvent activationSound, String dimensionSuffix) {
		super(name, registryName);
		this.portalBlock = portalBlock;
		this.portalSound = activationSound;
		this.msgSuffix = dimensionSuffix;
	}

	public PortalBlock getPortalBlock() {
		return portalBlock;
	}

	public SoundEvent getPortalSound() {
		return portalSound;
	}

	public String getMsgSuffix() {
		return msgSuffix;
	}
}
