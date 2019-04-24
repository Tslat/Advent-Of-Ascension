package net.nevermine.block.generation.structure;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.boss.dracyon.EntityDracyon;
import net.nevermine.izer.Itemizer;

import java.util.List;

public class DracyonAltar extends Block {
	private static Block.SoundType rck;

	public DracyonAltar(final Material p_i45394_1_) {
		super(p_i45394_1_);
		setCreativeTab(Itemizer.GenerationTab);
		setHardness(-1.0f);
		setResistance(999999999f);
		setSoundType(DracyonAltar.rck);
	}

	public boolean onBlockActivated(final World w, final int x, final int y, final int z, final EntityPlayer p, final int var6, final float var7, final float var8, final float var9) {
		if (!w.isRemote && !p.isSneaking() && p.getHeldItem() != null && p.getHeldItem().getItem() == Itemizer.CallOfTheDrake) {
			if (w.difficultySetting == EnumDifficulty.PEACEFUL) {
				p.addChatMessage(StringUtil.getLocale("message.feedback.spawnBoss.fail"));
				return false;
			}

			if (p.inventory.consumeInventoryItem(Itemizer.CallOfTheDrake)) {
				final EntityDracyon var10 = new EntityDracyon(w);
				var10.setLocationAndAngles((double)x, (double)(y + 3), (double)z, 0.0f, 0.0f);
				w.spawnEntityInWorld(var10);

				IChatComponent msg = StringUtil.getLocaleWithArguments("message.mob.dracyon.spawn", p.getDisplayName());

				for (final EntityPlayer e : (List<EntityPlayer>)p.worldObj.getEntitiesWithinAABB(EntityPlayer.class, p.boundingBox.expand(50.0, 50.0, 50.0))) {
					e.addChatMessage(msg);
				}
			}
		}
		return true;
	}

	public Block setSoundType(final Block.SoundType name) {
		return setStepSound(name);
	}

	static {
		DracyonAltar.rck = Block.soundTypeStone;
	}
}
