package net.tslat.aoa3.item.weapon.sword;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

public class VulcammerSword extends BaseSword implements AdventWeapon {
	public VulcammerSword(final ToolMaterial material, Float dmg, Double speed) {
		super(material, dmg, speed);
		setUnlocalizedName("VulcammerSword");
		setRegistryName("aoa3:vulcammer_sword");
	}

	@Override
	public boolean onLeftClickEntity(final ItemStack stack, final EntityPlayer player, final Entity target) {
		if (player.world.isRemote || !(target instanceof EntityLivingBase))
			return false;

		if (ItemUtil.checkCooledItemProc(player, 0.1f)) {
			for (EntityLivingBase e : target.world.getEntitiesWithinAABB(EntityLivingBase.class, player.getEntityBoundingBox().grow(3.5), EntityUtil::isHostileMob)) {
				e.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 40, 100));
				e.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 40, 100));
			}
		}

		return false;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(StringUtil.getColourLocaleString("item.VulcammerSword.desc.1", TextFormatting.DARK_GREEN));
	}
}