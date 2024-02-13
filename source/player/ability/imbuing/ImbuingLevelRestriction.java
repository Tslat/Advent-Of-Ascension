package net.tslat.aoa3.player.ability.imbuing;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.content.recipe.ImbuingRecipe;
import net.tslat.aoa3.player.ability.AoAAbility;
import net.tslat.aoa3.player.skill.AoASkill;

import java.util.Optional;

public class ImbuingLevelRestriction extends AoAAbility.Instance {
	private final ListenerType[] LISTENERS = new ListenerType[0];

	private final ResourceLocation recipeId;
	private Optional<ImbuingRecipe> cachedRecipe = null;

	public ImbuingLevelRestriction(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.IMBUING_LEVEL_RESTRICTION.get(), skill, data);

		this.recipeId = new ResourceLocation(GsonHelper.getAsString(data, "recipe_id"));
	}

	public ImbuingLevelRestriction(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.IMBUING_LEVEL_RESTRICTION.get(), skill, data);

		this.recipeId = new ResourceLocation(data.getString("recipe_id"));
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	protected void updateDescription(MutableComponent defaultDescription) {
		if (this.cachedRecipe == null)
			this.cachedRecipe = (Optional<ImbuingRecipe>)getPlayer().level().getRecipeManager().byKey(this.recipeId).filter(holder -> holder.value() instanceof ImbuingRecipe).map(RecipeHolder::value);

		super.updateDescription(Component.translatable(((TranslatableContents)defaultDescription.getContents()).getKey(), this.cachedRecipe.map(recipe -> recipe.getEnchant().left().getFullname(recipe.getEnchant().rightInt())).orElseGet(() -> Component.literal(this.recipeId.getPath()))));
	}

	@Override
	public CompoundTag getSyncData(boolean forClientSetup) {
		CompoundTag data = super.getSyncData(forClientSetup);

		if (forClientSetup)
			data.putString("recipe_id", this.recipeId.toString());

		return data;
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public boolean onGuiHover(int mouseX, int mouseY) {
		return false;
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public boolean onGuiClick(int mouseX, int mouseY) {
		return false;
	}
}
