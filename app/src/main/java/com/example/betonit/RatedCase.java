package com.example.betonit;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.Date;

@ParseClassName("RatedCase")
public class RatedCase extends ParseObject
{
    public static final String KEY_CASE_STATUS = "case_Status";
    public static final String KEY_CASE_ARBITRATOR = "case_Arbitrator";
    public static final String KEY_RATED_CASE_ID = "rated_Case_ID";
    public static final String KEY_RATED_CASE_ISRATED = "rated_isRated";
    public static final String KEY_RATED_VALUE = "rated_Value";

    public String getKeyCaseStatus()
    {
        return getString(KEY_CASE_STATUS);
    }

    public void setKeyCaseStatus(String status)
    {
        put(KEY_CASE_STATUS, status);
    }

    public ParseUser getKeyCaseArbitrator()
    {
        return getParseUser(KEY_CASE_ARBITRATOR);
    }

    public void setKeyCaseArbitrator(ParseUser user)
    {
        put(KEY_CASE_ARBITRATOR, user);
    }

    public ParseObject getKeyRatedCaseId()
    {
        return getParseObject(KEY_RATED_CASE_ID);
    }

    public void setKeyRatedCaseId(ParseObject caseId)
    {
        put(KEY_RATED_CASE_ID, caseId);
    }

    public Boolean getKeyRatedIsRated()
    {
        return getBoolean(KEY_RATED_CASE_ISRATED);
    }

    public void setKeyRatedIsRated(Boolean isRated)
    {
        put(KEY_RATED_CASE_ID, isRated);
    }

    public int getKeyRating()
    {
        return getInt(KEY_RATED_VALUE);
    }

    public void setKeyRating(int rating)
    {
        put(KEY_RATED_VALUE, rating);
    }

}