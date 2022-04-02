package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.util.GsonHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.event.custom.events.PlayerLevelChangeEvent;
import net.tslat.aoa3.player.skill.AoASkill;

public class DummyAbility extends AoAAbility.Instance {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.LEVEL_CHANGE};

	private final TranslatableComponent displayName;

	public DummyAbility(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.DUMMY_ABILITY.get(), skill, data);

		this.displayName = new TranslatableComponent(GsonHelper.getAsString(data, "display_name", ""));
	}

	public DummyAbility(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.DUMMY_ABILITY.get(), skill, data);

		this.displayName = new TranslatableComponent(data.getString("display_name"));
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleLevelChange(PlayerLevelChangeEvent ev) {
		if (getListenerState() == ListenerState.ACTIVE && !meetsRequirements())
			disable(ListenerState.DEACTIVATED, false);
	}

	@Override
	public TranslatableComponent getName() {
		return displayName;
	}

	@Override
	public CompoundTag getSyncData(boolean forClientSetup) {
		CompoundTag data = super.getSyncData(forClientSetup);

		if (forClientSetup)
			data.putString("display_name", this.displayName.getKey());

		return data;
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public boolean onGuiHover(int mouseX, int mouseY) {
		return false;
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public boolean onGuiClick(int mouseX, int mouseY) {
		return false;
	}
}
