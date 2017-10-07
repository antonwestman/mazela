/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.mejsla.camp.mazela.client.jme.gameobject;

import com.jme3.asset.AssetManager;
import se.mejsla.camp.mazela.client.jme.gameobject.geometry.PlayerGeometry;

/**
 *
 * @author antonwestman
 */
public class Player extends GameObject{
    
    public Player(String identifier, AssetManager assetManager){
        super(identifier);
        this.node.attachChild(new PlayerGeometry(identifier+"_geom", assetManager));
    }
}
