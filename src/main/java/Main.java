/**
 * @author: create by Hao Nan Liu
 * @date: Feb-20-2021
 * @description:
 **/
public class Main {
    public static void main(String[] args) {
        System.out.println("Starting the SQL");
        if (args.length < 2) {
            printUsage();
            System.exit(1);
        }

        System.out.println("Reading database ... ");


    }

    public static void printUsage() {
        System.out.println("Usage: ");
        System.out.println("java -jar DatabaseInterview-1.0-SNAPSHOT-jar-with-dependencies.jar DATASOURCE");
    }
}
