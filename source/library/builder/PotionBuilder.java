package net.tslat.aoa3.library.builder;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.component.ItemLore;

import java.util.List;
import java.util.Optional;

/**
 * Helper class to make building potion ItemStacks easier.
 */
public final class PotionBuilder {
	private final Item potionItem;
	private final Optional<Holder<Potion>> potion;

	private Optional<Component> displayName = Optional.empty();
	private Optional<List<Component>> lore = Optional.empty();
	private List<MobEffectInstance> effects = new ObjectArrayList<>(1);
	private Optional<Integer> colour = Optional.empty();

	public PotionBuilder(Item potionItem) {
		this.potionItem = potionItem;
		this.potion = Optional.empty();
	}

	public PotionBuilder(Item potionItem, Holder<Potion> potion) {
		this.potionItem = potionItem;
		this.potion = Optional.of(potion);
	}

	/**
	 * Set the literal display name of the potion ItemStack.
	 * @param name The literal name of the stack
	 * @return this
	 */
	public PotionBuilder withName(Component name) {
		this.displayName = Optional.of(name);

		return this;
	}

	/**
	 * Set the 'lore' tooltip lines for the ItemStack
	 *
	 * @param lore The lore lines of the Itemstack
	 * @return this
	 */
	public PotionBuilder withLore(Component... lore) {
		this.lore = Optional.of(List.of(lore));

		return this;
	}

	/**
	 * Add additional effects to the potion ItemStack.
	 * @param effects The effect instance
	 * @return this
	 */
	public PotionBuilder addEffects(MobEffectInstance... effects) {
		if (this.effects == null)
			this.effects = new ObjectArrayList<>(effects.length);

        this.effects.addAll(List.of(effects));

		return this;
	}

	/**
	 * Set the colour of the potion in the stack
	 * <p>
	 * This will override the default automatic computation of the colour from the potion contents
	 *
	 * @param colour The RGB packed colour int
	 * @return this
	 */
	public PotionBuilder withColour(int colour) {
		this.colour = Optional.of(colour);

		return this;
	}

	/**
	 * Build a potion {@link ItemStack} based on the builder's current state.
	 * @return The ItemStack
	 */
	public ItemStack build() {
		final ItemStack stack = new ItemStack(this.potionItem);

		this.displayName.ifPresent(name -> stack.set(DataComponents.CUSTOM_NAME, name));
		this.lore.ifPresent(lore -> stack.set(DataComponents.LORE, new ItemLore(lore)));
		stack.set(DataComponents.POTION_CONTENTS, new PotionContents(this.potion, this.colour, this.effects));

		return stack;
	}
}
