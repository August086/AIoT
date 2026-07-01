package com.aiot.infra.adapter;

import com.aiot.domain.port.NotificationPort;
import com.aiot.domain.shared.AccountId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class NotificationAdapter implements NotificationPort {

    private static final Logger log =
            LoggerFactory.getLogger(NotificationAdapter.class);

    @Override
    public void pushNotification(AccountId recipient, NotificationPayload payload)
            throws NotificationException {
        log.info("[NOTIFICATION-STUB] type={} priority={} to={}: {} — {}",
                payload.type(), payload.priority(), recipient.id(),
                payload.title(), payload.body());
    }
}
