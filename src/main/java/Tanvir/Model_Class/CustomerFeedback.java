package Tanvir.Model_Class;

public class CustomerFeedback {

    private final String customer;
    private final String product;
    private final String feedback;
    private final float rating;
    private final String date;

    public CustomerFeedback(String customer,
                            String product,
                            String feedback,
                            float rating,
                            String date) {

        this.customer = customer;
        this.product = product;
        this.feedback = feedback;
        this.rating = rating;
        this.date = date;
    }

    public final String getCustomer() {
        return customer;
    }

    public final String getProduct() {
        return product;
    }

    public final String getFeedback() {
        return feedback;
    }

    public final float getRating() {
        return rating;
    }

    public final String getDate() {
        return date;
    }
}

