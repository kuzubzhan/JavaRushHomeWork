package com.javarush.test.level39.lesson09.big01;

import com.javarush.test.level39.lesson09.big01.query.DateQuery;
import com.javarush.test.level39.lesson09.big01.query.IPQuery;
import com.javarush.test.level39.lesson09.big01.query.UserQuery;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogParser implements IPQuery, UserQuery, DateQuery {
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

    @Override
    public Set<String> getAllUsers() {
        return getSetUsersByEventOrTask(null, null, null, null, 0);
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        return getSetUsersByEventOrTask(null, null, after, before, 0).size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        List<String[]> list = getParseFile(after, before);
        Set<String> set = new LinkedHashSet<>();
        for (String[] strings : list) {
            if (strings[1].equals(user)) {
                if (strings[3].startsWith(Event.SOLVE_TASK.toString()) || strings[3].startsWith(Event.DONE_TASK.toString())) {
                    String[] slitEvent = strings[3].split("\\s");
                    set.add(slitEvent[0]);
                } else if (!strings[3].startsWith(Event.SOLVE_TASK.toString()) && !strings[3].startsWith(Event.DONE_TASK.toString())) {
                    set.add(strings[3]);
                }
            }
        }
        return set.size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        List<String[]> list = getParseFile(after, before);
        Set<String> set = new LinkedHashSet<>();
        for (String[] strings : list) {
            if (strings[0].equals(ip))
                set.add(strings[1]);
        }
        return set;
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        return getSetUsersByEventOrTask(Event.LOGIN, null, after, before, 0);
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        return getSetUsersByEventOrTask(Event.DOWNLOAD_PLUGIN, null, after, before, 0);
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        return getSetUsersByEventOrTask(Event.WRITE_MESSAGE, null, after, before, 0);
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        return getSetUsersByEventOrTask(Event.SOLVE_TASK, null, after, before, 0);
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        return getSetUsersByEventOrTask(Event.SOLVE_TASK, null, after, before, task);
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        return getSetUsersByEventOrTask(Event.DONE_TASK, null, after, before, 0);
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        return getSetUsersByEventOrTask(Event.DONE_TASK, null, after, before, task);
    }

    private Set<String> getSetUsersByEventOrTask(Event event, Status status, Date after, Date before, int task) {
        List<String[]> list = getParseFile(after, before);
        Set<String> set = new LinkedHashSet<>();
        for (String[] strings : list) {
            if (strings[3].startsWith(Event.SOLVE_TASK.toString()) || strings[3].startsWith(Event.DONE_TASK.toString())) {
                String[] slitEvent = strings[3].split("\\s");
                if (event != null) {
                    if (slitEvent[0].equals(event.toString())) {
                        if (status != null) {
                            if (strings[4].equals(status.toString())) {
                                if (task > 0) {
                                    if (slitEvent[1].equals(String.valueOf(task)))
                                        set.add(strings[1]);
                                } else set.add(strings[1]);
                            }
                        } else {
                            if (task > 0) {
                                if (slitEvent[1].equals(String.valueOf(task)))
                                    set.add(strings[1]);
                            } else set.add(strings[1]);
                        }
                    }
                } else set.add(strings[1]);
            } else if (!strings[3].startsWith(Event.SOLVE_TASK.toString()) && !strings[3].startsWith(Event.DONE_TASK.toString())) {
                if (event != null) {
                    if (strings[3].equals(event.toString())) {
                        if (status != null) {
                            if (strings[4].equals(status.toString()))
                                set.add(strings[1]);
                        } else set.add(strings[1]);
                    }
                } else set.add(strings[1]);
            }
        }
        return set;
    }

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        return getSetDatasForDiffParams(user, 0, event, null, after, before);
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        return getSetDatasForDiffParams(null, 0, null, Status.FAILED, after, before);
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        return getSetDatasForDiffParams(null, 0, null, Status.ERROR, after, before);
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        Set<Date> sortedSet = new TreeSet<>(getSetDatasForDiffParams(user, 0, Event.LOGIN, null, after, before));
        if (sortedSet.size() > 0)
            return new ArrayList<>(sortedSet).get(0);
        return null;
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        Set<Date> sortedSet = new TreeSet<>(getSetDatasForDiffParams(user, task, Event.SOLVE_TASK, null, after, before));
        if (sortedSet.size() > 0)
            return new ArrayList<>(sortedSet).get(0);
        return null;
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        Set<Date> sortedSet = new TreeSet<>(getSetDatasForDiffParams(user, task, Event.DONE_TASK, null, after, before));
        if (sortedSet.size() > 0)
            return new ArrayList<>(sortedSet).get(0);
        return null;
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        return getSetDatasForDiffParams(user, 0, Event.WRITE_MESSAGE, null, after, before);
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        return getSetDatasForDiffParams(user, 0, Event.DOWNLOAD_PLUGIN, null, after, before);
    }

    private Set<Date> getSetDatasForDiffParams(String user, int task, Event event, Status status, Date after, Date before) {
        List<String[]> list = getParseFile(after, before);
        Set<Date> set = new LinkedHashSet<>();
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        for (String[] strings : list) {
            if (strings[3].startsWith(Event.SOLVE_TASK.toString()) || strings[3].startsWith(Event.DONE_TASK.toString())) {
                String[] slitEvent = strings[3].split("\\s");
                try {
                    if (user != null) {
                        if (strings[1].equals(user)) {
                            if (task > 0) {
                                if (slitEvent[1].equals(String.valueOf(task))) {
                                    if (event != null) {
                                        if (slitEvent[0].equals(event.toString())) {
                                            if (status != null) {
                                                if (strings[4].equals(status.toString()))
                                                    set.add(df.parse(strings[2]));
                                            } else set.add(df.parse(strings[2]));
                                        }
                                    } else {
                                        if (status != null) {
                                            if (strings[4].equals(status.toString()))
                                                set.add(df.parse(strings[2]));
                                        } else set.add(df.parse(strings[2]));
                                    }
                                }
                            } else {
                                if (event != null) {
                                    if (slitEvent[0].equals(event.toString())) {
                                        if (status != null) {
                                            if (strings[4].equals(status.toString()))
                                                set.add(df.parse(strings[2]));
                                        } else set.add(df.parse(strings[2]));
                                    }
                                } else {
                                    if (status != null) {
                                        if (strings[4].equals(status.toString()))
                                            set.add(df.parse(strings[2]));
                                    } else set.add(df.parse(strings[2]));
                                }
                            }
                        }
                    } else {
                        if (task > 0) {
                            if (slitEvent[1].equals(String.valueOf(task))) {
                                if (event != null) {
                                    if (slitEvent[0].equals(event.toString())) {
                                        if (status != null) {
                                            if (strings[4].equals(status.toString()))
                                                set.add(df.parse(strings[2]));
                                        } else set.add(df.parse(strings[2]));
                                    }
                                } else {
                                    if (status != null) {
                                        if (strings[4].equals(status.toString()))
                                            set.add(df.parse(strings[2]));
                                    } else set.add(df.parse(strings[2]));
                                }
                            }
                        } else {
                            if (event != null) {
                                if (slitEvent[0].equals(event.toString())) {
                                    if (status != null) {
                                        if (strings[4].equals(status.toString()))
                                            set.add(df.parse(strings[2]));
                                    } else set.add(df.parse(strings[2]));
                                }
                            } else {
                                if (status != null) {
                                    if (strings[4].equals(status.toString()))
                                        set.add(df.parse(strings[2]));
                                } else set.add(df.parse(strings[2]));
                            }
                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else if (!strings[3].startsWith(Event.SOLVE_TASK.toString()) && !strings[3].startsWith(Event.DONE_TASK.toString())) {
                String[] slitEvent = strings[3].split("\\s");
                try {
                    if (user != null) {
                        if (strings[1].equals(user)) {
                            if (event != null) {
                                if (slitEvent[0].equals(event.toString())) {
                                    if (status != null) {
                                        if (strings[4].equals(status.toString()))
                                            set.add(df.parse(strings[2]));
                                    } else set.add(df.parse(strings[2]));
                                }
                            } else {
                                if (status != null) {
                                    if (strings[4].equals(status.toString()))
                                        set.add(df.parse(strings[2]));
                                } else set.add(df.parse(strings[2]));
                            }
                        }
                    } else {
                        if (event != null) {
                            if (slitEvent[0].equals(event.toString())) {
                                if (status != null) {
                                    if (strings[4].equals(status.toString()))
                                        set.add(df.parse(strings[2]));
                                } else set.add(df.parse(strings[2]));
                            }
                        } else {
                            if (status != null) {
                                if (strings[4].equals(status.toString()))
                                    set.add(df.parse(strings[2]));
                            } else set.add(df.parse(strings[2]));
                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        return set;
    }
}
