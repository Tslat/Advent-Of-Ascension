package net.tslat.aoa3.item.minionslab;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.entity.minions.AoAMinion;
import net.tslat.aoa3.entity.minions.EntityPenguin;
import net.tslat.aoa3.utils.ModUtil;

public class PenguinSlab extends BaseSlab {
	public PenguinSlab() {
		super("PenguinSlab", "penguin_slab", 1, 120, 1, 9);
	}

	@Override
	public AoAMinion activateSlab(EntityPlayer pl, ItemStack stack) {
		EntityPenguin penguin = new EntityPenguin(pl.world);

		penguin.setPositionAndUpdate(pl.posX, pl.posY, pl.posZ);
		penguin.setTamedBy(pl);
		pl.world.spawnEntity(penguin);

		if (pl instanceof EntityPlayerMP && pl.world.getBiome(pl.getPosition()).isSnowyBiome())
			ModUtil.completeAdvancement((EntityPlayerMP)pl, "overworld/happy_feet", "snowy_penguin_summon");

		return penguin;
	}
}
