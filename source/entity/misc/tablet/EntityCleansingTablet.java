package net.tslat.aoa3.entity.misc.tablet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.item.tablet.TabletItem;

import java.util.ArrayList;

public class EntityCleansingTablet extends EntitySoulTablet {
	public EntityCleansingTablet(World world) {
		this(world, null);
	}

	public EntityCleansingTablet(World world, EntityPlayer placer) {
		super(world, placer);
	}

	@Override
	protected void doTickEffect() {
		for (EntityPlayer pl : getTargetsWithinRadius(EntityPlayer.class, player -> player != null && player.isEntityAlive() && !player.getActivePotionMap().isEmpty())) {
			ArrayList<PotionEffect> removeList = new ArrayList<PotionEffect>(pl.getActivePotionEffects().size());

			for (PotionEffect effect : pl.getActivePotionEffects()) {
				if (effect.getPotion().isBadEffect())
					removeList.add(effect);
			}

			for (PotionEffect effect : removeList) {
				pl.removePotionEffect(effect.getPotion());
			}
		}
	}

	@Override
	public TabletItem getRelevantItem() {
		return ItemRegister.CLEANSING_TABLET;
	}
}
