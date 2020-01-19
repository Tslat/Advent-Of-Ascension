package net.tslat.aoa3.item.tool.shovel;

import net.minecraft.block.*;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.MaterialsRegister;
import net.tslat.aoa3.item.tool.SpecialHarvestTool;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.player.PlayerUtil;

import java.util.List;

public class SoulstoneShovel extends BaseShovel implements SpecialHarvestTool {
	public SoulstoneShovel() {
		super("SoulstoneShovel", "soulstone_shovel", MaterialsRegister.TOOL_SOULSTONE);
	}

	@Override
	public void doHarvestEffect(BlockEvent.HarvestDropsEvent e) {
		if (e.getHarvester() != null) {
			Block block = e.getState().getBlock();

			if ((block instanceof BlockDirt || block instanceof BlockGrass || block instanceof BlockSand || block instanceof BlockGravel || block instanceof BlockSoulSand || block instanceof BlockClay) && PlayerUtil.consumeResource(e.getHarvester(), Enums.Resources.SOUL, 1, false)) {
				ItemStack primaryStack = e.getDrops().get(0);

				primaryStack.setCount(primaryStack.getCount() * 2);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.SoulstoneShovel.desc.1", Enums.ItemDescriptionType.POSITIVE));
	}
}
