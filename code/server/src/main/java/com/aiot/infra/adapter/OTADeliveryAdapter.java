package com.aiot.infra.adapter;

import com.aiot.domain.port.OTADeliveryPort;
import com.aiot.domain.shared.VehicleId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OTADeliveryAdapter implements OTADeliveryPort {

    private static final Logger log =
            LoggerFactory.getLogger(OTADeliveryAdapter.class);

    @Override
    public DeliveryProgress deliverPackage(VehicleId vehicleId, OTAPackage pkg,
                                           Optional<Long> resumeFrom)
            throws OTADeliveryException {
        log.info("OTA delivering to vehicle={} version={} size={} resume={}",
                vehicleId.id(), pkg.version(), pkg.size(),
                resumeFrom.orElse(0L));
        return new DeliveryProgress(pkg.size(), pkg.size(), true);
    }

    @Override
    public void cancelDelivery(VehicleId vehicleId) {
        log.info("OTA delivery cancelled for vehicle={}", vehicleId.id());
    }
}
