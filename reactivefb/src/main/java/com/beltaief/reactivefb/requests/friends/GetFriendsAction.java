package com.beltaief.reactivefb.requests.friends;

import android.os.Bundle;
import android.util.Log;

import com.beltaief.reactivefb.SessionManager;
import com.beltaief.reactivefb.models.Profile;
import com.beltaief.reactivefb.models.Profile.Properties;
import com.beltaief.reactivefb.requests.common.GetAction;
import com.beltaief.reactivefb.util.GraphPath;
import com.beltaief.reactivefb.util.JsonUtils;
import com.facebook.GraphResponse;

import org.json.JSONException;

import java.util.List;

class GetFriendsAction extends GetAction<List<Profile>> {

    private static final String TAG = GetFriendsAction.class.getSimpleName();
    private Properties mProperties;

    GetFriendsAction(SessionManager sessionManager) {
        super(sessionManager);
    }

    @Override
    protected String getGraphPath() {
        return String.format("%s/%s", getTarget(), GraphPath.FRIENDS);
    }

    @Override
    protected Bundle getBundle() {
        if (mProperties != null) {
            return mProperties.getBundle();
        }
        return null;
    }

    @Override
    protected List<Profile> processResponse(GraphResponse response) {
        Exception el;
        try {
            return JsonUtils.parseFriends(response.getRawResponse());
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage()+"");
            el = e;
        }
        throw new RuntimeException("Exception while serializing a list of Profile.class :" + el.getMessage());
    }

}