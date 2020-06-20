package net.tslat.aoa3.item.weapon.greatblade;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.utils.ItemUtil;

import java.util.List;

import static net.tslat.aoa3.library.Enums.ItemDescriptionType.POSITIVE;

public class ShroomicGreatblade extends BaseGreatblade {
	public ShroomicGreatblade(double dmg, double speed, int durability) {
		super(dmg, speed, durability);
		setTranslationKey("ShroomicGreatblade");
		setRegistryName("aoa3:shroomic_greatblade");
	}

	@Override
	protected double getDamageForAttack(ItemStack stack, Entity target, EntityLivingBase attacker, double baseDmg) {
		return (float)(attacker.isPotionActive(MobEffects.POISON) ? getDamage() * 1.3f : getDamage());
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.ShroomicGreatblade.desc.1", POSITIVE));
	}
}
