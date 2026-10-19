public class Dessert {
    int flavor;
    int price;
    static int numDessert = 0;

    public Dessert(int f1, int p1) {
        this.price = p1;
        this.flavor = f1;
        numDessert ++;
    }

    public void printDessert () {
        System.out.print(flavor + " " + price + " " + numDessert);
    }

    public static void main(String[] args) {
        System.out.println("I love dessert!");
    }
}
