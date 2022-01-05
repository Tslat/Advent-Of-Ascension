package net.tslat.aoa3.player.ability;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.data.server.AoASkillReqReloadListener;
import net.tslat.aoa3.player.skill.AoASkill;

public class LevelRestriction extends AoAAbility.Instance {
	private final ListenerType[] LISTENERS = new ListenerType[0];

	private final CompoundNBT restrictionData;

	public LevelRestriction(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.LEVEL_RESTRICTION.get(), skill, data);

		this.restrictionData = new CompoundNBT();
		ListNBT restrictionsList = new ListNBT();
		String restrictingId = generateRestrictionHandlers(data, restrictionsList);

		this.restrictionData.put("restrictions", restrictionsList);
		this.restrictionData.putString("restricted_id", restrictingId);
	}

	public LevelRestriction(AoASkill.Instance skill, CompoundNBT data) {
		super(AoAAbilities.LEVEL_RESTRICTION.get(), skill, data);

		this.restrictionData = data.getCompound("reqs");
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	protected void updateDescription(TranslationTextComponent defaultDescription) {
		ListNBT restrictions = this.restrictionData.getList("restrictions", Constants.NBT.TAG_STRING);
		ITextComponent targetName;

		if (isForBlock(restrictions)) {
			targetName = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(this.restrictionData.getString("restricted_id"))).getName();
		}
		else {
			Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(this.restrictionData.getString("restricted_id")));
			targetName = item.getName(item.getDefaultInstance());
		}

		TranslationTextComponent description = new TranslationTextComponent(defaultDescription.getKey(), targetName);
		boolean comma = false;

		for (INBT restriction : restrictions) {
			if (comma)
				description.append(", ");

			description.append(new TranslationTextComponent(defaultDescription.getKey() + "." + restriction.getAsString()));

			comma = true;
		}

		super.updateDescription(description);
	}

	@Override
	public CompoundNBT getSyncData(boolean forClientSetup) {
		CompoundNBT data = super.getSyncData(forClientSetup);

		if (forClientSetup)
			data.put("reqs", this.restrictionData);

		return data;
	}

	protected String generateRestrictionHandlers(JsonObject json, ListNBT restrictionsList) {
		String restrictedId = JSONUtils.getAsString(json, "restricted_id");
		JsonArray reqs = JSONUtils.getAsJsonArray(json, "restrictions");

		if (reqs.size() == 0)
			throw new IllegalArgumentException("Invalid skill requirements for Level Restriction ability.");

		JsonObject reqData = new JsonObject();

		for (JsonElement element : reqs) {
			JsonObject group = new JsonObject();

			group.addProperty("skill", skill.type().getRegistryName().toString());
			group.addProperty("level", getLevelReq());
			restrictionsList.add(StringNBT.valueOf(element.getAsString()));

			reqData.add(element.getAsString(), group);
		}

		AoASkillReqReloadListener.SkillReqHandler skillReqHandler = AoASkillReqReloadListener.parse(reqData);

		if (!skillReqHandler.isValid())
			throw new IllegalArgumentException("Invalid skill requirements for Level Restriction ability.");

		AoASkillReqReloadListener.addRequirement(new ResourceLocation(restrictedId), skillReqHandler);

		return restrictedId;
	}

	private boolean isForBlock(ListNBT restrictions) {
		for (INBT nbt : restrictions) {
			switch (nbt.getAsString()) {
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
