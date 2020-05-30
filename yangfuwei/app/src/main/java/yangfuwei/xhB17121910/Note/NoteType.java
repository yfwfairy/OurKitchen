package yangfuwei.xhB17121910.Note;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum NoteType {
    DEFAULT(0,"默认分类"),
    FRIED(1,"炒菜"),
    PRINCIPLE(2,"主食"),
    STREET(3,"小吃");

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    private int value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    private NoteType(int i, String n) {
        value = i;
        name = n;
    }

    private static final Map<Integer, NoteType> lookup = new HashMap();

    public static NoteType get(int code) {
        return (NoteType)lookup.get(code);
    }

    static {
        Iterator i$ = EnumSet.allOf(NoteType.class).iterator();

        while(i$.hasNext()) {
            NoteType c = (NoteType)i$.next();
            lookup.put(c.value, c);
        }

    }
}
