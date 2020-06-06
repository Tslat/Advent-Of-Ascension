package net.tslat.aoa3.item.weapon.sniper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.entity.projectiles.gun.EntityDischargeSlug;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;
import java.util.List;

public class DischargeSniper extends BaseSniper {
	private final double dmg;
	private final int firingDelay;

	public DischargeSniper(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
		this.dmg = dmg;
		this.firingDelay = firingDelayTicks;
		setTranslationKey("DischargeSniper");
		setRegistryName("aoa3:discharge_sniper");
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundsRegister.DISCHARGE_GUN_FIRE;
	}

	@Override
	public Enums.ScopeScreens getScreen() {
		return Enums.ScopeScreens.DISCHARGE;
	}

	@Override
	public BaseBullet findAndConsumeAmmo(EntityPlayer player, BaseGun gun, EnumHand hand) {
		Item ammo = ItemUtil.findAndConsumeSpecialBullet(player, gun, true, ItemRegister.DISCHARGE_CAPSULE, player.getHeldItem(hand));

		if (ammo != null)
			return new EntityDischargeSlug(player, gun, 0);

		return null;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.DischargeSniper.desc.1", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(1, ItemUtil.getFormattedDescriptionText("items.description.damage.gun", Enums.ItemDescriptionType.ITEM_DAMAGE, Double.toString(dmg)));
		tooltip.add(ItemUtil.getFormattedDescriptionText("items.description.sniper.use", Enums.ItemDescriptionType.ITEM_TYPE_INFO));
		tooltip.add(StringUtil.getLocaleStringWithArguments("items.description.gun.speed", Double.toString((2000 / firingDelay) / (double)100)));
		tooltip.add(ItemUtil.getFormattedDescriptionText("items.description.ammo.other", Enums.ItemDescriptionType.ITEM_AMMO_COST, StringUtil.getLocaleString("item.DischargeCapsule.name")));
	}
}
