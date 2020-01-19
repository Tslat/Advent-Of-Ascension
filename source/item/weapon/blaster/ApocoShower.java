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
import net.tslat.aoa3.entity.projectiles.blaster.EntityHeavyShowerShot;
import net.tslat.aoa3.entity.projectiles.blaster.EntityShowerShot;
import net.tslat.aoa3.entity.projectiles.blaster.EntityWeightedShowerShot;
import net.tslat.aoa3.entity.projectiles.staff.BaseEnergyShot;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nullable;
import java.util.List;

public class ApocoShower extends BaseBlaster {
	public ApocoShower(double dmg, int durability, int fireDelayTicks, float energyCost) {
		super(dmg, durability, fireDelayTicks, energyCost);
		setTranslationKey("ApocoShower");
		setRegistryName("aoa3:apoco_shower");
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundsRegister.gunSpiritShower;
	}

	@Override
	public void fire(ItemStack blaster, EntityLivingBase shooter) {
		shooter.world.spawnEntity(new EntityShowerShot(shooter, this, 60));
		shooter.world.spawnEntity(new EntityWeightedShowerShot(shooter, this, 60));
		shooter.world.spawnEntity(new EntityHeavyShowerShot(shooter, this, 60));
	}

	@Override
	public void doBlockImpact(BaseEnergyShot shot, BlockPos block, EntityLivingBase shooter) {
		WorldUtil.createExplosion(shooter, shot.world, shot, 2.5f);
	}

	@Override
	protected void doImpactEffect(BaseEnergyShot shot, Entity target, EntityLivingBase shooter) {
		WorldUtil.createExplosion(shooter, shot.world, shot, 2.5f);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.SpiritShower.desc.1", Enums.ItemDescriptionType.POSITIVE));
		super.addInformation(stack, world, tooltip, flag);
	}
}
