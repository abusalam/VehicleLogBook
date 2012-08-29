package com.github.abusalam.vehiclelogbook;

public class Tour {
	private long _TourID;
	private String _VehicleNo;
	private String _Source;
	private String _Destination;
	private int _Distance;
	private String _Remarks;

	public Tour() {
		// TODO Auto-generated constructor stub
	}

	public Tour(int TourID, String VehicleNo, String Source,
			String Destination, int Distance, String Remarks) {
		// TODO Auto-generated constructor stub
	}

	public Tour(String VehicleNo, String Source, String Destination,
			int Distance, String Remarks) {
		// TODO Auto-generated constructor stub
	}

	public Tour(int TourID, String VehicleNo, String Source,
			String Destination, int Distance) {
		// TODO Auto-generated constructor stub
	}

	public Tour(String VehicleNo, String Source, String Destination,
			int Distance) {
		// TODO Auto-generated constructor stub
	}

	public long getTourID() {
		return this._TourID;
	}

	public void setTourID(long TourID) {
		this._TourID = TourID;
	}

	public String getVehicleNo() {
		return this._VehicleNo;
	}

	public void setVehicleNo(String VehicleNo) {
		this._VehicleNo = VehicleNo;
	}

	public String getSource() {
		return this._Source;
	}

	public void setSource(String Source) {
		this._Source = Source;
	}

	public String getDestination() {
		return this._Destination;
	}

	public void setDestination(String Destination) {
		this._Destination = Destination;
	}

	public String getRemarks() {
		return this._Remarks;
	}

	public void setRemarks(String Remarks) {
		this._Remarks = Remarks;
	}

	public int getDistance() {
		return this._Distance;
	}

	public void setDistance(int Distance) {
		this._Distance = Distance;
	}

	@Override
	public String toString() {
		return "Tour [TourID=" + getTourID() + ", VehicleNo=" + getVehicleNo()
				+ ", Source=" + getSource() + ", Destination="
				+ getDestination() + ", Remarks=" + getRemarks()
				+ ", Distance=" + getDistance() + "]";
	}
}
