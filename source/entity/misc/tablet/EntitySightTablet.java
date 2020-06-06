package net.tslat.aoa3.entity.misc.tablet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.item.tablet.TabletItem;

public class EntitySightTablet extends EntitySoulTablet {
	public EntitySightTablet(World world) {
		this(world, null);
	}

	public EntitySightTablet(World world, EntityPlayer placer) {
		super(world, placer);
	}

	@Override
	protected void doTickEffect() {
		for (EntityPlayer pl : getTargetsWithinRadius(EntityPlayer.class, player -> player != null && player.isEntityAlive())) {
			PotionEffect nightVision = pl.getActivePotionEffect(MobEffects.NIGHT_VISION);

			if (nightVision == null || nightVision.getDuration() < 250)
				pl.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 300, 0, true, true));
		}
	}

	@Override
	public TabletItem getRelevantItem() {
		return ItemRegister.SIGHT_TABLET;
	}
}
