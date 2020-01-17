package net.tslat.aoa3.item.tool.pickaxe;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.MaterialsRegister;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.WorldUtil;

import java.util.List;

public class Pickmax extends BasePickaxe {
	public Pickmax() {
		super("Pickmax", "pickmax", MaterialsRegister.TOOL_PICKMAX);
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState blockState, BlockPos pos, EntityLivingBase entity) {
		super.onBlockDestroyed(stack, world, blockState, pos, entity);

		if (!world.isRemote && entity instanceof EntityPlayer && !(entity instanceof FakePlayer) && blockState.getMaterial() == Material.ROCK && blockState.isOpaqueCube()) {
			for (int i = pos.getX() - 1; i < pos.getX() + 2; i++) {
				for (int j = pos.getY() - 1; j < pos.getY() + 2; j++) {
					for (int k = pos.getZ() - 1; k < pos.getZ() + 2; k++) {
						if (pos.getX() == i && pos.getY() == j && pos.getZ() == k)
							continue;

						BlockPos breakPos = new BlockPos(i, j, k);
						IBlockState state = world.getBlockState(breakPos);

						if (state.getMaterial() == Material.ROCK && state.isOpaqueCube())
							WorldUtil.harvestAdditionalBlock(world, (EntityPlayer)entity, stack, pos, breakPos);
					}
				}
			}
		}

		return true;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.Pickmax.desc.1", Enums.ItemDescriptionType.POSITIVE));
	}
}
