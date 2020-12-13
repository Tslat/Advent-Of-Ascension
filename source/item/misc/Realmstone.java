package net.tslat.aoa3.item.misc;

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
	private final String portalMessageSuffix;

	public Realmstone(RegistryObject<Block> portalBlock, @Nullable RegistryObject<SoundEvent> activationSound, String portalMessageSuffix) {
		super(new Item.Properties().group(AoAItemGroups.MISC_ITEMS).maxStackSize(1));

		this.portalBlock = portalBlock;
		this.activationSound = activationSound;
		this.portalMessageSuffix = portalMessageSuffix;
	}

	public RegistryObject<Block> getPortalBlock() {
		return portalBlock;
	}

	@Nullable
	public RegistryObject<SoundEvent> getActivationSound() {
		return activationSound;
	}

	public String getPortalMessageSuffix() {
		return portalMessageSuffix;
	}
}
