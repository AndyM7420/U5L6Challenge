public class BankApp {


    private String name;
    private String PIN;
    private Bank userBank;
    private CreditCard userCard;
    private int purchases;
    private CreditCard secondUserCard;
    private BagelShop userBagelShop;



    public BankApp(String name,String PIN,Bank userBank){
        purchases = 3;
        CreditCard userCard = new CreditCard(name,PIN);
        BagelShop userBagelShop = new BagelShop("Andy's Bagel Shop",50,5,userBank);
        this.name = name;
        this.PIN = PIN;
        this.userBank = userBank;
        this.userCard = userCard;
        this.userBagelShop = userBagelShop;


    }

    public static String appMenu() {
        return "Enter 1:make a purchase at the bagel shop. \n"+
                "Enter 2:make a return at the bagel shop.\n"+
                "Enter 3:make a payment on the credit card.\n"+
                "Enter 4:open a second card.\n"+
                "Enter 5:compare credit card balances.\n"+
                "Enter 6:deposit profits into the bank.\n"+
                "Enter 7:check inventory.\n"+
                "Enter 0 :Exit.\n";

    }
    public void purchasesSetter(int value){
        purchases = value;
    }



    public String purchaseBagels(int quantity,String cardPIN){
        boolean outcome = false;
        if (purchases == 1 ){
            outcome = userBagelShop.payForBagels(userCard,quantity,cardPIN);
            if (outcome == false){ return " unsuccessful purchase.";}
            else return "Receipt \n"+ quantity + " Bagels  "+ "$"+(userBagelShop.getBagelPrice()*quantity);
        }
        else if (purchases == 2){
            outcome = userBagelShop.payForBagels(secondUserCard,quantity,cardPIN);
            if (outcome == false){ return " unsuccessful purchase";}
            else return "Receipt \n"+ quantity + " Bagels  "+ "$"+(userBagelShop.getBagelPrice()*quantity);
        }
        else
            outcome = userBagelShop.payForBagels(userCard,quantity,cardPIN);
        if (outcome == false){ return " unsuccessful purchase";}
        else return "Receipt \n"+ quantity + " Bagels  "+ "$"+(userBagelShop.getBagelPrice()*quantity);}


    public BagelShop returnBagels(int quantity,String cardPIN){
        if  (purchases == 1){
            userBagelShop.returnBagels(secondUserCard,quantity,cardPIN);
            return userBagelShop;

        }
        else if (purchases == 2){
            userBagelShop.returnBagels(userCard,quantity,cardPIN);
            return userBagelShop;}
        else userBagelShop.returnBagels(userCard,quantity,cardPIN);
        return userBagelShop;
    }

    public CreditCard payment(int payment){
        userBank.makePayment(userCard,payment);
        return  userCard;
    }

    public CreditCard secondCard(String PIN){
        CreditCard secondUserCard = new CreditCard(name,PIN);
        this.secondUserCard = secondUserCard;
        return secondUserCard;
    }

    public CreditCard compareCards() {
        if (secondUserCard != null){
            return userBank.lowerBalance(userCard,secondUserCard);}
        else
            return userCard;

    }

    public Bank depositProfit(){
        userBagelShop.depositProfits();
        return userBank;
    }
    public String checkInventory(){
        int num = userBagelShop.getInventory();
        return "Inventory: "+ num;
    }




}
