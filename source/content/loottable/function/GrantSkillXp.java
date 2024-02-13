package net.tslat.aoa3.content.loottable.function;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.common.registration.loot.AoALootFunctions;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.PlayerUtil;

import java.util.List;

public class GrantSkillXp extends LootItemConditionalFunction {
	public static final Codec<GrantSkillXp> CODEC = RecordCodecBuilder.create(builder -> commonFields(builder).and(builder.group(
					ExtraCodecs.lazyInitializedCodec(AoARegistries.AOA_SKILLS::lookupCodec).fieldOf("skill").forGetter(GrantSkillXp::getSkill),
					Codec.FLOAT.fieldOf("xp").forGetter(GrantSkillXp::getXp)))
			.apply(builder, GrantSkillXp::new));

	private final AoASkill skill;
	private final float xp;

	protected GrantSkillXp(List<LootItemCondition> lootConditions, AoASkill skill, float xp) {
		super(lootConditions);

		this.skill = skill;
		this.xp	= xp;
	}

	@Override
	public LootItemFunctionType getType() {
		return AoALootFunctions.GRANT_SKILL_XP.get();
	}

	@Override
	protected ItemStack run(ItemStack stack, LootContext context) {
		Entity entity = context.getParamOrNull(LootContextParams.KILLER_ENTITY);

		if (!(entity instanceof Player))
			entity = context.getParamOrNull(LootContextParams.THIS_ENTITY);

		if (entity instanceof ServerPlayer pl)
			PlayerUtil.giveXpToPlayer(pl, this.skill, xp, false);

		return stack;
	}

	public AoASkill getSkill() {
		return this.skill;
	}

	public float getXp() {
		return this.xp;
	}

	public static Builder<?> builder(AoASkill skill, float xp) {
		return simpleBuilder((conditions) -> new GrantSkillXp(conditions, skill, xp));
	}
}
