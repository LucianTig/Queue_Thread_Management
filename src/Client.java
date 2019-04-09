public class Client {
	private int arrivalTime;
	private int serviceTime;
	private int idf_number;
	private int waitingTime;
	
	public Client(int ser, int arr, int idf_nr) {
		arrivalTime=arr;
		serviceTime=ser;
		idf_number=idf_nr;
	}
	
	public int getArrTime() {
		return this.arrivalTime;
	}
	
	public int getSerTime() {
		return this.serviceTime;
	}
	
	public void setSerTime() {
		this.serviceTime=this.serviceTime-1;
	}
	
	public void setArrTime(int arrivalTime) {
		this.arrivalTime=arrivalTime;
	}
	
	public int getID() {
		return idf_number;
	}
	
	public int getWTime() {
		return waitingTime;
	}
	
	public void setWTime() {
		this.waitingTime++;
	}
}
