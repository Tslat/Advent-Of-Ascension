package net.tslat.aoa3.item.tool.shovel;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.MaterialsRegister;
import net.tslat.aoa3.item.tool.SpecialHarvestTool;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;

import javax.annotation.Nullable;
import java.util.List;

public class SkeletalShovel extends BaseShovel implements SpecialHarvestTool {
	public SkeletalShovel() {
		super("SkeletalShovel", "skeletal_shovel", MaterialsRegister.TOOL_SKELETAL);
	}

	public void doHarvestEffect(BlockEvent.HarvestDropsEvent e) {
		if (!e.getWorld().isRemote && itemRand.nextInt(3) == 0) {
			int dropChoice = itemRand.nextInt(50);
			ItemStack drop;

			if (dropChoice == 0) {
				Item bone = ItemRegister.boneFragmentSkullbone;

				switch (itemRand.nextInt(4)) {
					case 0:
						bone = ItemRegister.boneFragmentSkullbone;
						break;
					case 1:
						bone = ItemRegister.boneFragmentChestbone;
						break;
					case 2:
						bone = ItemRegister.boneFragmentLegbone;
						break;
					case 3:
						bone = ItemRegister.boneFragmentFootbone;
						break;
				}

				drop = new ItemStack(bone);
			}
			else if (dropChoice < 10) {
				drop = new ItemStack(Items.DYE, 1, 15);
			}
			else {
				drop = new ItemStack(Items.BONE);
			}

			e.getDrops().add(drop);
		}
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("items.description.tool.skeletal", Enums.ItemDescriptionType.UNIQUE));
	}
}
