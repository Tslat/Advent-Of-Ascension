package net.nevermine.block.generation.structure;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.boss.fourguardians.EntityGuardianBlue;
import net.nevermine.boss.fourguardians.EntityGuardianGreen;
import net.nevermine.boss.fourguardians.EntityGuardianRed;
import net.nevermine.boss.fourguardians.EntityGuardianYellow;
import net.nevermine.izer.Itemizer;

import java.util.List;

public class GuardianAltar extends Block {
	@SideOnly(Side.CLIENT)
	private IIcon top;
	@SideOnly(Side.CLIENT)
	private IIcon side;
	@SideOnly(Side.CLIENT)
	private IIcon bottom;

	public GuardianAltar(final Material p_i45394_1_) {
		super(p_i45394_1_);
		setCreativeTab(Itemizer.TablesTab);
		setHardness(-1.0f);
		setResistance(999999999f);
		setLightLevel(0.0f);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(final int par1, final int par2) {
		return (par1 == 1) ? top : ((par1 == 0) ? top : ((par2 == 2 && par1 == 2) ? side : ((par2 == 3 && par1 == 5) ? side : ((par2 == 0 && par1 == 3) ? side : ((par2 == 1 && par1 == 4) ? side : blockIcon)))));
	}

	public boolean onBlockActivated(final World w, final int x, final int y, final int z, final EntityPlayer p, final int var6, final float var7, final float var8, final float var9) {
		if (!w.isRemote && !p.isSneaking() && p.getHeldItem() != null && p.getHeldItem().getItem() == Itemizer.VoliantHeart) {
			if (w.difficultySetting == EnumDifficulty.PEACEFUL) {
				p.addChatMessage(StringUtil.getLocale("message.feedback.spawnBoss.fail"));
				return false;
			}

			if (p.inventory.consumeInventoryItem(Itemizer.VoliantHeart)) {
				final EntityGuardianRed var10 = new EntityGuardianRed(w);
				var10.setLocationAndAngles((double)x, (double)(y + 3), (double)z, 0.0f, 0.0f);
				w.spawnEntityInWorld(var10);
				final EntityGuardianYellow var11 = new EntityGuardianYellow(w);
				var11.setLocationAndAngles((double)x, (double)(y + 4), (double)z, 0.0f, 0.0f);
				w.spawnEntityInWorld(var11);
				final EntityGuardianGreen var12 = new EntityGuardianGreen(w);
				var12.setLocationAndAngles((double)x, (double)(y + 5), (double)z, 0.0f, 0.0f);
				w.spawnEntityInWorld(var12);
				final EntityGuardianBlue var13 = new EntityGuardianBlue(w);
				var13.setLocationAndAngles((double)x, (double)(y + 2), (double)z, 0.0f, 0.0f);
				w.spawnEntityInWorld(var13);

				IChatComponent msg = StringUtil.getLocaleWithArguments("message.mob.guardians.spawn", p.getDisplayName());

				for (final EntityPlayer e : (List<EntityPlayer>)p.worldObj.getEntitiesWithinAABB(EntityPlayer.class, p.boundingBox.expand(50.0, 50.0, 50.0))) {
					e.addChatMessage(msg);
				}
			}
		}
		return true;
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(final IIconRegister icon) {
		top = icon.registerIcon("nevermine:guardianAltar_top");
		side = icon.registerIcon("nevermine:guardianAltar_side");
		bottom = icon.registerIcon("nevermine:guardianAltar_bottom");
		blockIcon = icon.registerIcon("nevermine:guardianAltar_side");
	}
}
