package net.tslat.aoa3.item.weapon.sword;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

public class SweetSword extends BaseSword implements AdventWeapon {
	public SweetSword(final ToolMaterial material, Float dmg, Double speed) {
		super(material, dmg, speed);
		setUnlocalizedName("SweetSword");
		setRegistryName("aoa3:sweet_sword");
	}

	@Override
	public boolean onLeftClickEntity(final ItemStack stack, final EntityPlayer player, final Entity target) {
		if (player.world.isRemote)
			return false;

		if (player.getCooledAttackStrength(0.0f) > 0.75f) {
			final int size = player.world.getEntitiesWithinAABB(EntityLivingBase.class, player.getEntityBoundingBox().grow(2.5f), EntityUtil::isHostileMob).size();

			if (size > 0)
				EntityUtil.healEntity(player, Math.min(6, size * 0.5f));
		}

		return false;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(StringUtil.getColourLocaleString("item.SweetSword.desc.1", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("item.SweetSword.desc.2", TextFormatting.DARK_GREEN));
	}
}
