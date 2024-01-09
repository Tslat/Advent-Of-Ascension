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

public class AoACycleTrigger extends SimpleCriterionTrigger<AoACycleTrigger.Instance> {
	@Override
	public Codec<AoACycleTrigger.Instance> codec() {
		return AoACycleTrigger.Instance.CODEC;
	}

	public void trigger(ServerPlayer player, AoASkill skill, int newLevel) {
		trigger(player, instance -> instance.test(skill, newLevel));
	}

	public static Criterion<AoACycleTrigger.Instance> onSkillCycle(@Nullable ContextAwarePredicate conditions, @Nullable AoASkill skill, Optional<Integer> cycle) {
		return AoAAdvancementTriggers.CYCLE_SKILL.get().createCriterion(new AoACycleTrigger.Instance(Optional.ofNullable(conditions), skill != null ? Optional.of(skill) : Optional.empty(), cycle));
	}

	public static Criterion<AoACycleTrigger.Instance> onSkillCycle(@Nullable AoASkill skill, Optional<Integer> cycle) {
		return onSkillCycle(null, skill, cycle);
	}

	public static Criterion<AoACycleTrigger.Instance> onSkillCycle(@Nullable AoASkill skill) {
		return onSkillCycle(skill, Optional.empty());
	}

	public static Criterion<AoACycleTrigger.Instance> onSkillCycle(Optional<Integer> cycle) {
		return onSkillCycle(null, cycle);
	}

	public record Instance(Optional<ContextAwarePredicate> player, Optional<AoASkill> skill, Optional<Integer> cycle) implements SimpleCriterionTrigger.SimpleInstance {
		private static final Codec<AoACycleTrigger.Instance> CODEC = RecordCodecBuilder.create(codec -> codec.group(
				ExtraCodecs.strictOptionalField(EntityPredicate.ADVANCEMENT_CODEC, "player").forGetter(AoACycleTrigger.Instance::player),
				ExtraCodecs.strictOptionalField(AoARegistries.AOA_SKILLS.lookupCodec(), "skill").forGetter(AoACycleTrigger.Instance::skill),
				ExtraCodecs.strictOptionalField(Codec.intRange(1, 10), "cycle").forGetter(AoACycleTrigger.Instance::cycle)
		).apply(codec, AoACycleTrigger.Instance::new));

		public boolean test(AoASkill skill, int level) {
			return (skill().isEmpty() || skill().get() == skill) && (cycle().isEmpty() || level >= cycle().get());
		}

		@Override
		public Optional<ContextAwarePredicate> player() {
			return Optional.empty();
		}
	}
}
