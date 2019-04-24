package net.nevermine.block.generation.structure;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.boss.tyrosaur.EntityTyrosaur;
import net.nevermine.izer.Itemizer;

import java.util.List;

public class TyrosaurAltar extends Block {
	private static SoundType rck = Block.soundTypeStone;

	public TyrosaurAltar(Material p_i45394_1_) {
		super(p_i45394_1_);
		this.setCreativeTab(Itemizer.GenerationTab);
		this.setHardness(-1f);
		this.setResistance(999999999f);
		setSoundType(rck);

	}

	@Override
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int var6, float var7, float var8, float var9) {
		if (!w.isRemote && !p.isSneaking()) {
			if (w.difficultySetting == EnumDifficulty.PEACEFUL) {
				p.addChatMessage(StringUtil.getLocale("message.feedback.spawnBoss.fail"));
				return false;
			}

			if (p.inventory.consumeInventoryItem(Itemizer.Bloodstone)) {
				EntityTyrosaur var2 = new EntityTyrosaur(w);
				var2.setLocationAndAngles(x, y + 3, z, 0F, 0.0F);
				w.spawnEntityInWorld(var2);

				IChatComponent msg = StringUtil.getLocaleWithArguments("message.mob.tyrosaur.spawn", p.getDisplayName());

				for (final EntityPlayer e : (List<EntityPlayer>)p.worldObj.getEntitiesWithinAABB(EntityPlayer.class, p.boundingBox.expand(50.0, 50.0, 50.0))) {
					e.addChatMessage(msg);
				}
			}
		}
		return true;
	}

	public Block setSoundType(SoundType name) {
		return setStepSound(name);
	}

}
