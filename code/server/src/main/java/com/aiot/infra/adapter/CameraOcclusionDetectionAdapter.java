package com.aiot.infra.adapter;

import com.aiot.domain.port.CameraOcclusionDetectionPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CameraOcclusionDetectionAdapter
        implements CameraOcclusionDetectionPort {

    private static final Logger log =
            LoggerFactory.getLogger(CameraOcclusionDetectionAdapter.class);

    @Override
    public void onOcclusionDetected(OcclusionDetectedSignal event) {
        log.warn("Camera occlusion: sensor={}, type={}",
                event.sensorId(), event.occlusionType());
    }

    @Override
    public void onOcclusionRemoved(OcclusionRemovedSignal event) {
        log.info("Camera occlusion removed: sensor={}, duration={}ms",
                event.sensorId(), event.durationMillis());
    }
}
