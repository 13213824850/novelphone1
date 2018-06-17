package com.novel.service;

import com.novel.bean.Report;
import com.novel.until.Msg;

public interface ReportService {

	int addReport(Report report);
    int deleteReport(Integer id);
	Msg getReport(String reporttype, Integer pn);
	Msg updateState(Integer id);
}
