package com.louis.mango.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

/**
 * 文件相关操作
 * @author Louis
 * @date Jan 14, 2019
 */
public class FileUtils {

	/**
	 * 下载文件
	 * @param response
	 * @param file
	 * @param newFileName
	 */
	public static void downloadFile(HttpServletResponse response, File file, String newFileName) {
		try {
			response.setHeader("Content-Disposition", "attachment; filename=" + new String(newFileName.getBytes("ISO-8859-1"), "UTF-8"));
			BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
			InputStream is = new FileInputStream(file.getAbsolutePath());
			BufferedInputStream bis = new BufferedInputStream(is);
			int length = 0;
			byte[] temp = new byte[1 * 1024 * 10];
			while ((length = bis.read(temp)) != -1) {
				bos.write(temp, 0, length);
			}
			bos.flush();
			bis.close();
			bos.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 读取txt文件的内容
	 * @param file 想要读取的文件路径
	 * @return 返回文件内容
	 */
	public static String readFile(String file){
		return readFile(new File(file));
	}
	
    /**
     * 读取txt文件的内容
     * @param file 想要读取的文件对象
     * @return 返回文件内容
     */
    public static String readFile(File file){
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){
            	//使用readLine方法，一次读一行
                result.append(System.lineSeparator() + s);
            }
            br.close();    
        }catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }
    
    /**
	 * 递归删除文件
	 * @param file
	 */
	public static void deleteFile(File file) {
	    // 判断是否是一个目录, 不是的话跳过, 直接删除; 如果是一个目录, 先将其内容清空.
	    if(file.isDirectory()) {
	        // 获取子文件/目录
	        File[] subFiles = file.listFiles();
	        // 遍历该目录
	        for (File subFile : subFiles) {
	            // 递归调用删除该文件: 如果这是一个空目录或文件, 一次递归就可删除. 
	        	// 如果这是一个非空目录, 多次递归清空其内容后再删除
	            deleteFile(subFile);
	        }
	    }
	    // 删除空目录或文件
	    file.delete();
	}
	
	/**
	 * 获取项目根路径
	 * @return
	 */
	public static String getProjectPath() {
		String classPath = getClassPath();
		return new File(classPath).getParentFile().getParentFile().getAbsolutePath();
	}

	/**
	 * 获取类路径
	 * @return
	 */
	public static String getClassPath() {
		String classPath = FileUtils.class.getClassLoader().getResource("").getPath();
		return classPath;
	}
	
	public static void main(String[] args){
//        File file = new File("D:/errlog.txt");
//        System.out.println(readFile(file));
        System.out.println(getClassPath());
        System.out.println(getProjectPath());
    }
}
