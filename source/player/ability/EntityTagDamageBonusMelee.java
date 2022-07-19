package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.DamageUtil;

public class EntityTagDamageBonusMelee extends EntityTagDamageBonus {
	public EntityTagDamageBonusMelee(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.ENTITY_TAG_DAMAGE_BONUS_MELEE.get(), skill, data);
	}

	public EntityTagDamageBonusMelee(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.ENTITY_TAG_DAMAGE_BONUS_MELEE.get(), skill, data);
	}

	@Override
	protected void updateDescription(MutableComponent defaultDescription) {
		defaultDescription = Component.translatable(((TranslatableContents)defaultDescription.getContents()).getKey() + ".melee", getScalingDescriptionComponent(4), Component.literal(this.tag.location().toString()));

		super.updateDescription(defaultDescription);
	}

	@Override
	public void handleOutgoingAttack(LivingHurtEvent ev) {
		if (ev.getEntity().getType().is(tag) && DamageUtil.isMeleeDamage(ev.getSource()))
			ev.setAmount(ev.getAmount() * (1 + getScaledValue()));
	}
}
