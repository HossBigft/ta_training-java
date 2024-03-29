package Planes;

import models.ClassificationLevel;
import models.ExperimentalTypes;

public class ExperimentalPlane extends Plane {

    private final ExperimentalTypes type;
    private ClassificationLevel classificationLevel;

    public ExperimentalPlane(PlaneEntry data, ExperimentalTypes type, ClassificationLevel classificationLevel) {
        super(data);
        this.type = type;
        this.classificationLevel = classificationLevel;
    }

    public ClassificationLevel getClassificationLevel(){
        return classificationLevel;
    }

    public void setClassificationLevel(ClassificationLevel classificationLevel){
        this.classificationLevel = classificationLevel;
    }

    @Override
    public boolean equals(Object compared) {
        return super.equals(compared);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public ExperimentalTypes getType() {
        return type;
    }

    @Override
    public String toString() {
        return "experimentalPlane{" +
                "model='" + getModel() + '\'' +
                '}';
    }

    public void accept(PlaneVisitor v){
         v.visit(this);
    }
}
