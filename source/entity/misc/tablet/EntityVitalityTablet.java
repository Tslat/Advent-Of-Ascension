package net.tslat.aoa3.entity.misc.tablet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.item.tablet.TabletItem;

public class EntityVitalityTablet extends EntitySoulTablet {
	public EntityVitalityTablet(World world) {
		this(world, null);
	}

	public EntityVitalityTablet(World world, EntityPlayer placer) {
		super(world, placer);
	}

	@Override
	protected void doTickEffect() {
		if (world.getTotalWorldTime() % 10 == 0) {
			for (EntityPlayer pl : getTargetsWithinRadius(EntityPlayer.class, player -> player != null && player.isEntityAlive())) {
				if (!pl.isPotionActive(MobEffects.REGENERATION))
					pl.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 50, 1, true, true));
			}
		}
	}

	@Override
	public TabletItem getRelevantItem() {
		return ItemRegister.VITALITY_TABLET;
	}
}
