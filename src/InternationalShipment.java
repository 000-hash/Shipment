public class InternationalShipment extends ShipmentOrder {
    private String destinationCountry;
    private boolean customsDocumentsRequired;
    private boolean expressDelivery;

    public InternationalShipment(String orderNumber, String customerName,
                                 int distanceKm, double baseFee, boolean insured,
                                 String destinationCountry,
                                 boolean customsDocumentsRequired,
                                 boolean expressDelivery) {
        super(orderNumber, customerName, distanceKm, baseFee, insured);
        this.customsDocumentsRequired = customsDocumentsRequired;
        this.destinationCountry = destinationCountry;
        this.expressDelivery = expressDelivery;
    }
    public String getShipmentType(){
        return "International";
    }
    protected double calculateBasePrice(){
        return getBaseFee() + getDistanceKm() * 2.10;
    }
    protected double calculateAdditionalFee() {
        double fee = 0;
        if (customsDocumentsRequired) {
            fee += 45;
        }
        if (expressDelivery) {
            fee += 80;
        }
        return fee;

    }
    @Override
     protected void validateSpecificRules(){
        if (destinationCountry == null || destinationCountry.trim().equals("")) {
            throw new IllegalArgumentException("Destination country is required");
        }

     }
    protected double applyBusinessDiscount(double price){
        if (expressDelivery==false && getDistanceKm()>1000) {
            price -=price*0.03;
        }
        return price;
    }

}

