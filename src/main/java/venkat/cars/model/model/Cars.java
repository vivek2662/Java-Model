package venkat.cars.model.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cars")
public class Cars {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="car_name")
	private String carname;
	
	@Column(name="company")
	private String company;
	
	@Column(name="fuel_type")
	private String fueltype;
	
	@Column(name="price")
	private long price;
	
	@Column(name="mileage")
	private long mileage;

	public Cars() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cars(String carname, String company, String fueltype, long price, long mileage) {
		super();
		this.carname = carname;
		this.company = company;
		this.fueltype = fueltype;
		this.price = price;
		this.mileage = mileage;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCarname() {
		return carname;
	}

	public void setCarname(String carname) {
		this.carname = carname;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getFueltype() {
		return fueltype;
	}

	public void setFueltype(String fueltype) {
		this.fueltype = fueltype;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public long getMileage() {
		return mileage;
	}

	public void setMileage(long mileage) {
		this.mileage = mileage;
	}

	@Override
	public String toString() {
		return "Cars [id=" + id + ", carname=" + carname + ", company=" + company + ", fueltype=" + fueltype
				+ ", price=" + price + ", mileage=" + mileage + "]";
	}
	
	
}
