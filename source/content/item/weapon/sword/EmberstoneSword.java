package net.tslat.aoa3.content.item.weapon.sword;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.tslat.aoa3.content.item.LootModifyingItem;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.particle.ParticleBuilder;

import java.util.List;
import java.util.Optional;

public class EmberstoneSword extends AoASword implements LootModifyingItem {
	public EmberstoneSword(Tier tier, Item.Properties properties) {
		super(tier, properties);
	}

	@Override
	public void doLootModification(List<ItemStack> existingLoot, LootContext lootContext) {
		if (!isEntityKillLoot(lootContext))
			return;

		ServerLevel level = lootContext.getLevel();
		Entity entity = lootContext.getParam(LootContextParams.THIS_ENTITY);

		for (int i = 0; i < existingLoot.size(); i++) {
			ItemStack lootStack = existingLoot.get(i);

			Optional<RecipeHolder<SmeltingRecipe>> smeltRecipe = level.getRecipeManager().getRecipeFor(RecipeType.SMELTING, new SingleRecipeInput(lootStack), level);
			final int stackIndex = i;

			smeltRecipe.ifPresent(holder -> {
				existingLoot.set(stackIndex, smeltRecipe.get().value().getResultItem(lootContext.getLevel().registryAccess()));

				ParticleBuilder.forRandomPosInEntity(ParticleTypes.FLAME, entity)
						.spawnNTimes(5)
						.sendToAllPlayersTrackingEntity(entity);
			});
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
		tooltipComponents.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
