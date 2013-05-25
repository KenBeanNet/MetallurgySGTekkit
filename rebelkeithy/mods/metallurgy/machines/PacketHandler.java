package rebelkeithy.mods.metallurgy.machines;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import rebelkeithy.mods.metallurgy.core.MetallurgyCore;

public class PacketHandler implements IPacketHandler
{
    public void onPacketData(INetworkManager var1, Packet250CustomPayload var2, Player var3)
    {
        
    }

    public static Packet getChangeTabPacket(int var0, int var1, int var2, int var3)
    {
        ByteArrayOutputStream var4 = new ByteArrayOutputStream();
        DataOutputStream var5 = new DataOutputStream(var4);

        try
        {
            var5.writeShort(0);
            var5.writeInt(var3);
            var5.writeInt(var0);
            var5.writeInt(var1);
            var5.writeInt(var2);
        }
        catch (IOException var7)
        {
            var7.printStackTrace();
        }

        Packet250CustomPayload var6 = new Packet250CustomPayload();
        var6.channel = "M3Machines";
        var6.data = var4.toByteArray();
        var6.length = var4.size();
        var6.isChunkDataPacket = false;
        return var6;
    }

    public static Packet getScrollPacket(int var0, int var1, int var2, int var3, int var4)
    {
        ByteArrayOutputStream var5 = new ByteArrayOutputStream();
        DataOutputStream var6 = new DataOutputStream(var5);

        try
        {
            var6.writeShort(1);
            var6.writeInt(var0);
            var6.writeInt(var1);
            var6.writeInt(var2);
            var6.writeInt(var3);
            var6.writeInt(var4);
        }
        catch (IOException var8)
        {
            var8.printStackTrace();
        }

        Packet250CustomPayload var7 = new Packet250CustomPayload();
        var7.channel = "M3Machines";
        var7.data = var5.toByteArray();
        var7.length = var5.size();
        var7.isChunkDataPacket = false;
        return var7;
    }

    public static Packet getAddTabPacket(int var0, int var1, int var2, int var3, int var4)
    {
        ByteArrayOutputStream var5 = new ByteArrayOutputStream();
        DataOutputStream var6 = new DataOutputStream(var5);

        try
        {
            var6.writeShort(2);
            var6.writeInt(var0);
            var6.writeInt(var1);
            var6.writeInt(var2);
            var6.writeInt(var3);
            var6.writeInt(var4);
        }
        catch (IOException var8)
        {
            var8.printStackTrace();
        }

        Packet250CustomPayload var7 = new Packet250CustomPayload();
        var7.channel = "M3Machines";
        var7.data = var5.toByteArray();
        var7.length = var5.size();
        var7.isChunkDataPacket = false;
        return var7;
    }

    public static Packet getSetInvSizePacket(int var0, int var1, int var2, int var3, int var4)
    {
        ByteArrayOutputStream var5 = new ByteArrayOutputStream();
        DataOutputStream var6 = new DataOutputStream(var5);

        try
        {
            var6.writeShort(3);
            var6.writeInt(var0);
            var6.writeInt(var1);
            var6.writeInt(var2);
            var6.writeInt(var3);
            var6.writeInt(var4);
        }
        catch (IOException var8)
        {
            var8.printStackTrace();
        }

        Packet250CustomPayload var7 = new Packet250CustomPayload();
        var7.channel = "M3Machines";
        var7.data = var5.toByteArray();
        var7.length = var5.size();
        var7.isChunkDataPacket = false;
        return var7;
    }

    public static Packet getTabListPacket(World var0, int var1, int var2, int var3)
    {
        ByteArrayOutputStream var4 = new ByteArrayOutputStream();
        DataOutputStream var5 = new DataOutputStream(var4);


        Packet250CustomPayload var10 = new Packet250CustomPayload();
        var10.channel = "M3Machines";
        var10.data = var4.toByteArray();
        var10.length = var4.size();
        var10.isChunkDataPacket = false;
        return var10;
    }
}
