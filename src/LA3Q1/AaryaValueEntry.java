package LA3Q1;

public class AaryaValueEntry {
    //private fields
    private Integer key;
    //constructor w/o argument
    public AaryaValueEntry(){
        this.key=-1;
    }
    //constructor with argument
    public AaryaValueEntry(Integer k){
        this.key=k;
    }
    //getting key
    public Integer getKey() {
        return key;
    }
    //setting key
    public void setKey(Integer k) {
        this.key = k;
    }
}
