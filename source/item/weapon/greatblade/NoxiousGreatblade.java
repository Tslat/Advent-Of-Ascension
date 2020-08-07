package net.tslat.aoa3.item.weapon.greatblade;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;

import java.util.List;

public class NoxiousGreatblade extends BaseGreatblade {
	public NoxiousGreatblade(double dmg, double speed, int durability) {
		super(dmg, speed, durability);
		setTranslationKey("NoxiousGreatblade");
		setRegistryName("aoa3:noxious_greatblade");
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, EntityLivingBase attacker, Entity target, float dmgDealt) {
		if (target instanceof EntityLivingBase) {
			if (((EntityLivingBase)target).isPotionActive(MobEffects.POISON)) {
				EntityAreaEffectCloud cloud = new EntityAreaEffectCloud(target.world, target.posX, target.posY, target.posZ);

				cloud.setRadius(2);
				cloud.setPotion(PotionTypes.STRONG_POISON);
				cloud.addEffect(new PotionEffect(MobEffects.POISON, 60, 2, true, true));
				cloud.setDuration(3);
				cloud.setColor(Enums.RGBIntegers.TOXIC_GREEN);
				cloud.setOwner(attacker);

				target.world.spawnEntity(cloud);
			}
			else {
				((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.POISON, 40, 1, true, true));
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.NoxiousGreatblade.desc.1", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.NoxiousGreatblade.desc.2", Enums.ItemDescriptionType.POSITIVE));
	}
}
