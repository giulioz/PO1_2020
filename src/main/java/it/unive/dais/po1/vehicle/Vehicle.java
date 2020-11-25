package it.unive.dais.po1.vehicle;

import it.unive.dais.po1.car.Car;

import java.io.IOException;
import java.util.Objects;

public class Vehicle implements Comparable<Vehicle> {
    @Speed(forward = true) public double speed;

    public Vehicle(@Speed(forward = true, type = "m/s") double initialSpeed) {
        this.speed = initialSpeed;
    }

    @Speed(forward = true)
    public double getSpeed() {
        return speed;
    }

    /**
     * Accelerate the vehicle by the given amount of km/h.
     * If the increase is negative, it does not accelerate
     *
     * @param a the increase of speed
     * @throws NegativeSpeedException the given acceleration is negative
     */
    public void accelerate(@Speed(forward = true, type = "miles/h") double a) throws NegativeSpeedException {
        assert a >= 0 : "The given speed is negative";
        if(a>=0)
            this.speed += a;
        else throw new NegativeSpeedException(a);
    }

    /**
     * Stops the vehicle
     */
    final public void fullBreak() {
        speed = 0.0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Double.compare(vehicle.speed, speed) == 0;
    }

    @SuppressWarnings("all")
    public Vehicle clone() {
        return new Vehicle(this.speed);
    }

    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "speed=" + speed +
                '}';
    }

    public int compareTo(Vehicle o) {
        if(this.equals(o)) return 0;
        if (o == null || getClass() != o.getClass()) {
            if(this instanceof Car) {
                return 1;
            }
            else return 1;
        }
        return (int) (o.speed - this.speed);
    }
}
