package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.player.skill.AoASkill;

public class EntityTagDamageBonus extends ScalableModAbility {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.OUTGOING_ATTACK_DURING};

	protected final Tags.IOptionalNamedTag<EntityType<?>> tag;

	public EntityTagDamageBonus(AoASkill.Instance skill, JsonObject data) {
		this(AoAAbilities.ENTITY_TAG_DAMAGE_BONUS.get(), skill, data);
	}

	public EntityTagDamageBonus(AoASkill.Instance skill, CompoundNBT data) {
		this(AoAAbilities.ENTITY_TAG_DAMAGE_BONUS.get(), skill, data);
	}

	public EntityTagDamageBonus(AoAAbility ability, AoASkill.Instance skill, JsonObject data) {
		super(ability, skill, data);

		this.tag = EntityTypeTags.createOptional(new ResourceLocation(JSONUtils.getAsString(data, "tag")));

		if (tag.isDefaulted())
			throw new IllegalArgumentException("Invalid tag for EntityTagDamageBonus: '" + tag.getName() + "'");
	}

	public EntityTagDamageBonus(AoAAbility ability, AoASkill.Instance skill, CompoundNBT data) {
		super(ability, skill, data);

		this.tag = EntityTypeTags.createOptional(new ResourceLocation(data.getString("tag")));
	}

	@Override
	protected void updateDescription(TranslationTextComponent defaultDescription) {
		defaultDescription = new TranslationTextComponent(defaultDescription.getKey(), getScalingDescriptionComponent(4), new StringTextComponent(this.tag.getName().toString()));

		super.updateDescription(defaultDescription);
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleOutgoingAttack(LivingHurtEvent ev) {
		if (ev.getEntityLiving().getType().is(tag))
			ev.setAmount(ev.getAmount() * (1 + getScaledValue()));
	}
}
