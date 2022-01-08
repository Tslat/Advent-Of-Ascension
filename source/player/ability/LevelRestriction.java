package net.tslat.aoa3.player.ability;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.data.server.AoASkillReqReloadListener;
import net.tslat.aoa3.player.skill.AoASkill;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LevelRestriction extends AoAAbility.Instance {
	private final ListenerType[] LISTENERS = new ListenerType[0];

	private final ResourceLocation restrictedId;

	public LevelRestriction(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.LEVEL_RESTRICTION.get(), skill, data);

		this.restrictedId = generateRestrictionHandlers(data);
	}

	public LevelRestriction(AoASkill.Instance skill, CompoundNBT data) {
		super(AoAAbilities.LEVEL_RESTRICTION.get(), skill, data);

		this.restrictedId = new ResourceLocation(data.getString("restriction_id"));
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	protected void updateDescription(TranslationTextComponent defaultDescription) {
		Map<String, List<Pair<ResourceLocation, Integer>>> restrictions = AoASkillReqReloadListener.getParsedReqDataFor(this.restrictedId);

		if (restrictions == null) {
			super.updateDescription(new TranslationTextComponent(defaultDescription.getKey(), "??"));

			return;
		}

		ITextComponent targetName;

		if (isForBlock(restrictions)) {
			targetName = ForgeRegistries.BLOCKS.getValue(restrictedId).getName();
		}
		else {
			Item item = ForgeRegistries.ITEMS.getValue(restrictedId);
			targetName = item.getName(item.getDefaultInstance());
		}

		TranslationTextComponent description = new TranslationTextComponent(defaultDescription.getKey(), targetName);
		boolean comma = false;

		for (Map.Entry<String, List<Pair<ResourceLocation, Integer>>> restriction : restrictions.entrySet()) {
			if (comma)
				description.append(", ");

			description.append(new TranslationTextComponent(defaultDescription.getKey() + "." + restriction.getKey()));

			comma = true;
		}

		super.updateDescription(description);
	}

	@Override
	public CompoundNBT getSyncData(boolean forClientSetup) {
		CompoundNBT data = super.getSyncData(forClientSetup);

		if (forClientSetup)
			data.putString("restriction_id", this.restrictedId.toString());

		return data;
	}

	protected ResourceLocation generateRestrictionHandlers(JsonObject json) {
		ResourceLocation restrictedId = new ResourceLocation(JSONUtils.getAsString(json, "restricted_id"));
		JsonArray reqs = JSONUtils.getAsJsonArray(json, "restrictions");

		if (reqs.size() == 0)
			throw new IllegalArgumentException("Invalid skill requirements for Level Restriction ability.");

		Map<String, List<Pair<ResourceLocation, Integer>>> reqData = new HashMap<String, List<Pair<ResourceLocation, Integer>>>();

		for (JsonElement entry : reqs) {
			reqData.put(entry.getAsJsonPrimitive().getAsString(), Collections.singletonList(Pair.of(skill.type().getRegistryName(), getLevelReq())));
		}

		AoASkillReqReloadListener.SkillReqHandler skillReqHandler = AoASkillReqReloadListener.parse(reqData);

		if (!skillReqHandler.isValid())
			throw new IllegalArgumentException("Invalid skill requirements for Level Restriction ability.");

		AoASkillReqReloadListener.addRequirement(restrictedId, skillReqHandler);
		AoASkillReqReloadListener.addParsedData(restrictedId, reqData);

		return restrictedId;
	}

	private boolean isForBlock(Map<String, List<Pair<ResourceLocation, Integer>>> restrictions) {
		for (String type : restrictions.keySet()) {
			switch (type) {
				case "break_block":
				case "place_block":
				case "interact_with":
					return true;
				default:
					break;
			}
		}

		return false;
	}
}
