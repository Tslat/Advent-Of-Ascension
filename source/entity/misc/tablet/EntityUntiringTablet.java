package net.tslat.aoa3.entity.misc.tablet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.item.tablet.TabletItem;

public class EntityUntiringTablet extends EntitySoulTablet {
	public EntityUntiringTablet(World world) {
		this(world, null);
	}

	public EntityUntiringTablet(World world, EntityPlayer placer) {
		super(world, placer);
	}

	@Override
	protected void doTickEffect() {
		for (EntityPlayer pl : getTargetsWithinRadius(EntityPlayer.class, player -> player != null && player.isEntityAlive())) {
			pl.addPotionEffect(new PotionEffect(MobEffects.HASTE, 10, 0, true, true));
		}
	}

	@Override
	public TabletItem getRelevantItem() {
		return ItemRegister.UNTIRING_TABLET;
	}
}
