import java.util.Scanner;

public class Money {
    public static final Money Zero = new Money(0.0);
    public static final Money OnePiaster = new Money(0.01);
    public static final Money FivePiasters = new Money(0.05);
    public static final Money TenPiasters = new Money(0.10);
    public static final Money TwentyFivePiasters = new Money(0.25);
    public static final Money FiftyPiasters = new Money(0.50);
    public static final Money OneDinar = new Money(1.0);
    public static final Money FiveDinars = new Money(5.0);
    public static final Money TenDinars = new Money(10.0);
    public static final Money TwentyDinars = new Money(20.0);
    public static final Money FiftyDinars = new Money(50.0);

    double amount;

    public Money(double amount){
        if(amount<0)
            throw new RuntimeException("Invalid Amount!");
        this.amount=amount;
    }

    public double amount() {
        return this.amount;
    }

    public Money times(int count) {
        if(count<=0)
            throw new RuntimeException("repeatition numbers can't be less than or equal to zero");
        return new Money((this.amount)*count);
    }

    public static Money sum(Money... items) {
        double sum=0;
        for(Money i : items){
            sum+=i.amount();
        }
        return new Money(sum);
    }

    // Amount returned to user
    public Money plus(Money other) {
        return new Money(other.amount-(this.amount()));
    }

    // Amount needed from user
    public Money minus(Money other) {
        return new Money(this.amount()-other.amount());
    }

    public static void main(String[] args) {
        String[] items={"Water", "Orange juice", "Limon juice", "Soda","Milk",
                "Potato chips","Corn chips", "Dark choclate","white choclate","Biscuits"};

        double[] prices={0.25,0.15,1,1.4,0.55,3.3,5.1,70,56,34};
        System.out.println("Enter the item number that you want to buy: ");
        for (int i = 0; i < 10; i++) {
            System.out.println(i+1 + ") " + items[i] + " " + prices[i]+"JD");
        }

        Scanner scan = new Scanner(System.in);
        int i=scan.nextInt();
        Money item = new Money(prices[i-1]);


        Money currentAmount = new Money(0);
        Money summedAmount = new Money(0);
        while((Money.sum(new Money(summedAmount.amount()-currentAmount.amount()),currentAmount).amount())<item.amount()){
            System.out.println("Amount needed = " + item.minus(summedAmount).amount() + "JD, please enter your money ");
            currentAmount = new Money(scan.nextDouble());
            summedAmount = new Money(summedAmount.amount()+currentAmount.amount());
            if(currentAmount.amount()==0){
                throw new RuntimeException("You have no enough money to purchase this item!");
            }
        }
        System.out.println("Summation of the Amount = " + summedAmount.amount()+"JD");
        if(summedAmount.amount()>item.amount()){
            System.out.println("Amount returned to user = " + item.plus(summedAmount).amount()+"JD");
        }
    }

}
