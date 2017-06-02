package com.javarush.test.level39.lesson09.big01;

import com.javarush.test.level39.lesson09.big01.query.IPQuery;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogParser implements IPQuery {
    private Path logDir;

    public LogParser(Path logDir) {
        this.logDir = logDir;
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        List<String[]> list = getParseFile(after, before);
        Set<String> set = new LinkedHashSet<>();
        for (String[] strings : list) {
            set.add(strings[0]);
        }
        return set;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        List<String[]> list = getParseFile(after, before);
        Set<String> set = new LinkedHashSet<>();
        for (String[] strings : list) {
            if (strings[1].equals(user))
                set.add(strings[0]);
        }
        return set;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        List<String[]> list = getParseFile(after, before);
        Set<String> set = new LinkedHashSet<>();
        for (String[] strings : list) {
            if (strings[3].startsWith(Event.SOLVE_TASK.toString()) || strings[3].startsWith(Event.DONE_TASK.toString())) {
                String[] slitEvent = strings[3].split("\\s");
                if (slitEvent[0].equals(event.toString()))
                    set.add(strings[0]);
            } else if (!strings[3].startsWith(Event.SOLVE_TASK.toString()) && !strings[3].startsWith(Event.DONE_TASK.toString())) {
                if (strings[3].equals(event.toString()))
                    set.add(strings[0]);
            }
        }
        return set;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        List<String[]> list = getParseFile(after, before);
        Set<String> set = new LinkedHashSet<>();
        for (String[] strings : list) {
            if (strings[4].equals(status.toString()))
                set.add(strings[0]);
        }
        return set;
    }

    private List<String[]> getParseFile(Date after, Date before) {
        List<String[]> list = new LinkedList<>();
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        File[] fileList = logDir.toFile().listFiles();

        if (fileList != null) {
            for (File file : fileList) {
                if (!file.isDirectory() && file.getName().endsWith(".log")) {
                    try {
                        BufferedReader fr = new BufferedReader(new FileReader(file));
                        while (fr.ready()) {
                            String[] split = fr.readLine().split("\\t");
                            Date date = df.parse(split[2]);

                            if (after == null && before == null) {
                                list.add(split);
                            } else if (after == null) {
                                if (date.compareTo(before) <= 0) {
                                    list.add(split);
                                }
                            } else if (before == null) {
                                if (date.compareTo(after) >= 0) {
                                    list.add(split);
                                }
                            } else {
                                if (date.compareTo(after) >= 0 && date.compareTo(before) <= 0) {
                                    list.add(split);
                                }
                            }
                        }
                        fr.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return list;
    }
}
