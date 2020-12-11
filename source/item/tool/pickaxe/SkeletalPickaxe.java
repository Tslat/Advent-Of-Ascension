package net.tslat.aoa3.item.tool.pickaxe;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.item.tool.SpecialHarvestTool;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.RandomUtil;

import javax.annotation.Nullable;
import java.util.List;

public class SkeletalPickaxe extends BasePickaxe implements SpecialHarvestTool {
	public SkeletalPickaxe() {
		super(ItemUtil.customItemTier(2000, 10.0f, 6.0f, 5, 10, null));
	}

	public void doHarvestEffect(BlockEvent.HarvestDropsEvent e) {
		if (!e.getWorld().isRemote() && RandomUtil.oneInNChance(3)) {
			int dropChoice = RandomUtil.randomNumberUpTo(50);
			ItemStack drop;

			if (dropChoice == 0) {
				drop = new ItemStack(RandomUtil.getRandomSelection(
						AoAItems.SKULLBONE_FRAGMENT.get(),
						AoAItems.CHESTBONE_FRAGMENT.get(),
						AoAItems.LEGBONE_FRAGMENT.get(),
						AoAItems.FOOTBONE_FRAGMENT.get()
				));
			}
			else if (dropChoice < 10) {
				drop = new ItemStack(Items.BONE_MEAL);
			}
			else {
				drop = new ItemStack(Items.BONE);
			}

			e.getDrops().add(drop);
		}
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.tool.skeletal", LocaleUtil.ItemDescriptionType.UNIQUE));
	}
}
