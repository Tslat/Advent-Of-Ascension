package net.tslat.aoa3.item.weapon.bow;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.entity.projectiles.arrow.EntityHollyArrow;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

public class PredatiousBow extends BaseBow {
	public PredatiousBow(double damage, float drawSpeedMultiplier, int durability) {
		super(damage, drawSpeedMultiplier, durability);
		setUnlocalizedName("PredatiousBow");
		setRegistryName("aoa3:predatious_bow");
	}

	@Override
	public void doImpactEffect(EntityHollyArrow arrow, Entity target, Entity shooter) {
		if (itemRand.nextInt(20) == 0)
			arrow.world.createExplosion(shooter, arrow.posX, arrow.posY, arrow.posZ, 3.0f, false);
	}

	@Override
	public void doBlockImpact(EntityHollyArrow arrow, IBlockState blockState, Entity shooter) {
		if (itemRand.nextInt(20) == 0)
			arrow.world.createExplosion(shooter, arrow.posX, arrow.posY, arrow.posZ, 3.5f, false);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("item.PredatiousBow.desc.1", TextFormatting.DARK_GREEN));
		super.addInformation(stack, world, tooltip, flag);
	}
}