package net.tslat.aoa3.item.weapon.bow;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.projectiles.arrow.EntityHollyArrow;
import net.tslat.aoa3.item.misc.HollyArrow;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;
import java.util.List;

public abstract class BaseBow extends ItemBow implements AdventWeapon {
	protected float drawSpeedMultiplier;
	protected double dmg;

	public BaseBow(double damage, float drawSpeedMultiplier, int durability) {
		this.dmg = damage;
		this.drawSpeedMultiplier = drawSpeedMultiplier;
		setMaxDamage(durability);
		setMaxStackSize(1);
		setFull3D();
		setNoRepair();
		setCreativeTab(CreativeTabsRegister.bowsTab);

		addPropertyOverride(new ResourceLocation("pull"), new IItemPropertyGetter() {
			@SideOnly(Side.CLIENT)
			public float apply(ItemStack stack, @Nullable World world, @Nullable EntityLivingBase entity) {
				if (entity == null) {
					return 0.0F;
				}
				else {
					return !(entity.getActiveItemStack().getItem() instanceof BaseBow) ? 0.0F : getDrawSpeedMultiplier() * (float)(getMaxItemUseDuration(stack) - entity.getItemInUseCount()) / 20.0F;
				}
			}
		});

		addPropertyOverride(new ResourceLocation("pulling"), new IItemPropertyGetter() {
			@SideOnly(Side.CLIENT)
			public float apply(ItemStack stack, @Nullable World world, @Nullable EntityLivingBase entity) {
				return entity != null && entity.isHandActive() && entity.getActiveItemStack() == stack ? 1.0F : 0.0F;
			}
		});
	}

	public double getDamage() {
		return dmg;
	}

	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase shooter, int timeLeft) {
		if (!(shooter instanceof EntityPlayer))
			return;

		EntityPlayer pl = (EntityPlayer)shooter;
		boolean infiniteAmmo = pl.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
		ItemStack ammoStack = findAmmo(pl);
		int charge = (int)((getMaxItemUseDuration(stack) - timeLeft) * getDrawSpeedMultiplier());
		charge = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, world, pl, charge, !ammoStack.isEmpty() || infiniteAmmo);

		if (charge < 0)
			return;

		if (!ammoStack.isEmpty() || infiniteAmmo) {
			if (ammoStack.isEmpty())
				ammoStack = new ItemStack(ItemRegister.hollyArrow);

			float velocity = getArrowVelocity(charge);

			if ((double)velocity >= 0.1D) {
				if (!world.isRemote) {
					EntityHollyArrow arrow = makeArrow(pl, stack, ammoStack, velocity, !infiniteAmmo);

					doArrowMods(arrow, shooter, timeLeft);
					world.spawnEntity(arrow);
				}

				world.playSound(null, pl.posX, pl.posY, pl.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + velocity * 0.5F);
			}
		}
	}

	protected EntityHollyArrow makeArrow(EntityLivingBase shooter, ItemStack bowStack, ItemStack ammoStack, float velocity, boolean consumeAmmo) {
		HollyArrow arrowItem = (HollyArrow)(ammoStack.getItem() instanceof HollyArrow ? ammoStack.getItem() : ItemRegister.hollyArrow);
		EntityHollyArrow arrowEntity = arrowItem.createArrow(shooter.world, this, ammoStack, shooter, dmg);
		arrowEntity.shoot(shooter, shooter.rotationPitch, shooter.rotationYaw, 0.0F, velocity * 3.0F, 1.0F);

		int powerEnchant = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, bowStack);
		int punchEnchant = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, bowStack);

		if (velocity >= 1.0F)
			arrowEntity.setIsCritical(true);

		if (powerEnchant > 0)
			arrowEntity.setDamage(arrowEntity.getDamage() + (double)powerEnchant * 1.5D + 1D);

		if (punchEnchant > 0)
			arrowEntity.setKnockbackStrength(punchEnchant);

		if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, bowStack) > 0)
			arrowEntity.setFire(100);

		if (!consumeAmmo || (shooter instanceof EntityPlayer && ((EntityPlayer)shooter).capabilities.isCreativeMode) && (ammoStack.getItem() == Items.SPECTRAL_ARROW || ammoStack.getItem() == Items.TIPPED_ARROW))
			arrowEntity.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;

		bowStack.damageItem(1, shooter);

		if (shooter instanceof EntityPlayer && consumeAmmo) {
			ammoStack.shrink(1);

			if (ammoStack.isEmpty())
				((EntityPlayer)shooter).inventory.deleteStack(ammoStack);
		}

		return arrowEntity;
	}

	protected ItemStack findAmmo(EntityPlayer player) {
		if (isArrow(player.getHeldItem(EnumHand.OFF_HAND))){
			return player.getHeldItem(EnumHand.OFF_HAND);
		}
		else if (isArrow(player.getHeldItem(EnumHand.MAIN_HAND))) {
			return player.getHeldItem(EnumHand.MAIN_HAND);
		}
		else {
			for (int i = 0; i < player.inventory.getSizeInventory(); ++i) {
				ItemStack itemstack = player.inventory.getStackInSlot(i);

				if (isArrow(itemstack))
					return itemstack;
			}

			return ItemStack.EMPTY;
		}
	}

	@Override
	protected boolean isArrow(ItemStack stack) {
		return stack.getItem() instanceof HollyArrow;
	}

	public static float getArrowVelocity(int charge) {
		float f = (float)charge / 20.0F;
		f = (f * f + f * 2.0F) / 3.0F;

		if (f > 1.0F)
			f = 1.0F;

		return f;
	}

	public float getDrawSpeedMultiplier() {
		return drawSpeedMultiplier;
	}

	@Override
	public boolean getIsRepairable(ItemStack stack, ItemStack repairMaterial) {
		return OreDictionary.itemMatches(repairMaterial, new ItemStack(ItemRegister.magicRepairDust), false) || super.getIsRepairable(stack, repairMaterial);
	}

	public void doArrowMods(EntityHollyArrow arrow, EntityLivingBase shooter, int useTicksRemaining) {}

	public void doImpactEffect(EntityHollyArrow arrow, Entity target, Entity shooter, float damage) {}

	public void doBlockImpact(EntityHollyArrow arrow, IBlockState blockState, Entity shooter) {}

	public void doArrowTick(EntityHollyArrow arrow, Entity shooter) {}

	public float getArrowDamage(EntityHollyArrow arrow, Entity target, float currentDamage) {
		return currentDamage;
	}

	@Override
	public int getItemEnchantability() {
		return 8;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(1, StringUtil.getColourLocaleStringWithArguments("items.description.damage.arrows", TextFormatting.DARK_RED, Double.toString(dmg)));
		tooltip.add(StringUtil.getLocaleStringWithArguments("items.description.bow.drawSpeed", Double.toString(((int)(72000 / getDrawSpeedMultiplier()) / 720) / (double)100)));
		tooltip.add(StringUtil.getColourLocaleString("items.description.ammo.arrows", TextFormatting.LIGHT_PURPLE));
	}
}