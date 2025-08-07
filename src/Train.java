public class Train {
    private int id;
    private String name;
    private String source;
    private String destination;
    private int totalSeats;
    private int availableSeats;

    public Train(int id, String name, String source, String destination, int totalSeats){
        this.id = id;
        this.name = name;
        this.source = source;
        this.destination = destination;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public boolean bookSeats(int count){
        if(count<=availableSeats){
            availableSeats -= count;
            return true;
        }
        return false;
    }

    public void cancelSeats(int count){
        availableSeats+=count;
    }

    @Override
    public String toString() {
        return id+" | "+name+" | "+source+" --> "+destination+" | Total Seats : "+totalSeats+" | Available Seats : "+availableSeats;
    }
}
