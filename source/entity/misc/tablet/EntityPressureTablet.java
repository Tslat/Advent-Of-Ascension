package net.tslat.aoa3.entity.misc.tablet;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.item.tablet.TabletItem;

public class EntityPressureTablet extends EntitySoulTablet {
	public EntityPressureTablet(World world) {
		this(world, null);
	}

	public EntityPressureTablet(World world, EntityPlayer placer) {
		super(world, placer);
	}

	@Override
	protected void doTickEffect() {
		for (EntityLivingBase entity : getTargetsWithinRadius(EntityLivingBase.class, entity -> entity instanceof IMob && entity.isEntityAlive())) {
			entity.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 10, 2, true, true));
		}
	}

	@Override
	public TabletItem getRelevantItem() {
		return ItemRegister.PRESSURE_TABLET;
	}
}
