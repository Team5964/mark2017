package org.usfirst.frc.team5964.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//https://github.com/FRC3620/FRC3620_2015_AverageJava/

public class DataLogger {
	File parentDirectory;
	long minimumInterval = 500;

	public DataLogger(File directory) {
		parentDirectory = directory;
	}

	List<String> dataNames = new ArrayList<>();
	List<String> dataValues = new ArrayList<>();
	public void addDataItem(String name, double value)
	{
		String valueString = String.valueOf(value);
		dataNames.add(name);
		dataValues.add(valueString);
	}

	public void addDataItem(String name, int value)
	{
		String valueString = String.valueOf(value);
		dataNames.add(name);
		dataValues.add(valueString);
	}

	public void addDataItem(String name, boolean value)
	{
		String valueString = String.valueOf(value);
		dataNames.add(name);
		dataValues.add(valueString);
	}

	public void addDataItem(String name, String value)
	{
		String valueString = String.valueOf(value);
		dataNames.add(name);
		dataValues.add(valueString);
	}

	PrintStream ps;
	long startTime;
	long timeUpdated;
	long timeSinceLog;

	public boolean shouldLogData() {
		long now = System.currentTimeMillis();
		if((ps==null) || (now - timeSinceLog) > minimumInterval)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public void saveDataItems()
	{
		if(shouldLogData())
		{
			try
			{
				if (ps == null)
				{
					synchronized (this) {
						if (ps == null) {
							String timestampString = LogTimestamp.getTimestampString();
							if (timestampString != null) {
								File logFile = new File(parentDirectory, timestampString + ".csv");
								ps = new PrintStream(new FileOutputStream(logFile));
								ps.print("time,timeSinceStart");
								writelist(ps, dataNames);
								startTime = System.currentTimeMillis();
							}
						}
					}
				}
				if (ps != null)
				{
					timeUpdated = (System.currentTimeMillis()-startTime);
					ps.print(getDate());
					ps.print(',');
					ps.print(timeUpdated);
					writelist(ps, dataValues);
					ps.flush();
					timeSinceLog = System.currentTimeMillis();
				}
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}

		dataValues.clear();
		dataNames.clear();

	}

	private void writelist(PrintStream stream, List<String> list)
	{
		for(int i = 0;i < list.size(); i++)
		{
			stream.print(',');
			stream.print(list.get(i));
		}
		stream.println();
	}

	public String getDate()
	{
		Date curDate = new Date();
		String DateToStr = format.format(curDate);
		return DateToStr;
	}

	SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss.SS");

	public void setMinimumInterval(long minimumInterval) {
		this.minimumInterval = minimumInterval;
	}
}
