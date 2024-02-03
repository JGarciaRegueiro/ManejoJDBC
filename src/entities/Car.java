package entities;

import java.io.Serializable;
import java.util.List;

public class Car implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String brand;
	private String model;
	private int year;
	private int km;
	
	public Car() {
	}
	
	public Car(String brand, String model, int year, int km) {
		super();
		this.brand = brand;
		this.model = model;
		this.year = year;
		this.km = km;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getKm() {
		return km;
	}
	public void setKm(int km) {
		this.km = km;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Car))
			return false;
		Car other = (Car) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Coche [id=" + id + ", brand=" + brand + ", model=" + model + ", year=" + year + ", km=" + km
				+ "]";
	}

}
