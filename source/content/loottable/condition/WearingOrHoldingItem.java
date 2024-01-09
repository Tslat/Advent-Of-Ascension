package net.tslat.aoa3.content.loottable.condition;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParam;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.tslat.aoa3.common.registration.loot.AoALootConditions;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.Set;

public record WearingOrHoldingItem(LootContext.EntityTarget target, ItemPredicate predicate, Optional<EquipmentSlot> slot) implements LootItemCondition {
	public static final Codec<WearingOrHoldingItem> CODEC = RecordCodecBuilder.create(builder -> builder.group(
					LootContext.EntityTarget.CODEC.fieldOf("target").forGetter(WearingOrHoldingItem::target),
					ItemPredicate.CODEC.fieldOf("predicate").forGetter(WearingOrHoldingItem::predicate),
					StringRepresentable.fromEnum(EquipmentSlot::values).optionalFieldOf("slot").forGetter(WearingOrHoldingItem::slot))
			.apply(builder, WearingOrHoldingItem::new));

	@Override
	public LootItemConditionType getType() {
		return AoALootConditions.HOLDING_ITEM.get();
	}

	public static LootItemCondition.Builder checkingForSlot(LootContext.EntityTarget entity, ItemPredicate predicate, @Nullable EquipmentSlot slot) {
		return () -> new WearingOrHoldingItem(entity, predicate, Optional.ofNullable(slot));
	}

	public static LootItemCondition.Builder checking(LootContext.EntityTarget entity, ItemPredicate predicate) {
		return checkingForSlot(entity, predicate, null);
	}

	@Override
	public Set<LootContextParam<?>> getReferencedContextParams() {
		return Set.of(this.target.getParam());
	}

	@Override
	public boolean test(LootContext lootContext) {
		if (lootContext.getParamOrNull(this.target.getParam()) instanceof LivingEntity entity) {
			return this.slot.map(specifiedSlot -> this.predicate.matches(entity.getItemBySlot(specifiedSlot))).orElseGet(() -> {
				for (EquipmentSlot slot : EquipmentSlot.values()) {
					if (this.predicate.matches(entity.getItemBySlot(slot)))
						return true;
				}

				return false;
			});
		}

		return false;
	}
}
