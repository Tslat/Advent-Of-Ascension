package net.tslat.aoa3.item.weapon.blaster;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.projectile.blaster.SwarmShotEntity;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;

public class Swarmotron extends BaseBlaster {
	public Swarmotron(double dmg, int durability, int fireDelayTicks, float energyCost) {
		super(dmg, durability, fireDelayTicks, energyCost);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_SWARMOTRON_FIRE.get();
	}

	@Override
	public void fire(ItemStack blaster, LivingEntity shooter) {
		shooter.world.addEntity(new SwarmShotEntity(shooter, this, 60, 0, 0, 0));
		shooter.world.addEntity(new SwarmShotEntity(shooter, this, 60, -0.125f, 0f, -0.125f));
		shooter.world.addEntity(new SwarmShotEntity(shooter, this, 60, -0.125f, 0, 0));
		shooter.world.addEntity(new SwarmShotEntity(shooter, this, 60, 0.125f, -0.125f, 0.125f));
		shooter.world.addEntity(new SwarmShotEntity(shooter, this, 60, 0.125f, 0.125f, 0.125f));
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.addInformation(stack, world, tooltip, flag);
	}
}
