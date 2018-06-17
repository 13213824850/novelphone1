package com.novel.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.novel.bean.Report;
import com.novel.bean.ReportExample;
import com.novel.bean.ReportExample.Criteria;
import com.novel.mapper.ReportMapper;
import com.novel.service.ReportService;
import com.novel.until.Msg;

@Service
public class ReportServiceimpl implements ReportService {

	@Autowired
	private ReportMapper reportMapper;
	@Override
	public int addReport(Report report) {
		//查询是否存在
		ReportExample example = new ReportExample();
		Criteria criteria = example.createCriteria();
		criteria.andNovelidEqualTo(report.getNovelid());
		List<Report> selectByExample = reportMapper.selectByExample(example);
		if(selectByExample.size()==0) {
			report.setCreatetime(new Date());
			report.setUpdatetime(new Date());
			report.setState(0);
			reportMapper.insert(report);
			return 1;
		}
		return 0;
	}
	@Override
	public int deleteReport(Integer id) {
		int deleteByPrimaryKey = reportMapper.deleteByPrimaryKey(id);
		return deleteByPrimaryKey;
	}
	@Override
	public Msg getReport(String reporttype, Integer pn) {
		ReportExample example = new ReportExample();
		Criteria criteria = example.createCriteria();
		criteria.andReporttypeEqualTo(reporttype);
		example.setOrderByClause("state DESC");
		PageHelper.startPage(pn,8);
		List<Report> selectByExample = reportMapper.selectByExample(example);
		PageInfo pageinfo = new PageInfo<>(selectByExample, 8);
		return Msg.success().add("pageinfo", pageinfo);
	}
	@Override
	public Msg updateState(Integer id) {
		Report report = new Report();
		report.setId(id);
		report.setState(1);
		reportMapper.updateByPrimaryKeySelective(report);
		return Msg.success();
	}

}
