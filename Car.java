/*
 * @author David Bickford
 * @email drb56@pitt.edu
 */
public class Car
{
    //various things inside the object
    String vin;
    String make;
    String model;
    double price;
    double miles;
    String color;
    //Car constructor with everyting
    public Car(String vinNum, String makeCar, String modelCar, double priceCar, double milesCar, String colorCar)
    {
        vin = vinNum;
        make = makeCar;
        model = modelCar;
        price = priceCar;
        miles = milesCar;
        color = colorCar;
    }
    //Car constructor just used for comparisons
    public Car(String vinNum, String makeCar, String modelCar)
    {
        vin = vinNum;
        make = makeCar;
        model = modelCar;
    }
    //sets the vin number
    public void setVin(String vinNum)
    {
        vin = vinNum;
    }
    //sets the make of the car
    public void setMake(String makeCar)
    {
        make = makeCar;
    }
    //sets the model of the car
    public void setModel(String modelCar)
    {
        model = modelCar;
    }
    //sets the price of the car
    public void setPrice(double priceCar)
    {
        price = priceCar;
    }
    //sets the miles of the car
    public void setMiles(double milesCar)
    {
        miles = milesCar;
    }
    //sets the color of the car
    public void setColor(String colorCar)
    {
        color = colorCar;
    }
    //returns the vin number of the object
    public String getVin()
    {
        return vin;
    }
    //returns the make of the object
    public String getMake()
    {
        return make;
    }
    //returns the model of the object
    public String getModel()
    {
        return model;
    }
    //returns the price of the object
    public double getPrice()
    {
        return price;
    }
    //returns the miles of the object
    public double getMiles()
    {
        return miles;
    }
    //returns the color of the object
    public String getColor()
    {
        return color;
    }
    
    //overriding the equals method to compare by vin number OR compare by make and model
    @Override
    public boolean equals(Object o)
    {
        return this.vin.equals(((Car)o).vin) || (this.make.equals(((Car)o).make) && this.model.equals(((Car)o).model));
    }
}
