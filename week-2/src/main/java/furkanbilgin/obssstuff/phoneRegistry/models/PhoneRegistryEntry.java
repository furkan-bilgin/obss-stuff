package furkanbilgin.obssstuff.phoneRegistry.models;

public class PhoneRegistryEntry {
    private String fullName;
    private String phoneNumber;

    public PhoneRegistryEntry(String fullName, String phoneNumber) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return fullName + ", " + phoneNumber;
    }
}
