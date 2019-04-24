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
import net.nevermine.boss.elusive.EntityElusive;
import net.nevermine.izer.Itemizer;

import java.util.List;

public class IllusionAltar extends Block {
	@SideOnly(Side.CLIENT)
	private IIcon top;
	@SideOnly(Side.CLIENT)
	private IIcon side;
	@SideOnly(Side.CLIENT)
	private IIcon bottom;

	public IllusionAltar(final Material p_i45394_1_) {
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
		if (!w.isRemote && !p.isSneaking() && p.getHeldItem() != null && p.getHeldItem().getItem() == Itemizer.StaringEye) {
			if (w.difficultySetting == EnumDifficulty.PEACEFUL) {
				p.addChatMessage(StringUtil.getLocale("message.feedback.spawnBoss.fail"));
				return false;
			}

			if (p.inventory.consumeInventoryItem(Itemizer.StaringEye)) {
				final EntityElusive var10 = new EntityElusive(w);
				var10.setLocationAndAngles((double)x + (Math.signum(p.posX - x) * 6), (double)y + 1, (double)z + (Math.signum(p.posZ - z) * 6), 0.0f, 0.0f);
				w.spawnEntityInWorld(var10);

				IChatComponent msg = StringUtil.getLocaleWithArguments("message.mob.elusive.spawn", p.getDisplayName());

				for (final EntityPlayer e : (List<EntityPlayer>)p.worldObj.getEntitiesWithinAABB(EntityPlayer.class, p.boundingBox.expand(50.0, 50.0, 50.0))) {
					e.addChatMessage(msg);
				}
			}
		}
		return true;
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(final IIconRegister icon) {
		top = icon.registerIcon("nevermine:illusionAltar_top");
		side = icon.registerIcon("nevermine:illusionAltar_side");
		bottom = icon.registerIcon("nevermine:illusionAltar_bottom");
		blockIcon = icon.registerIcon("nevermine:illusionAltar_side");
	}
}
