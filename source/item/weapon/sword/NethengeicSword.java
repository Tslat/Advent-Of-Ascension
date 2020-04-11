package net.tslat.aoa3.item.weapon.sword;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;

import java.util.List;

public class NethengeicSword extends BaseSword {
	public NethengeicSword(final ToolMaterial material, final double speed) {
		super(material, speed);
		setTranslationKey("NethengeicSword");
		setRegistryName("aoa3:nethengeic_sword");
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker, float attackCooldown) {
		if (!attacker.world.isRemote) {
			if (target.isImmuneToFire() || target.isEntityInvulnerable(DamageSource.ON_FIRE)) {

				target.addPotionEffect(new PotionEffect(MobEffects.WITHER, (int)(80 * attackCooldown), 2, false, true));
			}
			else {
				target.setFire((int)(4 * (attacker instanceof EntityPlayer ? attackCooldown : 1)));
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.NethengeicSword.desc.1", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.NethengeicSword.desc.2", Enums.ItemDescriptionType.POSITIVE));
	}
}
