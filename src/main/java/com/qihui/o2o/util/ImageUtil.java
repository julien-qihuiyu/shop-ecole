package com.qihui.o2o.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Random;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public class ImageUtil {
	private static String seperator = System.getProperty("file.separator");
	private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			private static final Random r = new Random();

	public static String getImgBasePath() {		
		String os = System.getProperty("os.name");
		String basePath = "";
		if (os.toLowerCase().startsWith("win")) {
			basePath = "D:/projectdev/image/";
		} else {
			basePath = "/Users/qihuiyu/image";
		}
		basePath = basePath.replace("/", seperator);
		return basePath;
	}

	public static String getShopImagePath(long shopId) {
		String imagePath = "upload/item/shop/" + shopId + "/";
		return imagePath.replace("/", seperator);
	}

	/**
	 *  	File is the spring input file stream
	 * @param thumbnailInputStream
	 * @param targetAddr
	 * @return
	 */
	public static String generateThumbnail(InputStream thumbnailInputStream, String fileName, String targetAddr) {
		String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();

		String realFileName = getRandomFileName();
		String extension = getFileExtension(fileName);
		makeDirPath(targetAddr);
		String relativeAddr = targetAddr + realFileName + extension;
		File dest = new File(getImgBasePath() + relativeAddr);
		try {
			Thumbnails.of(thumbnailInputStream).size(400,400)
			.watermark(Positions.BOTTOM_RIGHT,
					ImageIO.read(new File(basePath +"/watermark.png")), 0.5f)
			.outputQuality(0.8f).toFile(dest);
		} catch (IOException e) {
			throw new RuntimeException("Failed to create thumbnail：" + e.toString());
		}
		
		return relativeAddr;
	}

	/**
	 * create /Users/qihuiyu/image
	 * @param targetAddr
	 */
	private static void makeDirPath(String targetAddr) {
		String realFileParentPath = getImgBasePath() + targetAddr;
		File dirPath = new File(realFileParentPath);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
	}

	/**
	 * get extension name of input file stream
	 * @param cFile
	 * @return
	 */
	private static String getFileExtension(String cFile) {
		String FileName = cFile;
		return FileName.substring(FileName.lastIndexOf("."));
	}

	/**
	 * create random file name current time+5 digit of random numbers
	 * @return
	 */
	public static String getRandomFileName() {
		int rannum = r.nextInt(89999) + 10000;
		String nowTimeStr = sDateFormat.format(new Date());
		return nowTimeStr + rannum;
	}
	
	public static void deleteFileOrPath(String storePath) {
		File fileOrPath = new File(getImgBasePath() + storePath);
		if(fileOrPath.exists()) {
			if(fileOrPath.isDirectory()) {
				File files[] = fileOrPath.listFiles();
				for(int i = 0; i< files.length; i++) {
					files[i].delete();
				}
			}
			fileOrPath.delete();
		}
	}

}
