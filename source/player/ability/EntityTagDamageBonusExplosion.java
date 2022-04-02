package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.player.skill.AoASkill;

public class EntityTagDamageBonusExplosion extends EntityTagDamageBonus {
	public EntityTagDamageBonusExplosion(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.ENTITY_TAG_DAMAGE_BONUS_EXPLOSION.get(), skill, data);
	}

	public EntityTagDamageBonusExplosion(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.ENTITY_TAG_DAMAGE_BONUS_EXPLOSION.get(), skill, data);
	}

	@Override
	protected void updateDescription(TranslatableComponent defaultDescription) {
		defaultDescription = new TranslatableComponent(defaultDescription.getKey() + ".explosion", getScalingDescriptionComponent(4), new TextComponent(this.tag.location().toString()));

		super.updateDescription(defaultDescription);
	}

	@Override
	public void handleOutgoingAttack(LivingHurtEvent ev) {
		if (ev.getEntityLiving().getType().is(tag) && ev.getSource().isExplosion())
			ev.setAmount(ev.getAmount() * (1 + getScaledValue()));
	}
}
