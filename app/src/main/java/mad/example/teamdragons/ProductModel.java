package mad.example.teamdragons;

public class ProductModel {
    private int id;
    private String name;
    private String des;
    private String price;


    private byte[] image;

    public ProductModel(int id, String name, String des, String price, byte[] image) {
        this.id = id;
        this.name = name;
        this.des = des;
        this.price = price;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

}
