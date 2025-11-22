import java.time.LocalDate;//to easily import the LocalDate class

public class Document {
    private String type;
    private LocalDate expiry;
    private String imgPath;

    //in this class it will create the document class which will be used to store the information about the documents it confirms if the document 
    //is valid to use and accepts othercontrustors

    public Document(String type, LocalDate expiry, String imgPath) {
        this.type = type;
        this.expiry = expiry;
        this.imgPath = imgPath;
    }

    //this method checks if the document that has been contructed is valid
    public boolean isValid() {
        if (expiry == null) return true;//if the expiry date equals to null then the document will be valid and return as true
        return expiry.isAfter(LocalDate.now());// and here if the expiry date is after the current date it will also be valid and return it as true
    }
}
