package net.tslat.aoa3.item.weapon.blaster;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.projectiles.blaster.EntitySwarmShot;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;

import javax.annotation.Nullable;
import java.util.List;

public class Swarmotron extends BaseBlaster {
	public Swarmotron(double dmg, int durability, int fireDelayTicks, float energyCost) {
		super(dmg, durability, fireDelayTicks, energyCost);
		setTranslationKey("Swarmotron");
		setRegistryName("aoa3:swarmotron");
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundsRegister.SWARMOTRON_FIRE;
	}

	@Override
	public void fire(ItemStack blaster, EntityLivingBase shooter) {
		shooter.world.spawnEntity(new EntitySwarmShot(shooter, this, 60, 0, 0, 0));
		shooter.world.spawnEntity(new EntitySwarmShot(shooter, this, 60, -0.125f, 0f, -0.125f));
		shooter.world.spawnEntity(new EntitySwarmShot(shooter, this, 60, -0.125f, 0, 0));
		shooter.world.spawnEntity(new EntitySwarmShot(shooter, this, 60, 0.125f, -0.125f, 0.125f));
		shooter.world.spawnEntity(new EntitySwarmShot(shooter, this, 60, 0.125f, 0.125f, 0.125f));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.Swarmotron.desc.1", Enums.ItemDescriptionType.POSITIVE));
		super.addInformation(stack, world, tooltip, flag);
	}
}
