package es.apba.infra.esb.sample.aismanager.notification.entity;

/**
 * Class that represents a simple notification
 * 
 * @author fsaucedo
 */
public class Notification {
    
    private final String notificationText;

    public Notification(String notificationText) {
        this.notificationText = notificationText;
    }

    public String getNotification() {
        return notificationText;
    }
    
}
