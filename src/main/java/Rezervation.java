import java.io.Serializable;
import java.time.LocalDate;

public class Rezervation implements Serializable
{
    private LocalDate date;

    public Rezervation(Integer year, Integer month, Integer day)
    {
        date = LocalDate.of(year, month, day);
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof Rezervation)
        {
            Rezervation rhs = (Rezervation)obj;
            return  this.date.equals(rhs.date);
        }
        return false;
    }

    @Override
    public String toString()
    {
        return "{"+date.toString()+"}";
    }

    public int compareTo(Rezervation rhs)
    {
        //Comparing the following reservations:
        // this
        // rhs
        return this.date.compareTo(rhs.date);
    }
}
