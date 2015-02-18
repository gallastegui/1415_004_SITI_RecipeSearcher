package model.filtering;

public class TimeFilter implements Filter
{
	private String timePrepAssoc;
	private String timeCookAssoc;
	private String timeTotalAssoc;
	
	public String getTimePrepAssoc()
	{
		return timePrepAssoc;
	}
	public void setTimePrepAssoc(String timePrepAssoc)
	{
		this.timePrepAssoc = timePrepAssoc;
	}
	public String getTimeCookAssoc()
	{
		return timeCookAssoc;
	}
	public void setTimeCookAssoc(String timeCookAssoc)
	{
		this.timeCookAssoc = timeCookAssoc;
	}
	public String getTimeTotalAssoc()
	{
		return timeTotalAssoc;
	}
	public void setTimeTotalAssoc(String timeTotalAssoc)
	{
		this.timeTotalAssoc = timeTotalAssoc;
	}

}
