package net.nevermine.block.generation.structure;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.boss.craexxeus.EntityCraexxeus;
import net.nevermine.izer.Itemizer;

import java.util.List;

public class CraexxeusAltar extends Block {
	private static SoundType rck = Block.soundTypeStone;

	public CraexxeusAltar(net.minecraft.block.material.Material p_i45394_1_) {
		super(p_i45394_1_);
		setCreativeTab(Itemizer.GenerationTab);
		setHardness(-1.0F);
		setResistance(999999999F);
		setSoundType(rck);
	}

	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int var6, float var7, float var8, float var9) {
		if (!w.isRemote && !p.isSneaking() && p.getHeldItem() != null && p.getHeldItem().getItem() == Itemizer.AncientRing) {
			if (w.difficultySetting == EnumDifficulty.PEACEFUL) {
				p.addChatMessage(StringUtil.getLocale("message.feedback.spawnBoss.fail"));
				return false;
			}

			if (p.inventory.consumeInventoryItem(Itemizer.AncientRing)) {
				EntityCraexxeus var2 = new EntityCraexxeus(w, x, y, z);
				var2.setLocationAndAngles(x, y + 1, z, 0.0F, 0.0F);
				w.spawnEntityInWorld(var2);

				IChatComponent msg = StringUtil.getLocaleWithArguments("message.mob.craexxeus.spawn", p.getDisplayName());

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
