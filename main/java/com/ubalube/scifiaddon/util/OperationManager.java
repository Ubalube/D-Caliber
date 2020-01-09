package com.ubalube.scifiaddon.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

import com.ubalube.scifiaddon.main;

public class OperationManager 
{
	public static void grabFile(String file, String dest) throws IOException
	{
		InputStream is = main.class.getResourceAsStream(file);
		OutputStream os = new FileOutputStream(dest);
		byte[] buffer = new byte[4096];
		int length;
		while ((length = is.read(buffer)) > 0) {
		    os.write(buffer, 0, length);
		}
		os.close();
		is.close();
	}
}
