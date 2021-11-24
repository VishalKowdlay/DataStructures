/* Car object that will retain all of the relevant statistical data
1.Acceleration 2.Miles Per Gallon 3.Horsepower 4.Engine Size 5.Weight 6.Cylinders 7.CarID (Country of origin is not necessary for priority, but must exist).
Car ID	Miles Per Gallon	Engine Size (cube in)	Horse powers	Weight in pounds	Acceleration (0 to 60)	Country of Origin	Number of Cylinders */
public class Car
{
    private int carID, mpg, engineSize, horsepower, weight, acceleration, country, cylinders;
    

    public Car(int carID, int mpg, int engineSize, int horsepower, int weight, int acceleration, int country, int cylinders)
    {
        this.carID = carID;
        this.mpg = mpg;
        this.engineSize = engineSize;
        this.horsepower = horsepower;
        this.weight = weight;
        this.acceleration = acceleration;
        this.country = country;
        this.cylinders = cylinders;
    

    }

    public int getCarID() {
        return this.carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public int getMpg() {
        return this.mpg;
    }

    public void setMpg(int mpg) {
        this.mpg = mpg;
    }

    public int getEngineSize() {
        return this.engineSize;
    }

    public void setEngineSize(int engineSize) {
        this.engineSize = engineSize;
    }

    public int getHorsepower() {
        return this.horsepower;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    public int getWeight() {
        return this.weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getAcceleration() {
        return this.acceleration;
    }

    public void setAcceleration(int acceleration) {
        this.acceleration = acceleration;
    }

    public int getCountry() {
        return this.country;
    }

    public void setCountry(int country) {
        this.country = country;
    }

    public int getCylinders() {
        return this.cylinders;
    }

    public void setCylinders(int cylinders) {
        this.cylinders = cylinders;
    }


    @Override
    public String toString() {
        return "{" +
            " carID='" + getCarID() + "'" +
            ", mpg='" + getMpg() + "'" +
            ", engineSize='" + getEngineSize() + "'" +
            ", horsepower='" + getHorsepower() + "'" +
            ", weight='" + getWeight() + "'" +
            ", acceleration='" + getAcceleration() + "'" +
            ", country='" + getCountry() + "'" +
            ", cylinders='" + getCylinders() + "'" +
            "}";
    }

    
    
    

}