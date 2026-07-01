package com.aiot.infra.adapter;

import com.aiot.domain.port.DrivingBehaviorTrackingPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DrivingBehaviorTrackingAdapter implements DrivingBehaviorTrackingPort {

    private static final Logger log =
            LoggerFactory.getLogger(DrivingBehaviorTrackingAdapter.class);

    @Override
    public void onHardBrakingDetected(HardBrakingEvent event) {
        log.info("Hard braking: deceleration={} m/s² at {}",
                event.deceleration(), event.timestamp());
    }

    @Override
    public void onHardAccelerationDetected(HardAccelerationEvent event) {
        log.info("Hard acceleration: acceleration={} m/s² at {}",
                event.acceleration(), event.timestamp());
    }
}
