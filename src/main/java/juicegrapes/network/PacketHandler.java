package juicegrapes.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import juicegrapes.juicewares;
import juicegrapes.tileentities.TileEntityDrawer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler{

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
		byte packetID;
		
		try {
			packetID = inputStream.readByte();
		} catch(IOException e) {
			e.printStackTrace();
			return;
		}
		
		switch(packetID) {
		case 1:
			handleCabinet1(packet);
			break;
		case 2:
			handleCabinet2(packet);
			break;
		case 3:
			handleCabinet3(packet);
			break;
		case 4:
			handleCabinet4(packet);
			break;
		case 5:
			handleAltar(packet);
			break;
		default:
			break;
		}
		
		
	}
	
	private void handleCabinet1(Packet250CustomPayload packet) {
		DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
		@SuppressWarnings("unused")
		byte packetID;
		int x, y, z, itemID1, metaData1, stackSize1, itemID2, metaData2, stackSize2;
		try {
			packetID = inputStream.readByte();
			x = inputStream.readInt();
			y = inputStream.readInt();
			z = inputStream.readInt();
			itemID1 = inputStream.readInt();
			metaData1 = inputStream.readInt();
			stackSize1 = inputStream.readInt();
			itemID2 = inputStream.readInt();
			metaData2 = inputStream.readInt();
			stackSize2 = inputStream.readInt();
		}	catch(IOException e) {
			e.printStackTrace();
			return;
		}
		
		juicewares.proxy.handleCabinet(x, y, z, itemID1, stackSize1, metaData1, TileEntityDrawer.showSlot);
		juicewares.proxy.handleCabinet(x, y, z, itemID2, stackSize2, metaData2, TileEntityDrawer.showSlot2);
	}

	private void handleCabinet2(Packet250CustomPayload packet) {
		DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
		@SuppressWarnings("unused")
		byte packetID;
		int x, y, z, itemID1, metaData1, stackSize1;
		try {
			packetID = inputStream.readByte();
			x = inputStream.readInt();
			y = inputStream.readInt();
			z = inputStream.readInt();
			itemID1 = inputStream.readInt();
			metaData1 = inputStream.readInt();
			stackSize1 = inputStream.readInt();
		}	catch(IOException e) {
			e.printStackTrace();
			return;
		}
		
		juicewares.proxy.handleCabinet(x, y, z, itemID1, stackSize1, metaData1, TileEntityDrawer.showSlot);
		juicewares.proxy.updateTileEntityDrawer(x, y, z, TileEntityDrawer.showSlot2);
		
	}
	
	private void handleCabinet3(Packet250CustomPayload packet) {
		DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
		@SuppressWarnings("unused")
		byte packetID;
		int x, y, z, itemID2, metaData2, stackSize2;
		try {
			packetID = inputStream.readByte();
			x = inputStream.readInt();
			y = inputStream.readInt();
			z = inputStream.readInt();
			itemID2 = inputStream.readInt();
			metaData2 = inputStream.readInt();
			stackSize2 = inputStream.readInt();
		}	catch(IOException e) {
			e.printStackTrace();
			return;
		}
		
		juicewares.proxy.updateTileEntityDrawer(x, y, z, TileEntityDrawer.showSlot);
		juicewares.proxy.handleCabinet(x, y, z, itemID2, stackSize2, metaData2, TileEntityDrawer.showSlot2);
	}
	
	private void handleCabinet4(Packet250CustomPayload packet) {
		DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
		
		@SuppressWarnings("unused")
		byte packetID;
		int x, y, z;
		try {
			packetID = inputStream.readByte();
			x = inputStream.readInt();
			y = inputStream.readInt();
			z = inputStream.readInt();
		}	catch(IOException e) {
			e.printStackTrace();
			return;
		}
		
		juicewares.proxy.updateTileEntityDrawer(x, y, z, TileEntityDrawer.showSlot);
		juicewares.proxy.updateTileEntityDrawer(x, y, z, TileEntityDrawer.showSlot2);
		
	}
	
	private void handleAltar(Packet250CustomPayload packet) {
		
		DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));

		@SuppressWarnings("unused")
		byte packetID;
		int x, y, z;
		boolean hasStone, hasLens, isNormalLens, stoneDone;
		try {
			packetID = inputStream.readByte();
			x = inputStream.readInt();
			y = inputStream.readInt();
			z = inputStream.readInt();
			hasStone = inputStream.readBoolean();
			hasLens = inputStream.readBoolean();
			isNormalLens = inputStream.readBoolean();
			stoneDone = inputStream.readBoolean();
		}	catch(IOException e) {
			e.printStackTrace();
			return;
		}
		
		juicewares.proxy.handleAltar(x, y, z, hasStone, hasLens, isNormalLens, stoneDone);
	}

}
