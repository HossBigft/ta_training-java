package PageObjects.Google.Calculator.Menu;

import javax.xml.crypto.Data;

public enum DataCenterLocation {
    IOWA("us-central1"),
    SOUTH_CAROLINA("us-east1"),
    NORTHERN_VIRGINIA("us-east4"),
    COLUMBUS("us-east5"),
    DALLAS("us-south-1"),
    OREGON("us-west1"),
    LOS_ANGELES("us-west2"),
    SALT_LAKE_CIY("us-west3"),
    LAS_VEGAS("us-west4"),
    TAIWAN("asua-east1"),
    HONG_KONG("asia-east2"),
    TOKYO("asia-northeast1"),
    OSAKA("asia-northeast2"),
    SEOUL("asia-northeast3"),
    MUMBAI("asia-south1"),
    DELHI("asia-south2"),
    SINGAPURE("asia-southeast1"),
    JAKARTA("asia-southeast2"),
    SYDNEY("australia-southeast1"),
    MELBOURNE("australia-southeast2"),
    WARSAW("europe-central2"),
    FINLAD("europe-north1"),
    MADRID("europe-southwest1"),
    BELGIUM("europe-west1"),
    LONDON("europe-west2"),
    FRANKFURT("europe-west3"),
    NETHERLANDS("europe-west4"),
    ZURICH("europe-west6"),
    MILAN("europe-west8"),
    PARIS("europe-west9"),
    TURIN("europe-west12"),
    DOHA("me-central1"),
    TEL_AVIV("me-west1"),
    MONTREAL("northamerica-northeast1"),
    TORONTO("northamerica-northeast2"),
    SAO_PAULO("southamerica-east1"),
    SANTIAGO("southamerica-west1");
    private String tag;

    private DataCenterLocation(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

}
