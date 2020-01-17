package net.tslat.aoa3.item.misc;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;

public class FloatingStone extends Item {
	public FloatingStone() {
		setTranslationKey("FloatingStone");
		setRegistryName("aoa3:floating_stone");
		setCreativeTab(CreativeTabsRegister.miscTab);
	}

	@Override
	public boolean onEntityItemUpdate(EntityItem entityItem) {
		if (entityItem.posY < -10) {
			entityItem.setPositionAndUpdate(entityItem.posX, 257, entityItem.posZ);
			entityItem.setItem(new ItemStack(ItemRegister.heavyBoulder, 1));
		}

		return super.onEntityItemUpdate(entityItem);
	}
}
