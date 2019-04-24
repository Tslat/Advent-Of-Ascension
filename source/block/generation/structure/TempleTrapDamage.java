package net.nevermine.block.generation.structure;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.nevermine.izer.Blockizer;
import net.nevermine.izer.Itemizer;

public class TempleTrapDamage extends Block {
	public TempleTrapDamage(final Material p_i45394_1_) {
		super(p_i45394_1_);
		setCreativeTab(Itemizer.GenerationTab);
		setHardness(-1.0f);
		setResistance(999999999f);
		setLightLevel(0.0f);
		setBlockUnbreakable();
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(final World p_149668_1_, final int p_149668_2_, final int p_149668_3_, final int p_149668_4_) {
		final float f = 0.125f;
		return AxisAlignedBB.getBoundingBox((double)p_149668_2_, (double)p_149668_3_, (double)p_149668_4_, (double)(p_149668_2_ + 1), (double)(p_149668_3_ + 1 - f), (double)(p_149668_4_ + 1));
	}

	public void onEntityCollidedWithBlock(final World w, final int x, final int y, final int z, final Entity e) {
		if (w.getBlock(x, y, z) == Blockizer.TempleTrapDamage && e instanceof EntityPlayer && !((EntityPlayer)e).capabilities.isCreativeMode) {
			e.attackEntityFrom(DamageSource.cactus, 15.0f);
			e.worldObj.playSoundAtEntity(e, "nevermine:TempleLaugh", 1.3f, 1.0f);
			((EntityLivingBase)e).addPotionEffect(new PotionEffect(Potion.blindness.id, 40, 3));
		}
	}
}
