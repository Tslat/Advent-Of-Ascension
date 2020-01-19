package net.tslat.aoa3.item.weapon.bow;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.entity.projectiles.arrow.EntityHollyArrow;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.WorldUtil;

import java.util.List;

public class BoreicBow extends BaseBow {
	public BoreicBow(double damage, float drawSpeedMultiplier, int durability) {
		super(damage, drawSpeedMultiplier, durability);
		setTranslationKey("BoreicBow");
		setRegistryName("aoa3:boreic_bow");
	}

	@Override
	public void doArrowTick(EntityHollyArrow arrow, Entity shooter) {
		if (arrow.isInWater()) {
			if (!arrow.world.isRemote)
				WorldUtil.createExplosion(shooter, arrow.world, arrow, arrow.getIsCritical() ? 2.0f : 1.0f);

			arrow.setDead();
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.BoreicBow.desc.1", Enums.ItemDescriptionType.POSITIVE));
		super.addInformation(stack, world, tooltip, flag);
	}
}
