package net.tslat.aoa3.player.ability.extraction;

import com.google.gson.JsonObject;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.tslat.aoa3.common.registration.AoATags;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.event.custom.events.RetrieveSmeltedItemEvent;
import net.tslat.aoa3.event.dynamic.DynamicEventSubscriber;
import net.tslat.aoa3.player.ability.generic.ScalableModAbility;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.InventoryUtil;

import java.util.List;
import java.util.Random;

public class BonusSmeltResult extends ScalableModAbility {
	private final List<DynamicEventSubscriber<?>> eventSubscribers = List.of(
			listener(RetrieveSmeltedItemEvent.class, serverOnly(this::handleItemSmelting)));

	private final Random random = new Random();
	private final int uniqueIdHash;

	public BonusSmeltResult(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.BONUS_SMELT_RESULT.get(), skill, data);

		this.uniqueIdHash = this.getUniqueIdentifier().hashCode();
	}

	public BonusSmeltResult(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.BONUS_SMELT_RESULT.get(), skill, data);

		this.uniqueIdHash = this.getUniqueIdentifier().hashCode();
	}

	@Override
	public List<DynamicEventSubscriber<?>> getEventSubscribers() {
		return this.eventSubscribers;
	}

	private void handleItemSmelting(final RetrieveSmeltedItemEvent ev) {
		ItemStack baseStack = ev.getOriginalStack();
		ItemStack smeltedStack = ev.getOutputStack();

		if (!smeltedStack.has(DataComponents.FOOD) && !smeltedStack.is(AoATags.Items.NO_BONUS_SMELT_RESULT)) {
			int additional = 0;
			Player player = ev.getEntity();

			this.random.setSeed(this.uniqueIdHash * player.level().getGameTime() - (player.level().isClientSide ? 0 : 1) >> 4);
			this.random.setSeed(this.random.nextLong());
			this.random.setSeed(this.random.nextLong());

			for (int i = 0; i < baseStack.getCount(); i++) {
				if (this.random.nextFloat() < getScaledValue())
					additional++;
			}

			if (additional > 0) {
				int stackSize = smeltedStack.getMaxStackSize();

				while (smeltedStack.getCount() + additional > stackSize) {
					if (player instanceof ServerPlayer pl)
						InventoryUtil.giveItemTo(pl, smeltedStack.copyWithCount(stackSize));

					additional -= stackSize;
				}

				smeltedStack.setCount(smeltedStack.getCount() + additional);
			}
		}
	}
}
