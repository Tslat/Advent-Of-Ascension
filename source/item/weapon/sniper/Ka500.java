package net.tslat.aoa3.item.weapon.sniper;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoASounds;

import javax.annotation.Nullable;

public class Ka500 extends BaseSniper {
	private static final ResourceLocation DOTTED_SCOPE_TEXTURE = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/gui/overlay/scope/dotted.png");

	public Ka500(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_SNIPER_FIRE.get();
	}

	@Override
	public ResourceLocation getScopeTexture(ItemStack stack) {
		return DOTTED_SCOPE_TEXTURE;
	}
}
