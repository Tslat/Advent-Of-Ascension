package net.tslat.aoa3.item.minionslab;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.minion.AoAMinion;
import net.tslat.aoa3.entity.minion.MechaCyclopsEntity;

public class MechaCyclopsSlab extends BaseSlab {
	public MechaCyclopsSlab() {
		super(78, 160, 78, 4500);
	}

	@Override
	public AoAMinion activateSlab(PlayerEntity pl, ItemStack stack) {
		MechaCyclopsEntity mechaCyclops = new MechaCyclopsEntity(AoAEntities.Minions.MECHA_CYCLOPS.get(), pl.world);

		mechaCyclops.setPositionAndUpdate(pl.getPosX(), pl.getPosY(), pl.getPosZ());
		mechaCyclops.setTamedBy(pl);
		pl.world.addEntity(mechaCyclops);

		return mechaCyclops;
	}
}
