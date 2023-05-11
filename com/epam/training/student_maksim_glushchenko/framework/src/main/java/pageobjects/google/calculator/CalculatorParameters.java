package pageobjects.google.calculator;

import pageobjects.google.calculator.Menu.*;

public class CalculatorParameters {
    private final Integer instancesNumber;
    private final OperatingSystem operatingSystem;
    private final ProvisioningModel provisioningModel;
    private final Series series;
    private final MachineType machineType;
    private final GpuType gpuType;
    private final SsdQuantity ssdQuantity;
    private final DataCenterLocation dataCenterLocation;
    private final CommitedUsage commitedUsage;

    public Integer getInstancesNumber() {
        return instancesNumber;
    }

    public OperatingSystem getOperatingSystem() {
        return operatingSystem;
    }

    public ProvisioningModel getProvisioningModel() {
        return provisioningModel;
    }

    public Series getSeries() {
        return series;
    }

    public MachineType getMachineType() {
        return machineType;
    }

    public GpuType getGpuType() {
        return gpuType;
    }

    public SsdQuantity getSsdQuantity() {
        return ssdQuantity;
    }

    public DataCenterLocation getDataCenterLocation() {
        return dataCenterLocation;
    }

    public CommitedUsage getCommitedUsage() {
        return commitedUsage;
    }

    private CalculatorParameters(Builder builder) {
        this.instancesNumber = builder.instancesNumber;
        this.operatingSystem = builder.operatingSystem;
        this.provisioningModel = builder.provisioningModel;
        this.series = builder.series;
        this.machineType = builder.machineType;
        this.gpuType = builder.gpuType;
        this.ssdQuantity = builder.ssdQuantity;
        this.dataCenterLocation = builder.dataCenterLocation;
        this.commitedUsage = builder.commitedUsage;
    }

    public static class Builder {
        private Integer instancesNumber=0;
        private OperatingSystem operatingSystem=OperatingSystem.NONE;
        private ProvisioningModel provisioningModel=ProvisioningModel.NONE;
        private Series series=Series.NONE;
        private MachineType machineType=MachineType.NONE;
        private GpuType gpuType=GpuType.NONE;
        private SsdQuantity ssdQuantity=SsdQuantity.NONE;
        private DataCenterLocation dataCenterLocation=DataCenterLocation.NONE;
        private CommitedUsage commitedUsage=CommitedUsage.NONE;

        public Builder(Integer instancesNumber) {
            this.instancesNumber = instancesNumber;
        }

        public Builder withOperatingSystem(OperatingSystem operatingSystem) {
            this.operatingSystem = operatingSystem;
            return this;
        }

        public Builder withProvisioningModel(ProvisioningModel provisioningModel) {
            this.provisioningModel = provisioningModel;
            return this;
        }

        public Builder withSeries(Series series) {
            this.series = series;
            return this;
        }

        public Builder withMachineType(MachineType machineType) {
            this.machineType = machineType;
            return this;
        }

        public Builder withGpuType(GpuType gpuType) {
            this.gpuType = gpuType;
            return this;
        }

        public Builder withSsdQuantity(SsdQuantity ssdQuantity) {
            this.ssdQuantity = ssdQuantity;
            return  this;
        }

        public Builder withDataCenterLocation(DataCenterLocation dataCenterLocation) {
            this.dataCenterLocation = dataCenterLocation;
            return this;
        }

        public Builder withCommitedUsage(CommitedUsage commitedUsage) {
            this.commitedUsage = commitedUsage;
            return this;
        }

        public CalculatorParameters getParameters() {
            return new CalculatorParameters(this);
        }

    }
}
