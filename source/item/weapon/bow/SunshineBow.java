package net.tslat.aoa3.item.weapon.bow;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.projectiles.arrow.EntityHollyArrow;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.PredicateUtil;

import java.util.List;

public class SunshineBow extends BaseBow {
	public SunshineBow(double damage, float drawSpeedMultiplier, int durability) {
		super(damage, drawSpeedMultiplier, durability);
		setTranslationKey("SunshineBow");
		setRegistryName("aoa3:sunshine_bow");
	}

	@Override
	public void doImpactEffect(EntityHollyArrow arrow, Entity target, Entity shooter, float damage) {
		if (arrow.getIsCritical()) {
			EntityAreaEffectCloud cloud = new EntityAreaEffectCloud(target.world, arrow.posX, arrow.posY, arrow.posZ);

			cloud.addEffect(new PotionEffect(MobEffects.GLOWING, 200, 0, true, false));
			cloud.setRadius(0.5f);
			cloud.setRadiusPerTick(30);
			cloud.setDuration(2);
			cloud.setWaitTime(0);
			cloud.setColor(Enums.RGBIntegers.WHITE);
			cloud.setParticle(EnumParticleTypes.SPELL_INSTANT);

			if (shooter instanceof EntityLivingBase)
				cloud.setOwner((EntityLivingBase)shooter);

			target.world.spawnEntity(cloud);

			for (EntityLivingBase entity : arrow.world.getEntitiesWithinAABB(EntityLivingBase.class, arrow.getEntityBoundingBox().grow(30, 1, 30), PredicateUtil.IS_HOSTILE_MOB)) {
				entity.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 200, 0, true, false));
			}
		}
	}

	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.SunshineBow.desc.1", Enums.ItemDescriptionType.POSITIVE));
		super.addInformation(stack, world, tooltip, flag);
	}
}