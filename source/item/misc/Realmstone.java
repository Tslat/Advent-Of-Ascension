package net.tslat.aoa3.item.misc;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.tslat.aoa3.block.functional.portal.PortalBlock;

public class Realmstone extends SimpleItem {
	private final PortalBlock portalBlock;
	private final ResourceLocation portalSound;
	private final String msgSuffix;

	public Realmstone(String name, String registryName, PortalBlock portalBlock, ResourceLocation activationSound, String dimensionSuffix) {
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
		return ForgeRegistries.SOUND_EVENTS.getValue(portalSound);
	}

	public String getMsgSuffix() {
		return msgSuffix;
	}
}
