import java.io.Serializable;
import java.util.ArrayList;

public class Hotel implements Serializable
{
	private String name;
	private ArrayList<Room> allRooms;

	Hotel(String name, ArrayList<Room> rooms){
		this.name = name;
		allRooms = rooms;
	}


	@Override
	public String toString()
	{
		return "{"+name+" #"+allRooms.size()+" " + allRooms
				+ "}";
	}

	public void adaugaRezervare(Integer numarCamera, Integer an, Integer luna, Integer zi) throws Exception {
		Room roomAux = new Room(numarCamera);
		//indexOf will return the index of the given number
		int index = allRooms.indexOf(roomAux);
		if(index >= 0)
		{
			allRooms.get(index).addRezervation(an,luna,zi);
		}
	}

	public boolean stergeRezervare(Integer numarCamera, Integer an, Integer luna, Integer zi)
	{
		Room roomAux = new Room(numarCamera);
		//indexOf will return the index of the given number
		int index = allRooms.indexOf(roomAux);
		if(index >= 0)
		{
			return allRooms.get(index)
					.deleteRezervation(an, luna, zi);
		}
		return false;
	}

	public String getRoomDetails(Integer nrRoom)
	{
		Room roomAux = new Room(nrRoom);
		int index = allRooms.indexOf(roomAux);
		if(index >= 0)
		{
			return allRooms.get(index).toString();
		}
		return "Unknown room";
	}
}
