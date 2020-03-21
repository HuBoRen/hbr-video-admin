package com.hbr.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hbr.enums.VideoStatusEnum;
import com.hbr.pojo.Bgm;
import com.hbr.pojo.Videos;
import com.hbr.service.IVideoService;
import com.hbr.service.impl.VideoServiceImpl;
import com.hbr.utils.HbrJSONResult;
import com.hbr.utils.PagesResult;

@Controller
@RequestMapping("video")
public class VideoController {
	
	@Autowired
	private IVideoService videoService;
	@Value("${FILE_SPACE}")
	private String FILE_SPACE;
	
	
	
@GetMapping("/showAddBgm")
public String center() {
	return "video/addBgm";
}
@GetMapping("/showReportList")
public String showReportList() {
	return "video/reportList";
}

@GetMapping("/showBgmList")
public String showBgmList() {
	
	return "video/bgmList";
}

	
	  @GetMapping("/showVideosList") 
	  public String showVideosList() {
		  
		  return "video/videosList";
		  }
	  
	  @PostMapping("/videoList")
	  @ResponseBody 
	  public PagesResult videoList(Videos videos,Integer page) {
	  PagesResult result = videoService.queryVideos( page == null ? 1 : page, 10);
	  return result; 
	  }
	 
 
@PostMapping("/bgmUpload")
@ResponseBody
public HbrJSONResult bgmUpload(@RequestParam("file") MultipartFile[] files) throws IOException {
	//c盘文件保存的命名空间
	//String fileSpace="C:"+File.separator+"hbr_videos"+File.separator+"mvc-bgm";
	
	//保存到数据库中的相对路径
	String uplpadPathDB=File.separator+"bgm";
	FileOutputStream fileOutputStream=null;
	InputStream inputStream=null; 
	try {
		if(files!=null && files.length>0) {
			String fileName=files[0].getOriginalFilename();
			if(StringUtils.isNotBlank(fileName)) {
				//文件上传的最终保存路径
				String finalPath=FILE_SPACE+uplpadPathDB+File.separator+fileName;
				//设置数据库保存的路径
				uplpadPathDB+=(File.separator+fileName);
				File outFile=new File(finalPath);
				if(outFile.getParentFile()!=null || !outFile.getParentFile().isDirectory()) {
					//创建父文件夹
					outFile.getParentFile().mkdirs();
				}
				fileOutputStream=new FileOutputStream(outFile);
				inputStream=files[0].getInputStream();
				IOUtils.copy(inputStream, fileOutputStream);
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
		return HbrJSONResult.errorMsg("上传出现问题");
		
	}finally {
		if(fileOutputStream!=null) {
			fileOutputStream.flush();
			fileOutputStream.close();
		}
	}
	return HbrJSONResult.ok(uplpadPathDB);
}


@PostMapping("/addBgm")
@ResponseBody
public HbrJSONResult addBgm(Bgm bgm) {
	
	videoService.addBgm(bgm);
	return HbrJSONResult.ok();
}

@PostMapping("/reportList")
@ResponseBody
public PagesResult reportList(Integer page) {
	
	PagesResult result = videoService.queryReportList(page,10);
	return result;
}

@PostMapping("/forbidVideo")
@ResponseBody
public HbrJSONResult forbidVideo(String videoId) {
	Videos videos1 = videoService.queryVideoStatus(videoId);
	if(videos1.getStatus()==2) {
		return HbrJSONResult.errorMsg("您好，当前就是禁止播放状态");
	}
	videoService.updateVideoStatus(videoId, VideoStatusEnum.FORBID.value);
	return HbrJSONResult.ok();
}
@PostMapping("/queryBgmList")
@ResponseBody
public PagesResult queryBgmList(Integer page) {
	PagesResult result = videoService.queryBgmList(page, 10);
	return result;
}

@PostMapping("/delBgm")
@ResponseBody
public HbrJSONResult delBgm(String bgmId) {
	videoService.delBgm(bgmId);
	return HbrJSONResult.ok();
}

@PostMapping("/recoveryVideo")
@ResponseBody
public HbrJSONResult recoveryVideo(String videoId) {
	Videos videos1 = videoService.queryVideoStatus(videoId);
	if(videos1.getStatus()==1) {
		return HbrJSONResult.errorMsg("您好，当前就是可以播放状态");
	}
	videoService.recoveryVideo(videoId, VideoStatusEnum.SUCCESS.value);
	return HbrJSONResult.ok();
}

@PostMapping("/delVideos")
@ResponseBody
public HbrJSONResult delVideos(String videoId) {
	videoService.delVideo(videoId);
	return HbrJSONResult.ok();
}
}
