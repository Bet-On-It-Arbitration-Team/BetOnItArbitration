package com.example.betonit;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.Date;

@ParseClassName("Case")
public class Case extends ParseObject
{
    public static final String KEY_CASE_STATUS = "case_Status";
    public static final String KEY_CASE_ARBITRATOR = "case_Arbitrator";
    public static final String KEY_CASE_BET_ID = "case_Bet_ID";
    public static final String KEY_CASE_DATE_START = "case_Date_Start";
    public static final String KEY_CASE_DATE_END = "case_Date_End";
    public static final String KEY_CASE_CHALLENGER_EVIDENCE = "user1_Case";
    public static final String KEY_CASE_CHALLENGEE_EVIDENCE = "user2_Case";
    public static final String KEY_CASE_BET_WINNER = "case_Bet_Winner";
    public static final String KEY_CASE_BET_WINNER_DESCRIPTION = "case_Bet_Winner_Desc";

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

    public ParseObject getKeyCaseBetId()
    {
        return getParseObject(KEY_CASE_BET_ID);
    }

    public void setKeyCaseBetId(ParseObject betId)
    {
        put(KEY_CASE_STATUS, betId);
    }

    public Date getKeyCaseDateStart()
    {
        return getDate(KEY_CASE_DATE_START);
    }

    public void setKeyCaseDateStart(Date dateStart)
    {
        put(KEY_CASE_DATE_START, dateStart);
    }

    public Date getKeyCaseDateEnd()
    {
        return getDate(KEY_CASE_DATE_END);
    }

    public void setKeyCaseDateEnd(Date dateEnd)
    {
        put(KEY_CASE_DATE_END, dateEnd);
    }

    public String getKeyCaseChallengerEvidence()
    {
        return getString(KEY_CASE_CHALLENGER_EVIDENCE);
    }

    public void setKeyCaseChallengerEvidence(String chlngrEvid)
    {
        put(KEY_CASE_CHALLENGER_EVIDENCE, chlngrEvid);
    }

    public String getKeyCaseChallengeeEvidence()
    {
        return getString(KEY_CASE_CHALLENGEE_EVIDENCE);
    }

    public void setKeyCaseChallengeeEvidence(String challengeeEvidence)
    {
        put(KEY_CASE_CHALLENGEE_EVIDENCE, challengeeEvidence);
    }

    public ParseUser getKeyCaseBetWinner()
    {
        return getParseUser(KEY_CASE_BET_WINNER);
    }

    public void setKeyCaseBetWinner(ParseUser winner)
    {
        put(KEY_CASE_BET_WINNER, winner);
    }

    public String getKeyCaseBetWinnerDescription()
    {
        return getString(KEY_CASE_BET_WINNER_DESCRIPTION);
    }

    public void setKeyCaseBetWinnerDescription(String winnerDescription)
    {
        put(KEY_CASE_BET_WINNER_DESCRIPTION, winnerDescription);
    }

}