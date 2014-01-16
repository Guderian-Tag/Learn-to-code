package com.line0.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.line0.OrderDetailActivity;
import com.umeng.analytics.MobclickAgent;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.widget.Toast;

public class FileUtils {

	/**
	 * 保存图片
	 * @param mSignBitmap
	 * @param _path 图片保存路径
	 * @return
	 */
	public static boolean createFile(Bitmap mSignBitmap,String _path,Context context) {
		 String sdStatus = Environment.getExternalStorageState();
		 if(!sdStatus.equals(Environment.MEDIA_MOUNTED)){
			 Toast.makeText(context, "SD 卡不可用", Toast.LENGTH_SHORT).show();
			 return false;
		 }
		ByteArrayOutputStream baos = null;
		FileOutputStream outputStream = null;
		try {
			baos = new ByteArrayOutputStream();
			mSignBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
			byte[] photoBytes = baos.toByteArray();
			if (photoBytes != null) {
				outputStream = new FileOutputStream(new File(_path));
				outputStream.write(photoBytes);
			}
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			MobclickAgent.reportError(context,e.getMessage());
		} finally {
			try {
				if (baos != null)
					baos.flush();
					baos.close();
				if(outputStream!=null)
					outputStream.flush();
					outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
				MobclickAgent.reportError(context,e.getMessage());
			}
		}
		return false;
	}
	
	/**
	 * 生成txt文件保存
	 * @param phoneNumber
	 * @param context
	 * @param path
	 * @return
	 */
	public static boolean saveTxt(String phoneNumber,Context context,String path){
		 String sdStatus = Environment.getExternalStorageState();
		 if(!sdStatus.equals(Environment.MEDIA_MOUNTED)){
			 Toast.makeText(context, "SD 卡不可用", Toast.LENGTH_SHORT).show();
			 return false;
		 }
		 File file = new File(path);
		 file.exists();
		 file.mkdirs();
		 String p = path+File.separator+"1.txt";
		 FileOutputStream outputStream = null;
		 try {
			 outputStream = new FileOutputStream(new File(p));
			 String msg = new String(phoneNumber);
			 outputStream.write(msg.getBytes("UTF-8"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			MobclickAgent.reportError(context,e.getMessage());
			return false;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			MobclickAgent.reportError(context,e.getMessage());
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			MobclickAgent.reportError(context,e.getMessage());
			return false;
		}finally{
			if(outputStream!=null){
				try {
					outputStream.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	} 
	
	/**
	 * 读取txt文件的内容
	 * @param path
	 * @return
	 */
	public static String readPhoneNumber(String path){
		byte Buffer[] = new byte[1024];
		//得到文件输入流
		FileInputStream in = null;
		File file = new File(path+File.separator+"1.txt");
		if(file.exists()){
			ByteArrayOutputStream outputStream = null;
			try {
				in = new FileInputStream(file);
				//读出来的数据首先放入缓冲区，满了之后再写到字符输出流中
				int len = in.read(Buffer);
				//创建一个字节数组输出流
				outputStream = new ByteArrayOutputStream();
				outputStream.write(Buffer,0,len);
				//把字节输出流转String
				return new String(outputStream.toByteArray());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				if(in!=null){
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(outputStream!=null){
					try {
						outputStream.flush();
						outputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}
	
}
