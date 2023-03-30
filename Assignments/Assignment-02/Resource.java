public class Resource {
    private int state;
    public Resource(){
        this.state = 0;
    }

    public int read(){
        return this.state;
    }

    public void write(int state){
        this.state = state;
    }
}
