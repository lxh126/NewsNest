package com.app.luoxueheng.entity;

public class PreferenceInfo {
    private int preference_id;
    private String preferencetype;

    public PreferenceInfo(int preference_id, String preferencetype) {
        this.preference_id = preference_id;
        this.preferencetype = preferencetype;
    }

    public int getPreference_id() {
        return preference_id;
    }

    public void setPreference_id(int preference_id) {
        this.preference_id = preference_id;
    }

    public String getpreferencetype() {
        return preferencetype;
    }

    public void setpreferencetype(String newsID) {
        this.preferencetype= preferencetype;
    }


}