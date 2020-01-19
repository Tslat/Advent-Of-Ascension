package net.tslat.aoa3.item.weapon.sword;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;

import java.util.List;

public class CoralstormSword extends BaseSword implements AdventWeapon {
	public CoralstormSword(final ToolMaterial material, final double speed) {
		super(material, speed);
		setTranslationKey("CoralstormSword");
		setRegistryName("aoa3:coralstorm_sword");
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		if (entity.isInWater() && isSelected && entity instanceof EntityLivingBase)
			((EntityLivingBase)entity).addPotionEffect(new PotionEffect(MobEffects.STRENGTH, -1, 0, true, false));
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.CoralstormSword.desc.1", Enums.ItemDescriptionType.POSITIVE));
	}
}
