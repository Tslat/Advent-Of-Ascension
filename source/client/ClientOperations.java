package net.tslat.aoa3.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.BookViewScreen;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.ClientHooks;
import net.tslat.aoa3.client.gui.adventgui.AdventGuiTabLore;
import net.tslat.aoa3.client.gui.container.SelectInventoryItemScreen;
import net.tslat.aoa3.client.gui.hud.AoACameraModifications;
import net.tslat.aoa3.client.gui.realmstone.BlankRealmstoneScreen;
import net.tslat.aoa3.client.render.entity.misc.OccultBlockRenderer;
import net.tslat.aoa3.common.networking.AoANetworking;
import net.tslat.aoa3.common.networking.packets.UpdateClientMovementPacket;
import net.tslat.aoa3.common.networking.packets.adventplayer.SyncAoAAbilityDataPacket;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.item.misc.WornBook;
import net.tslat.aoa3.content.item.tool.pickaxe.OccultPickaxe;
import net.tslat.aoa3.player.ability.AoAAbility;
import net.tslat.aoa3.util.RegistryUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.OptionalDouble;

public final class ClientOperations {
	public static Level getLevel() {
		return Minecraft.getInstance().level;
	}

	public static Player getPlayer() {
		return Minecraft.getInstance().player;
	}

	public static boolean isLocalServer() {
		return Minecraft.getInstance().isLocalServer();
	}

	public static void displayWornBookGui() {
		Player player = Minecraft.getInstance().player;
		ItemStack bookStack = player.getMainHandItem().getItem() == AoAItems.WORN_BOOK.get() ? player.getMainHandItem() : player.getOffhandItem();

		Minecraft.getInstance().setScreen(new BookViewScreen(BookViewScreen.BookAccess.fromItem(WornBook.makeBook())));
	}

	public static void displayBlankRealmstoneGui() {
		Minecraft.getInstance().setScreen(new BlankRealmstoneScreen());
	}

	public static void addRecoil(final float vertical, final float horizontal) {
		AoACameraModifications.addScreenRecoil(vertical, horizontal);
	}

	public static void addScreenShake(final double frequency, final float strength, final float dampening) {
		AoACameraModifications.addScreenShake(frequency, strength, dampening);
	}

	public static void addOccultBlocks(int renderUntil, List<OccultPickaxe.LocatedBlock> blocks) {
		OccultBlockRenderer.addOccultBlocks(renderUntil, blocks);
	}

	public static void syncModonomiconBooks(List<ResourceLocation> books) {
		AdventGuiTabLore.syncBooks(books);
	}

	public static void adjustPlayerMovement(OptionalDouble x, OptionalDouble y, OptionalDouble z, UpdateClientMovementPacket.Operation operation) {
		Player player = Minecraft.getInstance().player;
		Vec3 velocity = player.getDeltaMovement();

		switch (operation) {
			case SET -> player.setDeltaMovement(x.orElseGet(velocity::x), y.orElseGet(velocity::y), z.orElseGet(velocity::z));
			case ADD -> player.setDeltaMovement(velocity.add(x.orElse(0), y.orElse(0), z.orElse(0)));
			case MULTIPLY -> player.setDeltaMovement(velocity.multiply(x.orElse(1), y.orElse(1), z.orElse(1)));
			case MAX -> player.setDeltaMovement(Math.min(x.orElseGet(velocity::x), velocity.x), Math.min(y.orElseGet(velocity::y), velocity.y), Math.min(z.orElseGet(velocity::z), velocity.z));
			case MIN -> player.setDeltaMovement(Math.max(x.orElseGet(velocity::x), velocity.x), Math.max(y.orElseGet(velocity::y), velocity.y), Math.max(z.orElseGet(velocity::z), velocity.z));
		}
	}

	public static boolean harvestAdditionalBlock(BlockPos breakPos) {
		if (Minecraft.getInstance().gameMode == null)
			return false;

		return Minecraft.getInstance().gameMode.destroyBlock(breakPos);
	}

	public static GameType getGameMode() {
		return Minecraft.getInstance().gameMode.getPlayerMode();
	}

	public static boolean isPressingCrouchKey() {
		return InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), Minecraft.getInstance().options.keyShift.getKey().getValue());
	}

	public static void applyFluidRenderType(LiquidBlock liquid) {
		ItemBlockRenderTypes.setRenderLayer(liquid.fluid.getFlowing(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(liquid.fluid.getSource(), RenderType.translucent());
	}

	public static boolean doAutoHarvestingTrashSelection(AoAAbility.Instance ability, @Nullable Item consumingItem) {
		Minecraft mc = Minecraft.getInstance();

		if (ClientOperations.isPressingCrouchKey()) {
			ClientHooks.pushGuiLayer(mc, new SelectInventoryItemScreen(mc, consumingItem, item -> AoANetworking.sendToServer(new SyncAoAAbilityDataPacket(ability, RegistryUtil.getId(item).toString()))));

			return false;
		}

		return true;
	}
}
