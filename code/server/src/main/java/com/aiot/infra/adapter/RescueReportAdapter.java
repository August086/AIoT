package com.aiot.infra.adapter;

import com.aiot.domain.model.RescueReport;
import com.aiot.domain.port.RescueReportPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RescueReportAdapter implements RescueReportPort {

    private static final Logger log =
            LoggerFactory.getLogger(RescueReportAdapter.class);

    @Override
    public void deliverRescueReport(RescueReport report)
            throws RescueReportException {
        log.info("[RESCUE-STUB] Report delivered: driver={} location={}",
                report.driverId(), report.gps());
    }
}
