package logic;

public class Subscriber {
    private int subscriber_id;
    private String subscriber_name;
    private int detailed_subscription_history;
    private String subscriber_phone_number;
    private String subscriber_email;

    public Subscriber(int id, String name, int history, String phone, String email) {
        this.subscriber_id = id;
        this.subscriber_name = name;
        this.detailed_subscription_history = history;
        this.subscriber_phone_number = phone;
        this.subscriber_email = email;
    }

    public int getSubscriberId() {
        return subscriber_id;
    }

    public String getSubscriberName() {
        return subscriber_name;
    }

    public int getDetailedSubscriptionHistory() {
        return detailed_subscription_history;
    }

    public String getSubscriberPhoneNumber() {
        return subscriber_phone_number;
    }

    public String getSubscriberEmail() {
        return subscriber_email;
    }

    public void setSubscriberId(int id) {
        this.subscriber_id = id;
    }

    public void setSubscriberName(String name) {
        this.subscriber_name = name;
    }

    public void setDetailedSubscriptionHistory(int history) {
        this.detailed_subscription_history = history;
    }

    public void setSubscriberPhoneNumber(String phone) {
        this.subscriber_phone_number = phone;
    }

    public void setSubscriberEmail(String email) {
        this.subscriber_email = email;
    }

    @Override
    public String toString() {
        return "[ID=" + subscriber_id + 
               ", Name=" + subscriber_name + 
               ", History=" + detailed_subscription_history + 
               ", Phone=" + subscriber_phone_number + 
               ", Email=" + subscriber_email + "]";
    }
}

