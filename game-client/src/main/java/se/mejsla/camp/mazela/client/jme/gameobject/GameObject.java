/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.mejsla.camp.mazela.client.jme.gameobject;

import com.jme3.scene.Node;

/**
 *
 * @author antonwestman
 */
public class GameObject {
    
    protected Node node;

    public GameObject(String identifier){
        this.node = new Node(identifier);
    }
    
    public Node getNode(){
        return this.node;
    }
    
}
