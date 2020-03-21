package com.hbr.service;

import com.hbr.pojo.Bgm;
import com.hbr.pojo.Users;
import com.hbr.pojo.Videos;
import com.hbr.utils.HbrJSONResult;
import com.hbr.utils.PagesResult;

/**
 * 视频相关的接口
 * @author huboren
 *
 */
public interface IVideoService {
/**
 * 添加bgm的接口
 * @param bgm
 */
public void addBgm(Bgm bgm);
/**
 * 查询bgm列表的接口
 * @param page
 * @param pageSize
 * @return
 */
public PagesResult queryBgmList(Integer page,Integer pageSize);

public void delBgm(String bgmId);
public PagesResult queryReportList(Integer page,Integer pageSize);
public void updateVideoStatus(String videoId, Integer status);
public Videos queryVideoStatus(String videoId);
public void recoveryVideo(String videoId,Integer status);
public PagesResult queryVideos(Integer page,Integer pageSize);
public void delVideo(String videoId);
}
