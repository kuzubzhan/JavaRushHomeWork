package com.javarush.test.level39.lesson09.big01;

import com.javarush.test.level39.lesson09.big01.query.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery {
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
        return getSetUsersForDiffParams(null, null, null, null, 0);
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        return getSetUsersForDiffParams(null, null, after, before, 0).size();
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
        return getSetUsersForDiffParams(Event.LOGIN, null, after, before, 0);
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        return getSetUsersForDiffParams(Event.DOWNLOAD_PLUGIN, null, after, before, 0);
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        return getSetUsersForDiffParams(Event.WRITE_MESSAGE, null, after, before, 0);
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        return getSetUsersForDiffParams(Event.SOLVE_TASK, null, after, before, 0);
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        return getSetUsersForDiffParams(Event.SOLVE_TASK, null, after, before, task);
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        return getSetUsersForDiffParams(Event.DONE_TASK, null, after, before, 0);
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        return getSetUsersForDiffParams(Event.DONE_TASK, null, after, before, task);
    }

    private Set<String> getSetUsersForDiffParams(Event event, Status status, Date after, Date before, int task) {
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
        return getSetDatesForDiffParams(user, 0, event, null, after, before);
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        return getSetDatesForDiffParams(null, 0, null, Status.FAILED, after, before);
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        return getSetDatesForDiffParams(null, 0, null, Status.ERROR, after, before);
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        Set<Date> sortedSet = new TreeSet<>(getSetDatesForDiffParams(user, 0, Event.LOGIN, null, after, before));
        if (sortedSet.size() > 0)
            return new ArrayList<>(sortedSet).get(0);
        return null;
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        Set<Date> sortedSet = new TreeSet<>(getSetDatesForDiffParams(user, task, Event.SOLVE_TASK, null, after, before));
        if (sortedSet.size() > 0)
            return new ArrayList<>(sortedSet).get(0);
        return null;
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        Set<Date> sortedSet = new TreeSet<>(getSetDatesForDiffParams(user, task, Event.DONE_TASK, null, after, before));
        if (sortedSet.size() > 0)
            return new ArrayList<>(sortedSet).get(0);
        return null;
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        return getSetDatesForDiffParams(user, 0, Event.WRITE_MESSAGE, null, after, before);
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        return getSetDatesForDiffParams(user, 0, Event.DOWNLOAD_PLUGIN, null, after, before);
    }

    private Set<Date> getSetDatesForDiffParams(String user, int task, Event event, Status status, Date after, Date before) {
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

    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        return getAllEvents(after, before).size();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        List<String[]> list = getParseFile(after, before);
        Set<Event> set = new LinkedHashSet<>();
        for (String[] strings : list) {
            if (strings[3].startsWith(Event.SOLVE_TASK.toString()) || strings[3].startsWith(Event.DONE_TASK.toString())) {
                String[] slitEvent = strings[3].split("\\s");
                if (slitEvent[0].equals(Event.SOLVE_TASK.toString()) || slitEvent[0].equals(Event.DONE_TASK.toString()))
                    set.add(Event.valueOf(slitEvent[0]));
            } else if (!strings[3].startsWith(Event.SOLVE_TASK.toString()) && !strings[3].startsWith(Event.DONE_TASK.toString())) {
                set.add(Event.valueOf(strings[3]));
            }
        }
        return set;
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        List<String[]> list = getParseFile(after, before);
        Set<Event> set = new LinkedHashSet<>();
        for (String[] strings : list) {
            if (strings[3].startsWith(Event.SOLVE_TASK.toString()) || strings[3].startsWith(Event.DONE_TASK.toString())) {
                String[] slitEvent = strings[3].split("\\s");
                if (slitEvent[0].equals(Event.SOLVE_TASK.toString()) || slitEvent[0].equals(Event.DONE_TASK.toString())) {
                    if (strings[0].equals(ip))
                        set.add(Event.valueOf(slitEvent[0]));
                }
            } else if (!strings[3].startsWith(Event.SOLVE_TASK.toString()) && !strings[3].startsWith(Event.DONE_TASK.toString())) {
                if (strings[0].equals(ip))
                    set.add(Event.valueOf(strings[3]));
            }
        }
        return set;
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        List<String[]> list = getParseFile(after, before);
        Set<Event> set = new LinkedHashSet<>();
        for (String[] strings : list) {
            if (strings[3].startsWith(Event.SOLVE_TASK.toString()) || strings[3].startsWith(Event.DONE_TASK.toString())) {
                String[] slitEvent = strings[3].split("\\s");
                if (slitEvent[0].equals(Event.SOLVE_TASK.toString()) || slitEvent[0].equals(Event.DONE_TASK.toString())) {
                    if (strings[1].equals(user))
                        set.add(Event.valueOf(slitEvent[0]));
                }
            } else if (!strings[3].startsWith(Event.SOLVE_TASK.toString()) && !strings[3].startsWith(Event.DONE_TASK.toString())) {
                if (strings[1].equals(user))
                    set.add(Event.valueOf(strings[3]));
            }
        }
        return set;
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        List<String[]> list = getParseFile(after, before);
        Set<Event> set = new LinkedHashSet<>();
        for (String[] strings : list) {
            if (strings[3].startsWith(Event.SOLVE_TASK.toString()) || strings[3].startsWith(Event.DONE_TASK.toString())) {
                String[] slitEvent = strings[3].split("\\s");
                if (slitEvent[0].equals(Event.SOLVE_TASK.toString()) || slitEvent[0].equals(Event.DONE_TASK.toString())) {
                    if (strings[4].equals(Status.FAILED.toString()))
                        set.add(Event.valueOf(slitEvent[0]));
                }
            } else if (!strings[3].startsWith(Event.SOLVE_TASK.toString()) && !strings[3].startsWith(Event.DONE_TASK.toString())) {
                if (strings[4].equals(Status.FAILED.toString()))
                    set.add(Event.valueOf(strings[3]));
            }
        }
        return set;
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        List<String[]> list = getParseFile(after, before);
        Set<Event> set = new LinkedHashSet<>();
        for (String[] strings : list) {
            if (strings[3].startsWith(Event.SOLVE_TASK.toString()) || strings[3].startsWith(Event.DONE_TASK.toString())) {
                String[] slitEvent = strings[3].split("\\s");
                if (slitEvent[0].equals(Event.SOLVE_TASK.toString()) || slitEvent[0].equals(Event.DONE_TASK.toString())) {
                    if (strings[4].equals(Status.ERROR.toString()))
                        set.add(Event.valueOf(slitEvent[0]));
                }
            } else if (!strings[3].startsWith(Event.SOLVE_TASK.toString()) && !strings[3].startsWith(Event.DONE_TASK.toString())) {
                if (strings[4].equals(Status.ERROR.toString()))
                    set.add(Event.valueOf(strings[3]));
            }
        }
        return set;
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        List<String[]> list = getParseFile(after, before);
        List<Event> eventList = new LinkedList<>();
        for (String[] strings : list) {
            if (strings[3].startsWith(Event.SOLVE_TASK.toString())) {
                String[] slitEvent = strings[3].split("\\s");
                if (slitEvent[0].equals(Event.SOLVE_TASK.toString()) && slitEvent[1].equals(String.valueOf(task)))
                    eventList.add(Event.valueOf(slitEvent[0]));
            }
        }
        return eventList.size();
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        List<String[]> list = getParseFile(after, before);
        List<Event> eventList = new LinkedList<>();
        for (String[] strings : list) {
            if (strings[3].startsWith(Event.SOLVE_TASK.toString()) && strings[4].equals(Status.OK.toString())) {
                String[] slitEvent = strings[3].split("\\s");
                if (slitEvent[0].equals(Event.SOLVE_TASK.toString())) {
                    if (slitEvent[1].equals(String.valueOf(task)))
                        eventList.add(Event.valueOf(slitEvent[0]));
                }
            }
        }
        return eventList.size();
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        List<String[]> list = getParseFile(after, before);
        Map<Integer, Integer> map = new LinkedHashMap<>();
        for (String[] strings : list) {
            if (strings[3].startsWith(Event.SOLVE_TASK.toString())) {
                String[] slitEvent = strings[3].split("\\s");
                if (slitEvent[0].equals(Event.SOLVE_TASK.toString())) {
                    if (!map.containsKey(Integer.valueOf(slitEvent[1]))) {
                        map.put(Integer.valueOf(slitEvent[1]), 1);
                    } else {
                        map.put(Integer.valueOf(slitEvent[1]), map.get(Integer.valueOf(slitEvent[1])) + 1);
                    }
                }
            }
        }
        return map;
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        List<String[]> list = getParseFile(after, before);
        Map<Integer, Integer> map = new LinkedHashMap<>();
        for (String[] strings : list) {
            if (strings[3].startsWith(Event.DONE_TASK.toString())) {
                String[] slitEvent = strings[3].split("\\s");
                if (slitEvent[0].equals(Event.DONE_TASK.toString())) {
                    if (!map.containsKey(Integer.valueOf(slitEvent[1]))) {
                        map.put(Integer.valueOf(slitEvent[1]), 1);
                    } else {
                        map.put(Integer.valueOf(slitEvent[1]), map.get(Integer.valueOf(slitEvent[1])) + 1);
                    }
                }
            }
        }
        return map;
    }

    @Override
    public Set<Object> execute(String query) {
        Set<Object> set = new LinkedHashSet<>();
        if (query.split("\\s").length == 2) {
            switch (query) {
                case "get ip":
                    set.addAll(getUniqueIPs(null, null));
                    return set;
                case "get user":
                    set.addAll(getAllUsers());
                    return set;
                case "get date":
                    set.addAll(getSetDatesForDiffParams(null, 0, null, null, null, null));
                    return set;
                case "get event":
                    set.addAll(getAllEvents(null, null));
                    return set;
                case "get status":
                    set.addAll(getAllStatus(null, null));
                    return set;
            }
        } else if (query.split("\\s").length > 2) {
            String[] splStr = query.split("\"");
            String[] fields = splStr[0].split("\\s");
            String field1 = fields[1];
            String field2 = fields[3];
            String value1 = splStr[1];
            List<String[]> list;
            if (!query.matches(".+and date between.+")) {
                list = getParseFile(null, null);
            } else {
                DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                Date after = null;
                Date before = null;
                try {
                    after = df.parse(splStr[3]);
                    before = df.parse(splStr[5]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                list = getParseFile(after, before);
            }

            for (String[] strings : list) {
                if (compareObjects(strings, value1, field2))
                    set.add(getObject(strings, field1));
            }
        }
        return set;
    }

    private Set<Status> getAllStatus(Date after, Date before) {
        List<String[]> list = getParseFile(after, before);
        Set<Status> set = new LinkedHashSet<>();
        for (String[] strings : list) {
            set.add(Status.valueOf(strings[4]));
        }
        return set;
    }

    private int getFieldNumber(String field) {
        if (field != null) {
            switch (field) {
                case "ip":
                    return 0;
                case "user":
                    return 1;
                case "date":
                    return 2;
                case "event":
                    return 3;
                case "status":
                    return 4;
            }
        }
        return -1;
    }

    private Object getObject(String[] fieldArr, String field) {
        if (fieldArr != null && field != null) {
            DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            String str = fieldArr[getFieldNumber(field)];
            switch (field) {
                case "ip":
                case "user":
                    return str;
                case "date":
                    try {
                        return df.parse(str);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
                case "event":
                    if (!str.matches("SOLVE_TASK\\s\\d+") && !str.matches("DONE_TASK\\s\\d+")) {
                        if (checkStr(str, field))
                            return Event.valueOf(str);
                    } else {
                        String[] slitEvent = str.split("\\s");
                        if (slitEvent.length == 2) {
                            if (checkStr(slitEvent[0], field))
                                return Event.valueOf(slitEvent[0]);
                        }
                    }
                    break;
                case "status":
                    if (checkStr(str, field))
                        return Status.valueOf(str);
            }
        }
        return null;
    }

    private boolean compareObjects(String[] fieldArr, String s, String field) {
        if (fieldArr != null && s != null && field != null) {
            String str = fieldArr[getFieldNumber(field)];
            switch (field) {
                case "ip":
                case "user":
                case "date":
                    return str.equals(s);
                case "event":
                    String[] slitEvent = str.split("\\s");
                    if (slitEvent.length == 2)
                        return slitEvent[0].equals(s);
                    return str.equals(s);
                case "status":
                    return str.equals(s);
            }
        }
        return false;
    }

    private boolean checkStr(String o, String cast) {
        boolean result = false;
        if (o != null && cast != null) {
            switch (cast) {
                case "event":
                    for (Event event : Event.values()) {
                        if (event.toString().equals(o)) {
                            result = true;
                            break;
                        }
                    }
                    break;
                case "status":
                    for (Status status : Status.values()) {
                        if (status.toString().equals(o)) {
                            result = true;
                            break;
                        }
                    }
                    break;
            }
        }
        return result;
    }
}
