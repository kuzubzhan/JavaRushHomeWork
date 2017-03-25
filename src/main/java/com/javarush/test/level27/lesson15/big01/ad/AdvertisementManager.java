package com.javarush.test.level27.lesson15.big01.ad;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;

import java.util.Collections;
import java.util.Comparator;

public class AdvertisementManager {
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() {
        Collections.sort(storage.list(), new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                int var = Long.compare(o2.getAmountPerOneDisplaying(), o1.getAmountPerOneDisplaying());
                if (var != 0)
                    return var;
                return Long.compare(o1.getAmountPerOneDisplaying() * 1000 / o1.getDuration(), o2.getAmountPerOneDisplaying() * 1000 / o2.getDuration());
            }
        });

        boolean isNotExist = true;
        for (Advertisement ad : storage.list()) {
            if (ad.getDuration() <= timeSeconds) {
                isNotExist = false;
                ConsoleHelper.writeMessage(String.format("%s is displaying... %d, %d", ad.getName(),
                        ad.getAmountPerOneDisplaying(), ad.getAmountPerOneDisplaying() * 1000 / ad.getDuration()));
                timeSeconds -= ad.getDuration();
                ad.revalidate();
            }
        }
        if (isNotExist)
            throw new NoVideoAvailableException();
    }
}
