package structData;

import java.util.Date;

/**
 * Shot is a class of a shot of a player.
 */
public class Shot extends Position{
    private Date time;
    
    /**
     * Shot :  default constructor with a Position
     * @param p a Position
     */
    public Shot(Position p){
        super(p);
        time = new Date();
    }
    
    /**
     * Shot :  constructor with a Position and a time
     * @param p a position
     * @param nTime
     */
    public Shot(Position p, Date nTime){
        super(p);
        time = nTime;
    } 

    /**
     * Mutator for time
     * @param nTime the shot's time
     */
    public void setTime(Date nTime){
        this.time = nTime;
    }
    
    /**
     * Accessor for time
     * @return time : the shot's time as a Date
     */
    public Date getTime(){
        return this.time;
    }
}
