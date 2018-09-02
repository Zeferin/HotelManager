import java.io.Serializable;
import java.util.ArrayList;

public class Room implements Serializable
{
	private Integer nr;
	private ArrayList<Rezervation> rezervations;

	public Room(Integer nr)
	{
		this.nr = nr;
		rezervations = new ArrayList<>();
	}

	public void addRezervation(Integer an, Integer luna, Integer zi) throws Exception {
		Rezervation newRezervation = new Rezervation(an, luna, zi);
		if (rezervations.contains(newRezervation))
		{
			throw new Exception("Already reserved for the given date");
		}
		int rightPlace = rezervations.size();
		int size = rezervations.size();
		for(int i=0;i<size;i++)
		{
			//Comparing two Rezervations
			if(rezervations.get(i).compareTo(newRezervation) > 0)
			{
				rightPlace = i;
				break;
			}
		}
		this.rezervations.add(rightPlace, newRezervation);
	}

	public boolean deleteRezervation(Integer an, Integer luna, Integer zi)
	{
		Rezervation aux = new Rezervation(an, luna, zi);
		return rezervations.remove(aux);
	}

	@Override
	public boolean equals(Object obj)
	{
		if(obj instanceof Room)
		{
			Room rhs = (Room) obj;
			return this.nr.equals(rhs.nr);
		}
		return false;
	}

	@Override
	public String toString()
	{
		return "{#"+rezervations.size() +" " +
				rezervations + "}";
	}
}
