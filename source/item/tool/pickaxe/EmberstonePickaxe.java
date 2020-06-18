package net.tslat.aoa3.item.tool.pickaxe;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.tslat.aoa3.common.registration.MaterialsRegister;
import net.tslat.aoa3.item.tool.SpecialHarvestTool;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;

import javax.annotation.Nullable;
import java.util.List;

public class EmberstonePickaxe extends BasePickaxe implements SpecialHarvestTool {
	public EmberstonePickaxe() {
		super("EmberstonePickaxe", "emberstone_pickaxe", MaterialsRegister.TOOL_EMBERSTONE);
	}

	public void doHarvestEffect(BlockEvent.HarvestDropsEvent e) {
		if (!e.getWorld().isRemote) {
			if (e.getDrops().isEmpty())
				return;

			ItemStack smeltedStack = FurnaceRecipes.instance().getSmeltingResult(e.getDrops().get(0));

			if (!smeltedStack.isEmpty()) {
				int xp = (int)FurnaceRecipes.instance().getSmeltingExperience(smeltedStack);

				e.getDrops().set(0, smeltedStack.copy());
				e.getState().getBlock().dropXpOnBlockBreak(e.getHarvester().world, e.getPos(), xp);
			}
		}
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.EmberstonePickaxe.desc.1", Enums.ItemDescriptionType.POSITIVE));
	}
}
