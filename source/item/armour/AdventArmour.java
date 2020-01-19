package net.tslat.aoa3.item.armour;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.living.*;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;

import javax.annotation.Nullable;
import java.util.HashSet;

public abstract class AdventArmour extends ItemArmor {
	public AdventArmour(ArmorMaterial material, String name, String registryName, EntityEquipmentSlot slot) {
		super(material, 0, slot);
		setCreativeTab(CreativeTabsRegister.armourTab);
		setTranslationKey(name);
		setRegistryName("aoa3:" + registryName);
	}

	public abstract Enums.ArmourSets setType();

	/**
	 * Called when the player equips the item or has it placed on their body by a third party method.
	 *
	 * @param plData the {@code PlayerDataManager} container for the player wearing the armour.
	 * @param slot the {@code EntityEquipmentSlot} slot that the item was equipped into. A null slot in this context is used to indicate that the full set was equipped.<br>
	 *             For example, if a player has the boots, legs, and chestplate of a set equipped, and equips the helmet, this event will be fired twice. Once with the head slot, once with a null slot for completing the set.
	 */
	public void onEquip(PlayerDataManager plData, @Nullable EntityEquipmentSlot slot) {}

	/**
	 * Called when the player unequips the item or has it removed from their body by a third party method.
	 *
	 * @param plData the {@code PlayerDataManager} container for the player wearing the armour.
	 * @param slot the {@code EntityEquipmentSlot} slot that the item was unequipped from. A null slot in this context is used to indicate that the full set status was broken.<br>
	 *             For example, if a player has the boots, legs, chestplate, and helmet equipped, then unequips the helmet, this event will be fired twice. Once with the head slot, once with a null slot for breaking the full set.
	 */
	public void onUnequip(PlayerDataManager plData, @Nullable EntityEquipmentSlot slot) {}

	/**
	 * Called immediately after equip, used to add buffs to the player safely.
	 *
	 * @param plBuffs the {@code PlayerBuffs} container for the player equipping the armour.
	 * @param slot the {@code EntityEquipmentSlot} slot that the item was equipped into. A null slot in this context is used to indicate that the full set was equipped.<br>
	 *             For example, if a player has the boots, legs, and chestplate of a set equipped, and equips the helmet, this event will be fired twice. Once with the head slot, once with a null slot for completing the set.
	 */
	public void addBuffs(PlayerDataManager.PlayerBuffs plBuffs, @Nullable EntityEquipmentSlot slot) {}

	/**
	 * Called immediately after unequip, used to remove previously added buffs from the player safely. Make sure that all buffs added in addBuffs are removed here.
	 *
	 * @param plBuffs the {@code PlayerBuffs} container for the player unequipping the armour.
	 * @param slot the {@code EntityEquipmentSlot} slot that the item was unequipped from. A null slot in this context is used to indicate that the full set status was broken.<br>
	 *             For example, if a player has the boots, legs, chestplate, and helmet equipped, then unequips the helmet, this event will be fired twice. Once with the head slot, once with a null slot for breaking the full set.
	 */
	public void removeBuffs(PlayerDataManager.PlayerBuffs plBuffs, @Nullable EntityEquipmentSlot slot) {}

	/**
	 * Called each tick during the player's tick cycle for each piece equipped. A null slot indicates that the armour is being ticked for the set's effect.
	 *
	 * @param plData the {@code PlayerDataManager} container for the player that the armour is worn by.
	 * @param slots the collection of {@code EntityEquipmentSlot}s that the player is wearing of this type. A null parameter in this context represents the method being called for set-effects.<br>
	 *              For example, if a player is wearing boots and legs from the same set, this method will be called with a 4-element HashSet containing FEET, LEGS, BODY, HEAD. It will then be called again with null slots, representing the set-effect call.
	 */
	public void onEffectTick(PlayerDataManager plData, @Nullable HashSet<EntityEquipmentSlot> slots) {}

	/**
	 * Called when the player successfully lands an attack on another entity via any method. This method is called prior the final damage being dealt to the entity.<br>
	 *     Use to augment the final damage output, or apply/activate any additional effects for damaging an entity.<br>
	 *     At this stage, the attack is considered to be successful, with only the final damage amount to be confirmed.
	 *
	 * @param plData the {@code PlayerDataManager} container for the player that the armour is worn by.
	 * @param slots the collection of {@code EntityEquipmentSlot}s that the player is wearing of this type. A null parameter in this context represents the method being called for set-effects.<br>
	 *              For example, if a player is wearing boots and legs from the same set, this method will be called with a 4-element HashSet containing FEET, LEGS, BODY, HEAD. It will then be called again with null slots, representing the set-effect call.
	 * @param event the {@code LivingHurtEvent} that was thrown as part of this attack
	 */
	public void onDamageDealt(PlayerDataManager plData, @Nullable HashSet<EntityEquipmentSlot> slots, LivingHurtEvent event) {}

	/**
	 * Called as soon as the player receives an incoming attack. At this stage, the attack isn't considered confirmed, and may still be cancelled or interrupted.<br>
	 *     This method is called at the beginning stages of the attack, and should only be used for monitoring purposes, or if you intend to cancel the event outright.
	 *
	 * @param plData the {@code PlayerDataManager} container for the player that the armour is worn by.
	 * @param slots the collection of {@code EntityEquipmentSlot}s that the player is wearing of this type. A null parameter in this context represents the method being called for set-effects.<br>
	 *              For example, if a player is wearing boots and legs from the same set, this method will be called with a 4-element HashSet containing FEET, LEGS, BODY, HEAD. It will then be called again with null slots, representing the set-effect call.
	 * @param event the {@code LivingAttackEvent} that was thrown as part of this attack
	 */
	public void onPreAttackReceived(PlayerDataManager plData, @Nullable HashSet<EntityEquipmentSlot> slots, LivingAttackEvent event) {}

	/**
	 * Called when an attack is successfully landed on the player. At this stage, the attack is considered final and confirmed, with only the final damage to be confirmed.<br>
	 *     Use this method if you intend to modify the amount of damage to be received by the player. Triggering effects caused by receiving an attack should be done in onPostAttackReceived.
	 *
	 * @param plData the {@code PlayerDataManager} container for the player that the armour is worn by.
	 * @param slots the collection of {@code EntityEquipmentSlot}s that the player is wearing of this type. A null parameter in this context represents the method being called for set-effects.<br>
	 *              For example, if a player is wearing boots and legs from the same set, this method will be called with a 4-element HashSet containing FEET, LEGS, BODY, HEAD. It will then be called again with null slots, representing the set-effect call.
	 * @param event the {@code LivingHurtEvent} that was thrown as part of this attack
	 */
	public void onAttackReceived(PlayerDataManager plData, @Nullable HashSet<EntityEquipmentSlot> slots, LivingHurtEvent event) {}

	/**
	 * Called when an attack is considered successful. Last-second damage modifications can still be made however. Armour damage, enchantments, and potion effect calculations have already taken place.<br>
	 *     Use this method to trigger effects that are to happen when the player receives an attack.
	 *
	 * @param plData the {@code PlayerDataManager} container for the player that the armour is worn by.
	 * @param slots the collection of {@code EntityEquipmentSlot}s that the player is wearing of this type. A null parameter in this context represents the method being called for set-effects.<br>
	 *              For example, if a player is wearing boots and legs from the same set, this method will be called with a 4-element HashSet containing FEET, LEGS, BODY, HEAD. It will then be called again with null slots, representing the set-effect call.
	 * @param event the {@code LivingDamageEvent} that was thrown as part of this attack
	 */
	public void onPostAttackReceived(PlayerDataManager plData, @Nullable HashSet<EntityEquipmentSlot> slots, LivingDamageEvent event) {}

	/**
	 * Called when the player lands from a fall of any height. A fall may not necessarily imply damage being dealt, just that the player has landed.<br>
	 *     This event can be used to modify fall damage, or trigger effects from falling.
	 *
	 * @param plData the {@code PlayerDataManager} container for the player that the armour is worn by.
	 * @param slots the collection of {@code EntityEquipmentSlot}s that the player is wearing of this type. A null parameter in this context represents the method being called for set-effects.<br>
	 *              For example, if a player is wearing boots and legs from the same set, this method will be called with a 4-element HashSet containing FEET, LEGS, BODY, HEAD. It will then be called again with null slots, representing the set-effect call.
	 * @param event the {@code LivingFallEvent} that was thrown as part of this attack
	 */
	public void onPlayerLandingFall(PlayerDataManager plData, @Nullable HashSet<EntityEquipmentSlot> slots, LivingFallEvent event) {}

	/**
	 * Called when the player dies with one or more pieces of Advent Armour equipped.<br>
	 *     This event is not cancellable and should only be used for triggering effects or for any additional tasks to be completed at the time of death.
	 *
	 * @param plData the {@code PlayerDataManager} container for the player that the armour is worn by.
	 * @param slots the collection of {@code EntityEquipmentSlot}s that the player is wearing of this type. A null parameter in this context represents the method being called for set-effects.<br>
	 *              For example, if a player is wearing boots and legs from the same set, this method will be called with a 4-element HashSet containing FEET, LEGS, BODY, HEAD. It will then be called again with null slots, representing the set-effect call.
	 * @param event the {@code LivingDeathEvent} that was thrown as part of this attack
	 */
	public void onPlayerDeath(PlayerDataManager plData, @Nullable HashSet<EntityEquipmentSlot> slots, LivingDeathEvent event) {}

	@Override
	public boolean getIsRepairable(ItemStack stack, ItemStack repairMaterial) {
		return false;
	}

	public boolean isSetHelmet(AdventArmour helmet) {
		return this.setType() == helmet.setType() || helmet.setType() == Enums.ArmourSets.ALL;
	}

	protected String setEffectHeader() {
		return StringUtil.getColourLocaleString("items.description.armour.set", TextFormatting.GOLD);
	}

	protected String pieceEffectHeader() {
		return StringUtil.getColourLocaleString("items.description.armour.piece", TextFormatting.GRAY);
	}

	protected String anySetEffectHeader() {
		return StringUtil.getColourLocaleString("items.description.armour.anySet", TextFormatting.DARK_AQUA);
	}
}
