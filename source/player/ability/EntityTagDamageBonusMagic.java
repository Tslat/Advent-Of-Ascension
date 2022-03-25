package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.DamageUtil;

public class EntityTagDamageBonusMagic extends EntityTagDamageBonus {
	public EntityTagDamageBonusMagic(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.ENTITY_TAG_DAMAGE_BONUS_MAGIC.get(), skill, data);
	}

	public EntityTagDamageBonusMagic(AoASkill.Instance skill, CompoundNBT data) {
		super(AoAAbilities.ENTITY_TAG_DAMAGE_BONUS_MAGIC.get(), skill, data);
	}

	@Override
	protected void updateDescription(TranslationTextComponent defaultDescription) {
		defaultDescription = new TranslationTextComponent(defaultDescription.getKey() + ".magic", getScalingDescriptionComponent(4), new StringTextComponent(this.tag.getName().toString()));

		super.updateDescription(defaultDescription);
	}

	@Override
	public void handleOutgoingAttack(LivingHurtEvent ev) {
		if (ev.getEntityLiving().getType().is(tag) && DamageUtil.isMagicDamage(ev.getSource(), ev.getEntityLiving(), ev.getAmount()))
			ev.setAmount(ev.getAmount() * (1 + getScaledValue()));
	}
}
