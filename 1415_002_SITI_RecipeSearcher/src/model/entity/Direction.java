package model.entity;

public class Direction
{
	private Integer directionId;
	private String description;
	
	public Direction(Integer directionId, String description)
	{
		this.directionId = directionId;
		this.description = description;
	}
	
	public Integer getDirectionId()
	{
		return directionId;
	}
	
	public void setDirectionId(Integer directionId)
	{
		this.directionId = directionId;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
}
