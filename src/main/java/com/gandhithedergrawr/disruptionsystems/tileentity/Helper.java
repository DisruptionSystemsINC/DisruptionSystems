package com.gandhithedergrawr.disruptionsystems.tileentity;

import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Helper {
    public static MatterEnergyNetworkInputNodeTile getMatterEnergyNetworkInputNode(World world, BlockPos pos){
        if (world.getTileEntity(pos.offset(Direction.UP)) instanceof MatterEnergyNetworkInputNodeTile){
            return (MatterEnergyNetworkInputNodeTile) world.getTileEntity(pos.offset(Direction.UP));
        }
        else if (world.getTileEntity(pos.offset(Direction.DOWN)) instanceof MatterEnergyNetworkInputNodeTile){
            return (MatterEnergyNetworkInputNodeTile) world.getTileEntity(pos.offset(Direction.DOWN));
        }
        else if (world.getTileEntity(pos.offset(Direction.NORTH)) instanceof MatterEnergyNetworkInputNodeTile){
            return (MatterEnergyNetworkInputNodeTile) world.getTileEntity(pos.offset(Direction.NORTH));
        }
        else if (world.getTileEntity(pos.offset(Direction.EAST)) instanceof MatterEnergyNetworkInputNodeTile){
            return (MatterEnergyNetworkInputNodeTile) world.getTileEntity(pos.offset(Direction.EAST));
        }
        else if (world.getTileEntity(pos.offset(Direction.SOUTH)) instanceof MatterEnergyNetworkInputNodeTile){
            return (MatterEnergyNetworkInputNodeTile) world.getTileEntity(pos.offset(Direction.SOUTH));
        }
        else if (world.getTileEntity(pos.offset(Direction.WEST)) instanceof MatterEnergyNetworkInputNodeTile){
            return (MatterEnergyNetworkInputNodeTile) world.getTileEntity(pos.offset(Direction.WEST));
        }
        return null;
    }

    public static boolean adjacentToMatterEnergyNetworkInputNode(World world, BlockPos pos){
        if (world.getTileEntity(pos.offset(Direction.UP)) instanceof MatterEnergyNetworkInputNodeTile ||
                world.getTileEntity(pos.offset(Direction.DOWN)) instanceof MatterEnergyNetworkInputNodeTile ||
                world.getTileEntity(pos.offset(Direction.EAST)) instanceof MatterEnergyNetworkInputNodeTile ||
                world.getTileEntity(pos.offset(Direction.SOUTH)) instanceof MatterEnergyNetworkInputNodeTile ||
                world.getTileEntity(pos.offset(Direction.WEST)) instanceof MatterEnergyNetworkInputNodeTile ||
                world.getTileEntity(pos.offset(Direction.NORTH)) instanceof MatterEnergyNetworkInputNodeTile){
            return true;
        }
        return false;
    }
    public static boolean inputNodeHasPower(MatterEnergyNetworkInputNodeTile tile, int MEDIS_PER_TICK){
        return tile.getMatterEnergyStorage().getEnergyStored() > MEDIS_PER_TICK;
    }
}
