package com.jakspinning.kingdomwar.listener;

import com.artemis.Entity;
import com.jakspinning.kingdomwar.Constants;
import com.jakspinning.kingdomwar.component.MapComponent;
import com.jakspinning.kingdomwar.component.MoveComponent;
import com.jakspinning.kingdomwar.component.PositionComponent;
import com.jakspinning.kingdomwar.map.GridPosition;
import com.jakspinning.kingdomwar.map.HexGridHelper;
import com.jakspinning.kingdomwar.map.PathFindingHelper;
import com.sun.corba.se.impl.orbutil.closure.Constant;

/**
 * Created by lverd_000 on 18/05/2015.
 */
public class UnitControlListener {

    public static Entity selected = null;

    public static void onTileSelected(MapComponent map, GridPosition target){
        //Used to simulate an event
        Entity entityOnTarget = map.getUnitOnTile(target);
        if(selected == null){
            selectUnit(entityOnTarget);
        }else{
            moveSelectedUnit(map, target);
        }
    }

    public static void selectUnit(Entity entity){
        selected = entity;
    }

    public static void moveSelectedUnit(MapComponent map, GridPosition target){
        MoveComponent component = selected.getComponent(MoveComponent.class);
        PositionComponent position = selected.getComponent(PositionComponent.class);
        component.path = PathFindingHelper.getPath(map,HexGridHelper.toHexCoord(position.position.x,position.position.y, Constants.HEX_TILE_W,Constants.HEX_TILE_H),target);
    }




}
