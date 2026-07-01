package com.aiot.infra.adapter;

import com.aiot.domain.port.MediaSessionPort;
import com.aiot.domain.shared.AccountId;
import com.aiot.domain.shared.DriverId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MediaSessionAdapter implements MediaSessionPort {

    private static final Logger log =
            LoggerFactory.getLogger(MediaSessionAdapter.class);

    @Override
    public SessionHandle establishSession(AccountId participant,
                                          DriverId driverId,
                                          SessionType sessionType)
            throws MediaSessionException {
        String roomId = "room-" + UUID.randomUUID().toString().substring(0, 8);
        log.info("[MEDIA-STUB] Session established: room={} participant={} "
                        + "driver={} type={}",
                roomId, participant.id(), driverId.id(), sessionType);
        return new SessionHandle(roomId);
    }

    @Override
    public void terminateSession(SessionHandle handle)
            throws MediaSessionException {
        log.info("[MEDIA-STUB] Session terminated: {}", handle.sessionId());
    }
}
