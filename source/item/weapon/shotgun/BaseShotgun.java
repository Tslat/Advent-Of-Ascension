package net.tslat.aoa3.item.weapon.shotgun;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.packet.PacketRecoil;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.EnchantmentsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.entity.projectiles.gun.EntityLimoniteBullet;
import net.tslat.aoa3.entity.projectiles.gun.EntityMetalSlug;
import net.tslat.aoa3.event.GlobalEvents;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.PacketUtil;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

public abstract class BaseShotgun extends BaseGun implements AdventWeapon {
	protected final int pelletCount;

	public BaseShotgun(final double dmg, final int pellets, final SoundEvent sound, final int durability, final int fireDelayTicks, final float recoil) {
		super(dmg, sound, durability, fireDelayTicks, recoil);

		this.pelletCount = pellets;
		setCreativeTab(CreativeTabsRegister.shotgunsTab);
	}

	public int getPelletCount() {
		return pelletCount;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);

		if (hand != getGunHand(stack))
			return ActionResult.newResult(EnumActionResult.FAIL, stack);

		if (player.getCooledAttackStrength(0.0f) < 1)
			return ActionResult.newResult(EnumActionResult.FAIL, stack);

		if (!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());

		if (stack.getTagCompound().getLong("NextShotTime") < GlobalEvents.tick) {
			BaseBullet ammo = findAndConsumeAmmo(player, this, hand);

			if (ammo != null) {
				if (!world.isRemote) {
					float form = 0.15f * EnchantmentHelper.getEnchantmentLevel(EnchantmentsRegister.form, stack);

					fireShotgun(player, hand, 0.1f * pelletCount * (1 - form), pelletCount);
				}

				player.world.playSound(null, player.posX, player.posY, player.posZ, sound, SoundCategory.PLAYERS, 1.0f, 1.0f);

				stack.damageItem(1, player);
				stack.getTagCompound().setInteger("NextShotTime", GlobalEvents.tick + firingDelay);

				if (player instanceof EntityPlayerMP)
					PacketUtil.network.sendTo(new PacketRecoil(recoil, firingDelay), (EntityPlayerMP)player);

				return ActionResult.newResult(EnumActionResult.PASS, stack);
			}

			if (player instanceof EntityPlayerMP)
				((EntityPlayerMP)player).sendContainerToPlayer(player.inventoryContainer);
		}
		else if (stack.getTagCompound().getLong("NextShotTime") > GlobalEvents.tick + firingDelay * 2) {
			stack.getTagCompound().setLong("NextShotTime", -1L);
		}

		return ActionResult.newResult(EnumActionResult.FAIL, stack);
	}

	public void fireShotgun(EntityLivingBase shooter, EnumHand hand, float spreadFactor, int pellets) {
		for (int i = 0; i < pellets; i++) {
			BaseBullet pellet = new EntityLimoniteBullet(shooter, this, hand, 4, 1.0f, 0, (itemRand.nextFloat() - 0.5f) * spreadFactor, (itemRand.nextFloat() - 0.5f) * spreadFactor, (itemRand.nextFloat() - 0.5f) * spreadFactor);
			shooter.world.spawnEntity(pellet);
		}
	}

	@Override
	public BaseBullet findAndConsumeAmmo(EntityPlayer player, BaseGun gun, EnumHand hand) {
		Item ammo = ItemUtil.findAndConsumeSpecialBullet(player, gun, true, ItemRegister.spreadshot, player.getHeldItem(hand));

		if (ammo != null)
			return new EntityMetalSlug(player, gun, hand,4, 0);

		return null;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(1, StringUtil.getColourLocaleStringWithArguments("items.description.damage.shotgun", TextFormatting.DARK_RED, Double.toString(dmg), Integer.toString(pelletCount)));
		tooltip.add(StringUtil.getLocaleStringWithArguments("items.description.gun.speed", Double.toString((2000 / firingDelay) / (double)100)));
		tooltip.add(StringUtil.getColourLocaleString("items.description.ammo.spreadshot", TextFormatting.LIGHT_PURPLE));
	}
}
