package net.tslat.aoa3.item.weapon.blaster;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ItemUtil;

import javax.annotation.Nullable;
import java.util.List;

public class VortexBlaster extends BaseBlaster {
	public VortexBlaster(double dmg, int durability, int fireDelayTicks, float energyCost) {
		super(dmg, durability, fireDelayTicks, energyCost);
		setTranslationKey("VortexBlaster");
		setRegistryName("aoa3:vortex_blaster");
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundsRegister.GRAVITY_BLASTER_FIRE;
	}

	@Override
	public void fire(ItemStack blaster, EntityLivingBase shooter) {
		float x = -MathHelper.sin(shooter.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(shooter.rotationPitch / 180.0F * (float)Math.PI);
		float y = -MathHelper.sin(shooter.rotationPitch / 180.0F * (float)Math.PI);
		float z = MathHelper.cos(shooter.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(shooter.rotationPitch / 180.0F * (float)Math.PI);

		for (EntityLivingBase entity : shooter.world.getEntitiesWithinAABB(EntityLivingBase.class, shooter.getEntityBoundingBox().grow(x * 7 + 1, y * 7 + 1, z * 7 + 1), entity -> !EntityUtil.isTypeImmune(entity, Enums.MobProperties.BLASTER_IMMUNE))) {
			EntityUtil.doScaledKnockback(entity, shooter, 4f, shooter.posX - entity.posX, shooter.posZ - entity.posZ);
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.VortexBlaster.desc.1", Enums.ItemDescriptionType.POSITIVE));
		super.addInformation(stack, world, tooltip, flag);
	}
}
