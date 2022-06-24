package net.tslat.aoa3.content.item.misc;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import net.tslat.aoa3.common.registration.AoACreativeModeTabs;

import javax.annotation.Nullable;

public class Realmstone extends Item {
	private final RegistryObject<Block> portalBlock;
	@Nullable
	private final RegistryObject<SoundEvent> activationSound;
	private final String dimensionMsgSuffix;

	public Realmstone(RegistryObject<Block> portalBlock, @Nullable RegistryObject<SoundEvent> activationSound, String dimension) {
		super(new Item.Properties().tab(AoACreativeModeTabs.MISC_ITEMS).stacksTo(1));

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
