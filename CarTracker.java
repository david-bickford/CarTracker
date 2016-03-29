

import java.util.Comparator;
//import java.util.PriorityQueue;
import java.util.Scanner;
/**
 *
 * @author David Bickford
 * @email drb56@pitt.edu
 */
public class CarTracker extends PriorityQueueAltered
{

    
    public static void main(String[] args) 
    {
        //using two separate comparators for the queues
        Comparator<Car> priceComparator = new PriceComparator();
        Comparator<Car> milesComparator = new MilesComparator();
        //using two separate queues, one for price one for miles
        PriorityQueueAltered<Car> priceQueue = new PriorityQueueAltered<Car>(10, priceComparator);
        PriorityQueueAltered<Car> milesQueue = new PriorityQueueAltered<Car>(10, milesComparator);
        Scanner reader = new Scanner(System.in);
        //various variables used
        String input;
        String vin;
        String make;
        String model;
        double price;
        double miles;
        String color;
        Car newCar;
        Car currCar;
        //while loop to continue until the user wants to exit
        while(true)
        {
            //asks the user what they want to do
            System.out.println("Enter the number of what you'd like to do:");
            System.out.println("1: Add car\n2: Update car\n3: Remove car"
                    + "\n4: Retrieve lowest price car\n5: Retrieve lowest mileage car"
                    + "\n6: Retrieve lowest price car by make and model"
                    + "\n7: Retrieve lowest mileage car by make and model"
                    + "\n8: Quit");
            input = reader.next(); 
            //if input equals 1 then enter a new car
            if(input.equals("1"))
            {
                //asks for vin number and sets it to upper case
                System.out.println("Enter vin: ");
                vin = reader.next();
                vin = vin.toUpperCase();
                //checks to make sure the vin is an acceptable vin number
                while(!acceptableVins(vin))
                {
                    //prints out that it's not an appropriate vin number
                    System.out.println("I'm sorry, that's not an appropriate vin number, you can't include I, O, or Q");
                    System.out.println("Enter vin: ");
                    vin = reader.next();
                    vin = vin.toUpperCase();
                }
                //asks for all the other information
                System.out.println("Enter make: ");
                make = reader.next();
                System.out.println("Enter model: ");
                model = reader.next();
                System.out.println("Enter price: ");
                price = Double.parseDouble(reader.next());
                System.out.println("Enter miles: ");
                miles = Double.parseDouble(reader.next());
                System.out.println("Enter color: ");
                color = reader.next();
                //creates a newCar object and adds it to both the priceQueue and the milesQueue
                newCar = new Car(vin, make, model, price, miles, color);
                priceQueue.add(newCar);
                milesQueue.add(newCar);
                System.out.println("Car added!\n\n");
            }
            //if the input is 2 then the user wants to change a car in the queue
            else if(input.equals("2"))
            {
                //asks the user for a vin number
                System.out.println("Enter vin: ");
                vin = reader.next();
                vin = vin.toUpperCase();
                //checks to make sure the car is in both queues
                if(priceQueue.contains(new Car(vin, " ", " ")) && milesQueue.contains(new Car(vin, " ", " ")))
                {
                    //asks the user what they want to change
                    System.out.println("Choose what you'd like to change:"
                            + "\n1: Price\n2: Mileage\n3: Color");
                    input = reader.next();
                    //if the input is 1 the user wants to change the price
                    if(input.equals("1"))
                    {
                        //asks the user to enter the new price
                        System.out.println("\nEnter price change: ");
                        price = Double.parseDouble(reader.next());
                        currCar = (Car)priceQueue.containsWithReturn(new Car(vin, " ", " "));
                        //creates a new car object and removes the old car and adds in a new car
                        newCar = new Car(currCar.vin, currCar.make, currCar.model, price, currCar.miles, currCar.color);
                        priceQueue.remove(new Car(vin, " ", " "));
                        priceQueue.add(newCar);
                        //creates a new car object and removes the old car and adds in a new car
                        currCar = (Car)milesQueue.containsWithReturn(new Car(vin, " ", " "));
                        newCar = new Car(currCar.vin, currCar.make, currCar.model, price, currCar.miles, currCar.color);
                        milesQueue.remove(new Car(vin, " ", " "));
                        milesQueue.add(newCar);
                    }
                    //if the input is 2 the user wnats to change the mileage
                    else if(input.equals("2"))
                    {
                        //asks the user to enter the new mileage
                        System.out.println("\nEnter mileage change: ");
                        miles = Double.parseDouble(reader.next());
                        currCar = (Car)priceQueue.containsWithReturn(new Car(vin, " ", " "));
                        //creates a new car object and removes the old car and adds in a new car
                        newCar = new Car(currCar.vin, currCar.make, currCar.model, currCar.price, miles, currCar.color);
                        priceQueue.remove(new Car(vin, " ", " "));
                        priceQueue.add(newCar);
                        //creates a new car object and removes the old car and adds in a new car
                        currCar = (Car)milesQueue.containsWithReturn(new Car(vin, " ", " "));
                        newCar = new Car(currCar.vin, currCar.make, currCar.model, currCar.price, miles, currCar.color);
                        milesQueue.remove(new Car(vin, " ", " "));
                        milesQueue.add(newCar);
                    }
                    //if the input is 3 the user wants to change the color
                    else if(input.equals("3"))
                    {
                        //asks the user to enter the new color
                        System.out.println("\nEnter color change: ");
                        color = reader.next();
                        currCar = (Car)priceQueue.containsWithReturn(new Car(vin, " ", " "));
                        //changes the color of the returned object
                        currCar.setColor(color);
                        currCar = (Car)milesQueue.containsWithReturn(new Car(vin, " ", " "));
                        currCar.setColor(color);
                    }
                    else
                    {
                        //if the user enters an invalid input prints this out
                        System.out.println("\nSorry, that isn't a valid input");
                    }
                }
                else
                {
                    //if the car isn't in the queue then it tells the user
                    System.out.println("Sorry, that car isn't contained in the queue");
                }
            }
            //if it's 3 then the user wants to remove a car from the queue
            else if(input.equals("3"))
            {
                //asks the user for the vin
                System.out.println("Enter vin: ");
                vin = reader.next();
                vin = vin.toUpperCase();
                //checks if the car is in the queue, removes if it is
                if(priceQueue.remove(new Car(vin, " ", " ")) && milesQueue.remove(new Car(vin, " ", " ")))
                {
                    System.out.println("Car succesfully removed");
                }
                else
                {
                    System.out.println("Sorry, that car isn't contained in the queue");
                }
            }
            //if it's 4 then it finds the highest priority of price
            else if(input.equals("4"))
            {
                //prints out lowest price item
                currCar = priceQueue.peek();
                if(currCar == null)
                {
                    System.out.println("Sorry, that's not a valid input");
                }
                else
                {
                    System.out.println("Vin: " + currCar.vin);
                    System.out.println("Make: " + currCar.make);
                    System.out.println("Model: " + currCar.model);
                    System.out.println("Price: " + Double.toString(currCar.price));
                    System.out.println("Miles: " + Double.toString(currCar.miles));
                    System.out.println("Color: " + currCar.color);
                }
                
            }
            //if it's 5 then it finds the highest priority of miles
            else if(input.equals("5"))
            {
                //prints out the lowest miles item
                currCar = milesQueue.peek();
                if(currCar == null)
                {
                    System.out.println("Sorry, that's not a valid input");
                }
                else
                {
                    System.out.println("Vin: " + currCar.vin);
                    System.out.println("Make: " + currCar.make);
                    System.out.println("Model: " + currCar.model);
                    System.out.println("Price: " + Double.toString(currCar.price));
                    System.out.println("Miles: " + Double.toString(currCar.miles));
                    System.out.println("Color: " + currCar.color);
                }
            }
            //if the user enters 6 then the user wants the highest priority item
            //with a specific make/model
            else if(input.equals("6"))
            {
                //asks the user to enter make and model
                System.out.println("\nEnter make: ");
                make = reader.next();
                System.out.println("\nEnter model: ");
                model = reader.next();
                //finds the car with the highest priority based on make/model
                currCar = (Car)priceQueue.containsWithReturn(new Car(" ", make, model));
                //prints out info
                if(currCar == null)
                {
                    System.out.println("Sorry, that's not a valid input");
                }
                else
                {
                    System.out.println("Vin: " + currCar.vin);
                    System.out.println("Make: " + currCar.make);
                    System.out.println("Model: " + currCar.model);
                    System.out.println("Price: " + Double.toString(currCar.price));
                    System.out.println("Miles: " + Double.toString(currCar.miles));
                    System.out.println("Color: " + currCar.color);
                }
            }
            //if the user enters 7 then the user wants the highest priority item
            //with a specific make/model
            else if(input.equals("7"))
            {
                //asks the user to enter make and model
                System.out.println("\nEnter make: ");
                make = reader.next();
                System.out.println("\nEnter model: ");
                model = reader.next();
                //finds the car with the highest priority based on make/model
                currCar = (Car)milesQueue.containsWithReturn(new Car(" ", make, model));
                //prints out info
                if(currCar == null)
                {
                    System.out.println("Sorry, that's not a valid input");
                }
                else
                {
                    System.out.println("Vin: " + currCar.vin);
                    System.out.println("Make: " + currCar.make);
                    System.out.println("Model: " + currCar.model);
                    System.out.println("Price: " + Double.toString(currCar.price));
                    System.out.println("Miles: " + Double.toString(currCar.miles));
                    System.out.println("Color: " + currCar.color);
                }
            }
            //if the input is 8 then it exits the system
            else if(input.equals("8"))
            {
                System.out.println("\nThank you for using the system!");
                break;
            }
//            else if(input.equals("9"))
//            {
//                Object[] milesArray = milesQueue.toArray();
//                Object[] priceArray = priceQueue.toArray();
//                
//                for(int i=0; i<milesArray.length; i++)
//                {
//                    currCar = (Car)milesArray[i];
//                    System.out.println(currCar.vin);
//                    System.out.println(currCar.make);
//                    System.out.println(currCar.model);
//                    System.out.println(currCar.price);
//                    System.out.println(currCar.miles);
//                    System.out.println(currCar.color + "\n");
//                }
//                
//                for(int i=0; i<milesArray.length; i++)
//                {
//                    currCar = (Car)priceArray[i];
//                    System.out.println(currCar.vin);
//                    System.out.println(currCar.make);
//                    System.out.println(currCar.model);
//                    System.out.println(currCar.price);
//                    System.out.println(currCar.miles);
//                    System.out.println(currCar.color + "\n");
//                }
//            }
            //tells the user it's not a valid input if anything else is typed in
            else
            {
                System.out.println("\nI'm sorry, that's not a valid input.\n");
            }
        }
        
    }
    
    /*
    purpose: checks to make sure the vin number is acceptable
    @param: String vin
    @return: boolean based on if it is acceptable or not
    */
    public static boolean acceptableVins(String vin)
    {
        //iterates through the string
        for(int i=0; i<vin.length(); i++)
        {
            //checks if the char at i is I, O, or Q
            if(vin.charAt(i) == 'I' || vin.charAt(i) == 'O' || vin.charAt(i) == 'Q')
            {
                //returns false if it is
                return false;
            }
        }
        //returns true if there isn't an I, O, or Q
        return true;
    }

    /*
        price comparator that the priceQueue uses to compare the cars
    */
    private static class PriceComparator implements Comparator<Car> {

        public PriceComparator() 
        {
            
        }
        //overriding the compare class to check for price
        @Override
        public int compare(Car car1, Car car2)
        {
            
            if(car1.price < car2.price)
            {
                return -1;
            }
            else if(car1.price == car2.price)
            {
                return 0;
            }
            else
            {
                return 1;
            }
        }
    }

    /*
        price comparator that the priceQueue uses to compare the cars
    */
    private static class MilesComparator implements Comparator<Car> {

        public MilesComparator() 
        {
            
        }
        //overriding the compare class to check for price
        @Override
        public int compare(Car car1, Car car2) 
        {
            
            if(car1.miles < car2.miles)
            {
                return -1;
            }
            else if(car1.miles == car2.miles)
            {
                return 0;
            }
            else
            {
                return 1;
            }
        }
    }
    
}
