public class PickupPointShipment extends ShipmentOrder {
    private boolean fragile;
    private String lockerSize;

    public PickupPointShipment(String orderNumber, String customerName,
                               int distanceKm, double baseFee, boolean insured,
                                String lockerSize,boolean fragile) {
        super(orderNumber, customerName, distanceKm, baseFee, insured);
        this.fragile = fragile;
        this.lockerSize = lockerSize;
    }

    public String getShipmentType() {
        return "Pickup Point";
    }

    protected double calculateBasePrice() {
        return getBaseFee()+getDistanceKm()*0.75;
    }
    protected double calculateAdditionalFee() {
        double fee = 0;
        if(lockerSize.equals("S")) {
            fee += 5;

        }else if(lockerSize.equals("M")) {
            fee += 10;

        }else if(lockerSize.equals("L")) {
            fee += 18;

        }
        if(fragile) {
            fee += 12;
        }
        return fee;

    }
    @Override
    protected void validateSpecificRules(){
        if (!(lockerSize.equals("S")
                || lockerSize.equals("M")
                || lockerSize.equals("L"))) {

            throw new IllegalArgumentException("Niepoprawny rozmiar skrytki");
        }

    }
}

