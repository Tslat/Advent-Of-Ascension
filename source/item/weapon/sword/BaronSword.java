package net.tslat.aoa3.item.weapon.sword;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ItemUtil;

import java.util.List;

public class BaronSword extends BaseSword {
	public BaronSword(final ToolMaterial material, final double speed) {
		super(material, speed);
		setTranslationKey("BaronSword");
		setRegistryName("aoa3:baron_sword");
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker, float attackCooldown) {
		if (itemRand.nextFloat() < 0.2f * attackCooldown)
			EntityUtil.dealMeleeDamage(attacker, target, getDamage() * 0.5f, false);
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.BaronSword.desc.1", Enums.ItemDescriptionType.POSITIVE));
	}
}
