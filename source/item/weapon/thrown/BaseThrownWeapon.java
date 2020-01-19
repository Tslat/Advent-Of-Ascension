package net.tslat.aoa3.item.weapon.thrown;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

public abstract class BaseThrownWeapon extends BaseGun implements AdventWeapon {
	double dmg;
	int firingDelay;

	public BaseThrownWeapon(double dmg, int fireDelayTicks) {
		super(dmg, 1, fireDelayTicks, 0.0f);
		setMaxDamage(0);
		setMaxStackSize(64);
		this.dmg = dmg;
		this.firingDelay = fireDelayTicks;
	}

	@Override
	public void doImpactDamage(Entity target, EntityLivingBase shooter, BaseBullet bullet, float bulletDmgMultiplier) {
		if (target != null && dmg > 0.0f)
			EntityUtil.dealRangedDamage(target, shooter, bullet, (float)dmg * bulletDmgMultiplier);
	}

	@Override
	public BaseBullet findAndConsumeAmmo(EntityPlayer player, BaseGun gun, EnumHand hand) {
		return null;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		if (dmg > 0.0f)
			tooltip.add(1, StringUtil.getColourLocaleStringWithArguments("items.description.damage.ranged", TextFormatting.DARK_RED, Double.toString(dmg)));

		tooltip.add(StringUtil.getColourLocaleString("items.description.thrownWeapon", TextFormatting.AQUA));
		tooltip.add(StringUtil.getLocaleStringWithArguments("items.description.throwable.speed", Double.toString((2000 / firingDelay) / (double)100)));
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot equipmentSlot, ItemStack stack) {
		return HashMultimap.<String, AttributeModifier>create();
	}
}
