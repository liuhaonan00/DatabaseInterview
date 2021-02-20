package Database;


/**
 * @author: create by Hao Nan Liu
 * @date: Feb-20-2021
 * @description:
 **/
public class Table {
    private int [][] data;
    private String [] label;
    private int currentSize = 0;

    public Table(int n, String columnA, String columnB) {
        data = new int [n][2];
        label = new String [2];
        label[0] = columnA;
        label[1] = columnB;
    }

    public int getDBSize() {
        return this.currentSize;
    }

    public void insertData(int a, int b) {
        if (currentSize == data.length) {
            System.out.println("Insert error");
        }
        data[currentSize][0] = a;
        data[currentSize][1] = b;
        currentSize++;
    }




}
