package net.tslat.aoa3.item.weapon.gun;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.EnchantmentsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.entity.projectiles.gun.EntityLimoniteBullet;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;

import javax.annotation.Nullable;
import java.util.List;

public class Clownershot extends BaseGun {
	public Clownershot(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
		setTranslationKey("Clownershot");
		setRegistryName("aoa3:clownershot");
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundsRegister.WOOD_RIFLE_FIRE;
	}

	public BaseBullet findAndConsumeAmmo(EntityPlayer player, ItemStack gunStack, EnumHand hand) {
		if (ItemUtil.findInventoryItem(player, new ItemStack(ItemRegister.LIMONITE_BULLET), true, 1 + EnchantmentHelper.getEnchantmentLevel(EnchantmentsRegister.GREED, gunStack))) {
			if (ItemUtil.findInventoryItem(player, new ItemStack(ItemRegister.LIMONITE_BULLET), true, 1 + EnchantmentHelper.getEnchantmentLevel(EnchantmentsRegister.GREED, gunStack))) {
				BaseBullet bullet2 = new EntityLimoniteBullet(player, (BaseGun)gunStack.getItem(), hand, 120, 0);

				bullet2.setPositionAndUpdate(bullet2.posX, bullet2.posY + 0.1f, bullet2.posZ);
				player.world.spawnEntity(bullet2);
			}

			return new EntityLimoniteBullet(player, (BaseGun)gunStack.getItem(), hand, 120, 0);
		}

		return null;
	}

	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.Overshot.desc.1", Enums.ItemDescriptionType.POSITIVE));
		super.addInformation(stack, world, tooltip, flag);
	}
}
