package net.tslat.aoa3.player.ability.imbuing;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.content.recipe.ImbuingRecipe;
import net.tslat.aoa3.event.dynamic.DynamicEventSubscriber;
import net.tslat.aoa3.player.ability.AoAAbility;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.EnchantmentUtil;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ImbuingLevelRestriction extends AoAAbility.Instance {
	private final ResourceLocation recipeId;
	private Optional<ImbuingRecipe> cachedRecipe = null;

	public ImbuingLevelRestriction(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.IMBUING_LEVEL_RESTRICTION.get(), skill, data);

		this.recipeId = ResourceLocation.read(GsonHelper.getAsString(data, "recipe_id")).getOrThrow();
	}

	public ImbuingLevelRestriction(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.IMBUING_LEVEL_RESTRICTION.get(), skill, data);

		this.recipeId = ResourceLocation.read(data.getString("recipe_id")).getOrThrow();
	}

	@Override
	public List<DynamicEventSubscriber<?>> getEventSubscribers() {
		return Collections.emptyList();
	}

	@Override
	protected void updateDescription(MutableComponent defaultDescription) {
		if (this.cachedRecipe == null)
			this.cachedRecipe = (Optional<ImbuingRecipe>)getPlayer().level().getRecipeManager().byKey(this.recipeId).filter(holder -> holder.value() instanceof ImbuingRecipe).map(RecipeHolder::value);

		super.updateDescription(Component.translatable(((TranslatableContents)defaultDescription.getContents()).getKey(), this.cachedRecipe.map(recipe -> EnchantmentUtil.getFormattedName(recipe.getEnchant().left(), recipe.getEnchant().rightInt())).orElseGet(() -> Component.literal(this.recipeId.getPath()))));
	}

	@Override
	public CompoundTag getSyncData(boolean forClientSetup) {
		CompoundTag data = super.getSyncData(forClientSetup);

		if (forClientSetup)
			data.putString("recipe_id", this.recipeId.toString());

		return data;
	}

	// Client Only
	@Override
	public boolean onGuiHover(int mouseX, int mouseY) {
		return false;
	}

	// Client Only
	@Override
	public boolean onGuiClick(int mouseX, int mouseY) {
		return false;
	}
}
