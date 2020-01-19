package net.tslat.aoa3.item.weapon.bow;

import net.minecraft.block.state.IBlockState;
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

public class ExplosiveBow extends BaseBow {
	public ExplosiveBow(double damage, float drawSpeedMultiplier, int durability) {
		super(damage, drawSpeedMultiplier, durability);
		setTranslationKey("ExplosiveBow");
		setRegistryName("aoa3:explosive_bow");
	}

	@Override
	public void doImpactEffect(EntityHollyArrow arrow, Entity target, Entity shooter, float damage) {
		if (arrow.getIsCritical())
			WorldUtil.createExplosion(shooter, arrow.world, arrow, 2.0f);
	}

	@Override
	public void doBlockImpact(EntityHollyArrow arrow, IBlockState blockState, Entity shooter) {
		if (arrow.getIsCritical())
			WorldUtil.createExplosion(shooter, arrow.world, arrow, 2.5f);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.ExplosiveBow.desc.1", Enums.ItemDescriptionType.POSITIVE));
		super.addInformation(stack, world, tooltip, flag);
	}
}
