package models;

public class Tag{
	private String _id;
	private String igdbId;
	private String name;
	private String type;
	
	
	
	/*
	 * Constructors, Getters and Setters
	 */	
	public Tag(String igdbId,String name, String type) {
		super();
		this.igdbId = igdbId;
		this.name = name;
		this.type = type;
		this._id = type+":"+igdbId;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getIgdbId() {
		return igdbId;
	}
	public void setIgdbId(String igdbId) {
		this.igdbId = igdbId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	/*
	 * Overrides
	 */
	@Override
    public boolean equals(Object o) { 
  
        // If the object is compared with itself then return true   
        if (o == this) { 
            return true; 
        } 
        if (!(o instanceof Tag)) { 
            return false; 
        } 
        Tag c = (Tag) o; 
        return this._id.equalsIgnoreCase(c._id);
    }
	@Override
	public String toString() {
		return "Tag [\n\t_id=" + _id + "\n\tigdbId=" + igdbId + "\n\tname=" + name + "\n\ttype=" + type + "]";
	} 
}
