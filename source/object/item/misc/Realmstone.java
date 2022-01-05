package net.tslat.aoa3.object.item.misc;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.tslat.aoa3.common.registration.AoAItemGroups;

import javax.annotation.Nullable;

public class Realmstone extends Item {
	private final RegistryObject<Block> portalBlock;
	@Nullable
	private final RegistryObject<SoundEvent> activationSound;
	private final String dimensionMsgSuffix;

	public Realmstone(RegistryObject<Block> portalBlock, @Nullable RegistryObject<SoundEvent> activationSound, String dimension) {
		super(new Item.Properties().tab(AoAItemGroups.MISC_ITEMS).stacksTo(1));

		this.portalBlock = portalBlock;
		this.activationSound = activationSound;
		this.dimensionMsgSuffix = dimension;
	}

	public RegistryObject<Block> getPortalBlock() {
		return portalBlock;
	}

	@Nullable
	public RegistryObject<SoundEvent> getActivationSound() {
		return activationSound;
	}

	public String getDimensionMsgSuffix() {
		return dimensionMsgSuffix;
	}
}
