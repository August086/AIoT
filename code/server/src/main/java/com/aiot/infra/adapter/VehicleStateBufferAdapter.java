package com.aiot.infra.adapter;

import com.aiot.domain.model.TimeRange;
import com.aiot.domain.model.VehicleStateSnapshot;
import com.aiot.domain.port.VehicleStateBuffer;
import com.aiot.domain.port.BufferException;
import com.aiot.domain.shared.TripId;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

@Component
public class VehicleStateBufferAdapter implements VehicleStateBuffer {

    private final ConcurrentLinkedDeque<VehicleStateSnapshot> deque = new ConcurrentLinkedDeque<>();
    private static final int CAPACITY = 600;

    @Override
    public List<VehicleStateSnapshot> getSnapshots(TripId tripId, TimeRange window)
            throws BufferException {
        List<VehicleStateSnapshot> result = new ArrayList<>();
        for (VehicleStateSnapshot s : deque) {
            if (!s.timestamp().isBefore(window.start())
                    && !s.timestamp().isAfter(window.end())) {
                result.add(s);
            }
        }
        if (result.isEmpty()) {
            throw new BufferException.WindowNotCoveredException(
                    "No snapshots in window: " + window);
        }
        return result;
    }

    public void append(VehicleStateSnapshot snapshot) {
        if (deque.size() >= CAPACITY) {
            deque.removeFirst();
        }
        deque.addLast(snapshot);
    }
}
