package net.tslat.aoa3.item.weapon.blaster;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.projectiles.blaster.EntityAtomizerBounce;
import net.tslat.aoa3.entity.projectiles.blaster.EntityAtomizerShot;
import net.tslat.aoa3.entity.projectiles.staff.BaseEnergyShot;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nullable;
import java.util.List;

public class Atomizer extends BaseBlaster {
	public Atomizer(double dmg, int durability, int fireDelayTicks, float energyCost) {
		super(dmg, durability, fireDelayTicks, energyCost);
		setTranslationKey("Atomizer");
		setRegistryName("aoa3:atomizer");
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundsRegister.gunAtomizer;
	}

	@Override
	public void fire(ItemStack blaster, EntityLivingBase shooter) {
		shooter.world.spawnEntity(new EntityAtomizerShot(shooter, this, 60));
	}

	@Override
	public void doBlockImpact(BaseEnergyShot shot, BlockPos block, EntityLivingBase shooter) {
		if (shot instanceof EntityAtomizerShot) {
			shot.world.spawnEntity(new EntityAtomizerBounce(shooter, this, (EntityAtomizerShot)shot, itemRand.nextGaussian() * 0.5, 1.3, itemRand.nextGaussian() * 0.5));
		}
		else {
			WorldUtil.createExplosion(shooter, shot.world, shot, 1.5f);
		}
	}

	@Override
	public boolean doEntityImpact(BaseEnergyShot shot, Entity target, EntityLivingBase shooter) {
		if (super.doEntityImpact(shot, target, shooter)) {
			WorldUtil.createExplosion(shooter, shot.world, shot, 1.5f);

			if (shot instanceof EntityAtomizerShot)
				shot.world.spawnEntity(new EntityAtomizerBounce(shooter, this, (EntityAtomizerShot)shot, itemRand.nextGaussian() * 0.5, 1.3, itemRand.nextGaussian() * 0.5));

			return true;
		}

		return false;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.Atomizer.desc.1", Enums.ItemDescriptionType.POSITIVE));
		super.addInformation(stack, world, tooltip, flag);
	}
}
