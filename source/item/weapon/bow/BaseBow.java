package net.tslat.aoa3.item.weapon.bow;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.*;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.entity.projectile.arrow.CustomArrowEntity;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;

public class BaseBow extends BowItem {
	protected float drawSpeedMultiplier;
	protected double dmg;

	public BaseBow(double damage, float drawSpeedMultiplier, int durability) {
		super(new Item.Properties().group(AoAItemGroups.BOWS).maxDamage(durability));
		this.dmg = damage;
		this.drawSpeedMultiplier = drawSpeedMultiplier;

		addPropertyOverride(new ResourceLocation("pull"), (stack, world, entity) -> {
			if (entity == null) {
				return 0.0F;
			}
			else {
				return !(entity.getActiveItemStack().getItem() instanceof BaseBow) ? 0.0F : getDrawSpeedMultiplier() * (float)(stack.getUseDuration() - entity.getItemInUseCount()) / 20.0F;
			}
		});

		addPropertyOverride(new ResourceLocation("pulling"), (stack, world, entity) -> entity != null && entity.isHandActive() && entity.getActiveItemStack() == stack ? 1.0F : 0.0F);
	}

	public double getDamage() {
		return dmg * (AoAConfig.COMMON.hardcoreMode.get() ? 1.25f : 1f);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		ItemStack heldStack = player.getHeldItem(hand);
		boolean hasAmmo = !findAmmo(player, heldStack, player.isCreative()).isEmpty();
		ActionResult<ItemStack> arrowNockEventResult = ForgeEventFactory.onArrowNock(heldStack, world, player, hand, hasAmmo);

		if (arrowNockEventResult != null)
			return arrowNockEventResult;

		if (!player.isCreative() && !hasAmmo) {
			return ActionResult.resultFail(heldStack);
		}
		else {
			player.setActiveHand(hand);
			return ActionResult.resultConsume(heldStack);
		}
	}

	public void onPlayerStoppedUsing(ItemStack stack, World world, LivingEntity shooter, int timeLeft) {
		if (!(shooter instanceof PlayerEntity))
			return;

		PlayerEntity pl = (PlayerEntity)shooter;
		boolean infiniteAmmo = pl.isCreative() || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
		ItemStack ammoStack = findAmmo(pl, stack, infiniteAmmo);
		int charge = (int)((getUseDuration(stack) - timeLeft) * getDrawSpeedMultiplier());
		charge = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, world, pl, charge, !ammoStack.isEmpty() || infiniteAmmo);

		if (charge < 0)
			return;

		if (!ammoStack.isEmpty() || infiniteAmmo) {
			if (ammoStack.isEmpty())
				ammoStack = new ItemStack(Items.ARROW);

			float velocity = getArrowVelocity(charge);

			if ((double)velocity >= 0.1D) {
				if (!world.isRemote) {
					CustomArrowEntity arrow = makeArrow(pl, stack, ammoStack, velocity, !infiniteAmmo);

					arrow = doArrowMods(arrow, shooter, ammoStack, timeLeft);
					world.addEntity(arrow);
				}

				world.playSound(null, pl.getPosX(), pl.getPosY(), pl.getPosZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + velocity * 0.5F);

				if (!infiniteAmmo && !pl.abilities.isCreativeMode) {
					ammoStack.shrink(1);

					if (ammoStack.isEmpty())
						pl.inventory.deleteStack(ammoStack);
				}

				pl.addStat(Stats.ITEM_USED.get(this));
			}
		}
	}

	protected ItemStack findAmmo(PlayerEntity shooter, ItemStack bowStack, boolean infiniteAmmo) {
		return shooter.findAmmo(bowStack);
	}

	protected CustomArrowEntity makeArrow(LivingEntity shooter, ItemStack bowStack, ItemStack ammoStack, float velocity, boolean consumeAmmo) {
		ArrowItem arrowItem = (ArrowItem)(ammoStack.getItem() instanceof ArrowItem ? ammoStack.getItem() : Items.ARROW);
		CustomArrowEntity arrow = CustomArrowEntity.fromArrow(arrowItem.createArrow(shooter.world, ammoStack, shooter), this, shooter, getDamage());

		arrow.shoot(shooter, shooter.rotationPitch, shooter.rotationYaw, 0.0F, velocity * 3.0F, 1.0F);

		if (velocity == 1.0F)
			arrow.setIsCritical(true);

		int powerEnchant = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, bowStack);
		int punchEnchant = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, bowStack);

		if (powerEnchant > 0)
			arrow.setDamage(arrow.getDamage() + powerEnchant * 1.5D + 1D);

		if (punchEnchant > 0)
			arrow.setKnockbackStrength(punchEnchant);

		if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, bowStack) > 0)
			arrow.setFire(100);

		bowStack.damageItem(1, shooter, (firingEntity) -> firingEntity.sendBreakAnimation(shooter.getActiveHand()));

		if (!consumeAmmo || (shooter instanceof PlayerEntity && ((PlayerEntity)shooter).isCreative()) && (ammoStack.getItem() == Items.SPECTRAL_ARROW || ammoStack.getItem() == Items.TIPPED_ARROW))
			arrow.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;

		return arrow;
	}

	public float getDrawSpeedMultiplier() {
		return drawSpeedMultiplier;
	}

	public CustomArrowEntity doArrowMods(CustomArrowEntity arrow, LivingEntity shooter, ItemStack ammoStack, int useTicksRemaining) {
		return arrow;
	}

	public void onEntityHit(CustomArrowEntity arrow, Entity target, Entity shooter, double damage, float drawStrength) {}

	public void onBlockHit(CustomArrowEntity arrow, BlockRayTraceResult rayTrace, Entity shooter) {}

	public void onArrowTick(CustomArrowEntity arrow, Entity shooter) {}

	public double getArrowDamage(CustomArrowEntity arrow, Entity target, double currentDamage, float drawStrength, boolean isCritical) {
		double damage = currentDamage * 0.5d * (drawStrength / 3f);

		if (isCritical)
			damage += damage + (damage * 0.35f * random.nextGaussian());

		return damage;
	}

	@Override
	public int getItemEnchantability() {
		return 8;
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(1, LocaleUtil.getFormattedItemDescriptionText("items.description.damage.arrows", LocaleUtil.ItemDescriptionType.ITEM_DAMAGE, Double.toString(getDamage())));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.bow.drawSpeed", LocaleUtil.ItemDescriptionType.NEUTRAL, Double.toString(((int)(72000 / getDrawSpeedMultiplier()) / 720) / (double)100)));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.AMMO_ITEM, LocaleUtil.ItemDescriptionType.ITEM_AMMO_COST, LocaleUtil.getItemName(Items.ARROW)));
	}
}