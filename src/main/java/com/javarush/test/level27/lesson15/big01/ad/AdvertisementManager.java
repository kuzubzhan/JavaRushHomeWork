package com.javarush.test.level27.lesson15.big01.ad;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;

import java.util.*;

public class AdvertisementManager {
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() {
        List<Advertisement> outAdvList = search(storage.list());

        Collections.sort(outAdvList, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                int var = Long.compare(o2.getAmountPerOneDisplaying(), o1.getAmountPerOneDisplaying());
                if (var != 0)
                    return var;
                return Long.compare(o1.getAmountPerOneDisplaying() * 1000 / o1.getDuration(), o2.getAmountPerOneDisplaying() * 1000 / o2.getDuration());
            }
        });

        boolean isNotExist = true;
        for (Advertisement ad : outAdvList) {
            isNotExist = false;
            ConsoleHelper.writeMessage(String.format("%s is displaying... %d, %d", ad.getName(), ad.getAmountPerOneDisplaying(), ad.getAmountPerOneDisplaying() * 1000 / ad.getDuration()));
            ad.revalidate();
        }
        if (isNotExist)
            throw new NoVideoAvailableException();
    }

    private List<Advertisement> tempStorage = new ArrayList<>();
    private List<List<Advertisement>> checkedAdv = new ArrayList<>();

    private List<Advertisement> search(List<Advertisement> advertisements) {
        if (advertisements == null)
            return null;
        List<Advertisement> list = new ArrayList<>();
        for (Advertisement advertisement : advertisements) {
            if (advertisement.getHits() > 0)
                list.add(advertisement);
        }

        for (List<Advertisement> advertisementList : checkedAdv) {
            if (advertisementList.containsAll(advertisements) && advertisements.containsAll(advertisementList)) {
                return null;
            }
        }
        checkedAdv.add(list);

        if (getAllDuration(list) <= timeSeconds) {
            if (getAllAmount(list) > getAllAmount(tempStorage)) {
                tempStorage = list;
            }
            else if (getAllAmount(list) == getAllAmount(tempStorage)) {
                if (getAllDuration(list) > getAllDuration(tempStorage)) {
                    tempStorage = list;
                }
                else if (getAllDuration(list) == getAllDuration(tempStorage)) {
                    if (list.size() < tempStorage.size()) {
                        tempStorage = new ArrayList<>(list);
                    }
                }
            }
        }

        for (int i = 0; i < list.size(); i++) {
            List<Advertisement> tempList = new ArrayList<>();
            for (int i1 = 0; i1 < list.size(); i1++) {
                if (i1 != i) {
                    tempList.add(list.get(i1));
                }
            }
            if (!tempList.isEmpty())
                search(tempList);
        }

        return new ArrayList<>(tempStorage);
    }

    private int getAllDuration(List<Advertisement> listVideos) {
        int allDuration = 0;
        for (Advertisement video : listVideos) {
            allDuration += video.getDuration();
        }
        return allDuration;
    }

    private long getAllAmount(List<Advertisement> listVideos) {
        long allAmount = 0;
        for (Advertisement video : listVideos) {
            allAmount += video.getAmountPerOneDisplaying();
        }
        return allAmount;
    }
}
