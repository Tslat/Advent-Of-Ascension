package net.tslat.aoa3.item.tool.shovel;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.world.BlockEvent;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.item.tool.SpecialHarvestTool;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class EmberstoneShovel extends BaseShovel implements SpecialHarvestTool {
	public EmberstoneShovel() {
		super(ItemUtil.customItemTier(2000, 10.0f, 5.5f, 5, 10, AoAItems.EMBERSTONE_INGOT));
	}

	public void doHarvestEffect(BlockEvent.HarvestDropsEvent e) {
		if (!e.getWorld().isRemote()) {
			if (e.getDrops().isEmpty())
				return;

			Optional<FurnaceRecipe> smeltRecipe = ((ServerWorld)e.getWorld()).getRecipeManager().getRecipe(IRecipeType.SMELTING, new Inventory(e.getDrops().get(0)), (World)e.getWorld());

			if (smeltRecipe.isPresent()) {
				ItemStack smeltedStack = smeltRecipe.get().getRecipeOutput();
				int xp = (int)smeltRecipe.get().getExperience();

				e.getDrops().set(0, smeltedStack.copy());
				e.getState().getBlock().dropXpOnBlockBreak(e.getHarvester().world, e.getPos(), xp);
			}
		}
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
