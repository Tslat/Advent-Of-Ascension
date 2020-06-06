package net.tslat.aoa3.hooks.tconstruct.traits;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitShyreSynthesis extends AbstractTrait {
	public TraitShyreSynthesis() {
		super("shyre_synthesis", 0x00CFEA);
	}

	@Override
	public int onToolDamage(ItemStack tool, int damage, int newDamage, EntityLivingBase entity) {
		if (entity.world.isRemote)
			return 0;

		if (entity.world.isDaytime() && entity.world.canSeeSky(entity.getPosition()))
			return Math.max(0, newDamage - damage);

		return newDamage;
	}
}
