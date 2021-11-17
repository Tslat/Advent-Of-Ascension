package net.tslat.aoa3.common.packet.packets;

import com.mojang.brigadier.tree.CommandNode;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class PatchouliGiveBookPacket implements AoAPacket {
	private final ResourceLocation id;

	public PatchouliGiveBookPacket(ResourceLocation bookId) {
		this.id = bookId;
	}

	@Override
	public void encode(PacketBuffer buffer) {
		buffer.writeResourceLocation(id);
	}

	public static PatchouliGiveBookPacket decode(PacketBuffer buffer) {
		return new PatchouliGiveBookPacket(buffer.readResourceLocation());
	}

	public void receiveMessage(Supplier<NetworkEvent.Context> context) {
		Item guideBook = ForgeRegistries.ITEMS.getValue(new ResourceLocation("patchouli", "guide_book"));

		if (guideBook != null && guideBook != Items.AIR) {
			ItemStack book = new ItemStack(guideBook);
			ServerPlayerEntity pl = context.get().getSender();
			CommandSource commandSource = pl.createCommandSourceStack();
			CommandNode<CommandSource> giveCommand = pl.getServer().getCommands().getDispatcher().getRoot().getChild("give");

			book.getOrCreateTag().putString("patchouli:book", id.toString());

			if ((giveCommand != null && giveCommand.canUse(commandSource)) || commandSource.hasPermission(pl.getServer().getOperatorUserPermissionLevel())) {
				if (pl.inventory.add(book) && book.isEmpty()) {
					book.setCount(1);

					ItemEntity itemEntity = pl.drop(book, false);

					if (itemEntity != null)
						itemEntity.makeFakeItem();

					pl.level.playSound(null, pl.getX(), pl.getY(), pl.getZ(), SoundEvents.ITEM_PICKUP, SoundCategory.PLAYERS, 0.2f, ((pl.getRandom().nextFloat() - pl.getRandom().nextFloat()) * 0.7f + 1) * 2f);
					pl.containerMenu.broadcastChanges();
				}
				else {
					ItemEntity itemEntity = pl.drop(book, false);

					if (itemEntity != null) {
						itemEntity.setNoPickUpDelay();
						itemEntity.setOwner(pl.getUUID());
					}
				}
			}
		}

		context.get().setPacketHandled(true);
	}
}
