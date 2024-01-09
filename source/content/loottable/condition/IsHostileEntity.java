package net.tslat.aoa3.content.loottable.condition;

import com.mojang.serialization.Codec;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.tslat.aoa3.common.registration.loot.AoALootConditions;

public record IsHostileEntity() implements LootItemCondition {
	public static final Codec<IsHostileEntity> CODEC = Codec.unit(IsHostileEntity::new);

	@Override
	public LootItemConditionType getType() {
		return AoALootConditions.IS_HOSTILE_ENTITY.get();
	}

	public static LootItemCondition.Builder instance() {
		return IsHostileEntity::new;
	}

	@Override
	public boolean test(LootContext lootContext) {
		Entity entity = lootContext.getParamOrNull(LootContextParams.THIS_ENTITY);

		return entity instanceof Enemy || (entity instanceof NeutralMob neutralMob && neutralMob.isAngry());
	}
}
