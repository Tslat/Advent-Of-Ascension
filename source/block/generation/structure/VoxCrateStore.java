package net.nevermine.block.generation.structure;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.izer.Itemizer;
import net.nevermine.npc.entity.EntityStoreKeeper;

public class VoxCrateStore extends Block {
	private static Block.SoundType rck;

	public VoxCrateStore(final Material p_i45394_1_) {
		super(p_i45394_1_);
		setCreativeTab(Itemizer.GenerationTab);
		setHardness(5.0f);
		setResistance(0.5f);
		setSoundType(VoxCrateStore.rck);
	}

	public boolean onBlockActivated(final World w, final int x, final int y, final int z, final EntityPlayer p, final int var6, final float var7, final float var8, final float var9) {
		if (!p.isSneaking() && p.inventory.consumeInventoryItem(Itemizer.SilverCoin)) {
			if (!w.isRemote) {
				final EntityStoreKeeper var10 = new EntityStoreKeeper(w);
				var10.setLocationAndAngles((double)x, (double)(y + 3), (double)z, 0.0f, 0.0f);
				w.spawnEntityInWorld(var10);
			}
			if (w.isRemote) {
				p.addChatMessage(StringUtil.getLocale("message.mob.voxStoreKeeper.spawn"));
			}
		}
		return true;
	}

	public Block setSoundType(final Block.SoundType name) {
		return setStepSound(name);
	}

	static {
		VoxCrateStore.rck = Block.soundTypeStone;
	}
}
