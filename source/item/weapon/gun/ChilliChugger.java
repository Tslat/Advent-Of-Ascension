package net.tslat.aoa3.item.weapon.gun;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.EnchantmentsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.entity.projectiles.gun.EntityChilliShot;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;
import java.util.List;

public class ChilliChugger extends BaseGun {
	double dmg;
	int firingDelay;

	public ChilliChugger(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
		this.dmg = dmg;
		this.firingDelay = firingDelayTicks;
		setTranslationKey("ChilliChugger");
		setRegistryName("aoa3:chilli_chugger");
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundsRegister.CHUGGER_FIRE;
	}

	@Override
	public BaseBullet findAndConsumeAmmo(EntityPlayer player, ItemStack gunStack, EnumHand hand) {
		if (ItemUtil.findInventoryItem(player, new ItemStack(ItemRegister.CHILLI), true, 1 + EnchantmentHelper.getEnchantmentLevel(EnchantmentsRegister.GREED, gunStack)))
			return new EntityChilliShot(player, (BaseGun)gunStack.getItem(), hand, 120, 0);

		return null;
	}

	@Override
	public void doImpactDamage(Entity target, EntityLivingBase shooter, BaseBullet bullet, float bulletDmgMultiplier) {
		super.doImpactDamage(target, shooter, bullet, bulletDmgMultiplier);

		if (target instanceof EntityLivingBase)
			target.setFire(6);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(1, ItemUtil.getFormattedDescriptionText("items.description.damage.gun", Enums.ItemDescriptionType.ITEM_DAMAGE, Double.toString(dmg)));
		tooltip.add(ItemUtil.getFormattedDescriptionText("items.description.damage.fire", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(StringUtil.getLocaleStringWithArguments("items.description.gun.speed", Double.toString((2000 / firingDelay) / (double)100)));
		tooltip.add(ItemUtil.getFormattedDescriptionText("items.description.ammo.other", Enums.ItemDescriptionType.ITEM_AMMO_COST, StringUtil.getLocaleString("item.Chilli.name")));
	}
}
