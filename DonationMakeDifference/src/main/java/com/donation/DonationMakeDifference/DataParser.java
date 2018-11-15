abstract class DataParser{
    public void analyzeData(){
        void readData();
        void processData();
        void writeData();
    }
    
    public void writeData(){
        System.out.println("Writing data");
    }
    
    public abstract void readData();
    
    public abstract void processData();
}