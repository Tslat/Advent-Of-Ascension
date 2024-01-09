package net.tslat.aoa3.common.networking.packets.patchouli;

import com.mojang.brigadier.tree.CommandNode;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.networking.packets.AoAPacket;
import net.tslat.aoa3.common.registration.AoARegistries;

public record GivePatchouliBookPacket(ResourceLocation book) implements AoAPacket<PlayPayloadContext> {
	public static final ResourceLocation ID = AdventOfAscension.id("give_patchouli_book");

	@Override
	public ResourceLocation id() {
		return ID;
	}

	@Override
	public void write(FriendlyByteBuf buffer) {
		buffer.writeResourceLocation(this.book);
	}

	public static GivePatchouliBookPacket decode(FriendlyByteBuf buffer) {
		return new GivePatchouliBookPacket(buffer.readResourceLocation());
	}

	@Override
	public void receiveMessage(PlayPayloadContext context) {
		Item guideBook = AoARegistries.ITEMS.getEntry(new ResourceLocation("patchouli", "guide_book"));

		if (guideBook != null && guideBook != Items.AIR) {
			ItemStack book = new ItemStack(guideBook);
			ServerPlayer pl = (ServerPlayer)context.player().get();
			CommandSourceStack commandSource = pl.createCommandSourceStack();
			CommandNode<CommandSourceStack> giveCommand = pl.getServer().getCommands().getDispatcher().getRoot().getChild("give");

			book.getOrCreateTag().putString("patchouli:book", this.book.toString());

			if ((giveCommand != null && giveCommand.canUse(commandSource)) || commandSource.hasPermission(pl.getServer().getOperatorUserPermissionLevel())) {
				if (pl.getInventory().add(book) && book.isEmpty()) {
					book.setCount(1);

					ItemEntity itemEntity = pl.drop(book, false);

					if (itemEntity != null)
						itemEntity.makeFakeItem();

					pl.level().playSound(null, pl.getX(), pl.getY(), pl.getZ(), SoundEvents.ITEM_PICKUP, SoundSource.PLAYERS, 0.2f, ((pl.getRandom().nextFloat() - pl.getRandom().nextFloat()) * 0.7f + 1) * 2f);
					pl.containerMenu.broadcastChanges();
				}
				else {
					ItemEntity itemEntity = pl.drop(book, false);

					if (itemEntity != null) {
						itemEntity.setNoPickUpDelay();
						itemEntity.setThrower(pl);
					}
				}
			}
		}
	}
}
