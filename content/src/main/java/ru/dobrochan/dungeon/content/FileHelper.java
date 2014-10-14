
package ru.dobrochan.dungeon.content;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SkinnyMan
 */
public final class FileHelper
{
	private FileHelper()
	{ }

	/**
	 * Opens the specified text file and returns all content as String.
	 *
	 * @param filePath the path to opened file
	 * @return the text file as String
	 * @throws FileNotFoundException the file doesn't exist
	 */
	public static String ReadFileToEnd(String filePath) throws FileNotFoundException
	{
		try
		{
			FileReader fr = new FileReader(filePath);
			BufferedReader br = new BufferedReader(fr);

			StringBuilder sb = new StringBuilder();
			String tmp;
			while ( (tmp = br.readLine())!= null )
			{
				sb.append(tmp).append("\r\n");
			}
			return sb.toString();
		}
		catch (IOException ex)
		{
			Logger.getLogger(FileHelper.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}
}
