package net.tslat.aoa3.content.loottable.condition;

import com.mojang.serialization.Codec;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParam;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.tslat.aoa3.common.registration.loot.AoALootConditions;

import java.util.Set;

public record IsBaby() implements LootItemCondition {
	public static final Codec<IsBaby> CODEC = Codec.unit(IsBaby::new);

	@Override
	public LootItemConditionType getType() {
		return AoALootConditions.IS_BABY.get();
	}

	public static LootItemCondition.Builder instance() {
		return IsBaby::new;
	}

	@Override
	public Set<LootContextParam<?>> getReferencedContextParams() {
		return Set.of(LootContextParams.THIS_ENTITY);
	}

	@Override
	public boolean test(LootContext lootContext) {
		Entity entity = lootContext.getParamOrNull(LootContextParams.THIS_ENTITY);

		return entity instanceof AgeableMob ageable && ageable.getAge() < 0;
	}
}
