package com.smart.api.service.impl;

import com.smart.api.service.ReportService;
import com.smart.db.dao.IprecordDAO;
import com.smart.db.model.Iprecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    IprecordDAO iprecordDAO;

    @Override
    public List<Iprecord> reportIp() {
        return iprecordDAO.getReportIp()
                .stream()
                //reversed 降序  默认升序
                .sorted(Comparator.comparing(Iprecord::getCount).reversed()).
                        map(e -> {
                                    e.setAddress(new StringBuffer(e.getCountry()).append(e.getCity()).append(e.getRegionName())
                                            .toString());
                                    return e;
                                }
                        ).collect(Collectors.toList());
    }
}
