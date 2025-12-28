package net.tslat.aoa3.content.item.weapon.sniper;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class DualSight extends AoASniper {
	public DualSight(Item.Properties properties) {
		super(properties);
	}

	@Override
	public ResourceLocation getScopeTexture(ItemStack stack) {
		return AERIAL;
	}
}
