package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.apache.logging.log4j.message.StringMapMessage;

public class PoolMetrixExample
{

    private static final Logger logger = LogManager.getLogger(PoolMetrixExample.class);
    private static final Marker poolMarker = MarkerManager.getMarker("POOL");
    private static final Marker poolDetailMarker = MarkerManager.getMarker("POOLDETAIL");


    public static void main(String[] args) {

        System.out.println("Start");

        for (int i = 0; i < 5; i++)
        {
            recordPoolData(i);
            recordPoolDetailData(i);
        }

        System.out.println("End");

    }

    // Recording Data for Pool structure that has field1 and field2
    public static final void recordPoolData(int avalue)
    {

        StringMapMessage msg = new StringMapMessage(2);
        msg.with("field1", "POOL- " + avalue + " -" + System.currentTimeMillis());
//        msg.with("field2", new java.sql.Timestamp(System.currentTimeMillis()));
        logger.debug(poolMarker, msg);
    }

    public static final void recordPoolDetailData(int avalue)
    {
        StringMapMessage msg = new StringMapMessage(2);
        msg.with("field1", "POOLDetail- " + avalue + " -" + System.currentTimeMillis());
//        msg.with("field2", new java.sql.Timestamp(System.currentTimeMillis()));
        logger.debug(poolDetailMarker, msg);
    }
}
