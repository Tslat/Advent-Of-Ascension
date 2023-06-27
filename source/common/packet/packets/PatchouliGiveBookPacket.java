package net.tslat.aoa3.common.packet.packets;

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
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class PatchouliGiveBookPacket implements AoAPacket {
	private final ResourceLocation id;

	public PatchouliGiveBookPacket(ResourceLocation bookId) {
		this.id = bookId;
	}

	@Override
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeResourceLocation(id);
	}

	public static PatchouliGiveBookPacket decode(FriendlyByteBuf buffer) {
		return new PatchouliGiveBookPacket(buffer.readResourceLocation());
	}

	public void receiveMessage(Supplier<NetworkEvent.Context> context) {
		Item guideBook = ForgeRegistries.ITEMS.getValue(new ResourceLocation("patchouli", "guide_book"));

		if (guideBook != null && guideBook != Items.AIR) {
			ItemStack book = new ItemStack(guideBook);
			ServerPlayer pl = context.get().getSender();
			CommandSourceStack commandSource = pl.createCommandSourceStack();
			CommandNode<CommandSourceStack> giveCommand = pl.getServer().getCommands().getDispatcher().getRoot().getChild("give");

			book.getOrCreateTag().putString("patchouli:book", id.toString());

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
						itemEntity.setThrower(pl.getUUID());
					}
				}
			}
		}

		context.get().setPacketHandled(true);
	}
}
