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

public class SoulfireBow extends BaseBow {
	public SoulfireBow(double damage, float drawSpeedMultiplier, int durability) {
		super(damage, drawSpeedMultiplier, durability);
		setUnlocalizedName("SoulfireBow");
		setRegistryName("aoa3:soulfire_bow");
	}

	@Override
	public void doImpactEffect(EntityHollyArrow arrow, Entity target, Entity shooter) {
		if (arrow.getIsCritical())
			arrow.world.createExplosion(shooter, arrow.posX, arrow.posY, arrow.posZ, 2.25f, false);
	}

	@Override
	public void doBlockImpact(EntityHollyArrow arrow, IBlockState blockState, Entity shooter) {
		if (arrow.getIsCritical())
			arrow.world.createExplosion(shooter, arrow.posX, arrow.posY, arrow.posZ, 2.75f, false);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("item.SoulfireBow.desc.1", TextFormatting.DARK_GREEN));
		super.addInformation(stack, world, tooltip, flag);
	}
}
