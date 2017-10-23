package uio.androidbootcamp.storagedatabaseusingdbflow.databases;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by jrodri on 7/7/17.
 */

//Databases with dbflow tutorial
//https://guides.codepath.com/android/DBFlow-Guide
@Database(name = TestApplicationDatabase.NAME, version = TestApplicationDatabase.VERSION)
public class TestApplicationDatabase {

    public static final String NAME = "TestApplicationDatabase";

    public static final int VERSION = 1;
}
