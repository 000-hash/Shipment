
public abstract class ShipmentOrder implements SummaryPrintable{
    private String orderNumber;
    private String customerName;
    private int distanceKm;
    private double baseFee;
    private boolean insured;
    private double lastCalculatedPrice;

    public ShipmentOrder(String orderNumber,
                         String customerName,
                         int distanceKm,
                         double baseFee,
                         boolean insured) {


        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.distanceKm = distanceKm;
        this.baseFee = baseFee;
        this.insured = insured;
    }

        public final void processOrder() {
            validateOrder();
            validateSpecificRules();

            double price = calculateBasePrice();
            price += calculateAdditionalFee();
            price = applyInsurance(price);
            price = applyBusinessDiscount(price);

            lastCalculatedPrice = price;
            printProcessingResult();
        }
        private void validateOrder() {
            if (orderNumber == null || orderNumber.trim().equals("")) {
                throw new IllegalArgumentException("Brak numeru zamowienia");
            }

            if (customerName == null || customerName.trim().equals("")) {
                throw new IllegalArgumentException("Brak nazwy klienta");
            }

            if (distanceKm <= 0) {
                throw new IllegalArgumentException("Niepoprawna odleglosc");
            }
        }
        protected void validateSpecificRules() {
        }
    private double applyInsurance(double price){
        if (insured == true) {
            price = price + price * 0.07;
        }

        return price;
        }
    protected double applyBusinessDiscount(double price){
        return price;
    }
    private void printProcessingResult(){
        System.out.println("Przetworzono zamowienie: " + orderNumber);
        System.out.println("Cena koncowa: " + lastCalculatedPrice + " PLN?");//brak waluty

    }
    public String buildSummaryLine(){
        return "Zamowienie: " + orderNumber
                + ", klient: " + customerName
                + ", typ: " + getShipmentType()
                + ", cena: " + lastCalculatedPrice + " PLN";

    }
    protected abstract double calculateBasePrice();
    protected abstract double calculateAdditionalFee();
    public abstract String getShipmentType();

    public double getBaseFee() {
        return baseFee;
    }

    public int getDistanceKm() {
        return distanceKm;
    }
}
