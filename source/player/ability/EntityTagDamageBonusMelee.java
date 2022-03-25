package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.DamageUtil;

public class EntityTagDamageBonusMelee extends EntityTagDamageBonus {
	public EntityTagDamageBonusMelee(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.ENTITY_TAG_DAMAGE_BONUS_MELEE.get(), skill, data);
	}

	public EntityTagDamageBonusMelee(AoASkill.Instance skill, CompoundNBT data) {
		super(AoAAbilities.ENTITY_TAG_DAMAGE_BONUS_MELEE.get(), skill, data);
	}

	@Override
	protected void updateDescription(TranslationTextComponent defaultDescription) {
		defaultDescription = new TranslationTextComponent(defaultDescription.getKey() + ".melee", getScalingDescriptionComponent(4), new StringTextComponent(this.tag.getName().toString()));

		super.updateDescription(defaultDescription);
	}

	@Override
	public void handleOutgoingAttack(LivingHurtEvent ev) {
		if (ev.getEntityLiving().getType().is(tag) && DamageUtil.isMeleeDamage(ev.getSource()))
			ev.setAmount(ev.getAmount() * (1 + getScaledValue()));
	}
}
