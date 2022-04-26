package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.client.ClientOperations;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.SyncAoAAbilityDataPacket;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.event.custom.events.PlayerChangeXpEvent;
import net.tslat.aoa3.library.object.DynamicTextComponent;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.NumberUtil;

public class XpRestrictedFreeItem extends AoAAbility.Instance {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.GAIN_SKILL_XP};

	private final float xpPerItem;
	private final Item item;
	private final int stackSize;
	private float xpRemaining = 50000;

	public XpRestrictedFreeItem(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.XP_RESTRICTED_FREE_ITEM.get(), skill, data);

		this.xpPerItem = JSONUtils.getAsFloat(data, "xp_per_item", 50000);
		this.item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(JSONUtils.getAsString(data, "item")));
		this.stackSize = JSONUtils.getAsInt(data, "amount", 1);
	}

	public XpRestrictedFreeItem(AoASkill.Instance skill, CompoundNBT data) {
		super(AoAAbilities.XP_RESTRICTED_FREE_ITEM.get(), skill, data);

		this.xpPerItem = data.getFloat("xp_per_item");
		this.item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(data.getString("item")));
		this.xpRemaining = data.getFloat("xp_remaining");
		this.stackSize = data.getInt("amount");
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	protected void updateDescription(TranslationTextComponent defaultDescription) {
		if (this.xpRemaining <= 0) {
			super.updateDescription(new TranslationTextComponent(defaultDescription.getKey() + ".ready", this.stackSize, item.getDefaultInstance().getHoverName(), NumberUtil.roundToNthDecimalPlace(this.xpPerItem, 1), new DynamicTextComponent(() -> NumberUtil.roundToNthDecimalPlace(this.xpRemaining, 1))));
		}
		else {
			super.updateDescription(new TranslationTextComponent(defaultDescription.getKey(), this.stackSize, item.getDefaultInstance().getHoverName(), NumberUtil.roundToNthDecimalPlace(this.xpPerItem, 1), new DynamicTextComponent(() -> NumberUtil.roundToNthDecimalPlace(this.xpRemaining, 1))));
		}
	}

	@Override
	public void handleSkillXpGain(PlayerChangeXpEvent ev) {
		if (this.xpRemaining > 0) {
			this.xpRemaining = Math.max(0, this.xpRemaining - ev.getNewXpGain());

			if (xpRemaining == 0)
				updateDescription(new TranslationTextComponent(Util.makeDescriptionId("ability", type().getRegistryName()) + ".description"));

			markForClientSync();
		}
	}

	@Override
	public CompoundNBT getSyncData(boolean forClientSetup) {
		CompoundNBT data = super.getSyncData(forClientSetup);

		if (forClientSetup) {
			data.putFloat("xp_per_item", this.xpPerItem);
			data.putString("item", this.item.getRegistryName().toString());
			data.putInt("amount", this.stackSize);
		}

		data.putFloat("xp_remaining", this.xpRemaining);

		return data;
	}

	@Override
	public void receiveSyncData(CompoundNBT data) {
		super.receiveSyncData(data);

		if (data.contains("xp_remaining")) {
			float xpRemaining = this.xpRemaining;
			this.xpRemaining = Math.max(0, data.getFloat("xp_remaining"));

			if (xpRemaining != this.xpRemaining && (xpRemaining == 0 || this.xpRemaining == this.xpPerItem))
				updateDescription(new TranslationTextComponent(Util.makeDescriptionId("ability", type().getRegistryName()) + ".description"));
		}
	}

	@Override
	public CompoundNBT saveToNbt() {
		CompoundNBT data = super.saveToNbt();

		data.putFloat("xp_remaining", this.xpRemaining);

		return data;
	}

	@Override
	public void loadFromNbt(CompoundNBT data) {
		super.loadFromNbt(data);

		if (data.contains("xp_remaining"))
			this.xpRemaining = Math.max(0, data.getFloat("xp_remaining"));
	}

	@Override
	public void receiveInteractionDataFromClient(String data) {
		if (this.xpRemaining <= 0) {
			ItemUtil.givePlayerItemOrDrop(getPlayer(), new ItemStack(this.item, this.stackSize));

			this.xpRemaining = this.xpPerItem;
			updateDescription(new TranslationTextComponent(Util.makeDescriptionId("ability", type().getRegistryName()) + ".description"));
		}

		markForClientSync();
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public boolean onGuiClick(int mouseX, int mouseY) {
		if (ClientOperations.isPressingCrouchKey()) {
			if (this.xpRemaining <= 0)
				AoAPackets.messageServer(new SyncAoAAbilityDataPacket(this, ""));

			return false;
		}

		return super.onGuiClick(mouseX, mouseY);
	}
}
