package com.company;
import java.util.*;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.Object;

class product{
    private String prodname;
    private double bprice;
    private double sprice;
    private int availnum = 0;
    private double profit = 0.0;

    product(String prodname, double bprice, double sprice, int availnum, double profit){
        this.prodname = prodname;
        this.bprice = bprice;
        this.sprice = sprice;
        this.availnum = availnum;
        this.profit = profit;
    }

    public String getProdname(){
        return prodname;
    }
    public double getBprice(){
        return bprice;
    }

    public double getSprice(){
        return sprice;
    }

    public int getAvailnum(){
        return availnum;
    }

    public double getProfit(){
        return profit;
    }

    public String toString(){
        return prodname+" "+bprice+" "+sprice+" "+availnum+" "+profit;
    }
}
public class Main {

    public static double balance = 20000;

    public static void main(String[] args) {

        List<product> p = new ArrayList<product>();
        Scanner s = new Scanner(System.in);
        Scanner s1 = new Scanner(System.in);
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
            ch = s.nextInt();

            switch (ch){
                case 1:
                    System.out.println("Enter product name: ");
                    String pname = s1.nextLine();
                    System.out.println("Enter buy price: ");
                    double bp = s.nextDouble();
                    System.out.println("Enter sell price: ");
                    double sp = s.nextDouble();
                    System.out.println("Enter available product in the inventory to sell: ");
                    int aprod = s.nextInt();
                    System.out.println("Enter total profit selling the product: ");
                    double profit = s.nextDouble();
                    p.add(new product(pname, bp, sp, aprod, profit));
                    break;
                case 2:
                    boolean found = false;
                    System.out.print("Enter your product name to delete :");
                    String name = s1.nextLine();
                    System.out.println("----------------------------");
                    Iterator<product> i = p.iterator();
                    while(i.hasNext()){
                        product e = i.next();
                        if(e.getProdname().equals(name))  {
                            i.remove();
                            found = true;
                        }
                    }
                    if(!found){
                        System.out.println("Your product is not found");
                    }else{
                        System.out.println("Your product is deleted successfully");
                        System.out.println("To see the new product list enter 5");
                    }
                    System.out.println("----------------------------");
                    break;
                case 5:
                    System.out.println("-----------------------------------------------------------------------------");
                    System.out.printf("%10s %20s %10s", "Product Name", "Available Product", "Profit");
                    System.out.println();
                    System.out.println("-----------------------------------------------------------------------------");
                    for(product product: p){
                        System.out.format("%10s %15d %17.2f",
                                product.getProdname(), product.getAvailnum(), product.getProfit());
                        System.out.println();
                    }
                    System.out.println("-----------------------------------------------------------------------------");
                    break;
                case 6:
                    System.out.println();
                    System.out.println("Available balance: " + balance + " BDT");
                    System.out.println();
                    break;
            }
        }while(ch!=7);
    }
}