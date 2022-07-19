package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.tslat.aoa3.common.registration.AoATags;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.player.skill.AoASkill;

public class EntityTagDamageBonus extends ScalableModAbility {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.OUTGOING_ATTACK_DURING};

	protected final TagKey<EntityType<?>> tag;

	public EntityTagDamageBonus(AoASkill.Instance skill, JsonObject data) {
		this(AoAAbilities.ENTITY_TAG_DAMAGE_BONUS.get(), skill, data);
	}

	public EntityTagDamageBonus(AoASkill.Instance skill, CompoundTag data) {
		this(AoAAbilities.ENTITY_TAG_DAMAGE_BONUS.get(), skill, data);
	}

	public EntityTagDamageBonus(AoAAbility ability, AoASkill.Instance skill, JsonObject data) {
		super(ability, skill, data);

		this.tag = AoATags.Entities.create(new ResourceLocation(GsonHelper.getAsString(data, "tag")));
	}

	public EntityTagDamageBonus(AoAAbility ability, AoASkill.Instance skill, CompoundTag data) {
		super(ability, skill, data);

		this.tag = AoATags.Entities.create(new ResourceLocation(data.getString("tag")));
	}

	@Override
	protected void updateDescription(MutableComponent defaultDescription) {
		defaultDescription = Component.translatable(((TranslatableContents)defaultDescription.getContents()).getKey(), getScalingDescriptionComponent(4), Component.literal(this.tag.location().toString()));

		super.updateDescription(defaultDescription);
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleOutgoingAttack(LivingHurtEvent ev) {
		if (ev.getEntity().getType().is(tag))
			ev.setAmount(ev.getAmount() * (1 + getScaledValue()));
	}
}
