package net.tslat.aoa3.item.weapon.sword;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ItemUtil;

import java.util.List;

public class RunicSword extends BaseSword implements AdventWeapon {
	public RunicSword(final ToolMaterial material, final double speed) {
		super(material, speed);
		setTranslationKey("RunicSword");
		setRegistryName("aoa3:runic_sword");
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker, float attackCooldown) {
		if (!attacker.world.isRemote && attackCooldown > 0.75 && attacker instanceof EntityPlayer) {
			ItemStack offhandStack = attacker.getHeldItemOffhand();

			if (offhandStack.getItem() instanceof RuneItem && offhandStack.getCount() >= 5) {
				RuneItem rune = (RuneItem)offhandStack.getItem();

				if (rune == ItemRegister.runePoison) {
					target.addPotionEffect(new PotionEffect(MobEffects.POISON, 72, 1, false, true));
				}
				else if (rune == ItemRegister.runeWither) {
					target.addPotionEffect(new PotionEffect(MobEffects.WITHER, 40, 2, false, true));
				}
				else if (rune == ItemRegister.runeFire) {
					target.setFire(5);
				}
				else if (rune == ItemRegister.runeWind) {
					EntityUtil.doScaledKnockback(target, attacker, 0.5f, attacker.posX - target.posX, attacker.posZ - target.posZ);
				}
				else if (rune == ItemRegister.runeWater) {
					target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 60, 0, false, true));
				}
				else if (rune == ItemRegister.runeCharged) {
					((WorldServer)target.world).spawnParticle(EnumParticleTypes.VILLAGER_ANGRY, target.posX + (itemRand.nextFloat() * target.width * 2f) - target.width, target.posY + 1 + (itemRand.nextFloat() * target.height), target.posZ + (itemRand.nextFloat() * target.width * 2f) - target.width, 3, 0, 0, 0, (double)0);
				}
				else {
					return;
				}

				offhandStack.shrink(5);
				stack.damageItem(1, attacker);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.RunicSword.desc.1", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.RunicSword.desc.2", Enums.ItemDescriptionType.NEGATIVE));
	}
}
