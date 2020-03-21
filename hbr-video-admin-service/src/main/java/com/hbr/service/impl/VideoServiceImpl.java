package com.hbr.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hbr.enums.BGMOperatorTypeEnum;
import com.hbr.mapper.BgmMapper;
import com.hbr.mapper.UsersReportMapperCustom;
import com.hbr.mapper.VideosMapper;
import com.hbr.pojo.Bgm;
import com.hbr.pojo.BgmExample;
import com.hbr.pojo.Videos;
import com.hbr.pojo.VideosExample;
import com.hbr.pojo.vo.ReportsVO;
import com.hbr.service.IVideoService;
import com.hbr.utils.HbrJSONResult;
import com.hbr.utils.JsonUtils;
import com.hbr.utils.PagesResult;
import com.hbr.web.util.ZKCurator;

@Service
public class VideoServiceImpl implements IVideoService{
@Autowired
private BgmMapper bgmMapper;
@Autowired
private Sid sid;
@Autowired
private ZKCurator zkCurator;
@Autowired
private UsersReportMapperCustom usersReportMapperCustom;
@Autowired
private VideosMapper videosMapper;
	@Override
	public void addBgm(Bgm bgm) {
		// TODO Auto-generated method stub
		String id=sid.nextShort();
		bgm.setId(id);
		bgmMapper.insert(bgm);
		Map<String, String> map=new HashMap<>();
		map.put("operType", BGMOperatorTypeEnum.ADD.type);
		map.put("path", bgm.getPath());
		zkCurator.sendBgmOperator(id, JsonUtils.objectToJson(map));
	}
	@Override
	public PagesResult queryBgmList(Integer page, Integer pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, pageSize);
		BgmExample example=new BgmExample();
		List<Bgm> list = bgmMapper.selectByExample(example);
		PageInfo<Bgm> pageList=new PageInfo<Bgm>(list);
		PagesResult result=new PagesResult();
		result.setTotal(pageList.getPages());
		result.setRows(list);
		result.setPage(page);
		result.setRecords(pageList.getTotal());
		return result;
	}
	@Override
	public void delBgm(String bgmId) {
		// TODO Auto-generated method stub
		Bgm bgm = bgmMapper.selectByPrimaryKey(bgmId);
		bgmMapper.deleteByPrimaryKey(bgmId);
		Map<String, String> map=new HashMap<>();
		map.put("operType", BGMOperatorTypeEnum.DELETE .type);
		map.put("path", bgm.getPath());
		zkCurator.sendBgmOperator(bgmId,JsonUtils.objectToJson(map));
	}
	@Override
	public PagesResult queryReportList(Integer page,Integer pageSize) {
		// TODO Auto-generated method stub
		
		PageHelper.startPage(page, pageSize);
		List<ReportsVO> list = usersReportMapperCustom.selectAllVideoReport();
		PageInfo<ReportsVO> pageList=new PageInfo<ReportsVO>(list);
		PagesResult result=new PagesResult();
		result.setRows(list);
		result.setPage(page);
		result.setRecords(pageList.getTotal());
		result.setRecords(pageList.getPages());
		return result;
	}
	@Override
	public void updateVideoStatus(String videoId, Integer status) {
		// TODO Auto-generated method stub
		Videos videos=new Videos();
		videos.setStatus(status);
		videos.setId(videoId);
		videosMapper.updateByPrimaryKeySelective(videos);
		
	}
	@Override
	public void recoveryVideo(String videoId, Integer status) {
		// TODO Auto-generated method stub
		Videos videos=new Videos();
		videos.setStatus(status);
		videos.setId(videoId);
		videosMapper.updateByPrimaryKeySelective(videos);
	}
	@Override
	public Videos queryVideoStatus(String videoId) {
		// TODO Auto-generated method stub
		Videos videos = videosMapper.selectByPrimaryKey(videoId);
		
		return videos;
	}
	@Override
	public PagesResult queryVideos(Integer page, Integer pageSize) {
		// TODO Auto-generated method stub
		VideosExample example=new VideosExample();
		List<Videos> list = videosMapper.selectByExample(example);
		PageHelper.startPage(page, pageSize);
		PageInfo<Videos> pageList=new PageInfo<Videos>(list);
		PagesResult result=new PagesResult();
		result.setPage(page);
		result.setTotal(pageList.getPages());
		result.setRecords(pageList.getTotal());
		result.setRows(list);
		return result;
	
	}
	@Override
	public void delVideo(String videoId) {
		// TODO Auto-generated method stub
		videosMapper.deleteByPrimaryKey(videoId);
	}

}
