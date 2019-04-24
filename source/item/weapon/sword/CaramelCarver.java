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
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

public class CaramelCarver extends BaseSword implements AdventWeapon {
	public CaramelCarver(final ToolMaterial material, Float dmg, Double speed) {
		super(material, dmg, speed);
		setUnlocalizedName("CaramelCarver");
		setRegistryName("aoa3:caramel_carver");
	}

	@Override
	public boolean onLeftClickEntity(final ItemStack stack, final EntityPlayer player, final Entity target) {
		if (player.world.isRemote)
			return false;

		if (target instanceof EntityLivingBase) {
			final int multiplier = player.world.getEntitiesWithinAABB(EntityLivingBase.class, player.getEntityBoundingBox().grow(5.0f), EntityUtil::isHostileMob).size();

			((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, (int)(multiplier * 15 * player.getCooledAttackStrength(0.0f)), 1));
		}

		return false;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(StringUtil.getColourLocaleString("items.description.damage.slow", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("item.CaramelCarver.desc.1", TextFormatting.DARK_GREEN));
	}
}
