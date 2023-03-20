public class Node{

    private int id;
    private String data;
    private Node previousNode;
    private Node nextNode;

    public Node(){

    }

    public Node(int id, String data){
        this.id = id;
        this.data = data;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getData(){
        return data;
    }

    public void getData(String data){
        this.data = data;
    }
}