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
import net.tslat.aoa3.entity.projectiles.blaster.EntityGoldShot;
import net.tslat.aoa3.entity.projectiles.staff.BaseEnergyShot;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nullable;
import java.util.List;

public class GoldBringer extends BaseBlaster {
	public GoldBringer(double dmg, int durability, int fireDelayTicks, float energyCost) {
		super(dmg, durability, fireDelayTicks, energyCost);
		setTranslationKey("GoldBringer");
		setRegistryName("aoa3:gold_bringer");
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundsRegister.DOOM_GUN_FIRE;
	}

	@Override
	public void fire(ItemStack blaster, EntityLivingBase shooter) {
		shooter.world.spawnEntity(new EntityGoldShot(shooter, this, 60));
	}

	@Override
	public void doBlockImpact(BaseEnergyShot shot, BlockPos block, EntityLivingBase shooter) {
		WorldUtil.createExplosion(shooter, shot.world, shot, 1.25f);
	}

	@Override
	public boolean doEntityImpact(BaseEnergyShot shot, Entity target, EntityLivingBase shooter) {
		WorldUtil.createExplosion(shooter, shot.world, shot, 1.25f);

		return true;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.DoomBringer.desc.1", Enums.ItemDescriptionType.POSITIVE));
		super.addInformation(stack, world, tooltip, flag);
	}
}
