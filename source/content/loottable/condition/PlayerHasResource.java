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
import net.tslat.aoa3.player.resource.AoAResource;
import net.tslat.aoa3.util.PlayerUtil;

public record PlayerHasResource(AoAResource resource, float amount, boolean consume) implements LootItemCondition {
	public static final Codec<PlayerHasResource> CODEC = RecordCodecBuilder.create(builder -> builder.group(
					ExtraCodecs.lazyInitializedCodec(AoARegistries.AOA_RESOURCES::lookupCodec).fieldOf("resource").forGetter(PlayerHasResource::resource),
					Codec.FLOAT.fieldOf("amount").forGetter(PlayerHasResource::amount),
					Codec.BOOL.optionalFieldOf("consume", false).forGetter(PlayerHasResource::consume))
			.apply(builder, PlayerHasResource::new));

	@Override
	public LootItemConditionType getType() {
		return AoALootConditions.PLAYER_HAS_RESOURCE.get();
	}

	public static LootItemCondition.Builder forResource(AoAResource resource, float amount) {
		return () -> new PlayerHasResource(resource, amount, false);
	}

	public static LootItemCondition.Builder checkAndConsume(AoAResource resource, float amount) {
		return () -> new PlayerHasResource(resource, amount, true);
	}

	@Override
	public boolean test(LootContext lootContext) {
		Entity entity = lootContext.getParamOrNull(LootContextParams.KILLER_ENTITY);

		if (entity == null)
			entity = lootContext.getParamOrNull(LootContextParams.THIS_ENTITY);

		if (entity instanceof ServerPlayer pl)
			return this.consume ? PlayerUtil.consumeResource(pl, this.resource, this.amount, false) : PlayerUtil.getResourceValue(pl, this.resource) >= this.amount;

		return false;
	}
}
