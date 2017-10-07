/*
 * Copyright 2017 Johan Maasing <johan@zoom.nu>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package se.mejsla.camp.mazela.game;

import com.google.common.base.Preconditions;
import java.util.concurrent.atomic.AtomicBoolean;
import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Mass;
import org.dyn4j.geometry.Vector2;

/**
 *
 * @author Johan Maasing <johan@zoom.nu>
 */
public class Player {

    private final Body physicsBody;
    private final AtomicBoolean up = new AtomicBoolean(false);
    private final AtomicBoolean down = new AtomicBoolean(false);
    private final AtomicBoolean left = new AtomicBoolean(false);
    private final AtomicBoolean right = new AtomicBoolean(false);
    private final AtomicBoolean jump = new AtomicBoolean(false);
    private final AtomicBoolean needsUpdate = new AtomicBoolean(false);
    
    private final float JUMP_FORCE_MULTIPLIER = 20;

    public Player(final Body physicsBody) {
        this.physicsBody = Preconditions.checkNotNull(physicsBody);
        this.physicsBody.setMass(new Mass(new Vector2(0,0), 1, 0.1));
    }

    public void update(final float tpf, final double baseLineY) {
        Vector2 force = new Vector2(0, 0);
        if (this.needsUpdate.get()) {
            this.needsUpdate.set(false);
        }
        if (this.right.get()) {
            force.add(tpf, 0);
        }
        if (this.left.get()) {
            force.add(-tpf, 0);
        }
        if (this.up.get()) {
            force.add(0, tpf);
        }
        if (this.down.get()) {
            force.add(0, -tpf);
        }
        if (this.jump.get()) {
            if (this.physicsBody.getTransform().getTranslationY() < baseLineY + 0.5) {
                force.add(getJumpForce(tpf)); 
            }
        }
        force.multiply(200);
        this.physicsBody.applyForce(force);
        setInput(false,false,false,false,false);
    }
    
    private Vector2 getJumpForce(float tpf){
        double rotation = this.physicsBody.getTransform().getRotation();
        Vector2 direction = new Vector2(0,1).rotate(rotation);
        Vector2 jumpForce = direction.multiply(tpf * JUMP_FORCE_MULTIPLIER);
        if (jumpForce.y < 0) {
            jumpForce.multiply(-1);
        }
        jumpForce.x /= 3; 
        return jumpForce;
    }

    public Body getPhysicsBody() {
        return physicsBody;
    }

    public void setInput(
            final boolean left,
            final boolean right,
            final boolean up,
            final boolean down,
            final boolean jump) {
        this.up.set(up);
        this.down.set(down);
        this.left.set(left);
        this.right.set(right);
        this.jump.set(jump);
        this.needsUpdate.set(true);
    }
}
