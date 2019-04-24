package net.tslat.aoa3.item.weapon.bow;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.entity.projectiles.arrow.EntityHollyArrow;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

public class SkydriverBow extends BaseBow {
	public SkydriverBow(double damage, float drawSpeedMultiplier, int durability) {
		super(damage, drawSpeedMultiplier, durability);
		setUnlocalizedName("SkydriverBow");
		setRegistryName("aoa3:skydriver_bow");
	}

	@Override
	public void doArrowTick(EntityHollyArrow arrow, Entity shooter) {
		if (!arrow.inGround && arrow.ticksExisted > 1) {
			BlockPos.MutableBlockPos testPos = new BlockPos.MutableBlockPos(arrow.getPosition());

			while (arrow.world.isAirBlock(testPos.move(EnumFacing.DOWN))) {
				;
			}

			if (arrow.world.getBlockState(testPos).isFullCube())
				arrow.world.setBlockState(testPos.up(), BlockRegister.orangeAcid.getDefaultState());
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("item.SkydriverBow.desc.1", TextFormatting.DARK_GREEN));
		super.addInformation(stack, world, tooltip, flag);
	}
}
