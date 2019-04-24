package net.tslat.aoa3.item.weapon.blaster;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.entity.projectiles.blaster.EntityHeavyShowerShot;
import net.tslat.aoa3.entity.projectiles.blaster.EntityShowerShot;
import net.tslat.aoa3.entity.projectiles.blaster.EntityWeightedShowerShot;
import net.tslat.aoa3.entity.projectiles.staff.BaseEnergyShot;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

public class SpiritShower extends BaseBlaster {
	public SpiritShower(double dmg, SoundEvent sound, int durability, int fireDelayTicks, float energyCost) {
		super(dmg, sound, durability, fireDelayTicks, energyCost);
		setUnlocalizedName("SpiritShower");
		setRegistryName("aoa3:spirit_shower");
	}

	@Override
	public void fire(ItemStack blaster, EntityLivingBase shooter) {
		shooter.world.spawnEntity(new EntityShowerShot(shooter, this, 60));
		shooter.world.spawnEntity(new EntityWeightedShowerShot(shooter, this, 60));
		shooter.world.spawnEntity(new EntityHeavyShowerShot(shooter, this, 60));
	}

	@Override
	public void doBlockImpact(BaseEnergyShot shot, BlockPos block, EntityLivingBase shooter) {
		shot.world.createExplosion(shooter, shot.posX, shot.posY, shot.posZ, 2.5f, false);
	}

	@Override
	public void doEntityImpact(BaseEnergyShot shot, Entity target, EntityLivingBase shooter) {
		super.doEntityImpact(shot, target, shooter);

		shot.world.createExplosion(shooter, shot.posX, shot.posY, shot.posZ, 2.5f, false);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("item.ApocoShower.desc.1", TextFormatting.DARK_GREEN));
		super.addInformation(stack, world, tooltip, flag);
	}
}
