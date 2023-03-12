package Planes;

import models.MilitaryType;

import java.util.Objects;

public class MilitaryPlane extends Plane{

    private final MilitaryType type;

    public MilitaryPlane(PlaneEntry data, MilitaryType type) {
        super(data);
        this.type = type;
    }

    public MilitaryType getType() {
        return type;
    }

    @Override
    public String toString() {
        return super.toString().replace("}",
                ", type=" + type +
                '}');
    }

    @Override
    public boolean equals(Object compared) {
        if (this == compared) return true;
        if (!(compared instanceof MilitaryPlane)) return false;
        if (!super.equals(compared)) return false;
        MilitaryPlane that = (MilitaryPlane) compared;
        return type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type);
    }
}
