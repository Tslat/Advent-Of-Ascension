/*
package net.tslat.aoa3.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.ResourceLocationException;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraftforge.server.ServerLifecycleHooks;
import net.minecraftforge.server.command.EnumArgument;
import net.tslat.aoa3.command.argument.StructureIdArgument;

import java.util.Optional;

public class StructuresCommand implements Command<CommandSource> {
	private static final StructuresCommand CMD = new StructuresCommand();

	public static ArgumentBuilder<CommandSource, ?> register(CommandDispatcher<CommandSource> dispatcher) {
		LiteralArgumentBuilder<CommandSource> builder = Commands.literal("structures").requires(sender -> sender.hasPermission(4)).executes(CMD);

		builder.then(Commands.literal("generate")
				.then(Commands.argument("structure_id", StructureIdArgument.instance())
						.then(Commands.argument("position", BlockPosArgument.blockPos())
								.executes(context -> spawnStructure(context, Mirror.NONE, Rotation.NONE, false)))
								.then(Commands.argument("mirror", EnumArgument.enumArgument(Mirror.class))
										.executes(context -> spawnStructure(context, context.getArgument("mirror", Mirror.class), Rotation.NONE, false)))
										.then(Commands.argument("rotation", EnumArgument.enumArgument(Rotation.class))
												.executes(context -> spawnStructure(context, context.getArgument("mirror", Mirror.class), context.getArgument("rotation", Rotation.class), false)))
												.then(Commands.argument("ignore_entities", BoolArgumentType.bool())
														.executes(context -> spawnStructure(context, context.getArgument("mirror", Mirror.class), context.getArgument("rotation", Rotation.class), context.getArgument("ignore_entities", Boolean.class))))));

		return builder;
	}

	private static int spawnStructure(CommandContext<CommandSource> cmd, Mirror mirror, Rotation rotation, boolean ignoreEntities) throws CommandSyntaxException {
		try {
			ResourceLocation id = StructureIdArgument.getStructureId(cmd, "structure_id");
			Optional<StructureTemplate> template = ServerLifecycleHooks.getCurrentServer().getStructureManager().get(id);
			BlockPos spawnPos = cmd.getArgument("position", ILocationArgument.class).getBlockPos(cmd.getSource());

			if (template.isEmpty()) {
				AoACommand.feedback(cmd.getSource(), "Structures", "command.aoa.structures.invalidStructure", AoACommand.CommandFeedbackType.WARN, Component.literal(id.toString()));

				return 1;
			}
			else {
				template.placeInWorldChunk(cmd.getSource().getLevel(), spawnPos, new StructurePlaceSettings().setMirror(mirror).setRotation(rotation).setIgnoreEntities(ignoreEntities).setFinalizeEntities(!ignoreEntities).setChunkPos(null), new Random());
			}

			AoACommand.feedback(cmd.getSource(), "Structures", "command.aoa.structures.spawn", AoACommand.CommandFeedbackType.SUCCESS, Component.literal(id.toString()), Component.literal(spawnPos.getX() + ", " + spawnPos.getY() + ", " + spawnPos.getZ()));
		}
		catch (ResourceLocationException ex) {
			AoACommand.error(cmd.getSource(), "Structures", ex.getLocalizedMessage());
		}

		return 1;
	}

	@Override
	public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
		AoACommand.feedback(context.getSource(), "Structures", "command.aoa.structures.desc", AoACommand.CommandFeedbackType.INFO);

		return 1;
	}
}
*/
