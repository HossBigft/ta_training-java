package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

public class GoogleCloudCalculator {
    private FluentWait<WebDriver> wait;
    private WebDriver driver;

    private  final String computerEngineButton = "[role='tab']>[title='Compute Engine']";
    private  final String numberOfInstancesField = "[name='quantity'][id='input_95']";
    private  final String operatingSystemsDropdown = "[id='select_value_label_87']";
    private  final String seriesDropdown = "[id='select_120']";
    private  final String seriesN1Option  = "[aria-selected='true'][value='n1']";
    private  final String machineTypeDropdown = "[placeholder='Instance type']";
    private  final String n1Standard8MachineOption = "[value='CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-8']";
    private final String addGpuCheckbox = "[ng-model='listingCtrl.computeServer.addGPUs']";

    private final String gpuTypeDropdown = "[placeholder='GPU type']";

    private final String v100GpuTypeOption = "[value='NVIDIA_TESLA_V100']";
    private final String gpuNumberDropdown = "[placeholder='Number of GPUs']";
    private final String localSsdDropdown = "[placeholder='Local SSD']#select_445";
    private final String two375gbSsdOption = "#select_option_471[ng-value='item.value']";
    private final String dataCenterLocationDropdown = "#select_128[placeholder='Datacenter location']";
    private final String europeWest3LocationOption = "#select_option_252[value='europe-west3']";
    private final String commitedUsageDropdown = "#select_135[placeholder='Committed usage']";
    private final String oneYearUsageOption = "#select_option_133";
    private final String addToEstimateButton = "form[name='ComputeEngineForm'] div[class='layout-align-end-start layout-row'] button[type='button']";
    private final String emailEstimateButton = "[id='Email Estimate']";
    private final String emailField = "[ng-model='emailQuote.user.email']";
    private final String sendEmailButton = "[ng-disabled='emailForm.$invalid']";
}
