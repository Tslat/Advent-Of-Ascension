package net.tslat.aoa3.item.weapon.sword;

import com.google.common.collect.Multimap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.tslat.aoa3.capabilities.providers.AdventMiscStackProvider;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;

import javax.annotation.Nullable;

public abstract class BaseSword extends ItemSword implements AdventWeapon {
	protected final float dmg;
	protected final double speed;

	public BaseSword(final ToolMaterial material, final double speed) {
		super(material);
		this.dmg = getAttackDamage();
		this.speed = speed == 0 ? Enums.WeaponSpeed.NORMAL.value : speed;
		setCreativeTab(CreativeTabsRegister.swordsTab);
	}

	public float getDamage() {
		return dmg;
	}

	public double getAttackSpeed() {
		return speed;
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		stack.getCapability(AdventMiscStackProvider.MISC_STACK, EnumFacing.NORTH).setValue(player.getCooledAttackStrength(0.0f));

		return super.onLeftClickEntity(stack, player, entity);
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		doMeleeEffect(stack, target, attacker, stack.getCapability(AdventMiscStackProvider.MISC_STACK, EnumFacing.NORTH).getValue());

		return super.hitEntity(stack, target, attacker);
	}

	protected void doMeleeEffect(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker, float attackCooldown) {}

	@Nullable
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
		return new AdventMiscStackProvider();
	}

	@Override
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot) {
		Multimap<String, AttributeModifier> modifierMap = super.getItemAttributeModifiers(equipmentSlot);

		if (equipmentSlot == EntityEquipmentSlot.MAINHAND) {
			ItemUtil.setAttribute(modifierMap, SharedMonsterAttributes.ATTACK_DAMAGE, ATTACK_DAMAGE_MODIFIER, dmg);
			ItemUtil.setAttribute(modifierMap, SharedMonsterAttributes.ATTACK_SPEED, ATTACK_SPEED_MODIFIER, speed);
		}

		return modifierMap;
	}
}

