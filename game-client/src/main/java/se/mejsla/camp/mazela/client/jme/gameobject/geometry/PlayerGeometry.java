/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.mejsla.camp.mazela.client.jme.gameobject.geometry;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Sphere;

/**
 *
 * @author antonwestman
 */
public class PlayerGeometry extends Geometry{
    
    private AssetManager assetManager;
    
    public PlayerGeometry(String identifier, AssetManager assetManager){
        super(identifier);
        this.assetManager = assetManager;
        initMesh();
        initMaterial();
    }
    
    private void initMesh(){
        this.mesh = new Sphere(32, 32, 1.0f);
    }
    
    private void initMaterial(){
        final Material sphereMat = new Material(
                assetManager,
                "Common/MatDefs/Light/Lighting.j3md"
        );
        sphereMat.setBoolean("UseMaterialColors", true);
        sphereMat.setColor("Diffuse", ColorRGBA.Red);
        sphereMat.setColor("Ambient", ColorRGBA.Red);
        sphereMat.setColor("Specular", ColorRGBA.White);
        sphereMat.setFloat("Shininess", 64f);  // [0,128]
        this.material = sphereMat;
    }
}
