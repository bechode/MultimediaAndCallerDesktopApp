package com.multimedia.chatsocket;

import com.multimedia.dto.AgendamientoDTO;

import java.util.concurrent.Flow;

/**
 *
 * @author EnriqueVazquez
 */
class AgendaSubscription implements Flow.Subscription {
    private final Flow.Subscriber<? super AgendamientoDTO> subscriber;
    private final AgendamientoDTO[] data;
    private int currentIndex = 0;
    private boolean isCancelled = false;

    public AgendaSubscription(Flow.Subscriber<? super AgendamientoDTO> subscriber, AgendamientoDTO[] data) {
        this.subscriber = subscriber;
        this.data = data;
    }

    @Override
    public void request(long n) {
        if (isCancelled) {
            return;
        }

        for (int i = 0; i < n && currentIndex < data.length; i++) {
            subscriber.onNext(data[currentIndex++]);
        }

        if (currentIndex == data.length) {
            subscriber.onComplete();
        }
    }

    @Override
    public void cancel() {
        isCancelled = true;
    }
}

