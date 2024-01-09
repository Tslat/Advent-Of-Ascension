package net.tslat.aoa3.advancement.trigger;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.ExtraCodecs;
import net.tslat.aoa3.common.registration.AoAAdvancementTriggers;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.player.skill.AoASkill;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;


public final class AoALevelUpTrigger extends SimpleCriterionTrigger<AoALevelUpTrigger.Instance> {
	@Override
	public Codec<Instance> codec() {
		return Instance.CODEC;
	}

	public void trigger(ServerPlayer player, AoASkill skill, int newLevel) {
		trigger(player, instance -> instance.test(skill, newLevel));
	}

	public static Criterion<Instance> onLevelUp(@Nullable ContextAwarePredicate conditions, @Nullable AoASkill skill, Optional<Integer> level) {
		return AoAAdvancementTriggers.LEVEL_UP.get().createCriterion(new Instance(Optional.ofNullable(conditions), skill != null ? Optional.of(skill) : Optional.empty(), level));
	}

	public static Criterion<Instance> onLevelUp(@Nullable AoASkill skill, Optional<Integer> level) {
		return onLevelUp(null, skill, level);
	}

	public static Criterion<Instance> onLevelUp(@Nullable AoASkill skill) {
		return onLevelUp(skill, Optional.empty());
	}

	public static Criterion<Instance> onLevelUp(Optional<Integer> level) {
		return onLevelUp(null, level);
	}

	public record Instance(Optional<ContextAwarePredicate> player, Optional<AoASkill> skill, Optional<Integer> levelReq) implements SimpleCriterionTrigger.SimpleInstance {
		private static final Codec<Instance> CODEC = RecordCodecBuilder.create(codec -> codec.group(
				ExtraCodecs.strictOptionalField(EntityPredicate.ADVANCEMENT_CODEC, "player").forGetter(Instance::player),
				ExtraCodecs.strictOptionalField(AoARegistries.AOA_SKILLS.lookupCodec(), "skill").forGetter(Instance::skill),
				ExtraCodecs.strictOptionalField(Codec.intRange(1, 1000), "level").forGetter(Instance::levelReq)
		).apply(codec, Instance::new));

		public boolean test(AoASkill skill, int level) {
			return (skill().isEmpty() || skill().get() == skill) && (levelReq().isEmpty() || level >= levelReq().get());
		}

		@Override
		public Optional<ContextAwarePredicate> player() {
			return Optional.empty();
		}
	}
}
