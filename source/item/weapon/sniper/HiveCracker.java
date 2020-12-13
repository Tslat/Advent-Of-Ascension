package net.tslat.aoa3.item.weapon.sniper;

import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.client.gui.overlay.ScopeOverlayRenderer;
import net.tslat.aoa3.common.registration.AoASounds;

import javax.annotation.Nullable;

public class HiveCracker extends BaseSniper {
	public HiveCracker(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_SNIPER_FIRE.get();
	}

	@Override
	public ScopeOverlayRenderer.Type getScopeType() {
		return ScopeOverlayRenderer.Type.DOTTED;
	}
}
