package net.tslat.aoa3.util;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;

/**
 * Over-engineered helper class designed to make concise and correct return calls for the various {@link net.minecraft.world.InteractionResult InteractionResult}
 * usages.<br>
 * This primarily exists because Mojang made this as confusing as possible.
 */
public final class InteractionResults {
	/**
	 * Specifically for {@link net.minecraft.world.item.Item#use(Level, Player, InteractionHand) Item.use}<br>
	 * The stack returned here will be the item left in the player's hand after completion.
	 */
	public static class ItemUse {
		/**
		 * Use if you want to count the call as a success (Your intended functionality occurred successfully),
		 * and want the player's arm to swing.<br>
		 * Only use if returning a different value on the opposing logical side to prevent wasteful double-swinging.<br>
		 * <br>
		 * <b>Properties</b>:
		 * <p>
		 *     On server:
		 *     <ul>
		 *         <li>{@link Player#swing(InteractionHand) Swings} the player's arm</li>
		 *         <li>Swaps the player's held {@link ItemStack} out with the returned stack, if different to the original</li>
		 *     </ul>
		 *
		 *     On client:
		 *     <ul>
		 *         <li>Swings the player's arm</li>
		 *         <li>Prevents the next hand from being checked for action</li>
		 *     </ul>
		 * </p>
		 *
		 */
		public static InteractionResultHolder<ItemStack> succeedAndSwingArmOneSide(ItemStack stack) {
			return InteractionResultHolder.success(stack);
		}

		/**
		 * Equivalent to {@link ItemUse#succeedAndSwingArmOneSide(ItemStack) ItemUse.succeedAndSwingArmOneSide} in functionality,
		 * but specifically when using this single return call for both server and client logical sides
		 */
		public static InteractionResultHolder<ItemStack> succeedAndSwingArmBothSides(ItemStack stack, boolean clientSide) {
			return InteractionResultHolder.sidedSuccess(stack, clientSide);
		}

		/**
		 * Use if you want to count the call as a success (Your intended functionality occurred successfully),
		 * but <u>don't</u> want the player's arm to swing.<br>
		 * <br>
		 * <b>Properties</b>:
		 * <p>
		 *     On server:
		 *     <ul>
		 *         <li>Swaps the player's held {@link ItemStack} out with the returned stack, if different to the original</li>
		 *     </ul>
		 *
		 *     On client:
		 *     <ul>
		 *         <li>Prevents the next hand from being checked for action</li>
		 *     </ul>
		 * </p>
		 *
		 */
		public static InteractionResultHolder<ItemStack> succeedNoArmSwing(ItemStack stack) {
			return InteractionResultHolder.consume(stack);
		}

		/**
		 * Use if you want to count the call as a miss and that you took no action and don't care about the result of this call<br>
		 * <br>
		 * <b>Properties:</b>
		 * <p>
		 *     On server:
		 *     <ul>
		 *         <li>Swaps the player's held {@link ItemStack} out with the returned stack, if different to the original</li>
		 *     </ul>
		 *
		 *     On client:
		 *     <ul>
		 *         <li>Lets the client check the next hand for action</li>
		 *     </ul>
		 * </p>
		 */
		public static InteractionResultHolder<ItemStack> noActionTaken(ItemStack stack) {
			return InteractionResultHolder.pass(stack);
		}

		/**
		 * Use if you want to count the call as a failure<br>
		 * <br>
		 * <b>Properties:</b>
		 * <p>
		 *     On server:
		 *     <ul>
		 *         <li>Discards the returned {@link ItemStack} if the item has a {@link net.minecraft.world.item.Item#getUseDuration(ItemStack) held-usage functionality}</li>
		 *     </ul>
		 *
		 *     On client:
		 *     <ul>
		 *         <li>Lets the client check the next hand for action</li>
		 *     </ul>
		 * </p>
		 */
		public static InteractionResultHolder<ItemStack> denyUsage(ItemStack stack) {
			return InteractionResultHolder.fail(stack);
		}
	}

	/**
	 * Specifically for {@link net.minecraftforge.common.extensions.IForgeItem#onItemUseFirst(ItemStack, UseOnContext) IForgeItem.onItemUseFirst}
	 */
	public static class ItemUseFirst {
		/**
		 * Use this if your item completed some functionality as a result of this call.<br>
		 * <br>
		 * <b>Properties:</b>
		 * <p>
		 *     On server:
		 *     <ul>
		 *         <li>Adds a point to the {@link net.minecraft.stats.Stats#ITEM_USED Stats.ITEM_USED} statistic for this item</li>
		 *         <li>Prevents any further block interaction functionality (such as {@link BlockState#use})</li>
		 *     </ul>
		 *     On client:
		 *     <ul>
		 *         <li>Prevents any further block interaction functionality (such as {@link BlockState#use})</li>
		 *     </ul>
		 * </p>
		 */
		public static InteractionResult itemCompletedFunctionality() {
			return InteractionResult.SUCCESS;
		}

		/**
		 * Use this if you want to prevent the {@link BlockState#use block interaction} from going ahead, but don't want to count it as an item usage for the purpose of {@link net.minecraft.stats.Stats#ITEM_USED statistics}<br>
		 * <br>
		 * <b>Properties:</b>
		 * <p>
		 *     On both client and server:
		 *     <ul>
		 *         <li>Prevents any further block interaction functionality (such as {@link BlockState#use})</li>
		 *     </ul>
		 * </p>
		 */
		public static InteractionResult cancelBlockUsage() {
			return InteractionResult.FAIL;
		}

		/**
		 * Use this if your item didn't get used, and you don't want to prevent any further action from taking place (I.E. pretend your call never happened)<br>
		 * <br>
		 * <b>Properties:</b>
		 * <p>
		 *     On both client and server:
		 *     <ul>
		 *         <li>The game continues on to {@link BlockState#use interact with} the clicked block</li>
		 *     </ul>
		 * </p>
		 */
		public static InteractionResult noActionTaken() {
			return InteractionResult.PASS;
		}
	}

	/**
	 * Specifically for {@link net.minecraft.world.item.Item#useOn Item.useOn}
	 */
	public static class ItemUseOn {
		/**
		 * Use if you want to count the call as a success (Your intended functionality occurred successfully),
		 * and want the player's arm to swing.<br>
		 * Only use if returning a different value on the opposing logical side to prevent wasteful double-swinging.<br>
		 * <br>
		 * <b>Properties</b>:
		 * <p>
		 *     On server:
		 *     <ul>
		 *         <li>{@link Player#swing(InteractionHand) Swings} the player's arm</li>
		 *         <li>Triggers {@link net.minecraft.advancements.CriteriaTriggers#ITEM_USED_ON_BLOCK ITEM_USED_ON_BLOCK} advancement trigger</li>
		 *     </ul>
		 *
		 *     On client:
		 *     <ul>
		 *         <li>Swings the player's arm</li>
		 *         <li>Prevents the next hand from being checked for action</li>
		 *     </ul>
		 * </p>
		 *
		 */
		public static InteractionResult succeedAndSwingArmOneSide() {
			return InteractionResult.SUCCESS;
		}

		/**
		 * Equivalent to {@link ItemUseOn#succeedAndSwingArmOneSide ItemUseOn.succeedAndSwingArmOneSide} in functionality,
		 * but specifically when using this single return call for both server and client logical sides
		 */
		public static InteractionResult succeedAndSwingArmBothSides(boolean clientSide) {
			return InteractionResult.sidedSuccess(clientSide);
		}

		/**
		 * Use this if you want to count the call as a success (Your intended functionality occured successfully),
		 * but don't want the player's arm to swing or an {@link net.minecraft.stats.Stats#ITEM_USED ITEM_USED} stat point to be added.<br>
		 * Normally this is used for items with secondary functions (such as a {@link net.minecraft.world.item.BlockItem BlockItem} that can also be eaten in-hand
		 */
		public static InteractionResult succeedNoSwingOrStats() {
			return InteractionResult.CONSUME_PARTIAL;
		}

		/**
		 * Use if you want to count the call as a success (Your intended functionality occurred successfully),
		 * but <u>don't</u> want the player's arm to swing.<br>
		 * <br>
		 * <b>Properties</b>:
		 * <p>
		 *     On server:
		 *     <ul>
		 *         <li>Triggers {@link net.minecraft.advancements.CriteriaTriggers#ITEM_USED_ON_BLOCK ITEM_USED_ON_BLOCK} advancement trigger</li>
		 *     </ul>
		 *
		 *     On client:
		 *     <ul>
		 *         <li>Prevents the next hand from being checked for action</li>
		 *     </ul>
		 * </p>
		 *
		 */
		public static InteractionResult succeedNoArmSwing() {
			return InteractionResult.CONSUME;
		}

		/**
		 * Use if you want to count the call as a miss and that you took no action and don't care about the result of this call<br>
		 * <br>
		 * <b>Properties:</b>
		 * <p>
		 *     On client:
		 *     <ul>
		 *         <li>Lets the client check the next hand for action</li>
		 *     </ul>
		 * </p>
		 */
		public static InteractionResult noActionTaken() {
			return InteractionResult.PASS;
		}

		/**
		 * Use if you want to count the call as a failure (E.G. some conditions weren't met)<br>
		 * <br>
		 * <b>Properties:</b>
		 * <p>
		 *     On client:
		 *     <ul>
		 *         <li>Prevents the next hand from being checked for action</li>
		 *     </ul>
		 * </p>
		 *
		 */
		public static InteractionResult denyUsage() {
			return InteractionResult.FAIL;
		}
	}

	/**
	 * Specifically for {@link net.minecraft.world.level.block.Block#use Block.use}
	 */
	public static class BlockUse {
		/**
		 * Use if you want to count the call as a success (Your intended functionality occurred successfully),
		 * and want the player's arm to swing.<br>
		 * Only use if returning a different value on the opposing logical side to prevent wasteful double-swinging.<br>
		 * <br>
		 * <b>Properties</b>:
		 * <p>
		 *     On server:
		 *     <ul>
		 *         <li>{@link Player#swing(InteractionHand) Swings} the player's arm</li>
		 *         <li>Triggers {@link net.minecraft.advancements.CriteriaTriggers#ITEM_USED_ON_BLOCK ITEM_USED_ON_BLOCK} advancement trigger</li>
		 *         <li>Prevents the game from proceeding onto trigger {@link ItemStack#useOn} for the current hand</li>
		 *     </ul>
		 *
		 *     On client:
		 *     <ul>
		 *         <li>Swings the player's arm</li>
		 *         <li>Prevents the next hand from being checked for action</li>
		 *         <li>Prevents the game from proceeding onto trigger {@link ItemStack#useOn} for the current hand</li>
		 *     </ul>
		 * </p>
		 *
		 */
		public static InteractionResult succeedAndSwingArmOneSide() {
			return InteractionResult.SUCCESS;
		}

		/**
		 * Equivalent to {@link BlockUse#succeedAndSwingArmOneSide BlockUse.succeedAndSwingArmOneSide} in functionality,
		 * but specifically when using this single return call for both server and client logical sides
		 */
		public static InteractionResult succeedAndSwingArmBothSides(boolean clientSide) {
			return InteractionResult.sidedSuccess(clientSide);
		}

		/**
		 * Use if you want to count the call as a success (Your intended functionality occurred successfully),
		 * but <u>don't</u> want the player's arm to swing.<br>
		 * <br>
		 * <b>Properties</b>:
		 * <p>
		 *     On server:
		 *     <ul>
		 *         <li>Triggers {@link net.minecraft.advancements.CriteriaTriggers#ITEM_USED_ON_BLOCK ITEM_USED_ON_BLOCK} advancement trigger</li>
		 *         <li>Prevents the game from proceeding onto trigger {@link ItemStack#useOn} for the current hand</li>
		 *     </ul>
		 *
		 *     On client:
		 *     <ul>
		 *         <li>Prevents the next hand from being checked for action</li>
		 *         <li>Prevents the game from proceeding onto trigger {@link ItemStack#useOn} for the current hand</li>
		 *     </ul>
		 * </p>
		 *
		 */
		public static InteractionResult succeedNoArmSwing() {
			return InteractionResult.CONSUME;
		}

		/**
		 * Use if you want to count the call as a miss and that you took no action and don't care about the result of this call<br>
		 * <br>
		 * <b>Properties:</b>
		 * <p>
		 *     On client:
		 *     <ul>
		 *         <li>Lets the client check the next hand for action</li>
		 *     </ul>
		 * </p>
		 */
		public static InteractionResult noActionTaken() {
			return InteractionResult.PASS;
		}

		/**
		 * Use if you want to count the call as a failure (E.G. some conditions weren't met)<br>
		 * <br>
		 * <b>Properties:</b>
		 * <p>
		 *     On client:
		 *     <ul>
		 *         <li>Prevents the next hand from being checked for action</li>
		 *     </ul>
		 * </p>
		 *
		 */
		public static InteractionResult denyUsage() {
			return InteractionResult.FAIL;
		}
	}

	/**
	 * Specifically for {@link net.minecraft.world.item.Item#interactLivingEntity Item.interactLivingEntity}
	 */
	public static class ItemInteractLivingEntity {
		/**
		 * Use if you want to count the call as a success (Your intended functionality occurred successfully),
		 * and want the player's arm to swing.<br>
		 * Only use if returning a different value on the opposing logical side to prevent wasteful double-swinging.<br>
		 * <br>
		 * <b>Properties</b>:
		 * <p>
		 *     On server:
		 *     <ul>
		 *         <li>{@link Player#swing(InteractionHand) Swings} the player's arm</li>
		 *         <li>Fires the {@link PlayerDestroyItemEvent} if the held stack is consumed in the call</li>
		 *         <li>Triggers the {@link net.minecraft.advancements.CriteriaTriggers#PLAYER_INTERACTED_WITH_ENTITY CriteriaTriggers.PLAYER_INTERACTED_WITH_ENTITY} advancement trigger</li>
		 *         <li>Sends the {@link GameEvent#ENTITY_INTERACT} game event</li>
		 *     </ul>
		 *
		 *     On client:
		 *     <ul>
		 *         <li>Swings the player's arm</li>
		 *         <li>Prevents the next hand from being checked for action</li>
		 *         <li>Prevents {@link Item#use} from being called</li>
		 *     </ul>
		 * </p>
		 *
		 */
		public static InteractionResult succeedAndSwingArmOneSide() {
			return InteractionResult.SUCCESS;
		}

		/**
		 * Equivalent to {@link ItemInteractLivingEntity#succeedAndSwingArmOneSide} in functionality,
		 * but specifically when using this single return call for both server and client logical sides
		 */
		public static InteractionResult succeedAndSwingArmBothSides(boolean clientSide) {
			return InteractionResult.sidedSuccess(clientSide);
		}

		/**
		 * Equivalent to {@link ItemInteractLivingEntity#succeedAndSwingArmOneSide} except that the player's arm does not swing
		 */
		public static InteractionResult succeedNoArmSwing() {
			return InteractionResult.CONSUME;
		}

		/**
		 * Use this if your function didn't take effect (either through not meeting the required conditions, or through failure)
		 */
		public static InteractionResult noActionTaken() {
			return InteractionResult.PASS;
		}
	}

	/**
	 * Specifically for {@link net.minecraft.world.entity.Entity#interact Entity.interact}
	 */
	public static class EntityInteract {
		/**
		 * Use if you want to count the call as a success (Your intended functionality occurred successfully),
		 * and want the player's arm to swing.<br>
		 * Only use if returning a different value on the opposing logical side to prevent wasteful double-swinging.<br>
		 * <br>
		 * <b>Properties</b>:
		 * <p>
		 *     On server:
		 *     <ul>
		 *         <li>{@link Player#swing(InteractionHand) Swings} the player's arm</li>
		 *         <li>Fires the {@link PlayerDestroyItemEvent} if the held stack is consumed in the call</li>
		 *         <li>Triggers the {@link net.minecraft.advancements.CriteriaTriggers#PLAYER_INTERACTED_WITH_ENTITY CriteriaTriggers.PLAYER_INTERACTED_WITH_ENTITY} advancement trigger</li>
		 *     </ul>
		 *
		 *     On client:
		 *     <ul>
		 *         <li>Swings the player's arm</li>
		 *         <li>Prevents the next hand from being checked for action</li>
		 *         <li>Prevents {@link Item#use} from being called</li>
		 *     </ul>
		 * </p>
		 *
		 */
		public static InteractionResult succeedAndSwingArmOneSide() {
			return InteractionResult.SUCCESS;
		}

		/**
		 * Equivalent to {@link EntityInteract#succeedAndSwingArmOneSide} in functionality,
		 * but specifically when using this single return call for both server and client logical sides
		 */
		public static InteractionResult succeedAndSwingArmBothSides(boolean clientSide) {
			return InteractionResult.sidedSuccess(clientSide);
		}

		/**
		 * Equivalent to {@link EntityInteract#succeedAndSwingArmOneSide} except that the player's arm does not swing
		 */
		public static InteractionResult succeedNoArmSwing() {
			return InteractionResult.CONSUME;
		}

		/**
		 * Use this if your function didn't take effect (either through not meeting the required conditions, or through failure).<br>
		 * <br>
		 * <b>Properties:</b>
		 * <ul>
		 *     <li>Proceeds onto {@link Item#interactLivingEntity}</li>
		 * </ul>
		 */
		public static InteractionResult noActionTaken() {
			return InteractionResult.PASS;
		}
	}

	/**
	 * Specifically for {@link net.minecraft.world.entity.Entity#interactAt Entity.interactAt}.<br>
	 * This is almost completely identical to {@link EntityInteract}, with the single difference being that
	 * an unsuccessful response just forwards the call on to {@link net.minecraft.world.entity.Entity#interact Entity.interact}.<br>
	 * Refer to {@link EntityInteract} for responses not listed below
	 */
	public static class EntityInteractAt {
		/**
		 * Use this if you don't have any specific action to take.<br>
		 * This just pushes the game onto Entity.interact, so any further responses can be referred to from {@link EntityInteract}.<br>
		 * <br>
		 * <b>NOTE:</b> This only passes onto Entity.interact if used on the client-side.
		 * However, you should still return this on the server side for consistency if returning it on the client side
		 */
		public static InteractionResult noActionTaken() {
			return InteractionResult.PASS;
		}
	}

	/**
	 * Specifically for {@link net.minecraft.world.entity.Mob#mobInteract Mob.mobInteract}.<br>
	 * This is technically a subsidiary of {@link net.minecraft.world.entity.Entity#interact Entity.interact},
	 * but contains an additional property if returning a successful response.
	 */
	public static class MobInteract {
		/**
		 * Use if you want to count the call as a success (Your intended functionality occurred successfully),
		 * and want the player's arm to swing.<br>
		 * Only use if returning a different value on the opposing logical side to prevent wasteful double-swinging.<br>
		 * <br>
		 * <b>Properties</b>:
		 * <p>
		 *     On server:
		 *     <ul>
		 *         <li>{@link Player#swing(InteractionHand) Swings} the player's arm</li>
		 *         <li>Fires the {@link PlayerDestroyItemEvent} if the held stack is consumed in the call</li>
		 *         <li>Triggers the {@link net.minecraft.advancements.CriteriaTriggers#PLAYER_INTERACTED_WITH_ENTITY CriteriaTriggers.PLAYER_INTERACTED_WITH_ENTITY} advancement trigger</li>
		 *         <li>Fires the {@link GameEvent#ENTITY_INTERACT} GameEvent</li>
		 *     </ul>
		 *
		 *     On client:
		 *     <ul>
		 *         <li>Swings the player's arm</li>
		 *         <li>Prevents the next hand from being checked for action</li>
		 *         <li>Prevents {@link Item#use} from being called</li>
		 *     </ul>
		 * </p>
		 *
		 */
		public static InteractionResult succeedAndSwingArmOneSide() {
			return InteractionResult.SUCCESS;
		}

		/**
		 * Equivalent to {@link MobInteract#succeedAndSwingArmOneSide} in functionality,
		 * but specifically when using this single return call for both server and client logical sides
		 */
		public static InteractionResult succeedAndSwingArmBothSides(boolean clientSide) {
			return InteractionResult.sidedSuccess(clientSide);
		}

		/**
		 * Equivalent to {@link MobInteract#succeedAndSwingArmOneSide} except that the player's arm does not swing
		 */
		public static InteractionResult succeedNoArmSwing() {
			return InteractionResult.CONSUME;
		}

		/**
		 * Use this if your function didn't take effect (either through not meeting the required conditions, or through failure).<br>
		 * <br>
		 * <b>Properties:</b>
		 * <ul>
		 *     <li>Proceeds onto {@link Item#interactLivingEntity}</li>
		 * </ul>
		 */
		public static InteractionResult noActionTaken() {
			return InteractionResult.PASS;
		}
	}

	/**
	 * Specifically for {@link net.minecraft.world.item.BlockItem#place BlockItem.place}.<br>
	 * This is a subsidiary of {@link net.minecraft.world.item.Item#useOn Item.useOn}.<br>
	 * Refer to {@link ItemUseOn} for responses, with the single exception of {@link BlockItemPlace#failAndCheckFood}
	 */
	public static class BlockItemPlace {
		/**
		 * Use this if your BlockItem failed to place, but it is also {@link net.minecraft.world.item.Item#isEdible() edible}.<br>
		 * The game will then proceed to check for eating actions.<br>
		 * <br>
		 * For everything else, use {@link ItemUseOn}'s return options
		 */
		public static InteractionResult failAndCheckFood() {
			return InteractionResult.FAIL;
		}
	}
}
