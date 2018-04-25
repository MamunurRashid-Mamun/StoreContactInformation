package Entity;

public class ContactInfo {

    private String name;
    private String phone;
    private String email;
    private String address;
    private String imageTitle;

    public ContactInfo() {
    }

    public ContactInfo(String name, String phone, String email, String address, String imageTitle) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.imageTitle = imageTitle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }

    @Override
    public String toString() {
        return "ContactInfo{" + "name=" + name + ", phone=" + phone + ", email=" + email + ", address=" + address + ", imageTitle=" + imageTitle + '}';
    }

}