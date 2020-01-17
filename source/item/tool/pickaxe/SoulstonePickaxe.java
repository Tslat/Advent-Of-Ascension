package net.tslat.aoa3.item.tool.pickaxe;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.block.generation.stone.StoneBlock;
import net.tslat.aoa3.common.registration.MaterialsRegister;
import net.tslat.aoa3.item.tool.SpecialHarvestTool;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.player.PlayerUtil;

import java.util.List;

public class SoulstonePickaxe extends BasePickaxe implements SpecialHarvestTool {
	public SoulstonePickaxe() {
		super("SoulstonePickaxe", "soulstone_pickaxe", MaterialsRegister.TOOL_SOULSTONE);
	}

	@Override
	public void doHarvestEffect(BlockEvent.HarvestDropsEvent e) {
		if (e.getHarvester() != null) {
			Block block = e.getState().getBlock();

			if ((block == Blocks.STONE || block instanceof BlockStone || block instanceof StoneBlock) && PlayerUtil.consumeResource(e.getHarvester(), Enums.Resources.SOUL, 1, false)) {
				ItemStack primaryStack = e.getDrops().get(0);

				primaryStack.setCount(primaryStack.getCount() * 2);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.SoulstonePickaxe.desc.1", Enums.ItemDescriptionType.POSITIVE));
	}
}
