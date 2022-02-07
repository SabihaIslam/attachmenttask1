package com.company;
import java.util.*;

public class Main {

    private static List<Map> product = new ArrayList<>();
    public static double balance = 20000;

    static void prodAdd(){
        Scanner s = new Scanner(System.in);
        Scanner s1 = new Scanner(System.in);

        String pname;
        double bp;
        double sp;
        int anum = 0;
        double profit = 0.0;
        boolean deleted = false;

        System.out.println("Enter product name: ");
        pname = s1.nextLine();
        System.out.println("Enter buy price: ");
        bp = s.nextDouble();
        System.out.println("Enter sell price: ");
        sp = s.nextDouble();

        Map prod = new HashMap();
        prod.put("pname", pname);
        prod.put("bp", bp);
        prod.put("sp", sp);
        prod.put("anum", anum);
        prod.put("profit", profit);
        prod.put("deleted", deleted);

        product.add(prod);
        System.out.println(pname + " is added in the list.");
        System.out.println("");
    }
    static void prodShow(){
        double tprofit = 0.0;
        System.out.println("-----------------------------------------------------------------------------");
        System.out.printf("%10s %20s %10s", "Product Name", "Available Product", "Profit");
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------");
        for(int i = 0; i<product.size();i++){
            tprofit += (double) product.get(i).get("profit");
            if ((boolean) product.get(i).get("deleted"))
                continue;
            System.out.format("%10s %15d %17.2f",
                    product.get(i).get("pname"), product.get(i).get("anum"), product.get(i).get("profit"));
            System.out.println();
        }
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("Total Profit: " + tprofit);
        System.out.println("");
    }
    static void balance(){
        System.out.println("");
        System.out.println("Available balance: " + balance);
        System.out.println("");
    }
    static void prodDelete() {
        Scanner s = new Scanner(System.in);
        boolean found = false;
        System.out.print("Enter product name to delete :");
        String delete = s.nextLine();
        System.out.println("----------------------------");
        for (int i = 0; i < product.size(); i++) {
            String name = product.get(i).get("pname").toString();
            boolean deleted = (boolean) product.get(i).get("deleted");
            if (name.equals(delete) && !deleted) {
                product.get(i).put("deleted", true);
                found = true;
                System.out.println(delete + "is deleted from inventory.");
                System.out.println("");
                break;
            }
        }
        if (!found) {
            System.out.println(delete + " is not found.");
            System.out.println("----------------------------");
        }
    }
    static void prodBuy(){
        Scanner s = new Scanner(System.in);
        Scanner s1 = new Scanner(System.in);
        boolean found = false;
        System.out.print("Enter product name to buy: ");
        String bname = s.nextLine();
        System.out.print("Enter quantity of product: ");
        int newnum = s1.nextInt();
        System.out.println("----------------------------");
        for (int i = 0; i < product.size(); i++) {
            if (product.get(i).get("pname").toString().equals(bname)){
                double buyp = (double) product.get(i).get("bp");
                if (buyp*newnum > balance)
                    System.out.println("Not enough balance to buy the product.");
                else{
                    int pnum = (int) product.get(i).get("anum");
                    product.get(i).put("anum", pnum + newnum);
                    balance -= newnum * buyp;
                    System.out.println(bname + " buy successfully.");
                }
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println(bname + " is not found in product list.");
            System.out.println("----------------------------");
        }
    }
    static void prodSell(){
        Scanner s = new Scanner(System.in);
        Scanner s1 = new Scanner(System.in);
        boolean found = false;
        System.out.print("Enter product name to sell: ");
        String name = s.nextLine();
        System.out.print("Enter quantity of product: ");
        int sellnum = s1.nextInt();
        System.out.println("----------------------------");
        for (int i = 0; i < product.size(); i++) {
            if (product.get(i).get("pname").toString().equals(name)){
                int available = (int) product.get(i).get("anum");
                if (sellnum > available)
                    System.out.println("Product is not available.");
                else{
                    int pnum1 = (int) product.get(i).get("anum");
                    double pprofit = (double) product.get(i).get("profit");
                    double sellp = (double) product.get(i).get("sp");
                    double buyp = (double) product.get(i).get("bp");

                    product.get(i).put("anum", pnum1 - sellnum);
                    product.get(i).put("profit", pprofit + (sellp - buyp) * sellnum);
                    balance += (sellnum * sellp);
                    System.out.println(name + " sell successfully.");
                }
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println(name + " is not found in product list.");
            System.out.println("----------------------------");
        }
    }

    public static void main(String[] args) {

        Scanner data = new Scanner(System.in);
        int ch;
        do{
            System.out.println("1.Add a product");
            System.out.println("2.Delete a product");
            System.out.println("3.Buy a product");
            System.out.println("4.Sell a product");
            System.out.println("5.See the list of product");
            System.out.println("6.See available balance");
            System.out.println("7.Exit");
            System.out.print("Enter Your Choice : ");
            ch = data.nextInt();

            switch (ch){
                case 1:
                    prodAdd();
                    break;
                case 2:
                    prodDelete();
                    break;
                case 3:
                    prodBuy();
                    break;
                case 4:
                    prodSell();
                    break;
                case 5:
                    prodShow();
                    break;
                case 6:
                    balance();
                    break;
            }
        }while(ch!=7);
    }
}