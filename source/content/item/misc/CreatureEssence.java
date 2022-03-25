package net.tslat.aoa3.content.item.misc;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class CreatureEssence extends Item {
	public CreatureEssence() {
		super(new Item.Properties().rarity(Rarity.RARE).tab(null).fireResistant());
	}

	@Nullable
	public EntityType<?> getStoredEntity(ItemStack stack) {
		if (!stack.hasTag())
			return null;

		CompoundNBT tag = stack.getTag();

		if (!tag.contains("essence_entity",Constants.NBT.TAG_STRING))
			return null;

		ResourceLocation entityId = new ResourceLocation(tag.getString("essence_entity"));

		return ForgeRegistries.ENTITIES.containsKey(entityId) ? ForgeRegistries.ENTITIES.getValue(entityId) : null;
	}

	@Nonnull
	public String getStoredEntityName(ItemStack stack) {
		String entityName = "?";

		if (stack.hasTag()) {
			CompoundNBT tag = stack.getTag();

			if (tag.contains("essence_entity_name"))
				entityName = tag.getString("essence_entity_name");
		}

		return entityName;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World level, List<ITextComponent> tooltip, ITooltipFlag tooltipType) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO, 1, new StringTextComponent(getStoredEntityName(stack))));
	}
}
