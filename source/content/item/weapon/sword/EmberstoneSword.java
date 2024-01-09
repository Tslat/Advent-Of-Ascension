package net.tslat.aoa3.content.item.weapon.sword;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.tslat.aoa3.common.registration.item.AoATiers;
import net.tslat.aoa3.content.item.LootModifyingItem;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.smartbrainlib.util.RandomUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class EmberstoneSword extends BaseSword implements LootModifyingItem {
	public EmberstoneSword() {
		super(AoATiers.EMBERSTONE);
	}

	@Override
	public void doLootModification(List<ItemStack> existingLoot, LootContext lootContext) {
		if (!isEntityKillLoot(lootContext))
			return;

		ServerLevel level = lootContext.getLevel();
		Entity entity = lootContext.getParam(LootContextParams.THIS_ENTITY);
		double posX = entity.getX() + entity.getBbWidth() / 2f;
		double posY = entity.getY() + entity.getBbHeight() / 2f;
		double posZ = entity.getZ() + entity.getBbWidth() / 2f;

		for (int i = 0; i < existingLoot.size(); i++) {
			ItemStack lootStack = existingLoot.get(i);

			Optional<RecipeHolder<SmeltingRecipe>> smeltRecipe = level.getRecipeManager().getRecipeFor(RecipeType.SMELTING, new SimpleContainer(lootStack), level);
			final int stackIndex = i;

			smeltRecipe.ifPresent(holder -> {
				existingLoot.set(stackIndex, smeltRecipe.get().value().getResultItem(lootContext.getLevel().registryAccess()));

				for (int x = 0; x < 5; x++) {
					level.sendParticles(ParticleTypes.FLAME, posX + RandomUtil.randomValueUpTo(1), posY + RandomUtil.randomValueUpTo(1), posZ + RandomUtil.randomValueUpTo(1), 1, 0, 0, 0, 0);
				}
			});
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
		tooltipComponents.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
