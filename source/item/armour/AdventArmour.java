package net.tslat.aoa3.item.armour;

import net.minecraft.entity.Entity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.living.*;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.player.PlayerDataManager;

import javax.annotation.Nullable;
import java.util.HashSet;

public abstract class AdventArmour extends ArmorItem {
	public AdventArmour(IArmorMaterial material, EquipmentSlotType slot) {
		super(material, slot, new Item.Properties().tab(AoAItemGroups.ARMOUR).durability(material.getDurabilityForSlot(slot)));
	}

	@Nullable
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
		return null;
	}

	public abstract Type setType();

	/**
	 * Called when the player equips the item or has it placed on their body by a third party method.
	 *
	 * @param plData the {@code PlayerDataManager} container for the player wearing the armour.
	 * @param slot the {@code EquipmentSlotType} slot that the item was equipped into. A null slot in this context is used to indicate that the full set was equipped.<br>
	 *             For example, if a player has the boots, legs, and chestplate of a set equipped, and equips the helmet, this event will be fired twice. Once with the head slot, once with a null slot for completing the set.
	 */
	public void onEquip(PlayerDataManager plData, @Nullable EquipmentSlotType slot) {}

	/**
	 * Called when the player unequips the item or has it removed from their body by a third party method.
	 *
	 * @param plData the {@code PlayerDataManager} container for the player wearing the armour.
	 * @param slot the {@code EquipmentSlotType} slot that the item was unequipped from. A null slot in this context is used to indicate that the full set status was broken.<br>
	 *             For example, if a player has the boots, legs, chestplate, and helmet equipped, then unequips the helmet, this event will be fired twice. Once with the head slot, once with a null slot for breaking the full set.
	 */
	public void onUnequip(PlayerDataManager plData, @Nullable EquipmentSlotType slot) {}

	/**
	 * Called immediately after equip, used to add buffs to the player safely.
	 *
	 * @param plBuffs the {@code PlayerBuffs} container for the player equipping the armour.
	 * @param slot the {@code EquipmentSlotType} slot that the item was equipped into. A null slot in this context is used to indicate that the full set was equipped.<br>
	 *             For example, if a player has the boots, legs, and chestplate of a set equipped, and equips the helmet, this event will be fired twice. Once with the head slot, once with a null slot for completing the set.
	 */
	public void addBuffs(PlayerDataManager.PlayerBuffs plBuffs, @Nullable EquipmentSlotType slot) {}

	/**
	 * Called immediately after unequip, used to remove previously added buffs from the player safely. Make sure that all buffs added in addBuffs are removed here.
	 *
	 * @param plBuffs the {@code PlayerBuffs} container for the player unequipping the armour.
	 * @param slot the {@code EquipmentSlotType} slot that the item was unequipped from. A null slot in this context is used to indicate that the full set status was broken.<br>
	 *             For example, if a player has the boots, legs, chestplate, and helmet equipped, then unequips the helmet, this event will be fired twice. Once with the head slot, once with a null slot for breaking the full set.
	 */
	public void removeBuffs(PlayerDataManager.PlayerBuffs plBuffs, @Nullable EquipmentSlotType slot) {}

	/**
	 * Called each tick during the player's tick cycle for each piece equipped. A null slot indicates that the armour is being ticked for the set's effect.
	 *
	 * @param plData the {@code PlayerDataManager} container for the player that the armour is worn by.
	 * @param slots the collection of {@code EquipmentSlotType}s that the player is wearing of this type. A null parameter in this context represents the method being called for set-effects.<br>
	 *              For example, if a player is wearing boots and legs from the same set, this method will be called with a 4-element HashSet containing FEET, LEGS, BODY, HEAD. It will then be called again with null slots, representing the set-effect call.
	 */
	public void onEffectTick(PlayerDataManager plData, @Nullable HashSet<EquipmentSlotType> slots) {}

	/**
	 * Called when the player successfully lands an attack on another entity via any method. This method is called prior the final damage being dealt to the entity.<br>
	 *     Use to augment the final damage output, or apply/activate any additional effects for damaging an entity.<br>
	 *     At this stage, the attack is considered to be successful, with only the final damage amount to be confirmed.
	 *
	 * @param plData the {@code PlayerDataManager} container for the player that the armour is worn by.
	 * @param slots the collection of {@code EquipmentSlotType}s that the player is wearing of this type. A null parameter in this context represents the method being called for set-effects.<br>
	 *              For example, if a player is wearing boots and legs from the same set, this method will be called with a 4-element HashSet containing FEET, LEGS, BODY, HEAD. It will then be called again with null slots, representing the set-effect call.
	 * @param event the {@code LivingHurtEvent} that was thrown as part of this attack
	 */
	public void onDamageDealt(PlayerDataManager plData, @Nullable HashSet<EquipmentSlotType> slots, LivingHurtEvent event) {}

	/**
	 * Called as soon as the player receives an incoming attack. At this stage, the attack isn't considered confirmed, and may still be cancelled or interrupted.<br>
	 *     This method is called at the beginning stages of the attack, and should only be used for monitoring purposes, or if you intend to cancel the event outright.
	 *
	 * @param plData the {@code PlayerDataManager} container for the player that the armour is worn by.
	 * @param slots the collection of {@code EquipmentSlotType}s that the player is wearing of this type. A null parameter in this context represents the method being called for set-effects.<br>
	 *              For example, if a player is wearing boots and legs from the same set, this method will be called with a 4-element HashSet containing FEET, LEGS, BODY, HEAD. It will then be called again with null slots, representing the set-effect call.
	 * @param event the {@code LivingAttackEvent} that was thrown as part of this attack
	 */
	public void onPreAttackReceived(PlayerDataManager plData, @Nullable HashSet<EquipmentSlotType> slots, LivingAttackEvent event) {}

	/**
	 * Called when an attack is successfully landed on the player. At this stage, the attack is considered final and confirmed, with only the final damage to be confirmed.<br>
	 *     Use this method if you intend to modify the amount of damage to be received by the player. Triggering effects caused by receiving an attack should be done in onPostAttackReceived.
	 *
	 * @param plData the {@code PlayerDataManager} container for the player that the armour is worn by.
	 * @param slots the collection of {@code EquipmentSlotType}s that the player is wearing of this type. A null parameter in this context represents the method being called for set-effects.<br>
	 *              For example, if a player is wearing boots and legs from the same set, this method will be called with a 4-element HashSet containing FEET, LEGS, BODY, HEAD. It will then be called again with null slots, representing the set-effect call.
	 * @param event the {@code LivingHurtEvent} that was thrown as part of this attack
	 */
	public void onAttackReceived(PlayerDataManager plData, @Nullable HashSet<EquipmentSlotType> slots, LivingHurtEvent event) {}

	/**
	 * Called when an attack is considered successful. Last-second damage modifications can still be made however. Armour damage, enchantments, and potion effect calculations have already taken place.<br>
	 *     Use this method to trigger effects that are to happen when the player receives an attack.
	 *
	 * @param plData the {@code PlayerDataManager} container for the player that the armour is worn by.
	 * @param slots the collection of {@code EquipmentSlotType}s that the player is wearing of this type. A null parameter in this context represents the method being called for set-effects.<br>
	 *              For example, if a player is wearing boots and legs from the same set, this method will be called with a 4-element HashSet containing FEET, LEGS, BODY, HEAD. It will then be called again with null slots, representing the set-effect call.
	 * @param event the {@code LivingDamageEvent} that was thrown as part of this attack
	 */
	public void onPostAttackReceived(PlayerDataManager plData, @Nullable HashSet<EquipmentSlotType> slots, LivingDamageEvent event) {}

	/**
	 * Called when the player lands from a fall of any height. A fall may not necessarily imply damage being dealt, just that the player has landed.<br>
	 *     This event can be used to modify fall damage, or trigger effects from falling.
	 *
	 * @param plData the {@code PlayerDataManager} container for the player that the armour is worn by.
	 * @param slots the collection of {@code EquipmentSlotType}s that the player is wearing of this type. A null parameter in this context represents the method being called for set-effects.<br>
	 *              For example, if a player is wearing boots and legs from the same set, this method will be called with a 4-element HashSet containing FEET, LEGS, BODY, HEAD. It will then be called again with null slots, representing the set-effect call.
	 * @param event the {@code LivingFallEvent} that was thrown as part of this attack
	 */
	public void onPlayerLandingFall(PlayerDataManager plData, @Nullable HashSet<EquipmentSlotType> slots, LivingFallEvent event) {}

	/**
	 * Called when the player dies with one or more pieces of Advent Armour equipped.<br>
	 *     This event is not cancellable and should only be used for triggering effects or for any additional tasks to be completed at the time of death.
	 *
	 * @param plData the {@code PlayerDataManager} container for the player that the armour is worn by.
	 * @param slots the collection of {@code EquipmentSlotType}s that the player is wearing of this type. A null parameter in this context represents the method being called for set-effects.<br>
	 *              For example, if a player is wearing boots and legs from the same set, this method will be called with a 4-element HashSet containing FEET, LEGS, BODY, HEAD. It will then be called again with null slots, representing the set-effect call.
	 * @param event the {@code LivingDeathEvent} that was thrown as part of this attack
	 */
	public void onPlayerDeath(PlayerDataManager plData, @Nullable HashSet<EquipmentSlotType> slots, LivingDeathEvent event) {}

	@Override
	public boolean isValidRepairItem(ItemStack stack, ItemStack repairMaterial) {
		return false;
	}

	public boolean isSetHelmet(AdventArmour helmet) {
		return this.setType() == helmet.setType() || helmet.setType() == Type.ALL;
	}

	protected TranslationTextComponent setEffectHeader() {
		return LocaleUtil.getLocaleMessage(LocaleUtil.Constants.ARMOUR_SET_HEADER, TextFormatting.GOLD);
	}

	protected TranslationTextComponent pieceEffectHeader() {
		return LocaleUtil.getLocaleMessage(LocaleUtil.Constants.ARMOUR_PIECE_HEADER, TextFormatting.GRAY);
	}

	protected TranslationTextComponent anySetEffectHeader() {
		return LocaleUtil.getLocaleMessage(LocaleUtil.Constants.ARMOUR_ANY_SET_HEADER, TextFormatting.DARK_AQUA);
	}

	public enum Type {
		ALACRITY,
		ALCHEMY,
		ALL,
		AMETHIND,
		ANIMA,
		ARCHAIC,
		AUGURY,
		BARON,
		BATTLEBORN,
		BIOGENIC,
		BOREIC,
		BUTCHERY,
		CANDY,
		COMMANDER,
		CREATION,
		CRYSTALLIS,
		ELECANYTE,
		EMBRODIUM,
		ENGINEERING,
		EXOPLATE,
		EXPEDITION,
		EXPLOSIVE,
		EXTRACTION,
		FORAGING,
		FUNGAL,
		GHASTLY,
		GHOULISH,
		HAULING,
		HAZMAT,
		HUNTER,
		HYDRANGIC,
		HYDROPLATE,
		ICE,
		INFERNAL,
		INFUSION,
		INNERVATION,
		KNIGHT,
		LOGGING,
		LUNAR,
		LYNDAMYTE,
		LYONIC,
		MERCURIAL,
		NECRO,
		NETHENGEIC,
		NIGHTMARE,
		NONE,
		OMNI,
		PHANTASM,
		POISON,
		PREDATIOUS,
		PRIMORDIAL,
		PURITY,
		ROCKBONE,
		ROSIDIAN,
		RUNATION,
		RUNIC,
		SHARPSHOT,
		SKELETAL,
		SPACEKING,
		SPEED,
		SUBTERRANEAN,
		UTOPIAN,
		VOID,
		WEAKEN,
		WITHER,
		ZARGONITE;
	}

	public enum Overlay {
		NIGHT_VISION_GOGGLES;
	}
}
