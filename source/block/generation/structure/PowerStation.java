package net.nevermine.block.generation.structure;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.boss.crystocore.EntityCrystocore;
import net.nevermine.izer.Itemizer;

import java.util.List;

public class PowerStation extends Block {
	private static Block.SoundType rck;

	public PowerStation(final Material p_i45394_1_) {
		super(p_i45394_1_);
		setCreativeTab(Itemizer.GenerationTab);
		setHardness(-1.0f);
		setResistance(999999999f);
		setSoundType(PowerStation.rck);
	}

	public boolean onBlockActivated(final World w, final int x, final int y, final int z, final EntityPlayer p, final int var6, final float var7, final float var8, final float var9) {
		if (!w.isRemote && !p.isSneaking() && p.getHeldItem() != null && p.getHeldItem().getItem() == Itemizer.GiantCrystal) {
			if (w.difficultySetting == EnumDifficulty.PEACEFUL) {
				p.addChatMessage(StringUtil.getLocale("message.feedback.spawnBoss.fail"));
				return false;
			}

			if (p.inventory.consumeInventoryItem(Itemizer.GiantCrystal)) {
				final EntityCrystocore var10 = new EntityCrystocore(w);
				var10.setLocationAndAngles((double)x, (double)(y + 2), (double)z, 0.0f, 0.0f);
				w.spawnEntityInWorld(var10);

				for (final EntityPlayer e : (List<EntityPlayer>)p.worldObj.getEntitiesWithinAABB(EntityPlayer.class, p.boundingBox.expand(50.0, 50.0, 50.0))) {
					e.addChatMessage(StringUtil.getLocaleWithArguments("message.mob.crystocore.spawn", p.getDisplayName()));
				}
			}
		}
		return true;
	}

	public Block setSoundType(final Block.SoundType name) {
		return setStepSound(name);
	}

	static {
		PowerStation.rck = Block.soundTypeStone;
	}
}
