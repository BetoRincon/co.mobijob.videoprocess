/**
 * This file was auto-generated by the Titanium Module SDK helper for Android
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2018 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 */
package co.mobijob.videoprocess;


import java.io.File;
import java.util.Arrays;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import android.os.Environment;
import android.net.Uri;

// Titanium
import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiApplication;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.kroll.common.TiConfig;
import org.appcelerator.kroll.KrollDict;

//MediaResizer
import pyxis.uzuki.live.mediaresizer.MediaResizer;
import pyxis.uzuki.live.mediaresizer.MediaResizerGlobal;
import pyxis.uzuki.live.mediaresizer.data.ResizeOption;
import pyxis.uzuki.live.mediaresizer.data.VideoResizeOption;
import pyxis.uzuki.live.mediaresizer.model.MediaType;
import pyxis.uzuki.live.mediaresizer.model.ScanRequest;
import pyxis.uzuki.live.mediaresizer.model.VideoResolutionType;
import pyxis.uzuki.live.richutilskt.impl.F2;
import kotlin.jvm.functions.Function2;





@Kroll.module(name = "CoMobijobVideoprocess", id = "co.mobijob.videoprocess")
public class CoMobijobVideoprocessModule extends KrollModule {

	// Standard Debugging variables
	private static final String LCAT = "CoMobijobVideoprocessModule";
	private static final boolean DBG = TiConfig.LOGD;

	// You can define constants with @Kroll.constant, for example:
	// @Kroll.constant public static final String EXTERNAL_NAME = value;

	public CoMobijobVideoprocessModule() {
		super();
	}

	@Kroll.onAppCreate
	public static void onAppCreate(TiApplication app) {
		Log.d(LCAT, "inside onAppCreate");
		// put module init code that needs to run when the application is created
		MediaResizerGlobal.INSTANCE.initializeApplication(app);
	}

	// Methods
	@Kroll.method
	public String example() {
		Log.d(LCAT, "example called");
		return "hello world";
	}

	// Properties
	@Kroll.method
	@Kroll.getProperty
	public String getExampleProp() {
		Log.d(LCAT, "get example property");
		return "hello world";
	}

	@Kroll.method
	@Kroll.setProperty
	public void setExampleProp(String value) {
		Log.d(LCAT, "set example property: " + value);
	}

	@Kroll.method
	public void resizeVideo(KrollDict options) {


		if (options != null) {

//			 Builder variables;
			 int resWidth = 640;
			 int resheight = 480;
			 int quality = 0;
			 String inputPathFile = "";
//			 Uri contentURI = null;
			 Boolean bitmapFilter = false;

			 VideoResizeOption videoOptions;
//			 String selectedVideoPath = "";
			 ResizeOption resizeOptions;
			 String outputFilePath = "";

			 if (options.containsKey("resolution")) {

				 String resolutionString = options.getString("resolution");
				 String[] resolutionArray = resolutionString.split("x");
				 resWidth = Integer.parseInt(resolutionArray[0]);
				 resheight = Integer.parseInt(resolutionArray[1]);
				 String originPath = "";
				 String destinationPath = "";
			 }

			 if (options.containsKey("quality")) {

			 	quality = options.getInt("quality");
			 }

			 if (options.containsKey("filePath")){
			 	inputPathFile = options.getString("filePath");
//			 	contentURI = Uri.parse(inputPathFile);
//				String selectedVideoPath = getPath(contentURI);
			 	outputFilePath = getOutputFilePath(inputPathFile);
			 }



			 videoOptions = new VideoResizeOption.Builder()
					 .setVideoResolutionType(VideoResolutionType.AS480)
					 .setVideoBitrate(1000 * 1000).setAudioBitrate(128 * 1000)
					 .setAudioChannel(1)
					 .setScanRequest(ScanRequest.TRUE)
					 .build();

			 resizeOptions = new ResizeOption.Builder()
					 .setMediaType(MediaType.VIDEO)
					 .setVideoResizeOption(videoOptions)
					 .setTargetPath(inputPathFile)
					 .setOutputPath(outputFilePath)
					 .setCallback(new F2<Integer, String>(){
						 @Override
						 public void invoke(Integer code, String output) {
							 Log.d("code ", "" + code);
							 Log.d("output ", "" + output);
						 // if (options.containsKey("callback")){
						 // KrollFunction callback = options.getKrollObject("callback");
						 // KrollObject result;
						 // callback.call(result, options); // define payload
						 // }
						 Log.d(LCAT, "onInvoke execution");
						 }
						 })
					 .build();

			 MediaResizer.process(resizeOptions);

		} else {
			Log.e(LCAT, "No available options, skipping...");
		}
	}

	private  String getOutputFilePath(String inputFile){
		String splitInputPath [] = inputFile.split("/");
		String OutputPath = "";

		for(int i=0; i < splitInputPath.length-1; i++){
			OutputPath +=   splitInputPath[i]+ "/";
		}
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddhhmmss");
		String dateTime = dateFormat.format(date);
		OutputPath += "vp_" +dateTime + ".mp4";
		return OutputPath;
	}





}
