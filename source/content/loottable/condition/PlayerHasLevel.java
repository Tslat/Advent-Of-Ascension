package net.tslat.aoa3.content.loottable.condition;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.common.registration.loot.AoALootConditions;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.PlayerUtil;

public record PlayerHasLevel(AoASkill skill, int level) implements LootItemCondition {
	public static final Codec<PlayerHasLevel> CODEC = RecordCodecBuilder.create(builder -> builder.group(
					ExtraCodecs.lazyInitializedCodec(AoARegistries.AOA_SKILLS::lookupCodec).fieldOf("skill").forGetter(PlayerHasLevel::skill),
					Codec.intRange(1, 1000).fieldOf("level").forGetter(PlayerHasLevel::level))
			.apply(builder, PlayerHasLevel::new));

	@Override
	public LootItemConditionType getType() {
		return AoALootConditions.PLAYER_HAS_LEVEL.get();
	}

	public static LootItemCondition.Builder forSkill(AoASkill skill, int level) {
		return () -> new PlayerHasLevel(skill, level);
	}

	@Override
	public boolean test(LootContext lootContext) {
		Entity entity = lootContext.getParamOrNull(LootContextParams.KILLER_ENTITY);

		if (entity == null)
			entity = lootContext.getParamOrNull(LootContextParams.THIS_ENTITY);

		return entity instanceof ServerPlayer pl && PlayerUtil.doesPlayerHaveLevel(pl, this.skill, this.level);
	}
}
