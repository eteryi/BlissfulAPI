package dev.cross.blissfulcore.ui.display;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Interaction;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public abstract class InteractWindowDisplay extends WindowDisplay {
    private Interaction interaction;
    private float interactionWidth;
    private float interactionHeight;
    protected Vector interactionOffset;

    protected InteractWindowDisplay(int customModelData) {
        super(customModelData);
        this.interactionWidth = 1;
        this.interactionHeight = 1;
        this.interactionOffset = new Vector(0, 0, 0);
    }

    protected void setInteractionWidth(float f) {
        this.interactionWidth = f;
    }

    protected void setInteractionHeight(float f) {
        this.interactionHeight = f;
    }

    protected void setInteractionOffset(Vector offset) {
        this.interactionOffset = offset;
    }

    public Interaction getInteraction() {
        return interaction;
    }

    @Override
    protected boolean isEntityFrom(Entity entity) {
        return super.isEntityFrom(entity) || entity == this.interaction;
    }

    @Override
    public List<Entity> getEntities() {
        List<Entity> entities = new ArrayList<>(super.getEntities());
        entities.add(interaction);
        return entities;
    }

    @Override
    public void spawn(Location location) {
        if (this.interaction != null || super.getDisplayEntity() != null) return;
        super.spawn(location);
        this.interaction = (Interaction) location.getWorld().spawnEntity(location.add(interactionOffset), EntityType.INTERACTION);
        this.interaction.setResponsive(true);
        this.interaction.setInteractionWidth(interactionWidth);
        this.interaction.setInteractionHeight(interactionHeight);

        // TODO Kill myself if this port doesn't work
    }

    private int viewers = 0;

    abstract public void onInteraction(Player player);
}

