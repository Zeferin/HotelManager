import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main
{
	private static Logger logger = Logger.getLogger("Hotel");

	public static Integer meniuPrincipal(Scanner sc)
	{
		System.out.println("Operatiuni:");
		System.out.println("1. Rezervare noua");
		System.out.println("2. Stergere rezervare");
		System.out.println("3. Afisare rezervari");
		System.out.println("4. Iesire");
		Integer optiune = sc.nextInt();
		return optiune;
	}

	public static void optiunea1(Scanner sc, Hotel hotel)
	{
		System.out.println("Nr camera:");
		Integer nrcamera = sc.nextInt();
		System.out.println("An:");
		Integer an = sc.nextInt();
		System.out.println("Luna:");
		Integer luna = sc.nextInt();
		System.out.println("Zi:");
		Integer zi = sc.nextInt();
		try {
			hotel.adaugaRezervare(nrcamera, an, luna, zi);
		} catch (Exception e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	public static void optiunea2(Scanner sc, Hotel hotel)
	{
		System.out.println("Nr camera:");
		Integer nrcamera = sc.nextInt();
		System.out.println("An:");
		Integer an = sc.nextInt();
		System.out.println("Luna:");
		Integer luna = sc.nextInt();
		System.out.println("Zi:");
		Integer zi = sc.nextInt();
		hotel.stergeRezervare(nrcamera, an, luna, zi);
	}

	public static void persistData(String fileName, Hotel hotel)
    {
        FileOutputStream file = null;
        ObjectOutputStream out = null;
        try {

            file = new FileOutputStream(fileName);
            out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(hotel);
            System.out.println("Persistency done");

        }catch(Exception e)
        {
            e.printStackTrace();
        }
        finally {


                try {
                    if(out!= null) {
                        out.close();
                    }
                    if(file != null)
                    {
                        file.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }


        }
    }

	public static void optiunea3(Scanner sc, Hotel hotel)
	{
		System.out.println("Nr camera:");
		Integer nrRoom = sc.nextInt();

		System.out.println(hotel.getRoomDetails(nrRoom));
	}

	public static Hotel init(String fileName)
    {
        FileInputStream file = null;
        ObjectInputStream in = null;

        try {
            file = new FileInputStream(fileName);
            in = new ObjectInputStream(file);
            Object obj = in.readObject();
            if(obj instanceof  Hotel)
            {
                return (Hotel)obj;
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        finally {
            try{
                if(in!=null)
                {
                    in.close();
                }
                if(file != null)
                {
                    file.close();
                }
            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }

	public static void main(String[] args)
	{
		logger.log(Level.INFO, "Program started");
		Hotel hotel = init("hotel.db");
		if(hotel == null)
        {
			logger.log(Level.INFO, "load persistency failed. Create default hotel");
            ArrayList<Room> listRoom = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
				logger.log(Level.FINE, "Adding room nr "+i);
                listRoom.add(new Room(i));
            }
            hotel = new Hotel("Cismigiu", listRoom);
        }
        else
		{
			logger.log(Level.INFO, "load persistency success");
		}
		Scanner sc = new Scanner(System.in);
		while (true)
		{
			Integer optiune = meniuPrincipal(sc);
			switch (optiune)
			{
			case 1:
				optiunea1(sc, hotel);
				break;
			case 2:
				optiunea2(sc, hotel);
				break;
			case 3:
				optiunea3(sc, hotel);
				break;
			case 4:
			    persistData("hotel.db", hotel);
				return;
			default:
				System.out.println("Optiunea nu exista");
				break;
			}
		}
	}
}
