package net.tropicraft.core.common.dimension;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.management.PlayerList;
import net.minecraft.world.DimensionType;
import net.minecraft.world.Teleporter;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fml.common.event.FMLInterModComms;

public class TropicraftWorldUtils {

	// TODO make configurable
	public static final int TROPICS_DIMENSION_ID = -127;
	public static final DimensionType tropicsDimension = DimensionType.register("Tropics", "_tropics", -127,
			WorldProviderTropicraft.class, true);

	public static void initializeDimension() {
		DimensionManager.registerDimension(TROPICS_DIMENSION_ID, tropicsDimension);

		NBTTagCompound n = new NBTTagCompound();
		n.setInteger("dim_id", tropicsDimension.getId());
		n.setString("dim_name", tropicsDimension.getName());
		FMLInterModComms.sendMessage(ForgeModContainer.getInstance().getModId(), "loaderFarewellSkip", n);
	}

	public static void teleportPlayer(EntityPlayerMP player) {
		long time = System.currentTimeMillis();
		if (player.dimension == TROPICS_DIMENSION_ID) {
			Teleporter tropicsTeleporter = new TeleporterTropics(player.getServer().worldServerForDimension(0));
			PlayerList pl = player.getServer().getPlayerList();
			pl.transferPlayerToDimension(player, 0, tropicsTeleporter);
		} else {
			Teleporter tropicsTeleporter = new TeleporterTropics(player.getServer().worldServerForDimension(TROPICS_DIMENSION_ID));
			PlayerList pl = player.getServer().getPlayerList();
			pl.transferPlayerToDimension(player, TROPICS_DIMENSION_ID, tropicsTeleporter);
		}

		long time2 = System.currentTimeMillis();

		System.out.printf("It took %f seconds to teleport\n", (time2 - time) / 1000.0F);
	}

}
