package hotelManageMentTest;

public class Room {
	private int roomNum;
	private String roomKind;
	private String rentPerson;
	
		
	public Room(int roomNum, String roomKind, String rentPerson) {
		super();
		this.roomNum = roomNum;
		this.roomKind = roomKind;
		this.rentPerson = rentPerson;
	}
	
	public  int getRoomNum() {
		return roomNum;
	}
	public  void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}
	public  String getRoomKind() {
		return roomKind;
	}
	public  void setRoomKind(String roomKind) {
		this.roomKind = roomKind;
	}
	public  String getRentPerson() {
		return rentPerson;
	}
	public  void setRentPerson(String rentPerson) {
		this.rentPerson = rentPerson;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rentPerson == null) ? 0 : rentPerson.hashCode());
		result = prime * result + ((roomKind == null) ? 0 : roomKind.hashCode());
		result = prime * result + roomNum;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		if (rentPerson == null) {
			if (other.rentPerson != null)
				return false;
		} else if (!rentPerson.equals(other.rentPerson))
			return false;
		if (roomKind == null) {
			if (other.roomKind != null)
				return false;
		} else if (!roomKind.equals(other.roomKind))
			return false;
		if (roomNum != other.roomNum)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Room [roomNum=" + roomNum + ", roomKind=" + roomKind + ", rentPerson=" + rentPerson + "]";
	}
	
	
}
