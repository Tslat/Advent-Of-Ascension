package net.tslat.aoa3.library.loot.modifiers;

import com.google.gson.JsonObject;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameters;
import net.minecraft.world.storage.loot.conditions.ILootCondition;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.event.world.BlockEvent;
import net.tslat.aoa3.event.PlayerEvents;

import javax.annotation.Nonnull;
import java.util.List;

public class HarvestDropsLootModifier extends LootModifier {
	protected HarvestDropsLootModifier(ILootCondition[] conditions) {
		super(conditions);
	}

	@Nonnull
	@Override
	protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
		if (!context.has(LootParameters.TOOL) || !context.has(LootParameters.THIS_ENTITY))
			return generatedLoot;

		Entity harvester = context.get(LootParameters.THIS_ENTITY);

		if (!(harvester instanceof PlayerEntity))
			return generatedLoot;

		ItemStack tool = context.get(LootParameters.TOOL);
		NonNullList<ItemStack> loot = NonNullList.create();

		loot.addAll(generatedLoot);

		BlockEvent.HarvestDropsEvent ev = new BlockEvent.HarvestDropsEvent(
				context.getWorld(),
				context.get(LootParameters.POSITION),
				context.get(LootParameters.BLOCK_STATE),
				EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, tool),
				1,
				loot,
				(PlayerEntity)harvester,
				EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, tool) > 0);

		PlayerEvents.onBlockHarvest(ev);

		return ev.getDrops();
	}

	public static class Serializer extends AoALootModifierSerializer<HarvestDropsLootModifier> {
		@Override
		public HarvestDropsLootModifier read(ResourceLocation location, JsonObject object, ILootCondition[] lootConditions) {
			return new HarvestDropsLootModifier(lootConditions == null ? new ILootCondition[]{} : lootConditions);
		}

		@Override
		public void write(JsonObject json) {}
	}
}
