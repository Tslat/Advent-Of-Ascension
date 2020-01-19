package net.tslat.aoa3.item.tool.pickaxe;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.BlockRedstoneOre;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.MaterialsRegister;
import net.tslat.aoa3.item.tool.SpecialHarvestTool;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;

import java.util.List;

public class Gemcracker extends BasePickaxe implements SpecialHarvestTool {
	public Gemcracker() {
		super("Gemcracker", "gemcracker", MaterialsRegister.TOOL_GEMCRACKER);
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
		return super.onBlockDestroyed(stack, worldIn, state, pos, entityLiving);
	}

	@Override
	public void doHarvestEffect(BlockEvent.HarvestDropsEvent e) {
		Block block = e.getState().getBlock();

		if (!e.getDrops().isEmpty() && (block instanceof BlockOre || block instanceof BlockRedstoneOre)) {
			Item blockDrop = block.getItemDropped(e.getState(), itemRand, 0);
			ItemStack primaryDrop = e.getDrops().get(0);

			if (!(blockDrop instanceof ItemBlock) && blockDrop == primaryDrop.getItem())
				primaryDrop.setCount(primaryDrop.getCount() + block.quantityDropped(itemRand) * (itemRand.nextBoolean() ? 1 : 2));
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.Gemcracker.desc.1", Enums.ItemDescriptionType.POSITIVE));
	}
}
